package model;

import static org.junit.Assert.*;

import model.blocks.*;
import org.junit.Test;

import utilities.*;

public class ModelProgramAreaTest {

    @Test
    public void addPABlock(){
        ModelProgramArea area = new ModelProgramArea();
        ModelActionBlock forwardBlock = new ModelActionBlock(new ProgramLocation(100,100), null);
        ModelActionBlock leftBlock = new ModelActionBlock(new ProgramLocation(400, 550), null);
        ModelActionBlock rightBlock = new ModelActionBlock(new ProgramLocation(400, 700), null);
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
        ModelActionBlock forwardBlock = new ModelActionBlock(new ProgramLocation(400,400), null);
        ModelActionBlock leftBlock = new ModelActionBlock(new ProgramLocation(400, 480), null);
        ModelActionBlock rightBlock = new ModelActionBlock(new ProgramLocation(400, 560), null);
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
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new ProgramLocation(400,400), false);
        ModelActionBlock leftBlock = new ModelActionBlock(new ProgramLocation(413, 443), null);
        ModelActionBlock rightBlock = new ModelActionBlock(new ProgramLocation(413, 523), null);
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
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new ProgramLocation(400,400), false);
        ModelActionBlock leftBlock = new ModelActionBlock(new ProgramLocation(413, 443), null);
        ModelActionBlock rightBlock = new ModelActionBlock(new ProgramLocation(413, 523), null);
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
        ModelActionBlock forwardBlock = new ModelActionBlock(new ProgramLocation(100,100), null);
        area.addPABlock(forwardBlock);
        ModelActionBlock leftBlock = new ModelActionBlock(new ProgramLocation(100, 100), null);
        area.addPABlock(leftBlock);
        assertEquals(leftBlock,area.selectBlock(new ProgramLocation(140,140)));
    }

    @Test
    public void handleMouseUp(){
        ModelProgramArea area = new ModelProgramArea();
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new ProgramLocation(100,100), false);
        ModelActionBlock forwardBlock = new ModelActionBlock(new ProgramLocation(100,20), null);
        ModelActionBlock leftBlock = new ModelActionBlock(new ProgramLocation(100, 180), null);
        ModelPredicateBlock wifBlock = new ModelPredicateBlock(new ProgramLocation(180, 100), null);
        area.addPABlock(forwardBlock);
        area.addPABlock(leftBlock);
        area.addPABlock(wifBlock);
        area.addAndConnectBlock(whileBlock, area.findClosestBlock(new ProgramLocation(100, 100), whileBlock));
        assertTrue(area.validExecutionState());
    }

    @Test
    public void handleMouseUpWhileWithCavity(){
        ModelProgramArea area = new ModelProgramArea();
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new ProgramLocation(100,100), false);
        ModelActionBlock forwardBlock = new ModelActionBlock(new ProgramLocation(90,20), null);
        ModelActionBlock leftBlock = new ModelActionBlock(new ProgramLocation(113, 143), null);
        ModelPredicateBlock wifBlock = new ModelPredicateBlock(new ProgramLocation(180, 100), null);
        area.addPABlock(whileBlock);
        area.addPABlock(leftBlock);
        area.addPABlock(wifBlock);
        whileBlock.setRightSocket(wifBlock);
        wifBlock.setLeftPlug(whileBlock);
        whileBlock.setCavityPlug(leftBlock);
        leftBlock.setTopSocket(whileBlock);
        leftBlock.setBottomPlug(whileBlock);
        whileBlock.setCavitySocket(leftBlock);
        area.addAndConnectBlock(forwardBlock, area.findClosestBlock(new ProgramLocation(90, 20), forwardBlock));
        assertEquals(new ProgramLocation(90, 100), whileBlock.getPos());
    }

    @Test
    public void getFirstBlock(){
        ModelProgramArea area = new ModelProgramArea();
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new ProgramLocation(100,100), false);
        ModelActionBlock forwardBlock = new ModelActionBlock(new ProgramLocation(100,20), null);
        ModelActionBlock rightBlock = new ModelActionBlock(new ProgramLocation(113, 143), null);
        ModelActionBlock leftBlock = new ModelActionBlock(new ProgramLocation(100, 260), null);
        ModelPredicateBlock wifBlock = new ModelPredicateBlock(new ProgramLocation(180, 100), null);
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
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new ProgramLocation(100,100), false);
        ModelActionBlock forwardBlock = new ModelActionBlock(new ProgramLocation(100,20), null);
        ModelActionBlock rightBlock = new ModelActionBlock(new ProgramLocation(113, 143), null);
        ModelActionBlock leftBlock = new ModelActionBlock(new ProgramLocation(100, 260), null);
        ModelPredicateBlock wifBlock = new ModelPredicateBlock(new ProgramLocation(180, 100), null);
        ModelWhileIfBlock whileBlock2 = new ModelWhileIfBlock(new ProgramLocation(100,100), false);
        ModelActionBlock forwardBlock2 = new ModelActionBlock(new ProgramLocation(100,20), null);
        ModelActionBlock rightBlock2 = new ModelActionBlock(new ProgramLocation(113, 143), null);
        ModelActionBlock leftBlock2 = new ModelActionBlock(new ProgramLocation(100, 260), null);
        ModelPredicateBlock wifBlock2 = new ModelPredicateBlock(new ProgramLocation(180, 100), null);
        ModelWhileIfBlock whileBlock3 = new ModelWhileIfBlock(new ProgramLocation(100,100), false);
        ModelActionBlock forwardBlock3 = new ModelActionBlock(new ProgramLocation(100,20), null);
        ModelActionBlock rightBlock3 = new ModelActionBlock(new ProgramLocation(113, 143), null);
        ModelActionBlock leftBlock3 = new ModelActionBlock(new ProgramLocation(100, 260), null);
        ModelPredicateBlock wifBlock3 = new ModelPredicateBlock(new ProgramLocation(180, 100), null);
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
        area.addPABlock(forwardBlock3);
        area.addPABlock(leftBlock3);
        area.addPABlock(wifBlock3);
        area.addPABlock(whileBlock3);
        area.addPABlock(rightBlock3);
        assertTrue(area.maxReached());
    }

    @Test
    public void dragBlock(){
        ModelProgramArea area = new ModelProgramArea();
        ModelActionBlock forwardBlock = new ModelActionBlock(new ProgramLocation(100,20), null);
        area.addPABlock(forwardBlock);
        area.dragBlock(forwardBlock, new ProgramLocation(250, 300));
        assertEquals(new ProgramLocation(250, 300), forwardBlock.getPos());
    }
}
