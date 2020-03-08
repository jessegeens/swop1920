package model;

import java.util.ArrayList;
import model.blocks.ModelBlock;
import utilities.Location;

class ModelController{

    private ArrayList<ModelBlock> blocks = new ArrayList<ModelBlock>();


    public void moveBlock(ModelBlock block, Location newPos){
        block.move(newPos, this.findClosestBlock(block));
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

    
}