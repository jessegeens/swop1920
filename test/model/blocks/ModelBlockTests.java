package model.blocks;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import utilities.*;

public class ModelBlockTests {

    @Test
    public void connectAndDisconnectTest() {
        WindowLocation loc1 = new WindowLocation(0, 0);
        ModelMoveBlock mmb1 = new ModelMoveBlock(loc1, new Blocktype(Blocktype.MOVEFORWARD));
        WindowLocation loc2 = new WindowLocation(10, 110);
        ModelMoveBlock mmb2 = new ModelMoveBlock(loc2, new Blocktype(Blocktype.MOVEFORWARD));
        mmb1.connect(mmb2);
        assertEquals(mmb1.getBottomPlug(), mmb2);
        mmb1.disconnect();
        assertNull(mmb1.getBottomPlug());
        assertNull(mmb2.getTopSocket());
    }

    @Test
    public void illegalConnectionTest(){
        WindowLocation loc1 = new WindowLocation(0,0);
        WindowLocation loc2 = new WindowLocation(150, 150);
        ModelMoveBlock mmb1 = new ModelMoveBlock(loc1, new Blocktype(Blocktype.TURNLEFT));
        ModelMoveBlock mmb2 = new ModelMoveBlock(loc2, new Blocktype(Blocktype.MOVEFORWARD));
        mmb1.connect(mmb2);
        assertNull(mmb1.getBottomPlug());
    }

    @Test //(expected = IllegalStateException.class) // has fault in itself, should not have an expected exception.
    public void connectAndDisconnectTest2() {
        WindowLocation loc1 = new WindowLocation(120, 0);
        WindowLocation loc2 = new WindowLocation(0, 0);
        ModelNotBlock mnb1 = new ModelNotBlock(loc1, new Blocktype(Blocktype.NOT));
        ModelWhileIfBlock mwb = new ModelWhileIfBlock(loc2, new Blocktype(Blocktype.WHILE));
        mwb.connect(mnb1);
        assertEquals(mwb.getRightSocket(), mnb1);
    }

    @Test
    public void connectAndDisconnectTest3() {
        WindowLocation loc1 = new WindowLocation(100, 100);
        WindowLocation loc2 = new WindowLocation(220, 100);
        ModelNotBlock mnb = new ModelNotBlock(loc1, new Blocktype(Blocktype.NOT));
        ModelWallInFrontBlock mwb = new ModelWallInFrontBlock(loc2, new Blocktype(Blocktype.WALLINFRONT));
        mnb.connect(mwb);
        assertEquals(mnb.getRightSocket(), mwb);
        mwb.disconnect();
        assertNull(mnb.getRightSocket());
        assertNull(mwb.getLeftPlug());
    }

    //TODO test isInCavity()
    //TODO test getSurroundingIfWhileBlock()
}