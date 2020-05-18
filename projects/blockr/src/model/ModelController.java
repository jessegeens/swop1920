package model;

import java.util.ArrayList;


import gameworldapi.GameWorld;
import gameworldapi.GameWorldType;
import main.MyCanvasWindow;
import model.Actions.*;
import model.blocks.ModelBlock;
import model.blocks.ModelFunctionDefinitionBlock;
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

//    private Stack<Action> undoStack;
//    private Stack<Action> redoStack;
//
//    private boolean isCreateConnect;
//
//    private ProgramLocation oldPos;
//    private boolean newBlockCreated;

    private ProgramLocation selectPosition;

    // Constructor
    public ModelController(GameWorldType worldType){
        //palette left, program middle, grid right
        palette = new ModelPalette(worldType.getSupportedActions(), worldType.getSupportedPredicates());
        PArea = new ModelProgramArea();
        this.gameWorld = worldType.newWorldInstance();
        //state = ProgramState.getInitialState();
        programRunner = new ProgramRunner(this.gameWorld);
//        undoStack = new Stack<>();
//        redoStack = new Stack<>();
//        isCreateConnect = false;
//        oldPos = null;
//        newBlockCreated = false;
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
                programRunner.initialise(PArea.getFirstBlock(), PArea.getAllModelFunctionDefinitionBlock());
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


    /**
     * Checks whether or not a game is running, if it is, undo is called on the game, otherwise undo is called on the blocks
     *
     * @author Bert
     */
//    public void globalUndo(){
//        if (this.programRunner.isRunning()) {
//            programRunner.undoProgramRunner();
//        }
//        else if(!undoStack.isEmpty()) {
//            this.undo();
//        }
//    }

    /**
     * Checks whether or not a game is running, if it is, redo is called on the game, otherwise undo is called on the blocks
     *
     * @author Bert
     */
//    public void globalRedo(){
//        if (this.programRunner.isRunning()) {
//            programRunner.redoProgramRunner();
//        }
//        else if(!redoStack.isEmpty()) {
//            this.redo();
//        }
//    }

//    /**
//     * Undo the block or game steps
//     *
//     * @author Bert
//     *
//     * Still WIP
//     *
//     */
//    public void undo(){
//        //TODO acties verlopen niet dubbel meer (waarschijnlijk toch wel ik heb gewoon brak geplaatst)
//        //TODO blokken hebben spatie bij disconnect
//        //=> gewoon plugs en sockets setten van het deleted block bij de connections
//        //System.out.println("UNDO");
//        if(!undoStack.empty()) {
//            Action current = undoStack.pop();
//            current.undo();
//            redoStack.push(current);
//            if (!(undoStack.empty())) {
//                Action peekedAction = undoStack.peek();
//                if (current instanceof ConnectAction) {
//                    this.undo();
//                }
//                try {
//                    if (peekedAction instanceof DisconnectAction) {
//                        this.undo();
//                    }
//                } catch (Exception e) {
//                }
//                if (current instanceof DeleteAction) {
//
//                    try {
//                        if (peekedAction instanceof MoveAction) {
//                            this.undo();
//                        }
//                    } catch (Exception e) {
//                    }
//                }
//            }
//        }
//        palette.populateBlocks(PArea.getActiveFunctionDefinitions());
//    }



//    /**
//     * Redo the block or game steps
//     *
//     * @author Bert
//     *
//     * Still WIP
//     *
//     */
//    public void redo() {
//        if (!redoStack.empty()) {
//            Action current = redoStack.pop();
//            current.redo();
//            undoStack.push(current);
//            if (!(redoStack.empty())) {
//                Action peekedAction = redoStack.peek();
//                try {
//                    if (peekedAction instanceof ConnectAction) {
//                        this.redo();
//                    }
//                } catch (Exception e) {
//                }
//                if (current instanceof DisconnectAction) {
//                    this.redo();
//                }
//                try {
//                    if (peekedAction instanceof DeleteAction) {
//                        this.redo();
//                    }
//                } catch (Exception e) {
//                }
//            }
//        }
//        palette.populateBlocks(PArea.getActiveFunctionDefinitions());
//    }

//    /**
//     * Clears the redo stack
//     *
//     * @author Bert
//     */
//    public void clearRedoStack(){
//        this.redoStack.clear();
//    }
//
//
//    /**
//     *
//     * @param location the selected location
//     * @return whether this event location is inside of the Palette
//     *
//     * @author Bert
//     */
//    protected boolean inPalette(ProgramLocation location){
//        return (location.getX() >= 0 && location.getX() < MyCanvasWindow.WIDTH/3);
//    }
//
//    /**
//     *
//     * @param location the selected location
//     * @return whether this event location is inside of the ProgramArea
//     *
//     * @author Bert
//     */
//    protected boolean inProgramArea(ProgramLocation location){
//        return (location.getX() > MyCanvasWindow.WIDTH/3 && location.getX() <  2 * MyCanvasWindow.WIDTH/3);
//    }



//    /**
//     * Handle a possible block selection (if the position is inbounds of a block)
//     *
//     * If it's selected in the palette, a new block gets selected and a new block of this type gets created in the palette
//     * If it's selected in the programarea, the active block gets set to this block
//     *
//     *
//     * @param eventLocation location of the event
//     *
//     * @author Bert
//     */
//    public void select(ProgramLocation eventLocation){
//        this.clearRedoStack();
//        if(this.inPalette(eventLocation)){
//            active = palette.returnSelectedBlock(eventLocation);
//            if(active != null) {
//                oldPos = active.getPos();
//                newBlockCreated = true;
//                if(active instanceof ModelFunctionDefinitionBlock){
//                    ArrayList<Integer> idsForPalette = PArea.getActiveFunctionDefinitions();
//                    idsForPalette.add(((ModelFunctionDefinitionBlock) active).getId());
//                    palette.populateBlocks(idsForPalette);
//                }
//            }
//        }
//        else if(this.inProgramArea(eventLocation)){
//            boolean isConnected = PArea.connectedBlockHere(eventLocation);
//            active = PArea.selectBlock(eventLocation);
//            if(active != null){
//                oldPos = active.getPos();
//                this.programRunner.reset();
//            }
//            if(isConnected){
//                undoStack.push(new DisconnectAction(active, active.getPos(), this.PArea));
//            }
//        }
//    }

    public void newSelect(ProgramLocation location) {
        newSelectHelp(location);
        if (active != null && LocationHandler.isInProgramArea(location)) PArea.removePABlock(active);
    }

    private void newSelectHelp(ProgramLocation location) {
        if (LocationHandler.isInPalette(location)) {
            active = palette.returnSelectedBlock(location);
        }
        else if (LocationHandler.isInProgramArea(location)) {
            active = PArea.selectBlock(location);
            this.programRunner.reset();
        }
        if (active != null) selectPosition = active.getPos();
    }

//    /**
//     * Handle a selected block being released (if there is one)
//     *
//     * If it's released in the palette, the block gets deleted
//     * If it's released in the programarea, it gets checked whether the block needs to get connected to another block
//     *
//     * @param eventLocation location of the event
//     *
//     * @author Bert
//     */
//
//    //Each release is a state
//    public void release(ProgramLocation eventLocation) {
//        this.clearRedoStack();
//        if (inPalette(eventLocation)) {
//            if (this.active != null) {
//                if(this.active instanceof ModelFunctionDefinitionBlock){
//                    ArrayList<ModelFunctionCallBlock> deletedList = PArea.deleteFunctionCallsById( ((ModelFunctionDefinitionBlock) this.active).getId());
//                    palette.populateBlocks(PArea.getActiveFunctionDefinitions());
//                    undoStack.push(new DeleteFunctionDefinitionAction(this.active,this.oldPos, this.PArea, deletedList));
//                    //TODO DeleteMFB action in undostack (and register the currently active block as well)
//                }
//                else{
//                    undoStack.push(new DeleteAction(this.active,this.oldPos, this.PArea));
//                }
//                this.active = null;
//            }
//        } else if (this.inProgramArea(eventLocation)) {
//            if(active == null) {
//                return;
//            }
//            programRunner.reset();
//            System.out.println("Programarea release");
//            if (!newBlockCreated) {
//                undoStack.push(new MoveAction(active, this.oldPos, active.getPos(), this.PArea)); //see active.getPos comment in select method. Same applies here.
//            }
//            if (newBlockCreated) {
//                undoStack.push(new CreateAction(this.active, this.PArea));
//            }
//            newBlockCreated = false;
//            if(PArea.findAndConnect(eventLocation, active)){
//                undoStack.push(new ConnectAction(this.active, eventLocation, this.PArea));
//            }
//            active = null;
//            if (PArea.maxReached()) {
//                palette.removeBlocks();
//                if (active != null) {
//                    PArea.findAndConnect(eventLocation, active);
//                    active = null;
//                    if (PArea.maxReached()) palette.removeBlocks();
//                }
//            }
//        }
//    }

    public void newRelease(ProgramLocation location) {
        addAction(location);
        newReleaseHelp(location);
        UndoRedoHandler.getInstance().removeRedoActions();
    }

    private void newReleaseHelp(ProgramLocation location) {
        if (LocationHandler.isInPalette(location)) {
            if (active != null) {
                if (active instanceof ModelFunctionDefinitionBlock) {
                    PArea.deleteFunctionCallsById(((ModelFunctionDefinitionBlock) active).getId());
                    //palette.populateBlocks(PArea.getActiveFunctionDefinitions());
                    palette.populateBlocks(PArea.getAllModelFunctionDefinitionBlock());
                }
                PArea.removePABlock(active);
                active = null;
            }
        }
        else if (LocationHandler.isInProgramArea(location)) {
            if (active != null) {
                programRunner.reset();
                PArea.addAndConnectBlock(active, PArea.findClosestBlock(location, active));
                if (active instanceof ModelFunctionDefinitionBlock) {
                    palette.populateBlocks(PArea.getAllModelFunctionDefinitionBlock());
                }
                active = null;
            }
            if (PArea.maxReached()) {
                palette.removeBlocks();
            }
        }
    }

    private void addAction(ProgramLocation location) {
        UndoRedoHandler.getInstance().executeAction(new OberonAction(selectPosition, location, active));
    }

    public void newUndo() {
        UndoRedoHandler.getInstance().undo();
        Object state = UndoRedoHandler.getInstance().getState();
        if (state instanceof OberonAction) {
            newSelectHelp(((OberonAction) state).getRelease());
            active = ((OberonAction) state).getBlock();
            PArea.removePABlock(active);
            newReleaseHelp(((OberonAction) state).getSelect());
        }
        else if (state instanceof ProgramState) {
            programRunner.setState((ProgramState) state);
        }
    }

    public void newRedo() {
        UndoRedoHandler.getInstance().redo();
        Object state = UndoRedoHandler.getInstance().getState();
        if (state instanceof OberonAction) {
            newSelectHelp(((OberonAction) state).getSelect());
            active = ((OberonAction) state).getBlock();
            PArea.removePABlock(active);
            newReleaseHelp(((OberonAction) state).getRelease());
        }
        else if (state instanceof ProgramState) {
            programRunner.setState((ProgramState) state);
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