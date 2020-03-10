package model.blocks;

import java.util.ArrayList;

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
    private int cavityHeight;
    private boolean isWhile;
    private ArrayList<ModelBlock> blocks;

    public ModelWhileIfBlock(Location pos, boolean whileblk){
        super.setPos(pos);
        this.setTopSocket(null);
        this.setBottomPlug(null);
        this.setRightSocket(null);
        this.setCavityPlug(this);
        this.setCavitySocket(this);
        this.setCavityHeight(0);
        this.setWhile(whileblk);
        this.setBlocks(new ArrayList<ModelBlock>());
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
        }
        if ((block.getTopSocket() == null) && (this.getBottomPlugPos().getDistance(block.getTopSocketPos()) < 50)){
            this.setBottomPlug(block);
            block.setTopSocket(this);    
        }
        if ((block.getLeftPlug() == null) && (this.getRightSocketPos().getDistance(block.getLeftPlugPos()) < 50)){
            this.setRightSocket(block);
            block.setLeftPlug(this);    
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
     * Setter for the height of the cavity of the while and if block.
     * @param cavityHeight
     */
    public void setCavityHeight(int cavityHeight) {
        this.cavityHeight = cavityHeight;
    }

    /**
     * Getter for a list of blocks this block is connected to //TODO: via rechts?
     * @return the list of blocks.
     */
    public ArrayList<ModelBlock> getBlocks() {
        return this.blocks;
    }

    /**
     * Setter for a list of blocks this block is connected to.
     * @param blocks the list of blocks this block is connected to.
     */
    public void setBlocks(ArrayList<ModelBlock> blocks) {
        this.blocks = blocks;
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
     * @return true if this is a while block.
     */
    public boolean isWhile() {
        return this.isWhile;
    }

    /**
     * 
     * @param whileBlock sets this block to a while block.
     */
    public void setWhile(boolean whileBlock) {
        this.isWhile = whileBlock;
    } //TODO: hier stond een louche puntkomma, ik heb die weggedaan
}