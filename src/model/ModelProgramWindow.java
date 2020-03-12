package model;

import java.util.ArrayList;

import model.blocks.ModelBlock;
import utilities.Location;


/**
 * Class representing the Program Window where the blocks will be set.
 */
class ModelProgramWindow extends ModelWindow{

    private ArrayList<ModelBlock> blocks;

    public ModelProgramWindow(int width, int height){
        super(width, height);
        this.setBlocks(new ArrayList<ModelBlock>());
    }

    public void updateLocationBlocks(){
        if (this.getBlocks().get(0) != null){
            for (ModelBlock blk : this.getBlocks().get(0).getConnections()){
                //blk.setPos(new Location());
            }
        }
    }

    

    /**
     * 
     * @return a list of blocks in the window.
     */
    public ArrayList<ModelBlock> getBlocks() {
        return this.blocks;
    }

    /**
     * 
     * @param blocks list of blocks to be set in a window.
     */
    public void setBlocks(ArrayList<ModelBlock> blocks) {
        this.blocks = blocks;
    }

    public void removeBlock(ModelBlock toBeRemoved){
        this.blocks.remove(toBeRemoved);
    }

    public void addBlock(ModelBlock toBeAdded){
        this.blocks.add(toBeAdded);
    }

    public ModelBlock handleMouseDown(Location eventLocation){
        //has to be done in reverse order due to rendering (ask Bert if unclear)
        for(int i = this.getBlocks().size() - 1; i >= 0; i--){
            if(this.getBlocks().get(i).inBounds(eventLocation)){
                ModelBlock toBeReturned = this.getBlocks().get(i);
                this.removeBlock(toBeReturned);
                return toBeReturned;
            }
        }




        return null;
    }

   
}