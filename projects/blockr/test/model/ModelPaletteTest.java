package model;

import static org.junit.Assert.*;

import model.blocks.*;
import org.junit.Test;

import utilities.*;

import java.util.ArrayList;

public class ModelPaletteTest {

    @Test
    public void populateBlocks() {
        ModelPalette palette = new ModelPalette(new ArrayList<>(), new ArrayList<>(), null);
        palette.populateBlocks();
        assertEquals(4, palette.getPaletteBlocks().size());
    }

    @Test
    public void removeBlocks(){
        ModelPalette palette = new ModelPalette(new ArrayList<>(), new ArrayList<>(), null);
        palette.removeBlocks();
        assertTrue(palette.getPaletteBlocks().isEmpty());
    }

    @Test
    public void handleMouseDownIf() {
        ModelPalette palette = new ModelPalette(new ArrayList<>(), new ArrayList<>(), null);
        assertTrue(palette.returnSelectedBlock(new ProgramLocation(60,60)) instanceof ModelWhileIfBlock);
    }

    @Test
    public void getPaletteBlocks() {
        ModelPalette palette = new ModelPalette(new ArrayList<>(), new ArrayList<>(), null);
        assertEquals(4, palette.getPaletteBlocks().size());
    }
}