package model;

import model.blocks.*;

public class ConnectionController {


    public ConnectionController() {
    }

    /**
     * Disconnecting by setting the connections of block a to null.
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
        //The cavity should not be disconnected, I would rather move the cavity blocks with the while if block if this gets moved. -Oberon
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
        socket.updatePos();
    }

    /**
     * Connecting by setting the correct connections of blocks a and b to point to eachother.
     * @param a first block
     * @param b second block
     * @author Oberon Swings
     */
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
            a.setTopSocket(b);
            b.setBottomPlug(a);
            //a.setTopSocketPos(b.getBottomPlugPos());
        }
        else if (a.hasBottomPlug() && b.hasTopSocket() && b.getTopSocket() == null){
            a.setBottomPlug(b);
            b.setTopSocket(a);
            //a.setBottomPlugPos(b.getTopSocketPos());
        }
        else if (a.hasRightSocket() && b.hasLeftPlug() && b.getLeftPlug() == null){
            a.setRightSocket(b);
            b.setLeftPlug(a);
            //a.setRightSocketPos(b.getLeftPlugPos());
        }
        else if (a.hasLeftPlug() && b.hasRightSocket() && b.getLeftPlug() == null){
            a.setLeftPlug(b);
            b.setRightSocket(a);
            //a.setRightSocketPos(b.getLeftPlugPos());
        }
    }

    /**
     *
     * @param b the block that possible needs to be connected in the cavity
     * @return true if and only if block is connected within the cavity, false otherwise
     * @author Oberon Swings
     */
    public boolean connectCavity(ModelWhileIfBlock a, ModelBlock b){
        if (b.hasBottomPlug() && a.getCavitySocketPos().getDistance(b.getBottomPlugPos()) < ModelBlock.PLUGSIZE){
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
            //updateCavityBlocksLocations();
            return true;
        }
        if (b.hasTopSocket() && a.getCavityPlugPos().getDistance(b.getTopSocketPos()) < ModelBlock.PLUGSIZE){
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
            //updateCavityBlocksLocations();
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
    public void connectIntoCavity(ModelWhileIfBlock a, ModelBlock extra, ModelBlock closest){
        if (closest.hasBottomPlug() && extra.hasTopSocket() && extra.getTopSocketPos().getDistance(closest.getBottomPlugPos()) < ModelBlock.PLUGSIZE * 1.5){
            ModelBlock next = closest.getBottomPlug();
            extra.setTopSocket(closest);
            closest.setBottomPlug(extra);
            if (extra.hasBottomPlug() && !extra.equals(next)){
                extra.setBottomPlug(next);
                if (!(next.equals(a))) next.setTopSocket(extra);
                else a.setCavitySocket(extra);
            }
            //updateCavityBlocksLocations();
        }
        else if (closest.hasTopSocket() && extra.hasBottomPlug() && extra.getBottomPlugPos().getDistance(closest.getTopSocketPos()) < ModelBlock.PLUGSIZE * 1.5){
            ModelBlock next = closest.getTopSocket();
            extra.setBottomPlug(closest);
            closest.setTopSocket(extra);
            if (extra.hasTopSocket() && !extra.equals(next)){
                extra.setTopSocket(next);
                if (!(next.equals(a))) next.setBottomPlug(extra);
                else a.setCavityPlug(extra);
            }
            //updateCavityBlocksLocations();
        }
    }
}
