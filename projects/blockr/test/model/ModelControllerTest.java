package model;

import static org.junit.Assert.*;

import gameworldapi.GameWorldType;
import model.blocks.*;
import org.junit.Before;
import org.junit.Test;

import ui.BlockState;
import utilities.*;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

public class ModelControllerTest {

    private GameWorldType GWT;

    @Before
    public void setUp() throws Exception {
        try {
            File file = new File("C:" + File.separator + "Users" + File.separator + "Aram" + File.separator + "Desktop" + File.separator + "Informatica - Computerwetenschappen" + File.separator + "Ba Inf" + File.separator + "4ba informatica" + File.separator + "software-ontwerp" + File.separator + "swop1920" + File.separator + "projects" + File.separator + "robotgameworld" + File.separator + "out" + File.separator + "production" + File.separator + "robotgameworld");
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
        MC.release(new ProgramLocation(420, 500));
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
        controller.select(new ProgramLocation(30, 270));
        controller.release(new ProgramLocation(420, 420));
        controller.select(new ProgramLocation(430, 430));
        assertEquals(ModelPredicateBlock.class, controller.getActiveBlock().getClass());
    }

    @Test
    public void getModelBlocksSize() {
        ModelController controller = new ModelController(GWT);
        assertEquals(7, controller.getModelBlocks().size());
    }

    @Test
    public void getGameWorld() {
        ModelController controller = new ModelController(GWT);
        assertNotNull(controller.getGameWorld());
    }

    //TODO: undo redo tests (Oberon zegt Bert)
}