package utilities;

import static org.junit.Assert.*;

import gameworldapi.GameWorldType;
import model.blocks.ModelWhileIfBlock;
import org.junit.*;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class UtilitiesTests {

    private GameWorldType GWT;

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
            //load the Address class in 'c:\\other_classes\\'
            Class cls = cl.loadClass("robotgameworld.RobotGameWorldType");
            //GameWorldType worldType = ((GameWorldType) Class.forName(args[0]).newInstance());
            GWT = (GameWorldType) cls.newInstance();
        }
        catch (Exception ex){
            System.out.println("Error: " + ex.getMessage().toString());
        }
    }

    @Test
    public void connectionPoint() {
        ModelWhileIfBlock mwif = new ModelWhileIfBlock(new ProgramLocation(10, 10), true);
        assertTrue(mwif.getConnectionPoints().contains(ConnectionPoint.CAVITY_PLUG));
    }


    @Test
    public void getDistanceIsZero() {
        ProgramLocation loc1 = new ProgramLocation(1, 1);
        ProgramLocation loc2 = new ProgramLocation(1, 1);
        assertEquals(0, loc1.getDistance(loc2));
    }


    @Test
    public void getVectorSum() {
        ProgramLocation loc1 = new ProgramLocation(2, 3);
        ProgramLocation loc2 = new ProgramLocation(4, 1);
        ProgramLocation loc3 = new ProgramLocation(6, 4);
        assertEquals(loc3, loc1.add(loc2));
    }

    @Test
    public void getComponentSum() {
        ProgramLocation loc1 = new ProgramLocation(2, 3);
        assertEquals(3, loc1.add(2, 0).getY());
    }
}