package usecases;
import gameworldapi.GameWorldType;
import main.GlobalController;
import model.ModelController;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

import static org.junit.Assert.assertEquals;

public class AddMoveRemoveBlockUseCaseTests {

    private GameWorldType GWT;
    private GlobalController gC;

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
    public void addBlock() {
        gC = new GlobalController(GWT);
        ModelController mC = new ModelController(GWT);
        gC.handleMouseEvent(501, 30, 30, 1);
        gC.handleMouseEvent(502, 420, 420, 1);
    }
}
