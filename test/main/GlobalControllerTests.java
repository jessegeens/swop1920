package main;

import model.ModelController;
import model.ModelGrid;
import model.blocks.ModelBlock;
import model.blocks.ModelMoveBlock;
import org.junit.Test;
import ui.UIController;
import utilities.Blocktype;
import utilities.Direction;
import utilities.GridInfo;
import utilities.Location;
import static org.junit.Assert.*;

import java.util.ArrayList;

public class GlobalControllerTests {

    @Test
    public void globalControllerTest() {
        GlobalController gC = new GlobalController();
        ArrayList<ModelBlock> mBlocks = new ArrayList<>();
        Location pos1 = new Location(100, 100);
        Blocktype t1 = new Blocktype(Blocktype.TURNLEFT);
        ModelMoveBlock mmb1 = new ModelMoveBlock(pos1, t1);
        Location pos2 = new Location(100, 220);
        Blocktype t2 = new Blocktype(Blocktype.TURNLEFT);
        ModelMoveBlock mmb2 = new ModelMoveBlock(pos2, t2);
        Location pos3 = new Location(100, 340);
        Blocktype t3 = new Blocktype(Blocktype.TURNLEFT);
        ModelMoveBlock mmb3 = new ModelMoveBlock(pos3, t3);
        mmb1.connect(mmb2);
        mmb2.connect(mmb3);
        mBlocks.add(mmb1);
        mBlocks.add(mmb2);
        mBlocks.add(mmb3);
        gC.getModelController().getPWindow().setBlocks(mBlocks);
        gC.execute();
        assertEquals(Direction.UP, gC.getModelController().getGrid().getRobotDir().getDirection());
        gC.execute();
        assertEquals(Direction.LEFT, gC.getModelController().getGrid().getRobotDir().getDirection());
        //gC.execute();
        //assertEquals(Direction.DOWN, gC.getModelController().getGrid().getRobotDir().getDirection());
    }
}
