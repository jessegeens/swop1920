package model;

import static org.junit.Assert.*;

import model.blocks.*;
import org.junit.Test;

import utilities.*;

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
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(400,400), BlockType.MOVEFORWARD);
        forwardBlock.setTopSocketPos(whileBlock.getCavityPlugPos());
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
    public void connectTopBottomInverse() {
        ConnectionHandler CC = new ConnectionHandler();
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(100,100), BlockType.MOVEFORWARD);
        ModelMoveBlock leftBlock = new ModelMoveBlock(new Location(100,20), BlockType.TURNLEFT);
        CC.connectTopBottom(leftBlock,forwardBlock);
        assertEquals(forwardBlock, leftBlock.getBottomPlug());
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
    public void connectRightLeftInverse(){
        ConnectionHandler CC = new ConnectionHandler();
        ModelNotBlock notBlock = new ModelNotBlock(new Location(100,100), BlockType.NOT);
        ModelWallInFrontBlock wifBlock = new ModelWallInFrontBlock(new Location(100,20), BlockType.WALLINFRONT);
        CC.connectRightLeft(wifBlock, notBlock);
        assertEquals(notBlock, wifBlock.getLeftPlug());
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
    public void connectCavitySocket(){
        ConnectionHandler CC = new ConnectionHandler();
        ModelWhileIfBlock ifBlock = new ModelWhileIfBlock(new Location(100,100), BlockType.WHILE);
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(110,60), BlockType.MOVEFORWARD);
        CC.connectCavitySocket(ifBlock,forwardBlock);
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
    public void connectIntoCavity(){
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
        CC.connectIntoCavityTop(whileBlock, rightBlock,leftBlock);
        assertEquals(3,whileBlock.getCavityBlocks().size());
    }
}