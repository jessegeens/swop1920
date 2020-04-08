package model;

import model.blocks.*;
import ui.UIBlock;
import utilities.*;
import java.util.ArrayList;

public class LocationHandler {

    private ArrayList<ModelBlock> blocks;

    public LocationHandler() {
    }


    public void setLocationBlock(ModelBlock block, Location location){
        block.setPos(location);
        if (block instanceof ModelWhileIfBlock){
            updateCavityBlocksLocations((ModelWhileIfBlock) block);
        }
    }

    /**
     * This functionupdates the location of blocks. Can be usefull when a while if block has to resize, but should be used on every update.
     * @param startBlocks the blocks where a blockcluster starts so the top left block
     * @author Oberon Swings
     */
    public void updateLocationBlocks(ArrayList<ModelBlock> startBlocks){
        for (ModelBlock blk : startBlocks){
            updateLocationBlock(blk);
        }
    }

    /**
     * Updates the position of the block according to their left and top connections to other blocks
     * The update goes from top to bottom and from left to right
     * @param block the block which position needs to be updated
     * @author Oberon Swings
     */
    public void updateLocationBlock(ModelBlock block){
        if (block instanceof ModelWhileIfBlock) updateCavityBlocksLocations((ModelWhileIfBlock) block);
        else{
            if (block.hasTopSocket() && block.getTopSocket() != null){
                setTopSocketLocation(block, block.getTopSocket());
            }
            if (block.hasBottomPlug() && block.getBottomPlug() != null){
                updateLocationBlock(block.getBottomPlug());
            }
            if (block.hasLeftPlug() && block.getLeftPlug() != null){
                setLeftPlugLocation(block, block.getLeftPlug());
            }
            if (block.hasRightSocket() && block.getRightSocket() != null){
                updateLocationBlock(block.getRightSocket());
            }
        }
    }

    /**
     * Updates the locations of the cavity blocks when one is added in the middle
     * This is much easier this way than trying to get this to work within the connect methods.
     * @param block the while/if block for which the location of the cavity blocks need to be updated
     * @author Oberon Swings
     */
    public void updateCavityBlocksLocations(ModelWhileIfBlock block){
        ModelBlock next = block.getCavityPlug();
        while (next != block && next != null){
            if (next.getTopSocket() == block){
                setTopSocketLocation(next, block);
            }
            else setTopSocketLocation(next, next.getTopSocket());
            next = next.getBottomPlug();
        }
    }

    /**
     * Sets the topSocket location of the toBeMoved block to the bottomPlug location of the reference block
     * @param toBeMoved the block that needs to be moved
     * @param reference the block to which the first one needs to be moved
     * @author Oberon Swings
     */
    public void setTopSocketLocation(ModelBlock toBeMoved, ModelBlock reference){
        Location referenceLocation;
        if (toBeMoved.isInCavity() && reference instanceof ModelWhileIfBlock){
            referenceLocation = ((ModelWhileIfBlock) reference).getCavityPlugPos();
        }
        else referenceLocation = reference.getBottomPlugPos();
        toBeMoved.setPos(referenceLocation.add(-UIBlock.STD_WIDTH/2, -UIBlock.PLUGSIZE/2));
    }

    /**
     * Sets the leftPlug location of the toBeMoved block to the rightSocket location of the reference block
     * @param toBeMoved the block that needs to be moved
     * @param reference the block to which the first one needs to be moved
     * @author Oberon Swings
     */
    public void setLeftPlugLocation(ModelBlock toBeMoved, ModelBlock reference){
        Location referenceLocation = reference.getRightSocketPos();
        toBeMoved.setPos(referenceLocation.add(UIBlock.PLUGSIZE/2, -UIBlock.STD_HEIGHT/2));
    }

    /**
     * This function finds the block of which a matching socket/plug is closest to the given block's plug/socket
     * @param block the block for which the closest neighbour needs to be found
     * @param blocks the group of blocks in which the closest needs to be found
     * @return the closest neighbour of the block, null if there is no closest block
     * @author Oberon Swings
     */
    public ModelBlock findClosestBlock(ModelBlock block, ArrayList<ModelBlock> blocks){
        ModelBlock closest = null;
        int d = UIBlock.STD_HEIGHT;
        if(block == null || blocks == null){
            return null;
        }
        for(int i = 0; i < blocks.size(); i++){
            ModelBlock current = blocks.get(i);
            if (current.compatibleTopBottom(block) && current.distanceTopBottom(block) < d){
                closest = current;
                d = current.distanceTopBottom(block);
            }
            if (block.compatibleTopBottom(current) && block.distanceTopBottom(current) < d){
                closest = current;
                d = block.distanceTopBottom(current);
            }
            if (block.compatibleLeftRight(current) && block.distanceLeftRight(current) < d){
                closest = current;
                d = block.distanceLeftRight(current);
            }
            if (current.compatibleLeftRight(block) && current.distanceLeftRight(block) < d){
                closest = current;
                d = current.distanceLeftRight(block);
            }
        }
        return closest;
    }
}
