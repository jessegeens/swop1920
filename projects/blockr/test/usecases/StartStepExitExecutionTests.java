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
            File file = new File("/home/oberon/Documents/Studies/SWOP/swop1920/projects/robotgameworld/out/production/robotgameworld/");
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
        mC.newSelect(new ProgramLocation(190,150));
        mC.newRelease(new ProgramLocation(420, 420));
        mC.newSelect(new ProgramLocation(190,150));
        mC.newRelease(new ProgramLocation(420, 500));
        mC.startOrExecuteProgram();
        assertTrue(mC.getProgramAreaBlocks().get(0).isHighlighted());
    }

    @Test
    public void stepExecution() {
        mC.newSelect(new ProgramLocation(190,150));
        mC.newRelease(new ProgramLocation(420, 420));
        mC.newSelect(new ProgramLocation(190,150));
        mC.newRelease(new ProgramLocation(420, 500));
        mC.startOrExecuteProgram();
        mC.startOrExecuteProgram();
        assertTrue(mC.getProgramAreaBlocks().get(1).isHighlighted());
    }

    @Test
    public void stopExecution() {
        mC.newSelect(new ProgramLocation(190,150));
        mC.newRelease(new ProgramLocation(420, 420));
        mC.newSelect(new ProgramLocation(190,150));
        mC.newRelease(new ProgramLocation(420, 500));
        mC.startOrExecuteProgram();
        mC.startOrExecuteProgram();
        mC.startOrExecuteProgram();
        assertFalse(mC.getProgramAreaBlocks().get(1).isHighlighted());
    }
}
