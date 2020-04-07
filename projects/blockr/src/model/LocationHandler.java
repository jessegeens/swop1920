package model;

import model.blocks.*;
import ui.UIBlock;
import utilities.*;
import java.util.ArrayList;

public class LocationHandler {

    private ArrayList<ModelBlock> blocks;

    public LocationHandler() {
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
            setTopSocketPos();
            block.setTopSocketPos(block.getTopSocket().getBottomPlugPos());
        }
        if (block.hasBottomPlug() && block.getBottomPlug() != null){
            block.getBottomPlug().updatePos();
        }
        if (block.hasLeftPlug() && block.getLeftPlug() != null){
            block.setLeftPlugPos(block.getLeftPlug().getRightSocketPos());
        }
        if (block.hasRightSocket() && block.getRightSocket() != null){
            block.getRightSocket().updatePos();
        }
        if (block instanceof ModelWhileIfBlock) updateCavityBlocksLocations((ModelWhileIfBlock) block);
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
                next.setTopSocketPos(block.getCavityPlugPos());
            }
            else next.setTopSocketPos(next.getTopSocket().getBottomPlugPos());
            next = next.getBottomPlug();
        }
    }

    public void setTopSocketLocation(ModelBlock toBeMoved, ModelBlock reference){
        Location referenceLocation = reference.getBottomPlugPos();
        toBeMoved.setPos(referenceLocation.add(-UIBlock.STD_WIDTH/2, -UIBlock.PLUGSIZE/2));
    }

    public void setLeftPlugLocation(ModelBlock toBeMoved, ModelBlock reference){
        Location referenceLocation = reference.getRightSocketPos();
        toBeMoved.setPos(referenceLocation.add(UIBlock.PLUGSIZE/2, -UIBlock.STD_HEIGHT/2));
    }
    /*
    public void setTopSocketPos(Location pos, ModelBlock block) {
        block.setPos(pos.add(-block.getWidth()/2, -UIBlock.PLUGSIZE/2));
    }
    public void setLeftPlugPos(Location pos, ModelBlock block) {
        block.setPos(pos.add(UIBlock.PLUGSIZE/2, -block.getHeight()/2));
    }
*/

    /**
     * This function finds the block of which a matching socket/plug is closest to the given block's plug/socket
     *
     * TODO: remove debug print statements
     * @param block The block for which the closest neighbour needs to be found
     * @return The closest neighbour of the block null if there is no closest block
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
            if (block.hasTopSocket() && current.hasBottomPlug() && block.getTopSocketPos().getDistance(current.getBottomPlugPos()) < d){
                closest = current;
                d = block.getTopSocketPos().getDistance(current.getBottomPlugPos());
            }
            if (block.hasBottomPlug() && current.hasTopSocket() && (block.getBottomPlugPos().getDistance(current.getTopSocketPos()) < d)){
                closest = current;
                d = block.getBottomPlugPos().getDistance(current.getTopSocketPos());
            }
            if (block.hasRightSocket() && current.hasLeftPlug() && block.getRightSocketPos().getDistance(current.getLeftPlugPos()) < d){
                closest = current;
                d = block.getRightSocketPos().getDistance(current.getLeftPlugPos());
            }
            if (block.hasLeftPlug() && current.hasRightSocket() && block.getLeftPlugPos().getDistance(current.getRightSocketPos()) < d){
                closest = current;
                d = block.getLeftPlugPos().getDistance(current.getRightSocketPos());
            }
        }
        return closest;
    }

    public ConnectionPoint findClosestConnectionPoint(ModelBlock closest, ModelBlock active){
        int d = UIBlock.STD_WIDTH;
        int dTop = 0, dBottom = 0, dRight = 0, dLeft = 0, dCPlug = 0, dCSocket = 0;
        if (closest.hasTopSocket() && active.hasBottomPlug()){
            dTop = closest.getTopSocketPos().getDistance(active.getBottomPlugPos());
            if (dTop < d) d = dTop;
        }
        if (closest.hasBottomPlug() && active.hasTopSocket()){
            dBottom = closest.getBottomPlugPos().getDistance(active.getTopSocketPos());
            if (dBottom < d) d = dBottom;
        }
        if (closest.hasRightSocket() && active.hasLeftPlug()){
            dRight = closest.getRightSocketPos().getDistance(active.getLeftPlugPos());
            if (dRight < d) d = dRight;
        }
        if (closest.hasLeftPlug() && active.hasRightSocket()){
            dLeft = closest.getLeftPlugPos().getDistance(active.getRightSocketPos());
            if (dLeft < d) d = dLeft;
        }
        if (closest instanceof ModelWhileIfBlock && active.hasTopSocket()){
            dCPlug = ((ModelWhileIfBlock) closest).getCavityPlugPos().getDistance(active.getTopSocketPos());
            if (dCPlug < d) d = dCPlug;
        }
        if (closest instanceof ModelWhileIfBlock && active.hasBottomPlug()){
            dCSocket = ((ModelWhileIfBlock) closest).getCavitySocketPos().getDistance(active.getBottomPlugPos());
            if (dCSocket < d) d = dCSocket;
        }
        if (d == dTop) return ConnectionPoint.TOPSOCKET;
        if (d == dBottom) return ConnectionPoint.BOTTOMPLUG;
        if (d == dRight) return ConnectionPoint.RIGHTSOCKET;
        if (d == dLeft) return ConnectionPoint.LEFTPLUG;
        if (d == dCPlug) return ConnectionPoint.CAVITYPLUG;
        if (d == dCSocket) return ConnectionPoint.CAVITYSOCKET;
        return null;
    }

}
