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

import static org.junit.Assert.*;

public class ProgramRunnerTest {

    GameWorld GW;
    ArrayList<PredicateType> Predicates;
    ArrayList<ActionType> Actions;

    @Before
    public void setUp() throws Exception {
        try {
            File file = new File("/home/oberon/Documents/Studies/SWOP/swop1920/projects/robotgameworld/out/production/robotgameworld/");
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
        ProgramRunner PR = new ProgramRunner(GW);
        ModelPredicateBlock predicateBlock = new ModelPredicateBlock(new ProgramLocation(100, 260), Predicates.get(0));
        ModelNotBlock notBlock = new ModelNotBlock(new ProgramLocation(100, 180));
        ModelActionBlock startBlock = new ModelActionBlock(new ProgramLocation(20, 100), Actions.get(0));
        ModelWhileIfBlock ifBlock = new ModelWhileIfBlock(new ProgramLocation(100,100), true);
        ModelActionBlock forwardBlock = new ModelActionBlock(new ProgramLocation(113,143), Actions.get(0));
        ModelActionBlock leftBlock = new ModelActionBlock(new ProgramLocation(113, 223), Actions.get(0));
        ModelActionBlock rightBlock = new ModelActionBlock(new ProgramLocation(113, 303), Actions.get(0));
        ModelActionBlock finishBlock = new ModelActionBlock(new ProgramLocation(100, 420), Actions.get(0));
        ifBlock.setRightSocket(notBlock);
        notBlock.setLeftPlug(ifBlock);
        notBlock.setRightSocket(predicateBlock);
        predicateBlock.setLeftPlug(ifBlock);
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