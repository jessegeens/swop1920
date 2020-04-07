package model;

import model.blocks.*;
import org.junit.Test;
import utilities.*;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class LocationHandlerTest {

    @Test
    public void updateLocationBlocksTop() {
        LocationHandler LH = new LocationHandler();
        ModelBlock rightB = new ModelMoveBlock(new Location(100,100), BlockType.TURNRIGHT);
        ModelBlock leftB = new ModelMoveBlock(new Location(200,200), BlockType.TURNLEFT);
        ModelBlock forwardB = new ModelMoveBlock(new Location(500,500), BlockType.MOVEFORWARD);
        rightB.setBottomPlug(leftB);
        leftB.setTopSocket(rightB);
        leftB.setBottomPlug(forwardB);
        forwardB.setTopSocket(leftB);
        LH.updateLocationBlocks(new ArrayList<ModelBlock>(Arrays.asList(rightB)));
        assertEquals(new Location(100,260), forwardB.getPos());
    }

    @Test
    public void updateLocationBlocksMiddle() {
        LocationHandler LH = new LocationHandler();
        ModelBlock rightB = new ModelMoveBlock(new Location(100,100), BlockType.TURNRIGHT);
        ModelBlock leftB = new ModelMoveBlock(new Location(200,200), BlockType.TURNLEFT);
        ModelBlock forwardB = new ModelMoveBlock(new Location(500,500), BlockType.MOVEFORWARD);
        rightB.setBottomPlug(leftB);
        leftB.setTopSocket(rightB);
        leftB.setBottomPlug(forwardB);
        forwardB.setTopSocket(leftB);
        LH.updateLocationBlocks(new ArrayList<ModelBlock>(Arrays.asList(forwardB)));
        assertEquals(new Location(200,200), leftB.getPos());
    }

    @Test
    public void updateLocationBlocksWhile() {
        LocationHandler LH = new LocationHandler();
        ModelWhileIfBlock whileB = new ModelWhileIfBlock(new Location(100,100), BlockType.WHILE);
        ModelBlock rightB = new ModelMoveBlock(new Location(200,200), BlockType.TURNRIGHT);
        ModelBlock leftB = new ModelMoveBlock(new Location(300,300), BlockType.TURNLEFT);
        ModelBlock forwardB = new ModelMoveBlock(new Location(500,500), BlockType.MOVEFORWARD);
        whileB.setCavityPlug(rightB);
        rightB.setTopSocket(whileB);
        rightB.setBottomPlug(leftB);
        leftB.setTopSocket(rightB);
        leftB.setBottomPlug(whileB);
        whileB.setCavitySocket(leftB);
        forwardB.setTopSocket(whileB);
        whileB.setBottomPlug(forwardB);
        LH.updateLocationBlocks(new ArrayList<ModelBlock>(Arrays.asList(whileB)));
        assertEquals(new Location(100,340), forwardB.getPos());
    }

    @Test
    public void updateCavityBlocksLocations() {
        LocationHandler LH = new LocationHandler();
        ModelWhileIfBlock whileB = new ModelWhileIfBlock(new Location(100,100), BlockType.WHILE);
        ModelBlock rightB = new ModelMoveBlock(new Location(200,200), BlockType.TURNRIGHT);
        ModelBlock leftB = new ModelMoveBlock(new Location(300,300), BlockType.TURNLEFT);
        whileB.setCavityPlug(rightB);
        rightB.setTopSocket(whileB);
        rightB.setBottomPlug(leftB);
        leftB.setTopSocket(rightB);
        leftB.setBottomPlug(whileB);
        whileB.setCavitySocket(leftB);
        LH.updateCavityBlocksLocations(whileB);
        assertEquals(new Location(113,223), leftB.getPos());
    }

    @Test
    public void setTopSocketLocation() {
        LocationHandler LH = new LocationHandler();
        ModelBlock rightB = new ModelMoveBlock(new Location(100,100), BlockType.TURNRIGHT);
        ModelBlock leftB = new ModelMoveBlock(new Location(300,300), BlockType.TURNLEFT);
        rightB.setBottomPlug(leftB);
        leftB.setTopSocket(rightB);
        LH.setTopSocketLocation(leftB,rightB);
        assertEquals(new Location(100,180), leftB.getPos());
    }

    @Test
    public void setTopSocketLocationCavity() {
        LocationHandler LH = new LocationHandler();
        ModelWhileIfBlock whileB = new ModelWhileIfBlock(new Location(100,100), BlockType.WHILE);
        ModelBlock rightB = new ModelMoveBlock(new Location(200,200), BlockType.TURNRIGHT);
        whileB.setCavityPlug(rightB);
        rightB.setTopSocket(whileB);
        rightB.setBottomPlug(whileB);
        whileB.setCavitySocket(rightB);
        LH.setTopSocketLocation(rightB, whileB);
        assertEquals(new Location(113, 143), rightB.getPos());
    }

    @Test
    public void setLeftPlugLocation() {
        LocationHandler LH = new LocationHandler();
        ModelBlock notB = new ModelNotBlock(new Location(100,100), BlockType.NOT);
        ModelBlock wifB = new ModelWallInFrontBlock(new Location (500, 500), BlockType.WALLINFRONT);
        notB.setRightSocket(wifB);
        wifB.setLeftPlug(notB);
        LH.setLeftPlugLocation(wifB, notB);
        assertEquals(new Location(180,100), wifB.getPos());
    }

    @Test
    public void findClosestBlock() {
        LocationHandler LH = new LocationHandler();
        ModelBlock notB = new ModelNotBlock(new Location(200,200), BlockType.NOT);
        ModelBlock wifB = new ModelWallInFrontBlock(new Location (300, 210), BlockType.WALLINFRONT);
        ModelBlock rightB = new ModelMoveBlock(new Location(200,280), BlockType.TURNRIGHT);
        ModelBlock leftB = new ModelMoveBlock(new Location(280,200), BlockType.TURNLEFT);
        ModelBlock forwardB = new ModelMoveBlock(new Location(180,120), BlockType.MOVEFORWARD);
        ModelWhileIfBlock whileB = new ModelWhileIfBlock(new Location(110, 210), BlockType.WHILE);
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