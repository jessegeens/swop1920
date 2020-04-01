package model.blocks;

import utilities.*;

import java.util.ArrayList;

/**
 * Class representing the 'wall in front' block with one plug to their left.
 */
public class ModelWallInFrontBlock extends ModelBlock{
    private ModelBlock leftPlug;

    // Constructor
    public ModelWallInFrontBlock(WindowLocation pos, BlockType type){
        super(pos,type);
        this.setLeftPlug(null);
        ArrayList<ConnectionPoint> connectionPoints = new ArrayList<>();
        connectionPoints.add(ConnectionPoint.LEFTPLUG);
        super.setConnectionPoints(connectionPoints);
    }

    /**
     * {@inheritDoc}
     * @author Oberon Swings
     */
    @Override
    public void disconnect() {
        if (this.getLeftPlug() != null){
            this.getLeftPlug().setRightSocket(null);
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
        System.out.println(this.getLeftPlugPos().getDistance(block.getRightSocketPos()));
        if (block.hasRightSocket() && (this.getLeftPlugPos().getDistance(block.getRightSocketPos()) < ModelBlock.PLUGSIZE * 1.5)
        && block.getRightSocket() == null){
            System.out.println("WIF CONNECTS");
            if(block.hasRightSocket()){
                this.setLeftPlug(block);
                block.setRightSocket(this);
                this.setLeftPlugPos(block.getRightSocketPos());
            }  
        }  
    }

    /**
     * {@inheritDoc}
     */
    public ModelBlock getLeftPlug() {
        return this.leftPlug;
    }

    /**
     * {@inheritDoc}
     */
    public void setLeftPlug(ModelBlock leftPlug) {
        this.leftPlug = leftPlug;
    }

    /**
     * {@inheritDoc}
     */
    public WindowLocation getLeftPlugPos() {
        return super.getPos().add(- ModelBlock.PLUGSIZE / 2, + this.getHeight() / 2);
    }

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