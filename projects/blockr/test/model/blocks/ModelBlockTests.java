package model.blocks;

import org.junit.Test;
import utilities.*;

import static org.junit.Assert.*;

public class ModelBlockTests {

    @Test
    public void isInCavityTrue(){
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new ProgramLocation(100,100), BlockType.WHILE);
        ModelActionBlock forwardBlock = new ModelActionBlock(new ProgramLocation(200, 200), BlockType.ACTION, null);
        ModelActionBlock leftBlock = new ModelActionBlock(new ProgramLocation(300, 200), BlockType.ACTION, null);
        ModelActionBlock rightBlock = new ModelActionBlock(new ProgramLocation(400, 200), BlockType.ACTION, null);
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
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new ProgramLocation(100,100), BlockType.WHILE);
        ModelActionBlock forwardBlock = new ModelActionBlock(new ProgramLocation(400,400), BlockType.ACTION, null);
        forwardBlock.setBottomPlug(whileBlock);
        whileBlock.setTopSocket(forwardBlock);
        assertFalse(forwardBlock.isInCavity());
    }

    @Test
    public void getSurroundingIfWhileBlock(){
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new ProgramLocation(100,100), BlockType.WHILE);
        ModelActionBlock forwardBlock = new ModelActionBlock(new ProgramLocation(400,400), BlockType.ACTION, null);
        ModelActionBlock leftBlock = new ModelActionBlock(new ProgramLocation(400, 550), BlockType.ACTION, null);
        ModelActionBlock rightBlock = new ModelActionBlock(new ProgramLocation(400, 700), BlockType.ACTION, null);
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
        ModelActionBlock rightBlock = new ModelActionBlock(new ProgramLocation(400, 700), BlockType.ACTION, null);
        assertTrue(rightBlock.inBoundsOfElement(new ProgramLocation(420, 720)));
    }
}