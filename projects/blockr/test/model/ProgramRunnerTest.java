package model;

import model.blocks.*;
import org.junit.Test;
import utilities.ProgramLocation;

import static org.junit.Assert.*;

public class ProgramRunnerTest {

    @Test
    public void reset() {
        ProgramRunner PR = new ProgramRunner(null);
        ModelBlock block = new ModelNotBlock(new ProgramLocation(100, 100));
        PR.initialise(block);
        PR.reset();
        assertFalse(block.isHighlighted());
    }

    @Test
    public void execute() {
        ProgramRunner PR = new ProgramRunner(null);
        ModelActionBlock rightBlock = new ModelActionBlock(new ProgramLocation(100, 100), null);
        ModelActionBlock leftBlock = new ModelActionBlock(new ProgramLocation(100 ,180), null);
        rightBlock.setBottomPlug(leftBlock);
        leftBlock.setTopSocket(rightBlock);
        PR.initialise(rightBlock);
        PR.execute();
        assertTrue(leftBlock.isHighlighted());
    }

    @Test
    public void executeIf(){
        ProgramRunner PR = new ProgramRunner(null);
        ModelActionBlock startBlock = new ModelActionBlock(new ProgramLocation(20, 100), null);
        ModelWhileIfBlock ifBlock = new ModelWhileIfBlock(new ProgramLocation(100,100), true);
        ModelActionBlock forwardBlock = new ModelActionBlock(new ProgramLocation(113,143), null);
        ModelActionBlock leftBlock = new ModelActionBlock(new ProgramLocation(113, 223), null);
        ModelActionBlock rightBlock = new ModelActionBlock(new ProgramLocation(113, 303), null);
        ModelActionBlock finishBlock = new ModelActionBlock(new ProgramLocation(100, 420), null);
        startBlock.setBottomPlug(ifBlock);
        ifBlock.setTopSocket(startBlock);
        forwardBlock.setTopSocket(ifBlock);
        ifBlock.setCavityPlug(forwardBlock);
        leftBlock.setTopSocket(forwardBlock);
        forwardBlock.setBottomPlug(leftBlock);
        rightBlock.setTopSocket(leftBlock);
        leftBlock.setBottomPlug(rightBlock);
        ifBlock.setCavitySocket(leftBlock);
        rightBlock.setBottomPlug(ifBlock);
        ifBlock.setBottomPlug(finishBlock);
        finishBlock.setTopSocket(ifBlock);
        PR.initialise(startBlock);
        PR.execute();
        PR.execute();
        PR.execute();
        PR.execute();
        assertTrue(finishBlock.isHighlighted());
    }
}