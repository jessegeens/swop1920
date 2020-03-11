package test.model.blocks;

import model.blocks.ModelMoveBlock;

public class MOdelMoveBlockTests {

    public void connectTest() {
        Location loc1 = new Location(1, 1);
        ModelMoveBlock mmb1 = new ModelMoveBlock(loc1, 2);

        Location loc2 = new Location(15, 15);
        ModelMoveBlock mmb2 = new ModelMoveBlock(loc2, 2);

        mmb1.connect(mmb2); //todo: how to test? which assertion?
    }

}