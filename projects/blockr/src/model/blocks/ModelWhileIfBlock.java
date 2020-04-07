package model.blocks;

import java.util.ArrayList;

import ui.UIBlock;
import utilities.*;

/**
 * Class representing the while and if blocks with one socket on the top and one plug at the bottom. They also have one socket on their 
 * right side for a condition block.
 */
public class ModelWhileIfBlock extends ModelBlock{
    private ModelBlock topSocket;
    private ModelBlock bottomPlug;
    private ModelBlock rightSocket;
    private ModelBlock cavitySocket;
    private ModelBlock cavityPlug;

    // Constructor
    public ModelWhileIfBlock(Location pos, BlockType type){
        super(pos,type);

        this.setTopSocket(null);
        this.setBottomPlug(null);
        this.setRightSocket(null);
        this.setCavityPlug(this);
        this.setCavitySocket(this);
        ArrayList<ConnectionPoint> connectionPoints = new ArrayList<>();
        connectionPoints.add(ConnectionPoint.BOTTOMPLUG);
        connectionPoints.add(ConnectionPoint.TOPSOCKET);
        connectionPoints.add(ConnectionPoint.CAVITYPLUG);
        connectionPoints.add(ConnectionPoint.CAVITYSOCKET);
        connectionPoints.add(ConnectionPoint.RIGHTSOCKET);
        super.setConnectionPoints(connectionPoints);
    }


    /**
     * TODO move to LocationController
     * Updates the locations of the cavity blocks when one is added in the middle
     * This is much easier this way than trying to get this to work within the connect methods.
     * @author Oberon Swings
     */
    public void updateCavityBlocksLocations(){
        ModelBlock next = this.getCavityPlug();
        while (next != this && next != null){
            if (next.getTopSocket() == this){
                next.setTopSocketPos(this.getCavityPlugPos());
            }
            else next.setTopSocketPos(next.getTopSocket().getBottomPlugPos());
            next = next.getBottomPlug();
        }
    }

    /**
     * {@inheritDoc}
     */
    public ModelBlock getTopSocket() {
        return this.topSocket;
    }

    /**
     * {@inheritDoc}
     */
    public void setTopSocket(ModelBlock topSocket) {
        this.topSocket = topSocket;
    }

    /**
     * {@inheritDoc}
     */
    public ModelBlock getBottomPlug() {
        return this.bottomPlug;
    }

    /**
     * {@inheritDoc}
     */
    public void setBottomPlug(ModelBlock bottomPlug) {
        this.bottomPlug = bottomPlug;
    }

    

    /**
     * Getter for the height of the cavity of the while and if block.
     * @return the height of the block
     * @author Oberon Swings
     */
    public int getCavityHeight() {
        if(!(getCavityBlocks().size() == 0)){
            return getCavityBlocks().size() * UIBlock.STD_HEIGHT;
        }
        else{
            return 0;
        } 
    }

    /**
     * Getter for a list of blocks this block has in its cavity.
     * @return the list of blocks.
     * @author Oberon Swings
     */
    public ArrayList<ModelBlock> getCavityBlocks() {
        ArrayList<ModelBlock> cav = new ArrayList<ModelBlock>();
        ModelBlock blk = this.getCavityPlug();
        while(!(blk.equals(this) || blk == null)){
            cav.add(blk);
            if (blk == null) System.out.println("The cavity of the while/if block is not connected properly, got null somewhere in the link");
            if(blk.hasBottomPlug()){
                blk = blk.getBottomPlug();
            }
        }
        return cav;
    }

    /**
     * {@inheritDoc}
     */
    public ModelBlock getRightSocket() {
        return this.rightSocket;
    }

    /**
     * {@inheritDoc}
     */
    public void setRightSocket(ModelBlock rightSocket) {
        this.rightSocket = rightSocket;
    }

    /**
     * 
     * @return the cavity of the socket.
     */
    public ModelBlock getCavitySocket() {
        return this.cavitySocket;
    }

    /**
     * 
     * @param cavitySocket sets the cavity of the socket.
     */
    public void setCavitySocket(ModelBlock cavitySocket) {
        this.cavitySocket = cavitySocket;
    }

    /**
     * 
     * @return the cavity of the plug.
     */
    public ModelBlock getCavityPlug() {
        return this.cavityPlug;
    }

    /**
     * 
     * @param cavityPlug sets the cavity of the plug.
     */
    public void setCavityPlug(ModelBlock cavityPlug) {
        this.cavityPlug = cavityPlug;
    }

    /**
     * 
     * @return the position of the cavity socket
     * @author Oberon Swings
     */
    public Location getCavitySocketPos() {
        return this.getPos().add(2*UIBlock.STD_WIDTH/3, this.getHeight() - UIBlock.STD_HEIGHT/3);
    }

    /**
     * 
     * @return the position of the cavity plug
     * @author Oberon Swings
     */
    public Location getCavityPlugPos() {
        return this.getPos().add(2*UIBlock.STD_WIDTH/3, 2*UIBlock.STD_HEIGHT/3);
    }

    /**
     * TODO move to LocationController
     * {@inheritDoc}
     */
    @Override
    public void setPos(Location pos) {
        super.setPos(pos);
        this.updateCavityBlocksLocations();
    }

    /**
     * Method which returns the condition that results from the blocks hanging to the right
     * 
     * @return the condition
     * @throws NoSuchFieldException if the WHILEIF rightsocket or the chain of condition blocks has an empty right sockket
     * (method should only be called when the blocks are in a valid state to run the program)
     * 
     * @author Bert De Vleeschouwer
     */
    public Condition getCondition() throws NoSuchFieldException {
        boolean not = false;

        ModelBlock current = this.getRightSocket();
        if(current == null){
            throw new NoSuchFieldException("the WHILEIF block socket is null");
        }

        while(!(current instanceof ModelWallInFrontBlock)){
            if(current instanceof ModelNotBlock){
                not = !not;
            }
            if (current.hasRightSocket()) current = current.getRightSocket();
            if(current == null){
                throw new NoSuchFieldException("the right socket of a condition block is null");
            }
        }

        Condition toBeReturned;

        if(not){
            toBeReturned = Condition.NOT_WALL_IN_FRONT;
        }
        else{
            toBeReturned = Condition.WALL_IN_FRONT;
        }

        return toBeReturned;
    }

    /**
     * Calculates the distance between cavityPlug and TopSocket
     * @param top the block with the topSocket
     * @return the distance form the cavityPlug to the topSocket of the top block
     */
    public int distanceCavityPlug(ModelBlock top){
        return this.getCavityPlugPos().getDistance(top.getTopSocketPos());
    }

    /**
     * Calculates the distance between cavitySocket and bottomPlug
     * @param bottom the block with the bottomPlug
     * @return the distance form the cavitySocket to the bottomPlug of the bottom block
     */
    public int distanceCavitySocket(ModelBlock bottom){
        return this.getCavitySocketPos().getDistance(bottom.getBottomPlugPos());
    }
}