package model;

import java.util.ArrayList;

import ui.UIBlock;
import utilities.*;
import model.blocks.*;

/**
 * Class representing the Program Window where the blocks will be set.
 */
public class ModelProgramArea{

    private final int MAX_BLOCKS = 15;
    private ArrayList<ModelBlock> blocks;
    private ConnectionHandler CH;
    private LocationHandler LH;

    // Constructor
    public ModelProgramArea(){
        blocks = new ArrayList<ModelBlock>();
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
     * Remove a block from the Program Area
     * 
     * @param toBeRemoved block that should be removed
     */
    public void removePABlock(ModelBlock toBeRemoved){
        CH.disconnect(toBeRemoved);
        blocks.remove(toBeRemoved);
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
     * @author Bert
     */
    public ModelBlock selectBlock(ProgramLocation eventWindowLocation){
        for(int i = blocks.size() - 1; i >= 0; i--){
            if(blocks.get(i).inBoundsOfElement(eventWindowLocation)){
                ModelBlock toBeReturned = blocks.get(i);
                removePABlock(toBeReturned);
                return toBeReturned;
            }
        }
        return null;
    }

    /**
     * This methods returns whether a block in the given location is connected to other blocks
     *
     * @param eventWindowLocation location of the mouseDown event
     * @return true if there is a block that is connected with at least one other block, false otherwise
     * @author Bert
     */
    public boolean connectedBlockHere(ProgramLocation eventWindowLocation){
        for(int i = blocks.size() - 1; i >= 0; i--){
            if(blocks.get(i).inBoundsOfElement(eventWindowLocation)){
                /*
                ModelBlock toBeReturned = blocks.get(i);
                if(CH.isConnected(blocks.get(i))){
                    return true;
                }
                */
                //TODO refactor so CH actually processes this?
                if(blocks.get(i).getConnectionsNoCavity().size() > 0){
                    //System.out.println("YES CONNECTION");
                    return true;

                }

            }
        }
        //System.out.println("NO CONNECTION");
        return false;
    }

    /**
     * This function handles the mouse up in the ProgramArea
     * @param eveWindowLocation the location of the mouseUp event
     * @param activeB activeBlock the current active block
     * @author Oberon Swings
     */
    public boolean findAndConnect(ProgramLocation eveWindowLocation, ModelBlock activeB){
        this.addPABlock(activeB);
        ProgramLocation location = LH.moveToInBounds(eveWindowLocation);
        LH.setLocationBlock(activeB, location);
        ModelBlock closest = LH.findClosestBlock(activeB, blocks);
        boolean connection = false;
        if (closest != null){
            connection = CH.connect(closest, activeB);
            LH.updateLocationBlock(activeB);
            CH.updateConnections(blocks);
            LH.updateLocationBlocks(CH.getStartBlocks(blocks));

        }
        return connection;
    }

    /**
     * Moves a block to the location to which it is dragged
     * @param block the block that needs to be moved
     * @param location the location to which the block is dragged
     * @author Oberon Swings
     */
    public void dragBlock(ModelBlock block, ProgramLocation location){
        LH.setLocationBlock(block, location);
    }

    /**
     * Tests if the current state of the program Area is valid to start execution
     * @return true if all blocks are connected
     * @author Oberon Swings
     */
    public boolean validExecutionState(){
        return CH.allBlocksConnected(blocks);
    }

    /**
     * Gives the block at which the execution needs to start
     * @return the first block for execution
     * @author Oberon Swings
     */
    public ModelBlock getFirstBlock(){
        return CH.getStartBlocks(blocks).get(0);
    }

    /**
     * Tests if the maximum amount of blocks in the program area is reached
     * @return true if blocks.size >= MAX_BLOCKS
     * @author Oberon Swings
     */
    public boolean maxReached(){
        return (blocks.size() >= MAX_BLOCKS);
    }
   
}