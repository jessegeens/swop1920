package model.blocks;

import java.util.ArrayList;

class ModelWhileIfBlock extends ModelBlock{
    private ModelBlock topSocket;
    private ModelBlock bottomPlug;
    private int cavityHeight;
    private ArrayList<ModelBlock> blocks;

    

    @Override
    public void disconnect() {
        if (getTopSocket() != null){
            getTopSocket().se
        }

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