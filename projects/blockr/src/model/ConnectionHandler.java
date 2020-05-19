package model;

import model.blocks.*;
import ui.UIBlock;

import java.util.ArrayList;

public class ConnectionHandler {

    private static ConnectionHandler instance;

    private ConnectionHandler() {
    }

    public static ConnectionHandler getInstance() {
        if (instance == null) {
            instance = new ConnectionHandler();
        }
        return instance;
    }

    /**
     * Disconnecting by setting the connections of block a to null.
     * This doesn't disconnect the cavity because we want to move the cavity blocks together with the if/while block when the if/while block is moved for instance
     * @param a the modelBlock which is disconnected
     * @author Oberon Swings
     */
    public void disconnect(ModelBlock a) {
        if (a.isInCavity()){
            disconnectCavity(a);
        }
        if (a.hasTopSocket() && a.getTopSocket() != null){
            a.getTopSocket().setBottomPlug(null);
            a.setTopSocket(null);
        }
        if (a.hasBottomPlug() && a.getBottomPlug() != null){
            a.getBottomPlug().setTopSocket(null);
            a.setBottomPlug(null);
        }
        if (a.hasLeftPlug() && a.getLeftPlug() != null){
            a.getLeftPlug().setRightSocket(null);
            a.setLeftPlug(null);
        }
    }

    /**
     * Disconnects block from the cavity and connects its upper and lower neighbour in the cavity to eachother.
     * @param a The cavityBlock
     * @author Oberon Swings
     */
    public void disconnectCavity(ModelBlock a){
        ModelCavityBlock b = a.getSurroundingCavityBlock();
        ModelBlock plug = a.getBottomPlug();
        ModelBlock socket = a.getTopSocket();
        a.setBottomPlug(null);
        a.setTopSocket(null);
        if (plug == b){
            b.setCavitySocket(socket);
        }
        else plug.setTopSocket(socket);
        if (socket == b){
            b.setCavityPlug(plug);
        }
        else socket.setBottomPlug(plug);
    }

    /**
     * Connects block extra to block closest.
     * @param closest first block
     * @param extra second block
     * @return true if connected
     * @author Oberon Swings (modified by Bert)
     */
    public void connect(ModelBlock closest, ModelBlock extra) {
        int d = LocationHandler.getInstance().getClosestdistance(extra, closest);
        if (closest instanceof ModelCavityBlock && extra.hasTopSocket() && ((ModelCavityBlock) closest).distanceCavityPlug(extra) == d) connectCavityPlug(((ModelCavityBlock)closest), extra);
        else if (closest instanceof ModelCavityBlock && extra.hasBottomPlug() && ((ModelCavityBlock) closest).distanceCavitySocket(extra) == d) connectCavitySocket((ModelCavityBlock) closest, extra);
        else if (closest.isInCavity() && closest.compatibleTopBottom(extra) && closest.distanceTopBottom(extra) == d) connectIntoCavityTop(extra, closest);
        else if (closest.isInCavity() && extra.compatibleTopBottom(closest) && extra.distanceTopBottom(closest) == d) connectIntoCavityBottom(extra, closest);
        else if (extra.isInCavity() && extra.compatibleTopBottom(closest) && extra.distanceTopBottom(closest) == d) connectIntoCavityTop(closest, extra);
        else if (extra.isInCavity() && closest.compatibleTopBottom(extra) && closest.distanceTopBottom(extra) == d) connectIntoCavityBottom(closest, extra);
        else if (extra.compatibleTopBottom(closest) && extra.distanceTopBottom(closest) == d){
            this.connectTopBottom(extra, closest);
        }
        else if (closest.compatibleTopBottom(extra) && closest.distanceTopBottom(extra) == d){
            this.connectTopBottom(closest, extra);
        }
        else if (extra.compatibleLeftRight(closest) && extra.distanceLeftRight(closest) == d){
            this.connectRightLeft(closest, extra);
        }
        else if (closest.compatibleLeftRight(extra) && closest.distanceLeftRight(extra) == d){
            this.connectRightLeft(extra, closest);
        }
    }

