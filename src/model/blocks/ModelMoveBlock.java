package model.blocks;

class ModelMoveBlock extends ModelBlock{
    private ModelBlock topSocket;
    private ModelBlock bottomPlug;



    @Override
    public void disconnect() {
        // TODO Auto-generated method stub

    }

    @Override
    public void connect(ModelBlock block) {
        // TODO Auto-generated method stub

    }

    @Override
    public ModelBlock getTopSocket() {
        return this.topSocket;
    }

    @Override
    public void setTopSocket(ModelBlock topSocket) {
        this.topSocket = topSocket;
    }

    @Override
    public ModelBlock getBottomPlug() {
        return this.bottomPlug;
    }

    @Override
    public void setBottomPlug(ModelBlock bottomPlug) {
        this.bottomPlug = bottomPlug;
    }

    

}