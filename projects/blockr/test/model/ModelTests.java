package model;


import static junit.framework.Assert.assertNotNull;

public class ModelTests {
    /*

    @Test
    public void TestModelGrid() {
        GridLocation goal = new GridLocation(40, 40);
        GridLocation rPos = new GridLocation(0, 0);
        Direction rDir = Direction.RIGHT;
        ArrayList<GridLocation> walls = new ArrayList<>();
        ProgramState state = new ProgramState(rDir, rPos, walls, goal, 100);
        ModelGrid grid = new ModelGrid(200, 200, state);
        assertEquals(goal, state.getGoalCell());
        GridLocation wall1 = new GridLocation(12, 15);
        GridLocation wall2 = new GridLocation(22, 24);
        ArrayList<GridLocation> lst = new ArrayList<>();
        lst.add(wall1);
        lst.add(wall2);
        //state.setWalls(lst);
        //states can't be set, a new state has to be created.
        assertNotNull(lst);
        assertEquals(22, lst.get(1).getX());
        assertEquals(15, state.getWalls().get(0).getY());
    }

    @Test
    public void testModelPalette() {
        ModelPalette mP = new ModelPalette(200, 200);
        Location loc = new Location(10, 10);
        BlockType bt = BlockType.MOVEFORWARD;
        ModelMoveBlock blk = new ModelMoveBlock(loc, bt);
        mP.blockToProgramArea(blk, false);
        mP.blockToProgramArea(blk, true);
        assertNull(mP.getForwardBlock());
    }

    @Test
    public void testModelPalette2() {
        ModelPalette mP = new ModelPalette(200, 200);
        assertNotNull(mP.selectBlock(mP.getForwardWindowLocation().add(5,5), false));
    }

    @Test
    public void programWindowTest() {
        ModelProgramArea pw = new ModelProgramArea(200, 200);
        Location pos1 = new Location(180, 10);
        BlockType type1 = BlockType.WHILE;
        ModelWhileIfBlock block1 = new ModelWhileIfBlock(pos1, type1);
        Location pos2 = new Location(180, 130);
        BlockType type2 = BlockType.WHILE;
        ModelWhileIfBlock block2  = new ModelWhileIfBlock(pos2, type2);
        block1.connect(block2);
        Location pos3 = new Location(20,20);
        BlockType type3 = BlockType.NOT;
        ModelNotBlock block3 = new ModelNotBlock(pos3, type3);
        pw.addBlock(block1);
        pw.addBlock(block2);
        pw.addBlock(block3);
        assertFalse(pw.allBlocksConnected());
        assertEquals(130, pw.getFinishBlocks().get(0).getPos().getY());
        pw.removeBlock(block3);
        assertEquals(2, pw.getPABlocks().size());
    }

     */
}