    /**
     * Connects two blocks in horizontal direction
     * @param right the block at the right
     * @param left the block at the left
     * @author Oberon Swings
     */
    public void connectRightLeft(ModelBlock right, ModelBlock left){
        if (right.getLeftPlug() != null && left.hasLeftPlug() && !left.equals(right.getLeftPlug())){
            ModelBlock temp = right.getLeftPlug();
            left.setLeftPlug(temp);
            temp.setRightSocket(left);
        }
        left.setRightSocket(right);
        right.setLeftPlug(left);
    }

    /**
     * Connects two blocks in vertical direction
     * @param top the block at the top
     * @param bottom the block at the bottom
     * @author Oberon Swings
     */
    public void connectTopBottom(ModelBlock top, ModelBlock bottom){
        if (top.getBottomPlug() != null && bottom.hasBottomPlug() && !bottom.equals(top.getBottomPlug())){
            ModelBlock temp = top.getBottomPlug();
            bottom.setBottomPlug(temp);
            temp.setTopSocket(bottom);
        }
        top.setBottomPlug(bottom);
        bottom.setTopSocket(top);
    }

    /**
     *
     * @param a the block to connect to
     * @param b the block that possible needs to be connected in the cavity
     * @author Oberon Swings
     */
    public void connectCavitySocket(ModelCavityBlock a, ModelBlock b){
        if (b.hasBottomPlug()){
            ModelBlock cavityPrevious = a.getCavitySocket(); //The previous block that was connected to the cavity
            a.setCavitySocket(b);
            b.setBottomPlug(a);
            if (b.hasTopSocket() && !b.equals(cavityPrevious)){
                b.setTopSocket(cavityPrevious); //The previous block in the cavitysocket needs to connect with the modelBlock
                if (a.getCavityPlug() == a){
                    a.setCavityPlug(b);
                }
                else cavityPrevious.setBottomPlug(b);
            }
        }
    }

    /**
     *
     * @param a the block to connect to
     * @param b the block that possible needs to be connected in the cavity
     * @author Oberon Swings
     */
    public void connectCavityPlug(ModelCavityBlock a, ModelBlock b){
        if (b.hasTopSocket()){
            ModelBlock cavityNext = a.getCavityPlug();
            a.setCavityPlug(b);
            b.setTopSocket(a);
            if (b.hasBottomPlug() && !b.equals(cavityNext)){
                b.setBottomPlug(cavityNext);
                if (a.getCavitySocket() == a){
                    a.setCavitySocket(b);
                }
                else cavityNext.setTopSocket(b);
            }
        }
    }

    /**
     * When a block connects to another block that is in a cavity this should be handled by the surroundingWhileIfBlock, that is this block
     * This function makes sure the extra block is connected correctly to the closest block and is also taken account for within the cavity
     * @param extra the newly added block
     * @param closest the block closest to the new block
     * @author Oberon Swings
     */
    public void connectIntoCavityTop(ModelBlock extra, ModelBlock closest){
        ModelCavityBlock a = closest.getSurroundingCavityBlock();
        if (closest.compatibleTopBottom(extra)){
            ModelBlock next = closest.getBottomPlug();
            extra.setTopSocket(closest);
            closest.setBottomPlug(extra);
            if (extra.hasBottomPlug() && !extra.equals(next)){
                extra.setBottomPlug(next);
                if (!(next.equals(a))) next.setTopSocket(extra);
                else a.setCavitySocket(extra);
            }
        }
    }

    /**
     * When a block connects to another block that is in a cavity this should be handled by the surroundingWhileIfBlock, that is this block
     * This function makes sure the extra block is connected correctly to the closest block and is also taken account for within the cavity
     * @param extra the newly added block
     * @param closest the block closest to the new block
     * @author Oberon Swings
     */
    public void connectIntoCavityBottom(ModelBlock extra, ModelBlock closest){
        ModelCavityBlock a = closest.getSurroundingCavityBlock();
        if (extra.compatibleTopBottom(closest)){
            ModelBlock next = closest.getTopSocket();
            extra.setBottomPlug(closest);
            closest.setTopSocket(extra);
            if (extra.hasTopSocket() && !extra.equals(next)){
                extra.setTopSocket(next);
                if (!(next.equals(a))) next.setBottomPlug(extra);
                else a.setCavityPlug(extra);
            }
        }
    }

