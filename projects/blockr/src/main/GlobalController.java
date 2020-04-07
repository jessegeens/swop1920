package main;

import java.util.ArrayList;
import java.awt.*;

import ui.*;
import utilities.*;
import model.*;
import model.blocks.*;
//import model.blocks.plugs.*;

/**
 * The GlobalController is the heart of the program and coordinates the state flow of the program
 * The GlobalController has a respective UIController and ModelController to update the UI and model
 */
public class GlobalController {

    //Controllers
    private ModelController modelController;
    private UIController uiController;


    // Constructor
    public GlobalController(){
        this.modelController = new ModelController();
        System.out.println(modelController.getModelBlocks());
        this.uiController = new UIController(MyCanvasWindow.WIDTH, MyCanvasWindow.HEIGHT);
    }
    
    /**
     * This function handles mouse events and passes them on to the model- and UIController
     */
    public void handleMouseEvent(int id, int x, int y, int clickCount){
        Location eventWindowLocation = new Location(x,y);

        switch(id){
            case 501: //MOUSE_PRESSED
                //return the topmost active block if one is in the click location
                //you remove it from the local list in PArea until mouseup
                System.out.println("MOUSE PRESSED start");
                this.modelController.select(eventWindowLocation);
                break;
            case 502: //MOUSE RELEASED
                System.out.println("MOUSE RELEASED start");
                this.modelController.release(eventWindowLocation);
                break;
            case 506: //MOUSE_DRAGGED
            System.out.println("MOUSE MOVED start");
                this.modelController.move(eventWindowLocation);
                break;
            default:
                break;
        }


        //this.modelController.handleMouseEvent(id, eventWindowLocation, clickCount);
    }

        /**
     * This function propagates 
     * 
     * @param id id of the event
     * @param keyCode keyCode of the pressed key: - 27  = ESC
     *              see: http://keycode.info      - 65  = A
     *                                            - 116 = F5 
     * @param keyChar character of the pressed key
     */
    public void handleKeyEvent(int id, int keyCode, char keyChar){
        this.modelController.handleKeyEvent(id, keyCode, keyChar);
    }

    /**
     * This function renders the UI
     * 
     * @param g the graphics object which the rendering uses
     */
    public void render(Graphics g){
        uiController.render(g, modelController.getState(), modelController.getBlockStates());
    }

}