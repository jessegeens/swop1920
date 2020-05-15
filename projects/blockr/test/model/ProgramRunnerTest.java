package model;

import gameworldapi.*;
import model.blocks.*;
import org.junit.Before;
import org.junit.Test;
import utilities.ProgramLocation;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;

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
    public void reset() {
        ProgramRunner PR = new ProgramRunner(GW);
        ModelBlock block = new ModelNotBlock(new ProgramLocation(100, 100));
        PR.initialise(block, new ArrayList<>());
        PR.reset();
        assertFalse(block.isHighlighted());
    }

    @Test
    public void execute() {
        ProgramRunner PR = new ProgramRunner(GW);
        ModelActionBlock rightBlock = new ModelActionBlock(new ProgramLocation(100, 100), Actions.get(0));
        ModelActionBlock leftBlock = new ModelActionBlock(new ProgramLocation(100 ,180), Actions.get(0));
        rightBlock.setBottomPlug(leftBlock);
        leftBlock.setTopSocket(rightBlock);
        PR.initialise(rightBlock, new ArrayList<>());
        PR.execute();
        assertTrue(leftBlock.isHighlighted());
    }

    @Test
    public void executeIf(){
        ProgramRunner PR = new ProgramRunner(GW);
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
        ArrayList<ModelFunctionDefinitionBlock> empty = new ArrayList<>();
        PR.initialise(ifBlock, empty);
        PR.execute();
        PR.execute();
        PR.execute();
        assertTrue(finishBlock.isHighlighted());
    }

    @Test
    public void executeEnd(){
        ProgramRunner PR = new ProgramRunner(GW);
        ModelActionBlock forwardBlock = new ModelActionBlock(new ProgramLocation(113,143), Actions.get(0));
        ModelActionBlock leftBlock = new ModelActionBlock(new ProgramLocation(113, 223), Actions.get(0));
        ModelActionBlock rightBlock = new ModelActionBlock(new ProgramLocation(113, 303), Actions.get(0));
        forwardBlock.setBottomPlug(leftBlock);
        leftBlock.setTopSocket(forwardBlock);
        leftBlock.setBottomPlug(rightBlock);
        rightBlock.setTopSocket(leftBlock);
        ArrayList<ModelFunctionDefinitionBlock> empty = new ArrayList<>();
        PR.initialise(forwardBlock, empty);
        PR.execute();
        PR.execute();
        PR.execute();
        PR.execute();
        assertFalse(PR.isRunning());
    }

    @Test
    public void executeWhileNotWIF(){
        ProgramRunner PR = new ProgramRunner(GW);
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
        ArrayList<ModelFunctionDefinitionBlock> empty = new ArrayList<>();
        PR.initialise(rightBlock, empty);
        PR.execute();
        PR.execute();
        PR.execute();
        assertFalse(PR.isRunning());
    }

    @Test
    public void undoBlock() {
        ProgramRunner pR = new ProgramRunner(GW);
        ModelActionBlock forwardBlock1 = new ModelActionBlock(new ProgramLocation(100,140), Actions.get(0));
        ModelActionBlock forwardBlock2 = new ModelActionBlock(new ProgramLocation(100,180), Actions.get(0));
        forwardBlock1.setBottomPlug(forwardBlock2);
        forwardBlock2.setTopSocket(forwardBlock1);
        ArrayList<ModelFunctionDefinitionBlock> empty = new ArrayList<>();
        pR.initialise(forwardBlock1, empty);
        pR.execute();
        pR.execute();
        pR.undoProgramRunner();
        pR.undoProgramRunner();
        assertTrue(forwardBlock1.isHighlighted());
    }

    @Test
    public void redoBlock() {
        ProgramRunner pR = new ProgramRunner(GW);
        ModelActionBlock forwardBlock1 = new ModelActionBlock(new ProgramLocation(100,140), Actions.get(0));
        ModelActionBlock forwardBlock2 = new ModelActionBlock(new ProgramLocation(100,180), Actions.get(0));
        forwardBlock1.setBottomPlug(forwardBlock2);
        forwardBlock2.setTopSocket(forwardBlock1);
        ArrayList<ModelFunctionDefinitionBlock> empty = new ArrayList<>();
        pR.initialise(forwardBlock1, empty);
        pR.execute();
        pR.execute();
        pR.undoProgramRunner();
        pR.undoProgramRunner();
        pR.redoProgramRunner();
        assertTrue(forwardBlock2.isHighlighted());
    }

    @Test
    public void redoWhileBlock() {
        ProgramRunner PR = new ProgramRunner(GW);
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
        ArrayList<ModelFunctionDefinitionBlock> empty = new ArrayList<>();
        PR.initialise(rightBlock, empty);
        PR.execute();
        PR.execute();
        PR.undoProgramRunner();
        assertTrue(forwardBlock.isHighlighted());
    }

    @Test
    public void undoHighlightFunction() throws Exception {
        setUp();
        ProgramRunner programRunner = new ProgramRunner(GW);
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
        ModelFunctionCallBlock call = new ModelFunctionCallBlock(new ProgramLocation(20, 20), 0);
        ModelActionBlock turnright = new ModelActionBlock(new ProgramLocation(20,100), Actions.get(2));
        call.setBottomPlug(turnright);
        turnright.setTopSocket(call);
        ModelFunctionCallBlock call2 = new ModelFunctionCallBlock(new ProgramLocation(20, 180), 0);
        turnright.setBottomPlug(call2);
        call2.setTopSocket(turnright);
        ModelActionBlock forwardBlock4 = new ModelActionBlock(new ProgramLocation(20,260), Actions.get(0));
        call2.setBottomPlug(forwardBlock4);
        forwardBlock4.setTopSocket(call2);

        programRunner.initialise(call, new ArrayList<>(Arrays.asList(funcDef)));

        for(int i = 0; i < 7; i++){
            programRunner.execute();
        }

        programRunner.undoProgramRunner();//undo first time seems to have to undo twice to work.
        assertTrue(forwardBlock.isHighlighted());
    }

    @Test
    public void findNextBlockEmptyCallStack() throws Exception {
        setUp();
        ProgramRunner programRunner = new ProgramRunner(GW);
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
        ModelFunctionCallBlock call = new ModelFunctionCallBlock(new ProgramLocation(20, 20), 0);
        ModelActionBlock turnright = new ModelActionBlock(new ProgramLocation(20,100), Actions.get(2));
        call.setBottomPlug(turnright);
        turnright.setTopSocket(call);
        ModelFunctionCallBlock call2 = new ModelFunctionCallBlock(new ProgramLocation(20, 180), 0);
        turnright.setBottomPlug(call2);
        call2.setTopSocket(turnright);
        ModelActionBlock forwardBlock4 = new ModelActionBlock(new ProgramLocation(20,260), Actions.get(1));
        call2.setBottomPlug(forwardBlock4);
        forwardBlock4.setTopSocket(call2);

        programRunner.initialise(call, new ArrayList<>(Arrays.asList(funcDef)));

        for(int i = 0; i < 7; i++){
            programRunner.execute();
        }

        programRunner.undoProgramRunner();
        programRunner.undoProgramRunner();

        assertTrue(call2.isHighlighted());
    }

    @Test
    public void undoSkippingBlock() throws Exception {
        setUp();
        ProgramRunner programRunner = new ProgramRunner(GW);
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
        ModelFunctionCallBlock call = new ModelFunctionCallBlock(new ProgramLocation(20, 20), 0);


        programRunner.initialise(call, new ArrayList<>(Arrays.asList(funcDef)));

        programRunner.execute();
        programRunner.execute();
        programRunner.undoProgramRunner();
        programRunner.undoProgramRunner();
        programRunner.redoProgramRunner();

        assertTrue(forwardBlock.isHighlighted());
    }

    @Test
    public void executeAfterRedo() throws Exception {
        setUp();
        ProgramRunner programRunner = new ProgramRunner(GW);
        ModelFunctionDefinitionBlock funcDef = new ModelFunctionDefinitionBlock(new ProgramLocation(120, 20), 0);
        ModelActionBlock forwardBlock = new ModelActionBlock(new ProgramLocation(133,143), Actions.get(0));
        ModelFunctionCallBlock call = new ModelFunctionCallBlock(new ProgramLocation(20, 20), 0);
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

        programRunner.initialise(call, new ArrayList<>(Arrays.asList(funcDef)));

        programRunner.execute();
        programRunner.execute();
        programRunner.undoProgramRunner();
        programRunner.undoProgramRunner();
        programRunner.redoProgramRunner();
        programRunner.redoProgramRunner();
        programRunner.execute();

        assertTrue(rightBlock.isHighlighted());
    }
}