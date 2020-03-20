package model.blocks;

import model.blocks.plugs.*;
import utilities.Blocktype;
import utilities.WindowLocation;

/**
 * Class representing the move forward, turn left and turn right blocks with one socket at the top and one plug at the bottom.
 */
public class ModelMoveBlock extends ModelBlock implements TopSocket,BottomPlug{

    private ModelBlock topSocket;
    private ModelBlock bottomPlug;
    
    public ModelMoveBlock(WindowLocation pos, Blocktype type) {
        super(pos,type);
        this.setTopSocket(null);
        this.setBottomPlug(null);
    }


    /**
     * {@inheritDoc}
     * @author Oberon Swings
     */
    @Override
    public void disconnect() {
        if (this.getTopSocket() != null){
            if (this.isInCavity()){

                //if this block is in the cavity of the while if block it should be removed from that.
                ((ModelWhileIfBlock)this.getTopSocket()).disconnectCavity(this);
            }
            else {
                ((BottomPlug)this.getTopSocket()).setBottomPlug(null);
            }
            this.setTopSocket(null);
        }
        if (this.getBottomPlug() != null){
            if (this.isInCavity()){
                //if this block is in the cavity of the while if block it should be removed from that
                ((ModelWhileIfBlock)this.getBottomPlug()).disconnectCavity(this);
            }
            else{
                ((TopSocket)this.getBottomPlug()).setTopSocket(null);
            }
            this.setBottomPlug(null);
        }
    }

    /**
     * {@inheritDoc}
     * @author Oberon Swings
     */
    @Override
    public void connect(ModelBlock block) {
        if (block instanceof ModelWhileIfBlock){
            ((ModelWhileIfBlock) block).connectCavity(this);
        }
        else if (block.isInCavity()){
            ((ModelWhileIfBlock)block.getSurroundingWhileIfBlock()).connectIntoCavity(this, block);
        }
        else if (block.hasBottomPlug() && (this.getTopSocketPos().getDistance(((BottomPlug)block).getBottomPlugPos()) < ModelBlock.PLUGSIZE * 1.5)
        && ((BottomPlug) block).getBottomPlug() == null) {
            this.setTopSocket(block);
            ((BottomPlug)block).setBottomPlug(this);
            this.setTopSocketPos(((BottomPlug) block).getBottomPlugPos());
        }
        else if (block.hasTopSocket() && (this.getBottomPlugPos().getDistance(((TopSocket)block).getTopSocketPos()) < ModelBlock.PLUGSIZE * 1.5)
        && ((TopSocket) block).getTopSocket() == null){
            this.setBottomPlug(block);
            ((TopSocket)block).setTopSocket(this);
            this.setBottomPlugPos(((TopSocket) block).getTopSocketPos());
        }
    }


    /**
     *
     * @param block
     */
    /*I think it was better to handle this in the while/if block it self, that's why I made IsInCavity and getSurroundingWhileIf.
    Don't think this is still necessary -Oberon*/
    public void connectModelWhileIfBlock(ModelWhileIfBlock block) {
        if (this.getTopSocketPos().getDistance(block.getCavityPlugPos()) < ModelBlock.PLUGSIZE * 1.5 ){
            this.setBottomPlug(block.getCavitySocket());
            this.setTopSocket(block);
            block.setCavityPlug(this);
            this.setPos(block.getPos().add(ModelBlock.STD_WIDTH/3,ModelBlock.STD_HEIGHT/2));
        }
        if (this.getBottomPlugPos().getDistance(block.getCavitySocketPos()) < ModelBlock.PLUGSIZE * 1.5){
            this.setTopSocket(block.getCavitySocket());
            this.setBottomPlug(block);
            block.setCavitySocket(this);
            this.setPos(block.getPos().add(ModelBlock.STD_WIDTH/3,block.getHeight() + ModelBlock.STD_HEIGHT/2));
        }
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void setBottomPlug(ModelBlock blk) {
        this.bottomPlug = blk;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ModelBlock getBottomPlug() {
        return this.bottomPlug;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WindowLocation getBottomPlugPos() {
        return super.getPos().add(this.getWidth() / 2, + this.getHeight() + ModelBlock.PLUGSIZE/2);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBottomPlugPos(WindowLocation pos) {
        super.setPos(pos.add(-this.getWidth()/2, -this.getHeight() - ModelBlock.PLUGSIZE/2));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTopSocket(ModelBlock blk) {
        this.topSocket = blk;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ModelBlock getTopSocket() {
        return this.topSocket;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WindowLocation getTopSocketPos() {
        return super.getPos().add(this.getWidth() / 2, + ModelBlock.PLUGSIZE/2);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTopSocketPos(WindowLocation pos) {
        super.setPos(pos.add(-this.getWidth()/2, -ModelBlock.PLUGSIZE/2));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasTopSocket(){
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasBottomPlug(){
        return true;
    }
}