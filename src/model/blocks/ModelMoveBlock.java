package model.blocks;

import utilities.Blocktype;
import utilities.Location;

/**
 * Class representing the move forward, turn left and turn right blocks with one socket at the top and one plug at the bottom.
 */
public class ModelMoveBlock extends ModelBlock implements TopSocket,BottomPlug{

    private ModelBlock topSocket;
    private ModelBlock bottomPlug;
    
    public ModelMoveBlock(Location pos, Blocktype type) {
        super(pos,type);
        this.setTopSocket(null);
        this.setBottomPlug(null);
    }


    /**
     * Method describing how a block will disconnect from another block in the program area.
     */
    @Override
    public void disconnect() {
        if (this.getTopSocket() != null){
            ((BottomPlug)this.getTopSocket()).setBottomPlug(null);
            this.setTopSocket(null);
        }
        if (this.getBottomPlug() != null){
            ((TopSocket)this.getBottomPlug()).setTopSocket(null);
            this.setBottomPlug(null);
        }
    }

    /**
     * Method describing how a block will connect to another block.
     */
    @Override
    public void connect(ModelBlock block) {
        if ((block.hasBottomPlug() && (this.getTopSocketPos().getDistance(((BottomPlug)block).getBottomPlugPos()) < 50))) {
            this.setTopSocket(block);
            ((BottomPlug)block).setBottomPlug(this);  
            this.setPos(block.getPos().add(new Location(0,block.getHeight())));  
        }
        if ((block.hasTopSocket() && (this.getBottomPlugPos().getDistance(((TopSocket)block).getTopSocketPos()) < 50))){
            this.setBottomPlug(block);
            ((TopSocket)block).setTopSocket(this); 
            this.setPos(block.getPos().add(new Location(0, -block.getHeight())));  
        }
    }

    @Override
    public void setBottomPlug(ModelBlock blk) {
        this.bottomPlug = blk;

    }

    @Override
    public ModelBlock getBottomPlug() {
        return this.bottomPlug;
    }

    @Override
    public Location getBottomPlugPos() {
        return super.getPos().add(this.getWidth() / 2, + this.getHeight() + ModelBlock.PLUGSIZE/2);
    }

    @Override
    public void setTopSocket(ModelBlock blk) {
        this.setTopSocket(blk);
    }

    @Override
    public ModelBlock getTopSocket() {
        return this.topSocket;
    }

    @Override
    public Location getTopSocketPos() {
        return super.getPos().add(this.getWidth() / 2, + ModelBlock.PLUGSIZE/2);
    }



    
}