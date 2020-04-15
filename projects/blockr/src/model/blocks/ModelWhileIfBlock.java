package model.blocks;

import java.util.ArrayList;

import gameworldapi.PredicateType;
import ui.UIBlock;
import utilities.*;

/**
 * Class representing the while and if blocks with one socket on the top and one plug at the bottom. They also have one socket on their 
 * right side for a condition block.
 */
public class ModelWhileIfBlock extends ModelBlock{
    private ModelBlock topSocket;
    private ModelBlock bottomPlug;
    private ModelBlock rightSocket;
    private ModelBlock cavitySocket;
    private ModelBlock cavityPlug;
    private boolean isIf;

    // Constructor
    public ModelWhileIfBlock(ProgramLocation pos, boolean isIf){
        super(pos);
        this.isIf = isIf;
        this.setTopSocket(null);
        this.setBottomPlug(null);
        this.setRightSocket(null);
        this.setCavityPlug(this);
        this.setCavitySocket(this);
        ArrayList<ConnectionPoint> connectionPoints = new ArrayList<>();
        connectionPoints.add(ConnectionPoint.BOTTOMPLUG);
        connectionPoints.add(ConnectionPoint.TOPSOCKET);
        connectionPoints.add(ConnectionPoint.CAVITYPLUG);
        connectionPoints.add(ConnectionPoint.CAVITYSOCKET);
        connectionPoints.add(ConnectionPoint.RIGHTSOCKET);
        super.setConnectionPoints(connectionPoints);
    }

    @Override
    public ModelWhileIfBlock clone() {
        if (this.isIf) { return new ModelWhileIfBlock(this.getPos(), true); }
        return new ModelWhileIfBlock(this.getPos(), false);
    }

    /**
     * Finds out if the predicate in the while/if block is negated
     * @return true if the predicate is negated, false otherwise
     * @author Oberon Swings
     */
    public boolean isNegated(){
        boolean negated = false;
        ModelBlock current = this.getRightSocket();
        while (!(current instanceof ModelPredicateBlock)){
            if (current instanceof ModelNotBlock){
                negated = !negated;
                current = current.getRightSocket();
            }
            else {
                return false;
            }
        }
        return negated;
    }

    /**
     * Finds out the predicate of the while/if block
     * @return the predicate which is coupled to this while/if block
     * @author Oberon Swings
     */
    public PredicateType getPredicate(){
        ModelBlock current = this.getRightSocket();
        while (!(current instanceof ModelPredicateBlock)){
            if (current == null){
                return null;
            }
            current = current.getRightSocket();
        }
        return ((ModelPredicateBlock)current).getPredicate();
    }

    public boolean isIf(){
        return isIf;
    }

    /**
     * {@inheritDoc}
     */
    public ModelBlock getTopSocket() {
        return this.topSocket;
    }

    /**
     * {@inheritDoc}
     */
    public void setTopSocket(ModelBlock topSocket) {
        this.topSocket = topSocket;
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
    public void setBottomPlug(ModelBlock bottomPlug) {
        this.bottomPlug = bottomPlug;
    }

    /**
     * Getter for the height of the cavity of the while and if block.
     * @return the height of the block
     * @author Oberon Swings
     */
    public int getCavityHeight() {
        if(!(getCavityBlocks().size() == 0)){
            return getCavityBlocks().size() * UIBlock.STD_HEIGHT;
        }
        else{
            return 0;
        } 
    }

    /**
     * Getter for a list of blocks this block has in its cavity.
     * @return the list of blocks.
     * @author Oberon Swings
     */
    public ArrayList<ModelBlock> getCavityBlocks() {
        ArrayList<ModelBlock> cav = new ArrayList<ModelBlock>();
        ModelBlock blk = this.getCavityPlug();
        while(!(blk.equals(this) || blk == null)){
            cav.add(blk);
            if (blk == null) return null;
            if(blk.hasBottomPlug()){
                blk = blk.getBottomPlug();
            }
        }
        return cav;
    }

    /**
     * {@inheritDoc}
     */
    public ModelBlock getRightSocket() {
        return this.rightSocket;
    }

    /**
     * {@inheritDoc}
     */
    public void setRightSocket(ModelBlock rightSocket) {
        this.rightSocket = rightSocket;
    }

    /**
     * 
     * @return the cavity of the socket.
     */
    public ModelBlock getCavitySocket() {
        return this.cavitySocket;
    }

    /**
     * 
     * @param cavitySocket sets the cavity of the socket.
     */
    public void setCavitySocket(ModelBlock cavitySocket) {
        this.cavitySocket = cavitySocket;
    }

    /**
     * 
     * @return the cavity of the plug.
     */
    public ModelBlock getCavityPlug() {
        return this.cavityPlug;
    }

    /**
     * 
     * @param cavityPlug sets the cavity of the plug.
     */
    public void setCavityPlug(ModelBlock cavityPlug) {
        this.cavityPlug = cavityPlug;
    }

    /**
     * 
     * @return the position of the cavity socket
     * @author Oberon Swings
     */
    public ProgramLocation getCavitySocketPos() {
        return this.getPos().add(2*UIBlock.STD_WIDTH/3, this.getHeight() - UIBlock.STD_HEIGHT/3);
    }

    /**
     * 
     * @return the position of the cavity plug
     * @author Oberon Swings
     */
    public ProgramLocation getCavityPlugPos() {
        return this.getPos().add(2*UIBlock.STD_WIDTH/3, 2*UIBlock.STD_HEIGHT/3);
    }

    /**
     * Calculates the distance between cavityPlug and TopSocket
     * @param top the block with the topSocket
     * @return the distance form the cavityPlug to the topSocket of the top block
     */
    public int distanceCavityPlug(ModelBlock top){
        return this.getCavityPlugPos().getDistance(top.getTopSocketPos());
    }

    /**
     * Calculates the distance between cavitySocket and bottomPlug
     * @param bottom the block with the bottomPlug
     * @return the distance form the cavitySocket to the bottomPlug of the bottom block
     */
    public int distanceCavitySocket(ModelBlock bottom){
        return this.getCavitySocketPos().getDistance(bottom.getBottomPlugPos());
    }
}