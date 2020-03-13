package model.blocks;

import utilities.Blocktype;
import utilities.Location;

/**
 * Class representing the not block with one plug to their left and one socket to their right.
 */
public class ModelNotBlock extends ModelBlock implements RightSocket,LeftPlug{
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
            ((LeftPlug)this.getRightSocket()).setLeftPlug(null);
            this.setRightSocket(null);
        }
        if (this.getLeftPlug() != null){
            ((RightSocket)this.getLeftPlug()).setRightSocket(null);
            this.setLeftPlug(null);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void connect(ModelBlock block) {
        if ((block.hasLeftPlug() && (this.getRightSocketPos().getDistance(((LeftPlug)block).getLeftPlugPos()) < 50))){
            System.out.println("NOT CONNECTS LEFT");
            this.setRightSocket(block);
            ((LeftPlug)block).setLeftPlug(this);
            this.setPos(block.getPos().add(new Location(-this.getWidth(),0)));   
        }
        if ((block.hasRightSocket()) && (this.getLeftPlugPos().getDistance(((RightSocket)block).getRightSocketPos()) < 50)){
            System.out.println("NOT CONNECTS RIGHT");
            this.setLeftPlug(block);
            ((RightSocket)block).setRightSocket(this); 
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

    @Override
    public Location getLeftPlugPos() {
        return super.getPos().add(- ModelBlock.PLUGSIZE / 2, + this.getHeight() / 2);
    }

    @Override
    public Location getRightSocketPos() {
        return super.getPos().add(this.getWidth() + ModelBlock.PLUGSIZE/2, + this.getHeight() / 2);
    }
    
    @Override
    public boolean hasRightSocket(){
        return true;
    }

    @Override
    public boolean hasLeftPlug(){
        return true;
    }
}