package model.blocks;

import static org.junit.Assert.*;

import gameworldapi.PredicateType;
import org.junit.Test;

import utilities.*;

public class ModelWhileIfBlockTest {


    @Test
    public void getPredicate(){
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new ProgramLocation(100,100), false);
        ModelNotBlock notBlock = new ModelNotBlock(new ProgramLocation(180,100));
        PredicateType pt = new PredicateType() {};
        ModelPredicateBlock wifBlock = new ModelPredicateBlock(new ProgramLocation(260, 100), pt);
        whileBlock.setRightSocket(notBlock);
        notBlock.setLeftPlug(whileBlock);
        notBlock.setRightSocket(wifBlock);
        wifBlock.setLeftPlug(notBlock);
        PredicateType whilePredicate = whileBlock.getPredicate();
        assertEquals(pt, whilePredicate);
    }

    @Test
    public void isNegatedTrue(){
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new ProgramLocation(100,100), false);
        ModelNotBlock notBlock = new ModelNotBlock(new ProgramLocation(180,100));
        PredicateType pt = new PredicateType() {};
        ModelPredicateBlock wifBlock = new ModelPredicateBlock(new ProgramLocation(260, 100), pt);
        whileBlock.setRightSocket(notBlock);
        notBlock.setLeftPlug(whileBlock);
        notBlock.setRightSocket(wifBlock);
        wifBlock.setLeftPlug(notBlock);
        assertTrue(whileBlock.isNegated());
    }

    @Test
    public void isNegatedFalse(){
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new ProgramLocation(100,100), false);
        ModelNotBlock notBlock = new ModelNotBlock(new ProgramLocation(180,100));
        ModelNotBlock notBlock2 = new ModelNotBlock(new ProgramLocation(260, 100));
        PredicateType pt = new PredicateType() {};
        ModelPredicateBlock wifBlock = new ModelPredicateBlock(new ProgramLocation(340, 100), pt);
        whileBlock.setRightSocket(notBlock);
        notBlock.setLeftPlug(whileBlock);
        notBlock.setRightSocket(notBlock2);
        notBlock2.setLeftPlug(notBlock);
        notBlock2.setRightSocket(wifBlock);
        wifBlock.setLeftPlug(notBlock2);
        assertFalse(whileBlock.isNegated());
    }

    @Test
    public void getCavityBlocks(){
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new ProgramLocation(100,100), false);
        ModelActionBlock forwardBlock = new ModelActionBlock(new ProgramLocation(400,400), null);
        ModelActionBlock leftBlock = new ModelActionBlock(new ProgramLocation(400, 550), null);
        ModelActionBlock rightBlock = new ModelActionBlock(new ProgramLocation(400, 700), null);
        forwardBlock.setTopSocket(whileBlock);
        whileBlock.setCavityPlug(forwardBlock);
        leftBlock.setTopSocket(forwardBlock);
        forwardBlock.setBottomPlug(leftBlock);
        rightBlock.setTopSocket(leftBlock);
        leftBlock.setBottomPlug(rightBlock);
        whileBlock.setCavitySocket(leftBlock);
        rightBlock.setBottomPlug(whileBlock);
        assertTrue(whileBlock.getCavityBlocks().contains(leftBlock));
    }

}
