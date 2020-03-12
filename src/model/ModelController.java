package model;

import java.util.ArrayList;

import main.MyCanvasWindow;
import model.blocks.ModelBlock;
import utilities.Location;



/**
 * A controller that controls the changes in the logic of the Blockr system and propagates it to the view.
 */
public class ModelController{

    private ArrayList<ModelBlock> blocks;

    

    private ModelPalette palette;
    private ModelProgramWindow pWindow;
    private ModelGrid grid;

    private ModelBlock active = null;


    
    public ModelController(){
        this.setBlocks(new ArrayList<ModelBlock>());
        //palette left, program middle, grid right
        this.setPalette(new ModelPalette(MyCanvasWindow.WIDTH/3,MyCanvasWindow.HEIGHT));
        this.setPWindow(new ModelProgramWindow(MyCanvasWindow.WIDTH/3,MyCanvasWindow.HEIGHT));
        this.setGrid(new ModelGrid(MyCanvasWindow.WIDTH/3, MyCanvasWindow.HEIGHT, null, null)); 
        //TODO Where is defined which cell is the goalcell, and where the robot starts?
        // => I think for now we can assume a random grid we generated ourselves
    }


    public void moveBlock(ModelBlock block, Location newPos){
        if (!block.equals(null)){
            block.move(newPos, this.findClosestBlock(block));
        }
        
    }

    /*

    public boolean blockInBounds(ModelBlock blk, ModelWindow win){
        if(win instanceof ModelPalette && blk.getPos().getX() < win.getWidth()){
            return true;
        }
        else if(win instanceof ModelProgramWindow && blk.getPos().getX() > getPalette().getWidth() && blk.getPos().getX() < (win.getWidth() + getPalette().getWidth())){
            return true;
        }
        else if(win instanceof ModelGrid && blk.getPos().getX() > (getPalette().getWidth() + getPWindow().getWidth())){
            return true;
        }
        else return false;
    }

    */

    /**
     * 
     * @param block The block for which the closest neighbour needs to be found
     * @return The closest neighbour of block
     */
    public ModelBlock findClosestBlock(ModelBlock block){
        ModelBlock closest = getBlocks().get(0);
        for(ModelBlock blk : getBlocks()){
            if (blk.getPos().getDistance(block.getPos()) < closest.getPos().getDistance(block.getPos())){
                closest = blk;
            }
        }
        return closest;
    }

    /**
     * 
     * @param block The block of which the neighbour is searched.
     * @return the left neighbour of the block if there is one, otherwise null.
     */
    public ModelBlock findLeftNeighbour(ModelBlock block){
        ModelBlock left = null;
        forloop:
        for(ModelBlock blk : getBlocks()){
            if ((blk.getPos().getX() == block.getPos().getX() - block.getWidth()) &&
            blk.getPos().getY() == block.getPos().getY()){
                left = blk;
                break forloop;
            }
        }
        return left;
    }

    public ModelBlock findRightNeighbour(ModelBlock block){
        ModelBlock right = null;
        forloop:
        for(ModelBlock blk : getBlocks()){
            if ((blk.getPos().getX() == block.getPos().getX() + block.getWidth()) &&
            blk.getPos().getY() == block.getPos().getY()){
                right = blk;
                break forloop;
            }
        }
        return right;
    }

    public ModelBlock findUpperNeighbour(ModelBlock block){
        ModelBlock up = null;
        forloop:
        for(ModelBlock blk : getBlocks()){
            if ((blk.getPos().getX() == block.getPos().getX()) &&
            blk.getPos().getY() == block.getPos().getY() - block.getHeight()){
                up = blk;
                break forloop;
            }
        }
        return up;
    }
    
    public ModelBlock findBottomNeighbour(ModelBlock block){
        ModelBlock down = null;
        forloop:
        for(ModelBlock blk : getBlocks()){
            if ((blk.getPos().getX() == block.getPos().getX() - block.getWidth()) &&
            blk.getPos().getY() == block.getPos().getY()){
                down = blk;
                break forloop;
            }
        }
        return down;
    }

    public ArrayList<ModelBlock> getBlocks() {
        return this.blocks;
    }

