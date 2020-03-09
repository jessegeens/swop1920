package model.blocks;

import utilities.Location;

public class ModelWallInFrontBlock extends ModelBlock {
    private ModelBlock leftPlug;


    public ModelWallInFrontBlock(Location pos){
        super.setPos(pos);
        this.setLeftPlug(null);
    }

    @Override
    public void disconnect() {
        if (this.getLeftPlug() != null){
            this.getLeftPlug().setRightSocket(null);
            this.setLeftPlug(null);
        }

    }

    @Override
    public void connect(ModelBlock block) {
        if ((block.getRightSocket() == null) && (this.getLeftPlugPos().getDistance(block.getRightSocketPos()) < 50)){
            this.setLeftPlug(block);
            block.setRightSocket(this);    
        }

    }

    @Override
    public ModelBlock getLeftPlug() {
        return this.leftPlug;
    }

    @Override
    public void setLeftPlug(ModelBlock leftPlug) {
        this.leftPlug = leftPlug;
    }

    


}