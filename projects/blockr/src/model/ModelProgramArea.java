package model;

import java.util.ArrayList;

import ui.UIBlock;
import utilities.*;
import model.blocks.*;

/**
 * Class representing the Program Window where the blocks will be set.
 */
public class ModelProgramArea extends ModelWindow{

    private ArrayList<ModelBlock> blocks;
    private ConnectionHandler CH;
    private LocationHandler LH;

    // Constructor
    public ModelProgramArea(int width, int height){
        super(width, height);
        this.setBlocks(new ArrayList<ModelBlock>());
        CH = new ConnectionHandler();
        LH = new LocationHandler();
    }

    /**
     * 
     * @return a list of blocks in the window.
     */
    public ArrayList<ModelBlock> getPABlocks() {
        return this.blocks;
    }

    /**
     * 
     * @param blocks list of blocks to be set in a window.
     */
    public void setBlocks(ArrayList<ModelBlock> blocks) {
        this.blocks = blocks;
    }

    /**
     * Remove a block from the Program Area
     * 
     * @param toBeRemoved block that should be removed
     */
    public void removePABlock(ModelBlock toBeRemoved){
        CH.disconnect(toBeRemoved);
        this.getPABlocks().remove(toBeRemoved);
        if(toBeRemoved instanceof ModelWhileIfBlock){
            for (ModelBlock block : ((ModelWhileIfBlock) toBeRemoved).getCavityBlocks()){
                blocks.remove(block);
            }
        }
    }

    /**
     * Add a block to the Program Area
     * @param toBeAdded the block which should be added
     */
    public void addPABlock(ModelBlock toBeAdded){
        if(toBeAdded != null){
            this.blocks.add(toBeAdded);
            if (toBeAdded instanceof ModelWhileIfBlock) {
                this.blocks.addAll(((ModelWhileIfBlock) toBeAdded).getCavityBlocks());
            }
        }
    }

    /**
     * This function handles the mouseDown in the Program Area
     * Note that the blocks list has to be traversed in reverse 
     *  order due to rendering (ask Bert if unclear)
     * 
     * @param eventWindowLocation location of the mouseDown event
     * @return block to be returned 
     */
    public ModelBlock handleMouseDown(Location eventWindowLocation){
        for(int i = this.getPABlocks().size() - 1; i >= 0; i--){
            if(this.getPABlocks().get(i).inBoundsOfElement(eventWindowLocation)){
                ModelBlock toBeReturned = blocks.get(i);
                removePABlock(toBeReturned);
                return toBeReturned;
            }
        }
        return null;
    }

    /**
     * This function handles the mouse up in the ProgramArea
     * @param eveWindowLocation the location of the mouseUp event
     * @param activeB activeBlock the current active block
     */
    public void handleMouseUp(Location eveWindowLocation, ModelBlock activeB){
        this.addPABlock(activeB);
        ModelBlock closest = LH.findClosestBlock(activeB, blocks);
        if (closest != null){
            CH.connect(closest, activeB);
            CH.updateConnections(blocks);
            LH.updateLocationBlocks(blocks);
        }
    }

    public boolean allBlocksConnected(){
        return CH.allBlocksConnected(blocks);
    }

    public ArrayList<ModelBlock> getStartBlocks(){
        return CH.getStartBlocks(blocks);
    }
   
}