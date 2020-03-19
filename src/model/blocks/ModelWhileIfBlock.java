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
    public ModelWhileIfBlock(WindowLocation pos, Blocktype type){
        super(pos,type);

        this.setTopSocket(null);
        this.setBottomPlug(null);
        this.setRightSocket(null);
        this.setCavityPlug(this);
        this.setCavitySocket(this);
    }

    /**
     * {@inheritDoc}
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
        /*if (this.getCavityPlug() != null){
            ((TopSocket)this.getCavityPlug()).setTopSocket(null);
            this.setCavityPlug(this);
        }
        if (this.getCavitySocket() != null){
            ((BottomPlug)this.getCavitySocket()).setBottomPlug(null);
            this.setCavitySocket(this);
        }*/
        //The cavity should not be disconnected, I would rather move the cavity blocks with the while if block if this gets moved.
    }

    /**
     * Disconnects block from the cavity and connects its upper and lower neighbour in the cavity to eachother.
     * @param block the block which is removed from the cavity.
     */
    public void disconnectCavity(ModelMoveBlock block){
        ModelBlock plug = block.getBottomPlug();
        ModelBlock socket = block.getTopSocket();
        block.setBottomPlug(null);
        block.setTopSocket(null);
        ((BottomPlug)socket).setBottomPlug(plug);
        ((TopSocket)plug).setTopSocket(socket);
    }

    /**
     * Updates the block connections within the cavity. Disconnecting blocks within a cavity can cause two blocks within the cavity to be disconnected which is
     * not wanted, because the cavity is a linked list.
     * This should be unnecessary but you should always prepare more than is needed.
     */
    public void updateCavity(){
        ModelBlock plug = this.getCavityPlug();
        ModelBlock socket = this.getCavitySocket();

        while (plug != null && plug != this){
            ((BottomPlug)plug).getBottomPlug();
        }
        if (plug == this) return;
        else plug = ((TopSocket)plug).getTopSocket();
        while (socket != null){
            ((TopSocket)socket).getTopSocket();
        }
        socket = ((BottomPlug)socket).getBottomPlug();
        ((BottomPlug)plug).setBottomPlug(socket);
        ((TopSocket)socket).setTopSocket(plug);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void connect(ModelBlock block) {
        boolean connected;
        if ((block.hasBottomPlug() && this.getCavitySocketPos().getDistance(((BottomPlug)block).getBottomPlugPos()) < ModelBlock.PLUGSIZE * 1.5)
            || (block.hasTopSocket() && this.getCavityPlugPos().getDistance(((TopSocket)block).getTopSocketPos()) < ModelBlock.PLUGSIZE * 1.5)){
            connected = connectCavity(block);
            if (connected) return;
        }
        if ((block.hasBottomPlug() && (this.getTopSocketPos().getDistance(((BottomPlug)block).getBottomPlugPos()) < ModelBlock.PLUGSIZE * 1.5))){
            this.setTopSocket(block);
            ((BottomPlug)block).setBottomPlug(this); 
            this.setPos(block.getPos().add(new WindowLocation(0, this.getHeight())));
        }
        else if ((block.hasTopSocket() && (this.getBottomPlugPos().getDistance(((TopSocket)block).getTopSocketPos()) < ModelBlock.PLUGSIZE * 1.5))){
            this.setBottomPlug(block);
            ((TopSocket)block).setTopSocket(this);  
            this.setPos(block.getPos().add(new WindowLocation(0,-block.getHeight())));
        }
        else if ((block.hasLeftPlug() && (this.getRightSocketPos().getDistance(((LeftPlug)block).getLeftPlugPos()) < ModelBlock.PLUGSIZE * 1.5))){
            this.setRightSocket(block);
            ((LeftPlug)block).setLeftPlug(this); 
            this.setPos(block.getPos().add(new WindowLocation(-this.getWidth(),0)));
        }
    }

    /**
     *
     * @param block the block that possible needs to be connected in the cavity
     * @return true if and only if block is connected within the cavity, false otherwise
     */
    public boolean connectCavity(ModelBlock block){
        if (block.hasBottomPlug() && this.getCavitySocketPos().getDistance(((BottomPlug)block).getBottomPlugPos()) < ModelBlock.PLUGSIZE * 1.5){
            ModelBlock cavityPrevious = this.getCavitySocket(); //The previous block that was connected to the cavity
            this.setCavitySocket(block);
            ((BottomPlug)block).setBottomPlug(this);
            if (block.hasTopSocket()){
                ((TopSocket)block).setTopSocket(cavityPrevious); //The previous block in the cavitysocket needs to connect with the modelBlock
                if (this.getCavityPlug() == this){
                    this.setCavityPlug(block);
                }
            }
            return true;
        }
        if (block.hasTopSocket() && this.getCavityPlugPos().getDistance(((TopSocket)block).getTopSocketPos()) < ModelBlock.PLUGSIZE * 1.5){
            ModelBlock cavityNext = this.getCavityPlug();
            this.setCavityPlug(this);
            ((TopSocket)block).setTopSocket(this);
            if (block.hasBottomPlug()){
                ((BottomPlug)block).setBottomPlug(cavityNext);
                if (this.getCavitySocket() == this){
                    this.setCavityPlug(block);
                }
            }
            return true;
        }
        return false;
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
     */
    public int getCavityWidth() {
        if(!(getCavityBlocks().size() == 0)){
            return getWidestBlockInCavity().getWidth() + STD_WIDTH;
        }
        else{
            return 0;
        }
    }

    /**
     * 
     * @return the widest block in the cavity of a if or while block
     */
    public ModelBlock getWidestBlockInCavity(){
        ModelBlock widest = getCavityBlocks().get(0);
        for(int i = 0; i < this.getCavityBlocks().size(); i++){
            ModelBlock current = this.getCavityBlocks().get(i);
            if(current.getWidth() > widest.getWidth()) widest = current;
        }
        return widest;
    }

    /**
     * Getter for a list of blocks this block has in its cavity.
     * @return the list of blocks.
     */
    public ArrayList<ModelBlock> getCavityBlocks() {
        ArrayList<ModelBlock> cav = new ArrayList<ModelBlock>();

        ModelBlock blk = this.getCavityPlug();
        System.out.println("type1");
        System.out.println(blk.getBlockType().getType());
        
        while(!blk.equals(this)){
            cav.add(blk);
            if(blk.hasBottomPlug()){
                blk = ((BottomPlug)blk).getBottomPlug();
            }
            System.out.println("type2");
            System.out.println(blk.getBlockType().getType());
            //the issue is that the block does not get the wileifblock as a bottomplug when connecting
            //so its bottom plug is null resulting in a nullpointer exception
            //else blk = this;//make sure that it doesn't form an infinite loop.
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
     */
    public WindowLocation getCavitySocketPos() {
        return this.getPos().add(ModelBlock.STD_WIDTH/2, this.getHeight() - ModelBlock.STD_HEIGHT/3);
    }

    /**
     * 
     * @return the position of the cavity plug
     */
    public WindowLocation getCavityPlugPos() {
        return this.getPos().add(ModelBlock.STD_WIDTH/2, 2*ModelBlock.STD_HEIGHT/3);
    }

    /**
     * 
     * @return the position of the top socket
     */
    public WindowLocation getTopSocketPos() {
        return super.getPos().add(this.getWidth() / 2, + ModelBlock.PLUGSIZE / 2);
    }

    /**
     * 
     * @return the position of the bottom plug
     */
    public WindowLocation getBottomPlugPos() {
        return super.getPos().add(this.getWidth() / 2,+this.getHeight() + ModelBlock.PLUGSIZE / 2);
    }

    /**
     * 
     * @return the position of the right socket
     */
    public WindowLocation getRightSocketPos() {
        return super.getPos().add(this.getWidth() - ModelBlock.PLUGSIZE / 2, this.getHeight() / 2);
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
}