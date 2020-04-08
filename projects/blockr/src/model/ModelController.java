package model;

import java.util.ArrayList;


import main.MyCanvasWindow;
import model.blocks.ModelBlock;
import model.blocks.ModelWhileIfBlock;
import ui.BlockState;
import utilities.*;



/**
 * A controller that controls the changes in the logic of the Blockr system and propagates it to the view.
 */
public class ModelController{

    private boolean maxReached = false;

    private final int MAX_BLOCKS = 10;

    private ModelPalette palette;
    private ModelProgramArea PArea;
    private ProgramState state;
    private ProgramRunner programRunner;

    private ModelBlock active = null;


    // Constructor
    public ModelController(){
        //palette left, program middle, grid right
        this.setPalette(new ModelPalette(MyCanvasWindow.WIDTH/3,MyCanvasWindow.HEIGHT));
        this.setPArea(new ModelProgramArea(MyCanvasWindow.WIDTH/3,MyCanvasWindow.HEIGHT));
        this.state = ProgramState.getInitialState();
        this.setProgramRunner(new ProgramRunner());
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
                    if(this.getProgramRunner().isRunning()){
                        System.out.println("executing on keypress, is already running");
                        state = this.getProgramRunner().execute(state);
                    } else {
                        this.getProgramRunner().initialise(PArea.getFirstBlock());
                    }
                } 
                break;
            case 27: //Esc
                this.getProgramRunner().reset();
                break;
        }        
    }

    /**
     * 
     * @return the model palette
     */
    public ModelPalette getPalette() {
        return this.palette;
    }

    /**
     * Set the Model Palette of the controller
     * 
     * @param palette the controller controls
     */
    public void setPalette(ModelPalette palette) {
        this.palette = palette;
    }

    /**
     * 
     * @return the model program window
     */
    public ModelProgramArea getPArea() {
        return this.PArea;
    }

    /**
     * Set the Model Program Window of the controller
     * 
     * @param PArea program window the controller controls
     */
    public void setPArea(ModelProgramArea PArea) {
        this.PArea = PArea;
    }

    /**
     * general handler for mouse events, checks where the mouse event should be handled
     * 
     * TODO: for some reason I can't use the static fields MouseEvent.MOUSE_PRESSED etc
     * 
     * @param id id of the event: - 500 = MOUSE_CLICKED: Press + release (comes after released + pressed), only comes if no dragging happended
     *                                  - 501 = MOUSE_PRESSED: Where you start holding the button down
     *                                  - 502 = MOUSE_RELEASED: Where you release the button
     *                                  - 506 = MOUSE_DRAGGED: Holding down, gets triggerd after each small move
     * @param eventWindowLocation location where the event happened
     * @param clickCount number of clicks
     */
    public void handleMouseEvent(int id, Location eventWindowLocation, int clickCount){
        if(eventWindowLocation.getX() > 0 && eventWindowLocation.getX() < MyCanvasWindow.WIDTH/3 ){
            System.out.println("Palette mouseEvent");
            this.handlePaletteMouseEvent(id, eventWindowLocation, clickCount);
        }
        if(eventWindowLocation.getX() > MyCanvasWindow.WIDTH/3 && eventWindowLocation.getX() <  2 * MyCanvasWindow.WIDTH/3){
            System.out.println("Programarea mouseEvent");
            this.handleProgramAreaMouseEvent(id, eventWindowLocation, clickCount);
        }
    }

    /**
     * 
     * @param location
     * @return whether this event location is inside of the Palette
     * 
     * @author Bert
     */
    protected boolean inPalette(Location location){
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
    protected boolean inProgramArea(Location location){
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
    public void select(Location eventLocation){
        if(this.inPalette(eventLocation)){
            System.out.println("Palette select");
            if(this.MAX_BLOCKS <= this.getProgramAreaBlocks().size()+1){
                this.setMaxReached(true);
            }
            this.setActiveBlock(this.getPalette().handleMouseDown(eventLocation, this.isMaxReached()));
        }
        else if(this.inProgramArea(eventLocation)){
            System.out.println("Programarea select");
            this.setActiveBlock(this.getPArea().handleMouseDown(eventLocation));
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

    public void release(Location eventLocation){
        if(this.inPalette(eventLocation) ){
            System.out.println("Palette release");
            if(this.getActiveBlock() != null){
                this.setMaxReached(false);
                this.getPalette().resetBlocks();
            }
            if (this.getActiveBlock() instanceof ModelWhileIfBlock){
                for (ModelBlock block : ((ModelWhileIfBlock) this.getActiveBlock()).getCavityBlocks()){
                    block = null;
                }
            }
            this.active = null;
            
        }
        else if(this.inProgramArea(eventLocation)){
            System.out.println("Programarea release");
            this.getPArea().handleMouseUp(eventLocation, this.getActiveBlock());
            this.setActiveBlock(null);
        }
        
    }


    /**
     * handle block moving, relocate an active block if there is one
     * 
     * @param eventLocation location where the event happened
     * @author Bert
     */
    public void move(Location eventLocation){
        if(this.getActiveBlock() != null){
            if((int) 2 * MyCanvasWindow.WIDTH / 3 - this.getActiveBlock().getWidth() > eventLocation.getX()){
                this.getActiveBlock().setPos(eventLocation);
            }
        }
        
    }

    /**
     * DEPRECATED
     * handle mouse events in the palette
     * 
     * @param id id of the event: - 500 = MOUSE_CLICKED: Press + release (comes after released + pressed), only comes if no dragging happended
     *                                  - 501 = MOUSE_PRESSED: Where you start holding the button down
     *                                  - 502 = MOUSE_RELEASED: Where you release the button
     *                                  - 506 = MOUSE_DRAGGED: Holding down, gets triggerd after each small move
     * @param eventWindowLocation location where the event happened
     * @param clickCount number of clicks
     */
    protected void handlePaletteMouseEvent(int id, Location eventWindowLocation, int clickCount){
        switch(id){
            case 501: //MOUSE_PRESSED
                System.out.println("MOUSE PRESSED start");
                if(this.MAX_BLOCKS <= this.getProgramAreaBlocks().size()+1){
                    this.setMaxReached(true);
                }
                this.setActiveBlock(this.getPalette().handleMouseDown(eventWindowLocation, this.isMaxReached()));
                break;
            case 502: //MOUSE RELEASED
                //delete the currently held item (if there is one)
                System.out.println("MOUSE RELEASED start");
                if(this.getActiveBlock() != null){
                    this.setMaxReached(false);
                    this.getPalette().resetBlocks();
                }
                if (this.getActiveBlock() instanceof ModelWhileIfBlock){
                    for (ModelBlock block : ((ModelWhileIfBlock) this.getActiveBlock()).getCavityBlocks()){
                        block = null;
                    }
                }
                this.active = null;
                break;
            case 506: //MOUSE_DRAGGED
                //if there is a currently held block, move it
                System.out.println("MOUSE MOVED start");
                this.getActiveBlock().setPos(eventWindowLocation);
                break;
            default:
                break;
        }

    }

    /**
     * DEPRECATED
     * handle mouse events in the program area
     * 
     * @param id id of the event: - 500 = MOUSE_CLICKED: Press + release (comes after released + pressed), only comes if no dragging happended
     *                                  - 501 = MOUSE_PRESSED: Where you start holding the button down
     *                                  - 502 = MOUSE_RELEASED: Where you release the button
     *                                  - 506 = MOUSE_DRAGGED: Holding down, gets triggerd after each small move
     * @param eventWindowLocation location where the event happened
     * @param clickCount number of clicks
     */
    protected void handleProgramAreaMouseEvent(int id, Location eventWindowLocation, int clickCount){
        switch(id){
            case 501: //MOUSE_PRESSED
                //return the topmost active block if one is in the click location
                //you remove it from the local list in PArea until mouseup
                System.out.println("MOUSE PRESSED start");
                this.setActiveBlock(this.getPArea().handleMouseDown(eventWindowLocation));
                break;
            case 502: //MOUSE RELEASED
                System.out.println("MOUSE RELEASED start");
                this.getPArea().handleMouseUp(eventWindowLocation, this.getActiveBlock());
                this.setActiveBlock(null);
                break;
            case 506: //MOUSE_DRAGGED
            System.out.println("MOUSE MOVED start");
                if(this.getActiveBlock() != null){
                    if((int) 2 * MyCanvasWindow.WIDTH / 3 - this.getActiveBlock().getWidth() > eventWindowLocation.getX()){
                        this.getActiveBlock().setPos(eventWindowLocation);
                    }
                }
                break;
            default:
                break;
        }
    }

    /**
     * 
     * @return all the blocks in the program. This includes, palette, program area and 
     *         (if there is one) the active block
     */
    public ArrayList<ModelBlock> getModelBlocks(){
        ArrayList<ModelBlock> blocks = new ArrayList<ModelBlock>();
        blocks.addAll(this.getProgramAreaBlocks());
        blocks.addAll(this.getPaletteBlocks());
        if(this.getActiveBlock()!=null)
            blocks.add(this.getActiveBlock());
        return blocks;
    }

    /**
     * 
     * @return all the blocks that are currently in the palette
     */
    protected ArrayList<ModelBlock> getPaletteBlocks(){
        return this.getPalette().getPaletteBlocks();

    }

    /**
     * 
     * @return all the blocks that are currently in the program area
     */
    protected ArrayList<ModelBlock> getProgramAreaBlocks(){
        return this.getPArea().getPABlocks();
    }

    /**
     * 
     * @return the current active block
     */
    protected ModelBlock getActiveBlock(){
        return this.active;
    }

    public void setActiveBlock(ModelBlock blk){
        this.active = blk;
    }


    public ProgramRunner getProgramRunner() {
        return programRunner;
    }

    public void setProgramRunner(ProgramRunner programRunner) {
        this.programRunner = programRunner;
    }

    public boolean isMaxReached() {
        return maxReached;
    }

    public void setMaxReached(boolean maxReached) {
        this.maxReached = maxReached;
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

    public ProgramState getState() {
        return state;
    }
}