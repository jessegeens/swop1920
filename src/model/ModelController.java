package model;

import java.util.ArrayList;
import model.blocks.ModelBlock;
import utilities.Location;

class ModelController{

    private ArrayList<ModelBlock> blocks = new ArrayList<ModelBlock>();


    public void moveBlock(ModelBlock block, Location newPos){
        block.move(newPos, this.findClosestBlock(block));
    }


    public ModelBlock findClosestBlock(ModelBlock block){
        ModelBlock closest = getBlocks().get(0);
        for(ModelBlock blk : getBlocks()){
            if (blk.getPos().getDistance(block.getPos()) < closest.getPos().getDistance(block.getPos())){
                closest = blk;
            }
        }
        return closest;
    }

    public ArrayList<ModelBlock> getBlocks() {
        return this.blocks;
    }

    public void setBlocks(ArrayList<ModelBlock> blocks) {
        this.blocks = blocks;
    }

    
}