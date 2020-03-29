package model.blocks;

import java.util.ArrayList;

import utilities.*;
import model.blocks.plugs.*;

/**
 * Class representing the while and if blocks with one socket on the top and one plug at the bottom. They also have one socket on their 
 * right side for a condition block.
 */
public class ModelWhileIfBlock extends ModelBlock implements TopSocket,BottomPlug,RightSocket{ 
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
    }

    /**
     * {@inheritDoc}
     * @author Oberon Swings
     */
    @Override
    public void disconnect() {
        if (this.getTopSocket() != null){
            ((BottomPlug)this.getTopSocket()).setBottomPlug(null);
            this.setTopSocket(null);
        }
        if (this.getBottomPlug() != null){
            ((TopSocket)this.getBottomPlug()).setTopSocket(null);
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
        else ((TopSocket)plug).setTopSocket(socket);
        if (socket == this){
            this.setCavityPlug(plug);
        }
        else  ((BottomPlug)socket).setBottomPlug(plug);
        socket.updatePos();
    }

    /**
     * {@inheritDoc}
     * @author Oberon Swings
     */
    @Override
    public void connect(ModelBlock block) {
        boolean connected;
        if ((block.hasBottomPlug() && this.getCavitySocketPos().getDistance(((BottomPlug)block).getBottomPlugPos()) < ModelBlock.PLUGSIZE)
            || (block.hasTopSocket() && this.getCavityPlugPos().getDistance(((TopSocket)block).getTopSocketPos()) < ModelBlock.PLUGSIZE)){
            connected = connectCavity(block);
            if (connected) return;
        }
        if (block.hasBottomPlug() && (this.getTopSocketPos().getDistance(((BottomPlug)block).getBottomPlugPos()) < ModelBlock.PLUGSIZE * 1.5)
        && ((BottomPlug) block).getBottomPlug() == null){
            this.setTopSocket(block);
            ((BottomPlug)block).setBottomPlug(this);
            this.setTopSocketPos(((BottomPlug) block).getBottomPlugPos());
        }
        else if (block.hasTopSocket() && (this.getBottomPlugPos().getDistance(((TopSocket)block).getTopSocketPos()) < ModelBlock.PLUGSIZE * 1.5)
        && ((TopSocket) block).getTopSocket() == null){
            this.setBottomPlug(block);
            ((TopSocket)block).setTopSocket(this);
            this.setBottomPlugPos(((TopSocket) block).getTopSocketPos());
        }
        else if (block.hasLeftPlug() && (this.getRightSocketPos().getDistance(((LeftPlug)block).getLeftPlugPos()) < ModelBlock.PLUGSIZE * 1.5)
        && ((LeftPlug) block).getLeftPlug() == null){
            this.setRightSocket(block);
            ((LeftPlug)block).setLeftPlug(this);
            this.setRightSocketPos(((LeftPlug) block).getLeftPlugPos());
        }
    }

    /**
     *
     * @param block the block that possible needs to be connected in the cavity
     * @return true if and only if block is connected within the cavity, false otherwise
     * @author Oberon Swings
     */
    public boolean connectCavity(ModelBlock block){
        if (block.hasBottomPlug() && this.getCavitySocketPos().getDistance(((BottomPlug)block).getBottomPlugPos()) < ModelBlock.PLUGSIZE){
            ModelBlock cavityPrevious = this.getCavitySocket(); //The previous block that was connected to the cavity
            this.setCavitySocket(block);
            ((BottomPlug)block).setBottomPlug(this);
            if (block.hasTopSocket() && !block.equals(cavityPrevious)){
                ((TopSocket)block).setTopSocket(cavityPrevious); //The previous block in the cavitysocket needs to connect with the modelBlock
                if (this.getCavityPlug() == this){
                    this.setCavityPlug(block);
                }
                else ((BottomPlug)cavityPrevious).setBottomPlug(block);
            }
            updateCavityBlocksLocations();
            return true;
        }
        if (block.hasTopSocket() && this.getCavityPlugPos().getDistance(((TopSocket)block).getTopSocketPos()) < ModelBlock.PLUGSIZE){
            ModelBlock cavityNext = this.getCavityPlug();
            this.setCavityPlug(block);
            ((TopSocket)block).setTopSocket(this);
            if (block.hasBottomPlug() && !block.equals(cavityNext)){
                ((BottomPlug)block).setBottomPlug(cavityNext);
                if (this.getCavitySocket() == this){
                    this.setCavitySocket(block);
                }
                else ((TopSocket)cavityNext).setTopSocket(block);
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
            if (((TopSocket)next).getTopSocket() == this){
                ((TopSocket)next).setTopSocketPos(this.getCavityPlugPos());
            }
            else ((TopSocket)next).setTopSocketPos(((BottomPlug)((TopSocket) next).getTopSocket()).getBottomPlugPos());
            next = ((BottomPlug)next).getBottomPlug();
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
        if (closest.hasBottomPlug() && extra.hasTopSocket() && ((TopSocket)extra).getTopSocketPos().getDistance(((BottomPlug)closest).getBottomPlugPos()) < ModelBlock.PLUGSIZE * 1.5){
            ModelBlock next = ((BottomPlug) closest).getBottomPlug();
            ((TopSocket) extra).setTopSocket(closest);
            ((BottomPlug) closest).setBottomPlug(extra);
            if (extra.hasBottomPlug() && !extra.equals(next)){
                ((BottomPlug)extra).setBottomPlug(next);
                if (!(next.equals(this))) ((TopSocket)next).setTopSocket(extra);
                else this.setCavitySocket(extra);
            }
            updateCavityBlocksLocations();
        }
        else if (closest.hasTopSocket() && extra.hasBottomPlug() && ((BottomPlug)extra).getBottomPlugPos().getDistance(((TopSocket)closest).getTopSocketPos()) < ModelBlock.PLUGSIZE * 1.5){
            ModelBlock next = ((TopSocket) closest).getTopSocket();
            ((BottomPlug) extra).setBottomPlug(closest);
            ((TopSocket) closest).setTopSocket(extra);
            if (extra.hasTopSocket() && !extra.equals(next)){
                ((TopSocket)extra).setTopSocket(next);
                if (!(next.equals(this))) ((BottomPlug)next).setBottomPlug(extra);
                else this.setCavityPlug(extra);
            }
            updateCavityBlocksLocations();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ModelBlock getTopSocket() {
        return this.topSocket;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTopSocket(ModelBlock topSocket) {
        this.topSocket = topSocket;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ModelBlock getBottomPlug() {
        return this.bottomPlug;
    }

    /**
     * {@inheritDoc}
     */
    @Override
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
     * 
     * @return the width of the block
     * @author Oberon Swings
     */
    /*public int getCavityWidth() {
        if(!(getCavityBlocks().size() == 0)){
            return getWidestBlockInCavity().getWidth() + STD_WIDTH;
        }
        else{
            return 0;
        }
    }function not used anymore because the cavity doesn't grow in width -Oberon*/

    /**
     * 
     * @return the widest block in the cavity of a if or while block
     * @author Oberon Swings
     */
   /* public ModelBlock getWidestBlockInCavity(){
        ModelBlock widest = getCavityBlocks().get(0);
        for(int i = 0; i < this.getCavityBlocks().size(); i++){
            ModelBlock current = this.getCavityBlocks().get(i);
            if(current.getWidth() > widest.getWidth()) widest = current;
        }
        return widest;
    }function not used anymore because the cavity doesn't grow in width -Oberon*/

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
                blk = ((BottomPlug)blk).getBottomPlug();
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
    @Override
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
    @Override
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
    @Override
    public void setRightSocketPos(WindowLocation pos) {
        super.setPos(pos.add(-this.getWidth() + ModelBlock.PLUGSIZE/2, -ModelBlock.STD_HEIGHT/3));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasTopSocket(){
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasBottomPlug(){
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasRightSocket(){
        return true;
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