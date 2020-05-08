package model;

import static org.junit.Assert.*;

import model.blocks.*;
import org.junit.Test;

import utilities.*;

import java.util.ArrayList;

public class ModelPaletteTest {

    @Test
    public void populateBlocks() {
        ModelPalette palette = new ModelPalette(new ArrayList<>(), new ArrayList<>());
        palette.populateBlocks();
        assertEquals(3, palette.getPaletteBlocks().size());
    }

    @Test
    public void removeBlocks(){
        ModelPalette palette = new ModelPalette(new ArrayList<>(), new ArrayList<>());
        palette.removeBlocks();
        assertTrue(palette.getPaletteBlocks().isEmpty());
    }

    @Test
    public void handleMouseDownIf() {
        ModelPalette palette = new ModelPalette(new ArrayList<>(), new ArrayList<>());
        assertTrue(palette.returnSelectedBlock(new ProgramLocation(60,60)) instanceof ModelWhileIfBlock);
    }

    @Test
    public void getPaletteBlocks() {
        ModelPalette palette = new ModelPalette(new ArrayList<>(), new ArrayList<>());
        assertEquals(3, palette.getPaletteBlocks().size());
    }
}