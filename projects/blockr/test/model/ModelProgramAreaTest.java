package model;

import static org.junit.Assert.*;

import model.blocks.*;
import org.junit.Test;

import utilities.*;

public class ModelProgramAreaTest {

    @Test
    public void addPABlock(){
        ModelProgramArea area = new ModelProgramArea();
        ModelActionBlock forwardBlock = new ModelActionBlock(new ProgramLocation(100,100), BlockType.ACTION, null);
        ModelActionBlock leftBlock = new ModelActionBlock(new ProgramLocation(400, 550), BlockType.ACTION, null);
        ModelActionBlock rightBlock = new ModelActionBlock(new ProgramLocation(400, 700), BlockType.ACTION, null);
        forwardBlock.setBottomPlug(leftBlock);
        leftBlock.setTopSocket(forwardBlock);
        leftBlock.setBottomPlug(rightBlock);
        rightBlock.setTopSocket(leftBlock);
        area.addPABlock(forwardBlock);
        area.addPABlock(leftBlock);
        area.addPABlock(rightBlock);
        assertEquals(3, area.getPABlocks().size());
    }

    @Test
    public void removePABlock(){
        ModelProgramArea area = new ModelProgramArea();
        ModelActionBlock forwardBlock = new ModelActionBlock(new ProgramLocation(400,400), BlockType.ACTION, null);
        ModelActionBlock leftBlock = new ModelActionBlock(new ProgramLocation(400, 480), BlockType.ACTION, null);
        ModelActionBlock rightBlock = new ModelActionBlock(new ProgramLocation(400, 560), BlockType.ACTION, null);
        area.addPABlock(forwardBlock);
        area.addPABlock(leftBlock);
        area.addPABlock(rightBlock);
        forwardBlock.setBottomPlug(leftBlock);
        leftBlock.setTopSocket(forwardBlock);
        leftBlock.setBottomPlug(rightBlock);
        rightBlock.setTopSocket(leftBlock);
        area.removePABlock(leftBlock);
        assertEquals(0,leftBlock.getConnections().size());
    }

