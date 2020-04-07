package model;

import model.blocks.*;
import ui.UIBlock;
import utilities.ConnectionPoint;

public class ConnectionHandler {


    public ConnectionHandler() {
    }

    /**
     * Disconnecting by setting the connections of block a to null.
     * This doesn't disconnect the cavity because we want to move the cavity blocks together with the if/while block when the if/while block is moved for instance
     * @param a the modelBlock which is disconnected
     * @author Oberon Swings
     */
    public void disconnect(ModelBlock a) {
        if (a.hasTopSocket() && a.getTopSocket() != null){
            a.getTopSocket().setBottomPlug(null);
            a.setTopSocket(null);
        }
        if (a.hasBottomPlug() && a.getBottomPlug() != null){
            a.getBottomPlug().setTopSocket(null);
            a.setBottomPlug(null);
        }
        if (a.hasRightSocket() && a.getRightSocket() != null){
            a.getRightSocket().setLeftPlug(null);
            a.setRightSocket(null);
        }
        if (a.hasLeftPlug() && a.getLeftPlug() != null){
            a.getLeftPlug().setRightSocket(null);
            a.setLeftPlug(null);
        }
    }

    /**
     * Disconnects block from the cavity and connects its upper and lower neighbour in the cavity to eachother.
     * @param a The while if block
     * @param b The cavityBlock
     * @author Oberon Swings
     */
    public void disconnectCavity(ModelWhileIfBlock a, ModelMoveBlock b){
        ModelBlock plug = b.getBottomPlug();
        ModelBlock socket = b.getTopSocket();
        b.setBottomPlug(null);
        b.setTopSocket(null);
        if (plug == a){
            a.setCavitySocket(socket);
        }
        else plug.setTopSocket(socket);
        if (socket == a){
            a.setCavityPlug(plug);
        }
        else socket.setBottomPlug(plug);
    }

    /**
     * Connecting by setting the correct connections of blocks a and b to point to eachother.
     * @param a first block
     * @param b second block
     * @author Oberon Swings
     */
    /*
    public void connect(ModelBlock a, ModelBlock b) {
        boolean connected;
        if ((a instanceof ModelWhileIfBlock && b.hasBottomPlug()) || (a instanceof ModelWhileIfBlock && b.hasTopSocket())){
            connected = connectCavity(((ModelWhileIfBlock)a), b);
            if (connected) return;
        }
        else if ((b instanceof ModelWhileIfBlock && a.hasBottomPlug()) || (b instanceof ModelWhileIfBlock && a.hasTopSocket())){
            connected = connectCavity(((ModelWhileIfBlock)b), a);
            if (connected) return;
        }
        if (a.isInCavity()){
            this.connectIntoCavity(((ModelWhileIfBlock)a.getSurroundingWhileIfBlock()),a,b);
        }
        else if (b.isInCavity()){
            this.connectIntoCavity(((ModelWhileIfBlock)b.getSurroundingWhileIfBlock()),a,b);
        }
        if (a.hasTopSocket() && b.hasBottomPlug() && b.getBottomPlug() == null){
            this.connectTopBottom(b,a);
            //a.setTopSocketPos(b.getBottomPlugPos());
        }
        else if (a.hasBottomPlug() && b.hasTopSocket() && b.getTopSocket() == null){
            this.connectTopBottom(a,b);
            //a.setBottomPlugPos(b.getTopSocketPos());
        }
        else if (a.hasRightSocket() && b.hasLeftPlug() && b.getLeftPlug() == null){
            this.connectRightLeft(b,a);
            //a.setRightSocketPos(b.getLeftPlugPos());
        }
        else if (a.hasLeftPlug() && b.hasRightSocket() && b.getLeftPlug() == null){
            this.connectRightLeft(a,b);
            //a.setRightSocketPos(b.getLeftPlugPos());
        }
    }*/