    /**
     * @return whether the block is fully connected (at all ends)
     * @param block the block for which the connections are checked.
     * @author Oberon Swings
     */
    public boolean isFullyConnected(ModelBlock block) {
        if(block.hasTopSocket() && block.getTopSocket() == null) return false;
        if(block.hasBottomPlug() && block.getBottomPlug() == null) return false;
        if(block.hasRightSocket() && block.getRightSocket() == null) return false;
        if(block.hasLeftPlug() && block.getLeftPlug() == null) return false;
        return true;
    }

    /**
     * @param blocks list of blocks to check
     * @return true if all the blocks in the ProgramArea are connected
     * @author Oberon Swings
     */
    public Boolean allBlocksConnected(ArrayList<ModelBlock> blocks){
        if (blocks.isEmpty()) return true;
        if (this.getStartBlocks(blocks).size() > 1 || this.getStartBlocks(blocks).size() == 0) return false;
        for (ModelBlock blk : blocks){
            if (!(blk.equals(getFinishBlocks(blocks).get(0))||(blk.equals(getStartBlocks(blocks).get(0))))){
                return isFullyConnected(blk);
            }
            else if (blk.equals(this.getStartBlocks(blocks).get(0)) && blk.equals(this.getFinishBlocks(blocks).get(0))){
                if(blk.hasRightSocket() && blk.getRightSocket() == null) return false;
                if(blk.hasLeftPlug() && blk.getLeftPlug() == null) return false;
            }
            else if(blk.equals(this.getFinishBlocks(blocks).get(0))) {
                if(blk.hasTopSocket() && blk.getTopSocket() == null) return false;
                if(blk.hasRightSocket() && blk.getRightSocket() == null) return false;
                if(blk.hasLeftPlug() && blk.getLeftPlug() == null) return false;
            }
            else if(blk.equals(this.getStartBlocks(blocks).get(0))){
                if(blk.hasBottomPlug() && blk.getBottomPlug() == null) return false;
                if(blk.hasRightSocket() && blk.getRightSocket() == null) return false;
                if(blk.hasLeftPlug() && blk.getLeftPlug() == null) return false;
            }
        }
        return true;
    }

    /**
     * @param blocks list of blocks to check
     * @return the starting blocks of the program, should be only one to be a valid start state
     * @author Oberon Swings
     */
    public ArrayList<ModelBlock> getStartBlocks(ArrayList<ModelBlock> blocks){
        ArrayList<ModelBlock> startBlocks = new ArrayList<>();
        for(ModelBlock blk : blocks){
            if(blk.hasTopSocket() && blk.getTopSocket() == null) startBlocks.add(blk);
        }
        return startBlocks;
    }

    /**
     * @param blocks list of blocks to check
     * @return the finishing blocks of the program, should be only one to be a valid start state
     * @author Oberon Swings
     */
    public ArrayList<ModelBlock> getFinishBlocks(ArrayList<ModelBlock> blocks){
        ArrayList<ModelBlock> finishBlocks = new ArrayList<>();
        for(ModelBlock blk : blocks){
            if(blk.hasBottomPlug() && blk.getBottomPlug() == null) finishBlocks.add(blk);
        }
        return finishBlocks;
    }

    /**
     * @param blk the block to connect
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
     * Checks if a pair of blocks has corresponding TopSocketPos and BottomPlugPos or RightSocketPos and LeftPlugPos
     * If so they connect, otherwise they don't
     * @param blocks the list of blocks to check
     * @author Oberon Swings
     */
    public void updateConnections(ArrayList<ModelBlock> blocks){
        for (ModelBlock blk : blocks){
            for (ModelBlock blk1 : blocks){
                if (!(blk.equals(blk1))){
                    if (blk.compatibleLeftRight(blk1) && blk.distanceLeftRight(blk1) == 0){
                        connectRightLeft(blk1,blk);
                    }
                    if (blk.compatibleTopBottom(blk1) && blk.distanceTopBottom(blk1) == 0){
                        connectTopBottom(blk,blk1);
                    }
                    if (blk instanceof ModelCavityBlock && blk1.hasTopSocket() && ((ModelCavityBlock) blk).distanceCavityPlug(blk1) == 0){
                        connectCavityPlug((ModelCavityBlock) blk,blk1);
                    }
                    if (blk instanceof ModelCavityBlock && blk1.hasBottomPlug() && ((ModelCavityBlock) blk).distanceCavitySocket(blk1) == 0){
                        connectCavitySocket((ModelCavityBlock) blk, blk1);
                    }
                }
            }
        }
    }
}
