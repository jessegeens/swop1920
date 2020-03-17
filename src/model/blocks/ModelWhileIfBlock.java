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
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void connect(ModelBlock block) {
        if ((block.hasBottomPlug() && (this.getCavitySocketPos().getDistance(((BottomPlug)block).getBottomPlugPos()) < ModelBlock.PLUGSIZE * 1.5))){
            this.setCavitySocket(block);
            ((BottomPlug)block).setBottomPlug(this);
            if(this.getCavityPlug() == this){ 
                this.setCavityPlug(block);
                if(block.hasTopSocket()) ((TopSocket)block).setTopSocket(this);
            }    
        }
        else if ((block.hasTopSocket() && (this.getCavityPlugPos().getDistance(((TopSocket)block).getTopSocketPos()) < ModelBlock.PLUGSIZE * 1.5))){
            this.setCavityPlug(block);
            ((TopSocket)block).setTopSocket(this);
            if(this.getCavitySocket() == this){ 
                this.setCavitySocket(block);
                if(block.hasBottomPlug()) ((BottomPlug)block).setBottomPlug(this);
            }
        }
        else if ((block.hasBottomPlug() && (this.getTopSocketPos().getDistance(((BottomPlug)block).getBottomPlugPos()) < ModelBlock.PLUGSIZE * 1.5))){
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
        if(!getCavityBlocks().isEmpty()){
            return getCavityBlocks().size() * STD_HEIGHT + STD_HEIGHT;
        }
        else return 0;
    }

    /**
     * 
     * @return the width of the block
     */
    public int getCavityWidth() {
        if(!getCavityBlocks().isEmpty())
            return getWidestBlockInCavity().getWidth() + STD_WIDTH;
        else
            return 0;
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
        while(!blk.equals(this)){
            cav.add(blk);
            if(blk.hasBottomPlug()){
                blk = ((BottomPlug)blk).getBottomPlug();
            }
            else blk = this;//make sure that it doesn't form an infinite loop.
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
        return this.getPos().add(new WindowLocation(ModelBlock.STD_WIDTH/2 + getCavityWidth(), this.getHeight() - ModelBlock.STD_HEIGHT/3));
    }

    /**
     * 
     * @return the position of the cavity plug
     */
    public WindowLocation getCavityPlugPos() {
        return this.getPos().add(new WindowLocation(ModelBlock.STD_WIDTH/2 + getCavityWidth(), 2*ModelBlock.STD_HEIGHT/3));
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