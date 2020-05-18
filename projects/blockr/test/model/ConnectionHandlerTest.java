package model;

import static org.junit.Assert.*;

import model.blocks.*;
import org.junit.Test;

import ui.UIBlock;
import utilities.*;

import java.util.ArrayList;

public class ConnectionHandlerTest {

    @Test
    public void disconnectTopSocket() {
        ModelActionBlock forwardBlock = new ModelActionBlock(new ProgramLocation(100,100), null);
        ModelActionBlock leftBlock = new ModelActionBlock(new ProgramLocation(100,20), null);
        forwardBlock.setTopSocket(leftBlock);
        leftBlock.setBottomPlug(forwardBlock);
        ConnectionHandler.getInstance().disconnect(forwardBlock);
        assertEquals(null, forwardBlock.getTopSocket());
    }

    @Test
    public void disconnectTopSocketInverse() {
        ModelActionBlock forwardBlock = new ModelActionBlock(new ProgramLocation(100,100), null);
        ModelActionBlock leftBlock = new ModelActionBlock(new ProgramLocation(100,20), null);
        forwardBlock.setTopSocket(leftBlock);
        leftBlock.setBottomPlug(forwardBlock);
        ConnectionHandler.getInstance().disconnect(leftBlock);
        assertEquals(null, forwardBlock.getTopSocket());
    }

    @Test
    public void disconnectBottomPlug() {
        ModelActionBlock forwardBlock = new ModelActionBlock(new ProgramLocation(100,100), null);
        ModelActionBlock leftBlock = new ModelActionBlock(new ProgramLocation(100,20), null);
        forwardBlock.setBottomPlug(leftBlock);
        leftBlock.setTopSocket(forwardBlock);
        ConnectionHandler.getInstance().disconnect(forwardBlock);
        assertEquals(null, forwardBlock.getBottomPlug());
    }

    @Test
    public void disconnectBottomPlugInverse() {
        ModelActionBlock forwardBlock = new ModelActionBlock(new ProgramLocation(100,100), null);
        ModelActionBlock leftBlock = new ModelActionBlock(new ProgramLocation(100,20), null);
        forwardBlock.setBottomPlug(leftBlock);
        leftBlock.setTopSocket(forwardBlock);
        ConnectionHandler.getInstance().disconnect(leftBlock);
        assertEquals(null, forwardBlock.getBottomPlug());
    }

    @Test
    public void disconnectRightSocket() {
        ModelNotBlock notBlock = new ModelNotBlock(new ProgramLocation(100,100));
        ModelPredicateBlock wifBlock = new ModelPredicateBlock(new ProgramLocation(100,20), null);
        notBlock.setRightSocket(wifBlock);
        wifBlock.setLeftPlug(notBlock);
        ConnectionHandler.getInstance().disconnect(notBlock);
        assertEquals(wifBlock, notBlock.getRightSocket());
    }

    @Test
    public void disconnectRightSocketInverse() {
        ModelNotBlock notBlock = new ModelNotBlock(new ProgramLocation(100,100));
        ModelPredicateBlock wifBlock = new ModelPredicateBlock(new ProgramLocation(100,20), null);
        notBlock.setRightSocket(wifBlock);
        wifBlock.setLeftPlug(notBlock);
        ConnectionHandler.getInstance().disconnect(wifBlock);
        assertEquals(null, notBlock.getRightSocket());
    }

    @Test
    public void disconnectLeftPlug() {
        ModelNotBlock notBlock = new ModelNotBlock(new ProgramLocation(100,100));
        ModelPredicateBlock wifBlock = new ModelPredicateBlock(new ProgramLocation(100,20), null);
        notBlock.setRightSocket(wifBlock);
        wifBlock.setLeftPlug(notBlock);
        ConnectionHandler.getInstance().disconnect(wifBlock);
        assertEquals(null, wifBlock.getLeftPlug());
    }

    @Test
    public void disconnectLeftPlugInverse() {
        ModelNotBlock notBlock = new ModelNotBlock(new ProgramLocation(100,100));
        ModelPredicateBlock wifBlock = new ModelPredicateBlock(new ProgramLocation(100,20), null);
        notBlock.setRightSocket(wifBlock);
        wifBlock.setLeftPlug(notBlock);
        ConnectionHandler.getInstance().disconnect(notBlock);
        assertEquals(notBlock, wifBlock.getLeftPlug());
    }

