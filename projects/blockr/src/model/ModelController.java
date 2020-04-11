package model;

import java.util.ArrayList;
import java.util.Stack;


import gameworldapi.GameWorld;
import gameworldapi.GameWorldType;
import main.MyCanvasWindow;
import model.Actions.Action;
import model.Actions.CreateAction;
import model.Actions.DeleteAction;
import model.Actions.MoveAction;
import model.blocks.ModelBlock;
import ui.BlockState;
import utilities.*;



/**
 * A controller that controls the changes in the logic of the Blockr system and propagates it to the view.
 */
public class ModelController{

    private ModelPalette palette;
    private ModelProgramArea PArea;
    private ProgramRunner programRunner;
    private GameWorld gameWorld;

    private ModelBlock active = null;


    // Constructor
    public ModelController(GameWorldType worldType){
        //palette left, program middle, grid right
        palette = new ModelPalette(worldType.getSupportedActions(), worldType.getSupportedPredicates());
        PArea = new ModelProgramArea();
        this.gameWorld = worldType.newWorldInstance();
        //state = ProgramState.getInitialState();
        programRunner = new ProgramRunner(this.gameWorld);
    }


    /**
     * This function handles key events by telling the model controller
     * to either step through the execution or stop running the program
     *
     * TODO: propagate to modelController
     *
     * @param id id of the event
     * @param keyCode keyCode of the pressed key: - 27  = ESC
     *              see: http://keycode.info      - 65  = A
     *                                            - 116 = F5
     * @param keyChar character of the pressed key
     */
    public void handleKeyEvent(int id, int keyCode, char keyChar){
        switch(keyCode){
            case 65: //A;
            case 116: //F5;
                if (PArea.validExecutionState()){//Check if all blocks are connected, and if so execute.
                    if(programRunner.isRunning()){
                        programRunner.execute();
                    } else {
                        programRunner.initialise(PArea.getFirstBlock());
                    }
                }
                break;
            case 27: //Esc
                programRunner.reset();
                break;
        }
    }


    /**
     * Try to start the program or do the next step in program execution
     *
     * @author Bert (refactored from old handleKeyEvent)
     *
     */
    public void startOrExecuteProgram(){
        if (PArea.validExecutionState()){//Check if all blocks are connected, and if so execute.
            if(programRunner.isRunning()){
                programRunner.execute();
            } else {
                programRunner.initialise(PArea.getFirstBlock());
            }
        }

    }

    /**
     * Stop program execution and reset the game (if the program was running)
     *
     * @author Bert (refactored from old handleKeyEvent)
     *
     */
    public void exitExecution(){
        programRunner.reset();
    }





    public Stack<Action> undoStack = new Stack();

    public Stack<Action> redoStack = new Stack();

    /**
     * Undo the block or game steps
     *
     * @author Bert
     *
     * Still WIP
     *
     */
    public void undo(){

        System.out.println("UNDO");
        if(!undoStack.empty()){
            Action current = undoStack.pop();
            current.undo();
            System.out.println("current");
            System.out.println(current);
            redoStack.push(current);
        }




        //System.out.println("UNDO");


    }

    //sequentieel nieuwe blokken maken zorgt voor issues
    //meer dan een verplaatsing reverten

    /**
     * Redo the block or game steps
     *
     * @author Bert
     *
     * Still WIP
     *
     */
    public void redo(){

        System.out.println("REDO");
        if(!redoStack.empty()){
            Action current = redoStack.pop();
            current.redo();
            //TODO see below (necessary or not)
            redoStack.push(current);
        }


        //System.out.println("REDO");


    }



    /**
     * 
     * @param location
     * @return whether this event location is inside of the Palette
     * 
     * @author Bert
     */
    protected boolean inPalette(ProgramLocation location){
        if(location.getX() >= 0 && location.getX() < MyCanvasWindow.WIDTH/3 ){
            return true;
        }
        return false;
    }

    /**
     * 
     * @param location
     * @return whether this event location is inside of the ProgramArea
     * 
     * @author Bert
     */
    protected boolean inProgramArea(ProgramLocation location){
        if(location.getX() > MyCanvasWindow.WIDTH/3 && location.getX() <  2 * MyCanvasWindow.WIDTH/3){
            return true;
        }
        return false;
    }


