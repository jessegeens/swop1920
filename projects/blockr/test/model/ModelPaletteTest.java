package model;

import static org.junit.Assert.*;

import model.blocks.*;
import org.junit.Test;

import utilities.*;

public class ModelPaletteTest {

    @Test
    public void resetBlocks() {
        ModelPalette palette = new ModelPalette(1000,1000);
        palette.resetBlocks();
        assertEquals(7, palette.getPaletteBlocks().size());
    }

    @Test
    public void removeBlocks(){
        ModelPalette palette = new ModelPalette(1000,1000);
        palette.removeBlocks();
        assertTrue(palette.getPaletteBlocks().isEmpty());
    }

    @Test
    public void handleMouseDownIf() {
        ModelPalette palette = new ModelPalette(1000,1000);
        assertEquals(BlockType.IF, palette.handleMouseDown(new Location(60,540)).getBlockType());
    }

    @Test
    public void getPaletteBlocks() {
        ModelPalette palette = new ModelPalette(1000,1000);
        assertEquals(7, palette.getPaletteBlocks().size());
    }
}