package model;

import java.util.ArrayList;

import utilities.*;
import model.blocks.*;
import model.blocks.plugs.*;

/**
 * Class representing the Program Window where the blocks will be set.
 */
public class ModelProgramArea extends ModelWindow{

    private ArrayList<ModelBlock> blocks;

    // Constructor
    public ModelProgramArea(int width, int height){
        super(width, height);
        this.setBlocks(new ArrayList<ModelBlock>());
    }

    /**
     * This functionupdates the location of blocks. Can be usefull when a while if block has to resize, but should be used on every update.
     * @author Oberon Swings
     */
    public void updateLocationBlocks(){
        for (ModelBlock blk : this.getStartBlocks()){
            blk.updatePos();
        }
    }

    /**
     * 
     * @return a list of all the blocks that are connected
     * @author Oberon Swings
     */
    public ArrayList<ModelBlock> getConnectedBlocks(ModelBlock blk){
        ArrayList<ModelBlock> connectedBlocks = new ArrayList<ModelBlock>();
        connectedBlocks.add(blk);
        ArrayList<ModelBlock> nextConnection = blk.getConnections();
        while(!(nextConnection.isEmpty())){
            ModelBlock blk1 = nextConnection.get(0);
            connectedBlocks.add(blk1);
            nextConnection.addAll(blk1.getConnections());
            nextConnection.removeAll(connectedBlocks);
        }
        return connectedBlocks;
    }

    /**
     * 
     * @return true if all the blocks in the ProgramArea are connected
     * @author Oberon Swings
     */
    public Boolean allBlocksConnected(){
        if (this.getPABlocks().isEmpty()) return true;
        if (this.getStartBlocks().size() > 1) return false;
        ArrayList<ModelBlock> connectedB = getConnectedBlocks(this.getPABlocks().get(0));
        for (ModelBlock blk : getPABlocks()){
            if (!(connectedB.contains(blk))) return false;
            if (!(blk.equals(this.getFinishBlocks().get(0))||(blk.equals(this.getStartBlocks().get(0))))){
                return blk.isFullyConnected();
            }
            else if (blk.equals(this.getStartBlocks().get(0)) && blk.equals(this.getFinishBlocks().get(0))){
                if(blk.hasRightSocket() && ((RightSocket)blk).getRightSocket() == null) return false;
                if(blk.hasLeftPlug() && ((LeftPlug)blk).getLeftPlug() == null) return false;
            }
            else if(blk.equals(this.getFinishBlocks().get(0))) {
                if(blk.hasTopSocket() && ((TopSocket)blk).getTopSocket() == null) return false;
                if(blk.hasRightSocket() && ((RightSocket)blk).getRightSocket() == null) return false;
                if(blk.hasLeftPlug() && ((LeftPlug)blk).getLeftPlug() == null) return false;
            }
            else if(blk.equals(this.getStartBlocks().get(0))){
                if(blk.hasBottomPlug() && ((BottomPlug)blk).getBottomPlug() == null) return false;
                if(blk.hasRightSocket() && ((RightSocket)blk).getRightSocket() == null) return false;
                if(blk.hasLeftPlug() && ((LeftPlug)blk).getLeftPlug() == null) return false;
            }
        }
        return true;
    }

    /**
     * Checks if a pair of blocks has corresponding TopSocketPos and BottomPlugPos or RightSocketPos and LeftPlugPos
     * If so they connect, otherwise they don't
     * @author Oberon Swings
     */
    public void updateConnections(){
        for (ModelBlock blk : getPABlocks()){
            System.out.println("blk");
            for (ModelBlock blk1 : getPABlocks()){
                if (!(blk.equals(blk1))){
                    System.out.println("blk1");
                    if (blk.hasRightSocket() && blk1.hasLeftPlug() && ((RightSocket)blk).getRightSocketPos().equals(((LeftPlug)blk1).getLeftPlugPos())){
                        blk.connect(blk1);
                        System.out.println("if1");
                    }
                    System.out.println("else1");
                    if (blk.hasTopSocket() && blk1.hasBottomPlug() && ((TopSocket)blk).getTopSocketPos().equals(((BottomPlug)blk1).getBottomPlugPos())){
                        blk.connect(blk1);
                        System.out.println("if2");
                    }
                    System.out.println("else2");
                    if (blk instanceof ModelWhileIfBlock && ((blk1.hasTopSocket() && ((ModelWhileIfBlock) blk).getCavityPlugPos().equals(((TopSocket)blk1).getTopSocketPos()))
                            || (blk1.hasBottomPlug() && ((ModelWhileIfBlock) blk).getCavitySocketPos().equals(((BottomPlug)blk1).getBottomPlugPos())))){
                        blk.connect(blk1);
                        System.out.println("if3");
                    }
                    System.out.println("end blk1");
                }
                else System.out.println("same");
            }
            System.out.println("end blk");
        }
        System.out.println("end for");
    }

