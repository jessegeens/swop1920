package model.blocks;

import utilities.Location;

public class ModelMoveBlock extends ModelBlock {
    private ModelBlock topSocket;
    private ModelBlock bottomPlug;
    private int type;    //0 voor forward, 1 naar left, 2 naar right

    
    public ModelMoveBlock(Location pos, int type) {
        super.setPos(pos);
        this.setType(type);
        this.setTopSocket(null);
        this.setBottomPlug(null);
    }



    @Override
    public void disconnect() {
        if (this.getTopSocket() != null){
            this.getTopSocket().setBottomPlug(null);
            this.setTopSocket(null);
        }
        if (this.getBottomPlug() != null){
            this.getBottomPlug().setTopSocket(null);
            this.setBottomPlug(null);
        }
    }

    @Override
    public void connect(ModelBlock block) {
        if ((block.getBottomPlug() == null) && (this.getTopSocketPos().getDistance(block.getBottomPlugPos()) < 50)){
            this.setTopSocket(block);
            block.setBottomPlug(this);    
        }
        if ((block.getTopSocket() == null) && (this.getBottomPlugPos().getDistance(block.getTopSocketPos()) < 50)){
            this.setBottomPlug(block);
            block.setTopSocket(this);    
        }
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

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    
    

}