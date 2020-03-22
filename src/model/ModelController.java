package model;

import java.util.ArrayList;


import main.MyCanvasWindow;
import model.blocks.ModelBlock;
import model.blocks.ModelWhileIfBlock;
import utilities.*;



/**
 * A controller that controls the changes in the logic of the Blockr system and propagates it to the view.
 */
public class ModelController{

    private boolean maxReached = false;

    private final int MAX_BLOCKS = 15;

    private ModelPalette palette;
    private ModelProgramArea PArea;
    private ModelGrid grid;
    private ProgramRunner programRunner;

    private ModelBlock active = null;


    // Constructor
    public ModelController(){
        //palette left, program middle, grid right
        this.setPalette(new ModelPalette(MyCanvasWindow.WIDTH/3,MyCanvasWindow.HEIGHT));
        this.setPArea(new ModelProgramArea(MyCanvasWindow.WIDTH/3,MyCanvasWindow.HEIGHT));
        this.setGrid(new ModelGrid(MyCanvasWindow.WIDTH/3, MyCanvasWindow.HEIGHT, ProgramRunner.getInitialState()));
        this.programRunner = new ProgramRunner();
    }

    /**
     * This function handles key events by telling the model controller
     * to either step through the execution or stop running the program
     * 
     * TODO: propragate to modelController
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
                if (getPArea().allBlocksConnected()){//Check if all blocks are connected, and if so execute.
                    if(programRunner.isRunning()){
                        System.out.println("executing on keypress, is already running");
                        getGrid().setGridState(programRunner.execute(getGrid().getGridState()));
                    } else {
                        programRunner.initialise(getPArea().getStartBlocks().get(0), getPArea().getFinishBlocks().get(0));
                        getGrid().setGridState(programRunner.execute(getGrid().getGridState()));
                    }
                } 
                break;
            case 27: //Esc
 
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
     * 
     * @return the model grid
     */
    public ModelGrid getGrid() {
        return this.grid;
    }

    /**
     * Set the Model Grid of the controller
     * 
     * @param grid the grid the controller controls
     */
    public void setGrid(ModelGrid grid) {
        this.grid = grid;
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
    public void handleMouseEvent(int id, WindowLocation eventWindowLocation, int clickCount){
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
     * handle mouse events in the palette
     * 
     * @param id id of the event: - 500 = MOUSE_CLICKED: Press + release (comes after released + pressed), only comes if no dragging happended
     *                                  - 501 = MOUSE_PRESSED: Where you start holding the button down
     *                                  - 502 = MOUSE_RELEASED: Where you release the button
     *                                  - 506 = MOUSE_DRAGGED: Holding down, gets triggerd after each small move
     * @param eventWindowLocation location where the event happened
     * @param clickCount number of clicks
     */
    protected void handlePaletteMouseEvent(int id, WindowLocation eventWindowLocation, int clickCount){
        switch(id){
            case 501: //MOUSE_PRESSED
                System.out.println("MOUSE PRESSED start");
                if(this.MAX_BLOCKS <= this.getProgramAreaBlocks().size()+1){
                    this.maxReached = true;
                }
                this.active = palette.handleMouseDown(eventWindowLocation, maxReached);
                break;
            case 502: //MOUSE RELEASED
                //delete the currently held item (if there is one)
                System.out.println("MOUSE RELEASED start");
                if(this.active != null){
                    this.maxReached = false;
                    this.palette.resetBlocks();
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
                this.moveBlock(active, eventWindowLocation, false);
                break;
            default:
                break;
        }

    }

    /**
     * handle mouse events in the program area
     * 
     * @param id id of the event: - 500 = MOUSE_CLICKED: Press + release (comes after released + pressed), only comes if no dragging happended
     *                                  - 501 = MOUSE_PRESSED: Where you start holding the button down
     *                                  - 502 = MOUSE_RELEASED: Where you release the button
     *                                  - 506 = MOUSE_DRAGGED: Holding down, gets triggerd after each small move
     * @param eventWindowLocation location where the event happened
     * @param clickCount number of clicks
     */
    protected void handleProgramAreaMouseEvent(int id, WindowLocation eventWindowLocation, int clickCount){
        switch(id){
            case 501: //MOUSE_PRESSED
                //return the topmost active block if one is in the click location
                //you remove it from the local list in PArea until mouseup
                System.out.println("MOUSE PRESSED start");
                this.active = PArea.handleMouseDown(eventWindowLocation);
                break;
            case 502: //MOUSE RELEASED
                System.out.println("MOUSE RELEASED start");
                PArea.handleMouseUp(eventWindowLocation, this.active);
                this.active = null;
                break;
            case 506: //MOUSE_DRAGGED
            System.out.println("MOUSE MOVED start");
                if(active != null){
                    if((int) 2 * MyCanvasWindow.WIDTH / 3 - active.getWidth() > eventWindowLocation.getX()){
                        this.moveBlock(active, eventWindowLocation, true);
                    }
                    
                }
                
                break;
            default:
                break;
        }
    }

    //TODO: this doesn't seem right
    /**
     * 
     * @param block block to move
     * @param newPos new position the block should be at
     * @param inProgramArea signify whether the block is moved into the program area
     */
    public void moveBlock(ModelBlock block, WindowLocation newPos, boolean inProgramArea){
        if (block != null){
            if(inProgramArea){
                block.setPos(newPos);
            }
            else{
                block.setPos(newPos);
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
}