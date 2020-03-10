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

    public ModelController(){
        this.setBlocks(new ArrayList<ModelBlock>());
        this.setPalette(new ModelPalette(MyCanvasWindow.WIDTH/3,MyCanvasWindow.HEIGHT));
        this.setPWindow(new ModelProgramWindow(MyCanvasWindow.WIDTH/3,MyCanvasWindow.HEIGHT));
        this.setGrid(new ModelGrid(MyCanvasWindow.WIDTH/3, MyCanvasWindow.HEIGHT, null, null)); //TODO Where is defined which cell is the goalcell, and where the robot starts?
                                                // => I think for now we can assume a random grid we generated ourselves
    }


    public void moveBlock(ModelBlock block, Location newPos){
        block.move(newPos, this.findClosestBlock(block));
    }

    public boolean blockInBounds(ModelBlock blk, ModelWindow win){
        //TODO implementatie (Oberon)
    }

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

    
}