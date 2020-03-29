package main;

import model.ModelGrid;
import model.ProgramState;
import model.blocks.*;
import org.junit.Test;
import utilities.*;

import static org.junit.Assert.*;

import java.util.ArrayList;

public class GlobalControllerTests {

    @Test
    public void globalControllerTest() {
        GlobalController gC = new GlobalController();
        ArrayList<ModelBlock> mBlocks = new ArrayList<>();
        WindowLocation pos1 = new WindowLocation(100, 100);
        BlockType t1 = BlockType.TURNLEFT;
        ModelMoveBlock mmb1 = new ModelMoveBlock(pos1, t1);
        WindowLocation pos2 = new WindowLocation(100, 220);
        BlockType t2 = BlockType.TURNLEFT;
        ModelMoveBlock mmb2 = new ModelMoveBlock(pos2, t2);
        WindowLocation pos3 = new WindowLocation(100, 340);
        BlockType t3 = BlockType.TURNLEFT;
        ModelMoveBlock mmb3 = new ModelMoveBlock(pos3, t3);
        mmb1.connect(mmb2);
        mmb2.connect(mmb3);
        mBlocks.add(mmb1);
        mBlocks.add(mmb2);
        mBlocks.add(mmb3);
        gC.getModelController().getPArea().setBlocks(mBlocks);
        //gC.execute();
        assertEquals(Direction.UP, gC.getModelController().getGrid().getGridState().getRobotDirection());
        //gC.execute();
        assertEquals(Direction.LEFT, gC.getModelController().getGrid().getGridState().getRobotDirection());
        //gC.execute();
        assertEquals(Direction.DOWN, gC.getModelController().getGrid().getGridState().getRobotDirection());
    }

    @Test
    public void globalControllerMoveForwardTest() {
        GlobalController gC = new GlobalController();
        ArrayList<ModelBlock> mBlocks = new ArrayList<>();
        WindowLocation pos1 = new WindowLocation(100, 100);
        BlockType t1 = BlockType.MOVEFORWARD;
        ModelMoveBlock mmb1 = new ModelMoveBlock(pos1, t1);
        WindowLocation pos2 = new WindowLocation(100, 200);
        BlockType t2 = BlockType.MOVEFORWARD;
        ModelMoveBlock mmb2 = new ModelMoveBlock(pos2, t2);
        mmb1.connect(mmb2);
        mBlocks.add(mmb1);
        mBlocks.add(mmb2);
        gC.getModelController().getPArea().setBlocks(mBlocks);
        Direction dir = Direction.RIGHT;
        //gC.getModelController().getGrid().setRobotDir(dir);
        GridLocation pos = new GridLocation(5, 5);
        //gC.getModelController().getGrid().setRobotPos(pos);
        //int a1 = gC.getModelController().getGrid().getRobotPos().getX();
        //int b1 = gC.getModelController().getGrid().getRobotPos().getY();
        ArrayList<Integer> list1 = new ArrayList<>();
        //list1.add(a1);
        //list1.add(b1);
        //System.out.println(a1);
        //System.out.println(b1);
        //gC.execute();
        //int a2 = gC.getModelController().getGrid().getRobotPos().getX();
        //int b2 = gC.getModelController().getGrid().getRobotPos().getY();
        ArrayList<Integer> list2 = new ArrayList<>();
        //list2.add(a2);
        //list2.add(b2);
        //System.out.println(a2);
        //System.out.println(b2);
        assertNotEquals(list1, list2);
    }

    @Test
    public void globalControllerTest2() {
        GlobalController gC = new GlobalController();
        ArrayList<ModelBlock> mBlocks = new ArrayList<>();
        WindowLocation pos1 = new WindowLocation(100, 100);
        BlockType t1 = BlockType.WHILE;
        ModelWhileIfBlock mwb = new ModelWhileIfBlock(pos1, t1);
        WindowLocation pos2 = new WindowLocation(220, 100);
        BlockType t2 = BlockType.NOT;
        ModelNotBlock mnb = new ModelNotBlock(pos2, t2);
        WindowLocation pos3 = new WindowLocation(340, 100);
        BlockType t3 = BlockType.WALLINFRONT;
        ModelWallInFrontBlock mwifb = new ModelWallInFrontBlock(pos3, t3);
        mwb.connect(mnb);
        mnb.connect(mwifb);
        mBlocks.add(mwb);
        mBlocks.add(mnb);
        mBlocks.add(mwifb);
        WindowLocation pos4 = new WindowLocation(160, 180);
        BlockType t4 = BlockType.MOVEFORWARD;
        ModelMoveBlock mmb = new ModelMoveBlock(pos4, t4);
        mwb.connect(mmb);
        mBlocks.add(mmb);
        GridLocation wall = new GridLocation(2,0);
        ArrayList<GridLocation> walls = new ArrayList<>();
        walls.add(wall);
        GridLocation goal = new GridLocation(4,4);
        GridLocation rPos = new GridLocation(0, 0);
        Direction rDir = Direction.RIGHT;
        ProgramState state = new ProgramState(rDir, rPos, walls, goal, 100);
        ModelGrid grid = new ModelGrid(5, 5, state);
        gC.getModelController().setGrid(grid);
        gC.getModelController().getPArea().setBlocks(mBlocks);
        //gC.execute();
        //assertEquals(1, gC.getModelController().getGrid().getRobotPos().getX());
    }
}
