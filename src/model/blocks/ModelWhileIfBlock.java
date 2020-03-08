package model.blocks;

import java.util.ArrayList;

class ModelWhileIfBlock extends ModelBlock{
    private ModelBlock topSocket;
    private ModelBlock bottomPlug;
    private ModelBlock rightSocket;
    private ModelBlock cavitySocket = this;
    private ModelBlock cavityPlug = this;
    private int cavityHeight;
    private ArrayList<ModelBlock> blocks;

    

    @Override
    public void disconnect() {
        if (getTopSocket() != null){
            getTopSocket().setBottomPlug(null);
            this.setTopSocket(null);
        }
        if (getBottomPlug() != null){
            getBottomPlug().setTopSocket(null);
            this.setBottomPlug(null);
        }
    }

    @Override
    public void connect(ModelBlock block) {
        

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

    public int getCavityHeight() {
        return this.cavityHeight;
    }

    public void setCavityHeight(int cavityHeight) {
        this.cavityHeight = cavityHeight;
    }

    public ArrayList<ModelBlock> getBlocks() {
        return this.blocks;
    }

    public void setBlocks(ArrayList<ModelBlock> blocks) {
        this.blocks = blocks;
    }

    public ModelBlock getRightSocket() {
        return this.rightSocket;
    }

    public void setRightSocket(ModelBlock rightSocket) {
        this.rightSocket = rightSocket;
    }

    public ModelBlock getCavitySocket() {
        return this.cavitySocket;
    }

    public void setCavitySocket(ModelBlock cavitySocket) {
        this.cavitySocket = cavitySocket;
    }

    public ModelBlock getCavityPlug() {
        return this.cavityPlug;
    }

    public void setCavityPlug(ModelBlock cavityPlug) {
        this.cavityPlug = cavityPlug;
    }


}