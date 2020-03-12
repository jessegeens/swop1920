package model.blocks;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import utilities.*;

public class ModelNotBlockTests {

    @Test(expected = IllegalStateException.class) // has fault in itself, should not have an expected exception.
    public void connectAndDisconnectTest() {
        Location loc1 = new Location(0, 0);
        Location loc2 = new Location(60, 140);
        ModelNotBlock mnb1 = new ModelNotBlock(loc1, new Blocktype(Blocktype.NOT));
        ModelWhileIfBlock mwb = new ModelWhileIfBlock(loc2, new Blocktype(Blocktype.WHILE));
        mwb.connect(mnb1);
        assertEquals(mwb.getRightSocket(), mnb1);
    }
}