package model.blocks;

import model.ConnectionHandler;
import org.junit.Test;

import utilities.*;

import static org.junit.Assert.*;

public class ModelBlockTests {

    @Test
    public void isInCavityTrue(){
        ConnectionHandler CC = new ConnectionHandler();
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new Location(100,100), BlockType.WHILE);
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(400,400), BlockType.MOVEFORWARD);
        ModelMoveBlock leftBlock = new ModelMoveBlock(new Location(400, 550), BlockType.TURNLEFT);
        ModelMoveBlock rightBlock = new ModelMoveBlock(new Location(400, 700), BlockType.TURNRIGHT);
        CC.connect(whileBlock, forwardBlock, ConnectionPoint.CAVITYPLUG);
        CC.connect(forwardBlock, leftBlock, ConnectionPoint.BOTTOMPLUG);
        CC.connect(leftBlock, rightBlock, ConnectionPoint.BOTTOMPLUG);
        assertTrue(forwardBlock.isInCavity());
    }

    @Test
    public void isInCavityFalse(){
        ConnectionHandler CC = new ConnectionHandler();
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new Location(100,100), BlockType.WHILE);
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(400,400), BlockType.MOVEFORWARD);
        ModelMoveBlock leftBlock = new ModelMoveBlock(new Location(400, 550), BlockType.TURNLEFT);
        ModelMoveBlock rightBlock = new ModelMoveBlock(new Location(400, 700), BlockType.TURNRIGHT);
        CC.connect(whileBlock, forwardBlock, ConnectionPoint.CAVITYPLUG);
        CC.connect(whileBlock, leftBlock, ConnectionPoint.BOTTOMPLUG);
        CC.connect(forwardBlock, rightBlock, ConnectionPoint.BOTTOMPLUG);
        assertFalse(leftBlock.isInCavity());
    }

    @Test
    public void getSurroundingIfWhileBlock(){
        ConnectionHandler CC = new ConnectionHandler();
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new Location(100,100), BlockType.WHILE);
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(400,400), BlockType.MOVEFORWARD);
        ModelMoveBlock leftBlock = new ModelMoveBlock(new Location(400, 550), BlockType.TURNLEFT);
        ModelMoveBlock rightBlock = new ModelMoveBlock(new Location(400, 700), BlockType.TURNRIGHT);
        CC.connect(whileBlock, forwardBlock, ConnectionPoint.CAVITYPLUG);
        CC.connect(forwardBlock, leftBlock, ConnectionPoint.BOTTOMPLUG);
        CC.connect(leftBlock, rightBlock, ConnectionPoint.BOTTOMPLUG);
        assertEquals(whileBlock, forwardBlock.getSurroundingWhileIfBlock());
    }

    @Test
    public void inBoundsOfElement(){
        ModelMoveBlock rightBlock = new ModelMoveBlock(new Location(400, 700), BlockType.TURNRIGHT);
        assertTrue(rightBlock.inBoundsOfElement(new Location(420, 720)));
    }



    //TODO move the following tests to the LocationController tests

    @Test
    public void setTopSocketPosConnects(){
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new Location(100,100), BlockType.WHILE);
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(400,400), BlockType.MOVEFORWARD);
        forwardBlock.setTopSocketPos(whileBlock.getBottomPlugPos());
        assertEquals(forwardBlock.getTopSocketPos(), whileBlock.getBottomPlugPos());
    }

    @Test
    public void setLeftPlugPosConnects(){
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new Location(100,100), BlockType.WHILE);
        ModelNotBlock notBlock = new ModelNotBlock(new Location(400,400), BlockType.NOT);
        notBlock.setLeftPlugPos(whileBlock.getRightSocketPos());
        assertEquals(notBlock.getLeftPlugPos(), whileBlock.getRightSocketPos());
    }


}