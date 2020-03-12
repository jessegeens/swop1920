package model.blocks;

import utilities.*;

public class ModelMoveBlockTests {

    public void connectTest() {
        Location loc1 = new Location(1, 1);
        ModelMoveBlock mmb1 = new ModelMoveBlock(loc1, new Blocktype(Blocktype.MOVEFORWARD));

        Location loc2 = new Location(15, 15);
        ModelMoveBlock mmb2 = new ModelMoveBlock(loc2, new Blocktype(Blocktype.MOVEFORWARD));

        mmb1.connect(mmb2); //todo: how to test? which assertion?
    }

}