package main;

import java.awt.*;
import java.util.ArrayList;

import ui.*;
import utilities.*;
import model.*;
import gameworldapi.*;

/**
 * The GlobalController is the heart of the program and coordinates the state flow of the program
 * The GlobalController has a respective UIController and ModelController to update the UI and model
 */
public class GlobalController {

    //Controllers
    private ModelController modelController;
    private UIController uiController;



    // Constructor
    public GlobalController(GameWorldType gameWorldType){
        this.modelController = new ModelController(gameWorldType);
        this.uiController = new UIController(MyCanvasWindow.WIDTH, MyCanvasWindow.HEIGHT);

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
                this.modelController.select(eventWindowLocation);
                break;
            case 502: //MOUSE_RELEASED
                this.modelController.release(eventWindowLocation);
                break;
            case 506: //MOUSE_DRAGGED
                this.modelController.drag(eventWindowLocation);
                break;
            default:
                break;
        }


        //this.modelController.handleMouseEvent(id, eventWindowLocation, clickCount);
    }

    /**
     * This function handles key events by telling the model controller
     * to either step through the execution or stop running the program
     *
     *
     * @param id id of the event
     *                              400 pressed
     *                              401 released
     * @param keyCode keyCode of the pressed key: - 27  = ESC
     *              see: http://keycode.info      - 65  = A
     *                                            - 116 = F5
     *                                            - 17 = Ctrl
     *                                            - 16 = Shift
     *                                            - 90 = Z
     * @param keyChar character of the pressed key
     *
     * @author
     *
     *
     *
     */
    public void handleKeyEvent(int id, int keyCode, char keyChar, boolean isControlDown, boolean isShiftDown){
        System.out.println("KEYEVENT");
        System.out.println(keyCode);
        System.out.println(keyChar);
        System.out.println(id);
        System.out.println(isControlDown);
        switch(keyCode) {
            case 65: //A;
            case 116: //F5;
                this.modelController.startOrExecuteProgram();
                break;
            case 27: //Esc
                this.modelController.exitExecution();
                break;
            case 90: //Z
                if(isControlDown){
                    if(isShiftDown){
                        this.modelController.globalRedo();
                    }
                    else{
                        this.modelController.globalUndo();
                    }
                }
                break;
        }
    }

    /**
     * This function renders the UI
     * 
     * @param g the graphics object which the rendering uses
     */
    public void render(Graphics g){
        ProgramLocation gridLocation = new ProgramLocation(MyCanvasWindow.WIDTH*2/3, 0);
        modelController.getGameWorld().render(g, gridLocation.getX(), gridLocation.getY());
        uiController.render(g, modelController.getBlockStates());
    }
}