package model;

import java.util.ArrayList;
import java.util.Stack;


import gameworldapi.GameWorld;
import gameworldapi.GameWorldType;
import main.MyCanvasWindow;
import model.Actions.*;
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

    private Stack<Action> undoStack;
    private Stack<Action> redoStack;

    private boolean isCreateConnect;

    // Constructor
    public ModelController(GameWorldType worldType){
        //palette left, program middle, grid right
        palette = new ModelPalette(worldType.getSupportedActions(), worldType.getSupportedPredicates());
        PArea = new ModelProgramArea();
        this.gameWorld = worldType.newWorldInstance();
        //state = ProgramState.getInitialState();
        programRunner = new ProgramRunner(this.gameWorld);
        undoStack = new Stack<>();
        redoStack = new Stack<>();
        isCreateConnect = false;
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
                boolean resetTriggered = programRunner.execute();
                if(resetTriggered){
                    undoStack.push(new GameEndAction());
                }

            } else {
                programRunner.initialise(PArea.getFirstBlock());

                ArrayList<Action> toRemove = new ArrayList<>();
                for (Action current : undoStack){
                    if(current instanceof GameStartAction || current instanceof GameEndAction){
                        toRemove.add(current);
                    }
                }
                for(Action current : toRemove){
                    undoStack.remove(current);
                }


                undoStack.push(new GameStartAction());
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
        undoStack.push(new GameEndAction());
    }


    /**
     * @author Bert
     */
    public void globalUndo(){
        //TODO this is temporary for trying out the programrunner undo/redo without case checking in the modelcontroller
        if(this.undoStack.peek() instanceof GameStartAction){
            System.out.println("gamestart on top of stack");

        }
        System.out.println("PEEK undostack");
        System.out.println(undoStack.toString());

        if(programRunner.undoFinished()){
            System.out.println("undo finished");
        }

        if(this.undoStack.peek() instanceof GameStartAction && programRunner.undoFinished()){
            System.out.println("exitececution");
            this.exitExecution();
            redoStack.push(undoStack.pop());
            //this.undo();

        }
        if(this.undoStack.peek() instanceof GameEndAction && !programRunner.isRunning()){
            System.out.println("CALLTHIS");
            //startorexecute creates a new gameend?
            this.startOrExecuteProgram();
            System.out.println(undoStack.toString());
            Action popThis = undoStack.pop();
            redoStack.push(popThis);
            System.out.println(undoStack.toString());
        }


        if(this.programRunner.isRunning()){
            programRunner.undoProgramRunner();
            System.out.println("programrunner undo is triggered");
        }
        else{
            this.undo();
            System.out.println("blockr undo is triggered");
        }


    }

    /**
     * @author Bert
     */
    public void globalRedo(){
        //TODO this is temporary for trying out the programrunner undo/redo without case checking in the modelcontroller
        programRunner.redoProgramrunner();

        /*
        if(this.programRunner.isRunning()){

        }
        else{
            this.redo();
        }
        */

    }

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
        if(!undoStack.empty()) {
            Action current = undoStack.pop();
            current.undo();
            System.out.println(current);

            redoStack.push(current);

            if (!(undoStack.empty())) {
                Action peekedAction = undoStack.peek();
                if (current instanceof ConnectAction) {
                    this.undo();
                }
                try {
                    if (peekedAction instanceof DisconnectAction) {
                        this.undo();
                    }
                } catch (Exception e) {
                }
                if (current instanceof DeleteAction) {

                    try {
                        if (peekedAction instanceof MoveAction) {
                            this.undo();
                        }
                    } catch (Exception e) {
                    }
                }
            }
        }
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
    public void redo() {

        System.out.println("REDO");
        if (!redoStack.empty()) {
            Action current = redoStack.pop();
            System.out.println(current);
            current.redo();

            //TODO when stack clear? =>

            undoStack.push(current);
            //TODO when stack clear?
            if (!(redoStack.empty())) {
                Action peekedAction = redoStack.peek();
                try {
                    if (peekedAction instanceof ConnectAction) {
                        this.redo();
                    }
                } catch (Exception e) {
                }
                if (current instanceof DisconnectAction) {
                    this.redo();
                }
                try {
                    if (peekedAction instanceof DeleteAction) {
                        this.redo();
                        //check for similar issue as with undo
                    }
                } catch (Exception e) {
                }
            }
        }
    }

    /**
     * Clears the redo stack
     *
     * @author Bert
     */
    public void clearRedoStack(){
        this.redoStack.clear();
    }


    /**
     * 
     * @param location the selected location
     * @return whether this event location is inside of the Palette
     * 
     * @author Bert
     */
    protected boolean inPalette(ProgramLocation location){
        return (location.getX() >= 0 && location.getX() < MyCanvasWindow.WIDTH/3);
    }

    /**
     * 
     * @param location the selected location
     * @return whether this event location is inside of the ProgramArea
     * 
     * @author Bert
     */
    protected boolean inProgramArea(ProgramLocation location){
        return (location.getX() > MyCanvasWindow.WIDTH/3 && location.getX() <  2 * MyCanvasWindow.WIDTH/3);
    }

    //TODO voor wat diene deze variabelen? mss vanboven zetten bij de rest van de vars.. -Jesse
    private ProgramLocation oldPos = null;
    private boolean newBlockCreated = false;

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
        //TODO checker for whether or not the game is running
        this.clearRedoStack();
        if(this.inPalette(eventLocation)){
            active = palette.handleMouseDown(eventLocation);
            if(active != null) {
                oldPos = active.getPos();
                newBlockCreated = true;
            }


        }
        else if(this.inProgramArea(eventLocation)){
            boolean isConnected = PArea.connectedBlockHere(eventLocation);
            System.out.println("Programarea select");
            active = PArea.selectBlock(eventLocation);
            if(active != null){
                oldPos = active.getPos();
            }
            if(isConnected){
                undoStack.push(new DisconnectAction(active, active.getPos(), this.PArea));
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
        this.clearRedoStack();
        if (inPalette(eventLocation)) {
            if (this.active != null) {
                palette.populateBlocks();


                undoStack.push(new DeleteAction(this.active,this.oldPos, this.PArea));
                this.active = null;
            }
        } else if (this.inProgramArea(eventLocation)) {
            if(active == null) {
                return;
            }
            System.out.println("Programarea release");
            if (!newBlockCreated) {
                undoStack.push(new MoveAction(active, this.oldPos, active.getPos())); //see active.getPos comment in select method. Same applies here.
            }
            if (newBlockCreated) {
                undoStack.push(new CreateAction(this.active, this.PArea));
                //undoStack.push(new MoveAction(active, this.oldPos, active.getPos().clone()));
            }
            newBlockCreated = false;
            if(PArea.findAndConnect(eventLocation, active)){
                undoStack.push(new ConnectAction(this.active, eventLocation, this.PArea));
            }




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
        this.clearRedoStack();

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
    public ArrayList<ModelBlock> getPaletteBlocks(){
        return palette.getPaletteBlocks();
    }

    /**
     * 
     * @return all the blocks that are currently in the program area
     */
    public ArrayList<ModelBlock> getProgramAreaBlocks(){
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

    /**
     * Method just for testing
     * @return active
     */
    public ModelBlock getActiveBlock() {
        return this.active;
    }
}