    @Test
    public void disconnectCavity(){
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new ProgramLocation(100,100), false);
        ModelActionBlock forwardBlock = new ModelActionBlock(new ProgramLocation(113,143), null);
        whileBlock.setCavityPlug(forwardBlock);
        whileBlock.setCavitySocket(forwardBlock);
        forwardBlock.setTopSocket(whileBlock);
        forwardBlock.setBottomPlug(whileBlock);
        ConnectionHandler.getInstance().disconnectCavity(forwardBlock);
        assertTrue(whileBlock.getCavityBlocks().isEmpty());
    }

    @Test
    public void disconnectOneCavityManySize(){
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new ProgramLocation(100,100), false);
        ModelActionBlock forwardBlock = new ModelActionBlock(new ProgramLocation(400,400), null);
        ModelActionBlock leftBlock = new ModelActionBlock(new ProgramLocation(400, 550), null);
        ModelActionBlock rightBlock = new ModelActionBlock(new ProgramLocation(400, 700), null);
        forwardBlock.setTopSocket(whileBlock);
        whileBlock.setCavityPlug(forwardBlock);
        leftBlock.setTopSocket(forwardBlock);
        forwardBlock.setBottomPlug(leftBlock);
        rightBlock.setTopSocket(leftBlock);
        leftBlock.setBottomPlug(rightBlock);
        whileBlock.setCavitySocket(leftBlock);
        rightBlock.setBottomPlug(whileBlock);
        ConnectionHandler.getInstance().disconnectCavity(rightBlock);
        assertEquals(2,whileBlock.getCavityBlocks().size());
    }

    @Test
    public void disconnectOneCavityManyTop(){
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new ProgramLocation(100,100), false);
        ModelActionBlock forwardBlock = new ModelActionBlock(new ProgramLocation(400,400), null);
        ModelActionBlock leftBlock = new ModelActionBlock(new ProgramLocation(400, 550), null);
        ModelActionBlock rightBlock = new ModelActionBlock(new ProgramLocation(400, 700), null);
        forwardBlock.setTopSocket(whileBlock);
        whileBlock.setCavityPlug(forwardBlock);
        leftBlock.setTopSocket(forwardBlock);
        forwardBlock.setBottomPlug(leftBlock);
        rightBlock.setTopSocket(leftBlock);
        leftBlock.setBottomPlug(rightBlock);
        whileBlock.setCavitySocket(rightBlock);
        rightBlock.setBottomPlug(whileBlock);
        ConnectionHandler.getInstance().disconnectCavity(forwardBlock);
        assertEquals(rightBlock,whileBlock.getCavitySocket());
    }

    @Test
    public void disconnectOneCavityManyBottom(){
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new ProgramLocation(100,100), false);
        ModelActionBlock forwardBlock = new ModelActionBlock(new ProgramLocation(400,400), null);
        ModelActionBlock leftBlock = new ModelActionBlock(new ProgramLocation(400, 550), null);
        ModelActionBlock rightBlock = new ModelActionBlock(new ProgramLocation(400, 700), null);
        forwardBlock.setTopSocket(whileBlock);
        whileBlock.setCavityPlug(forwardBlock);
        leftBlock.setTopSocket(forwardBlock);
        forwardBlock.setBottomPlug(leftBlock);
        rightBlock.setTopSocket(leftBlock);
        leftBlock.setBottomPlug(rightBlock);
        whileBlock.setCavitySocket(rightBlock);
        rightBlock.setBottomPlug(whileBlock);
        ConnectionHandler.getInstance().disconnectCavity(forwardBlock);
        assertEquals(leftBlock,whileBlock.getCavityPlug());
    }

    @Test
    public void connectTopBottom() {
        ModelActionBlock forwardBlock = new ModelActionBlock(new ProgramLocation(100,100), null);
        ModelActionBlock leftBlock = new ModelActionBlock(new ProgramLocation(100,20), null);
        ConnectionHandler.getInstance().connectTopBottom(leftBlock,forwardBlock);
        assertEquals(leftBlock, forwardBlock.getTopSocket());
    }

    @Test
    public void generalConnectTopBottom() {
        ModelActionBlock forwardBlock = new ModelActionBlock(new ProgramLocation(100,100), null);
        ModelActionBlock leftBlock = new ModelActionBlock(new ProgramLocation(100,20), null);
        ConnectionHandler.getInstance().connect(leftBlock,forwardBlock);
        assertEquals(leftBlock, forwardBlock.getTopSocket());
    }

    @Test
    public void connectTopBottomInverse() {
        ModelActionBlock forwardBlock = new ModelActionBlock(new ProgramLocation(100,100), null);
        ModelActionBlock leftBlock = new ModelActionBlock(new ProgramLocation(100,20), null);
        ConnectionHandler.getInstance().connectTopBottom(leftBlock,forwardBlock);
        assertEquals(forwardBlock, leftBlock.getBottomPlug());
    }

    @Test
    public void connectTopBottomBetween() {
        ModelActionBlock forwardBlock = new ModelActionBlock(new ProgramLocation(100,100), null);
        ModelActionBlock leftBlock = new ModelActionBlock(new ProgramLocation(100,20), null);
        ModelActionBlock rightBlock = new ModelActionBlock(new ProgramLocation(100, 100), null);
        ConnectionHandler.getInstance().connectTopBottom(leftBlock,forwardBlock);
        ConnectionHandler.getInstance().connectTopBottom(leftBlock, rightBlock);
        assertEquals(forwardBlock, rightBlock.getBottomPlug());
    }

    @Test
    public void connectRightLeft(){
        ModelNotBlock notBlock = new ModelNotBlock(new ProgramLocation(100,100));
        ModelPredicateBlock wifBlock = new ModelPredicateBlock(new ProgramLocation(100,20), null);
        ConnectionHandler.getInstance().connectRightLeft(wifBlock, notBlock);
        assertEquals(wifBlock, notBlock.getRightSocket());
    }

    @Test
    public void generalConnectRightLeft(){
        ModelNotBlock notBlock = new ModelNotBlock(new ProgramLocation(20,100));
        ModelPredicateBlock wifBlock = new ModelPredicateBlock(new ProgramLocation(100,100), null);
        ConnectionHandler.getInstance().connect(wifBlock, notBlock);
        assertEquals(wifBlock, notBlock.getRightSocket());
    }

    @Test
    public void connectRightLeftInverse(){
        ModelNotBlock notBlock = new ModelNotBlock(new ProgramLocation(100,100));
        ModelPredicateBlock wifBlock = new ModelPredicateBlock(new ProgramLocation(100,20), null);
        ConnectionHandler.getInstance().connectRightLeft(wifBlock, notBlock);
        assertEquals(notBlock, wifBlock.getLeftPlug());
    }

    @Test
    public void connectRightLeftBetween(){
        ModelNotBlock notBlock = new ModelNotBlock(new ProgramLocation(100,100));
        ModelNotBlock not2Block = new ModelNotBlock(new ProgramLocation(100,100));
        ModelPredicateBlock wifBlock = new ModelPredicateBlock(new ProgramLocation(100,20), null);
        ConnectionHandler.getInstance().connectRightLeft(wifBlock, notBlock);
        ConnectionHandler.getInstance().connectRightLeft(wifBlock, not2Block);
        assertEquals(notBlock, not2Block.getLeftPlug());
    }

    @Test
    public void connectCavityPlug(){
        ModelWhileIfBlock ifBlock = new ModelWhileIfBlock(new ProgramLocation(100,100), false);
        ModelActionBlock forwardBlock = new ModelActionBlock(new ProgramLocation(110,140), null);
        ConnectionHandler.getInstance().connectCavityPlug(ifBlock,forwardBlock);
        assertEquals(forwardBlock, ifBlock.getCavityPlug());
    }

    @Test
    public void generalConnectCavityPlug(){
        ModelWhileIfBlock ifBlock = new ModelWhileIfBlock(new ProgramLocation(100,100), false);
        ModelActionBlock forwardBlock = new ModelActionBlock(new ProgramLocation(110,140), null);
        ConnectionHandler.getInstance().connect(ifBlock,forwardBlock);
        assertEquals(forwardBlock, ifBlock.getCavityPlug());
    }

    @Test
    public void connectCavitySocket(){
        ModelWhileIfBlock ifBlock = new ModelWhileIfBlock(new ProgramLocation(100,100), false);
        ModelActionBlock forwardBlock = new ModelActionBlock(new ProgramLocation(110,60), null);
        ConnectionHandler.getInstance().connectCavitySocket(ifBlock,forwardBlock);
        assertEquals(forwardBlock, ifBlock.getCavitySocket());
    }

    @Test
    public void generalConnectCavitySocket(){
        ModelWhileIfBlock ifBlock = new ModelWhileIfBlock(new ProgramLocation(100,100), false);
        ModelActionBlock forwardBlock = new ModelActionBlock(new ProgramLocation(110,60), null);
        ConnectionHandler.getInstance().connect(ifBlock,forwardBlock);
        assertEquals(forwardBlock, ifBlock.getCavitySocket());
    }

    @Test
    public void connectCavityPlugConnectsSocket(){
        ModelWhileIfBlock ifBlock = new ModelWhileIfBlock(new ProgramLocation(100,100), false);
        ModelActionBlock forwardBlock = new ModelActionBlock(new ProgramLocation(110,140), null);
        ConnectionHandler.getInstance().connectCavitySocket(ifBlock,forwardBlock);
        assertEquals(forwardBlock, ifBlock.getCavitySocket());
    }

    @Test
    public void connectCavitySocketConnectsPlug(){
        ModelWhileIfBlock ifBlock = new ModelWhileIfBlock(new ProgramLocation(100,100), false);
        ModelActionBlock forwardBlock = new ModelActionBlock(new ProgramLocation(110,60), null);
        ConnectionHandler.getInstance().connectCavityPlug(ifBlock,forwardBlock);
        assertEquals(forwardBlock, ifBlock.getCavityPlug());
    }

    @Test
    public void connectIntoCavityTop(){
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new ProgramLocation(100,100), false);
        ModelActionBlock forwardBlock = new ModelActionBlock(new ProgramLocation(400,400), null);
        ModelActionBlock leftBlock = new ModelActionBlock(new ProgramLocation(400, 550), null);
        ModelActionBlock rightBlock = new ModelActionBlock(new ProgramLocation(400, 700), null);
        forwardBlock.setTopSocket(whileBlock);
        whileBlock.setCavityPlug(forwardBlock);
        leftBlock.setTopSocket(forwardBlock);
        forwardBlock.setBottomPlug(leftBlock);
        leftBlock.setBottomPlug(whileBlock);
        whileBlock.setCavitySocket(leftBlock);
        ConnectionHandler.getInstance().connectIntoCavityTop(rightBlock,leftBlock);
        assertEquals(rightBlock, whileBlock.getCavitySocket());
    }

    @Test
    public void generalConnectIntoCavityTop(){
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new ProgramLocation(100,100), false);
        ModelActionBlock forwardBlock = new ModelActionBlock(new ProgramLocation(113,143), null);
        ModelActionBlock leftBlock = new ModelActionBlock(new ProgramLocation(113, 223), null);
        ModelActionBlock rightBlock = new ModelActionBlock(new ProgramLocation(113, 303), null);
        forwardBlock.setTopSocket(whileBlock);
        whileBlock.setCavityPlug(forwardBlock);
        leftBlock.setTopSocket(forwardBlock);
        forwardBlock.setBottomPlug(leftBlock);
        leftBlock.setBottomPlug(whileBlock);
        whileBlock.setCavitySocket(leftBlock);
        ConnectionHandler.getInstance().connect(rightBlock,leftBlock);
        assertEquals(rightBlock, whileBlock.getCavitySocket());
    }

    @Test
    public void connectIntoCavityBottom(){
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new ProgramLocation(100,100), false);
        ModelActionBlock forwardBlock = new ModelActionBlock(new ProgramLocation(400,400), null);
        ModelActionBlock leftBlock = new ModelActionBlock(new ProgramLocation(400, 550), null);
        ModelActionBlock rightBlock = new ModelActionBlock(new ProgramLocation(400, 700), null);
        forwardBlock.setTopSocket(whileBlock);
        whileBlock.setCavityPlug(forwardBlock);
        leftBlock.setTopSocket(forwardBlock);
        forwardBlock.setBottomPlug(leftBlock);
        leftBlock.setBottomPlug(whileBlock);
        whileBlock.setCavitySocket(leftBlock);
        ConnectionHandler.getInstance().connectIntoCavityBottom(rightBlock,leftBlock);
        assertEquals(leftBlock, whileBlock.getCavitySocket());
    }

    @Test
    public void generalConnectIntoCavityBottom(){
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new ProgramLocation(100,100), false);
        ModelActionBlock forwardBlock = new ModelActionBlock(new ProgramLocation(100 + UIBlock.STD_WIDTH/6,100 + UIBlock.STD_HEIGHT*2/3 - UIBlock.PLUGSIZE/2), null);
        ModelActionBlock leftBlock = new ModelActionBlock(new ProgramLocation(100 + UIBlock.STD_WIDTH/6, 100 + UIBlock.STD_HEIGHT*5/3 - UIBlock.PLUGSIZE/2), null);
        ModelActionBlock rightBlock = new ModelActionBlock(new ProgramLocation(100 + UIBlock.STD_WIDTH/6, 100 + UIBlock.STD_HEIGHT*8/3 - UIBlock.PLUGSIZE/2), null);
        forwardBlock.setTopSocket(whileBlock);
        whileBlock.setCavityPlug(forwardBlock);
        leftBlock.setTopSocket(forwardBlock);
        forwardBlock.setBottomPlug(leftBlock);
        leftBlock.setBottomPlug(whileBlock);
        whileBlock.setCavitySocket(leftBlock);
        ConnectionHandler.getInstance().connect(rightBlock,leftBlock);
        assertEquals(rightBlock, whileBlock.getCavitySocket());
    }

    @Test
    public void isFullyConnected(){
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new ProgramLocation(100,100), false);
        ModelActionBlock forwardBlock = new ModelActionBlock(new ProgramLocation(113,143), null);
        ModelActionBlock leftBlock = new ModelActionBlock(new ProgramLocation(113, 223), null);
        ModelPredicateBlock wifBlock = new ModelPredicateBlock(new ProgramLocation(113, 223), null);
        ConnectionHandler.getInstance().connectTopBottom(forwardBlock, whileBlock);
        ConnectionHandler.getInstance().connectTopBottom(whileBlock, leftBlock);
        ConnectionHandler.getInstance().connectRightLeft(wifBlock, whileBlock);
        boolean bool = ConnectionHandler.getInstance().isFullyConnected(whileBlock);
        assertTrue(bool);
    }

    @Test
    public void allBlocksConnectedTrue(){
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new ProgramLocation(100,100), false);
        ModelActionBlock forwardBlock = new ModelActionBlock(new ProgramLocation(113,143), null);
        ModelActionBlock leftBlock = new ModelActionBlock(new ProgramLocation(113, 223), null);
        ModelPredicateBlock wifBlock = new ModelPredicateBlock(new ProgramLocation(113, 223), null);
        ConnectionHandler.getInstance().connectTopBottom(forwardBlock, whileBlock);
        ConnectionHandler.getInstance().connectTopBottom(whileBlock, leftBlock);
        ConnectionHandler.getInstance().connectRightLeft(wifBlock, whileBlock);
        ArrayList<ModelBlock> blocks = new ArrayList<>();
        blocks.add(whileBlock);
        blocks.add(forwardBlock);
        blocks.add(leftBlock);
        blocks.add(wifBlock);
        boolean bool = ConnectionHandler.getInstance().allBlocksConnected(blocks);
        assertTrue(bool);
    }

    @Test
    public void allBlocksConnectedFalse(){
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new ProgramLocation(100,100), false);
        ModelActionBlock forwardBlock = new ModelActionBlock(new ProgramLocation(113,143), null);
        ModelActionBlock leftBlock = new ModelActionBlock(new ProgramLocation(113, 223), null);
        ModelActionBlock rightBlock = new ModelActionBlock(new ProgramLocation(200,200), null);
        ModelPredicateBlock wifBlock = new ModelPredicateBlock(new ProgramLocation(113, 223), null);
        ConnectionHandler.getInstance().connectTopBottom(forwardBlock, whileBlock);
        ConnectionHandler.getInstance().connectTopBottom(whileBlock, leftBlock);
        ConnectionHandler.getInstance().connectRightLeft(wifBlock, whileBlock);
        ArrayList<ModelBlock> blocks = new ArrayList<>();
        blocks.add(whileBlock);
        blocks.add(forwardBlock);
        blocks.add(leftBlock);
        blocks.add(wifBlock);
        blocks.add(rightBlock);
        boolean bool = ConnectionHandler.getInstance().allBlocksConnected(blocks);
        assertFalse(bool);
    }

    @Test
    public void startBlocks(){
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new ProgramLocation(100,100), false);
        ModelActionBlock forwardBlock = new ModelActionBlock(new ProgramLocation(113,143), null);
        ModelActionBlock leftBlock = new ModelActionBlock(new ProgramLocation(113, 223), null);
        ModelActionBlock rightBlock = new ModelActionBlock(new ProgramLocation(200,200), null);
        ModelPredicateBlock wifBlock = new ModelPredicateBlock(new ProgramLocation(113, 223), null);
        ConnectionHandler.getInstance().connectTopBottom(forwardBlock, whileBlock);
        ConnectionHandler.getInstance().connectTopBottom(whileBlock, leftBlock);
        ConnectionHandler.getInstance().connectRightLeft(wifBlock, whileBlock);
        ArrayList<ModelBlock> blocks = new ArrayList<>();
        blocks.add(whileBlock);
        blocks.add(forwardBlock);
        blocks.add(leftBlock);
        blocks.add(wifBlock);
        blocks.add(rightBlock);
        ArrayList<ModelBlock> startBlocks = ConnectionHandler.getInstance().getStartBlocks(blocks);
        boolean bool = false;
        if (startBlocks.contains(rightBlock) && startBlocks.contains(forwardBlock)) bool = true;
        assertTrue(bool);
    }

    @Test
    public void finishBlocks(){
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new ProgramLocation(100,100), false);
        ModelActionBlock forwardBlock = new ModelActionBlock(new ProgramLocation(113,143), null);
        ModelActionBlock leftBlock = new ModelActionBlock(new ProgramLocation(113, 223), null);
        ModelActionBlock rightBlock = new ModelActionBlock(new ProgramLocation(200,200), null);
        ModelPredicateBlock wifBlock = new ModelPredicateBlock(new ProgramLocation(113, 223), null);
        ConnectionHandler.getInstance().connectTopBottom(forwardBlock, whileBlock);
        ConnectionHandler.getInstance().connectTopBottom(whileBlock, leftBlock);
        ConnectionHandler.getInstance().connectRightLeft(wifBlock, whileBlock);
        ArrayList<ModelBlock> blocks = new ArrayList<>();
        blocks.add(whileBlock);
        blocks.add(forwardBlock);
        blocks.add(leftBlock);
        blocks.add(wifBlock);
        blocks.add(rightBlock);
        ArrayList<ModelBlock> finishBlocks = ConnectionHandler.getInstance().getFinishBlocks(blocks);
        boolean bool = false;
        if (finishBlocks.contains(rightBlock) && finishBlocks.contains(leftBlock)) bool = true;
        assertTrue(bool);
    }

    @Test
    public void getConnectedBlocks(){
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new ProgramLocation(100,100), false);
        ModelActionBlock forwardBlock = new ModelActionBlock(new ProgramLocation(113,143), null);
        ModelActionBlock leftBlock = new ModelActionBlock(new ProgramLocation(113, 223), null);
        ModelActionBlock rightBlock = new ModelActionBlock(new ProgramLocation(200,200), null);
        ModelPredicateBlock wifBlock = new ModelPredicateBlock(new ProgramLocation(113, 223), null);
        ConnectionHandler.getInstance().connectTopBottom(forwardBlock, whileBlock);
        ConnectionHandler.getInstance().connectTopBottom(whileBlock, leftBlock);
        ConnectionHandler.getInstance().connectRightLeft(wifBlock, whileBlock);
        ArrayList<ModelBlock> blocks = new ArrayList<>();
        blocks.add(whileBlock);
        blocks.add(forwardBlock);
        blocks.add(leftBlock);
        blocks.add(wifBlock);
        blocks.add(rightBlock);
        ArrayList<ModelBlock> connectedBlocks = ConnectionHandler.getInstance().getConnectedBlocks(forwardBlock);
        boolean bool = false;
        if (connectedBlocks.contains(whileBlock) && connectedBlocks.contains(leftBlock) && connectedBlocks.contains(forwardBlock) && connectedBlocks.contains(wifBlock)) bool = true;
        assertTrue(bool);
    }

    @Test
    public void updateConnections(){
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new ProgramLocation(100,100 + UIBlock.STD_HEIGHT), false);
        ModelActionBlock forwardBlock = new ModelActionBlock(new ProgramLocation(100,100), null);
        ModelActionBlock leftBlock = new ModelActionBlock(new ProgramLocation(100 + UIBlock.STD_WIDTH/6, 100 + UIBlock.STD_HEIGHT + UIBlock.STD_HEIGHT*2/3 - UIBlock.PLUGSIZE/2), null);
        ModelActionBlock rightBlock = new ModelActionBlock(new ProgramLocation(100,100 + UIBlock.STD_HEIGHT * 3), null);
        ModelPredicateBlock wifBlock = new ModelPredicateBlock(new ProgramLocation(100 + UIBlock.STD_WIDTH, 100 + UIBlock.STD_HEIGHT), null);
        ArrayList<ModelBlock> blocks = new ArrayList<>();
        blocks.add(whileBlock);
        blocks.add(forwardBlock);
        blocks.add(leftBlock);
        blocks.add(wifBlock);
        blocks.add(rightBlock);
        ConnectionHandler.getInstance().updateConnections(blocks);
        assertTrue(ConnectionHandler.getInstance().allBlocksConnected(blocks));
    }
}