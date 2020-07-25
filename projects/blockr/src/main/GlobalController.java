package main;

import java.awt.*;

import ui.*;
import model.*;
import gameworldapi.*;

/**
 * The GlobalController is the heart of the program and coordinates the state flow of the program
 * The GlobalController has a respective UIController and ModelController to update the UI and model
 */
public class GlobalController {

    //Controllers
    private final ModelController modelController;
    private final UIController uiController;

    // Constructor
    public GlobalController(GameWorldType gameWorldType){
        this.modelController = new ModelController(gameWorldType);
        this.uiController = new UIController(modelController.getContent());
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
        uiController.handleMouseEvent(id, x, y);
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
     * @param isControlDown true if ctrl is down
     * @param isShiftDown true if shift is down
     * @author Bert
     */
    public void handleKeyEvent(int id, int keyCode, char keyChar, boolean isControlDown, boolean isShiftDown){
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
                        modelController.redo();
                    }
                    else{
                        modelController.undo();
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
        uiController.render(g);
    }
}