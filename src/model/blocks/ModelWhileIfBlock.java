package model.blocks;

import java.util.ArrayList;

import utilities.Blocktype;
import utilities.Location;

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

    private Location topSocketPos = super.getPos().add(this.getWidth() / 2, + this.getPlugSize()/2);
    private Location bottomPlugPos = super.getPos().add(this.getWidth() / 2, + this.getHeight() + this.getPlugSize()/2);
    private Location rightSocketPos = super.getPos().add(this.getWidth() + this.getPlugSize()/2, + this.getHeight() / 2);
    private Location cavitySocketPos;
    private Location cavityPlugPos;

    private int cavityHeight;
    private int cavityWidth;


    public ModelWhileIfBlock(Location pos, Blocktype type){
        super(pos,type);

        this.setTopSocket(null);
        this.setBottomPlug(null);
        this.setRightSocket(null);
        this.setCavityPlug(this);
        this.setCavitySocket(this);

        this.setCavityPlugPos(getPos().add(new Location(getWidth()/2,getHeight()/2)));
        this.setCavitySocketPos(getCavityPlugPos());

        this.updateCavityHeight();
        this.updateCavityWidth();
    }

    /**
     * {@inheritDoc}
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
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void connect(ModelBlock block) {
        if ((block.getBottomPlug() == null) && (this.getTopSocketPos().getDistance(block.getBottomPlugPos()) < 50)){
            this.setTopSocket(block);
            block.setBottomPlug(this); 
            this.setPos(block.getPos().add(new Location(0,-block.getHeight())));  
        }
        if ((block.getTopSocket() == null) && (this.getBottomPlugPos().getDistance(block.getTopSocketPos()) < 50)){
            this.setBottomPlug(block);
            block.setTopSocket(this);  
            this.setPos(block.getPos().add(new Location(0, this.getHeight())));  
        }
        if ((block.getRightSocket() == null) && (this.getLeftPlugPos().getDistance(block.getRightSocketPos()) < 50)){
            this.setLeftPlug(block);
            block.setRightSocket(this); 
            this.setPos(block.getPos().add(new Location(block.getWidth(),0)));   
        }
        if ((block.getBottomPlug() == null) && (this.getCavitySocketPos().getDistance(block.getBottomPlugPos()) < 50)){
            this.setCavitySocket(block);
            block.setBottomPlug(this);
        }
        if ((block.getTopSocket() == null) && (this.getCavityPlugPos().getDistance(block.getTopSocketPos()) < 50)){
            this.setCavityPlug(block);
            block.setTopSocket(this);
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
     * @return
     */
    public int getCavityHeight() {
        return this.cavityHeight;
    }

    /**
     * Updater for the height of the cavity of the while and if block.
     */
    public void updateCavityHeight() {
        this.cavityHeight = getCavityBlocks().size() * 120;
        this.setHeight(120+getCavityHeight());
    }

    public int getCavityWidth() {
        return this.cavityWidth;
    }

    public void updateCavityWidth() {
        this.cavityWidth = getCavityBlocks().get(0).getWidth() + ModelBlock.WIDTH;
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
            blk = blk.getBottomPlug();
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
        return this.cavitySocketPos;
    }

    public void setCavitySocketPos(Location cavitySocketPos) {
        this.cavitySocketPos = cavitySocketPos;
    }

    public Location getCavityPlugPos() {
        return this.cavityPlugPos;
    }

    public void setCavityPlugPos(Location cavityPlugPos) {
        this.cavityPlugPos = cavityPlugPos;
    }



    
}