    /**
     * 
     * @return the starting blocks of the program, should be only one to be a valid start state
     * @author Oberon Swings
     */
    public ArrayList<ModelBlock> getStartBlocks(){
        ArrayList<ModelBlock> startBlocks = new ArrayList<>();
        for(ModelBlock blk : getPABlocks()){
            if(blk.hasTopSocket() && ((TopSocket)blk).getTopSocket() == null) startBlocks.add(blk);
        }
        return startBlocks;
    }

    /**
     * 
     * @return the finishing blocks of the program, should be only one to be a valid start state
     * @author Oberon Swings
     */
    public ArrayList<ModelBlock> getFinishBlocks(){
        ArrayList<ModelBlock> finishBlocks = new ArrayList<>();
        for(ModelBlock blk : getPABlocks()){
            if(blk.hasBottomPlug() && ((BottomPlug)blk).getBottomPlug() == null) finishBlocks.add(blk);
        }
        return finishBlocks;
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
    public void removeBlock(ModelBlock toBeRemoved){
        toBeRemoved.disconnect();
        this.blocks.remove(toBeRemoved);
    }

    /**
     * Add a block to the Program Area
     * 
     * TODO: remove debug print statements
     * 
     * @param toBeAdded the block which should be added
     */
    public void addBlock(ModelBlock toBeAdded){
        System.out.println("here");
        if(toBeAdded != null){
            this.blocks.add(toBeAdded);
            System.out.println("adds");
        }
        System.out.println("notadds");
        
    }

    /**
     * This function finds the block of which a matching socket/plug is closest to the given block's plug/socket
     * 
     * TODO: remove debug print statements
     * @param block The block for which the closest neighbour needs to be found
     * @return The closest neighbour of the block null if there is no closest block
     * @author Oberon Swings
     */
    public ModelBlock findClosestBlock(ModelBlock block){
        System.out.println("list length");
        System.out.println(this.getPABlocks().size());

        ModelBlock closest = null;
        int d = ModelBlock.STD_HEIGHT;

        if(block == null){
            return null;
        }
        for(int i = 0; i < this.getPABlocks().size(); i++){
            ModelBlock current = this.getPABlocks().get(i);
            System.out.println("INDEX IN LIST");
            System.out.println(i);
            System.out.println("CURRENT BLOCK ID");
            System.out.println(this.getPABlocks().get(i).getBlockType().getType());

            if (block.hasTopSocket() && current.hasBottomPlug() && ((TopSocket)block).getTopSocketPos().getDistance(((BottomPlug)current).getBottomPlugPos()) < d){
                closest = current;
                d = ((TopSocket)block).getTopSocketPos().getDistance(((BottomPlug)current).getBottomPlugPos());
            }
            if (block.hasBottomPlug() && current.hasTopSocket() && ((BottomPlug)block).getBottomPlugPos().getDistance(((TopSocket)current).getTopSocketPos()) < d){
                closest = current;
                d = ((BottomPlug)block).getBottomPlugPos().getDistance(((TopSocket)current).getTopSocketPos());
            }
            if (block.hasRightSocket() && current.hasLeftPlug() && ((RightSocket)block).getRightSocketPos().getDistance(((LeftPlug)current).getLeftPlugPos()) < d){
                closest = current;
                d = ((RightSocket)block).getRightSocketPos().getDistance(((LeftPlug)current).getLeftPlugPos());
            }
            if (block.hasLeftPlug() && current.hasRightSocket() && ((LeftPlug)block).getLeftPlugPos().getDistance(((RightSocket)current).getRightSocketPos()) < d){
                closest = current;
                d = ((LeftPlug)block).getLeftPlugPos().getDistance(((RightSocket)current).getRightSocketPos());
            }
        }
        return closest;             
    }

    /**
     * This function handles the mouseDown in the Program Area
     * Note that the blocks list has to be traversed in reverse 
     *  order due to rendering (ask Bert if unclear)
     * 
     * @param eventWindowLocation location of the mouseDown event
     * @return block to be returned 
     */
    public ModelBlock handleMouseDown(WindowLocation eventWindowLocation){
        for(int i = this.getPABlocks().size() - 1; i >= 0; i--){
            if(this.getPABlocks().get(i).inBounds(eventWindowLocation)){
                ModelBlock toBeReturned = this.getPABlocks().get(i);
                this.removeBlock(toBeReturned);
                return toBeReturned;
            }
        }
        return null;
    }

    /**
     * This function handles the mouse up in the ProgramArea
     * 
     * TODO: remove debug print statements
     * 
     * @param eveWindowLocation the location of the mouseUp event
     * @param activeB activeBlock the current active block
     */
    public void handleMouseUp(WindowLocation eveWindowLocation, ModelBlock activeB){
        this.addBlock(activeB);
        ModelBlock closest = this.findClosestBlock(activeB);
        if (closest != null){
            System.out.println("CLOSEST IS NOT NULL");
            System.out.println(closest.getBlockType().getType());
            System.out.println("list length2");
            System.out.println(this.getPABlocks().size());
            activeB.connect(closest);
            System.out.println("connection made");
            this.updateConnections();
            System.out.println("connections updated");
            this.updateLocationBlocks();
            System.out.println("locations updated");
        } 
        else{
            System.out.println("CLOSEST IS NULL");
        }
    }

   
}