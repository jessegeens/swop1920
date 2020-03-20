package model.blocks;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import utilities.*;

public class ModelWhileIfBlockTests {

    private ModelWhileIfBlock whileBlock;
    private ModelWhileIfBlock ifBlock;
    private ModelMoveBlock forwardBlock;
    private ModelMoveBlock leftBlock;
    private ModelMoveBlock rightBlock;
    private ModelNotBlock notBlock;
    private ModelWallInFrontBlock wallBlock;


    /**
     * One block of each type is generated at a certain place so we can interact with them within the tests.
     */
    @Before
    public void setUp(){
        whileBlock = new ModelWhileIfBlock(new WindowLocation(420,420), new Blocktype(Blocktype.WHILE));
        ifBlock = new ModelWhileIfBlock(new WindowLocation(420,820), new Blocktype(Blocktype.IF));
        forwardBlock = new ModelMoveBlock(new WindowLocation(20,20), new Blocktype(Blocktype.MOVEFORWARD));
        leftBlock = new ModelMoveBlock(new WindowLocation(20,220), new Blocktype(Blocktype.TURNLEFT));
        rightBlock = new ModelMoveBlock(new WindowLocation(20, 420), new Blocktype(Blocktype.TURNRIGHT));
        notBlock = new ModelNotBlock(new WindowLocation(20, 620), new Blocktype(Blocktype.NOT));
        wallBlock = new ModelWallInFrontBlock(new WindowLocation(20,820), new Blocktype(Blocktype.WALLINFRONT));
    }

    @Test
    public void ExactConnectTopSocketTest(){
        forwardBlock.setPos(new WindowLocation(420,320));
        whileBlock.connect(forwardBlock);
        assertEquals(forwardBlock, whileBlock.getTopSocket());
    }

    @Test
    public void ExactConnectTopSocketInverseTest(){
        forwardBlock.setPos(new WindowLocation(420,320));
        forwardBlock.connect(whileBlock);
        assertEquals(forwardBlock, whileBlock.getTopSocket());
    }

    @Test
    public void ExactConnectBottomPlugTest(){
        leftBlock.setPos(new WindowLocation(420, 500));
        whileBlock.connect(leftBlock);
        assertEquals(leftBlock,whileBlock.getBottomPlug());
    }

    @Test
    public void ExactConnectBottomPlugInverseTest(){
        leftBlock.setPos(new WindowLocation(420, 500));
        leftBlock.connect(whileBlock);
        assertEquals(leftBlock,whileBlock.getBottomPlug());
    }

    @Test
    public void ExactConnectRightSocketTest(){
        notBlock.setPos(new WindowLocation(500, 420 - ModelBlock.STD_HEIGHT/6));
        whileBlock.connect(notBlock);
        assertEquals(notBlock, whileBlock.getRightSocket());
    }

    @Test
    public void ExactConnectRightSocketInverseTest(){
        notBlock.setPos(new WindowLocation(500, 420 - ModelBlock.STD_HEIGHT/6));
        whileBlock.connect(notBlock);
        assertEquals(notBlock, whileBlock.getRightSocket());
    }

    @Test
    public void RelativeConnectTopSocketTest(){
        forwardBlock.setBottomPlugPos(whileBlock.getTopSocketPos());
        whileBlock.connect(forwardBlock);
        assertEquals(forwardBlock, whileBlock.getTopSocket());
    }

    @Test
    public void RelativeConnectTopSocketInverseTest(){
        forwardBlock.setBottomPlugPos(whileBlock.getTopSocketPos());
        forwardBlock.connect(whileBlock);
        assertEquals(forwardBlock, whileBlock.getTopSocket());
    }

    @Test
    public void RelativeConnectBottomPlugTest(){
        leftBlock.setTopSocketPos(whileBlock.getBottomPlugPos());
        whileBlock.connect(leftBlock);
        assertEquals(leftBlock,whileBlock.getBottomPlug());
    }

    @Test
    public void RelativeConnectBottomPlugInverseTest(){
        leftBlock.setTopSocketPos(whileBlock.getBottomPlugPos());
        leftBlock.connect(whileBlock);
        assertEquals(leftBlock,whileBlock.getBottomPlug());
    }

    @Test
    public void RelativeConnectRightSocketTest(){
        notBlock.setLeftPlugPos(whileBlock.getRightSocketPos());
        whileBlock.connect(notBlock);
        assertEquals(notBlock, whileBlock.getRightSocket());
    }

    @Test
    public void RelativeConnectRightSocketInverseTest(){
        notBlock.setLeftPlugPos(whileBlock.getRightSocketPos());
        whileBlock.connect(notBlock);
        assertEquals(notBlock, whileBlock.getRightSocket());
    }



}
