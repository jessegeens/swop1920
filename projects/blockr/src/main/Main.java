package main;
import gameworldapi.*;

import java.lang.reflect.Constructor;

class Main {
    /**
     * main function of the program
     * @param {String[]} args list of arguments to pass on to the program
     */
    public static void main(String[] args) {
        try{
            GameWorldType worldType = ((GameWorldType) Class.forName(args[0]).newInstance());
            java.awt.EventQueue.invokeLater(() -> {
                new MyCanvasWindow("Blockr Group 5", worldType).show();
            }); }
        catch (Exception ex){
            System.out.println("Error: " + ex.getMessage().toString());
        }
    }
}
