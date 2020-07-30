package model;

import java.util.ArrayList;
import gameworldapi.GameWorld;
import gameworldapi.GameWorldType;
import model.blocks.ModelBlock;
import model.blocks.ModelFunctionDefinitionBlock;
import ui.BlockState;
import ui.window.*;
import utilities.*;



/**
 * A controller that controls the changes in the logic of the Blockr system and propagates it to the view.
 */
public class ModelController{

    private ModelPalette palette;
    private ModelProgramArea pArea;
    private ProgramRunner programRunner;
    private GameWorld gameWorld;

    private ModelBlock active = null;

    private ProgramLocation selectPosition;

    // Constructor
    public ModelController(GameWorldType worldType){
        //palette left, program middle, grid right
        palette = new ModelPalette(worldType.getSupportedActions(), worldType.getSupportedPredicates(), this);
        pArea = new ModelProgramArea(this);
        this.gameWorld = worldType.newWorldInstance();
        programRunner = new ProgramRunner(this.gameWorld);
    }

    /**
     * Try to start the program or do the next step in program execution
     *
     * @author Bert (refactored from old handleKeyEvent)
     *
     */
    public void startOrExecuteProgram(){
        if (pArea.validExecutionState()){//Check if all blocks are connected, and if so execute.
            if(programRunner.isRunning()){
                programRunner.execute();
            } else {
                if(pArea.getFirstBlock() != null)
                    programRunner.initialise(pArea.getFirstBlock());
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
     * Select event in the palette
     * @param location where the select event in the palette happened
     */
    public void selectPalette(ProgramLocation location) {
        active = palette.returnSelectedBlock(location);
        palette.populateBlocks(pArea.getAllModelFunctionDefinitionBlock());
        if (active != null) selectPosition = active.getPos();
    }

    /**
     * Select event in the programArea
     * @param location where the select event in the programArea happened
     */
    public void selectProgramArea(ProgramLocation location) {
        active = pArea.selectBlock(location);
        this.programRunner.reset();
        if (active != null){
            selectPosition = active.getPos();
            pArea.removePABlock(active);
            LocationHandler.getInstance().updateLocationBlocks(pArea.getBlocks());
        }
    }

    /**
     * selects a block when it is clicked.
     * @param location location of the selection
     */
    public void select(ProgramLocation location) {
        selectHelp(location);
        if (active != null && LocationHandler.isInProgramArea(location)) pArea.removePABlock(active);
        LocationHandler.getInstance().updateLocationBlocks(pArea.getBlocks());
    }

    /**
     * Helper function for select so this function can be reused for undo and redo purpose.
     * @param location location of the selection
     */
    private void selectHelp(ProgramLocation location) {
        if (LocationHandler.isInPalette(location)) {
            active = palette.returnSelectedBlock(location);
            palette.populateBlocks(pArea.getAllModelFunctionDefinitionBlock());
        }
        else if (LocationHandler.isInProgramArea(location)) {
            active = pArea.selectBlock(location);
            this.programRunner.reset();
        }
        if (active != null) selectPosition = active.getPos();
    }

    /**
     * Release event in the palette
     * @param location where the release event in the palette happened
     */
    public void releasePalette(ProgramLocation location) {
        addAction(location);
        if (active != null) {
            if (active instanceof ModelFunctionDefinitionBlock) {
                pArea.deleteFunctionCallsById(((ModelFunctionDefinitionBlock) active).getId());
                palette.updateFunctionCounter(pArea.getAllModelFunctionDefinitionBlock());
            }
            pArea.removePABlock(active);
            active = null;
            palette.populateBlocks(pArea.getAllModelFunctionDefinitionBlock());
        }
        UndoRedoHandler.getInstance().removeRedoActions();
    }

    /**
     * Release event in the programArea
     * @param location The location where the release event happened
     */
    public void releaseProgramArea(ProgramLocation location){
        addAction(location);
        if (active != null) {
            programRunner.reset();
            pArea.addAndConnectBlock(active, pArea.findClosestBlock(location, active));
            if (active instanceof ModelFunctionDefinitionBlock) {
                palette.populateBlocks(pArea.getAllModelFunctionDefinitionBlock());
            }
            active = null;
        }
        if (pArea.maxReached()) {
            palette.removeBlocks();
        }
        UndoRedoHandler.getInstance().removeRedoActions();
    }

    /**
     * release function when a block is released.
     * @param location location of the release
     */
    public void release(ProgramLocation location) {
        addAction(location);
        releaseHelp(location);
        UndoRedoHandler.getInstance().removeRedoActions();
    }

    /**
     * helper function for the release so this one can be reused for undo and redo.
     * @param location location of the release
     */
    private void releaseHelp(ProgramLocation location) {
        if (LocationHandler.isInPalette(location)) {
            if (active != null) {
                if (active instanceof ModelFunctionDefinitionBlock) {
                    pArea.deleteFunctionCallsById(((ModelFunctionDefinitionBlock) active).getId());
                    palette.updateFunctionCounter(pArea.getAllModelFunctionDefinitionBlock());
                }
                pArea.removePABlock(active);
                active = null;
                palette.populateBlocks(pArea.getAllModelFunctionDefinitionBlock());
            }
        }
        else if (LocationHandler.isInProgramArea(location)) {
            if (active != null) {
                programRunner.reset();
                pArea.addAndConnectBlock(active, pArea.findClosestBlock(location, active));
                if (active instanceof ModelFunctionDefinitionBlock) {
                    palette.populateBlocks(pArea.getAllModelFunctionDefinitionBlock());
                }
                active = null;
            }
            if (pArea.maxReached()) {
                palette.removeBlocks();
            }
        }
    }

    /**
     * Adds the action to the undostack in the undoRedoHandler
     * @param location
     */
    private void addAction(ProgramLocation location) {
        if (active != null) {
            UndoRedoHandler.getInstance().executeAction(new BlockAction(selectPosition, location, active));
        }
    }

    /**
     * Undo the last action execution (ProgramState or BlockAction)
     */
    public void undo() {
        Object state = UndoRedoHandler.getInstance().getState();
        UndoRedoHandler.getInstance().undo();
        if (state instanceof ProgramState) {
            state = UndoRedoHandler.getInstance().getState();
            if (state instanceof ProgramState) programRunner.setState((ProgramState) state);
            else programRunner.setState(null);
        }
        else if (state instanceof BlockAction) {
            selectHelp(((BlockAction) state).getRelease());
            active = ((BlockAction) state).getBlock();
            pArea.removePABlock(active);
            releaseHelp(((BlockAction) state).getSelect());
            LocationHandler.getInstance().updateLocationBlocks(pArea.getBlocks());
        }
    }

    /**
     * Redo the last undone action (ProgramState or BlockAction)
     */
    public void redo() {
        UndoRedoHandler.getInstance().redo();
        Object state = UndoRedoHandler.getInstance().getState();
        if (state instanceof BlockAction) {
            selectHelp(((BlockAction) state).getSelect());
            active = ((BlockAction) state).getBlock();
            if(active != null) pArea.removePABlock(active);
            releaseHelp(((BlockAction) state).getRelease());
            LocationHandler.getInstance().updateLocationBlocks(pArea.getBlocks());
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
            LocationHandler.getInstance().setLocationBlock(active, eventLocation);
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
        return palette.getBlocks();
    }

    /**
     * 
     * @return all the blocks that are currently in the program area
     */
    public ArrayList<ModelBlock> getProgramAreaBlocks(){
        return pArea.getBlocks();
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
     * @return active
     */
    public ModelBlock getActiveBlock() {
        return this.active;
    }

    /**
     *
     * @return an Arraylist of contents that the controller controls.
     */
    public ArrayList<WindowMediator> getContent(){
        ArrayList<WindowMediator> windowContents = new ArrayList<WindowMediator>();
        WindowMediator palette = new ModelWindowMediator(this.palette);
        WindowMediator pArea = new ModelWindowMediator(this.pArea);
        WindowMediator gameWorld = new GameWorldWindowMediator(getGameWorld());
        windowContents.add(palette);
        windowContents.add(pArea);
        windowContents.add(gameWorld);
        return windowContents;
    }
}