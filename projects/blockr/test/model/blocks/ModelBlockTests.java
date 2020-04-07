package model.blocks;

import org.junit.Test;

import utilities.*;

import static org.junit.Assert.*;

public class ModelBlockTests {

    @Test
    public void isInCavityTrue(){
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new Location(100,100), BlockType.WHILE);
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(200,200), BlockType.MOVEFORWARD);
        ModelMoveBlock leftBlock = new ModelMoveBlock(new Location(300, 200), BlockType.TURNLEFT);
        ModelMoveBlock rightBlock = new ModelMoveBlock(new Location(400, 200), BlockType.TURNRIGHT);
        whileBlock.setCavityPlug(rightBlock);
        rightBlock.setTopSocket(whileBlock);
        rightBlock.setBottomPlug(forwardBlock);
        forwardBlock.setTopSocket(rightBlock);
        forwardBlock.setBottomPlug(leftBlock);
        leftBlock.setTopSocket(forwardBlock);
        leftBlock.setBottomPlug(whileBlock);
        whileBlock.setCavitySocket(leftBlock);
        assertTrue(forwardBlock.isInCavity());
    }

    @Test
    public void isInCavityFalse(){
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new Location(100,100), BlockType.WHILE);
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(400,400), BlockType.MOVEFORWARD);
        forwardBlock.setBottomPlug(whileBlock);
        whileBlock.setTopSocket(forwardBlock);
        assertFalse(forwardBlock.isInCavity());
    }

    @Test
    public void getSurroundingIfWhileBlock(){
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new Location(100,100), BlockType.WHILE);
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(400,400), BlockType.MOVEFORWARD);
        ModelMoveBlock leftBlock = new ModelMoveBlock(new Location(400, 550), BlockType.TURNLEFT);
        ModelMoveBlock rightBlock = new ModelMoveBlock(new Location(400, 700), BlockType.TURNRIGHT);
        whileBlock.setCavityPlug(rightBlock);
        rightBlock.setTopSocket(whileBlock);
        rightBlock.setBottomPlug(forwardBlock);
        forwardBlock.setTopSocket(rightBlock);
        forwardBlock.setBottomPlug(leftBlock);
        leftBlock.setTopSocket(forwardBlock);
        leftBlock.setBottomPlug(whileBlock);
        whileBlock.setCavitySocket(leftBlock);
        assertEquals(whileBlock, forwardBlock.getSurroundingWhileIfBlock());
    }

    @Test
    public void inBoundsOfElement(){
        ModelMoveBlock rightBlock = new ModelMoveBlock(new Location(400, 700), BlockType.TURNRIGHT);
        assertTrue(rightBlock.inBoundsOfElement(new Location(420, 720)));
    }
}