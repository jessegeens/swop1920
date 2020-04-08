package model;

import static org.junit.Assert.*;

import model.blocks.*;
import org.junit.Test;

import utilities.*;

public class ModelProgramAreaTest {

    @Test
    public void addPABlock(){
        ModelProgramArea area = new ModelProgramArea(1000,1000);
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(100,100), BlockType.MOVEFORWARD);
        ModelMoveBlock leftBlock = new ModelMoveBlock(new Location(400, 550), BlockType.TURNLEFT);
        ModelMoveBlock rightBlock = new ModelMoveBlock(new Location(400, 700), BlockType.TURNRIGHT);
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
        ModelProgramArea area = new ModelProgramArea(1000,1000);
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(400,400), BlockType.MOVEFORWARD);
        ModelMoveBlock leftBlock = new ModelMoveBlock(new Location(400, 480), BlockType.TURNLEFT);
        ModelMoveBlock rightBlock = new ModelMoveBlock(new Location(400, 560), BlockType.TURNRIGHT);
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
        ModelProgramArea area = new ModelProgramArea(1000,1000);
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new Location(400,400), BlockType.WHILE);
        ModelMoveBlock leftBlock = new ModelMoveBlock(new Location(413, 443), BlockType.TURNLEFT);
        ModelMoveBlock rightBlock = new ModelMoveBlock(new Location(413, 523), BlockType.TURNRIGHT);
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
        ModelProgramArea area = new ModelProgramArea(1000,1000);
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new Location(400,400), BlockType.WHILE);
        ModelMoveBlock leftBlock = new ModelMoveBlock(new Location(413, 443), BlockType.TURNLEFT);
        ModelMoveBlock rightBlock = new ModelMoveBlock(new Location(413, 523), BlockType.TURNRIGHT);
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
        ModelProgramArea area = new ModelProgramArea(1000,1000);
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(100,100), BlockType.MOVEFORWARD);
        area.addPABlock(forwardBlock);
        ModelMoveBlock leftBlock = new ModelMoveBlock(new Location(100, 100), BlockType.TURNLEFT);
        area.addPABlock(leftBlock);
        assertEquals(leftBlock,area.handleMouseDown(new Location(140,140)));
    }

    @Test
    public void handleMouseUp(){
        ModelProgramArea area = new ModelProgramArea(1000,1000);
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new Location(100,100), BlockType.WHILE);
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(100,20), BlockType.MOVEFORWARD);
        ModelMoveBlock leftBlock = new ModelMoveBlock(new Location(100, 180), BlockType.TURNLEFT);
        ModelWallInFrontBlock wifBlock = new ModelWallInFrontBlock(new Location(180, 100), BlockType.WALLINFRONT);
        area.addPABlock(forwardBlock);
        area.addPABlock(leftBlock);
        area.addPABlock(wifBlock);
        area.handleMouseUp(new Location(100,100),whileBlock);
        assertTrue(area.validExecutionState());
    }

}
