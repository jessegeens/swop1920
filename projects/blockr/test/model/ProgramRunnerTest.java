package model;

import gameworldapi.*;
import model.blocks.*;
import org.junit.Before;
import org.junit.Test;
import utilities.ProgramLocation;
import utilities.ProgramState;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class ProgramRunnerTest {

    private GameWorld GW;
    private ArrayList<PredicateType> Predicates;
    private ArrayList<ActionType> Actions;

    @Before
    public void setUp() throws Exception {
        try {
            File file = new File("/home/oberon/Documents/Studies/SWOP/swop1920/projects/robotgameworld/out/production/robotgameworld/");
            //File file = new File("/home/jesse/Code/School/3ba/swop1920/projects/robotgameworld/out/production/robotgameworld/");
            //convert the file to URL format
            URL url = file.toURI().toURL();
            URL[] urls = new URL[]{url};
            //load this folder into Class loader
            ClassLoader cl = new URLClassLoader(urls);
            //load the Address class in 'c:\\other_classes\\'
            Class cls = cl.loadClass("robotgameworld.RobotGameWorldType");
            //GameWorldType worldType = ((GameWorldType) Class.forName(args[0]).newInstance());
            GameWorldType worldType = (GameWorldType) cls.newInstance();
            GW = worldType.newWorldInstance();
            Predicates = worldType.getSupportedPredicates();
            Actions = worldType.getSupportedActions();
        }
        catch (Exception ex){
            System.out.println("Error: " + ex.getMessage().toString());
        }
    }

    @Test
    public void resetTest() {
        ProgramRunner pr = new ProgramRunner(GW);
        ModelBlock block = new ModelNotBlock(new ProgramLocation(100, 100));
        pr.initialise(block);
        pr.reset();
        assertFalse(block.isHighlighted());
    }

    @Test
    public void executeTest() {
        ProgramRunner pr = new ProgramRunner(GW);
        ModelActionBlock rightBlock = new ModelActionBlock(new ProgramLocation(100, 100), Actions.get(0));
        ModelActionBlock leftBlock = new ModelActionBlock(new ProgramLocation(100 ,180), Actions.get(0));
        rightBlock.setBottomPlug(leftBlock);
        leftBlock.setTopSocket(rightBlock);
        pr.initialise(rightBlock);
        pr.execute();
        assertTrue(leftBlock.isHighlighted());
    }

    @Test
    public void executeIfTest(){
        ProgramRunner pr = new ProgramRunner(GW);
        ModelPredicateBlock predicateBlock = new ModelPredicateBlock(new ProgramLocation(100, 260), Predicates.get(0));
        ModelNotBlock notBlock = new ModelNotBlock(new ProgramLocation(100, 180));
        ModelWhileIfBlock ifBlock = new ModelWhileIfBlock(new ProgramLocation(100,100), true);
        ModelActionBlock forwardBlock = new ModelActionBlock(new ProgramLocation(113,143), Actions.get(0));
        ModelActionBlock leftBlock = new ModelActionBlock(new ProgramLocation(113, 223), Actions.get(0));
        ModelActionBlock rightBlock = new ModelActionBlock(new ProgramLocation(113, 303), Actions.get(0));
        ModelActionBlock finishBlock = new ModelActionBlock(new ProgramLocation(100, 420), Actions.get(0));
        ifBlock.setRightSocket(notBlock);
        notBlock.setLeftPlug(ifBlock);
        notBlock.setRightSocket(predicateBlock);
        predicateBlock.setLeftPlug(ifBlock);
        ifBlock.setCavityPlug(forwardBlock);
        forwardBlock.setTopSocket(ifBlock);
        forwardBlock.setBottomPlug(leftBlock);
        leftBlock.setTopSocket(forwardBlock);
        leftBlock.setBottomPlug(rightBlock);
        rightBlock.setTopSocket(leftBlock);
        rightBlock.setBottomPlug(ifBlock);
        ifBlock.setCavitySocket(rightBlock);
        ifBlock.setBottomPlug(finishBlock);
        finishBlock.setTopSocket(ifBlock);
        pr.initialise(ifBlock);
        pr.execute();
        pr.execute();
        pr.execute();
        assertTrue(finishBlock.isHighlighted());
    }

    @Test
    public void executeEndTest(){
        ProgramRunner pr = new ProgramRunner(GW);
        ModelActionBlock forwardBlock = new ModelActionBlock(new ProgramLocation(113,143), Actions.get(0));
        ModelActionBlock leftBlock = new ModelActionBlock(new ProgramLocation(113, 223), Actions.get(0));
        ModelActionBlock rightBlock = new ModelActionBlock(new ProgramLocation(113, 303), Actions.get(0));
        forwardBlock.setBottomPlug(leftBlock);
        leftBlock.setTopSocket(forwardBlock);
        leftBlock.setBottomPlug(rightBlock);
        rightBlock.setTopSocket(leftBlock);
        pr.initialise(forwardBlock);
        pr.execute();
        pr.execute();
        pr.execute();
        pr.execute();
        assertFalse(pr.isRunning());
    }

    @Test
    public void executeWhileNotWIFTest(){
        ProgramRunner pr = new ProgramRunner(GW);
        ModelActionBlock rightBlock = new ModelActionBlock(new ProgramLocation(100, 20), Actions.get(2));
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new ProgramLocation(100,100), false);
        ModelActionBlock forwardBlock = new ModelActionBlock(new ProgramLocation(113,143), Actions.get(0));
        ModelPredicateBlock wifBlock = new ModelPredicateBlock(new ProgramLocation(100, 260), Predicates.get(0));
        ModelNotBlock notBlock = new ModelNotBlock(new ProgramLocation(100, 180));
        rightBlock.setBottomPlug(whileBlock);
        whileBlock.setTopSocket(rightBlock);
        whileBlock.setCavityPlug(forwardBlock);
        forwardBlock.setTopSocket(whileBlock);
        forwardBlock.setBottomPlug(whileBlock);
        whileBlock.setCavitySocket(whileBlock);
        whileBlock.setRightSocket(notBlock);
        notBlock.setLeftPlug(whileBlock);
        notBlock.setRightSocket(wifBlock);
        wifBlock.setLeftPlug(notBlock);
        pr.initialise(rightBlock);
        pr.execute();
        pr.execute();
        pr.execute();
        assertFalse(pr.isRunning());
    }

    @Test
    public void undoBlockTest() {
        ProgramRunner pr = new ProgramRunner(GW);
        ModelActionBlock forwardBlock1 = new ModelActionBlock(new ProgramLocation(100,140), Actions.get(0));
        ModelActionBlock forwardBlock2 = new ModelActionBlock(new ProgramLocation(100,180), Actions.get(0));
        forwardBlock1.setBottomPlug(forwardBlock2);
        forwardBlock2.setTopSocket(forwardBlock1);
        pr.initialise(forwardBlock1);
        pr.execute();
        pr.execute();
        UndoRedoHandler.getInstance().undo();
        pr.setState((ProgramState) UndoRedoHandler.getInstance().getState());
        UndoRedoHandler.getInstance().undo();
        pr.setState((ProgramState) UndoRedoHandler.getInstance().getState());
        assertTrue(forwardBlock1.isHighlighted());
    }

    @Test
    public void undoInitialisationTest() {
        ProgramRunner pr = new ProgramRunner(GW);
        ModelActionBlock forwardBlock1 = new ModelActionBlock(new ProgramLocation(100,140), Actions.get(0));
        ModelActionBlock forwardBlock2 = new ModelActionBlock(new ProgramLocation(100,180), Actions.get(0));
        forwardBlock1.setBottomPlug(forwardBlock2);
        forwardBlock2.setTopSocket(forwardBlock1);
        pr.initialise(forwardBlock1);
        pr.execute();
        pr.execute();
        UndoRedoHandler.getInstance().undo();
        pr.setState((ProgramState) UndoRedoHandler.getInstance().getState());
        UndoRedoHandler.getInstance().undo();
        pr.setState((ProgramState) UndoRedoHandler.getInstance().getState());
        UndoRedoHandler.getInstance().undo();
        pr.setState((ProgramState) UndoRedoHandler.getInstance().getState());
        assertFalse(pr.isRunning());
    }

    @Test
    public void redoBlockTest() {
        ProgramRunner pr = new ProgramRunner(GW);
        ModelActionBlock forwardBlock1 = new ModelActionBlock(new ProgramLocation(100,140), Actions.get(0));
        ModelActionBlock forwardBlock2 = new ModelActionBlock(new ProgramLocation(100,180), Actions.get(0));
        forwardBlock1.setBottomPlug(forwardBlock2);
        forwardBlock2.setTopSocket(forwardBlock1);
        pr.initialise(forwardBlock1);
        pr.execute();
        pr.execute();
        UndoRedoHandler.getInstance().undo();
        pr.setState((ProgramState) UndoRedoHandler.getInstance().getState());
        UndoRedoHandler.getInstance().undo();
        pr.setState((ProgramState) UndoRedoHandler.getInstance().getState());
        UndoRedoHandler.getInstance().redo();
        pr.setState((ProgramState) UndoRedoHandler.getInstance().getState());
        assertTrue(forwardBlock2.isHighlighted());
    }

    @Test
    public void redoWhileBlockTest() {
        ProgramRunner pr = new ProgramRunner(GW);
        ModelActionBlock rightBlock = new ModelActionBlock(new ProgramLocation(100, 20), Actions.get(2));
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new ProgramLocation(100,100), false);
        ModelActionBlock forwardBlock = new ModelActionBlock(new ProgramLocation(113,143), Actions.get(0));
        ModelPredicateBlock wifBlock = new ModelPredicateBlock(new ProgramLocation(100, 260), Predicates.get(0));
        ModelNotBlock notBlock = new ModelNotBlock(new ProgramLocation(100, 180));
        rightBlock.setBottomPlug(whileBlock);
        whileBlock.setTopSocket(rightBlock);
        whileBlock.setCavityPlug(forwardBlock);
        forwardBlock.setTopSocket(whileBlock);
        forwardBlock.setBottomPlug(whileBlock);
        whileBlock.setCavitySocket(whileBlock);
        whileBlock.setRightSocket(notBlock);
        notBlock.setLeftPlug(whileBlock);
        notBlock.setRightSocket(wifBlock);
        wifBlock.setLeftPlug(notBlock);
        pr.initialise(rightBlock);
        pr.execute();
        pr.execute();
        UndoRedoHandler.getInstance().undo();
        pr.setState((ProgramState) UndoRedoHandler.getInstance().getState());
        assertTrue(forwardBlock.isHighlighted());
    }

    @Test
    public void undoHighlightFunctionTest() {
        ProgramRunner pr = new ProgramRunner(GW);
        ModelFunctionDefinitionBlock funcDef = new ModelFunctionDefinitionBlock(new ProgramLocation(120, 20), 0);
        ModelActionBlock forwardBlock = new ModelActionBlock(new ProgramLocation(133,143), Actions.get(0));
        ModelActionBlock forwardBlock2 = new ModelActionBlock(new ProgramLocation(133,223), Actions.get(0));
        ModelActionBlock forwardBlock3 = new ModelActionBlock(new ProgramLocation(133,303), Actions.get(0));
        funcDef.setCavityPlug(forwardBlock);
        forwardBlock.setTopSocket(funcDef);
        forwardBlock.setBottomPlug(forwardBlock2);
        forwardBlock2.setTopSocket(forwardBlock);
        forwardBlock2.setBottomPlug(forwardBlock3);
        forwardBlock3.setTopSocket(forwardBlock2);
        forwardBlock3.setBottomPlug(funcDef);
        funcDef.setCavitySocket(forwardBlock3);
        ModelFunctionCallBlock call = new ModelFunctionCallBlock(new ProgramLocation(20, 20), funcDef);
        ModelActionBlock turnright = new ModelActionBlock(new ProgramLocation(20,100), Actions.get(2));
        call.setBottomPlug(turnright);
        turnright.setTopSocket(call);
        ModelFunctionCallBlock call2 = new ModelFunctionCallBlock(new ProgramLocation(20, 180), funcDef);
        turnright.setBottomPlug(call2);
        call2.setTopSocket(turnright);
        ModelActionBlock forwardBlock4 = new ModelActionBlock(new ProgramLocation(20,260), Actions.get(0));
        call2.setBottomPlug(forwardBlock4);
        forwardBlock4.setTopSocket(call2);

        pr.initialise(call);

        for(int i = 0; i < 7; i++){
            pr.execute();
        }

        UndoRedoHandler.getInstance().undo();
        pr.setState((ProgramState) UndoRedoHandler.getInstance().getState());
        assertTrue(forwardBlock.isHighlighted());
    }

    @Test
    public void findNextBlockEmptyCallStackTest() {
        ProgramRunner pr = new ProgramRunner(GW);
        ModelFunctionDefinitionBlock funcDef = new ModelFunctionDefinitionBlock(new ProgramLocation(120, 20), 0);
        ModelActionBlock forwardBlock = new ModelActionBlock(new ProgramLocation(133,143), Actions.get(0));
        ModelActionBlock forwardBlock2 = new ModelActionBlock(new ProgramLocation(133,223), Actions.get(0));
        ModelActionBlock forwardBlock3 = new ModelActionBlock(new ProgramLocation(133,303), Actions.get(0));
        funcDef.setCavityPlug(forwardBlock);
        forwardBlock.setTopSocket(funcDef);
        forwardBlock.setBottomPlug(forwardBlock2);
        forwardBlock2.setTopSocket(forwardBlock);
        forwardBlock2.setBottomPlug(forwardBlock3);
        forwardBlock3.setTopSocket(forwardBlock2);
        forwardBlock3.setBottomPlug(funcDef);
        funcDef.setCavitySocket(forwardBlock3);
        ModelFunctionCallBlock call = new ModelFunctionCallBlock(new ProgramLocation(20, 20), funcDef);
        ModelActionBlock turnright = new ModelActionBlock(new ProgramLocation(20,100), Actions.get(2));
        call.setBottomPlug(turnright);
        turnright.setTopSocket(call);
        ModelFunctionCallBlock call2 = new ModelFunctionCallBlock(new ProgramLocation(20, 180), funcDef);
        turnright.setBottomPlug(call2);
        call2.setTopSocket(turnright);
        ModelActionBlock forwardBlock4 = new ModelActionBlock(new ProgramLocation(20,260), Actions.get(1));
        call2.setBottomPlug(forwardBlock4);
        forwardBlock4.setTopSocket(call2);

        pr.initialise(call);

        for(int i = 0; i < 7; i++){
            pr.execute();
        }

        UndoRedoHandler.getInstance().undo();
        pr.setState((ProgramState) UndoRedoHandler.getInstance().getState());
        UndoRedoHandler.getInstance().undo();
        pr.setState((ProgramState) UndoRedoHandler.getInstance().getState());

        assertTrue(call2.isHighlighted());
    }

    @Test
    public void undoSkippingBlockTest() {
        ProgramRunner pr = new ProgramRunner(GW);
        ModelFunctionDefinitionBlock funcDef = new ModelFunctionDefinitionBlock(new ProgramLocation(120, 20), 0);
        ModelActionBlock forwardBlock = new ModelActionBlock(new ProgramLocation(133,143), Actions.get(0));
        ModelActionBlock forwardBlock2 = new ModelActionBlock(new ProgramLocation(133,223), Actions.get(0));
        ModelActionBlock forwardBlock3 = new ModelActionBlock(new ProgramLocation(133,303), Actions.get(0));
        funcDef.setCavityPlug(forwardBlock);
        forwardBlock.setTopSocket(funcDef);
        forwardBlock.setBottomPlug(forwardBlock2);
        forwardBlock2.setTopSocket(forwardBlock);
        forwardBlock2.setBottomPlug(forwardBlock3);
        forwardBlock3.setTopSocket(forwardBlock2);
        forwardBlock3.setBottomPlug(funcDef);
        funcDef.setCavitySocket(forwardBlock3);
        ModelFunctionCallBlock call = new ModelFunctionCallBlock(new ProgramLocation(20, 20), funcDef);


        pr.initialise(call);

        pr.execute();
        pr.execute();
        UndoRedoHandler.getInstance().undo();
        pr.setState((ProgramState) UndoRedoHandler.getInstance().getState());
        UndoRedoHandler.getInstance().undo();
        pr.setState((ProgramState) UndoRedoHandler.getInstance().getState());
        UndoRedoHandler.getInstance().redo();
        pr.setState((ProgramState) UndoRedoHandler.getInstance().getState());
        UndoRedoHandler.getInstance().redo();
        pr.setState((ProgramState) UndoRedoHandler.getInstance().getState());

        assertTrue(forwardBlock2.isHighlighted());
    }

    @Test
    public void executeAfterRedoTest() {
        ProgramRunner pr = new ProgramRunner(GW);
        ModelFunctionDefinitionBlock funcDef = new ModelFunctionDefinitionBlock(new ProgramLocation(120, 20), 0);
        ModelActionBlock forwardBlock = new ModelActionBlock(new ProgramLocation(133,143), Actions.get(0));
        ModelFunctionCallBlock call = new ModelFunctionCallBlock(new ProgramLocation(20, 20), funcDef);
        ModelActionBlock forwardBlock2 = new ModelActionBlock(new ProgramLocation(200, 20), Actions.get(0));
        ModelActionBlock rightBlock = new ModelActionBlock(new ProgramLocation(280, 20), Actions.get(2));

        funcDef.setCavityPlug(forwardBlock);
        forwardBlock.setTopSocket(funcDef);
        forwardBlock.setBottomPlug(funcDef);
        funcDef.setCavitySocket(forwardBlock);
        call.setBottomPlug(forwardBlock2);
        forwardBlock2.setTopSocket(call);
        forwardBlock2.setBottomPlug(rightBlock);
        rightBlock.setTopSocket(forwardBlock2);

        pr.initialise(call);

        pr.execute();
        pr.execute();
        UndoRedoHandler.getInstance().undo();
        pr.setState((ProgramState) UndoRedoHandler.getInstance().getState());
        UndoRedoHandler.getInstance().undo();
        pr.setState((ProgramState) UndoRedoHandler.getInstance().getState());
        UndoRedoHandler.getInstance().redo();
        pr.setState((ProgramState) UndoRedoHandler.getInstance().getState());
        UndoRedoHandler.getInstance().redo();
        pr.setState((ProgramState) UndoRedoHandler.getInstance().getState());
        pr.execute();

        assertTrue(rightBlock.isHighlighted());
    }

    @Test
    public void doubleCallBlockTest() {
        ProgramRunner pr = new ProgramRunner(GW);
        ModelFunctionDefinitionBlock funcDef = new ModelFunctionDefinitionBlock(new ProgramLocation(120, 20), 0);
        ModelFunctionCallBlock call1 = new ModelFunctionCallBlock(new ProgramLocation(20, 20), funcDef);
        ModelFunctionCallBlock call2 = new ModelFunctionCallBlock(new ProgramLocation(20, 100), funcDef);
        ModelActionBlock forward = new ModelActionBlock(new ProgramLocation(133, 63), Actions.get(0));
        funcDef.setCavityPlug(forward);
        funcDef.setCavitySocket(forward);
        forward.setTopSocket(funcDef);
        forward.setBottomPlug(funcDef);
        call1.setBottomPlug(call2);
        call2.setTopSocket(call1);
        pr.initialise(call1);
        pr.execute();
        pr.execute();
        pr.execute();
        pr.execute();
        pr.execute();
        assertFalse(pr.isRunning());
    }

    @Test
    public void whileInDefinitionTest() {
        ProgramRunner pr = new ProgramRunner(GW);
        ModelFunctionDefinitionBlock definitionBlock = new ModelFunctionDefinitionBlock(new ProgramLocation(120, 20), 0);
        ModelActionBlock rightBlock = new ModelActionBlock(new ProgramLocation(20, 20), Actions.get(2));
        ModelFunctionCallBlock callBlock = new ModelFunctionCallBlock(new ProgramLocation(20, 100), definitionBlock);
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new ProgramLocation(133, 63), false);
        ModelActionBlock forwardBlock = new ModelActionBlock(new ProgramLocation(146, 106), Actions.get(0));
        ModelNotBlock notBlock = new ModelNotBlock(new ProgramLocation(213, 63));
        ModelPredicateBlock predicateBlock = new ModelPredicateBlock(new ProgramLocation(293, 63), Predicates.get(0));
        ModelActionBlock leftBlock = new ModelActionBlock(new ProgramLocation(20, 180), Actions.get(1));
        rightBlock.setBottomPlug(callBlock);
        callBlock.setTopSocket(rightBlock);
        callBlock.setBottomPlug(leftBlock);
        leftBlock.setTopSocket(callBlock);
        definitionBlock.setCavityPlug(whileBlock);
        whileBlock.setTopSocket(definitionBlock);
        whileBlock.setCavityPlug(forwardBlock);
        forwardBlock.setTopSocket(whileBlock);
        forwardBlock.setBottomPlug(whileBlock);
        whileBlock.setCavitySocket(forwardBlock);
        whileBlock.setRightSocket(notBlock);
        notBlock.setLeftPlug(whileBlock);
        notBlock.setRightSocket(predicateBlock);
        predicateBlock.setLeftPlug(notBlock);
        whileBlock.setBottomPlug(definitionBlock);
        definitionBlock.setCavitySocket(whileBlock);
        pr.initialise(rightBlock);
        pr.execute();
        pr.execute();
        pr.execute();
        pr.execute();
        pr.execute();
        assertFalse(pr.isRunning());
    }

    @Test
    public void programEndCallTest() {
        ProgramRunner pr = new ProgramRunner(GW);
        ModelFunctionDefinitionBlock definitionBlock = new ModelFunctionDefinitionBlock(new ProgramLocation(120, 20), 0);
        ModelActionBlock rightBlock = new ModelActionBlock(new ProgramLocation(20, 20), Actions.get(2));
        ModelFunctionCallBlock callBlock = new ModelFunctionCallBlock(new ProgramLocation(20, 100), definitionBlock);
        ModelActionBlock forwardBlock = new ModelActionBlock(new ProgramLocation(133, 63), Actions.get(0));
        rightBlock.setBottomPlug(callBlock);
        callBlock.setTopSocket(rightBlock);
        definitionBlock.setCavityPlug(forwardBlock);
        forwardBlock.setTopSocket(definitionBlock);
        forwardBlock.setBottomPlug(definitionBlock);
        definitionBlock.setCavitySocket(forwardBlock);
        pr.initialise(rightBlock);
        pr.execute();
        pr.execute();
        pr.execute();
        pr.setState((ProgramState) UndoRedoHandler.getInstance().getState());
        UndoRedoHandler.getInstance().undo();
        pr.setState((ProgramState) UndoRedoHandler.getInstance().getState());
        UndoRedoHandler.getInstance().undo();
        pr.setState((ProgramState) UndoRedoHandler.getInstance().getState());
        UndoRedoHandler.getInstance().undo();
        UndoRedoHandler.getInstance().redo();
        pr.setState((ProgramState) UndoRedoHandler.getInstance().getState());
        UndoRedoHandler.getInstance().redo();
        pr.setState((ProgramState) UndoRedoHandler.getInstance().getState());
        UndoRedoHandler.getInstance().redo();
        pr.setState((ProgramState) UndoRedoHandler.getInstance().getState());
        pr.execute();
        assertFalse(forwardBlock.isHighlighted());
    }

    @Test
    public void falseWhileInDefinitionAndCallRightAfter() {
        ProgramRunner pr = new ProgramRunner(GW);
        ModelFunctionDefinitionBlock definitionBlock1 = new ModelFunctionDefinitionBlock(new ProgramLocation(120, 20), 0);
        ModelFunctionCallBlock call1Block = new ModelFunctionCallBlock(new ProgramLocation(20, 100), definitionBlock1);
        ModelWhileIfBlock whileBlock = new ModelWhileIfBlock(new ProgramLocation(133, 63), false);
        ModelActionBlock forwardBlock = new ModelActionBlock(new ProgramLocation(146, 106), Actions.get(0));
        ModelPredicateBlock predicateBlock = new ModelPredicateBlock(new ProgramLocation(213, 63), Predicates.get(0));
        ModelFunctionDefinitionBlock definitionBlock2 = new ModelFunctionDefinitionBlock(new ProgramLocation(420, 420), 1);
        ModelFunctionCallBlock call2Block = new ModelFunctionCallBlock(new ProgramLocation(20, 180), definitionBlock2);
        ModelActionBlock leftBlock = new ModelActionBlock(new ProgramLocation(433, 463), Actions.get(1));
        ModelFunctionDefinitionBlock definitionBlock3 = new ModelFunctionDefinitionBlock(new ProgramLocation(7, 57), 2);
        ModelFunctionCallBlock call3Block = new ModelFunctionCallBlock(new ProgramLocation(420, 20), definitionBlock3);
        definitionBlock3.setCavityPlug(call1Block);
        call1Block.setTopSocket(definitionBlock3);
        call1Block.setBottomPlug(call2Block);
        call2Block.setTopSocket(call1Block);
        call2Block.setBottomPlug(definitionBlock3);
        definitionBlock1.setCavityPlug(whileBlock);
        whileBlock.setTopSocket(definitionBlock1);
        whileBlock.setCavityPlug(forwardBlock);
        forwardBlock.setTopSocket(whileBlock);
        forwardBlock.setBottomPlug(whileBlock);
        whileBlock.setCavitySocket(forwardBlock);
        whileBlock.setRightSocket(predicateBlock);
        predicateBlock.setLeftPlug(whileBlock);
        whileBlock.setBottomPlug(definitionBlock1);
        definitionBlock1.setCavitySocket(whileBlock);
        definitionBlock2.setCavityPlug(leftBlock);
        leftBlock.setTopSocket(definitionBlock2);
        leftBlock.setBottomPlug(definitionBlock2);
        definitionBlock2.setCavitySocket(leftBlock);
        pr.initialise(call3Block);
        pr.execute();
        pr.execute();
        pr.execute();
        pr.execute();
        pr.execute();
        assertFalse(pr.isRunning());
    }

    @Test
    public void ifInDefinitionWithRecursiveCallTest() {
        ProgramRunner pr = new ProgramRunner(GW);
        ModelFunctionDefinitionBlock definitionBlock = new ModelFunctionDefinitionBlock(new ProgramLocation(120, 20), 0);
        ModelActionBlock rightBlock = new ModelActionBlock(new ProgramLocation(133, 223), Actions.get(2));
        ModelFunctionCallBlock callBlock = new ModelFunctionCallBlock(new ProgramLocation(20, 100), definitionBlock);
        ModelWhileIfBlock ifBlock = new ModelWhileIfBlock(new ProgramLocation(133, 143), true);
        ModelFunctionCallBlock callBlock2 = new ModelFunctionCallBlock(new ProgramLocation(146, 106), definitionBlock);
        ModelNotBlock notBlock = new ModelNotBlock(new ProgramLocation(213, 63));
        ModelPredicateBlock predicateBlock = new ModelPredicateBlock(new ProgramLocation(293, 63), Predicates.get(0));
        ModelActionBlock forwardBlock = new ModelActionBlock(new ProgramLocation(133, 63), Actions.get(0));
        definitionBlock.setCavityPlug(forwardBlock);
        forwardBlock.setTopSocket(definitionBlock);
        forwardBlock.setBottomPlug(ifBlock);
        ifBlock.setTopSocket(forwardBlock);
        ifBlock.setCavityPlug(callBlock2);
        callBlock2.setTopSocket(ifBlock);
        callBlock2.setBottomPlug(ifBlock);
        ifBlock.setCavitySocket(callBlock2);
        ifBlock.setRightSocket(notBlock);
        notBlock.setLeftPlug(ifBlock);
        notBlock.setRightSocket(predicateBlock);
        predicateBlock.setLeftPlug(notBlock);
        ifBlock.setBottomPlug(rightBlock);
        rightBlock.setTopSocket(ifBlock);
        rightBlock.setBottomPlug(definitionBlock);
        definitionBlock.setCavitySocket(rightBlock);
        pr.initialise(callBlock);
        pr.execute();
        pr.execute();
        pr.execute();
        pr.execute();
        pr.execute();
        pr.execute();
        pr.execute();
        pr.execute();
        pr.execute();
        pr.execute();
        pr.execute();
        pr.execute();
        pr.execute();
        assertFalse(pr.isRunning());
    }
}