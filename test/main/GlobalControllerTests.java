package main;

import model.ModelController;
import model.ModelGrid;
import model.blocks.*;
import org.junit.Test;
import ui.UIController;
import utilities.Blocktype;
import utilities.Direction;
import utilities.GridInfo;
import utilities.Location;
import static org.junit.Assert.*;

import java.util.ArrayList;

public class GlobalControllerTests {

    @Test
    public void globalControllerTest() {
        GlobalController gC = new GlobalController();
        ArrayList<ModelBlock> mBlocks = new ArrayList<>();
        Location pos1 = new Location(100, 100);
        Blocktype t1 = new Blocktype(Blocktype.TURNLEFT);
        ModelMoveBlock mmb1 = new ModelMoveBlock(pos1, t1);
        Location pos2 = new Location(100, 220);
        Blocktype t2 = new Blocktype(Blocktype.TURNLEFT);
        ModelMoveBlock mmb2 = new ModelMoveBlock(pos2, t2);
        Location pos3 = new Location(100, 340);
        Blocktype t3 = new Blocktype(Blocktype.TURNLEFT);
        ModelMoveBlock mmb3 = new ModelMoveBlock(pos3, t3);
        mmb1.connect(mmb2);
        mmb2.connect(mmb3);
        mBlocks.add(mmb1);
        mBlocks.add(mmb2);
        mBlocks.add(mmb3);
        gC.getModelController().getPWindow().setBlocks(mBlocks);
        gC.execute();
        assertEquals(Direction.UP, gC.getModelController().getGrid().getRobotDir().getDirection());
        gC.execute();
        assertEquals(Direction.LEFT, gC.getModelController().getGrid().getRobotDir().getDirection());
        gC.execute();
        assertEquals(Direction.DOWN, gC.getModelController().getGrid().getRobotDir().getDirection());
    }

    @Test
    public void globalControllerTest2() {
        GlobalController gC = new GlobalController();
        ArrayList<ModelBlock> mBlocks = new ArrayList<>();
        Location pos1 = new Location(100, 100);
        Blocktype t1 = new Blocktype(Blocktype.WHILE);
        ModelWhileIfBlock mwb = new ModelWhileIfBlock(pos1, t1);
        Location pos2 = new Location(220, 100);
        Blocktype t2 = new Blocktype(Blocktype.NOT);
        ModelNotBlock mnb = new ModelNotBlock(pos2, t2);
        Location pos3 = new Location(340, 100);
        Blocktype t3 = new Blocktype(Blocktype.WALLINFRONT);
        ModelWallInFrontBlock mwifb = new ModelWallInFrontBlock(pos3, t3);
        mwb.connect(mnb);
        mnb.connect(mwifb);
        mBlocks.add(mwb);
        mBlocks.add(mnb);
        mBlocks.add(mwifb);
        Location pos4 = new Location(160, 180);
        Blocktype t4 = new Blocktype(Blocktype.MOVEFORWARD);
        ModelMoveBlock mmb = new ModelMoveBlock(pos4, t4);
        mwb.connect(mmb);
        mBlocks.add(mmb);
        Location wall = new Location(2,0);
        ArrayList<Location> walls = new ArrayList<>();
        walls.add(wall);
        Location goal = new Location(4,4);
        Location rPos = new Location(0, 0);
        Direction rDir = new Direction(Direction.RIGHT);
        ModelGrid grid = new ModelGrid(5, 5, goal, rPos, rDir, walls);
        gC.getModelController().setGrid(grid);
        gC.getModelController().getPWindow().setBlocks(mBlocks);
        gC.execute();
        assertEquals(1, gC.getModelController().getGrid().getRobotPos().getX());
    }
}
