package main;

import gameworldapi.GameWorldType;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class Main {

    public static void main(String[] args) {
        try{
            File file = new File(args[0]);

            //convert the file to URL format
            URL url = file.toURI().toURL();
            URL[] urls = new URL[]{url};

            //load this folder into Class loader
            ClassLoader cl = new URLClassLoader(urls);

            //load the Address class in 'c:\\other_classes\\'
            Class cls = cl.loadClass(args[1]);
            //GameWorldType worldType = ((GameWorldType) Class.forName(args[0]).newInstance());
            GameWorldType worldType = (GameWorldType) cls.newInstance();
            java.awt.EventQueue.invokeLater(() -> {
                new MyCanvasWindow("Blockr Group 5", worldType).show();
            }); }
        catch (Exception ex){
            System.out.println("Error: " + ex.getMessage().toString());
        }
    }
}
