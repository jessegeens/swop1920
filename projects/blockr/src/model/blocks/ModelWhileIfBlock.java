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
        //System.out.println("type1");
        //System.out.println(blk.getBlockType().getType());
        
        while(!(blk.equals(this))){
            cav.add(blk);
            if(blk.hasBottomPlug()){
                blk = blk.getBottomPlug();
            }
           // System.out.println("type2");
           // System.out.println(blk.getBlockType().getType());
            //the issue is that the block does not get the wileifblock as a bottomplug when connecting
            //so its bottom plug is null resulting in a nullpointer exception
            //else blk = this;//make sure that it doesn't form an infinite loop.
            //--Should be fixed now because connect works fine -Oberon
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
     * {@inheritDoc}
     * @author Oberon Swings
     */
    public Location getTopSocketPos() {
        return super.getPos().add(this.getWidth() / 2, + UIBlock.PLUGSIZE / 2);
    }

    /**
     * {@inheritDoc}
     * @author Oberon Swings
     */
    public void setTopSocketPos(Location pos) {
        super.setPos(pos.add(-this.getWidth()/2, -UIBlock.PLUGSIZE/2));
    }

    /**
     * {@inheritDoc}
     * @author Oberon Swings
     */
    public Location getBottomPlugPos() {
        return super.getPos().add(this.getWidth() / 2,+this.getHeight() + UIBlock.PLUGSIZE / 2);
    }

    /**
     * {@inheritDoc}
     * @author Oberon Swings
     */
    public void setBottomPlugPos(Location pos) {
        super.setPos(pos.add(-this.getWidth()/2, -this.getHeight() - UIBlock.PLUGSIZE/2));
    }

    /**
     * {@inheritDoc}
     * @author Oberon Swings
     */
    public Location getRightSocketPos() {
        return super.getPos().add(this.getWidth() - UIBlock.PLUGSIZE / 2, UIBlock.STD_HEIGHT / 3);
    }

    /**
     * {@inheritDoc}
     * @author Oberon Swings
     */
    public void setRightSocketPos(Location pos) {
        super.setPos(pos.add(-this.getWidth() + UIBlock.PLUGSIZE/2, -UIBlock.STD_HEIGHT/3));
    }

    /**
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
}