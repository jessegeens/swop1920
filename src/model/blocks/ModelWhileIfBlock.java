package model.blocks;

import java.util.ArrayList;

import utilities.Location;

public class ModelWhileIfBlock extends ModelBlock{
    private ModelBlock topSocket;
    private ModelBlock bottomPlug;
    private ModelBlock rightSocket;
    private ModelBlock cavitySocket;
    private ModelBlock cavityPlug;
    private int cavityHeight;
    private boolean isWhile;
    private ArrayList<ModelBlock> blocks;

    

    public ModelWhileIfBlock(Location pos, boolean whileblk){
        super.setPos(pos);
        this.setTopSocket(null);
        this.setBottomPlug(null);
        this.setRightSocket(null);
        this.setCavityPlug(this);
        this.setCavitySocket(this);
        this.setCavityHeight(0);
        this.setWhile(whileblk);
        this.setBlocks(new ArrayList<ModelBlock>());
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
        if ((block.getLeftPlug() == null) && (this.getRightSocketPos().getDistance(block.getLeftPlugPos()) < 50)){
            this.setRightSocket(block);
            block.setLeftPlug(this);    
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

    public boolean isWhile() {
        return this.isWhile;
    }

    public void setWhile(boolean whileBlock) {
        this.isWhile = whileBlock;
    };


}