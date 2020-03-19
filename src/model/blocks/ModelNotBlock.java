package model.blocks;

import utilities.*;
import model.blocks.plugs.*;
/**
 * Class representing the not block with one plug to their left and one socket to their right.
 */
public class ModelNotBlock extends ModelBlock implements RightSocket,LeftPlug{
    private ModelBlock rightSocket;
    private ModelBlock leftPlug;

    public ModelNotBlock(WindowLocation pos, Blocktype type){
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
        if ((block.hasLeftPlug() && (this.getRightSocketPos().getDistance(((LeftPlug)block).getLeftPlugPos()) < ModelBlock.PLUGSIZE * 1.5)
        && ((LeftPlug) block).getLeftPlug() == null)){
            System.out.println("NOT CONNECTS LEFT");
            this.setRightSocket(block);
            ((LeftPlug)block).setLeftPlug(this);
            this.setRightSocketPos(((LeftPlug) block).getLeftPlugPos());
        }
        if ((block.hasRightSocket()) && (this.getLeftPlugPos().getDistance(((RightSocket)block).getRightSocketPos()) < ModelBlock.PLUGSIZE * 1.5)
        && ((RightSocket) block).getRightSocket() == null){
            System.out.println("NOT CONNECTS RIGHT");
            this.setLeftPlug(block);
            ((RightSocket)block).setRightSocket(this);
            this.setLeftPlugPos(((RightSocket) block).getRightSocketPos());
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

    /**
     * {@inheritDoc}
     */
    @Override
    public WindowLocation getLeftPlugPos() {
        return super.getPos().add(- ModelBlock.PLUGSIZE / 2, + this.getHeight() / 2);
    }

    @Override
    public void setLeftPlugPos(WindowLocation pos) {
        super.setPos(pos.add(ModelBlock.PLUGSIZE/2, -this.getHeight()/2));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WindowLocation getRightSocketPos() {
        return super.getPos().add(this.getWidth() - ModelBlock.PLUGSIZE/2, + this.getHeight() / 2);
    }

    @Override
    public void setRightSocketPos(WindowLocation pos) {
        super.setPos(pos.add(-this.getWidth() + ModelBlock.PLUGSIZE/2, -this.getHeight()/2));
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
    public boolean hasLeftPlug(){
        return true;
    }
}