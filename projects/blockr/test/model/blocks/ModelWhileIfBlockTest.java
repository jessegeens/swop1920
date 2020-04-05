package model.blocks;

import static org.junit.Assert.*;
import org.junit.Test;

import utilities.*;

public class ModelWhileIfBlockTest {
    /*

    @Test
    public void connectTopSocket(){
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new Location(100,100), BlockType.WHILE);
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(100,20), BlockType.MOVEFORWARD);
        whileBlock.connect(forwardBlock);
        assertEquals(forwardBlock, whileBlock.getTopSocket());
    }

    @Test
    public void connectTopSocketInverse(){
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new Location(100,100), BlockType.WHILE);
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(100,20), BlockType.MOVEFORWARD);
        forwardBlock.connect(whileBlock);
        assertEquals(forwardBlock, whileBlock.getTopSocket());
    }

    @Test
    public void connectBottomPlug(){
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new Location(100,100), BlockType.WHILE);
        ModelMoveBlock leftBlock = new ModelMoveBlock(new Location(100,180), BlockType.TURNLEFT);
        whileBlock.connect(leftBlock);
        assertEquals(leftBlock,whileBlock.getBottomPlug());
    }

    @Test
    public void connectBottomPlugInverse(){
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new Location(100,100), BlockType.WHILE);
        ModelMoveBlock leftBlock = new ModelMoveBlock(new Location(100,180), BlockType.TURNLEFT);
        leftBlock.connect(whileBlock);
        assertEquals(leftBlock,whileBlock.getBottomPlug());
    }

    @Test
    public void connectRightSocket(){
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new Location(100,100), BlockType.WHILE);
        ModelNotBlock notBlock = new ModelNotBlock(new Location(180,90), BlockType.NOT);
        whileBlock.connect(notBlock);
        assertEquals(notBlock, whileBlock.getRightSocket());
    }

    @Test
    public void connectRightSocketInverse(){
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new Location(100,100), BlockType.WHILE);
        ModelNotBlock notBlock = new ModelNotBlock(new Location(180,90), BlockType.NOT);
        notBlock.connect(whileBlock);
        assertEquals(notBlock, whileBlock.getRightSocket());
    }

    @Test
    public void connectCavityPlug(){
        ModelWhileIfBlock ifBlock = new ModelWhileIfBlock(new Location(100,100), BlockType.WHILE);
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(110,140), BlockType.MOVEFORWARD);
        ifBlock.connect(forwardBlock);
        assertEquals(forwardBlock, ifBlock.getCavityPlug());
    }

    @Test
    public void connectCavityPlugInverse(){
        ModelWhileIfBlock ifBlock = new ModelWhileIfBlock(new Location(100,100), BlockType.WHILE);
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(110,140), BlockType.MOVEFORWARD);
        forwardBlock.connect(ifBlock);
        assertEquals(forwardBlock, ifBlock.getCavityPlug());
    }

    @Test
    public void connectCavitySocket(){
        ModelWhileIfBlock ifBlock = new ModelWhileIfBlock(new Location(100,100), BlockType.WHILE);
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(110,60), BlockType.MOVEFORWARD);
        ifBlock.connect(forwardBlock);
        assertEquals(forwardBlock, ifBlock.getCavitySocket());
    }

    @Test
    public void connectCavitySocketInverse(){
        ModelWhileIfBlock ifBlock = new ModelWhileIfBlock(new Location(100,100), BlockType.WHILE);
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(110,60), BlockType.MOVEFORWARD);
        forwardBlock.connect(ifBlock);
        assertEquals(forwardBlock, ifBlock.getCavitySocket());
    }

    @Test
    public void connectCavityPlugConnectsSocket(){
        ModelWhileIfBlock ifBlock = new ModelWhileIfBlock(new Location(100,100), BlockType.WHILE);
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(110,140), BlockType.MOVEFORWARD);
        ifBlock.connect(forwardBlock);
        assertEquals(forwardBlock, ifBlock.getCavitySocket());
    }

    @Test
    public void connectCavityPlugInverseConnectsSocket(){
        ModelWhileIfBlock ifBlock = new ModelWhileIfBlock(new Location(100,100), BlockType.WHILE);
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(110,140), BlockType.MOVEFORWARD);
        forwardBlock.connect(ifBlock);
        assertEquals(forwardBlock, ifBlock.getCavitySocket());
    }

    @Test
    public void connectCavitySocketConnectsPlug(){
        ModelWhileIfBlock ifBlock = new ModelWhileIfBlock(new Location(100,100), BlockType.WHILE);
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(110,60), BlockType.MOVEFORWARD);
        ifBlock.connect(forwardBlock);
        assertEquals(forwardBlock, ifBlock.getCavityPlug());
    }

    @Test
    public void connectCavitySocketInverseConnectsPlug(){
        ModelWhileIfBlock ifBlock = new ModelWhileIfBlock(new Location(100,100), BlockType.WHILE);
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(110,60), BlockType.MOVEFORWARD);
        forwardBlock.connect(ifBlock);
        assertEquals(forwardBlock, ifBlock.getCavityPlug());
    }

    @Test
    public void connectMultipleTopCavity(){
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new Location(100,100), BlockType.WHILE);
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(400,400), BlockType.MOVEFORWARD);
        ModelMoveBlock leftBlock = new ModelMoveBlock(new Location(400, 550), BlockType.TURNLEFT);
        ModelMoveBlock rightBlock = new ModelMoveBlock(new Location(400, 700), BlockType.TURNRIGHT);
        forwardBlock.setTopSocketPos(whileBlock.getCavityPlugPos());
        forwardBlock.connect(whileBlock);
        leftBlock.setTopSocketPos(whileBlock.getCavityPlugPos());
        leftBlock.connect(whileBlock);
        rightBlock.setTopSocketPos(whileBlock.getCavityPlugPos());
        rightBlock.connect(whileBlock);
        assertEquals(3,whileBlock.getCavityBlocks().size());
    }

    @Test
    public void connectMultipleBottomCavity(){
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new Location(100,100), BlockType.WHILE);
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(400,400), BlockType.MOVEFORWARD);
        ModelMoveBlock leftBlock = new ModelMoveBlock(new Location(400, 550), BlockType.TURNLEFT);
        ModelMoveBlock rightBlock = new ModelMoveBlock(new Location(400, 700), BlockType.TURNRIGHT);
        forwardBlock.setBottomPlugPos(whileBlock.getCavitySocketPos());
        forwardBlock.connect(whileBlock);
        leftBlock.setBottomPlugPos(whileBlock.getCavitySocketPos());
        leftBlock.connect(whileBlock);
        rightBlock.setBottomPlugPos(whileBlock.getCavitySocketPos());
        rightBlock.connect(whileBlock);
        assertEquals(3,whileBlock.getCavityBlocks().size());
    }

    @Test
    public void connectIntoCavity(){
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new Location(100,100), BlockType.WHILE);
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(400,400), BlockType.MOVEFORWARD);
        ModelMoveBlock leftBlock = new ModelMoveBlock(new Location(400, 550), BlockType.TURNLEFT);
        ModelMoveBlock rightBlock = new ModelMoveBlock(new Location(400, 700), BlockType.TURNRIGHT);
        forwardBlock.setBottomPlugPos(whileBlock.getCavitySocketPos());
        forwardBlock.connect(whileBlock);
        rightBlock.setBottomPlugPos(whileBlock.getCavitySocketPos());
        rightBlock.connect(whileBlock);
        leftBlock.setBottomPlugPos(rightBlock.getTopSocketPos());
        leftBlock.connect(rightBlock);
        assertEquals(3,whileBlock.getCavityBlocks().size());
    }

    @Test
    public void setBottomPlugPosConnects(){
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new Location(100,100), BlockType.WHILE);
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(400,400), BlockType.MOVEFORWARD);
        forwardBlock.setBottomPlugPos(whileBlock.getTopSocketPos());
        whileBlock.connect(forwardBlock);
        assertEquals(forwardBlock, whileBlock.getTopSocket());
    }

    @Test
    public void setTopSocketPosConnects(){
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new Location(100,100), BlockType.WHILE);
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(400,400), BlockType.MOVEFORWARD);
        forwardBlock.setTopSocketPos(whileBlock.getBottomPlugPos());
        whileBlock.connect(forwardBlock);
        assertEquals(forwardBlock, whileBlock.getBottomPlug());
    }

    @Test
    public void setLeftPlugPosConnects(){
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new Location(100,100), BlockType.WHILE);
        ModelNotBlock notBlock = new ModelNotBlock(new Location(400,400), BlockType.NOT);
        notBlock.setLeftPlugPos(whileBlock.getRightSocketPos());
        whileBlock.connect(notBlock);
        assertEquals(notBlock, whileBlock.getRightSocket());
    }

    @Test
    public void setTopSocketPosCavityConnects(){
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new Location(100,100), BlockType.WHILE);
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(400,400), BlockType.MOVEFORWARD);
        forwardBlock.setTopSocketPos(whileBlock.getCavityPlugPos());
        whileBlock.connect(forwardBlock);
        assertEquals(forwardBlock, whileBlock.getCavityPlug());
    }

    @Test
    public void setBottomPlugPosCavityConnects(){
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new Location(100,100), BlockType.WHILE);
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(400,400), BlockType.MOVEFORWARD);
        forwardBlock.setBottomPlugPos(whileBlock.getCavitySocketPos());
        whileBlock.connect(forwardBlock);
        assertEquals(forwardBlock, whileBlock.getCavitySocket());
    }

    @Test
    public void disconnect(){
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new Location(100,100), BlockType.WHILE);
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(400,400), BlockType.MOVEFORWARD);
        ModelMoveBlock leftBlock = new ModelMoveBlock(new Location(400,520), BlockType.TURNLEFT);
        forwardBlock.setTopSocketPos(whileBlock.getBottomPlugPos());
        leftBlock.setBottomPlugPos(whileBlock.getTopSocketPos());
        whileBlock.connect(forwardBlock);
        whileBlock.connect(leftBlock);
        whileBlock.setPos(new Location(400,400));
        whileBlock.disconnect();
        assertTrue(whileBlock.getConnections().isEmpty());
    }

    @Test
    public void disconnectInverse(){
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new Location(100,100), BlockType.WHILE);
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(400,400), BlockType.MOVEFORWARD);
        ModelMoveBlock leftBlock = new ModelMoveBlock(new Location(400,520), BlockType.TURNLEFT);
        forwardBlock.setTopSocketPos(whileBlock.getBottomPlugPos());
        leftBlock.setBottomPlugPos(whileBlock.getTopSocketPos());
        whileBlock.connect(forwardBlock);
        whileBlock.connect(leftBlock);
        forwardBlock.disconnect();
        leftBlock.disconnect();
        assertTrue(whileBlock.getConnections().isEmpty());
    }

    @Test
    public void disconnectCavity(){
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new Location(100,100), BlockType.WHILE);
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(400,400), BlockType.MOVEFORWARD);
        forwardBlock.setTopSocketPos(whileBlock.getCavityPlugPos());
        whileBlock.connectCavity(forwardBlock);
        whileBlock.disconnectCavity(forwardBlock);
        assertTrue(whileBlock.getCavityBlocks().isEmpty());
    }

    @Test
    public void disconnectCavityInverse(){
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new Location(100,100), BlockType.WHILE);
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(400,400), BlockType.MOVEFORWARD);
        forwardBlock.setTopSocketPos(whileBlock.getCavityPlugPos());
        whileBlock.connectCavity(forwardBlock);
        forwardBlock.disconnect();
        assertTrue(whileBlock.getCavityBlocks().isEmpty());
    }

    @Test
    public void disconnectOneCavityManySize(){
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new Location(100,100), BlockType.WHILE);
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(400,400), BlockType.MOVEFORWARD);
        ModelMoveBlock leftBlock = new ModelMoveBlock(new Location(400, 550), BlockType.TURNLEFT);
        ModelMoveBlock rightBlock = new ModelMoveBlock(new Location(400, 700), BlockType.TURNRIGHT);
        forwardBlock.setTopSocketPos(whileBlock.getCavityPlugPos());
        forwardBlock.connect(whileBlock);
        leftBlock.setTopSocketPos(whileBlock.getCavityPlugPos());
        leftBlock.connect(whileBlock);
        rightBlock.setTopSocketPos(whileBlock.getCavityPlugPos());
        rightBlock.connect(whileBlock);
        leftBlock.disconnect();
        assertEquals(2,whileBlock.getCavityBlocks().size());
    }

    @Test
    public void disconnectOneCavityManyHeight(){
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new Location(100,100), BlockType.WHILE);
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(400,400), BlockType.MOVEFORWARD);
        ModelMoveBlock leftBlock = new ModelMoveBlock(new Location(400, 550), BlockType.TURNLEFT);
        ModelMoveBlock rightBlock = new ModelMoveBlock(new Location(400, 700), BlockType.TURNRIGHT);
        forwardBlock.setTopSocketPos(whileBlock.getCavityPlugPos());
        forwardBlock.connect(whileBlock);
        leftBlock.setTopSocketPos(whileBlock.getCavityPlugPos());
        leftBlock.connect(whileBlock);
        rightBlock.setTopSocketPos(whileBlock.getCavityPlugPos());
        rightBlock.connect(whileBlock);
        leftBlock.disconnect();
        assertEquals(3*80,whileBlock.getHeight());
    }

    @Test
    public void disconnectMiddleCavityBottomPosition(){
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new Location(100,100), BlockType.WHILE);
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(400,400), BlockType.MOVEFORWARD);
        ModelMoveBlock leftBlock = new ModelMoveBlock(new Location(400, 550), BlockType.TURNLEFT);
        ModelMoveBlock rightBlock = new ModelMoveBlock(new Location(400, 700), BlockType.TURNRIGHT);
        forwardBlock.setTopSocketPos(whileBlock.getCavityPlugPos());
        forwardBlock.connect(whileBlock);
        leftBlock.setTopSocketPos(whileBlock.getCavityPlugPos());
        leftBlock.connect(whileBlock);
        rightBlock.setTopSocketPos(whileBlock.getCavityPlugPos());
        rightBlock.connect(whileBlock);
        leftBlock.disconnect();
        assertEquals(new Location(113,223), forwardBlock.getPos());
    }

    @Test
    public void updateCavityBlocksLocationTopBottom(){
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new Location(100,100), BlockType.WHILE);
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(400,400), BlockType.MOVEFORWARD);
        ModelMoveBlock leftBlock = new ModelMoveBlock(new Location(400, 550), BlockType.TURNLEFT);
        ModelMoveBlock rightBlock = new ModelMoveBlock(new Location(400, 700), BlockType.TURNRIGHT);
        forwardBlock.setTopSocketPos(whileBlock.getCavityPlugPos());
        forwardBlock.connect(whileBlock);
        leftBlock.setTopSocketPos(whileBlock.getCavityPlugPos());
        leftBlock.connect(whileBlock);
        rightBlock.setTopSocketPos(whileBlock.getCavityPlugPos());
        rightBlock.connect(whileBlock);
        assertEquals(new Location(113,303), leftBlock.getPos());
    }

    @Test
    public void updateCavityBlocksLocationTopMiddle(){
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new Location(100,100), BlockType.WHILE);
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(400,400), BlockType.MOVEFORWARD);
        ModelMoveBlock leftBlock = new ModelMoveBlock(new Location(400, 550), BlockType.TURNLEFT);
        ModelMoveBlock rightBlock = new ModelMoveBlock(new Location(400, 700), BlockType.TURNRIGHT);
        forwardBlock.setTopSocketPos(whileBlock.getCavityPlugPos());
        forwardBlock.connect(whileBlock);
        leftBlock.setTopSocketPos(whileBlock.getCavityPlugPos());
        leftBlock.connect(whileBlock);
        rightBlock.setTopSocketPos(whileBlock.getCavityPlugPos());
        rightBlock.connect(whileBlock);
        assertEquals(new Location(113,223), forwardBlock.getPos());
    }

    @Test
    public void updateCavityBlocksLocationTopTop(){
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new Location(100,100), BlockType.WHILE);
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(400,400), BlockType.MOVEFORWARD);
        ModelMoveBlock leftBlock = new ModelMoveBlock(new Location(400, 550), BlockType.TURNLEFT);
        ModelMoveBlock rightBlock = new ModelMoveBlock(new Location(400, 700), BlockType.TURNRIGHT);
        forwardBlock.setTopSocketPos(whileBlock.getCavityPlugPos());
        forwardBlock.connect(whileBlock);
        leftBlock.setTopSocketPos(whileBlock.getCavityPlugPos());
        leftBlock.connect(whileBlock);
        rightBlock.setTopSocketPos(whileBlock.getCavityPlugPos());
        rightBlock.connect(whileBlock);
        assertEquals(new Location(113,143), rightBlock.getPos());
    }

    @Test
    public void updateCavityBlocksLocationBottom(){
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new Location(100,100), BlockType.WHILE);
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(400,400), BlockType.MOVEFORWARD);
        ModelMoveBlock leftBlock = new ModelMoveBlock(new Location(400, 550), BlockType.TURNLEFT);
        ModelMoveBlock rightBlock = new ModelMoveBlock(new Location(400, 700), BlockType.TURNRIGHT);
        forwardBlock.setBottomPlugPos(whileBlock.getCavitySocketPos());
        forwardBlock.connect(whileBlock);
        leftBlock.setBottomPlugPos(whileBlock.getCavitySocketPos());
        leftBlock.connect(whileBlock);
        rightBlock.setBottomPlugPos(whileBlock.getCavitySocketPos());
        rightBlock.connect(whileBlock);
        assertEquals(new Location(113,303), rightBlock.getPos());
    }

    @Test
    public void updateCavityBlocksLocationIntoMiddle(){
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new Location(100,100), BlockType.WHILE);
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(400,400), BlockType.MOVEFORWARD);
        ModelMoveBlock leftBlock = new ModelMoveBlock(new Location(400, 550), BlockType.TURNLEFT);
        ModelMoveBlock rightBlock = new ModelMoveBlock(new Location(400, 700), BlockType.TURNRIGHT);
        forwardBlock.setBottomPlugPos(whileBlock.getCavitySocketPos());
        forwardBlock.connect(whileBlock);
        rightBlock.setBottomPlugPos(whileBlock.getCavitySocketPos());
        rightBlock.connect(whileBlock);
        leftBlock.setBottomPlugPos(rightBlock.getTopSocketPos());
        leftBlock.connect(rightBlock);
        assertEquals(new Location(113,223), leftBlock.getPos());
    }

    @Test
    public void updateCavityBlocksLocationIntoBottom(){
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new Location(100,100), BlockType.WHILE);
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(400,400), BlockType.MOVEFORWARD);
        ModelMoveBlock leftBlock = new ModelMoveBlock(new Location(400, 550), BlockType.TURNLEFT);
        ModelMoveBlock rightBlock = new ModelMoveBlock(new Location(400, 700), BlockType.TURNRIGHT);
        forwardBlock.setBottomPlugPos(whileBlock.getCavitySocketPos());
        forwardBlock.connect(whileBlock);
        rightBlock.setBottomPlugPos(whileBlock.getCavitySocketPos());
        rightBlock.connect(whileBlock);
        leftBlock.setBottomPlugPos(rightBlock.getTopSocketPos());
        leftBlock.connect(rightBlock);
        assertEquals(new Location(113,303),rightBlock.getPos());
    }
    */
}
