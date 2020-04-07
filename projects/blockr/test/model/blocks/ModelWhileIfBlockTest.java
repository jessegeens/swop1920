package model.blocks;

import static org.junit.Assert.*;
import org.junit.Test;

import utilities.*;

public class ModelWhileIfBlockTest {

    @Test
    public void getCondition(){
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new Location(100,100), BlockType.WHILE);
        ModelNotBlock notBlock = new ModelNotBlock(new Location(180,100), BlockType.NOT);
        ModelWallInFrontBlock wifBlock = new ModelWallInFrontBlock(new Location(260, 100), BlockType.WALLINFRONT);
        whileBlock.setRightSocket(notBlock);
        notBlock.setLeftPlug(whileBlock);
        notBlock.setRightSocket(wifBlock);
        wifBlock.setLeftPlug(notBlock);
        Condition whileCondition = null;
        try {
            whileCondition = whileBlock.getCondition();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        assertEquals(Condition.NOT_WALL_IN_FRONT, whileCondition);
    }

}
