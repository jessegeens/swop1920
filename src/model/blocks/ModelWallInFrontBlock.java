package model.blocks;

import utilities.*;
import model.blocks.plugs.*;

/**
 * Class representing the 'wall in front' block with one plug to their left.
 */
public class ModelWallInFrontBlock extends ModelBlock implements LeftPlug{
    private ModelBlock leftPlug;

    // Constructor
    public ModelWallInFrontBlock(WindowLocation pos, Blocktype type){
        super(pos,type);
        this.setLeftPlug(null);
    }

    /**
     * {@inheritDoc}
     * @author Oberon Swings
     */
    @Override
    public void disconnect() {
        if (this.hasLeftPlug()){
            ((RightSocket)this.getLeftPlug()).setRightSocket(null);
            this.setLeftPlug(null);
        }
    }

    /**
     * {@inheritDoc}
     * @author Oberon Swings
     */
    @Override
    public void connect(ModelBlock block) {
        System.out.println("DISTANCE");
        System.out.println(this.getLeftPlugPos().getDistance(((RightSocket)block).getRightSocketPos()));
        if (block.hasRightSocket() && (this.getLeftPlugPos().getDistance(((RightSocket)block).getRightSocketPos()) < ModelBlock.PLUGSIZE * 1.5)
        && ((RightSocket) block).getRightSocket() == null){
            System.out.println("WIF CONNECTS");
            if(block.hasRightSocket()){
                this.setLeftPlug(block);
                ((RightSocket)block).setRightSocket(this);
                this.setLeftPlugPos(((RightSocket) block).getRightSocketPos());
            }  
        }  
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
    public boolean hasLeftPlug(){
        return true;
    }

    


}