package model;

import static org.junit.Assert.*;

import model.blocks.*;
import org.junit.Test;

import utilities.*;

public class ModelControllerTest {

    @Test
    public void handleKeyEventStart() {
        ModelController controller = new ModelController();
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new WindowLocation(400,400), new Blocktype(Blocktype.MOVEFORWARD));
        ModelMoveBlock leftBlock = new ModelMoveBlock(new WindowLocation(400, 480), new Blocktype(Blocktype.TURNLEFT));
        forwardBlock.connect(leftBlock);
        controller.getPArea().addBlock(forwardBlock);
        controller.getPArea().addBlock(leftBlock);
        controller.handleKeyEvent(1, 116, 'a');
        assertTrue(controller.getProgramRunner().isRunning());
    }

    @Test
    public void handleKeyEventStop() {
        ModelController controller = new ModelController();
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new WindowLocation(400,400), new Blocktype(Blocktype.MOVEFORWARD));
        ModelMoveBlock leftBlock = new ModelMoveBlock(new WindowLocation(400, 480), new Blocktype(Blocktype.TURNLEFT));
        forwardBlock.connect(leftBlock);
        controller.getPArea().addBlock(forwardBlock);
        controller.getPArea().addBlock(leftBlock);
        controller.handleKeyEvent(1, 116, 'a');
        controller.handleKeyEvent(1, 27, 'a');
        assertFalse(controller.getProgramRunner().isRunning());
    }

    @Test
    public void handleMouseEvent() {

    }

    @Test
    public void handlePaletteMouseEvent() {
    }

    @Test
    public void handleProgramAreaMouseEvent() {
    }

    @Test
    public void moveBlock() {
    }

    @Test
    public void getActiveBlock() {
    }
}