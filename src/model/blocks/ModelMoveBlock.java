package model.blocks;

import utilities.BlockType;
import utilities.ConnectionPoint;
import utilities.WindowLocation;

import java.util.ArrayList;

/**
 * Class representing the move forward, turn left and turn right blocks with one socket at the top and one plug at the bottom.
 */
public class ModelMoveBlock extends ModelBlock{

    private ModelBlock topSocket;
    private ModelBlock bottomPlug;
    
    public ModelMoveBlock(WindowLocation pos, BlockType type) {
        super(pos,type);
        this.setTopSocket(null);
        this.setBottomPlug(null);
        ArrayList<ConnectionPoint> connectionPoints = new ArrayList<>();
        connectionPoints.add(ConnectionPoint.BOTTOMPLUG);
        connectionPoints.add(ConnectionPoint.TOPSOCKET);
        super.setConnectionPoints(connectionPoints);
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
                ((ModelWhileIfBlock)this.getSurroundingWhileIfBlock()).disconnectCavity(this);
            }
            else {
                this.getTopSocket().setBottomPlug(null);
            }
            this.setTopSocket(null);
        }
        if (this.getBottomPlug() != null){
            if (this.isInCavity()){
                //if this block is in the cavity of the while if block it should be removed from that
                ((ModelWhileIfBlock)this.getSurroundingWhileIfBlock()).disconnectCavity(this);
            }
            else{
                this.getBottomPlug().setTopSocket(null);
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
        boolean connected = false;
        if (block instanceof ModelWhileIfBlock){
            connected = ((ModelWhileIfBlock) block).connectCavity(this);
        }
        if (connected) return;
        else if (block.isInCavity()){
            ((ModelWhileIfBlock)block.getSurroundingWhileIfBlock()).connectIntoCavity(this, block);
        }
        else if (block.hasBottomPlug() && (this.getTopSocketPos().getDistance(block.getBottomPlugPos()) < ModelBlock.PLUGSIZE * 1.5)
        && block.getBottomPlug() == null) {
            this.setTopSocket(block);
            block.setBottomPlug(this);
            this.setTopSocketPos(block.getBottomPlugPos());
        }
        else if (block.hasTopSocket() && (this.getBottomPlugPos().getDistance(block.getTopSocketPos()) < ModelBlock.PLUGSIZE * 1.5)
        && block.getTopSocket() == null){
            this.setBottomPlug(block);
            block.setTopSocket(this);
            this.setBottomPlugPos(block.getTopSocketPos());
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
    public void setBottomPlug(ModelBlock blk) {
        this.bottomPlug = blk;
    }

    /**
     * {@inheritDoc}
     */
    public ModelBlock getBottomPlug() {
        return this.bottomPlug;
    }




    /**
     * {@inheritDoc}
     */
    public void setTopSocket(ModelBlock blk) {
        this.topSocket = blk;
    }

    /**
     * {@inheritDoc}
     */
    public ModelBlock getTopSocket() {
        return this.topSocket;
    }




}