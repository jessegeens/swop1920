package main;

import java.util.ArrayList;
import java.awt.*;

import ui.*;
import utilities.*;
import model.*;
import model.blocks.*;
import model.blocks.plugs.*;

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
        this.uiController = new UIController(MyCanvasWindow.WIDTH, MyCanvasWindow.HEIGHT, modelController.getModelBlocks(), ProgramRunner.getInitialState());
    }
    
    /**
     * This function handles mouse events and passes them on to the model- and UIController
     */
    public void handleMouseEvent(int id, int x, int y, int clickCount){
        WindowLocation eventWindowLocation = new WindowLocation(x,y);
        this.modelController.handleMouseEvent(id, eventWindowLocation, clickCount);
        this.uiController.updateBlocks(this.modelController.getModelBlocks());

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
        this.getModelController().handleKeyEvent(id, keyCode, keyChar);
    }

    /**
     * This function renders the UI
     * 
     * @param g the graphics object which the rendering uses
     */
    public void render(Graphics g){
        uiController.render(g);
    }

    /**
     * @return the modelController
     */
    public ModelController getModelController() {
        return this.modelController;
    }

    /**
     * @return the uiController
     */
    public UIController getUIController(){
        return this.uiController;
    }
}