    public void setBlocks(ArrayList<ModelBlock> blocks) {
        this.blocks = blocks;
    }

    public void addBlock(ModelBlock block) {
        this.blocks.add(block);
    }

    public ModelPalette getPalette() {
        return this.palette;
    }

    public void setPalette(ModelPalette palette) {
        this.palette = palette;
    }

    public ModelProgramWindow getPWindow() {
        return this.pWindow;
    }

    public void setPWindow(ModelProgramWindow pWindow) {
        this.pWindow = pWindow;
    }

    public ModelGrid getGrid() {
        return this.grid;
    }

    public void setGrid(ModelGrid grid) {
        this.grid = grid;
    }


            

        
        //TODO provide an explanation why the list should be traversed in reversed due to render order

        //MOUSE_PRESSED where you start holding the button down 501
        //MOUSE_RELEASED where you release the button      502  
        //MOUSE_CLICKED => press + release (comes after released + pressed) only comes if no dragging happended 500
        //MOUSE_DRAGGED => Holding down, gets triggerd after each small move 506
        //interesting to know: there is no difference detected between left and right button in the current handlemouseevent function

        /*
        if(id == 501){
            
            for (int i = modelElements.size() - 1; i >= 0; i--) {
                if(modelElements.get(i).inBounds(eventLocation)){
                    active = modelElements.get(i);
                }
            }            
        }

        */


    

    //TODO for some reason I can't use the static fields MouseEvent.MOUSE_PRESSED etc

    public void handleMouseEvent(int id, Location eventLocation, int clickCount){
        if(eventLocation.getX() > 0 && eventLocation.getX() < MyCanvasWindow.WIDTH/3 ){



            this.handlePaletteMouseEvent(id, eventLocation, clickCount);
            //TODO get activeblock?
            //TODO check if a new block has been created
        }
        if(eventLocation.getX() > MyCanvasWindow.WIDTH/3 && eventLocation.getX() <  2 * MyCanvasWindow.WIDTH/3){
            this.handleProgramAreaMouseEvent(id, eventLocation, clickCount);
        }




        System.out.println("mouse");



        

    }

    protected void handlePaletteMouseEvent(int id, Location eventLocation, int clickCount){
        //MOUSE_PRESSED 501
        if(id == 501){
            //return the selected block if one is clicked
            this.active = palette.handleMouseDown(eventLocation);

        }
        //MOUSE RELEASED 502
        else if(id==502){
            //MOUSE RELEASED, delete the currently held item (if there is one)
            this.active = null;
    

        }
        //MOUSE MOVED 506
        else if(id==506){
            //MOUSE MOVED, if there is a currently held block, move it
            this.moveBlock(active, eventLocation);
        }

    }

    protected void handleProgramAreaMouseEvent(int id, Location eventLocation, int clickCount){
        //MOUSE_PRESSED 501
        if(id == 501){
            //return the topmost active block if one is in the click location
            //you remove it from the local list in pWindow until mouseup
            pWindow.handleMouseDown(eventLocation);
        }
        //MOUSE RELEASED 502
        else if(id==502){
            pWindow.addBlock(active);
            this.active = null;

        }
        //MOUSE MOVED 506
        else if(id==506){
            this.moveBlock(active, eventLocation);

        }

    }

    protected ArrayList<ModelBlock> getModelBlocks(){
        ArrayList<ModelBlock> blocks = new ArrayList<ModelBlock>();
        blocks.addAll(this.getModelBlocks());
        blocks.addAll(this.getPaletteBlocks());
        blocks.add(this.getActiveBlock());

        return blocks;
    }

    protected ArrayList<ModelBlock> getPaletteBlocks(){
        return palette.getBlocks();

    }

    protected ArrayList<ModelBlock> getProgramAreaBlocks(){
        return pWindow.getBlocks();
    }

    protected ModelBlock getActiveBlock(){
        return this.active;
    }
    
}

/*abstract
//MOUSE_PRESSED 501
        if(id == 501){

        }
        //MOUSE RELEASED 502
        else if(id==502){

        }
        //MOUSE MOVED 506
        else if(id==506){

        }*/