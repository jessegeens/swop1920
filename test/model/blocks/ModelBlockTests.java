package model.blocks;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import utilities.*;

public class ModelBlockTests {

    @Test
    public void connectAndDisconnectTest() {
        Location loc1 = new Location(0, 0);
        ModelMoveBlock mmb1 = new ModelMoveBlock(loc1, new Blocktype(Blocktype.MOVEFORWARD));
        Location loc2 = new Location(10, 110);
        ModelMoveBlock mmb2 = new ModelMoveBlock(loc2, new Blocktype(Blocktype.MOVEFORWARD));
        mmb1.connect(mmb2);
        assertEquals(mmb1.getBottomPlug(), mmb2);
        mmb1.disconnect();
        assertNull(mmb1.getBottomPlug());
        assertNull(mmb2.getTopSocket());
    }

    @Test
    public void illegalConnectionTest(){
        Location loc1 = new Location(0,0);
        Location loc2 = new Location(150, 150);
        ModelMoveBlock mmb1 = new ModelMoveBlock(loc1, new Blocktype(Blocktype.TURNLEFT));
        ModelMoveBlock mmb2 = new ModelMoveBlock(loc2, new Blocktype(Blocktype.MOVEFORWARD));
        mmb1.connect(mmb2);
        assertNull(mmb1.getBottomPlug());
    }

    @Test //(expected = IllegalStateException.class) // has fault in itself, should not have an expected exception.
    public void connectAndDisconnectTest2() {
        Location loc1 = new Location(120, 0);
        Location loc2 = new Location(0, 0);
        ModelNotBlock mnb1 = new ModelNotBlock(loc1, new Blocktype(Blocktype.NOT));
        ModelWhileIfBlock mwb = new ModelWhileIfBlock(loc2, new Blocktype(Blocktype.WHILE));
        mwb.connect(mnb1);
        assertEquals(mwb.getRightSocket(), mnb1);
    }

    @Test
    public void connectAndDisconnectTest3() {
        Location loc1 = new Location(100, 100);
        Location loc2 = new Location(220, 100);
        ModelNotBlock mnb = new ModelNotBlock(loc1, new Blocktype(Blocktype.NOT));
        ModelWallInFrontBlock mwb = new ModelWallInFrontBlock(loc2, new Blocktype(Blocktype.WALLINFRONT));
        mnb.connect(mwb);
        assertEquals(mnb.getRightSocket(), mwb);
        mwb.disconnect();
        assertNull(mnb.getRightSocket());
        assertNull(mwb.getLeftPlug());
    }
}