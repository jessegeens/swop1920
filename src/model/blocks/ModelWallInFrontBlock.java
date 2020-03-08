package model.blocks;

class ModelWallInFrontBlock extends ModelBlock{
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
    public ModelBlock getLeftPlug() {
        return this.leftPlug;
    }

    @Override
    public void setLeftPlug(ModelBlock leftPlug) {
        this.leftPlug = leftPlug;
    }

    


}