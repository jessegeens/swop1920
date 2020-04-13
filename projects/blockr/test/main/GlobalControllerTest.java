package main;


import gameworldapi.GameWorldType;
import model.blocks.*;
import org.junit.Before;
import org.junit.Test;
import utilities.*;

import javax.swing.*;

import static org.junit.Assert.*;

import java.awt.*;
import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

public class GlobalControllerTest {

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
    public void HandleMouseEvent(){
        GlobalController GC = new GlobalController(GWT);
        GC.handleMouseEvent(506, 30, 30, 1);
        assertTrue(true);
    }

    @Test
    public void HandleKeyEvent() {
        GlobalController GC = new GlobalController(GWT);
        GC.handleKeyEvent(1, 27, 'e');
        assertTrue(true);
    }
}
