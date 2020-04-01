package model;

import static org.junit.Assert.*;

import model.blocks.*;
import org.junit.Test;

import utilities.*;

public class ConnectionControllerTest {

    @Test
    public void disconnectTopSocket() {
        ConnectionController CC = new ConnectionController();
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new WindowLocation(100,100), BlockType.MOVEFORWARD);
        ModelMoveBlock leftBlock = new ModelMoveBlock(new WindowLocation(100,20), BlockType.TURNLEFT);
        forwardBlock.setTopSocket(leftBlock);
        leftBlock.setBottomPlug(forwardBlock);
        CC.disconnect(forwardBlock);
        assertEquals(null, forwardBlock.getTopSocket());
    }

    @Test
    public void disconnectTopSocketInverse() {
        ConnectionController CC = new ConnectionController();
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new WindowLocation(100,100), BlockType.MOVEFORWARD);
        ModelMoveBlock leftBlock = new ModelMoveBlock(new WindowLocation(100,20), BlockType.TURNLEFT);
        forwardBlock.setTopSocket(leftBlock);
        leftBlock.setBottomPlug(forwardBlock);
        CC.disconnect(leftBlock);
        assertEquals(null, forwardBlock.getTopSocket());
    }

    @Test
    public void disconnectBottomPlug() {
        ConnectionController CC = new ConnectionController();
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new WindowLocation(100,100), BlockType.MOVEFORWARD);
        ModelMoveBlock leftBlock = new ModelMoveBlock(new WindowLocation(100,20), BlockType.TURNLEFT);
        forwardBlock.setBottomPlug(leftBlock);
        leftBlock.setTopSocket(forwardBlock);
        CC.disconnect(forwardBlock);
        assertEquals(null, forwardBlock.getBottomPlug());
    }

    @Test
    public void disconnectBottomPlugInverse() {
        ConnectionController CC = new ConnectionController();
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new WindowLocation(100,100), BlockType.MOVEFORWARD);
        ModelMoveBlock leftBlock = new ModelMoveBlock(new WindowLocation(100,20), BlockType.TURNLEFT);
        forwardBlock.setBottomPlug(leftBlock);
        leftBlock.setTopSocket(forwardBlock);
        CC.disconnect(leftBlock);
        assertEquals(null, forwardBlock.getBottomPlug());
    }

    @Test
    public void disconnectRightSocket() {
        ConnectionController CC = new ConnectionController();
        ModelNotBlock notBlock = new ModelNotBlock(new WindowLocation(100,100), BlockType.NOT);
        ModelWallInFrontBlock wifBlock = new ModelWallInFrontBlock(new WindowLocation(100,20), BlockType.WALLINFRONT);
        notBlock.setRightSocket(wifBlock);
        wifBlock.setLeftPlug(notBlock);
        CC.disconnect(notBlock);
        assertEquals(null, notBlock.getRightSocket());
    }

    @Test
    public void disconnectRightSocketInverse() {
        ConnectionController CC = new ConnectionController();
        ModelNotBlock notBlock = new ModelNotBlock(new WindowLocation(100,100), BlockType.NOT);
        ModelWallInFrontBlock wifBlock = new ModelWallInFrontBlock(new WindowLocation(100,20), BlockType.WALLINFRONT);
        notBlock.setRightSocket(wifBlock);
        wifBlock.setLeftPlug(notBlock);
        CC.disconnect(wifBlock);
        assertEquals(null, notBlock.getRightSocket());
    }

    @Test
    public void disconnectLeftPlug() {
        ConnectionController CC = new ConnectionController();
        ModelNotBlock notBlock = new ModelNotBlock(new WindowLocation(100,100), BlockType.NOT);
        ModelWallInFrontBlock wifBlock = new ModelWallInFrontBlock(new WindowLocation(100,20), BlockType.WALLINFRONT);
        notBlock.setRightSocket(wifBlock);
        wifBlock.setLeftPlug(notBlock);
        CC.disconnect(wifBlock);
        assertEquals(null, wifBlock.getLeftPlug());
    }

    @Test
    public void disconnectLeftPlugInverse() {
        ConnectionController CC = new ConnectionController();
        ModelNotBlock notBlock = new ModelNotBlock(new WindowLocation(100,100), BlockType.NOT);
        ModelWallInFrontBlock wifBlock = new ModelWallInFrontBlock(new WindowLocation(100,20), BlockType.WALLINFRONT);
        notBlock.setRightSocket(wifBlock);
        wifBlock.setLeftPlug(notBlock);
        CC.disconnect(notBlock);
        assertEquals(null, wifBlock.getLeftPlug());
    }



    @Test
    public void disconnectCavity() {
    }

    @Test
    public void connectTopBottom() {
        ConnectionController CC = new ConnectionController();
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new WindowLocation(100,100), BlockType.MOVEFORWARD);
        ModelMoveBlock leftBlock = new ModelMoveBlock(new WindowLocation(100,20), BlockType.TURNLEFT);
        CC.connectTopBottom(leftBlock,forwardBlock);
        assertEquals(leftBlock, forwardBlock.getTopSocket());
    }

    @Test
    public void connectTopBottomInverse() {
        ConnectionController CC = new ConnectionController();
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new WindowLocation(100,100), BlockType.MOVEFORWARD);
        ModelMoveBlock leftBlock = new ModelMoveBlock(new WindowLocation(100,20), BlockType.TURNLEFT);
        CC.connectTopBottom(leftBlock,forwardBlock);
        assertEquals(forwardBlock, leftBlock.getBottomPlug());
    }

    @Test
    public void connectRightLeft(){
        ConnectionController CC = new ConnectionController();
        ModelNotBlock notBlock = new ModelNotBlock(new WindowLocation(100,100), BlockType.NOT);
        ModelWallInFrontBlock wifBlock = new ModelWallInFrontBlock(new WindowLocation(100,20), BlockType.WALLINFRONT);
        CC.connectRightLeft(wifBlock, notBlock);
        assertEquals(wifBlock, notBlock.getRightSocket());
    }

    @Test
    public void connectRightLeftInverse(){
        ConnectionController CC = new ConnectionController();
        ModelNotBlock notBlock = new ModelNotBlock(new WindowLocation(100,100), BlockType.NOT);
        ModelWallInFrontBlock wifBlock = new ModelWallInFrontBlock(new WindowLocation(100,20), BlockType.WALLINFRONT);
        CC.connectRightLeft(wifBlock, notBlock);
        assertEquals(notBlock, wifBlock.getLeftPlug());
    }

    @Test
    public void connectCavity() {
    }

    @Test
    public void connectIntoCavity() {
    }
}