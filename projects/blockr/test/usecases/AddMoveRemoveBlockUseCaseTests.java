package usecases;
import gameworldapi.GameWorldType;
import model.ModelController;
import org.junit.Before;
import org.junit.Test;
import utilities.ProgramLocation;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AddMoveRemoveBlockUseCaseTests {

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
    public void addBlock() {
        mC.select(new ProgramLocation(30,30));
        mC.release(new ProgramLocation(420, 420));
        assertEquals(1,mC.getProgramAreaBlocks().size());
    }

    @Test
    public void moveBlock() {
        mC.select(new ProgramLocation(30,30));
        mC.release(new ProgramLocation(420, 420));
        mC.select(new ProgramLocation(430,430));
        mC.drag(new ProgramLocation(450, 450));
        assertEquals(new ProgramLocation(450,450), mC.getActiveBlock().getPos());
    }

    @Test
    public void connectBlock() {
        mC.select(new ProgramLocation(30,30));
        mC.release(new ProgramLocation(420, 420));
        mC.select(new ProgramLocation(30,30));
        mC.release(new ProgramLocation(420, 500));
        assertEquals(mC.getProgramAreaBlocks().get(0), mC.getProgramAreaBlocks().get(1).getConnections().get(0));
    }

    @Test
    public void disconnectBlock() {
        mC.select(new ProgramLocation(30,30));
        mC.release(new ProgramLocation(420, 420));
        mC.select(new ProgramLocation(30,30));
        mC.release(new ProgramLocation(420, 500));
        mC.select(new ProgramLocation(430,510));
        mC.release(new ProgramLocation(600, 600));
        assertTrue(mC.getProgramAreaBlocks().get(0).getConnections().isEmpty());
    }

    @Test
    public void removeBlock() {
        mC.select(new ProgramLocation(30,30));
        mC.release(new ProgramLocation(420, 420));
        mC.select(new ProgramLocation(460,460));
        mC.release(new ProgramLocation(30, 30));
        assertTrue(mC.getProgramAreaBlocks().isEmpty());
    }

}
