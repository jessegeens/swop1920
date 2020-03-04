package model.blocks;

class ModelMoveBlock extends ModelBlock{
    private ModelBlock topSocket;
    private ModelBlock bottomPlug;




    public ModelBlock getTopSocket() {
        return this.topSocket;
    }

    public void setTopSocket(ModelBlock topSocket) {
        this.topSocket = topSocket;
    }

    public ModelBlock getBottomPlug() {
        return this.bottomPlug;
    }

    public void setBottomPlug(ModelBlock bottomPlug) {
        this.bottomPlug = bottomPlug;
    }

}