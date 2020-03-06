package model.blocks;

import java.util.ArrayList;

class ModelWhileIfBlock extends ModelBlock{
    private ModelBlock topSocket;
    private ModelBlock bottomPlug;
    private int cavityHeight;
    private ArrayList<ModelBlock> blocks;

    

    @Override
    public void disconnect() {
        // TODO Auto-generated method stub

    }

    @Override
    public void connect(ModelBlock block) {
        // TODO Auto-generated method stub

    }



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

    


}