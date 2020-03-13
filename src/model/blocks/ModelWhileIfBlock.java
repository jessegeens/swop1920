package model.blocks;

import java.util.ArrayList;

import utilities.Blocktype;
import utilities.Location;

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


    public ModelWhileIfBlock(Location pos, Blocktype type){
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
        if ((block.hasBottomPlug() && (this.getTopSocketPos().getDistance(((BottomPlug)block).getBottomPlugPos()) < 50))){
            this.setTopSocket(block);
            ((BottomPlug)block).setBottomPlug(this); 
            this.setPos(block.getPos().add(new Location(0,-block.getHeight())));  
        }
        if ((block.hasTopSocket() && (this.getBottomPlugPos().getDistance(((TopSocket)block).getTopSocketPos()) < 50))){
            this.setBottomPlug(block);
            ((TopSocket)block).setTopSocket(this);  
            this.setPos(block.getPos().add(new Location(0, this.getHeight())));  
        }
        if ((block.hasLeftPlug() && (this.getRightSocketPos().getDistance(((LeftPlug)block).getLeftPlugPos()) < 50))){
            this.setRightSocket(block);
            ((LeftPlug)block).setLeftPlug(this); 
            this.setPos(block.getPos().add(new Location(-this.getWidth(),0)));   
        }
        if ((block.hasBottomPlug() && (this.getCavitySocketPos().getDistance(((BottomPlug)block).getBottomPlugPos()) < 50))){
            this.setCavitySocket(block);
            ((BottomPlug)block).setBottomPlug(this);
        }
        if ((block.hasTopSocket() && (this.getCavityPlugPos().getDistance(((TopSocket)block).getTopSocketPos()) < 50))){
            this.setCavityPlug(block);
            ((TopSocket)block).setTopSocket(this);
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
            return getCavityBlocks().size() * HEIGHTSTD + HEIGHTSTD;
        }
        else return 0;
    }

    /**
     * 
     * @return the width of the block
     */
    public int getCavityWidth() {
        if(!getCavityBlocks().isEmpty())
            return getWidestBlockInCavity().getWidth() + WIDTHSTD;
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
        while(blk != this){
            cav.add(blk);
            if(blk.hasBottomPlug()){
                blk = ((BottomPlug)blk).getBottomPlug();
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

    public Location getCavitySocketPos() {
        return this.getPos().add(new Location(this.getWidth()/2,ModelBlock.HEIGHTSTD/2));
    }

    public Location getCavityPlugPos() {
        return this.getPos().add(new Location(this.getWidth()/2,this.getHeight() - ModelBlock.HEIGHTSTD/2));
    }

    public Location getTopSocketPos() {
        return super.getPos().add(this.getWidth() / 2, + ModelBlock.PLUGSIZE / 2);
    }

    public Location getBottomPlugPos() {
        return super.getPos().add(this.getWidth() / 2,+this.getHeight() + ModelBlock.PLUGSIZE / 2);
    }

    public Location getRightSocketPos() {
        return super.getPos().add(this.getWidth() - ModelBlock.PLUGSIZE / 2, this.getHeight() / 2);
    }

    @Override
    public boolean hasTopSocket(){
        return true;
    }

    @Override
    public boolean hasBottomPlug(){
        return true;
    }

    @Override
    public boolean hasRightSocket(){
        return true;
    }

    
}