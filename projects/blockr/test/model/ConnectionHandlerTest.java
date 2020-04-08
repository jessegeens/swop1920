package model;

import static org.junit.Assert.*;

import model.blocks.*;
import org.junit.Test;

import utilities.*;

import java.util.ArrayList;

public class ConnectionHandlerTest {

    @Test
    public void disconnectTopSocket() {
        ConnectionHandler CC = new ConnectionHandler();
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(100,100), BlockType.MOVEFORWARD);
        ModelMoveBlock leftBlock = new ModelMoveBlock(new Location(100,20), BlockType.TURNLEFT);
        forwardBlock.setTopSocket(leftBlock);
        leftBlock.setBottomPlug(forwardBlock);
        CC.disconnect(forwardBlock);
        assertEquals(null, forwardBlock.getTopSocket());
    }

    @Test
    public void disconnectTopSocketInverse() {
        ConnectionHandler CC = new ConnectionHandler();
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(100,100), BlockType.MOVEFORWARD);
        ModelMoveBlock leftBlock = new ModelMoveBlock(new Location(100,20), BlockType.TURNLEFT);
        forwardBlock.setTopSocket(leftBlock);
        leftBlock.setBottomPlug(forwardBlock);
        CC.disconnect(leftBlock);
        assertEquals(null, forwardBlock.getTopSocket());
    }

    @Test
    public void disconnectBottomPlug() {
        ConnectionHandler CC = new ConnectionHandler();
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(100,100), BlockType.MOVEFORWARD);
        ModelMoveBlock leftBlock = new ModelMoveBlock(new Location(100,20), BlockType.TURNLEFT);
        forwardBlock.setBottomPlug(leftBlock);
        leftBlock.setTopSocket(forwardBlock);
        CC.disconnect(forwardBlock);
        assertEquals(null, forwardBlock.getBottomPlug());
    }

    @Test
    public void disconnectBottomPlugInverse() {
        ConnectionHandler CC = new ConnectionHandler();
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(100,100), BlockType.MOVEFORWARD);
        ModelMoveBlock leftBlock = new ModelMoveBlock(new Location(100,20), BlockType.TURNLEFT);
        forwardBlock.setBottomPlug(leftBlock);
        leftBlock.setTopSocket(forwardBlock);
        CC.disconnect(leftBlock);
        assertEquals(null, forwardBlock.getBottomPlug());
    }

    @Test
    public void disconnectRightSocket() {
        ConnectionHandler CC = new ConnectionHandler();
        ModelNotBlock notBlock = new ModelNotBlock(new Location(100,100), BlockType.NOT);
        ModelWallInFrontBlock wifBlock = new ModelWallInFrontBlock(new Location(100,20), BlockType.WALLINFRONT);
        notBlock.setRightSocket(wifBlock);
        wifBlock.setLeftPlug(notBlock);
        CC.disconnect(notBlock);
        assertEquals(null, notBlock.getRightSocket());
    }

    @Test
    public void disconnectRightSocketInverse() {
        ConnectionHandler CC = new ConnectionHandler();
        ModelNotBlock notBlock = new ModelNotBlock(new Location(100,100), BlockType.NOT);
        ModelWallInFrontBlock wifBlock = new ModelWallInFrontBlock(new Location(100,20), BlockType.WALLINFRONT);
        notBlock.setRightSocket(wifBlock);
        wifBlock.setLeftPlug(notBlock);
        CC.disconnect(wifBlock);
        assertEquals(null, notBlock.getRightSocket());
    }

    @Test
    public void disconnectLeftPlug() {
        ConnectionHandler CC = new ConnectionHandler();
        ModelNotBlock notBlock = new ModelNotBlock(new Location(100,100), BlockType.NOT);
        ModelWallInFrontBlock wifBlock = new ModelWallInFrontBlock(new Location(100,20), BlockType.WALLINFRONT);
        notBlock.setRightSocket(wifBlock);
        wifBlock.setLeftPlug(notBlock);
        CC.disconnect(wifBlock);
        assertEquals(null, wifBlock.getLeftPlug());
    }

    @Test
    public void disconnectLeftPlugInverse() {
        ConnectionHandler CC = new ConnectionHandler();
        ModelNotBlock notBlock = new ModelNotBlock(new Location(100,100), BlockType.NOT);
        ModelWallInFrontBlock wifBlock = new ModelWallInFrontBlock(new Location(100,20), BlockType.WALLINFRONT);
        notBlock.setRightSocket(wifBlock);
        wifBlock.setLeftPlug(notBlock);
        CC.disconnect(notBlock);
        assertEquals(null, wifBlock.getLeftPlug());
    }

    @Test
    public void disconnectCavity(){
        ConnectionHandler CC = new ConnectionHandler();
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new Location(100,100), BlockType.WHILE);
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(113,143), BlockType.MOVEFORWARD);
        whileBlock.setCavityPlug(forwardBlock);
        whileBlock.setCavitySocket(forwardBlock);
        forwardBlock.setTopSocket(whileBlock);
        forwardBlock.setBottomPlug(whileBlock);
        CC.disconnectCavity(whileBlock,forwardBlock);
        assertTrue(whileBlock.getCavityBlocks().isEmpty());
    }

    @Test
    public void disconnectOneCavityManySize(){
        ConnectionHandler CC = new ConnectionHandler();
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new Location(100,100), BlockType.WHILE);
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(400,400), BlockType.MOVEFORWARD);
        ModelMoveBlock leftBlock = new ModelMoveBlock(new Location(400, 550), BlockType.TURNLEFT);
        ModelMoveBlock rightBlock = new ModelMoveBlock(new Location(400, 700), BlockType.TURNRIGHT);
        forwardBlock.setTopSocket(whileBlock);
        whileBlock.setCavityPlug(forwardBlock);
        leftBlock.setTopSocket(forwardBlock);
        forwardBlock.setBottomPlug(leftBlock);
        rightBlock.setTopSocket(leftBlock);
        leftBlock.setBottomPlug(rightBlock);
        whileBlock.setCavitySocket(leftBlock);
        rightBlock.setBottomPlug(whileBlock);
        CC.disconnectCavity(whileBlock,rightBlock);
        assertEquals(2,whileBlock.getCavityBlocks().size());
    }

    @Test
    public void disconnectOneCavityManyTop(){
        ConnectionHandler CC = new ConnectionHandler();
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new Location(100,100), BlockType.WHILE);
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(400,400), BlockType.MOVEFORWARD);
        ModelMoveBlock leftBlock = new ModelMoveBlock(new Location(400, 550), BlockType.TURNLEFT);
        ModelMoveBlock rightBlock = new ModelMoveBlock(new Location(400, 700), BlockType.TURNRIGHT);
        forwardBlock.setTopSocket(whileBlock);
        whileBlock.setCavityPlug(forwardBlock);
        leftBlock.setTopSocket(forwardBlock);
        forwardBlock.setBottomPlug(leftBlock);
        rightBlock.setTopSocket(leftBlock);
        leftBlock.setBottomPlug(rightBlock);
        whileBlock.setCavitySocket(rightBlock);
        rightBlock.setBottomPlug(whileBlock);
        CC.disconnectCavity(whileBlock,forwardBlock);
        assertEquals(rightBlock,whileBlock.getCavitySocket());
    }

    @Test
    public void disconnectOneCavityManyBottom(){
        ConnectionHandler CC = new ConnectionHandler();
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new Location(100,100), BlockType.WHILE);
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(400,400), BlockType.MOVEFORWARD);
        ModelMoveBlock leftBlock = new ModelMoveBlock(new Location(400, 550), BlockType.TURNLEFT);
        ModelMoveBlock rightBlock = new ModelMoveBlock(new Location(400, 700), BlockType.TURNRIGHT);
        forwardBlock.setTopSocket(whileBlock);
        whileBlock.setCavityPlug(forwardBlock);
        leftBlock.setTopSocket(forwardBlock);
        forwardBlock.setBottomPlug(leftBlock);
        rightBlock.setTopSocket(leftBlock);
        leftBlock.setBottomPlug(rightBlock);
        whileBlock.setCavitySocket(rightBlock);
        rightBlock.setBottomPlug(whileBlock);
        CC.disconnectCavity(whileBlock,forwardBlock);
        assertEquals(leftBlock,whileBlock.getCavityPlug());
    }

    @Test
    public void connectTopBottom() {
        ConnectionHandler CC = new ConnectionHandler();
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(100,100), BlockType.MOVEFORWARD);
        ModelMoveBlock leftBlock = new ModelMoveBlock(new Location(100,20), BlockType.TURNLEFT);
        CC.connectTopBottom(leftBlock,forwardBlock);
        assertEquals(leftBlock, forwardBlock.getTopSocket());
    }

    @Test
    public void generalConnectTopBottom() {
        ConnectionHandler CC = new ConnectionHandler();
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(100,100), BlockType.MOVEFORWARD);
        ModelMoveBlock leftBlock = new ModelMoveBlock(new Location(100,20), BlockType.TURNLEFT);
        CC.connect(leftBlock,forwardBlock);
        assertEquals(leftBlock, forwardBlock.getTopSocket());
    }

    @Test
    public void connectTopBottomInverse() {
        ConnectionHandler CC = new ConnectionHandler();
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(100,100), BlockType.MOVEFORWARD);
        ModelMoveBlock leftBlock = new ModelMoveBlock(new Location(100,20), BlockType.TURNLEFT);
        CC.connectTopBottom(leftBlock,forwardBlock);
        assertEquals(forwardBlock, leftBlock.getBottomPlug());
    }

    @Test
    public void connectTopBottomBetween() {
        ConnectionHandler CC = new ConnectionHandler();
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(100,100), BlockType.MOVEFORWARD);
        ModelMoveBlock leftBlock = new ModelMoveBlock(new Location(100,20), BlockType.TURNLEFT);
        ModelMoveBlock rightBlock = new ModelMoveBlock(new Location(100, 100), BlockType.TURNRIGHT);
        CC.connectTopBottom(leftBlock,forwardBlock);
        CC.connectTopBottom(leftBlock, rightBlock);
        assertEquals(forwardBlock, rightBlock.getBottomPlug());
    }

    @Test
    public void connectRightLeft(){
        ConnectionHandler CC = new ConnectionHandler();
        ModelNotBlock notBlock = new ModelNotBlock(new Location(100,100), BlockType.NOT);
        ModelWallInFrontBlock wifBlock = new ModelWallInFrontBlock(new Location(100,20), BlockType.WALLINFRONT);
        CC.connectRightLeft(wifBlock, notBlock);
        assertEquals(wifBlock, notBlock.getRightSocket());
    }

    @Test
    public void generalConnectRightLeft(){
        ConnectionHandler CC = new ConnectionHandler();
        ModelNotBlock notBlock = new ModelNotBlock(new Location(20,100), BlockType.NOT);
        ModelWallInFrontBlock wifBlock = new ModelWallInFrontBlock(new Location(100,100), BlockType.WALLINFRONT);
        CC.connect(wifBlock, notBlock);
        assertEquals(wifBlock, notBlock.getRightSocket());
    }

    @Test
    public void connectRightLeftInverse(){
        ConnectionHandler CC = new ConnectionHandler();
        ModelNotBlock notBlock = new ModelNotBlock(new Location(100,100), BlockType.NOT);
        ModelWallInFrontBlock wifBlock = new ModelWallInFrontBlock(new Location(100,20), BlockType.WALLINFRONT);
        CC.connectRightLeft(wifBlock, notBlock);
        assertEquals(notBlock, wifBlock.getLeftPlug());
    }

    @Test
    public void connectRightLeftBetween(){
        ConnectionHandler CC = new ConnectionHandler();
        ModelNotBlock notBlock = new ModelNotBlock(new Location(100,100), BlockType.NOT);
        ModelNotBlock not2Block = new ModelNotBlock(new Location(100,100), BlockType.NOT);
        ModelWallInFrontBlock wifBlock = new ModelWallInFrontBlock(new Location(100,20), BlockType.WALLINFRONT);
        CC.connectRightLeft(wifBlock, notBlock);
        CC.connectRightLeft(wifBlock, not2Block);
        assertEquals(notBlock, not2Block.getLeftPlug());
    }

    @Test
    public void connectCavityPlug(){
        ConnectionHandler CC = new ConnectionHandler();
        ModelWhileIfBlock ifBlock = new ModelWhileIfBlock(new Location(100,100), BlockType.WHILE);
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(110,140), BlockType.MOVEFORWARD);
        CC.connectCavityPlug(ifBlock,forwardBlock);
        assertEquals(forwardBlock, ifBlock.getCavityPlug());
    }

    @Test
    public void generalConnectCavityPlug(){
        ConnectionHandler CC = new ConnectionHandler();
        ModelWhileIfBlock ifBlock = new ModelWhileIfBlock(new Location(100,100), BlockType.WHILE);
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(110,140), BlockType.MOVEFORWARD);
        CC.connect(ifBlock,forwardBlock);
        assertEquals(forwardBlock, ifBlock.getCavityPlug());
    }

    @Test
    public void connectCavitySocket(){
        ConnectionHandler CC = new ConnectionHandler();
        ModelWhileIfBlock ifBlock = new ModelWhileIfBlock(new Location(100,100), BlockType.WHILE);
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(110,60), BlockType.MOVEFORWARD);
        CC.connectCavitySocket(ifBlock,forwardBlock);
        assertEquals(forwardBlock, ifBlock.getCavitySocket());
    }

    @Test
    public void generalConnectCavitySocket(){
        ConnectionHandler CC = new ConnectionHandler();
        ModelWhileIfBlock ifBlock = new ModelWhileIfBlock(new Location(100,100), BlockType.WHILE);
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(110,60), BlockType.MOVEFORWARD);
        CC.connect(ifBlock,forwardBlock);
        assertEquals(forwardBlock, ifBlock.getCavitySocket());
    }

    @Test
    public void connectCavityPlugConnectsSocket(){
        ConnectionHandler CC = new ConnectionHandler();
        ModelWhileIfBlock ifBlock = new ModelWhileIfBlock(new Location(100,100), BlockType.WHILE);
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(110,140), BlockType.MOVEFORWARD);
        CC.connectCavitySocket(ifBlock,forwardBlock);
        assertEquals(forwardBlock, ifBlock.getCavitySocket());
    }

    @Test
    public void connectCavitySocketConnectsPlug(){
        ConnectionHandler CC = new ConnectionHandler();
        ModelWhileIfBlock ifBlock = new ModelWhileIfBlock(new Location(100,100), BlockType.WHILE);
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(110,60), BlockType.MOVEFORWARD);
        CC.connectCavityPlug(ifBlock,forwardBlock);
        assertEquals(forwardBlock, ifBlock.getCavityPlug());
    }

    @Test
    public void connectIntoCavityTop(){
        ConnectionHandler CC = new ConnectionHandler();
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new Location(100,100), BlockType.WHILE);
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(400,400), BlockType.MOVEFORWARD);
        ModelMoveBlock leftBlock = new ModelMoveBlock(new Location(400, 550), BlockType.TURNLEFT);
        ModelMoveBlock rightBlock = new ModelMoveBlock(new Location(400, 700), BlockType.TURNRIGHT);
        forwardBlock.setTopSocket(whileBlock);
        whileBlock.setCavityPlug(forwardBlock);
        leftBlock.setTopSocket(forwardBlock);
        forwardBlock.setBottomPlug(leftBlock);
        leftBlock.setBottomPlug(whileBlock);
        whileBlock.setCavitySocket(leftBlock);
        CC.connectIntoCavityTop(rightBlock,leftBlock);
        assertEquals(rightBlock, whileBlock.getCavitySocket());
    }

    @Test
    public void generalConnectIntoCavityTop(){
        ConnectionHandler CC = new ConnectionHandler();
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new Location(100,100), BlockType.WHILE);
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(113,143), BlockType.MOVEFORWARD);
        ModelMoveBlock leftBlock = new ModelMoveBlock(new Location(113, 223), BlockType.TURNLEFT);
        ModelMoveBlock rightBlock = new ModelMoveBlock(new Location(113, 303), BlockType.TURNRIGHT);
        forwardBlock.setTopSocket(whileBlock);
        whileBlock.setCavityPlug(forwardBlock);
        leftBlock.setTopSocket(forwardBlock);
        forwardBlock.setBottomPlug(leftBlock);
        leftBlock.setBottomPlug(whileBlock);
        whileBlock.setCavitySocket(leftBlock);
        CC.connect(rightBlock,leftBlock);
        assertEquals(rightBlock, whileBlock.getCavitySocket());
    }

    @Test
    public void connectIntoCavityBottom(){
        ConnectionHandler CC = new ConnectionHandler();
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new Location(100,100), BlockType.WHILE);
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(400,400), BlockType.MOVEFORWARD);
        ModelMoveBlock leftBlock = new ModelMoveBlock(new Location(400, 550), BlockType.TURNLEFT);
        ModelMoveBlock rightBlock = new ModelMoveBlock(new Location(400, 700), BlockType.TURNRIGHT);
        forwardBlock.setTopSocket(whileBlock);
        whileBlock.setCavityPlug(forwardBlock);
        leftBlock.setTopSocket(forwardBlock);
        forwardBlock.setBottomPlug(leftBlock);
        leftBlock.setBottomPlug(whileBlock);
        whileBlock.setCavitySocket(leftBlock);
        CC.connectIntoCavityBottom(rightBlock,leftBlock);
        assertEquals(leftBlock, whileBlock.getCavitySocket());
    }

    @Test
    public void generalConnectIntoCavityBottom(){
        ConnectionHandler CC = new ConnectionHandler();
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new Location(100,100), BlockType.WHILE);
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(113,143), BlockType.MOVEFORWARD);
        ModelMoveBlock leftBlock = new ModelMoveBlock(new Location(113, 223), BlockType.TURNLEFT);
        ModelMoveBlock rightBlock = new ModelMoveBlock(new Location(113, 223), BlockType.TURNRIGHT);
        forwardBlock.setTopSocket(whileBlock);
        whileBlock.setCavityPlug(forwardBlock);
        leftBlock.setTopSocket(forwardBlock);
        forwardBlock.setBottomPlug(leftBlock);
        leftBlock.setBottomPlug(whileBlock);
        whileBlock.setCavitySocket(leftBlock);
        CC.connect(rightBlock,leftBlock);
        assertEquals(leftBlock, whileBlock.getCavitySocket());
    }

    @Test
    public void isFullyConnected(){
        ConnectionHandler CC = new ConnectionHandler();
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new Location(100,100), BlockType.WHILE);
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(113,143), BlockType.MOVEFORWARD);
        ModelMoveBlock leftBlock = new ModelMoveBlock(new Location(113, 223), BlockType.TURNLEFT);
        ModelWallInFrontBlock wifBlock = new ModelWallInFrontBlock(new Location(113, 223), BlockType.WALLINFRONT);
        CC.connectTopBottom(forwardBlock, whileBlock);
        CC.connectTopBottom(whileBlock, leftBlock);
        CC.connectRightLeft(wifBlock, whileBlock);
        boolean bool = CC.isFullyConnected(whileBlock);
        assertTrue(bool);
    }

    @Test
    public void allBlocksConnectedTrue(){
        ConnectionHandler CC = new ConnectionHandler();
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new Location(100,100), BlockType.WHILE);
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(113,143), BlockType.MOVEFORWARD);
        ModelMoveBlock leftBlock = new ModelMoveBlock(new Location(113, 223), BlockType.TURNLEFT);
        ModelWallInFrontBlock wifBlock = new ModelWallInFrontBlock(new Location(113, 223), BlockType.WALLINFRONT);
        CC.connectTopBottom(forwardBlock, whileBlock);
        CC.connectTopBottom(whileBlock, leftBlock);
        CC.connectRightLeft(wifBlock, whileBlock);
        ArrayList<ModelBlock> blocks = new ArrayList<>();
        blocks.add(whileBlock);
        blocks.add(forwardBlock);
        blocks.add(leftBlock);
        blocks.add(wifBlock);
        boolean bool = CC.allBlocksConnected(blocks);
        assertTrue(bool);
    }

    @Test
    public void allBlocksConnectedFalse(){
        ConnectionHandler CC = new ConnectionHandler();
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new Location(100,100), BlockType.WHILE);
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(113,143), BlockType.MOVEFORWARD);
        ModelMoveBlock leftBlock = new ModelMoveBlock(new Location(113, 223), BlockType.TURNLEFT);
        ModelMoveBlock rightBlock = new ModelMoveBlock(new Location(200,200), BlockType.TURNRIGHT);
        ModelWallInFrontBlock wifBlock = new ModelWallInFrontBlock(new Location(113, 223), BlockType.WALLINFRONT);
        CC.connectTopBottom(forwardBlock, whileBlock);
        CC.connectTopBottom(whileBlock, leftBlock);
        CC.connectRightLeft(wifBlock, whileBlock);
        ArrayList<ModelBlock> blocks = new ArrayList<>();
        blocks.add(whileBlock);
        blocks.add(forwardBlock);
        blocks.add(leftBlock);
        blocks.add(wifBlock);
        blocks.add(rightBlock);
        boolean bool = CC.allBlocksConnected(blocks);
        assertFalse(bool);
    }

    @Test
    public void startBlocks(){
        ConnectionHandler CC = new ConnectionHandler();
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new Location(100,100), BlockType.WHILE);
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(113,143), BlockType.MOVEFORWARD);
        ModelMoveBlock leftBlock = new ModelMoveBlock(new Location(113, 223), BlockType.TURNLEFT);
        ModelMoveBlock rightBlock = new ModelMoveBlock(new Location(200,200), BlockType.TURNRIGHT);
        ModelWallInFrontBlock wifBlock = new ModelWallInFrontBlock(new Location(113, 223), BlockType.WALLINFRONT);
        CC.connectTopBottom(forwardBlock, whileBlock);
        CC.connectTopBottom(whileBlock, leftBlock);
        CC.connectRightLeft(wifBlock, whileBlock);
        ArrayList<ModelBlock> blocks = new ArrayList<>();
        blocks.add(whileBlock);
        blocks.add(forwardBlock);
        blocks.add(leftBlock);
        blocks.add(wifBlock);
        blocks.add(rightBlock);
        ArrayList<ModelBlock> startBlocks = CC.getStartBlocks(blocks);
        boolean bool = false;
        if (startBlocks.contains(rightBlock) && startBlocks.contains(forwardBlock)) bool = true;
        assertTrue(bool);
    }

    @Test
    public void finishBlocks(){
        ConnectionHandler CC = new ConnectionHandler();
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new Location(100,100), BlockType.WHILE);
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(113,143), BlockType.MOVEFORWARD);
        ModelMoveBlock leftBlock = new ModelMoveBlock(new Location(113, 223), BlockType.TURNLEFT);
        ModelMoveBlock rightBlock = new ModelMoveBlock(new Location(200,200), BlockType.TURNRIGHT);
        ModelWallInFrontBlock wifBlock = new ModelWallInFrontBlock(new Location(113, 223), BlockType.WALLINFRONT);
        CC.connectTopBottom(forwardBlock, whileBlock);
        CC.connectTopBottom(whileBlock, leftBlock);
        CC.connectRightLeft(wifBlock, whileBlock);
        ArrayList<ModelBlock> blocks = new ArrayList<>();
        blocks.add(whileBlock);
        blocks.add(forwardBlock);
        blocks.add(leftBlock);
        blocks.add(wifBlock);
        blocks.add(rightBlock);
        ArrayList<ModelBlock> finishBlocks = CC.getFinishBlocks(blocks);
        boolean bool = false;
        if (finishBlocks.contains(rightBlock) && finishBlocks.contains(leftBlock)) bool = true;
        assertTrue(bool);
    }

    @Test
    public void getConnectedBlocks(){
        ConnectionHandler CC = new ConnectionHandler();
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new Location(100,100), BlockType.WHILE);
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(113,143), BlockType.MOVEFORWARD);
        ModelMoveBlock leftBlock = new ModelMoveBlock(new Location(113, 223), BlockType.TURNLEFT);
        ModelMoveBlock rightBlock = new ModelMoveBlock(new Location(200,200), BlockType.TURNRIGHT);
        ModelWallInFrontBlock wifBlock = new ModelWallInFrontBlock(new Location(113, 223), BlockType.WALLINFRONT);
        CC.connectTopBottom(forwardBlock, whileBlock);
        CC.connectTopBottom(whileBlock, leftBlock);
        CC.connectRightLeft(wifBlock, whileBlock);
        ArrayList<ModelBlock> blocks = new ArrayList<>();
        blocks.add(whileBlock);
        blocks.add(forwardBlock);
        blocks.add(leftBlock);
        blocks.add(wifBlock);
        blocks.add(rightBlock);
        ArrayList<ModelBlock> connectedBlocks = CC.getConnectedBlocks(forwardBlock);
        boolean bool = false;
        if (connectedBlocks.contains(whileBlock) && connectedBlocks.contains(leftBlock) && connectedBlocks.contains(forwardBlock) && connectedBlocks.contains(wifBlock)) bool = true;
        assertTrue(bool);
    }

    @Test
    public void updateConnections(){
        ConnectionHandler CC = new ConnectionHandler();
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new Location(100,180), BlockType.WHILE);
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(100,100), BlockType.MOVEFORWARD);
        ModelMoveBlock leftBlock = new ModelMoveBlock(new Location(113, 223), BlockType.TURNLEFT);
        ModelMoveBlock rightBlock = new ModelMoveBlock(new Location(100,340), BlockType.TURNRIGHT);
        ModelWallInFrontBlock wifBlock = new ModelWallInFrontBlock(new Location(180, 180), BlockType.WALLINFRONT);
        ArrayList<ModelBlock> blocks = new ArrayList<>();
        blocks.add(whileBlock);
        blocks.add(forwardBlock);
        blocks.add(leftBlock);
        blocks.add(wifBlock);
        blocks.add(rightBlock);
        CC.updateConnections(blocks);
        assertTrue(CC.allBlocksConnected(blocks));
    }
}