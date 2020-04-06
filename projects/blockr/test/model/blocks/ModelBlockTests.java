package model.blocks;

import model.ConnectionController;
import org.junit.Test;

import utilities.*;

import static org.junit.Assert.*;

public class ModelBlockTests {

    @Test
    public void isInCavityTrue(){
        ConnectionController CC = new ConnectionController();
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
        ConnectionController CC = new ConnectionController();
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
        ConnectionController CC = new ConnectionController();
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new Location(100,100), BlockType.WHILE);
        ModelMoveBlock forwardBlock = new ModelMoveBlock(new Location(400,400), BlockType.MOVEFORWARD);
        ModelMoveBlock leftBlock = new ModelMoveBlock(new Location(400, 550), BlockType.TURNLEFT);
        ModelMoveBlock rightBlock = new ModelMoveBlock(new Location(400, 700), BlockType.TURNRIGHT);
        CC.connect(whileBlock, forwardBlock, ConnectionPoint.CAVITYPLUG);
        CC.connect(forwardBlock, leftBlock, ConnectionPoint.BOTTOMPLUG);
        CC.connect(leftBlock, rightBlock, ConnectionPoint.BOTTOMPLUG);
        assertEquals(whileBlock, forwardBlock.getSurroundingWhileIfBlock());
    }
}