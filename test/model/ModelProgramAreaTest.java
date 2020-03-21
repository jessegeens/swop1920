package model;

import static org.junit.Assert.*;

import model.blocks.*;
import org.junit.Test;

import utilities.*;

public class ModelProgramAreaTest {

    @Test
    public void addBlock(){
        ModelProgramArea area = new ModelProgramArea(1000,1000);
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new WindowLocation(100,100), new Blocktype(Blocktype.MOVEFORWARD));
        ModelMoveBlock leftBlock = new ModelMoveBlock(new WindowLocation(400, 550), new Blocktype(Blocktype.TURNLEFT));
        ModelMoveBlock rightBlock = new ModelMoveBlock(new WindowLocation(400, 700), new Blocktype(Blocktype.TURNRIGHT));
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
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new WindowLocation(100,100), new Blocktype(Blocktype.MOVEFORWARD));
        ModelMoveBlock leftBlock = new ModelMoveBlock(new WindowLocation(400, 550), new Blocktype(Blocktype.TURNLEFT));
        ModelMoveBlock rightBlock = new ModelMoveBlock(new WindowLocation(400, 700), new Blocktype(Blocktype.TURNRIGHT));
        forwardBlock.setBottomPlug(leftBlock);
        leftBlock.setTopSocket(forwardBlock);
        leftBlock.setBottomPlug(rightBlock);
        rightBlock.setTopSocket(leftBlock);
        area.addBlock(forwardBlock);
        area.addBlock(leftBlock);
        area.addBlock(rightBlock);
        area.updateLocationBlocks();
        assertEquals(new WindowLocation(100, 260), rightBlock.getPos());
    }

    @Test
    public void getConnectedBlocks(){
        ModelProgramArea area = new ModelProgramArea(1000,1000);
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new WindowLocation(100,100),new Blocktype(Blocktype.WHILE));
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new WindowLocation(400,400), new Blocktype(Blocktype.MOVEFORWARD));
        ModelMoveBlock leftBlock = new ModelMoveBlock(new WindowLocation(400, 550), new Blocktype(Blocktype.TURNLEFT));
        ModelMoveBlock rightBlock = new ModelMoveBlock(new WindowLocation(400, 700), new Blocktype(Blocktype.TURNRIGHT));
        ModelWallInFrontBlock wallBlock = new ModelWallInFrontBlock(new WindowLocation(400, 850), new Blocktype(Blocktype.WALLINFRONT));
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
    public void allBlocksConnectedPositive(){
        ModelProgramArea area = new ModelProgramArea(1000,1000);
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new WindowLocation(100,100), new Blocktype(Blocktype.MOVEFORWARD));
        ModelMoveBlock leftBlock = new ModelMoveBlock(new WindowLocation(400, 550), new Blocktype(Blocktype.TURNLEFT));
        ModelMoveBlock rightBlock = new ModelMoveBlock(new WindowLocation(400, 700), new Blocktype(Blocktype.TURNRIGHT));
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
    public void allBlocksConnectedNegative(){
        ModelProgramArea area = new ModelProgramArea(1000,1000);
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new WindowLocation(100,100), new Blocktype(Blocktype.MOVEFORWARD));
        ModelMoveBlock leftBlock = new ModelMoveBlock(new WindowLocation(400, 550), new Blocktype(Blocktype.TURNLEFT));
        ModelMoveBlock rightBlock = new ModelMoveBlock(new WindowLocation(400, 700), new Blocktype(Blocktype.TURNRIGHT));
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
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new WindowLocation(100,100),new Blocktype(Blocktype.WHILE));
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new WindowLocation(400,400), new Blocktype(Blocktype.MOVEFORWARD));
        ModelMoveBlock leftBlock = new ModelMoveBlock(new WindowLocation(400, 550), new Blocktype(Blocktype.TURNLEFT));
        ModelMoveBlock rightBlock = new ModelMoveBlock(new WindowLocation(400, 700), new Blocktype(Blocktype.TURNRIGHT));
        ModelWallInFrontBlock wallBlock = new ModelWallInFrontBlock(new WindowLocation(400, 850), new Blocktype(Blocktype.WALLINFRONT));
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


}
