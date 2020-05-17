package model;

import main.MyCanvasWindow;
import model.blocks.*;
import ui.UIBlock;
import utilities.*;
import java.util.ArrayList;

public class LocationHandler {

    private ArrayList<ModelBlock> blocks;

    public LocationHandler() {
    }


    public void setLocationBlock(ModelBlock block, ProgramLocation location){
        block.setPos(location);
        if (block instanceof ModelCavityBlock){
            updateCavityBlocksLocations((ModelCavityBlock) block);
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
        if (block.hasTopSocket() && block.getTopSocket() != null){
            setTopSocketLocation(block, block.getTopSocket());
        }
        if (block.hasBottomPlug() && block.getBottomPlug() != null){
            if (block.isInCavity() && !block.getSurroundingCavityBlock().equals(block.getBottomPlug()) || !block.isInCavity()){
                updateLocationBlock(block.getBottomPlug());
            }
        }
        updatePredicateBlocksLocations(block);
        if (block instanceof ModelCavityBlock) updateCavityBlocksLocations((ModelCavityBlock) block);
    }

    public void updatePredicateBlocksLocations(ModelBlock block){
        if (block.hasLeftPlug() && block.getLeftPlug() != null){
            setLeftPlugLocation(block, block.getLeftPlug());
        }
        if (block.hasRightSocket() && block.getRightSocket() != null){
            updateLocationBlock(block.getRightSocket());
        }
    }

    /**
     * Updates the locations of the cavity blocks when one is added in the middle
     * This is much easier this way than trying to get this to work within the connect methods.
     * @param block the while/if block for which the location of the cavity blocks need to be updated
     * @author Oberon Swings
     */
    public void updateCavityBlocksLocations(ModelCavityBlock block){
        ModelBlock next = block.getCavityPlug();
        while (next != block && next != null) {
            if (next instanceof ModelCavityBlock) {
                updateCavityBlocksLocations((ModelCavityBlock) next);
                updatePredicateBlocksLocations(next);
            }
            if (next.getTopSocket() == block) {
                setTopSocketLocation(next, block);
            }
            else setTopSocketLocation(next, next.getTopSocket());
            next = next.getBottomPlug();
        }
        updatePredicateBlocksLocations(block);
    }

    /**
     * Sets the topSocket location of the toBeMoved block to the bottomPlug location of the reference block
     * @param toBeMoved the block that needs to be moved
     * @param reference the block to which the first one needs to be moved
     * @author Oberon Swings
     */
    public void setTopSocketLocation(ModelBlock toBeMoved, ModelBlock reference){
        ProgramLocation referenceLocation;
        if (toBeMoved.isInCavity() && reference instanceof ModelCavityBlock){
            referenceLocation = ((ModelCavityBlock) reference).getCavityPlugPos();
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
        ProgramLocation referenceLocation = reference.getRightSocketPos();
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
        for (ModelBlock current : blocks) {
            if (current instanceof ModelCavityBlock) {
                if (((ModelCavityBlock) current).distanceCavitySocket(block) < d) {
                    closest = current;
                    d = ((ModelCavityBlock) current).distanceCavitySocket(block);
                }
                if (((ModelCavityBlock) current).distanceCavityPlug(block) < d) {
                    closest = current;
                    d = ((ModelCavityBlock) current).distanceCavityPlug(block);
                }
            }
            if (current.compatibleTopBottom(block) && current.distanceTopBottom(block) < d) {
                closest = current;
                d = current.distanceTopBottom(block);
            }
            if (block.compatibleTopBottom(current) && block.distanceTopBottom(current) < d) {
                closest = current;
                d = block.distanceTopBottom(current);
            }
            if (block.compatibleLeftRight(current) && block.distanceLeftRight(current) < d) {
                closest = current;
                d = block.distanceLeftRight(current);
            }
            if (current.compatibleLeftRight(block) && current.distanceLeftRight(block) < d) {
                closest = current;
                d = current.distanceLeftRight(block);
            }
        }
        return closest;
    }

    /**
     * If a block is dragged outside of the ProgramArea/Palette, its location
     * should not update but be restricted to the CanvasWindow and not
     * the game world
     *
     * @param eveWindowLocation the event window
     * @author Jesse Geens
     * @return ProgramLocation in bounds
     */
    public ProgramLocation moveToInBounds(ProgramLocation eveWindowLocation){
        if(eveWindowLocation.getX() > (MyCanvasWindow.WIDTH *2/3 - UIBlock.STD_WIDTH)) return new ProgramLocation(MyCanvasWindow.WIDTH *2/3 - UIBlock.STD_WIDTH, eveWindowLocation.getY());
        if(eveWindowLocation.getY() > (MyCanvasWindow.HEIGHT - UIBlock.STD_HEIGHT)) return new ProgramLocation(eveWindowLocation.getX(), MyCanvasWindow.HEIGHT - UIBlock.STD_HEIGHT);
        return eveWindowLocation;
    }
}
