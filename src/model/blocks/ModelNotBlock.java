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
    

    public ModelBlock getRightSocket() {
        return this.rightSocket;
    }

    public void setRightSocket(ModelBlock rightSocket) {
        this.rightSocket = rightSocket;
    }

    public ModelBlock getLeftPlug() {
        return this.leftPlug;
    }

    public void setLeftPlug(ModelBlock leftPlug) {
        this.leftPlug = leftPlug;
    }

    


}