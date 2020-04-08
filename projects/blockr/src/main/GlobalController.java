package main;

import java.awt.*;
import java.util.ArrayList;

import ui.*;
import utilities.*;
import model.*;
import gameworldapi.*;
//import model.blocks.plugs.*;

/**
 * The GlobalController is the heart of the program and coordinates the state flow of the program
 * The GlobalController has a respective UIController and ModelController to update the UI and model
 */
public class GlobalController {

    //Controllers
    private ModelController modelController;
    private UIController uiController;
    private GameWorld gameWorld;
    private ArrayList<ActionType> actions;
    private ArrayList<PredicateType> predicates;

    // Constructor
    public GlobalController(GameWorldType gameWorldType){
        this.modelController = new ModelController();
        //System.out.println(modelController.getModelBlocks());
        this.uiController = new UIController(MyCanvasWindow.WIDTH, MyCanvasWindow.HEIGHT);
        this.gameWorld = gameWorldType.newWorldInstance();
        this.actions = gameWorldType.getSupportedActions();
        this.predicates = gameWorldType.getSupportedPredicates();
    }
    
    /**
     * This function handles mouse events and passes them on to the model- and UIController
     * 
     * @param id id of the event: - 500 = MOUSE_CLICKED: Press + release (comes after released + pressed), only comes if no dragging happended
     *                                  - 501 = MOUSE_PRESSED: Where you start holding the button down
     *                                  - 502 = MOUSE_RELEASED: Where you release the button
     *                                  - 506 = MOUSE_DRAGGED: Holding down, gets triggerd after each small move
     * 
     * @param x xCor of event
     * @param y yCor of event
     * @param clickCount amount of clicks (not used atm)
     * 
     * @author Bert
     */
    public void handleMouseEvent(int id, int x, int y, int clickCount){
        ProgramLocation eventWindowLocation = new ProgramLocation(x,y);

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
                this.modelController.drag(eventWindowLocation);
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
        //ProgramLocation gridLocation = new ProgramLocation(MyCanvasWindow.WIDTH*2/3, 0);
        //gameWorld.render(g, gridLocation);
        uiController.render(g, modelController.getState(), modelController.getBlockStates());
    }

}