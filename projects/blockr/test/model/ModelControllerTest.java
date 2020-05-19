package model;

import static org.junit.Assert.*;

import gameworldapi.GameWorldType;
import model.blocks.ModelBlock;
import model.blocks.ModelNotBlock;
import model.blocks.ModelPredicateBlock;
import model.blocks.ModelWhileIfBlock;
import org.junit.Before;
import org.junit.Test;

import ui.UIBlock;
import utilities.*;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class ModelControllerTest {

    private GameWorldType GWT;

    @Before
    public void setUp() {
        try {
            File file = new File("/home/oberon/Documents/Studies/SWOP/swop1920/projects/robotgameworld/out/production/robotgameworld/");
            System.out.println(file.toString());
            //convert the file to URL format
            URL url = file.toURI().toURL();
            URL[] urls = new URL[]{url};
            //load this folder into Class loader
            ClassLoader cl = new URLClassLoader(urls);
            //load the Address class in 'c:\\other_classes\\'
            Class cls = cl.loadClass("robotgameworld.RobotGameWorldType");
            //GameWorldType worldType = ((GameWorldType) Class.forName(args[0]).newInstance());
            GWT = (GameWorldType) cls.newInstance();
        }
        catch (Exception ex){
            System.out.println("Error: " + ex.getMessage().toString());
        }
    }

    @Test
    public void getBlockStates(){
        ModelController MC = new ModelController(GWT);
        MC.select(new ProgramLocation (40, 40));
        MC.release(new ProgramLocation (420, 420));
        MC.select(new ProgramLocation(40, 200));
        MC.release(new ProgramLocation(420, 420 + UIBlock.STD_HEIGHT));
        assertEquals(10, MC.getBlockStates().size());
    }

    @Test
    public void startExecution() {
        ModelController controller = new ModelController(GWT);
        controller.select(new ProgramLocation(190, 150));
        controller.release(new ProgramLocation(410, 410));
        controller.select(new ProgramLocation(190, 270));
        controller.release(new ProgramLocation(410, 490));
        controller.startOrExecuteProgram();
        assertTrue(controller.getProgramAreaBlocks().get(0).isHighlighted());
    }

    @Test
    public void exitExecution() {
        ModelController controller = new ModelController(GWT);
        controller.select(new ProgramLocation(190, 150));
        controller.release(new ProgramLocation(410, 410));
        controller.select(new ProgramLocation(190, 270));
        controller.release(new ProgramLocation(410, 490));
        controller.startOrExecuteProgram();
        controller.exitExecution();
        assertFalse(controller.getProgramAreaBlocks().get(0).isHighlighted());
    }

    @Test
    public void handlePaletteSelect() {
        ModelController controller = new ModelController(GWT);
        controller.select(new ProgramLocation(40, 40));
        assertEquals(ModelWhileIfBlock.class, controller.getActiveBlock().getClass());
    }

    @Test
    public void handlePaletteRelease(){
        ModelController controller = new ModelController(GWT);
        controller.select(new ProgramLocation(40,40));
        controller.release(new ProgramLocation(120, 120));
        assertNull(controller.getActiveBlock());
    }

    @Test
    public void handlePaletteDrag(){
        ModelController controller = new ModelController(GWT);
        controller.select(new ProgramLocation(40,40));
        controller.drag(new ProgramLocation(120, 120));
        assertEquals(new ProgramLocation(120,120), controller.getActiveBlock().getPos());
    }

    @Test
    public void handleProgramAreaSelect() {
        ModelController controller = new ModelController(GWT);
        controller.select(new ProgramLocation(190, 150));
        controller.release(new ProgramLocation(410, 410));
        controller.select(new ProgramLocation(420, 420));
        assertEquals("MOVE_FORWARD", controller.getActiveBlock().getTitle());
    }

    @Test
    public void handleProgramAreaRelease(){
        ModelController controller = new ModelController(GWT);
        controller.select(new ProgramLocation(190, 150));
        controller.release(new ProgramLocation(410, 410));
        assertEquals(1, controller.getProgramAreaBlocks().size());
    }

    @Test
    public void handleProgramAreaDrag(){
        ModelController controller = new ModelController(GWT);
        controller.select(new ProgramLocation(40,40));
        controller.drag(new ProgramLocation(420, 420));
        assertEquals(new ProgramLocation(420,420), controller.getActiveBlock().getPos());
    }

    @Test
    public void wallInFrontBlockNullPointer(){
        ModelController controller = new ModelController(GWT);
        controller.select(new ProgramLocation(30, 390));
        controller.release(new ProgramLocation(420, 420));
        controller.select(new ProgramLocation(430, 430));
        assertEquals(ModelPredicateBlock.class, controller.getActiveBlock().getClass());
    }

    @Test
    public void getModelBlocksSize() {
        ModelController controller = new ModelController(GWT);
        assertEquals(8, controller.getModelBlocks().size());
    }

    @Test
    public void getGameWorld() {
        ModelController controller = new ModelController(GWT);
        assertNotNull(controller.getGameWorld());
    }

    @Test
    public void undoCreateTest() {
        ModelController controller = new ModelController(GWT);
        controller.select(new ProgramLocation(30, 270));
        controller.release(new ProgramLocation(420, 420));
        controller.undo();
        assertTrue(controller.getProgramAreaBlocks().isEmpty());
    }

    @Test
    public void undoMoveTest() {
        ModelController controller = new ModelController(GWT);
        controller.select(new ProgramLocation(30, 30));
        controller.release(new ProgramLocation(420, 420));
        controller.select(new ProgramLocation(440, 440));
        controller.release(new ProgramLocation(320, 600));
        controller.undo();
        assertEquals(new ProgramLocation(420, 420), controller.getProgramAreaBlocks().get(0).getPos());
    }

    @Test
    public void undoConnectTest() {
        ModelController controller = new ModelController(GWT);
        controller.select(new ProgramLocation(30, 30));
        controller.release(new ProgramLocation(420, 420));
        controller.select(new ProgramLocation(30, 30));
        controller.release(new ProgramLocation(420, 500));
        controller.undo();
        assertTrue(controller.getProgramAreaBlocks().get(0).getConnections().isEmpty());
    }

    @Test
    public void redoRightBlockTest() {
        ModelController controller = new ModelController(GWT);
        controller.select(new ProgramLocation(30, 30));
        controller.release(new ProgramLocation(420, 420));
        controller.select(new ProgramLocation(30, 30));
        controller.release(new ProgramLocation(420, 500));
        controller.select(new ProgramLocation(30, 30));
        controller.release(new ProgramLocation(420, 580));
        controller.undo();
        controller.undo();
        controller.undo();
        controller.redo();
        controller.redo();
        assertEquals(2, controller.getProgramAreaBlocks().size());
    }

    @Test
    public void redoDeleteTest() {
        ModelController controller = new ModelController(GWT);
        controller.select(new ProgramLocation(30, 30));
        controller.release(new ProgramLocation(420, 420));
        controller.select(new ProgramLocation(30, 30));
        controller.release(new ProgramLocation(420, 500));
        controller.select(new ProgramLocation(430, 430));
        controller.release(new ProgramLocation(30, 30));
        controller.undo();
        controller.redo();
        assertTrue(controller.getProgramAreaBlocks().get(0).getConnections().isEmpty());
    }

    @Test
    public void ifBlockInsideIfBlock() {
        ModelController controller = new ModelController(GWT);
        controller.select(new ProgramLocation(30, 30));
        controller.release(new ProgramLocation(420, 420));
        controller.select(new ProgramLocation(30, 30));
        controller.release(new ProgramLocation(443, 453));
        assertEquals(new ProgramLocation(433, 463), controller.getProgramAreaBlocks().get(1).getPos());
    }

    @Test
    public void moveWhileBlockWithConnectedBlocks() {
        ModelController controller = new ModelController(GWT);
        controller.select(new ProgramLocation(30, 150));
        controller.release(new ProgramLocation(320, 20));
        controller.select(new ProgramLocation(190, 30));
        controller.release(new ProgramLocation(400, 20));
        controller.select(new ProgramLocation(30, 390));
        controller.release(new ProgramLocation(480, 20));
        controller.select(new ProgramLocation(190, 150));
        controller.release(new ProgramLocation(333, 63));
        controller.select(new ProgramLocation(330, 30));
        controller.release(new ProgramLocation(320, 420));
        ModelNotBlock notBlock = new ModelNotBlock(new ProgramLocation(0, 0));
        for (ModelBlock block : controller.getProgramAreaBlocks()) {
            if (block instanceof ModelNotBlock) notBlock = (ModelNotBlock) block;
        }
        assertEquals(new ProgramLocation(400, 420), notBlock.getPos());
    }

    @Test
    public void redoRemoveBlockWithinCavityBlock() {
        ModelController controller = new ModelController(GWT);
        controller.select(new ProgramLocation(30, 30));
        controller.release(new ProgramLocation(420, 420));
        controller.select(new ProgramLocation(190, 150));
        controller.release(new ProgramLocation(433, 443));
        controller.undo();
        assertEquals(1, controller.getProgramAreaBlocks().size());
    }

    @Test
    public void undoTransitionExecutionBlockAction() {
        ModelController controller = new ModelController(GWT);
        controller.select(new ProgramLocation(190, 150));
        controller.release(new ProgramLocation(420, 420));
        controller.select(new ProgramLocation(190, 270));
        controller.release(new ProgramLocation(420, 500));
        controller.startOrExecuteProgram();
        controller.startOrExecuteProgram();
        controller.undo();
        controller.undo();
        controller.undo();
        assertEquals(1 , controller.getProgramAreaBlocks().size());
    }

    @Test
    public void redoTransitionExecutionBlockAction() {
        ModelController controller = new ModelController(GWT);
        controller.select(new ProgramLocation(190, 150));
        controller.release(new ProgramLocation(420, 420));
        controller.select(new ProgramLocation(190, 270));
        controller.release(new ProgramLocation(420, 500));
        controller.startOrExecuteProgram();
        controller.startOrExecuteProgram();
        controller.undo();
        controller.undo();
        controller.undo();
        controller.redo();
        controller.redo();
        assertTrue(controller.getProgramAreaBlocks().get(0).isHighlighted());
    }

    @Test
    public void executeAfterRedoTransitionExecutionBlockAction() {
        ModelController controller = new ModelController(GWT);
        controller.select(new ProgramLocation(190, 150));
        controller.release(new ProgramLocation(420, 420));
        controller.select(new ProgramLocation(190, 270));
        controller.release(new ProgramLocation(420, 500));
        controller.startOrExecuteProgram();
        controller.startOrExecuteProgram();
        controller.undo();
        controller.undo();
        controller.undo();
        controller.redo();
        controller.redo();
        controller.startOrExecuteProgram();
        assertTrue(controller.getProgramAreaBlocks().get(1).isHighlighted());
    }

    @Test
    public void whileBlockInDefinitionBlockWithBottomPlugConnect() {
        ModelController controller = new ModelController(GWT);
        controller.select(new ProgramLocation(20, 260));
        controller.release(new ProgramLocation(320, 20));
        controller.select(new ProgramLocation(20, 20));
        controller.release(new ProgramLocation(333, 63));
        controller.select(new ProgramLocation(180, 140));
        controller.release(new ProgramLocation(346, 106));
        controller.select(new ProgramLocation(20, 380));
        controller.release(new ProgramLocation(400, 20));
        controller.select(new ProgramLocation(180, 260));
        controller.release(new ProgramLocation(320, 260));
        controller.select(new ProgramLocation(340, 260));
        controller.release(new ProgramLocation(320, 260));
        controller.select(new ProgramLocation(20, 500));
        controller.release(new ProgramLocation(520, 420));
        assertTrue(ConnectionHandler.getInstance().allBlocksConnected(controller.getProgramAreaBlocks()));
    }
}