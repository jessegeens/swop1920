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
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new WindowLocation(100,100), new Blocktype(Blocktype.MOVEFORWARD));
        ModelMoveBlock leftBlock = new ModelMoveBlock(new WindowLocation(400, 550), new Blocktype(Blocktype.TURNLEFT));
        ModelMoveBlock rightBlock = new ModelMoveBlock(new WindowLocation(400, 700), new Blocktype(Blocktype.TURNRIGHT));
        area.addBlock(forwardBlock);
        area.addBlock(leftBlock);
        area.addBlock(rightBlock);
        forwardBlock.setBottomPlug(leftBlock);
        leftBlock.setTopSocket(forwardBlock);
        assertEquals(2, area.getConnectedBlocks(forwardBlock).size());
    }
}
