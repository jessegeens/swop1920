package model;

import static org.junit.Assert.*;

import model.blocks.*;
import org.junit.Test;

import utilities.*;

public class ModelProgramAreaTest {
    /*

    @Test
    public void addBlock(){
        ModelProgramArea area = new ModelProgramArea(1000,1000);
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(100,100), BlockType.MOVEFORWARD);
        ModelMoveBlock leftBlock = new ModelMoveBlock(new Location(400, 550), BlockType.TURNLEFT);
        ModelMoveBlock rightBlock = new ModelMoveBlock(new Location(400, 700), BlockType.TURNRIGHT);
        forwardBlock.setBottomPlug(leftBlock);
        leftBlock.setTopSocket(forwardBlock);
        leftBlock.setBottomPlug(rightBlock);
        rightBlock.setTopSocket(leftBlock);
        area.addBlock(forwardBlock);
        area.addBlock(leftBlock);
        area.addBlock(rightBlock);
        assertEquals(3, area.getPABlocks().size());
    }

    @Test
    public void updateLocationBlocks(){
        ModelProgramArea area = new ModelProgramArea(1000,1000);
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(100,100), BlockType.MOVEFORWARD);
        ModelMoveBlock leftBlock = new ModelMoveBlock(new Location(400, 550), BlockType.TURNLEFT);
        ModelMoveBlock rightBlock = new ModelMoveBlock(new Location(400, 700), BlockType.TURNRIGHT);
        forwardBlock.setBottomPlug(leftBlock);
        leftBlock.setTopSocket(forwardBlock);
        leftBlock.setBottomPlug(rightBlock);
        rightBlock.setTopSocket(leftBlock);
        area.addBlock(forwardBlock);
        area.addBlock(leftBlock);
        area.addBlock(rightBlock);
        area.updateLocationBlocks();
        assertEquals(new Location(100, 260), rightBlock.getPos());
    }

    @Test
    public void getConnectedBlocks(){
        ModelProgramArea area = new ModelProgramArea(1000,1000);
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new Location(100,100), BlockType.WHILE);
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(400,400), BlockType.MOVEFORWARD);
        ModelMoveBlock leftBlock = new ModelMoveBlock(new Location(400, 550), BlockType.TURNLEFT);
        ModelMoveBlock rightBlock = new ModelMoveBlock(new Location(400, 700), BlockType.TURNRIGHT);
        ModelWallInFrontBlock wallBlock = new ModelWallInFrontBlock(new Location(400, 850), BlockType.WALLINFRONT);
        area.addBlock(forwardBlock);
        area.addBlock(leftBlock);
        area.addBlock(rightBlock);
        area.addBlock(whileBlock);
        area.addBlock(wallBlock);
        forwardBlock.setBottomPlugPos(whileBlock.getTopSocketPos());
        forwardBlock.connect(whileBlock);
        rightBlock.setBottomPlugPos(whileBlock.getCavitySocketPos());
        rightBlock.connect(whileBlock);
        leftBlock.setTopSocketPos(whileBlock.getBottomPlugPos());
        leftBlock.connect(whileBlock);
        wallBlock.setLeftPlugPos(whileBlock.getRightSocketPos());
        wallBlock.connect(whileBlock);
        assertEquals(5, area.getConnectedBlocks(whileBlock).size());
    }

    @Test
    public void allBlocksConnectedSingle(){
        ModelProgramArea area = new ModelProgramArea(1000,1000);
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(400,400), BlockType.MOVEFORWARD);
        area.addBlock(forwardBlock);
        assertTrue(area.allBlocksConnected());
    }

    @Test
    public void allBlocksConnectedMultiplePositive(){
        ModelProgramArea area = new ModelProgramArea(1000,1000);
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(100,100), BlockType.MOVEFORWARD);
        ModelMoveBlock leftBlock = new ModelMoveBlock(new Location(400, 550), BlockType.TURNLEFT);
        ModelMoveBlock rightBlock = new ModelMoveBlock(new Location(400, 700), BlockType.TURNRIGHT);
        forwardBlock.setBottomPlug(leftBlock);
        leftBlock.setTopSocket(forwardBlock);
        leftBlock.setBottomPlug(rightBlock);
        rightBlock.setTopSocket(leftBlock);
        area.addBlock(forwardBlock);
        area.addBlock(leftBlock);
        area.addBlock(rightBlock);
        assertTrue(area.allBlocksConnected());
    }

    @Test
    public void allBlocksConnectedMultipleNegative(){
        ModelProgramArea area = new ModelProgramArea(1000,1000);
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(100,100), BlockType.MOVEFORWARD);
        ModelMoveBlock leftBlock = new ModelMoveBlock(new Location(400, 550), BlockType.TURNLEFT);
        ModelMoveBlock rightBlock = new ModelMoveBlock(new Location(400, 700), BlockType.TURNRIGHT);
        forwardBlock.setBottomPlug(leftBlock);
        leftBlock.setTopSocket(forwardBlock);
        area.addBlock(forwardBlock);
        area.addBlock(leftBlock);
        area.addBlock(rightBlock);
        assertFalse(area.allBlocksConnected());
    }

    @Test
    public void updateConnections(){
        ModelProgramArea area = new ModelProgramArea(1000,1000);
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new Location(100,100), BlockType.WHILE);
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(400,400), BlockType.MOVEFORWARD);
        ModelMoveBlock leftBlock = new ModelMoveBlock(new Location(400, 550), BlockType.TURNLEFT);
        ModelMoveBlock rightBlock = new ModelMoveBlock(new Location(400, 700), BlockType.TURNRIGHT);
        ModelWallInFrontBlock wallBlock = new ModelWallInFrontBlock(new Location(400, 850), BlockType.WALLINFRONT);
        area.addBlock(forwardBlock);
        area.addBlock(leftBlock);
        area.addBlock(rightBlock);
        area.addBlock(whileBlock);
        area.addBlock(wallBlock);
        forwardBlock.setBottomPlugPos(whileBlock.getTopSocketPos());
        rightBlock.setBottomPlugPos(whileBlock.getCavitySocketPos());
        leftBlock.setTopSocketPos(whileBlock.getBottomPlugPos());
        wallBlock.setLeftPlugPos(whileBlock.getRightSocketPos());
        area.updateConnections();
        assertTrue(area.allBlocksConnected());
    }

    @Test
    public void updateConnectionsInfiniteLoop(){
        ModelProgramArea area = new ModelProgramArea(1000,1000);
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new Location(100,100), BlockType.WHILE);
        area.addBlock(whileBlock);
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(400,400), BlockType.MOVEFORWARD);
        area.addBlock(forwardBlock);
        ModelMoveBlock leftBlock = new ModelMoveBlock(new Location(400, 550), BlockType.TURNLEFT);
        area.addBlock(leftBlock);
        forwardBlock.setTopSocketPos(whileBlock.getCavityPlugPos());
        forwardBlock.connect(whileBlock);
        leftBlock.setTopSocketPos(forwardBlock.getBottomPlugPos());
        leftBlock.connect(forwardBlock);
        area.updateConnections();
        assertTrue(true);
    }

    @Test
    public void getStartBlocks(){
        ModelProgramArea area = new ModelProgramArea(1000,1000);
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(400,400), BlockType.MOVEFORWARD);
        ModelMoveBlock leftBlock = new ModelMoveBlock(new Location(400, 550), BlockType.TURNLEFT);
        ModelMoveBlock rightBlock = new ModelMoveBlock(new Location(400, 700), BlockType.TURNRIGHT);
        ModelWallInFrontBlock wallBlock = new ModelWallInFrontBlock(new Location(400, 850), BlockType.WALLINFRONT);
        area.addBlock(forwardBlock);
        area.addBlock(leftBlock);
        area.addBlock(rightBlock);
        area.addBlock(wallBlock);
        forwardBlock.setBottomPlugPos(leftBlock.getTopSocketPos());
        forwardBlock.connect(leftBlock);
        assertEquals(2, area.getStartBlocks().size());
    }

    @Test
    public void getFinishBlocks(){
        ModelProgramArea area = new ModelProgramArea(1000,1000);
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(400,400), BlockType.MOVEFORWARD);
        ModelMoveBlock leftBlock = new ModelMoveBlock(new Location(400, 550), BlockType.TURNLEFT);
        ModelMoveBlock rightBlock = new ModelMoveBlock(new Location(400, 700), BlockType.TURNRIGHT);
        ModelWallInFrontBlock wallBlock = new ModelWallInFrontBlock(new Location(400, 850), BlockType.WALLINFRONT);
        area.addBlock(forwardBlock);
        area.addBlock(leftBlock);
        area.addBlock(rightBlock);
        area.addBlock(wallBlock);
        forwardBlock.setBottomPlugPos(leftBlock.getTopSocketPos());
        forwardBlock.connect(leftBlock);
        assertEquals(2, area.getFinishBlocks().size());
    }

    @Test
    public void removeBlock(){
        ModelProgramArea area = new ModelProgramArea(1000,1000);
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(400,400), BlockType.MOVEFORWARD);
        ModelMoveBlock leftBlock = new ModelMoveBlock(new Location(400, 550), BlockType.TURNLEFT);
        ModelMoveBlock rightBlock = new ModelMoveBlock(new Location(400, 700), BlockType.TURNRIGHT);
        area.addBlock(forwardBlock);
        area.addBlock(leftBlock);
        area.addBlock(rightBlock);
        forwardBlock.setBottomPlugPos(leftBlock.getTopSocketPos());
        forwardBlock.connect(leftBlock);
        rightBlock.setTopSocketPos(leftBlock.getBottomPlugPos());
        rightBlock.connect(leftBlock);
        area.removeBlock(leftBlock);
        assertEquals(0,leftBlock.getConnections().size());
    }

    @Test
    public void removeWhileBlockCavity(){
        ModelProgramArea area = new ModelProgramArea(1000,1000);
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new Location(400,400), BlockType.WHILE);
        ModelMoveBlock leftBlock = new ModelMoveBlock(new Location(400, 550), BlockType.TURNLEFT);
        ModelMoveBlock rightBlock = new ModelMoveBlock(new Location(400, 700), BlockType.TURNRIGHT);
        area.addBlock(whileBlock);
        area.addBlock(leftBlock);
        area.addBlock(rightBlock);
        leftBlock.setBottomPlugPos(whileBlock.getCavitySocketPos());
        leftBlock.connect(whileBlock);
        rightBlock.setTopSocketPos(leftBlock.getBottomPlugPos());
        rightBlock.connect(leftBlock);
        area.removeBlock(whileBlock);
        assertEquals(0,area.getPABlocks().size());
    }

    @Test
    public void addWhileBlockCavity(){
        ModelProgramArea area = new ModelProgramArea(1000,1000);
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new Location(400,400), BlockType.WHILE);
        ModelMoveBlock leftBlock = new ModelMoveBlock(new Location(400, 550), BlockType.TURNLEFT);
        ModelMoveBlock rightBlock = new ModelMoveBlock(new Location(400, 700), BlockType.TURNRIGHT);
        leftBlock.setBottomPlugPos(whileBlock.getCavitySocketPos());
        leftBlock.connect(whileBlock);
        rightBlock.setTopSocketPos(leftBlock.getBottomPlugPos());
        rightBlock.connect(leftBlock);
        area.addBlock(whileBlock);
        assertEquals(3,area.getPABlocks().size());
    }

    @Test
    public void findClosestTopSocket(){
        ModelProgramArea area = new ModelProgramArea(1000,1000);
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(400,400), BlockType.MOVEFORWARD);
        ModelMoveBlock leftBlock = new ModelMoveBlock(new Location(400, 550), BlockType.TURNLEFT);
        area.addBlock(forwardBlock);
        area.addBlock(leftBlock);
        forwardBlock.setBottomPlugPos(leftBlock.getTopSocketPos());
        assertEquals(forwardBlock, area.findClosestBlock(leftBlock));
    }

    @Test
    public void findClosestBottomPlug(){
        ModelProgramArea area = new ModelProgramArea(1000,1000);
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(400,400), BlockType.MOVEFORWARD);
        ModelMoveBlock leftBlock = new ModelMoveBlock(new Location(400, 550), BlockType.TURNLEFT);
        area.addBlock(forwardBlock);
        area.addBlock(leftBlock);
        forwardBlock.setBottomPlugPos(leftBlock.getTopSocketPos());
        assertEquals(leftBlock, area.findClosestBlock(forwardBlock));
    }

    @Test
    public void handleMouseDown(){
        ModelProgramArea area = new ModelProgramArea(1000,1000);
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(100,100), BlockType.MOVEFORWARD);
        area.addBlock(forwardBlock);
        ModelMoveBlock leftBlock = new ModelMoveBlock(new Location(100, 100), BlockType.TURNLEFT);
        area.addBlock(leftBlock);
        assertEquals(leftBlock,area.handleMouseDown(new Location(140,140)));
    }

    @Test
    public void handleMouseUp(){
        ModelProgramArea area = new ModelProgramArea(1000,1000);
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new Location(100,100), BlockType.WHILE);
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(400,400), BlockType.MOVEFORWARD);
        ModelMoveBlock leftBlock = new ModelMoveBlock(new Location(400, 550), BlockType.TURNLEFT);
        ModelWallInFrontBlock wallBlock = new ModelWallInFrontBlock(new Location(400, 850), BlockType.WALLINFRONT);
        area.addBlock(forwardBlock);
        area.addBlock(leftBlock);
        area.addBlock(wallBlock);
        forwardBlock.setBottomPlugPos(whileBlock.getTopSocketPos());
        leftBlock.setTopSocketPos(whileBlock.getBottomPlugPos());
        wallBlock.setLeftPlugPos(whileBlock.getRightSocketPos());
        area.handleMouseUp(new Location(100,100),whileBlock);
        assertTrue(area.allBlocksConnected());
    }

     */

}
