package model;

import java.util.ArrayList;


import gameworldapi.ActionType;
import gameworldapi.GameWorld;
import gameworldapi.GameWorldType;
import gameworldapi.PredicateType;
import main.MyCanvasWindow;
import model.blocks.ModelBlock;
import model.blocks.ModelWhileIfBlock;
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
        System.out.println("key pressed");
        switch(keyCode){
            case 65: //A;
            case 116: //F5;
                if (PArea.validExecutionState()){//Check if all blocks are connected, and if so execute.
                    if(programRunner.isRunning()){
                        System.out.println("executing on keypress, is already running");
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
                System.out.println("executing on keypress, is already running");
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

    /**
     * Undo the block or game steps
     *
     * @author Bert
     *
     */
    public void undo(){
        System.out.println("UNDO");

    }

    /**
     * Redo the block or game steps
     *
     * @author Bert
     *
     */
    public void redo(){
        System.out.println("REDO");

    }



    /**
     * 
     * @param location
     * @return whether this event location is inside of the Palette
     * 
     * @author Bert
     */
    protected boolean inPalette(ProgramLocation location){
        if(location.getX() > 0 && location.getX() < MyCanvasWindow.WIDTH/3 ){
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
            System.out.println("Palette select");
            active = palette.handleMouseDown(eventLocation);
        }
        else if(this.inProgramArea(eventLocation)){
            System.out.println("Programarea select");
            active = PArea.handleMouseDown(eventLocation);
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

    public void release(ProgramLocation eventLocation){
        if(inPalette(eventLocation) ){
            System.out.println("Palette release");
            if(active != null){
                palette.populateBlocks();
            }
            /*if (active instanceof ModelWhileIfBlock){
                for (ModelBlock block : ((ModelWhileIfBlock) active).getCavityBlocks()){
                    block = null;
                }
            }*/
            this.active = null;
        }
        else if(this.inProgramArea(eventLocation)){
            if(active != null) {
                System.out.println("Programarea release");
                PArea.handleMouseUp(eventLocation, active);
                active = null;
                if (PArea.maxReached()) palette.removeBlocks();
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