    /**
     * Connects block extra to the connectionPoint p of block closest.
     * @param closest first block
     * @param extra second block
     * @author Oberon Swings
     */
    public void connect(ModelBlock closest, ModelBlock extra) {
        boolean connect = false;
        if (closest instanceof ModelWhileIfBlock){
            if (extra.hasTopSocket() && ((ModelWhileIfBlock) closest).distanceCavityPlug(extra) < UIBlock.PLUGSIZE) connect = this.connectCavityPlug(((ModelWhileIfBlock)closest), extra);
            if (extra.hasBottomPlug() && ((ModelWhileIfBlock) closest).distanceCavitySocket(extra) < UIBlock.PLUGSIZE) connect = this.connectCavitySocket((ModelWhileIfBlock) closest, extra);
        }
        if (connect) return;
        else{
            if (closest.isInCavity() && closest.compatibleTopBottom(extra) && closest.distanceTopBottom(extra) < UIBlock.PLUGSIZE) connect = connectIntoCavityTop(extra, closest);
            if (closest.isInCavity() && extra.compatibleTopBottom(closest) && extra.distanceTopBottom(closest) < UIBlock.PLUGSIZE) connect = connectIntoCavityBottom(extra, closest);
            if (extra.isInCavity() && extra.compatibleTopBottom(closest) && extra.distanceTopBottom(closest) < UIBlock.PLUGSIZE) connect = connectIntoCavityTop(closest, extra);
            if (extra.isInCavity() && closest.compatibleTopBottom(extra) && closest.distanceTopBottom(extra) < UIBlock.PLUGSIZE) connect = connectIntoCavityBottom(closest, extra);
        }
        if (connect) return;
        else{
            if (extra.compatibleTopBottom(closest) && extra.distanceTopBottom(closest) < UIBlock.PLUGSIZE) this.connectTopBottom(extra, closest);
            if (closest.compatibleTopBottom(extra) && closest.distanceTopBottom(extra) < UIBlock.PLUGSIZE) this.connectTopBottom(closest, extra);
            if (extra.compatibleLeftRight(closest) && extra.distanceLeftRight(closest) < UIBlock.PLUGSIZE) this.connectRightLeft(closest, extra);
            if (closest.compatibleLeftRight(extra) && closest.distanceLeftRight(extra) < UIBlock.PLUGSIZE) this.connectRightLeft(extra, closest);
        }
    }

    /**
     * Connects two blocks in horizontal direction
     * @param right the block at the right
     * @param left the block at the left
     * @author Oberon Swings
     */
    public void connectRightLeft(ModelBlock right, ModelBlock left){
        if (left.getRightSocket() != null && right.hasRightSocket()){
            ModelBlock temp = left.getRightSocket();
            right.setRightSocket(temp);
            temp.setLeftPlug(right);
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
        if (top.getBottomPlug() != null && bottom.hasBottomPlug()){
            ModelBlock temp = top.getBottomPlug();
            bottom.setBottomPlug(temp);
            temp.setTopSocket(bottom);
        }
        top.setBottomPlug(bottom);
        bottom.setTopSocket(top);
    }

    /**
     *
     * @param b the block that possible needs to be connected in the cavity
     * @return true if and only if block is connected within the cavity, false otherwise
     * @author Oberon Swings
     */
    public boolean connectCavitySocket(ModelWhileIfBlock a, ModelBlock b){
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
     * @param b the block that possible needs to be connected in the cavity
     * @return true if and only if block is connected within the cavity, false otherwise
     * @author Oberon Swings
     */
    public boolean connectCavityPlug(ModelWhileIfBlock a, ModelBlock b){
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
     * @author Oberon Swings
     */
    public boolean connectIntoCavityTop(ModelBlock extra, ModelBlock closest){
        ModelWhileIfBlock a = closest.getSurroundingWhileIfBlock();
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
     * @author Oberon Swings
     */
    public boolean connectIntoCavityBottom(ModelBlock extra, ModelBlock closest){
        ModelWhileIfBlock a = closest.getSurroundingWhileIfBlock();
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
}
