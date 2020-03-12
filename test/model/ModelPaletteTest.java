package model;

import model.blocks.ModelBlock;
import model.blocks.ModelMoveBlock;
import org.junit.Assert;
import org.junit.Test;
import utilities.Blocktype;
import utilities.Direction;
import utilities.Location;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ModelPaletteTest {

    @Test
    public void testModelPalette() {
        ModelPalette mP = new ModelPalette(200, 200);
        Location loc = new Location(10, 10);
        Blocktype bt = new Blocktype(Blocktype.MOVEFORWARD);
        ModelMoveBlock blk = new ModelMoveBlock(loc, bt);
        mP.blockToProgramWindow(blk, false);
        mP.blockToProgramWindow(blk, true);
        assertNull(mP.getForwardBlock());
    }

    @Test
    public void testModelPalette2() {
        ModelPalette mP = new ModelPalette(200, 200);
        assertNotNull(mP.handleMouseDown(mP.getForwardLocation().add(5,5)));
    }

}
