package model.blocks;

import static org.junit.Assert.*;

import model.ConnectionHandler;
import org.junit.Test;

import utilities.*;

public class ModelWhileIfBlockTest {

    @Test
    public void getCondition(){
        ConnectionHandler CC = new ConnectionHandler();
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new Location(100,100), BlockType.WHILE);
        ModelNotBlock notBlock = new ModelNotBlock(new Location(180,100), BlockType.NOT);
        ModelWallInFrontBlock wifBlock = new ModelWallInFrontBlock(new Location(260, 100), BlockType.WALLINFRONT);
        CC.connect(whileBlock, notBlock, ConnectionPoint.RIGHTSOCKET);
        CC.connect(notBlock, wifBlock, ConnectionPoint.RIGHTSOCKET);
        Condition whileCondition = null;
        try {
            whileCondition = whileBlock.getCondition();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        assertEquals(Condition.NOT_WALL_IN_FRONT, whileCondition);
    }


    //TODO move following tests to LocationController tests
    @Test
    public void updateCavityBlocksLocationTopBottom(){
        ConnectionHandler CC = new ConnectionHandler();
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new Location(100,100), BlockType.WHILE);
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(400,400), BlockType.MOVEFORWARD);
        ModelMoveBlock leftBlock = new ModelMoveBlock(new Location(400, 550), BlockType.TURNLEFT);
        ModelMoveBlock rightBlock = new ModelMoveBlock(new Location(400, 700), BlockType.TURNRIGHT);
        forwardBlock.setTopSocketPos(whileBlock.getCavityPlugPos());
        CC.connect(whileBlock, forwardBlock, ConnectionPoint.CAVITYPLUG);
        leftBlock.setTopSocketPos(whileBlock.getCavityPlugPos());
        CC.connect(forwardBlock, leftBlock, ConnectionPoint.BOTTOMPLUG);
        rightBlock.setTopSocketPos(whileBlock.getCavityPlugPos());
        CC.connect(leftBlock, rightBlock, ConnectionPoint.BOTTOMPLUG);
        whileBlock.updateCavityBlocksLocations();
        assertEquals(new Location(113,303), rightBlock.getPos());
    }

}
