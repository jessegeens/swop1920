package model;

import java.util.ArrayList;
import gameworldapi.GameWorld;
import gameworldapi.GameWorldType;
import main.MyCanvasWindow;
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

    private ProgramLocation selectPosition;

    // Constructor
    public ModelController(GameWorldType worldType){
        //palette left, program middle, grid right
        palette = new ModelPalette(worldType.getSupportedActions(), worldType.getSupportedPredicates());
        PArea = new ModelProgramArea();
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
        if (PArea.validExecutionState()){//Check if all blocks are connected, and if so execute.
            if(programRunner.isRunning()){
                programRunner.execute();
            } else {
                if(PArea.getFirstBlock() != null)
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

    public void select(ProgramLocation location) {
        selectHelp(location);
        if (active != null && LocationHandler.isInProgramArea(location)) PArea.removePABlock(active);
        LocationHandler.getInstance().updateLocationBlocks(PArea.getPABlocks());
    }

    private void selectHelp(ProgramLocation location) {
        if (LocationHandler.isInPalette(location)) {
            active = palette.returnSelectedBlock(location);
            palette.populateBlocks(PArea.getAllModelFunctionDefinitionBlock());
        }
        else if (LocationHandler.isInProgramArea(location)) {
            active = PArea.selectBlock(location);
            this.programRunner.reset();
        }
        if (active != null) selectPosition = active.getPos();
    }

    public void release(ProgramLocation location) {
        addAction(location);
        releaseHelp(location);
        UndoRedoHandler.getInstance().removeRedoActions();
    }

    private void releaseHelp(ProgramLocation location) {
        if (LocationHandler.isInPalette(location)) {
            if (active != null) {
                if (active instanceof ModelFunctionDefinitionBlock) {
                    PArea.deleteFunctionCallsById(((ModelFunctionDefinitionBlock) active).getId());
                    palette.updateFunctionCounter(PArea.getAllModelFunctionDefinitionBlock());
                }
                PArea.removePABlock(active);
                active = null;
                palette.populateBlocks(PArea.getAllModelFunctionDefinitionBlock());
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
        if (active != null) {
            UndoRedoHandler.getInstance().executeAction(new BlockAction(selectPosition, location, active));
        }
    }

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
            PArea.removePABlock(active);
            releaseHelp(((BlockAction) state).getSelect());
            LocationHandler.getInstance().updateLocationBlocks(PArea.getPABlocks());
        }
    }

    public void redo() {
        UndoRedoHandler.getInstance().redo();
        Object state = UndoRedoHandler.getInstance().getState();
        if (state instanceof BlockAction) {
            selectHelp(((BlockAction) state).getSelect());
            active = ((BlockAction) state).getBlock();
            if(active != null) PArea.removePABlock(active);
            releaseHelp(((BlockAction) state).getRelease());
            LocationHandler.getInstance().updateLocationBlocks(PArea.getPABlocks());
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