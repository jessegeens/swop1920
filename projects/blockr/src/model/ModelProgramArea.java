package model;

import java.util.ArrayList;

import ui.window.Content;
import utilities.*;
import model.blocks.*;

/**
 * Class representing the Program Window where the blocks will be set.
 */
public class ModelProgramArea implements Content {

    private final ModelController modelController;

    private final int MAX_BLOCKS = 15;
    private ArrayList<ModelBlock> blocks;

    // Constructor
    public ModelProgramArea(ModelController modelController){
        this.modelController = modelController;
        blocks = new ArrayList<ModelBlock>();
    }

    /**
     * 
     * @return a list of blocks in the window.
     */
    public ArrayList<ModelBlock> getPABlocks() {
        return this.blocks;
    }

    /**
     * Remove a block from the Program Area
     * 
     * @param toBeRemoved block that should be removed
     */
    public void removePABlock(ModelBlock toBeRemoved){
        ConnectionHandler.getInstance().disconnect(toBeRemoved);
        ArrayList<ModelBlock> toRemoveBlocks = new ArrayList<>();
        toRemoveBlocks.add(toBeRemoved);
        int i = 0;
        while (toRemoveBlocks.size() > i) {
            for (ModelBlock block : toRemoveBlocks.get(i).getConnectionsCavityRight()) {
                if (!toRemoveBlocks.contains(block)) {
                    toRemoveBlocks.add(block);
                }
            }
            i ++;
        }
        blocks.removeAll(toRemoveBlocks);
    }

    /**
     * Add a block to the Program Area
     * @param toBeAdded the block which should be added
     */
    public void addPABlock(ModelBlock toBeAdded){
        ArrayList<ModelBlock> toAddBlocks = new ArrayList<>();
        toAddBlocks.add(toBeAdded);
        int i = 0;
        while (toAddBlocks.size() > i) {
            for (ModelBlock block : toAddBlocks.get(i).getConnectionsCavityRight()) {
                if (!toAddBlocks.contains(block)) {
                    toAddBlocks.add(block);
                }
            }
            i ++;
        }
        blocks.addAll(toAddBlocks);
    }

    /**
     * This function handles the mouseDown in the Program Area
     * Note that the blocks list has to be traversed in reverse 
     *  order due to rendering (ask Bert if unclear)
     * 
     * @param eventWindowLocation location of the mouseDown event
     * @return block to be returned
     * @author Bert
     */
    public ModelBlock selectBlock(ProgramLocation eventWindowLocation){
        for(int i = blocks.size() - 1; i >= 0; i--){
            if(blocks.get(i).inBoundsOfElement(eventWindowLocation)){
                ModelBlock toBeReturned = blocks.get(i);
                return toBeReturned;
            }
        }
        return null;
    }

    /**
     *
     * @param location the location of the block
     * @param active the block that is active
     * @return the closest block in the programArea
     */
    public ModelBlock findClosestBlock(ProgramLocation location, ModelBlock active) {
        ProgramLocation newLocation = LocationHandler.getInstance().moveToInBounds(location);
        LocationHandler.getInstance().setLocationBlock(active, newLocation);
        return LocationHandler.getInstance().findClosestBlock(active, blocks);
    }

    /**
     * adds the block to the programArea and connects it if possible.
     * @param active the block that is active
     * @param closest the closest block to check
     */
    public void addAndConnectBlock(ModelBlock active, ModelBlock closest) {
        if (!(this.getPABlocks().contains(active))) {
            this.addPABlock(active);
        }
        if (closest != null){
            ConnectionHandler.getInstance().connect(closest, active);
            LocationHandler.getInstance().updateLocationBlock(active);
            ConnectionHandler.getInstance().updateConnections(blocks);
            LocationHandler.getInstance().updateLocationBlocks(ConnectionHandler.getInstance().getStartBlocks(blocks));
        }
    }

    /**
     * Moves a block to the location to which it is dragged
     * @param block the block that needs to be moved
     * @param location the location to which the block is dragged
     * @author Oberon Swings
     */
    public void dragBlock(ModelBlock block, ProgramLocation location){
        LocationHandler.getInstance().setLocationBlock(block, location);
    }

    /**
     * Tests if the current state of the program Area is valid to start execution
     * @return true if all blocks are connected
     * @author Oberon Swings
     */
    public boolean validExecutionState(){
        return ConnectionHandler.getInstance().allBlocksConnected(blocks);
    }

    /**
     * Gives the block at which the execution needs to start
     * @return the first block for execution
     * @author Oberon Swings
     */
    public ModelBlock getFirstBlock(){
        if (ConnectionHandler.getInstance().getStartBlocks(blocks).size() > 0)
            return ConnectionHandler.getInstance().getStartBlocks(blocks).get(0);
        else
            return null;
    }

    /**
     * Tests if the maximum amount of blocks in the program area is reached
     * @return true if blocks.size bigger than or equal to MAX_BLOCKS
     * @author Oberon Swings
     */
    public boolean maxReached(){
        return (blocks.size() >= MAX_BLOCKS);
    }

    /**
     * Returns the all of the function definition blocks in the PA
     *
     * @return an arraylist of all function def blocks
     * @author bert_dvl
     */
    public ArrayList<ModelFunctionDefinitionBlock> getAllModelFunctionDefinitionBlock(){
        ArrayList<ModelFunctionDefinitionBlock> toBeReturned = new ArrayList<>();
        for(ModelBlock block : blocks){
            if(block instanceof ModelFunctionDefinitionBlock){
                toBeReturned.add((ModelFunctionDefinitionBlock) block);
            }
        }
        return toBeReturned;
    }

    /**
     * Deletes the ModelFunctionCallBlocks with a certain id when the ModelFunctionDefinitionBlock has been removed
     *
     * @param id the id of the functionCallBlocks
     * @author Bert
     */
    public void deleteFunctionCallsById(int id){
        ArrayList<ModelFunctionCallBlock> toBeReturned = new ArrayList<>();
        for(ModelBlock block : blocks){
            if(block instanceof ModelFunctionCallBlock){
                if(((ModelFunctionCallBlock) block).getId() == id){
                    toBeReturned.add( (ModelFunctionCallBlock) block);
                }
            }
        }
        for(ModelBlock block: toBeReturned){
            removePABlock(block);
        }
    }

    @Override
    public int getHeight() {
        int minY = 0;
        int maxY = Integer.MIN_VALUE;
        for (ModelBlock block : blocks){
            if (block.getPos().getY() < minY) minY = block.getPos().getY();
            if (block.getPos().getY() + block.getHeight() > maxY) maxY = block.getPos().getY() + block.getHeight();
        }
        return maxY - minY;
    }

    @Override
    public void onClick(int x, int y) {
        ProgramLocation location = new ProgramLocation(x, y);
        modelController.selectProgramArea(location);
    }

    @Override
    public void onDrag(int x1, int y1, int x2, int y2) {
        ProgramLocation location = new ProgramLocation(x2, y2);
        modelController.drag(location);
    }

    @Override
    public void onRelease(int x, int y) {
        ProgramLocation location = new ProgramLocation(x, y);
        modelController.releaseProgramArea(location);
    }
}