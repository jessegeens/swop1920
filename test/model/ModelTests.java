package model;

import model.blocks.ModelMoveBlock;
import model.blocks.ModelNotBlock;
import model.blocks.ModelWhileIfBlock;
import org.junit.Test;
import utilities.Blocktype;
import utilities.Direction;
import utilities.Location;

import java.util.ArrayList;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.*;

public class ModelTests {

    @Test
    public void TestModelGrid() {
        Location loc = new Location(40, 40);
        Location rPos = new Location(0, 0);
        Direction rDir = new Direction(0);
        ArrayList<Location> walls = new ArrayList<>();
        ModelGrid grid = new ModelGrid(200, 200, loc, rPos, rDir, walls);
        assertEquals(loc, grid.getGoalCell());
        Location wall1 = new Location(12, 15);
        Location wall2 = new Location(22, 24);
        ArrayList<Location> lst = new ArrayList<>();
        lst.add(wall1);
        lst.add(wall2);
        grid.setWalls(lst);
        assertNotNull(lst);
        assertEquals(22, lst.get(1).getX());
        assertEquals(15, grid.getWalls().get(0).getY());
    }

    @Test
    public void testModelPalette() {
        ModelPalette mP = new ModelPalette(200, 200);
        Location loc = new Location(10, 10);
        Blocktype bt = new Blocktype(Blocktype.MOVEFORWARD);
        ModelMoveBlock blk = new ModelMoveBlock(loc, bt);
        mP.blockToProgramWindow(blk, false);
        mP.blockToProgramWindow(blk, true);
        assertNull(mP.getForwardBlock());
    }

    @Test
    public void testModelPalette2() {
        ModelPalette mP = new ModelPalette(200, 200);
        assertNotNull(mP.handleMouseDown(mP.getForwardLocation().add(5,5), false));
    }

    @Test
    public void programWindowTest() {
        ModelProgramWindow pw = new ModelProgramWindow(200, 200);
        Location pos1 = new Location(180, 10);
        Blocktype type1 = new Blocktype(Blocktype.WHILE);
        ModelWhileIfBlock block1 = new ModelWhileIfBlock(pos1, type1);
        Location pos2 = new Location(180, 130);
        Blocktype type2 = new Blocktype(Blocktype.WHILE);
        ModelWhileIfBlock block2  = new ModelWhileIfBlock(pos2, type2);
        block1.connect(block2);
        Location pos3 = new Location(20,20);
        Blocktype type3 = new Blocktype(Blocktype.NOT);
        ModelNotBlock block3 = new ModelNotBlock(pos3, type3);
        pw.addBlock(block1);
        pw.addBlock(block2);
        pw.addBlock(block3);
        assertFalse(pw.allBlocksConnected());
        assertEquals(130, pw.getFinishBlock().getPos().getY());
        pw.removeBlock(block3);
        assertEquals(2, pw.getPABlocks().size());
    }

    @Test
    public void testModelRobot() {
        Location pos = new Location(2,2);
        Direction dir = new Direction(Direction.RIGHT);
        ModelRobot rb = new ModelRobot(pos, dir);
        rb.turnLeft();
        rb.turnRight();
        assertNotNull(rb.getDirection());
    }
}
