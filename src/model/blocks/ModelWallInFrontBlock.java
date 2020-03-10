package model.blocks;

import utilities.Blocktype;
import utilities.Location;

/**
 * Class representing the 'wall in front' block with one plug to their left.
 */
public class ModelWallInFrontBlock extends ModelBlock {
    private ModelBlock leftPlug;


    public ModelWallInFrontBlock(Location pos, Blocktype type){
        super(pos,type);
        this.setLeftPlug(null);
    }

    /**
     * {@inheritDoc}
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
     */
    @Override
    public void connect(ModelBlock block) {
        if ((block.getRightSocket() == null) && (this.getLeftPlugPos().getDistance(block.getRightSocketPos()) < 50)){
            this.setLeftPlug(block);
            block.setRightSocket(this);    
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

    


}