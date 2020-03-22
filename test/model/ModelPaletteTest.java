package model;

import static org.junit.Assert.*;

import model.blocks.*;
import org.junit.Test;

import utilities.*;

public class ModelPaletteTest {

    @Test
    public void blockToProgramWindow() {
        ModelPalette palette = new ModelPalette(1000,1000);
        palette.setTurnLeftBlock(null);
        ModelBlock blk = new ModelMoveBlock(new WindowLocation(100,100),new Blocktype(Blocktype.TURNLEFT));
        palette.blockToProgramArea(blk,false);
        assertEquals(7, palette.getPaletteBlocks().size());
    }

    @Test
    public void blockToProgramWindowLimit() {
        ModelPalette palette = new ModelPalette(1000,1000);
        palette.setTurnLeftBlock(null);
        ModelBlock blk = new ModelMoveBlock(new WindowLocation(100,100),new Blocktype(Blocktype.TURNLEFT));
        palette.blockToProgramArea(blk,true);
        assertEquals(0, palette.getPaletteBlocks().size());
    }

    @Test
    public void resetBlocks() {
        ModelPalette palette = new ModelPalette(1000,1000);
        palette.setTurnLeftBlock(null);
        palette.resetBlocks();
        assertFalse(palette.getTurnLeftBlock() == null);
    }

    @Test
    public void handleMouseDownLeft() {
        ModelPalette palette = new ModelPalette(1000,1000);
        assertEquals(palette.getTurnLeftBlock(), palette.handleMouseDown(new WindowLocation(60,60), false));
    }

    @Test
    public void handleMouseDownIf() {
        ModelPalette palette = new ModelPalette(1000,1000);
        assertEquals(palette.getIfBlock(), palette.handleMouseDown(new WindowLocation(60,540), false));
    }

    @Test
    public void getPaletteBlocks() {
        ModelPalette palette = new ModelPalette(1000,1000);
        assertEquals(7, palette.getPaletteBlocks().size());
    }
}