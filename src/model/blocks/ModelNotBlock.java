package model.blocks;

class ModelNotBlock extends ModelBlock{
    private ModelBlock rightSocket;
    private ModelBlock leftPlug;



    

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