    @Test
    public void removeWhileBlockCavity(){
        ModelProgramArea area = new ModelProgramArea();
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new ProgramLocation(400,400), BlockType.WHILE);
        ModelActionBlock leftBlock = new ModelActionBlock(new ProgramLocation(413, 443), BlockType.ACTION, null);
        ModelActionBlock rightBlock = new ModelActionBlock(new ProgramLocation(413, 523), BlockType.ACTION, null);
        area.addPABlock(whileBlock);
        area.addPABlock(leftBlock);
        area.addPABlock(rightBlock);
        whileBlock.setCavityPlug(leftBlock);
        leftBlock.setTopSocket(whileBlock);
        leftBlock.setBottomPlug(rightBlock);
        rightBlock.setTopSocket(leftBlock);
        rightBlock.setBottomPlug(whileBlock);
        whileBlock.setCavitySocket(rightBlock);
        area.removePABlock(whileBlock);
        assertEquals(0,area.getPABlocks().size());
    }

    @Test
    public void addWhileBlockCavity(){
        ModelProgramArea area = new ModelProgramArea();
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new ProgramLocation(400,400), BlockType.WHILE);
        ModelActionBlock leftBlock = new ModelActionBlock(new ProgramLocation(413, 443), BlockType.ACTION, null);
        ModelActionBlock rightBlock = new ModelActionBlock(new ProgramLocation(413, 523), BlockType.ACTION, null);
        whileBlock.setCavityPlug(leftBlock);
        leftBlock.setTopSocket(whileBlock);
        leftBlock.setBottomPlug(rightBlock);
        rightBlock.setTopSocket(leftBlock);
        rightBlock.setBottomPlug(whileBlock);
        whileBlock.setCavitySocket(rightBlock);
        area.addPABlock(whileBlock);
        assertEquals(3,area.getPABlocks().size());
    }

    @Test
    public void handleMouseDown(){
        ModelProgramArea area = new ModelProgramArea();
        ModelActionBlock forwardBlock = new ModelActionBlock(new ProgramLocation(100,100), BlockType.ACTION, null);
        area.addPABlock(forwardBlock);
        ModelActionBlock leftBlock = new ModelActionBlock(new ProgramLocation(100, 100), BlockType.ACTION, null);
        area.addPABlock(leftBlock);
        assertEquals(leftBlock,area.handleMouseDown(new ProgramLocation(140,140)));
    }

    @Test
    public void handleMouseUp(){
        ModelProgramArea area = new ModelProgramArea();
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new ProgramLocation(100,100), BlockType.WHILE);
        ModelActionBlock forwardBlock = new ModelActionBlock(new ProgramLocation(100,20), BlockType.ACTION, null);
        ModelActionBlock leftBlock = new ModelActionBlock(new ProgramLocation(100, 180), BlockType.ACTION, null);
        ModelPredicateBlock wifBlock = new ModelPredicateBlock(new ProgramLocation(180, 100), BlockType.PREDICATE, null);
        area.addPABlock(forwardBlock);
        area.addPABlock(leftBlock);
        area.addPABlock(wifBlock);
        area.handleMouseUp(new ProgramLocation(100,100),whileBlock);
        assertTrue(area.validExecutionState());
    }

    @Test
    public void handleMouseUpWhileWithCavity(){
        ModelProgramArea area = new ModelProgramArea();
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new ProgramLocation(100,100), BlockType.WHILE);
        ModelActionBlock forwardBlock = new ModelActionBlock(new ProgramLocation(90,20), BlockType.ACTION, null);
        ModelActionBlock leftBlock = new ModelActionBlock(new ProgramLocation(113, 143), BlockType.ACTION, null);
        ModelPredicateBlock wifBlock = new ModelPredicateBlock(new ProgramLocation(180, 100), BlockType.PREDICATE, null);
        area.addPABlock(whileBlock);
        area.addPABlock(leftBlock);
        area.addPABlock(wifBlock);
        whileBlock.setRightSocket(wifBlock);
        wifBlock.setLeftPlug(whileBlock);
        whileBlock.setCavityPlug(leftBlock);
        leftBlock.setTopSocket(whileBlock);
        leftBlock.setBottomPlug(whileBlock);
        whileBlock.setCavitySocket(leftBlock);
        area.handleMouseUp(new ProgramLocation(90,20),forwardBlock);
        assertEquals(new ProgramLocation(90, 100), whileBlock.getPos());
    }

    @Test
    public void getFirstBlock(){
        ModelProgramArea area = new ModelProgramArea();
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new ProgramLocation(100,100), BlockType.WHILE);
        ModelActionBlock forwardBlock = new ModelActionBlock(new ProgramLocation(100,20), BlockType.ACTION, null);
        ModelActionBlock rightBlock = new ModelActionBlock(new ProgramLocation(113, 143), BlockType.ACTION, null);
        ModelActionBlock leftBlock = new ModelActionBlock(new ProgramLocation(100, 260), BlockType.ACTION, null);
        ModelPredicateBlock wifBlock = new ModelPredicateBlock(new ProgramLocation(180, 100), BlockType.PREDICATE, null);
        area.addPABlock(forwardBlock);
        area.addPABlock(leftBlock);
        area.addPABlock(wifBlock);
        area.addPABlock(whileBlock);
        area.addPABlock(rightBlock);
        forwardBlock.setBottomPlug(whileBlock);
        whileBlock.setTopSocket(forwardBlock);
        whileBlock.setCavityPlug(rightBlock);
        rightBlock.setTopSocket(whileBlock);
        rightBlock.setBottomPlug(whileBlock);
        whileBlock.setCavitySocket(rightBlock);
        whileBlock.setBottomPlug(leftBlock);
        leftBlock.setTopSocket(whileBlock);
        whileBlock.setRightSocket(wifBlock);
        wifBlock.setLeftPlug(whileBlock);
        assertEquals(forwardBlock, area.getFirstBlock());
    }

    @Test
    public void maxReached(){
        ModelProgramArea area = new ModelProgramArea();
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new ProgramLocation(100,100), BlockType.WHILE);
        ModelActionBlock forwardBlock = new ModelActionBlock(new ProgramLocation(100,20), BlockType.ACTION, null);
        ModelActionBlock rightBlock = new ModelActionBlock(new ProgramLocation(113, 143), BlockType.ACTION, null);
        ModelActionBlock leftBlock = new ModelActionBlock(new ProgramLocation(100, 260), BlockType.ACTION, null);
        ModelPredicateBlock wifBlock = new ModelPredicateBlock(new ProgramLocation(180, 100), BlockType.PREDICATE, null);
        ModelWhileIfBlock whileBlock2 = new ModelWhileIfBlock(new ProgramLocation(100,100), BlockType.WHILE);
        ModelActionBlock forwardBlock2 = new ModelActionBlock(new ProgramLocation(100,20), BlockType.ACTION, null);
        ModelActionBlock rightBlock2 = new ModelActionBlock(new ProgramLocation(113, 143), BlockType.ACTION, null);
        ModelActionBlock leftBlock2 = new ModelActionBlock(new ProgramLocation(100, 260), BlockType.ACTION, null);
        ModelPredicateBlock wifBlock2 = new ModelPredicateBlock(new ProgramLocation(180, 100), BlockType.PREDICATE, null);
        area.addPABlock(forwardBlock);
        area.addPABlock(leftBlock);
        area.addPABlock(wifBlock);
        area.addPABlock(whileBlock);
        area.addPABlock(rightBlock);
        area.addPABlock(forwardBlock2);
        area.addPABlock(leftBlock2);
        area.addPABlock(wifBlock2);
        area.addPABlock(whileBlock2);
        area.addPABlock(rightBlock2);
        assertTrue(area.maxReached());
    }

    @Test
    public void dragBlock(){
        ModelProgramArea area = new ModelProgramArea();
        ModelActionBlock forwardBlock = new ModelActionBlock(new ProgramLocation(100,20), BlockType.ACTION, null);
        area.addPABlock(forwardBlock);
        area.dragBlock(forwardBlock, new ProgramLocation(250, 300));
        assertEquals(new ProgramLocation(250, 300), forwardBlock.getPos());
    }

}
