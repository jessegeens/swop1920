package model;

import model.blocks.*;
import org.junit.Test;
import utilities.*;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class LocationHandlerTest {

    @Test
    public void setLocationBlock(){
        LocationHandler LH = new LocationHandler();
        ModelWhileIfBlock whileB = new ModelWhileIfBlock(new ProgramLocation(100,100), BlockType.WHILE);
        LH.setLocationBlock(whileB, new ProgramLocation(200,200));
        assertEquals(new ProgramLocation(200, 200), whileB.getPos());
    }

    @Test
    public void updateLocationBlocksTop() {
        LocationHandler LH = new LocationHandler();
        ModelBlock rightB = new ModelActionBlock(new ProgramLocation(100,100), BlockType.ACTION, null);
        ModelBlock leftB = new ModelActionBlock(new ProgramLocation(200,200), BlockType.ACTION, null);
        ModelBlock forwardB = new ModelActionBlock(new ProgramLocation(500,500), BlockType.ACTION, null);
        rightB.setBottomPlug(leftB);
        leftB.setTopSocket(rightB);
        leftB.setBottomPlug(forwardB);
        forwardB.setTopSocket(leftB);
        LH.updateLocationBlocks(new ArrayList<ModelBlock>(Arrays.asList(rightB)));
        assertEquals(new ProgramLocation(100,260), forwardB.getPos());
    }

    @Test
    public void updateLocationBlocksMiddle() {
        LocationHandler LH = new LocationHandler();
        ModelBlock rightB = new ModelActionBlock(new ProgramLocation(100,100), BlockType.ACTION, null);
        ModelBlock leftB = new ModelActionBlock(new ProgramLocation(200,200), BlockType.ACTION, null);
        ModelBlock forwardB = new ModelActionBlock(new ProgramLocation(500,500), BlockType.ACTION, null);
        rightB.setBottomPlug(leftB);
        leftB.setTopSocket(rightB);
        leftB.setBottomPlug(forwardB);
        forwardB.setTopSocket(leftB);
        LH.updateLocationBlocks(new ArrayList<ModelBlock>(Arrays.asList(forwardB)));
        assertEquals(new ProgramLocation(200,200), leftB.getPos());
    }

    @Test
    public void updateLocationBlocksWhile() {
        LocationHandler LH = new LocationHandler();
        ModelWhileIfBlock whileB = new ModelWhileIfBlock(new ProgramLocation(100,100), BlockType.WHILE);
        ModelBlock rightB = new ModelActionBlock(new ProgramLocation(200,200), BlockType.ACTION, null);
        ModelBlock leftB = new ModelActionBlock(new ProgramLocation(300,300), BlockType.ACTION, null);
        ModelBlock forwardB = new ModelActionBlock(new ProgramLocation(500,500), BlockType.ACTION, null);
        whileB.setCavityPlug(rightB);
        rightB.setTopSocket(whileB);
        rightB.setBottomPlug(leftB);
        leftB.setTopSocket(rightB);
        leftB.setBottomPlug(whileB);
        whileB.setCavitySocket(leftB);
        forwardB.setTopSocket(whileB);
        whileB.setBottomPlug(forwardB);
        LH.updateLocationBlocks(new ArrayList<ModelBlock>(Arrays.asList(whileB)));
        assertEquals(new ProgramLocation(100,340), forwardB.getPos());
    }

    @Test
    public void updateCavityBlocksLocations() {
        LocationHandler LH = new LocationHandler();
        ModelWhileIfBlock whileB = new ModelWhileIfBlock(new ProgramLocation(100,100), BlockType.WHILE);
        ModelBlock rightB = new ModelActionBlock(new ProgramLocation(200,200), BlockType.ACTION, null);
        ModelBlock leftB = new ModelActionBlock(new ProgramLocation(300,300), BlockType.ACTION, null);
        whileB.setCavityPlug(rightB);
        rightB.setTopSocket(whileB);
        rightB.setBottomPlug(leftB);
        leftB.setTopSocket(rightB);
        leftB.setBottomPlug(whileB);
        whileB.setCavitySocket(leftB);
        LH.updateCavityBlocksLocations(whileB);
        assertEquals(new ProgramLocation(113,223), leftB.getPos());
    }

    @Test
    public void setTopSocketLocation() {
        LocationHandler LH = new LocationHandler();
        ModelBlock rightB = new ModelActionBlock(new ProgramLocation(100,100), BlockType.ACTION, null);
        ModelBlock leftB = new ModelActionBlock(new ProgramLocation(300,300), BlockType.ACTION, null);
        rightB.setBottomPlug(leftB);
        leftB.setTopSocket(rightB);
        LH.setTopSocketLocation(leftB,rightB);
        assertEquals(new ProgramLocation(100,180), leftB.getPos());
    }

    @Test
    public void setTopSocketLocationCavity() {
        LocationHandler LH = new LocationHandler();
        ModelWhileIfBlock whileB = new ModelWhileIfBlock(new ProgramLocation(100,100), BlockType.WHILE);
        ModelBlock rightB = new ModelActionBlock(new ProgramLocation(200,200), BlockType.ACTION, null);
        whileB.setCavityPlug(rightB);
        rightB.setTopSocket(whileB);
        rightB.setBottomPlug(whileB);
        whileB.setCavitySocket(rightB);
        LH.setTopSocketLocation(rightB, whileB);
        assertEquals(new ProgramLocation(113, 143), rightB.getPos());
    }

    @Test
    public void setLeftPlugLocation() {
        LocationHandler LH = new LocationHandler();
        ModelBlock notB = new ModelNotBlock(new ProgramLocation(100,100), BlockType.NOT);
        ModelBlock wifB = new ModelPredicateBlock(new ProgramLocation (500, 500), BlockType.PREDICATE, null);
        notB.setRightSocket(wifB);
        wifB.setLeftPlug(notB);
        LH.setLeftPlugLocation(wifB, notB);
        assertEquals(new ProgramLocation(180,100), wifB.getPos());
    }

    @Test
    public void findClosestBlock() {
        LocationHandler LH = new LocationHandler();
        ModelBlock notB = new ModelNotBlock(new ProgramLocation(200,200), BlockType.NOT);
        ModelBlock wifB = new ModelPredicateBlock(new ProgramLocation (300, 210), BlockType.PREDICATE, null);
        ModelBlock rightB = new ModelActionBlock(new ProgramLocation(200,280), BlockType.ACTION, null);
        ModelBlock leftB = new ModelActionBlock(new ProgramLocation(280,200), BlockType.ACTION, null);
        ModelBlock forwardB = new ModelActionBlock(new ProgramLocation(180,120), BlockType.ACTION, null);
        ModelWhileIfBlock whileB = new ModelWhileIfBlock(new ProgramLocation(110, 210), BlockType.WHILE);
        ArrayList<ModelBlock> blocks = new ArrayList<>();
        blocks.add(notB);
        blocks.add(wifB);
        blocks.add(rightB);
        blocks.add(leftB);
        blocks.add(forwardB);
        blocks.add(whileB);
        ModelBlock closest = LH.findClosestBlock(notB, blocks);
        assertEquals(whileB, closest);
    }
}