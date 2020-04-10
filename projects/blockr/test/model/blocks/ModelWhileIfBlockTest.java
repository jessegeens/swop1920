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

}
