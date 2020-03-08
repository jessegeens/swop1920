package model.blocks;

class ModelNotBlock extends ModelBlock{
    private ModelBlock rightSocket;
    private ModelBlock leftPlug;



    @Override
    public void disconnect() {
        // TODO Auto-generated method stub

    }

    @Override
    public void connect(ModelBlock block) {
        // TODO Auto-generated method stub

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