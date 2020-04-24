package usecases;

import gameworldapi.GameWorldType;
import model.ModelController;
import org.junit.Before;
import org.junit.Test;
import utilities.ProgramLocation;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

public class StartStepExitExecutionTests {

    private ModelController mC;

    @Before
    public void setUp() throws Exception {
        try {
            File file = new File("C:" + File.separator + "Users" + File.separator + "Aram" + File.separator + "Desktop" + File.separator + "Informatica - Computerwetenschappen" + File.separator + "Ba Inf" + File.separator + "4ba informatica" + File.separator + "software-ontwerp" + File.separator + "swop1920" + File.separator + "projects" + File.separator + "robotgameworld" + File.separator + "out" + File.separator + "production" + File.separator + "robotgameworld");
            System.out.println(file.toString());
            //convert the file to URL format
            URL url = file.toURI().toURL();
            URL[] urls = new URL[]{url};
            //load this folder into Class loader
            ClassLoader cl = new URLClassLoader(urls);
            //load the Address class in 'cls:\\other_classes\\'
            Class cls = cl.loadClass("robotgameworld.RobotGameWorldType");
            //GameWorldType worldType = ((GameWorldType) Class.forName(args[0]).newInstance());
            GameWorldType GWT = (GameWorldType) cls.newInstance();
            mC = new ModelController(GWT);
        }
        catch (Exception ex){
            System.out.println("Error: " + ex.getMessage().toString());
        }
    }

    @Test
    public void startExecution() {
        mC.select(new ProgramLocation(190,150));
        mC.release(new ProgramLocation(420, 420));
        mC.select(new ProgramLocation(190,150));
        mC.release(new ProgramLocation(420, 500));
        mC.startOrExecuteProgram();
        assertTrue(mC.getProgramAreaBlocks().get(0).isHighlighted());
    }

    @Test
    public void stepExecution() {
        mC.select(new ProgramLocation(190,150));
        mC.release(new ProgramLocation(420, 420));
        mC.select(new ProgramLocation(190,150));
        mC.release(new ProgramLocation(420, 500));
        mC.startOrExecuteProgram();
        mC.startOrExecuteProgram();
        assertTrue(mC.getProgramAreaBlocks().get(1).isHighlighted());
    }

    @Test
    public void stopExecution() {
        mC.select(new ProgramLocation(190,150));
        mC.release(new ProgramLocation(420, 420));
        mC.select(new ProgramLocation(190,150));
        mC.release(new ProgramLocation(420, 500));
        mC.startOrExecuteProgram();
        mC.startOrExecuteProgram();
        mC.startOrExecuteProgram();
        assertFalse(mC.getProgramAreaBlocks().get(1).isHighlighted());
    }
}
