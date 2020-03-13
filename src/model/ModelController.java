package model;

import java.util.ArrayList;


import main.MyCanvasWindow;
import model.blocks.ModelBlock;
import utilities.GridInfo;
import utilities.Location;


/**
 * A controller that controls the changes in the logic of the Blockr system and propagates it to the view.
 */
public class ModelController{

    private final int MAX_BLOCKS;
    private final int CELL_SIZE;
    private boolean maxReached = false;

    private ModelPalette palette;
    private ModelProgramWindow pWindow;
    private ModelGrid grid;

    private ModelBlock active = null;


    // Constructor
    public ModelController(GridInfo gridInfo, int maxBlocks, int cellSize){
        this.MAX_BLOCKS = maxBlocks;
        this.CELL_SIZE = cellSize;
        //palette left, program middle, grid right
        this.setPalette(new ModelPalette(MyCanvasWindow.WIDTH/3,MyCanvasWindow.HEIGHT));
        this.setPWindow(new ModelProgramWindow(MyCanvasWindow.WIDTH/3,MyCanvasWindow.HEIGHT));
        this.setGrid(new ModelGrid(MyCanvasWindow.WIDTH/3, MyCanvasWindow.HEIGHT, gridInfo.getGoalCell(), gridInfo.getRobotLocation(), gridInfo.getRobotDirection(),new ArrayList<Location>(), CELL_SIZE)); 
    }


    /**
     * 
     * @param block block to move
     * @param newPos new position the block should be at
     * @param inProgramArea signify whether the block is moved into the program area
     */
    public void moveBlock(ModelBlock block, Location newPos, boolean inProgramArea){
        if (block != null){
            if(inProgramArea){
                block.move(newPos);
            }
            else{
                block.setPos(newPos);
            }
        }
    }

    /**
     * 
     * @param block The block of which the neighbour is searched.
     * @return the left neighbour of the block if there is one, otherwise null.
     */
    public ModelBlock findLeftNeighbour(ModelBlock block){
        ModelBlock left = null;
        forloop:
        for(ModelBlock blk : getProgramAreaBlocks()){
            if ((blk.getPos().getX() == block.getPos().getX() - block.getWidth()) &&
            blk.getPos().getY() == block.getPos().getY()){
                left = blk;
                break forloop;
            }
        }
        return left;
    }

    /**
     * 
     * @param block The block of which the neighbour is searched.
     * @return the right neighbour of the block if there is one, otherwise null.
     */
    public ModelBlock findRightNeighbour(ModelBlock block){
        ModelBlock right = null;
        forloop:
        for(ModelBlock blk : getProgramAreaBlocks()){
            if ((blk.getPos().getX() == block.getPos().getX() + block.getWidth()) &&
            blk.getPos().getY() == block.getPos().getY()){
                right = blk;
                break forloop;
            }
        }
        return right;
    }


    /**
     * 
     * @param block The block of which the neighbour is searched.
     * @return the upper neighbour of the block if there is one, otherwise null.
     */
    public ModelBlock findUpperNeighbour(ModelBlock block){
        ModelBlock up = null;
        forloop:
        for(ModelBlock blk : getProgramAreaBlocks()){
            if ((blk.getPos().getX() == block.getPos().getX()) &&
            blk.getPos().getY() == block.getPos().getY() - block.getHeight()){
                up = blk;
                break forloop;
            }
        }
        return up;
    }
    
    /**
     * 
     * @param block The block of which the neighbour is searched.
     * @return the bottom neighbour of the block if there is one, otherwise null.
     */
    public ModelBlock findBottomNeighbour(ModelBlock block){
        ModelBlock down = null;
        forloop:
        for(ModelBlock blk : getProgramAreaBlocks()){
            if ((blk.getPos().getX() == block.getPos().getX() - block.getWidth()) &&
            blk.getPos().getY() == block.getPos().getY()){
                down = blk;
                break forloop;
            }
        }
        return down;
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
    public ModelProgramWindow getPWindow() {
        return this.pWindow;
    }

    /**
     * Set the Model Program Window of the controller
     * 
     * @param pWindow program window the controller controls
     */
    public void setPWindow(ModelProgramWindow pWindow) {
        this.pWindow = pWindow;
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
     * @param eventLocation location where the event happened
     * @param clickCount number of clicks
     */
    public void handleMouseEvent(int id, Location eventLocation, int clickCount){
        if(eventLocation.getX() > 0 && eventLocation.getX() < MyCanvasWindow.WIDTH/3 ){
            System.out.println("Palette mouseEvent");
            this.handlePaletteMouseEvent(id, eventLocation, clickCount);
        }
        if(eventLocation.getX() > MyCanvasWindow.WIDTH/3 && eventLocation.getX() <  2 * MyCanvasWindow.WIDTH/3){
            System.out.println("Programarea mouseEvent");
            this.handleProgramAreaMouseEvent(id, eventLocation, clickCount); 
        }
    }


    /**
     * handle mouse events in the palette
     * 
     * @param id id of the event: - 500 = MOUSE_CLICKED: Press + release (comes after released + pressed), only comes if no dragging happended
     *                                  - 501 = MOUSE_PRESSED: Where you start holding the button down
     *                                  - 502 = MOUSE_RELEASED: Where you release the button
     *                                  - 506 = MOUSE_DRAGGED: Holding down, gets triggerd after each small move
     * @param eventLocation location where the event happened
     * @param clickCount number of clicks
     */
    protected void handlePaletteMouseEvent(int id, Location eventLocation, int clickCount){
        switch(id){
            case 501: //MOUSE_PRESSED
                System.out.println("MOUSE PRESSED start");
                if(this.MAX_BLOCKS <= this.getProgramAreaBlocks().size()+1){
                    this.maxReached = true;
                }
                this.active = palette.handleMouseDown(eventLocation, maxReached);
                break;
            case 502: //MOUSE RELEASED
                //delete the currently held item (if there is one)
                System.out.println("MOUSE RELEASED start");
                if(this.active != null){
                    this.maxReached = false;
                    this.palette.resetBlocks();
                }
                this.active = null;
                break;
            case 506: //MOUSE_DRAGGED
                //if there is a currently held block, move it
                System.out.println("MOUSE MOVED start");
                this.moveBlock(active, eventLocation, false);
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
     * @param eventLocation location where the event happened
     * @param clickCount number of clicks
     */
    protected void handleProgramAreaMouseEvent(int id, Location eventLocation, int clickCount){
        switch(id){
            case 501: //MOUSE_PRESSED
                //return the topmost active block if one is in the click location
                //you remove it from the local list in pWindow until mouseup
                System.out.println("MOUSE PRESSED start");
                this.active = pWindow.handleMouseDown(eventLocation);
                break;
            case 502: //MOUSE RELEASED
                System.out.println("MOUSE RELEASED start");
                pWindow.handleMouseUp(eventLocation, this.active);
                this.active = null;
                break;
            case 506: //MOUSE_DRAGGED
                System.out.println("MOUSE MOVED start");
                this.moveBlock(active, eventLocation, true);
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
        return palette.getPaletteBlocks();

    }

    /**
     * 
     * @return all the blocks that are currently in the program area
     */
    protected ArrayList<ModelBlock> getProgramAreaBlocks(){
        return pWindow.getPABlocks();
    }

    /**
     * 
     * @return the current active block
     */
    protected ModelBlock getActiveBlock(){
        return this.active;
    }
}