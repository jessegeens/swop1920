package model;

import static org.junit.Assert.*;

import model.blocks.*;
import org.junit.Test;

import utilities.*;

public class ModelControllerTest {

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
    }

     */

}