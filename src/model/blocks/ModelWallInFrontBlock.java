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