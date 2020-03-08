package model.blocks;

class ModelNotBlock extends ModelBlock{
    private ModelBlock rightSocket;
    private ModelBlock leftPlug;



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

    @Override
    public void connect(ModelBlock block) {
        if ((block.getLeftPlug() == null) && (this.getRightSocketPos().getDistance(block.getLeftPlugPos()) < 50)){
            this.setRightSocket(block);
            block.setLeftPlug(this);    
        }
        if ((block.getRightSocket() == null) && (this.getLeftPlugPos().getDistance(block.getRightSocketPos()) < 50)){
            this.setLeftPlug(block);
            block.setRightSocket(this);    
        }
    }
    

    @Override
    public ModelBlock getRightSocket() {
        return this.rightSocket;
    }

    @Override
    public void setRightSocket(ModelBlock rightSocket) {
        this.rightSocket = rightSocket;
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