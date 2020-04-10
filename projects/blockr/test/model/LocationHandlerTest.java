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
        ModelWhileIfBlock whileB = new ModelWhileIfBlock(new ProgramLocation(100,100), false);
        LH.setLocationBlock(whileB, new ProgramLocation(200,200));
        assertEquals(new ProgramLocation(200, 200), whileB.getPos());
    }

    @Test
    public void updateLocationBlocksTop() {
        LocationHandler LH = new LocationHandler();
        ModelBlock rightB = new ModelActionBlock(new ProgramLocation(100,100), null);
        ModelBlock leftB = new ModelActionBlock(new ProgramLocation(200,200), null);
        ModelBlock forwardB = new ModelActionBlock(new ProgramLocation(500,500), null);
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
        ModelBlock rightB = new ModelActionBlock(new ProgramLocation(100,100), null);
        ModelBlock leftB = new ModelActionBlock(new ProgramLocation(200,200), null);
        ModelBlock forwardB = new ModelActionBlock(new ProgramLocation(500,500), null);
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
        ModelWhileIfBlock whileB = new ModelWhileIfBlock(new ProgramLocation(100,100), false);
        ModelBlock rightB = new ModelActionBlock(new ProgramLocation(200,200), null);
        ModelBlock leftB = new ModelActionBlock(new ProgramLocation(300,300), null);
        ModelBlock forwardB = new ModelActionBlock(new ProgramLocation(500,500), null);
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
        ModelWhileIfBlock whileB = new ModelWhileIfBlock(new ProgramLocation(100,100), false);
        ModelBlock rightB = new ModelActionBlock(new ProgramLocation(200,200), null);
        ModelBlock leftB = new ModelActionBlock(new ProgramLocation(300,300), null);
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
        ModelBlock rightB = new ModelActionBlock(new ProgramLocation(100,100), null);
        ModelBlock leftB = new ModelActionBlock(new ProgramLocation(300,300), null);
        rightB.setBottomPlug(leftB);
        leftB.setTopSocket(rightB);
        LH.setTopSocketLocation(leftB,rightB);
        assertEquals(new ProgramLocation(100,180), leftB.getPos());
    }

    @Test
    public void setTopSocketLocationCavity() {
        LocationHandler LH = new LocationHandler();
        ModelWhileIfBlock whileB = new ModelWhileIfBlock(new ProgramLocation(100,100), false);
        ModelBlock rightB = new ModelActionBlock(new ProgramLocation(200,200), null);
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
        ModelBlock notB = new ModelNotBlock(new ProgramLocation(100,100));
        ModelBlock wifB = new ModelPredicateBlock(new ProgramLocation (500, 500), null);
        notB.setRightSocket(wifB);
        wifB.setLeftPlug(notB);
        LH.setLeftPlugLocation(wifB, notB);
        assertEquals(new ProgramLocation(180,100), wifB.getPos());
    }

    @Test
    public void findClosestBlock() {
        LocationHandler LH = new LocationHandler();
        ModelBlock notB = new ModelNotBlock(new ProgramLocation(200,200));
        ModelBlock wifB = new ModelPredicateBlock(new ProgramLocation (300, 210), null);
        ModelBlock rightB = new ModelActionBlock(new ProgramLocation(200,280), null);
        ModelBlock leftB = new ModelActionBlock(new ProgramLocation(280,200), null);
        ModelBlock forwardB = new ModelActionBlock(new ProgramLocation(180,120), null);
        ModelWhileIfBlock whileB = new ModelWhileIfBlock(new ProgramLocation(110, 210), false);
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