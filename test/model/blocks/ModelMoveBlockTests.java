package model.blocks;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import utilities.*;

public class ModelMoveBlockTests {

    @Test
    public void connectTest() {
        Location loc1 = new Location(1, 1);
        ModelMoveBlock mmb1 = new ModelMoveBlock(loc1, new Blocktype(Blocktype.MOVEFORWARD));

        Location loc2 = new Location(110, 10);
        ModelMoveBlock mmb2 = new ModelMoveBlock(loc2, new Blocktype(Blocktype.MOVEFORWARD));

        mmb1.connect(mmb2);

        assertEquals(mmb1.getBottomPlug(), mmb2);
        assertEquals(mmb2.getTopSocket(), mmb1);
    }

}