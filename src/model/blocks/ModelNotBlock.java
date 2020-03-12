package model.blocks;

import utilities.Blocktype;
import utilities.Location;

/**
 * Class representing the not block with one plug to their left and one socket to their right.
 */
public class ModelNotBlock extends ModelBlock {
    private ModelBlock rightSocket;
    private ModelBlock leftPlug;


    public ModelNotBlock(Location pos, Blocktype type){
        super(pos,type);
        this.setRightSocket(null);
        this.setLeftPlug(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void disconnect() {
        if (this.getRightSocket() != null){
            this.getRightSocket().setLeftPlug(null);
            this.setRightSocket(null);
        }
        if (this.getLeftPlug() != null){
            this.getLeftPlug().setRightSocket(null);
            this.setLeftPlug(null);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void connect(ModelBlock block) {
        if ((block.getLeftPlug() == null) && (this.getRightSocketPos().getDistance(block.getLeftPlugPos()) < 50)){
            this.setRightSocket(block);
            block.setLeftPlug(this);
            this.setPos(block.getPos().add(new Location(-this.getWidth(),0)));   
        }
        if ((block.getRightSocket() == null) && (this.getLeftPlugPos().getDistance(block.getRightSocketPos()) < 50)){
            this.setLeftPlug(block);
            block.setRightSocket(this); 
            this.setPos(block.getPos().add(new Location(block.getWidth(),0)));   
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public ModelBlock getRightSocket() {
        return this.rightSocket;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRightSocket(ModelBlock rightSocket) {
        this.rightSocket = rightSocket;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ModelBlock getLeftPlug() {
        return this.leftPlug;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLeftPlug(ModelBlock leftPlug) {
        this.leftPlug = leftPlug;
    }
}