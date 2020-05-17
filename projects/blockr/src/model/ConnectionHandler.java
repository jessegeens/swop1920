package model;

import model.blocks.*;
import ui.UIBlock;

import java.util.ArrayList;

public class ConnectionHandler {

    public static final int CONNECTIONDISTANCE = 20;

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
     * Connects block extra to the connectionPoint p of block closest.
     * @param closest first block
     * @param extra second block
     * @return true if connected
     * @author Oberon Swings (modified by Bert)
     */
    public boolean connect(ModelBlock closest, ModelBlock extra) {
        boolean connect = false;
        if (closest instanceof ModelCavityBlock){
            if (extra.hasTopSocket() && ((ModelCavityBlock) closest).distanceCavityPlug(extra) < CONNECTIONDISTANCE) connect = this.connectCavityPlug(((ModelCavityBlock)closest), extra);
            if (extra.hasBottomPlug() && ((ModelCavityBlock) closest).distanceCavitySocket(extra) < CONNECTIONDISTANCE) connect = this.connectCavitySocket((ModelCavityBlock) closest, extra);
        }
        if (connect) return connect; //Yes these are needed
        else{
            if (closest.isInCavity() && closest.compatibleTopBottom(extra) && closest.distanceTopBottom(extra) < CONNECTIONDISTANCE) connect = connectIntoCavityTop(extra, closest);
            if (closest.isInCavity() && extra.compatibleTopBottom(closest) && extra.distanceTopBottom(closest) < CONNECTIONDISTANCE) connect = connectIntoCavityBottom(extra, closest);
            if (extra.isInCavity() && extra.compatibleTopBottom(closest) && extra.distanceTopBottom(closest) < CONNECTIONDISTANCE) connect = connectIntoCavityTop(closest, extra);
            if (extra.isInCavity() && closest.compatibleTopBottom(extra) && closest.distanceTopBottom(extra) < CONNECTIONDISTANCE) connect = connectIntoCavityBottom(closest, extra);
        }
        if (connect) return connect;
        else{
            if (extra.compatibleTopBottom(closest) && extra.distanceTopBottom(closest) < CONNECTIONDISTANCE){
                this.connectTopBottom(extra, closest);
                connect = true;
            }
            if (closest.compatibleTopBottom(extra) && closest.distanceTopBottom(extra) < CONNECTIONDISTANCE){
                this.connectTopBottom(closest, extra);
                connect = true;
            }
            if (extra.compatibleLeftRight(closest) && extra.distanceLeftRight(closest) < CONNECTIONDISTANCE){
                this.connectRightLeft(closest, extra);
                connect = true;
            }
            if (closest.compatibleLeftRight(extra) && closest.distanceLeftRight(extra) < CONNECTIONDISTANCE){
                this.connectRightLeft(extra, closest);
                connect = true;
            }
        }
        return connect;
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
     * @return true if and only if block is connected within the cavity, false otherwise
     * @author Oberon Swings
     */
    public boolean connectCavitySocket(ModelCavityBlock a, ModelBlock b){
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
            return true;
        }
        return false;
    }

    /**
     *
     * @param a the block to connect to
     * @param b the block that possible needs to be connected in the cavity
     * @return true if and only if block is connected within the cavity, false otherwise
     * @author Oberon Swings
     */
    public boolean connectCavityPlug(ModelCavityBlock a, ModelBlock b){
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
            return true;
        }
        return false;
    }

    /**
     * When a block connects to another block that is in a cavity this should be handled by the surroundingWhileIfBlock, that is this block
     * This function makes sure the extra block is connected correctly to the closest block and is also taken account for within the cavity
     * @param extra the newly added block
     * @param closest the block closest to the new block
     * @return true of connected into top of the cavity
     * @author Oberon Swings
     */
    public boolean connectIntoCavityTop(ModelBlock extra, ModelBlock closest){
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
            return true;
        }
        return false;
    }

    /**
     * When a block connects to another block that is in a cavity this should be handled by the surroundingWhileIfBlock, that is this block
     * This function makes sure the extra block is connected correctly to the closest block and is also taken account for within the cavity
     * @param extra the newly added block
     * @param closest the block closest to the new block
     * @return true of connected into bottom of cavity
     * @author Oberon Swings
     */
    public boolean connectIntoCavityBottom(ModelBlock extra, ModelBlock closest){
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
            return true;
        }
        return false;
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
        if (this.getStartBlocks(blocks).size() > 1) return false;
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
