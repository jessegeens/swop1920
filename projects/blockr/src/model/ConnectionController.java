package model;

import model.blocks.*;
import utilities.ConnectionPoint;

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
        //socket.updatePos();
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
     * @param p The plug/socket of the first block to which the second block needs to be connected
     * @author Oberon Swings
     */
    public void connect(ModelBlock closest, ModelBlock extra, ConnectionPoint p) {
        if ((closest instanceof ModelWhileIfBlock && extra.hasTopSocket() && p.equals(ConnectionPoint.CAVITYPLUG))) this.connectCavityPlug((ModelWhileIfBlock)closest,extra);
        else if ((closest instanceof ModelWhileIfBlock && extra.hasBottomPlug() && p.equals(ConnectionPoint.CAVITYSOCKET))) this.connectCavitySocket((ModelWhileIfBlock)closest,extra);
        else if ((closest.isInCavity() && closest.hasTopSocket() && extra.hasBottomPlug() && p.equals(ConnectionPoint.TOPSOCKET))) this.connectIntoCavityBottom((ModelWhileIfBlock)closest.getSurroundingWhileIfBlock(), extra, closest);
        else if ((closest.isInCavity() && extra.hasTopSocket() && closest.hasBottomPlug() && p.equals(ConnectionPoint.BOTTOMPLUG))) this.connectIntoCavityTop((ModelWhileIfBlock)closest.getSurroundingWhileIfBlock(), extra, closest);
        else if ((extra.isInCavity() && extra.hasTopSocket() && closest.hasBottomPlug() && p.equals(ConnectionPoint.TOPSOCKET))) this.connectIntoCavityBottom((ModelWhileIfBlock)extra.getSurroundingWhileIfBlock(), closest, extra);
        else if ((extra.isInCavity() && closest.hasTopSocket() && extra.hasBottomPlug() && p.equals(ConnectionPoint.BOTTOMPLUG))) this.connectIntoCavityTop((ModelWhileIfBlock)extra.getSurroundingWhileIfBlock(), closest, extra);
        else if (closest.hasTopSocket() && extra.hasBottomPlug() && p.equals(ConnectionPoint.TOPSOCKET)) this.connectTopBottom(extra, closest);
        else if (closest.hasBottomPlug() && extra.hasTopSocket() && p.equals(ConnectionPoint.BOTTOMPLUG)) this.connectTopBottom(closest, extra);
        else if (closest.hasLeftPlug() && extra.hasRightSocket() && p.equals(ConnectionPoint.LEFTPLUG)) this.connectRightLeft(extra, closest);
        else if (closest.hasRightSocket() && extra.hasLeftPlug() && p.equals(ConnectionPoint.RIGHTSOCKET)) this.connectRightLeft(closest, extra);
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
            //updateCavityBlocksLocations();
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
    public void connectIntoCavityTop(ModelWhileIfBlock a, ModelBlock extra, ModelBlock closest){
        if (closest.hasBottomPlug() && extra.hasTopSocket()){
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
    }

    /**
     * When a block connects to another block that is in a cavity this should be handled by the surroundingWhileIfBlock, that is this block
     * This function makes sure the extra block is connected correctly to the closest block and is also taken account for within the cavity
     * @param extra the newly added block
     * @param closest the block closest to the new block
     * @author Oberon Swings
     */
    public void connectIntoCavityBottom(ModelWhileIfBlock a, ModelBlock extra, ModelBlock closest){
        if (closest.hasTopSocket() && extra.hasBottomPlug()){
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