    ProgramLocation oldPos = null;
    boolean newBlockCreated = false;

    /**
     * Handle a possible block selection (if the position is inbounds of a block)
     * 
     * If it's selected in the palette, a new block gets selected and a new block of this type gets created in the palette
     * If it's selected in the programarea, the active block gets set to this block
     * 
     * 
     * @param eventLocation location of the event
     * 
     * @author Bert
     */
    public void select(ProgramLocation eventLocation){
        if(this.inPalette(eventLocation)){
            active = palette.handleMouseDown(eventLocation);
            newBlockCreated = true;

        }
        else if(this.inProgramArea(eventLocation)){

            System.out.println("Programarea select");
            active = PArea.selectBlock(eventLocation);
            if(active != null){
                oldPos = active.getPos().clone();

            }


        }

    }

    /**
     * Handle a selected block being released (if there is one)
     * 
     * If it's released in the palette, the block gets deleted
     * If it's released in the programarea, it gets checked whether the block needs to get connected to another block
     * 
     * @param eventLocation location of the event
     * 
     * @author Bert
     */

    //Each release is a state
    public void release(ProgramLocation eventLocation) {
        if (inPalette(eventLocation)) {
            if (active != null) {
                palette.populateBlocks();
            }
            if(this.active != null){
                //TODO it gets repositioned to the last palette location
                undoStack.push(new DeleteAction(this.active, this.PArea));
                this.active = null;

            }

        } else if (this.inProgramArea(eventLocation)) {

            System.out.println("Programarea release");
            if (!newBlockCreated) {
                undoStack.push(new MoveAction(active, this.oldPos, active.getPos().clone()));
            }
            if (newBlockCreated) {
                undoStack.push(new CreateAction(this.active, this.PArea));
                //undoStack.push(new MoveAction(active, this.oldPos, active.getPos().clone()));
            }
            newBlockCreated = false;
            PArea.findAndConnect(eventLocation, active);
            //TODO connection event here


            active = null;
            if (PArea.maxReached()) {
                palette.removeBlocks();

                if (active != null) {
                    PArea.findAndConnect(eventLocation, active);
                    active = null;
                    if (PArea.maxReached()) palette.removeBlocks();

                }
            }

        }
    }


    /**
     * handle block moving, relocate an active block if there is one
     * 
     * @param eventLocation location where the event happened
     * @author Bert
     */
    public void drag(ProgramLocation eventLocation){
        if(active != null){
            if(2 * MyCanvasWindow.WIDTH / 3 - active.getWidth() > eventLocation.getX()){
                PArea.dragBlock(active, eventLocation);
            }
        }
    }

    /**
     * 
     * @return all the blocks in the program. This includes, palette, program area and 
     *         (if there is one) the active block
     */
    public ArrayList<ModelBlock> getModelBlocks(){
        ArrayList<ModelBlock> blocks = new ArrayList<ModelBlock>();
        blocks.addAll(getProgramAreaBlocks());
        blocks.addAll(getPaletteBlocks());
        if(active != null)
            blocks.add(active);
        return blocks;
    }

    /**
     *
     * @return the game world
     */
    public GameWorld getGameWorld(){
        return gameWorld;
    }

    /**
     * 
     * @return all the blocks that are currently in the palette
     */
    protected ArrayList<ModelBlock> getPaletteBlocks(){
        return palette.getPaletteBlocks();
    }

    /**
     * 
     * @return all the blocks that are currently in the program area
     */
    protected ArrayList<ModelBlock> getProgramAreaBlocks(){
        return PArea.getPABlocks();
    }

    /**
     *
     * @return the block states for all blocks in the model
     * @author Oberon Swings
     */
    public ArrayList<BlockState> getBlockStates(){
        ArrayList<BlockState> blockStates = new ArrayList<>();
        for (ModelBlock block : getModelBlocks()){
            blockStates.add(new BlockState(block));
        }
        return blockStates;
    }

}