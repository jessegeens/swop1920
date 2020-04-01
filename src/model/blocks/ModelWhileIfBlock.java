package model.blocks;

import java.util.ArrayList;

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
    public ModelWhileIfBlock(WindowLocation pos, BlockType type){
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
     * {@inheritDoc}
     * @author Oberon Swings
     */
    @Override
    public void disconnect() {
        if (this.getTopSocket() != null){
            this.getTopSocket().setBottomPlug(null);
            this.setTopSocket(null);
        }
        if (this.getBottomPlug() != null){
            this.getBottomPlug().setTopSocket(null);
            this.setBottomPlug(null);
        }
        //The cavity should not be disconnected, I would rather move the cavity blocks with the while if block if this gets moved. -Oberon
    }

    /**
     * Disconnects block from the cavity and connects its upper and lower neighbour in the cavity to eachother.
     * @param block the block which is removed from the cavity.
     * @author Oberon Swings
     */
    public void disconnectCavity(ModelMoveBlock block){
        ModelBlock plug = block.getBottomPlug();
        ModelBlock socket = block.getTopSocket();
        block.setBottomPlug(null);
        block.setTopSocket(null);
        if (plug == this){
            this.setCavitySocket(socket);
        }
        else plug.setTopSocket(socket);
        if (socket == this){
            this.setCavityPlug(plug);
        }
        else socket.setBottomPlug(plug);
        socket.updatePos();
    }

    /**
     * {@inheritDoc}
     * @author Oberon Swings
     */
    @Override
    public void connect(ModelBlock block) {
        boolean connected;
        if ((block.hasBottomPlug() && this.getCavitySocketPos().getDistance(block.getBottomPlugPos()) < ModelBlock.PLUGSIZE)
            || (block.hasTopSocket() && this.getCavityPlugPos().getDistance(block.getTopSocketPos()) < ModelBlock.PLUGSIZE)){
            connected = connectCavity(block);
            if (connected) return;
        }
        if (block.hasBottomPlug() && (this.getTopSocketPos().getDistance(block.getBottomPlugPos()) < ModelBlock.PLUGSIZE * 1.5)
        && block.getBottomPlug() == null){
            this.setTopSocket(block);
            block.setBottomPlug(this);
            this.setTopSocketPos(block.getBottomPlugPos());
        }
        else if (block.hasTopSocket() && (this.getBottomPlugPos().getDistance(block.getTopSocketPos()) < ModelBlock.PLUGSIZE * 1.5)
        && block.getTopSocket() == null){
            this.setBottomPlug(block);
            block.setTopSocket(this);
            this.setBottomPlugPos(block.getTopSocketPos());
        }
        else if (block.hasLeftPlug() && (this.getRightSocketPos().getDistance(block.getLeftPlugPos()) < ModelBlock.PLUGSIZE * 1.5)
        && block.getLeftPlug() == null){
            this.setRightSocket(block);
            block.setLeftPlug(this);
            this.setRightSocketPos(block.getLeftPlugPos());
        }
    }

    /**
     *
     * @param block the block that possible needs to be connected in the cavity
     * @return true if and only if block is connected within the cavity, false otherwise
     * @author Oberon Swings
     */
    public boolean connectCavity(ModelBlock block){
        if (block.hasBottomPlug() && this.getCavitySocketPos().getDistance(block.getBottomPlugPos()) < ModelBlock.PLUGSIZE){
            ModelBlock cavityPrevious = this.getCavitySocket(); //The previous block that was connected to the cavity
            this.setCavitySocket(block);
            block.setBottomPlug(this);
            if (block.hasTopSocket() && !block.equals(cavityPrevious)){
                block.setTopSocket(cavityPrevious); //The previous block in the cavitysocket needs to connect with the modelBlock
                if (this.getCavityPlug() == this){
                    this.setCavityPlug(block);
                }
                else cavityPrevious.setBottomPlug(block);
            }
            updateCavityBlocksLocations();
            return true;
        }
        if (block.hasTopSocket() && this.getCavityPlugPos().getDistance(block.getTopSocketPos()) < ModelBlock.PLUGSIZE){
            ModelBlock cavityNext = this.getCavityPlug();
            this.setCavityPlug(block);
            block.setTopSocket(this);
            if (block.hasBottomPlug() && !block.equals(cavityNext)){
                block.setBottomPlug(cavityNext);
                if (this.getCavitySocket() == this){
                    this.setCavitySocket(block);
                }
                else cavityNext.setTopSocket(block);
            }
            updateCavityBlocksLocations();
            return true;
        }
        return false;
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
        if (next == this) return;
    }

    /**
     * When a block connects to another block that is in a cavity this should be handled by the surroundingWhileIfBlock, that is this block
     * This function makes sure the extra block is connected correctly to the closest block and is also taken account for within the cavity
     * @param extra the newly added block
     * @param closest the block closest to the new block
     * @author Oberon Swings
     */
    public void connectIntoCavity(ModelBlock extra, ModelBlock closest){
        if (closest.hasBottomPlug() && extra.hasTopSocket() && extra.getTopSocketPos().getDistance(closest.getBottomPlugPos()) < ModelBlock.PLUGSIZE * 1.5){
            ModelBlock next = closest.getBottomPlug();
            extra.setTopSocket(closest);
            closest.setBottomPlug(extra);
            if (extra.hasBottomPlug() && !extra.equals(next)){
                extra.setBottomPlug(next);
                if (!(next.equals(this))) next.setTopSocket(extra);
                else this.setCavitySocket(extra);
            }
            updateCavityBlocksLocations();
        }
        else if (closest.hasTopSocket() && extra.hasBottomPlug() && extra.getBottomPlugPos().getDistance(closest.getTopSocketPos()) < ModelBlock.PLUGSIZE * 1.5){
            ModelBlock next = closest.getTopSocket();
            extra.setBottomPlug(closest);
            closest.setTopSocket(extra);
            if (extra.hasTopSocket() && !extra.equals(next)){
                extra.setTopSocket(next);
                if (!(next.equals(this))) next.setBottomPlug(extra);
                else this.setCavityPlug(extra);
            }
            updateCavityBlocksLocations();
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
            return getCavityBlocks().size() * STD_HEIGHT;
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
    public WindowLocation getCavitySocketPos() {
        return this.getPos().add(2*ModelBlock.STD_WIDTH/3, this.getHeight() - ModelBlock.STD_HEIGHT/3);
    }

    /**
     * 
     * @return the position of the cavity plug
     * @author Oberon Swings
     */
    public WindowLocation getCavityPlugPos() {
        return this.getPos().add(2*ModelBlock.STD_WIDTH/3, 2*ModelBlock.STD_HEIGHT/3);
    }

    /**
     * {@inheritDoc}
     * @author Oberon Swings
     */
    public WindowLocation getTopSocketPos() {
        return super.getPos().add(this.getWidth() / 2, + ModelBlock.PLUGSIZE / 2);
    }

    /**
     * {@inheritDoc}
     * @author Oberon Swings
     */
    public void setTopSocketPos(WindowLocation pos) {
        super.setPos(pos.add(-this.getWidth()/2, -ModelBlock.PLUGSIZE/2));
    }

    /**
     * {@inheritDoc}
     * @author Oberon Swings
     */
    public WindowLocation getBottomPlugPos() {
        return super.getPos().add(this.getWidth() / 2,+this.getHeight() + ModelBlock.PLUGSIZE / 2);
    }

    /**
     * {@inheritDoc}
     * @author Oberon Swings
     */
    public void setBottomPlugPos(WindowLocation pos) {
        super.setPos(pos.add(-this.getWidth()/2, -this.getHeight() - ModelBlock.PLUGSIZE/2));
    }

    /**
     * {@inheritDoc}
     * @author Oberon Swings
     */
    public WindowLocation getRightSocketPos() {
        return super.getPos().add(this.getWidth() - ModelBlock.PLUGSIZE / 2, ModelBlock.STD_HEIGHT / 3);
    }

    /**
     * {@inheritDoc}
     * @author Oberon Swings
     */
    public void setRightSocketPos(WindowLocation pos) {
        super.setPos(pos.add(-this.getWidth() + ModelBlock.PLUGSIZE/2, -ModelBlock.STD_HEIGHT/3));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPos(WindowLocation pos) {
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

        while( current.getClass() != ModelWallInFrontBlock.class){  
            if(current.getClass() == ModelNotBlock.class){
                not = !not;
            }
            if(current.getClass() == ModelWallInFrontBlock.class){
                break;
            }
            current = ((ModelNotBlock) current).getRightSocket();  
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