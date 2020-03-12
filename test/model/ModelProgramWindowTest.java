package model;

import model.blocks.ModelBlock;
import model.blocks.ModelNotBlock;
import model.blocks.ModelWhileIfBlock;
import org.junit.Test;
import utilities.Blocktype;
import utilities.Location;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ModelProgramWindowTest {

    @Test
    public void programWindowTest() {
        ModelProgramWindow pw = new ModelProgramWindow(200, 200);
        Location pos1 = new Location(180, 10);
        Blocktype type1 = new Blocktype(Blocktype.WHILE);
        ModelWhileIfBlock block1 = new ModelWhileIfBlock(pos1, type1);
        Location pos2 = new Location(180, 130);
        Blocktype type2 = new Blocktype(Blocktype.WHILE);
        ModelWhileIfBlock block2  = new ModelWhileIfBlock(pos2, type2);
        block1.connect(block2);
        Location pos3 = new Location(20,20);
        Blocktype type3 = new Blocktype(Blocktype.NOT);
        ModelNotBlock block3 = new ModelNotBlock(pos3, type3);
        pw.addBlock(block1);
        pw.addBlock(block2);
        pw.addBlock(block3);
        assertFalse(pw.allBlocksConnected());
        assertEquals(130, pw.getFinishBlock().getPos().getY());
        pw.removeBlock(block3);
        assertEquals(2, pw.getPABlocks().size());
    }
}
