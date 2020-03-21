package model.blocks;

import static org.junit.Assert.*;
import org.junit.Test;

import utilities.*;

public class ModelWhileIfBlockTest {

    @Test
    public void connectTopSocket(){
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new WindowLocation(100,100),new Blocktype(Blocktype.WHILE));
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new WindowLocation(100,20), new Blocktype(Blocktype.MOVEFORWARD));
        whileBlock.connect(forwardBlock);
        assertEquals(forwardBlock, whileBlock.getTopSocket());
    }

    @Test
    public void connectTopSocketInverse(){
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new WindowLocation(100,100),new Blocktype(Blocktype.WHILE));
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new WindowLocation(100,20), new Blocktype(Blocktype.MOVEFORWARD));
        forwardBlock.connect(whileBlock);
        assertEquals(forwardBlock, whileBlock.getTopSocket());
    }

    @Test
    public void connectBottomPlug(){
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new WindowLocation(100,100),new Blocktype(Blocktype.WHILE));
        ModelMoveBlock leftBlock = new ModelMoveBlock(new WindowLocation(100,180), new Blocktype(Blocktype.TURNLEFT));
        whileBlock.connect(leftBlock);
        assertEquals(leftBlock,whileBlock.getBottomPlug());
    }

    @Test
    public void connectBottomPlugInverse(){
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new WindowLocation(100,100),new Blocktype(Blocktype.WHILE));
        ModelMoveBlock leftBlock = new ModelMoveBlock(new WindowLocation(100,180), new Blocktype(Blocktype.TURNLEFT));
        leftBlock.connect(whileBlock);
        assertEquals(leftBlock,whileBlock.getBottomPlug());
    }

    @Test
    public void connectRightSocket(){
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new WindowLocation(100,100),new Blocktype(Blocktype.WHILE));
        ModelNotBlock notBlock = new ModelNotBlock(new WindowLocation(180,90), new Blocktype(Blocktype.NOT));
        whileBlock.connect(notBlock);
        assertEquals(notBlock, whileBlock.getRightSocket());
    }

    @Test
    public void connectRightSocketInverse(){
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new WindowLocation(100,100),new Blocktype(Blocktype.WHILE));
        ModelNotBlock notBlock = new ModelNotBlock(new WindowLocation(180,90), new Blocktype(Blocktype.NOT));
        notBlock.connect(whileBlock);
        assertEquals(notBlock, whileBlock.getRightSocket());
    }

    @Test
    public void connectCavityPlug(){
        ModelWhileIfBlock ifBlock = new ModelWhileIfBlock(new WindowLocation(100,100),new Blocktype(Blocktype.WHILE));
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new WindowLocation(110,140), new Blocktype(Blocktype.MOVEFORWARD));
        ifBlock.connect(forwardBlock);
        assertEquals(forwardBlock, ifBlock.getCavityPlug());
    }

    @Test
    public void connectCavityPlugInverse(){
        ModelWhileIfBlock ifBlock = new ModelWhileIfBlock(new WindowLocation(100,100),new Blocktype(Blocktype.WHILE));
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new WindowLocation(110,140), new Blocktype(Blocktype.MOVEFORWARD));
        forwardBlock.connect(ifBlock);
        assertEquals(forwardBlock, ifBlock.getCavityPlug());
    }

    @Test
    public void connectCavitySocket(){
        ModelWhileIfBlock ifBlock = new ModelWhileIfBlock(new WindowLocation(100,100),new Blocktype(Blocktype.WHILE));
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new WindowLocation(110,60), new Blocktype(Blocktype.MOVEFORWARD));
        ifBlock.connect(forwardBlock);
        assertEquals(forwardBlock, ifBlock.getCavitySocket());
    }

    @Test
    public void connectCavitySocketInverse(){
        ModelWhileIfBlock ifBlock = new ModelWhileIfBlock(new WindowLocation(100,100),new Blocktype(Blocktype.WHILE));
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new WindowLocation(110,60), new Blocktype(Blocktype.MOVEFORWARD));
        forwardBlock.connect(ifBlock);
        assertEquals(forwardBlock, ifBlock.getCavitySocket());
    }

    @Test
    public void connectCavityPlugConnectsSocket(){
        ModelWhileIfBlock ifBlock = new ModelWhileIfBlock(new WindowLocation(100,100),new Blocktype(Blocktype.WHILE));
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new WindowLocation(110,140), new Blocktype(Blocktype.MOVEFORWARD));
        ifBlock.connect(forwardBlock);
        assertEquals(forwardBlock, ifBlock.getCavitySocket());
    }

    @Test
    public void connectCavityPlugInverseConnectsSocket(){
        ModelWhileIfBlock ifBlock = new ModelWhileIfBlock(new WindowLocation(100,100),new Blocktype(Blocktype.WHILE));
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new WindowLocation(110,140), new Blocktype(Blocktype.MOVEFORWARD));
        forwardBlock.connect(ifBlock);
        assertEquals(forwardBlock, ifBlock.getCavitySocket());
    }

    @Test
    public void connectCavitySocketConnectsPlug(){
        ModelWhileIfBlock ifBlock = new ModelWhileIfBlock(new WindowLocation(100,100),new Blocktype(Blocktype.WHILE));
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new WindowLocation(110,60), new Blocktype(Blocktype.MOVEFORWARD));
        ifBlock.connect(forwardBlock);
        assertEquals(forwardBlock, ifBlock.getCavityPlug());
    }

    @Test
    public void connectCavitySocketInverseConnectsPlug(){
        ModelWhileIfBlock ifBlock = new ModelWhileIfBlock(new WindowLocation(100,100),new Blocktype(Blocktype.WHILE));
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new WindowLocation(110,60), new Blocktype(Blocktype.MOVEFORWARD));
        forwardBlock.connect(ifBlock);
        assertEquals(forwardBlock, ifBlock.getCavityPlug());
    }

    @Test
    public void connectMultipleTopCavity(){
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new WindowLocation(100,100),new Blocktype(Blocktype.WHILE));
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new WindowLocation(400,400), new Blocktype(Blocktype.MOVEFORWARD));
        ModelMoveBlock leftBlock = new ModelMoveBlock(new WindowLocation(400, 550), new Blocktype(Blocktype.TURNLEFT));
        ModelMoveBlock rightBlock = new ModelMoveBlock(new WindowLocation(400, 700), new Blocktype(Blocktype.TURNRIGHT));
        forwardBlock.setTopSocketPos(whileBlock.getCavityPlugPos());
        forwardBlock.connect(whileBlock);
        leftBlock.setTopSocketPos(whileBlock.getCavityPlugPos());
        leftBlock.connect(whileBlock);
        rightBlock.setTopSocketPos(whileBlock.getCavityPlugPos());
        rightBlock.connect(whileBlock);
        assertEquals(3,whileBlock.getCavityBlocks().size()); /*The order may seem strange but that is because
        the second block is actually connecting on the bottom of the if/while cavity because it's bottomPlug
        is as close to the cavitySocket as it's topSocket is to the cavityPlug. We can't and we actually don't want
        to change this, this purely has to do with the order of the if statements in the cavityConnect function.
        */
    }

    @Test
    public void connectMultipleBottomCavity(){
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new WindowLocation(100,100),new Blocktype(Blocktype.WHILE));
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new WindowLocation(400,400), new Blocktype(Blocktype.MOVEFORWARD));
        ModelMoveBlock leftBlock = new ModelMoveBlock(new WindowLocation(400, 550), new Blocktype(Blocktype.TURNLEFT));
        ModelMoveBlock rightBlock = new ModelMoveBlock(new WindowLocation(400, 700), new Blocktype(Blocktype.TURNRIGHT));
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
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new WindowLocation(100,100),new Blocktype(Blocktype.WHILE));
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new WindowLocation(400,400), new Blocktype(Blocktype.MOVEFORWARD));
        ModelMoveBlock leftBlock = new ModelMoveBlock(new WindowLocation(400, 550), new Blocktype(Blocktype.TURNLEFT));
        ModelMoveBlock rightBlock = new ModelMoveBlock(new WindowLocation(400, 700), new Blocktype(Blocktype.TURNRIGHT));
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
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new WindowLocation(100,100),new Blocktype(Blocktype.WHILE));
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new WindowLocation(400,400), new Blocktype(Blocktype.MOVEFORWARD));
        forwardBlock.setBottomPlugPos(whileBlock.getTopSocketPos());
        whileBlock.connect(forwardBlock);
        assertEquals(forwardBlock, whileBlock.getTopSocket());
    }

    @Test
    public void setTopSocketPosConnects(){
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new WindowLocation(100,100),new Blocktype(Blocktype.WHILE));
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new WindowLocation(400,400), new Blocktype(Blocktype.MOVEFORWARD));
        forwardBlock.setTopSocketPos(whileBlock.getBottomPlugPos());
        whileBlock.connect(forwardBlock);
        assertEquals(forwardBlock, whileBlock.getBottomPlug());
    }

    @Test
    public void setLeftPlugPosConnects(){
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new WindowLocation(100,100),new Blocktype(Blocktype.WHILE));
        ModelNotBlock notBlock = new ModelNotBlock(new WindowLocation(400,400), new Blocktype(Blocktype.NOT));
        notBlock.setLeftPlugPos(whileBlock.getRightSocketPos());
        whileBlock.connect(notBlock);
        assertEquals(notBlock, whileBlock.getRightSocket());
    }

    @Test
    public void setTopSocketPosCavityConnects(){
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new WindowLocation(100,100),new Blocktype(Blocktype.WHILE));
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new WindowLocation(400,400), new Blocktype(Blocktype.MOVEFORWARD));
        forwardBlock.setTopSocketPos(whileBlock.getCavityPlugPos());
        whileBlock.connect(forwardBlock);
        assertEquals(forwardBlock, whileBlock.getCavityPlug());
    }

    @Test
    public void setBottomPlugPosCavityConnects(){
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new WindowLocation(100,100),new Blocktype(Blocktype.WHILE));
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new WindowLocation(400,400), new Blocktype(Blocktype.MOVEFORWARD));
        forwardBlock.setBottomPlugPos(whileBlock.getCavitySocketPos());
        whileBlock.connect(forwardBlock);
        assertEquals(forwardBlock, whileBlock.getCavitySocket());
    }

    @Test
    public void disconnect(){
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new WindowLocation(100,100),new Blocktype(Blocktype.WHILE));
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new WindowLocation(400,400), new Blocktype(Blocktype.MOVEFORWARD));
        ModelMoveBlock leftBlock = new ModelMoveBlock(new WindowLocation(400,520), new Blocktype(Blocktype.TURNLEFT));
        forwardBlock.setTopSocketPos(whileBlock.getBottomPlugPos());
        leftBlock.setBottomPlugPos(whileBlock.getTopSocketPos());
        whileBlock.connect(forwardBlock);
        whileBlock.connect(leftBlock);
        whileBlock.setPos(new WindowLocation(400,400));
        whileBlock.disconnect();
        assertTrue(whileBlock.getConnections().isEmpty());
    }

    @Test
    public void disconnectInverse(){
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new WindowLocation(100,100),new Blocktype(Blocktype.WHILE));
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new WindowLocation(400,400), new Blocktype(Blocktype.MOVEFORWARD));
        ModelMoveBlock leftBlock = new ModelMoveBlock(new WindowLocation(400,520), new Blocktype(Blocktype.TURNLEFT));
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
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new WindowLocation(100,100),new Blocktype(Blocktype.WHILE));
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new WindowLocation(400,400), new Blocktype(Blocktype.MOVEFORWARD));
        forwardBlock.setTopSocketPos(whileBlock.getCavityPlugPos());
        whileBlock.connectCavity(forwardBlock);
        whileBlock.disconnectCavity(forwardBlock);
        assertTrue(whileBlock.getCavityBlocks().isEmpty());
    }

    @Test
    public void disconnectCavityInverse(){
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new WindowLocation(100,100),new Blocktype(Blocktype.WHILE));
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new WindowLocation(400,400), new Blocktype(Blocktype.MOVEFORWARD));
        forwardBlock.setTopSocketPos(whileBlock.getCavityPlugPos());
        whileBlock.connectCavity(forwardBlock);
        forwardBlock.disconnect();
        assertTrue(whileBlock.getCavityBlocks().isEmpty());
    }

    @Test
    public void disconnectOneCavityManySize(){
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new WindowLocation(100,100),new Blocktype(Blocktype.WHILE));
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new WindowLocation(400,400), new Blocktype(Blocktype.MOVEFORWARD));
        ModelMoveBlock leftBlock = new ModelMoveBlock(new WindowLocation(400, 550), new Blocktype(Blocktype.TURNLEFT));
        ModelMoveBlock rightBlock = new ModelMoveBlock(new WindowLocation(400, 700), new Blocktype(Blocktype.TURNRIGHT));
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
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new WindowLocation(100,100),new Blocktype(Blocktype.WHILE));
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new WindowLocation(400,400), new Blocktype(Blocktype.MOVEFORWARD));
        ModelMoveBlock leftBlock = new ModelMoveBlock(new WindowLocation(400, 550), new Blocktype(Blocktype.TURNLEFT));
        ModelMoveBlock rightBlock = new ModelMoveBlock(new WindowLocation(400, 700), new Blocktype(Blocktype.TURNRIGHT));
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
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new WindowLocation(100,100),new Blocktype(Blocktype.WHILE));
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new WindowLocation(400,400), new Blocktype(Blocktype.MOVEFORWARD));
        ModelMoveBlock leftBlock = new ModelMoveBlock(new WindowLocation(400, 550), new Blocktype(Blocktype.TURNLEFT));
        ModelMoveBlock rightBlock = new ModelMoveBlock(new WindowLocation(400, 700), new Blocktype(Blocktype.TURNRIGHT));
        forwardBlock.setTopSocketPos(whileBlock.getCavityPlugPos());
        forwardBlock.connect(whileBlock);
        leftBlock.setTopSocketPos(whileBlock.getCavityPlugPos());
        leftBlock.connect(whileBlock);
        rightBlock.setTopSocketPos(whileBlock.getCavityPlugPos());
        rightBlock.connect(whileBlock);
        leftBlock.disconnect();
        assertEquals(new WindowLocation(113,223), forwardBlock.getPos());
    }

    @Test
    public void updateCavityBlocksLocationTopBottom(){
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new WindowLocation(100,100),new Blocktype(Blocktype.WHILE));
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new WindowLocation(400,400), new Blocktype(Blocktype.MOVEFORWARD));
        ModelMoveBlock leftBlock = new ModelMoveBlock(new WindowLocation(400, 550), new Blocktype(Blocktype.TURNLEFT));
        ModelMoveBlock rightBlock = new ModelMoveBlock(new WindowLocation(400, 700), new Blocktype(Blocktype.TURNRIGHT));
        forwardBlock.setTopSocketPos(whileBlock.getCavityPlugPos());
        forwardBlock.connect(whileBlock);
        leftBlock.setTopSocketPos(whileBlock.getCavityPlugPos());
        leftBlock.connect(whileBlock);
        rightBlock.setTopSocketPos(whileBlock.getCavityPlugPos());
        rightBlock.connect(whileBlock);
        assertEquals(new WindowLocation(113,303), leftBlock.getPos());
    }

    @Test
    public void updateCavityBlocksLocationTopMiddle(){
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new WindowLocation(100,100),new Blocktype(Blocktype.WHILE));
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new WindowLocation(400,400), new Blocktype(Blocktype.MOVEFORWARD));
        ModelMoveBlock leftBlock = new ModelMoveBlock(new WindowLocation(400, 550), new Blocktype(Blocktype.TURNLEFT));
        ModelMoveBlock rightBlock = new ModelMoveBlock(new WindowLocation(400, 700), new Blocktype(Blocktype.TURNRIGHT));
        forwardBlock.setTopSocketPos(whileBlock.getCavityPlugPos());
        forwardBlock.connect(whileBlock);
        leftBlock.setTopSocketPos(whileBlock.getCavityPlugPos());
        leftBlock.connect(whileBlock);
        rightBlock.setTopSocketPos(whileBlock.getCavityPlugPos());
        rightBlock.connect(whileBlock);
        assertEquals(new WindowLocation(113,223), forwardBlock.getPos());
    }

    @Test
    public void updateCavityBlocksLocationTopTop(){
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new WindowLocation(100,100),new Blocktype(Blocktype.WHILE));
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new WindowLocation(400,400), new Blocktype(Blocktype.MOVEFORWARD));
        ModelMoveBlock leftBlock = new ModelMoveBlock(new WindowLocation(400, 550), new Blocktype(Blocktype.TURNLEFT));
        ModelMoveBlock rightBlock = new ModelMoveBlock(new WindowLocation(400, 700), new Blocktype(Blocktype.TURNRIGHT));
        forwardBlock.setTopSocketPos(whileBlock.getCavityPlugPos());
        forwardBlock.connect(whileBlock);
        leftBlock.setTopSocketPos(whileBlock.getCavityPlugPos());
        leftBlock.connect(whileBlock);
        rightBlock.setTopSocketPos(whileBlock.getCavityPlugPos());
        rightBlock.connect(whileBlock);
        assertEquals(new WindowLocation(113,143), rightBlock.getPos());
    }

    @Test
    public void updateCavityBlocksLocationBottom(){
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new WindowLocation(100,100),new Blocktype(Blocktype.WHILE));
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new WindowLocation(400,400), new Blocktype(Blocktype.MOVEFORWARD));
        ModelMoveBlock leftBlock = new ModelMoveBlock(new WindowLocation(400, 550), new Blocktype(Blocktype.TURNLEFT));
        ModelMoveBlock rightBlock = new ModelMoveBlock(new WindowLocation(400, 700), new Blocktype(Blocktype.TURNRIGHT));
        forwardBlock.setBottomPlugPos(whileBlock.getCavitySocketPos());
        forwardBlock.connect(whileBlock);
        leftBlock.setBottomPlugPos(whileBlock.getCavitySocketPos());
        leftBlock.connect(whileBlock);
        rightBlock.setBottomPlugPos(whileBlock.getCavitySocketPos());
        rightBlock.connect(whileBlock);
        assertEquals(new WindowLocation(113,303), rightBlock.getPos());
    }

    @Test
    public void updateCavityBlocksLocationIntoMiddle(){
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new WindowLocation(100,100),new Blocktype(Blocktype.WHILE));
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new WindowLocation(400,400), new Blocktype(Blocktype.MOVEFORWARD));
        ModelMoveBlock leftBlock = new ModelMoveBlock(new WindowLocation(400, 550), new Blocktype(Blocktype.TURNLEFT));
        ModelMoveBlock rightBlock = new ModelMoveBlock(new WindowLocation(400, 700), new Blocktype(Blocktype.TURNRIGHT));
        forwardBlock.setBottomPlugPos(whileBlock.getCavitySocketPos());
        forwardBlock.connect(whileBlock);
        rightBlock.setBottomPlugPos(whileBlock.getCavitySocketPos());
        rightBlock.connect(whileBlock);
        leftBlock.setBottomPlugPos(rightBlock.getTopSocketPos());
        leftBlock.connect(rightBlock);
        assertEquals(new WindowLocation(113,223), leftBlock.getPos());
    }

    @Test
    public void updateCavityBlocksLocationIntoBottom(){
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new WindowLocation(100,100),new Blocktype(Blocktype.WHILE));
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new WindowLocation(400,400), new Blocktype(Blocktype.MOVEFORWARD));
        ModelMoveBlock leftBlock = new ModelMoveBlock(new WindowLocation(400, 550), new Blocktype(Blocktype.TURNLEFT));
        ModelMoveBlock rightBlock = new ModelMoveBlock(new WindowLocation(400, 700), new Blocktype(Blocktype.TURNRIGHT));
        forwardBlock.setBottomPlugPos(whileBlock.getCavitySocketPos());
        forwardBlock.connect(whileBlock);
        rightBlock.setBottomPlugPos(whileBlock.getCavitySocketPos());
        rightBlock.connect(whileBlock);
        leftBlock.setBottomPlugPos(rightBlock.getTopSocketPos());
        leftBlock.connect(rightBlock);
        assertEquals(new WindowLocation(113,303),rightBlock.getPos());
    }



}
