package main;

import java.util.ArrayList;
import java.awt.*;

import ui.*;
import utilities.*;
import model.*;
import model.blocks.*;

/**
 * The GlobalController is the heart of the program and coordinates the state flow of the program
 * The GlobalController has a respective UIController and ModelController to update the UI and model
 */
public class GlobalController {

    //Program parameters
    private final int MAX_BLOCKS = 15;
    private final int CELL_SIZE = 50;
    private final Location GOAL_CELL = new Location(5, 5);
    private final Location ROBOT_START_LOCATION = new Location(0, 0);
    private final Direction ROBOT_START_DIRECTION = new Direction(Direction.RIGHT);

    //Controllers
    private ModelController modelController;
    private UIController uiController;

    //Execution state
    private boolean running;
    private ModelBlock current;

    // Constructor
    public GlobalController(){
        GridInfo gridInfo = new GridInfo(GOAL_CELL, new ArrayList<Location>(), ROBOT_START_LOCATION, ROBOT_START_DIRECTION, CELL_SIZE);
        this.modelController = new ModelController(gridInfo, this.MAX_BLOCKS, this.CELL_SIZE);
        System.out.println(modelController.getModelBlocks());
        this.uiController = new UIController(MyCanvasWindow.WIDTH, MyCanvasWindow.HEIGHT, modelController.getModelBlocks(), gridInfo);
        this.running = false;
        this.current = null;
    }
    
    /**
     * This function handles mouse events and passes them on to the model- and UIController
     */
    public void handleMouseEvent(int id, int x, int y, int clickCount){
        Location eventLocation = new Location(x,y);
        this.modelController.handleMouseEvent(id, eventLocation, clickCount);
        this.uiController.updateBlocks(this.modelController.getModelBlocks());

    }

    /**
     * This function handles key events by telling the model controller
     * to either step through the execution or stop running the program
     * 
     * @param {int} id id of the event
     * @param {int} keyCode keyCode of the pressed key: - 27  = ESC
     *              see: http://keycode.info            - 65  = A
     *                                                  - 116 = F5 
     * @param {char} keyChar character of the pressed key
     */
    public void handleKeyEvent(int id, int keyCode, char keyChar){
        System.out.println("key pressed");
        switch(keyCode){
            case 65: //A;
                if (this.modelController.getPWindow().allBlocksConnected()) execute();//Check if all blocks are connected, and if so execute.
                break;
            case 116: //F5;
                if (this.modelController.getPWindow().allBlocksConnected()) execute();//Check if all blocks are connected, and if so execute.
                break;
            
            case 27: //Esc
                this.stopRunning();
                break;
            default:
                this.uiController.updateBlocks(this.modelController.getModelBlocks());
                break;
        }
        //TODO create function in modelcontroller
        
    }

    /**
     * This function starts the execution
     * 1. this function starts the program if it is not running yet,
     * 2. then it steps,
     * 3. then it checks whether it is finished
     *  3a. if necessary, the program stops running
     *  3b. otherwise, the next block is highlighted
     */
    public void execute(){
        if(!(isRunning())){
            startRunning();
            setCurrent(this.modelController.getPWindow().getStartBlock());
        }
        System.out.println("now executing: " + getCurrent().getBlockType().getType());
        step();
        if(this.getCurrent().equals(this.getModelController().getPWindow().getFinishBlock())) stopRunning();
        else{
            if(!this.getCurrent().equals(this.getModelController().getPWindow().getFinishBlock())) this.highlightNext();
            this.setCurrent(findNextBlock());
        }
    }

    /**
     * This function executes one step of the program
     */
    public void step(){
        switch(getCurrent().getBlockType().getType()){
            case(Blocktype.MOVEFORWARD):
            this.modelController.getGrid().robotForward();
            GridInfo gridInfo = this.getModelController().getGrid().getGridInfo();
            this.getUIController().updateGrid(gridInfo);
            break;
            case(Blocktype.TURNLEFT):
                this.modelController.getGrid().robotTurnLeft();
                break;
            case(Blocktype.TURNRIGHT):
                this.modelController.getGrid().robotTurnRight();
                break;
        }
    }


    /**
     * This function renders the UI
     * 
     * @param {Graphics} g the graphics object which the rendering uses
     */
    public void render(Graphics g){
        uiController.render(g);
    }

    /**
     * 
     * @return whether the program is currently running
     */
    public boolean isRunning(){
        return this.running;
    }

    /**
     * This function tells the globalController that the program will start running
     */
    public void startRunning(){
        this.running = true;
    }

    /**
     * This function tells the globalController that the program will stop running
     * It also resets the grid to its initial state and unhighlights the last executed block
     */
    public void stopRunning(){
        this.running = false;
        if(!this.getCurrent().equals(this.getModelController().getPWindow().getFinishBlock())) findNextBlock().setUnHighlight();
        else this.getCurrent().setUnHighlight();
        this.getModelController().setGrid(new ModelGrid(MyCanvasWindow.WIDTH/3, MyCanvasWindow.HEIGHT, GOAL_CELL, ROBOT_START_LOCATION, ROBOT_START_DIRECTION, new ArrayList<Location>(), CELL_SIZE));
        this.getCurrent().setUnHighlight();
    }

    /**
     * 
     * @return the current active modelBlock
     */
    public ModelBlock getCurrent(){
        return this.current;
    }

    /**
     * Updates the current active modelBlock
     * 
     * @param {ModelBlock} blk the new active modelBlock
     */
    public void setCurrent(ModelBlock blk){
        this.current = blk;
    }

    /**
     * Highlights the next block in the program and unhighlight the currrent one
     */
    public void highlightNext(){
        getCurrent().setUnHighlight();
        findNextBlock().setHighlight();
    }

    /**
     * Returns the bottomplug if it is a normal block (move block) or if the condition of a WhileIf block fails 
     * If the condition of a WhileIfBlock succeeds it gives the first cavity block of the WhileIf block.
     * 
     * TODO: next block in execution // Maybe explain what is meant with this?
     * 
     * @return the next block that will be run in the program
     */
    public ModelBlock findNextBlock(){
        if ((getCurrent().getBlockType().getType() == Blocktype.IF) || (getCurrent().getBlockType().getType() == Blocktype.WHILE)){
            if (evaluateCurrentCondition()){
                return ((ModelWhileIfBlock)getCurrent()).getCavitySocket();
            }
        }
        return ((BottomPlug)getCurrent()).getBottomPlug();
    }

    /**
     * 
     * @return whether the current condition is true or false
     */
    public boolean evaluateCurrentCondition(){
        boolean negate = false;
        if (getCurrent().hasRightSocket()){
            ModelBlock cond = ((RightSocket)getCurrent()).getRightSocket();
            while (cond.hasRightSocket()){
                if(cond instanceof ModelNotBlock){
                    negate = !negate;
                    cond = ((ModelNotBlock)cond).getRightSocket();
                }
                else if((cond instanceof ModelWallInFrontBlock) && this.modelController.getGrid().wallInFrontOfRobot()){
                    return !negate;
                }
            }
        } 
        return negate;
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