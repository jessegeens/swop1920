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
            File file = new File("/home/oberon/Documents/Studies/SWOP/swop1920/projects/robotgameworld/out/production/robotgameworld/");
            //convert the file to URL format
            URL url = file.toURI().toURL();
            URL[] urls = new URL[]{url};
            //load this folder into Class loader
            ClassLoader cl = new URLClassLoader(urls);
            //load the Address class in 'c:\\other_classes\\'
            Class cls = cl.loadClass("robotgameworld.RobotGameWorldType");
            //GameWorldType worldType = ((GameWorldType) Class.forName(args[0]).newInstance());
            GameWorldType worldType = (GameWorldType) cls.newInstance();
            GWT = worldType;
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



    /*
    @Test
    public void handleKeyEventStart() {
        ModelController controller = new ModelController();
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(400,400), BlockType.MOVEFORWARD);
        ModelMoveBlock leftBlock = new ModelMoveBlock(new Location(400, 480), BlockType.TURNLEFT);
        forwardBlock.connect(leftBlock);
        controller.getPArea().addBlock(forwardBlock);
        controller.getPArea().addBlock(leftBlock);
        controller.handleKeyEvent(1, 116, 'a');
        assertTrue(controller.getProgramRunner().isRunning());
    }

    @Test
    public void handleKeyEventStop() {
        ModelController controller = new ModelController();
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(400,400), BlockType.MOVEFORWARD);
        ModelMoveBlock leftBlock = new ModelMoveBlock(new Location(400, 480), BlockType.TURNLEFT);
        forwardBlock.connect(leftBlock);
        controller.getPArea().addBlock(forwardBlock);
        controller.getPArea().addBlock(leftBlock);
        controller.handleKeyEvent(1, 116, 'a');
        controller.handleKeyEvent(1, 27, 'a');
        assertFalse(controller.getProgramRunner().isRunning());
    }

    @Test
    public void handlePaletteMouseDownEvent() {
        ModelController controller = new ModelController();
        controller.handleMouseEvent(501,new Location(40,40),1);
        assertEquals(controller.getPalette().getTurnLeftBlock().getBlockType(), controller.getActiveBlock().getBlockType());
    }

    @Test
    public void handlePaletteMouseUpEvent(){
        ModelController controller = new ModelController();
        controller.setActiveBlock(new ModelMoveBlock(new Location(120,120), BlockType.TURNLEFT));
        controller.handleMouseEvent(502,new Location(120,120),1);
        assertNull(controller.getActiveBlock());
    }

    @Test
    public void handlePaletteMouseDragEvent(){
        ModelController controller = new ModelController();
        ModelMoveBlock leftBlock = new ModelMoveBlock(new Location(120,120), BlockType.TURNLEFT);
        controller.setActiveBlock(leftBlock);
        controller.handleMouseEvent(506,new Location(170,170),1);
        assertEquals(new Location(170,170), leftBlock.getPos());
    }

    @Test
    public void handleProgramAreaMouseDownEvent() {
        ModelController controller = new ModelController();
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(400,400), BlockType.MOVEFORWARD);
        controller.getPArea().addBlock(forwardBlock);
        controller.handleMouseEvent(501,new Location(420,420),1);
        assertEquals(forwardBlock, controller.getActiveBlock());
    }

    @Test
    public void handleProgramAreaMouseUpEvent(){
        ModelController controller = new ModelController();
        controller.setActiveBlock(new ModelMoveBlock(new Location(120,120), BlockType.TURNLEFT));
        controller.handleMouseEvent(502,new Location(420,120),1);
        assertNull(controller.getActiveBlock());
    }

    @Test
    public void handleProgramAreaMouseDragEvent(){
        ModelController controller = new ModelController();
        ModelMoveBlock leftBlock = new ModelMoveBlock(new Location(120,120), BlockType.TURNLEFT);
        controller.setActiveBlock(leftBlock);
        controller.handleMouseEvent(506,new Location(470,170),1);
        assertEquals(new Location(470,170), leftBlock.getPos());
    }


    @Test
    public void wallInFrontBlockNullPointer(){
        ModelController controller = new ModelController();
        ModelWallInFrontBlock wallInFrontBlock = new ModelWallInFrontBlock(new Location(400,400), BlockType.WALLINFRONT);
        controller.setActiveBlock(wallInFrontBlock);
        controller.handleProgramAreaMouseEvent(502, new Location(420,420), 1);
        controller.handleProgramAreaMouseEvent(501, new Location(440,440), 1);
        assertEquals(wallInFrontBlock, controller.getActiveBlock());
    }*/

}