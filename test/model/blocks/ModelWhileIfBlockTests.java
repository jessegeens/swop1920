package model.blocks;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import utilities.*;

public class ModelWhileIfBlockTests {

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

}
