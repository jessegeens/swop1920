package model;

import static org.junit.Assert.*;

import model.blocks.*;
import org.junit.Test;

import utilities.*;

public class ConnectionControllerTest {

    @Test
    public void disconnectTopSocket() {
        ConnectionController CC = new ConnectionController();
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(100,100), BlockType.MOVEFORWARD);
        ModelMoveBlock leftBlock = new ModelMoveBlock(new Location(100,20), BlockType.TURNLEFT);
        forwardBlock.setTopSocket(leftBlock);
        leftBlock.setBottomPlug(forwardBlock);
        CC.disconnect(forwardBlock);
        assertEquals(null, forwardBlock.getTopSocket());
    }

    @Test
    public void disconnectTopSocketInverse() {
        ConnectionController CC = new ConnectionController();
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(100,100), BlockType.MOVEFORWARD);
        ModelMoveBlock leftBlock = new ModelMoveBlock(new Location(100,20), BlockType.TURNLEFT);
        forwardBlock.setTopSocket(leftBlock);
        leftBlock.setBottomPlug(forwardBlock);
        CC.disconnect(leftBlock);
        assertEquals(null, forwardBlock.getTopSocket());
    }

    @Test
    public void disconnectBottomPlug() {
        ConnectionController CC = new ConnectionController();
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(100,100), BlockType.MOVEFORWARD);
        ModelMoveBlock leftBlock = new ModelMoveBlock(new Location(100,20), BlockType.TURNLEFT);
        forwardBlock.setBottomPlug(leftBlock);
        leftBlock.setTopSocket(forwardBlock);
        CC.disconnect(forwardBlock);
        assertEquals(null, forwardBlock.getBottomPlug());
    }

    @Test
    public void disconnectBottomPlugInverse() {
        ConnectionController CC = new ConnectionController();
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(100,100), BlockType.MOVEFORWARD);
        ModelMoveBlock leftBlock = new ModelMoveBlock(new Location(100,20), BlockType.TURNLEFT);
        forwardBlock.setBottomPlug(leftBlock);
        leftBlock.setTopSocket(forwardBlock);
        CC.disconnect(leftBlock);
        assertEquals(null, forwardBlock.getBottomPlug());
    }

    @Test
    public void disconnectRightSocket() {
        ConnectionController CC = new ConnectionController();
        ModelNotBlock notBlock = new ModelNotBlock(new Location(100,100), BlockType.NOT);
        ModelWallInFrontBlock wifBlock = new ModelWallInFrontBlock(new Location(100,20), BlockType.WALLINFRONT);
        notBlock.setRightSocket(wifBlock);
        wifBlock.setLeftPlug(notBlock);
        CC.disconnect(notBlock);
        assertEquals(null, notBlock.getRightSocket());
    }

    @Test
    public void disconnectRightSocketInverse() {
        ConnectionController CC = new ConnectionController();
        ModelNotBlock notBlock = new ModelNotBlock(new Location(100,100), BlockType.NOT);
        ModelWallInFrontBlock wifBlock = new ModelWallInFrontBlock(new Location(100,20), BlockType.WALLINFRONT);
        notBlock.setRightSocket(wifBlock);
        wifBlock.setLeftPlug(notBlock);
        CC.disconnect(wifBlock);
        assertEquals(null, notBlock.getRightSocket());
    }

    @Test
    public void disconnectLeftPlug() {
        ConnectionController CC = new ConnectionController();
        ModelNotBlock notBlock = new ModelNotBlock(new Location(100,100), BlockType.NOT);
        ModelWallInFrontBlock wifBlock = new ModelWallInFrontBlock(new Location(100,20), BlockType.WALLINFRONT);
        notBlock.setRightSocket(wifBlock);
        wifBlock.setLeftPlug(notBlock);
        CC.disconnect(wifBlock);
        assertEquals(null, wifBlock.getLeftPlug());
    }

    @Test
    public void disconnectLeftPlugInverse() {
        ConnectionController CC = new ConnectionController();
        ModelNotBlock notBlock = new ModelNotBlock(new Location(100,100), BlockType.NOT);
        ModelWallInFrontBlock wifBlock = new ModelWallInFrontBlock(new Location(100,20), BlockType.WALLINFRONT);
        notBlock.setRightSocket(wifBlock);
        wifBlock.setLeftPlug(notBlock);
        CC.disconnect(notBlock);
        assertEquals(null, wifBlock.getLeftPlug());
    }

    @Test
    public void disconnectCavity(){
        ConnectionController CC = new ConnectionController();
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
        ConnectionController CC = new ConnectionController();
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
        ConnectionController CC = new ConnectionController();
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
        CC.disconnectCavity(whileBlock,forwardBlock);
        assertEquals(rightBlock,whileBlock.getCavityPlug());
    }

    @Test
    public void disconnectOneCavityManyBottom(){
        ConnectionController CC = new ConnectionController();
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
        CC.disconnectCavity(whileBlock,forwardBlock);
        assertEquals(rightBlock,whileBlock.getCavityPlug());
    }

    @Test
    public void connectTopBottom() {
        ConnectionController CC = new ConnectionController();
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(100,100), BlockType.MOVEFORWARD);
        ModelMoveBlock leftBlock = new ModelMoveBlock(new Location(100,20), BlockType.TURNLEFT);
        CC.connectTopBottom(leftBlock,forwardBlock);
        assertEquals(leftBlock, forwardBlock.getTopSocket());
    }

    @Test
    public void connectTopBottomInverse() {
        ConnectionController CC = new ConnectionController();
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(100,100), BlockType.MOVEFORWARD);
        ModelMoveBlock leftBlock = new ModelMoveBlock(new Location(100,20), BlockType.TURNLEFT);
        CC.connectTopBottom(leftBlock,forwardBlock);
        assertEquals(forwardBlock, leftBlock.getBottomPlug());
    }

    @Test
    public void connectRightLeft(){
        ConnectionController CC = new ConnectionController();
        ModelNotBlock notBlock = new ModelNotBlock(new Location(100,100), BlockType.NOT);
        ModelWallInFrontBlock wifBlock = new ModelWallInFrontBlock(new Location(100,20), BlockType.WALLINFRONT);
        CC.connectRightLeft(wifBlock, notBlock);
        assertEquals(wifBlock, notBlock.getRightSocket());
    }

    @Test
    public void connectRightLeftInverse(){
        ConnectionController CC = new ConnectionController();
        ModelNotBlock notBlock = new ModelNotBlock(new Location(100,100), BlockType.NOT);
        ModelWallInFrontBlock wifBlock = new ModelWallInFrontBlock(new Location(100,20), BlockType.WALLINFRONT);
        CC.connectRightLeft(wifBlock, notBlock);
        assertEquals(notBlock, wifBlock.getLeftPlug());
    }

    @Test
    public void connectCavityPlug(){
        ConnectionController CC = new ConnectionController();
        ModelWhileIfBlock ifBlock = new ModelWhileIfBlock(new Location(100,100), BlockType.WHILE);
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(110,140), BlockType.MOVEFORWARD);
        CC.connectCavity(ifBlock,forwardBlock);
        assertEquals(forwardBlock, ifBlock.getCavityPlug());
    }

    @Test
    public void connectCavitySocket(){
        ConnectionController CC = new ConnectionController();
        ModelWhileIfBlock ifBlock = new ModelWhileIfBlock(new Location(100,100), BlockType.WHILE);
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(110,60), BlockType.MOVEFORWARD);
        CC.connectCavity(ifBlock,forwardBlock);
        assertEquals(forwardBlock, ifBlock.getCavitySocket());
    }

    @Test
    public void connectCavityPlugConnectsSocket(){
        ConnectionController CC = new ConnectionController();
        ModelWhileIfBlock ifBlock = new ModelWhileIfBlock(new Location(100,100), BlockType.WHILE);
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(110,140), BlockType.MOVEFORWARD);
        CC.connectCavity(ifBlock,forwardBlock);
        assertEquals(forwardBlock, ifBlock.getCavitySocket());
    }

    @Test
    public void connectCavitySocketConnectsPlug(){
        ConnectionController CC = new ConnectionController();
        ModelWhileIfBlock ifBlock = new ModelWhileIfBlock(new Location(100,100), BlockType.WHILE);
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(110,60), BlockType.MOVEFORWARD);
        CC.connectCavity(ifBlock,forwardBlock);
        assertEquals(forwardBlock, ifBlock.getCavityPlug());
    }

    @Test
    public void connectIntoCavity(){
        ConnectionController CC = new ConnectionController();
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
        CC.connectIntoCavity(whileBlock, leftBlock,rightBlock);
        assertEquals(3,whileBlock.getCavityBlocks().size());
    }
}