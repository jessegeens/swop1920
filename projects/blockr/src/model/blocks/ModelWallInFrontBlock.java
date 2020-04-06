package model.blocks;

import ui.UIBlock;
import utilities.*;

import java.util.ArrayList;

/**
 * Class representing the 'wall in front' block with one plug to their left.
 */
public class ModelWallInFrontBlock extends ModelBlock{
    private ModelBlock leftPlug;

    // Constructor
    public ModelWallInFrontBlock(Location pos, BlockType type){
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
    public Location getLeftPlugPos() {
        return super.getPos().add(- UIBlock.PLUGSIZE / 2, + this.getHeight() / 2);
    }

    public void setLeftPlugPos(Location pos) {
        super.setPos(pos.add(UIBlock.PLUGSIZE/2, -this.getHeight()/2));
    }

    


}