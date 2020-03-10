package model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.Assert;
import java.util.ArrayList;
import model.blocks.*;


public class ModelControllerTest {

    @Test
    public void testMoveBlock(){
        ModelController modelController = new ModelController();
        ArrayList<ModelBlock> blocks = new ArrayList<ModelBlock>();
        modelController.setBlocks(blocks);
    }

    @Test
    public void testFindClosestBlock(){}

    @Test
    public void testFindLeftNeighbour(){}
}