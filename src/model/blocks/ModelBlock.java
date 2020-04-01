package model.blocks;

import java.util.ArrayList;

import model.*;
import utilities.*;
/**
 * Abstract representation of a block that can be placed from the palette onto the program area.
 */
public abstract class ModelBlock extends ModelElement{

    public static final int PLUGSIZE = 20;
    private final BlockType type;
    private ArrayList<ConnectionPoint> connectionPoints = new ArrayList<>();

    public static final int STD_WIDTH = 80; //final standard width of blocks
    public static final int STD_HEIGHT = 80; //final standard height of blocks

    private boolean highlighted = false;
    
    // Constructor
    public ModelBlock(WindowLocation pos, BlockType type){
        super(pos);
        this.type = type;
    }

    public void setHighlight(){
        this.highlighted = true;
    }

    public void setUnHighlight(){
        System.out.println("UNHIGHLIGHT");
        this.highlighted = false;
    }

    public boolean isHighlighted(){
        return this.highlighted;
    }


    //For now a block is considered to be a square
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean inBounds(WindowLocation coordinate){
        if(this.getPos().getX() < coordinate.getX() && this.getPos().getX() + getWidth() > coordinate.getX() && this.getPos().getY() < coordinate.getY() && this.getPos().getY() + getWidth() > coordinate.getY() ){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * abstract method describing how a block will disconnect from another block in the program area.
     */
    public abstract void disconnect();

    /**
     * abstract method describing how a block will connect to another block
     * @param block the other block to connect to. 
     */
    public abstract void connect(ModelBlock block);

    /**
     * A method that gives the connection from a block with all its neighbours.
     * @return the list with all the connection of a block.
     * @author Oberon Swings
     */
    public ArrayList<ModelBlock> getConnections(){
        ArrayList<ModelBlock> connections = new ArrayList<ModelBlock>();
        if (this.hasBottomPlug() && this.getBottomPlug() != null) connections.add(this.getBottomPlug());
        if (this.hasLeftPlug() && this.getLeftPlug() != null) connections.add(this.getLeftPlug());
        if (this.hasTopSocket() && this.getTopSocket() != null) connections.add(this.getTopSocket());
        if (this.hasRightSocket() && this.getRightSocket() != null) connections.add(this.getRightSocket());
        if (this instanceof ModelWhileIfBlock) connections.addAll(((ModelWhileIfBlock) this).getCavityBlocks());
        return connections; 
    }

    /**
     * @return whether this block is fully connected (at all ends)
     * @author Oberon Swings
     */
    public boolean isFullyConnected() {
        if(this.hasTopSocket() && this.getTopSocket() == null) return false;
        if(this.hasBottomPlug() && this.getBottomPlug() == null) return false;
        if(this.hasRightSocket() && this.getRightSocket() == null) return false;
        if(this.hasLeftPlug() && this.getLeftPlug() == null) return false;
        return true;
    }
    
    /**
     * @return the height of the block.
     * @author Oberon Swings
     */
    public int getHeight() {
		if (this instanceof ModelWhileIfBlock){
            return (((ModelWhileIfBlock)this).getCavityHeight() + STD_HEIGHT);
        }
        else return STD_HEIGHT;

    }
    
    /**
     * @return the width of the block.
     * @author Oberon Swings
     */
    public int getWidth() {
		/*if (this instanceof ModelWhileIfBlock){
            return (((ModelWhileIfBlock)this).getCavityWidth() + STD_WIDTH);
        }*/
        return STD_WIDTH;
        /*It would be more clear if the while and if blocks don't become so wide, this probably becomes more clear when the blocks have different colours.
        Hopefully it doesn't become too confusing if multiple if or while blocks are nested within eachother.*/
    }
    
    /**
     * 
     * @return {Blocktype} the Blocktype of this block
     */
    public BlockType getBlockType() {
		return this.type;
    }

    /**
     *
     * @return true if and only if the block is within a cavity, false otherwise
     * @author Oberon Swings
     */
    public boolean isInCavity(){
        ModelBlock plug = this;
        if (this.hasBottomPlug()){
            while (!(plug instanceof ModelWhileIfBlock || plug == null)){
                plug = plug.getBottomPlug();
            }
            if (plug == null) return false;
            else if (plug instanceof ModelWhileIfBlock && ((ModelWhileIfBlock) plug).getCavityBlocks().contains(this)) return true;
        }
        return false;
    }

    /**
     *
     * @return the while/if block that has this block in it's cavity, null if this block is within no cavity
     * @author Oberon Swings
     */
    public ModelBlock getSurroundingWhileIfBlock(){
        ModelBlock plug = this;
        if (this.hasBottomPlug()){
            while (!(plug instanceof ModelWhileIfBlock || plug == null)){
                plug = plug.getBottomPlug();
            }
            if (plug == null) return null;
            else if (plug instanceof ModelWhileIfBlock && ((ModelWhileIfBlock) plug).getCavityBlocks().contains(this)) return plug;
        }
        return null;
    }

    /**
     * Updates the position of the block according to their left and top connections to other blocks
     * The update goes from top to bottom and from left to right
     * @author Oberon Swings
     */
    public void updatePos(){
        if (this.hasTopSocket() && this.getTopSocket() != null){
            this.setTopSocketPos(this.getTopSocket().getBottomPlugPos());
        }
        if (this.hasBottomPlug() && this.getBottomPlug() != null){
            this.getBottomPlug().updatePos();
        }
        if (this.hasLeftPlug() && this.getLeftPlug() != null){
            this.setLeftPlugPos(this.getLeftPlug().getRightSocketPos());
        }
        if (this.hasRightSocket() && this.getRightSocket() != null){
            this.getRightSocket().updatePos();
        }
        if (this instanceof ModelWhileIfBlock) ((ModelWhileIfBlock) this).updateCavityBlocksLocations();
    }

    /**
     * 
     * @return {boolean} true if this block has a top socket, false otherwise
     */
    public boolean hasTopSocket(){
        if (this.connectionPoints.contains(ConnectionPoint.TOPSOCKET)) return true;
        else return false;
    }

    /**
     * 
     * @return {boolean} true if this block has a bottom plug, false otherwise
     */
    public boolean hasBottomPlug(){
        if (this.connectionPoints.contains(ConnectionPoint.BOTTOMPLUG)) return true;
        else return false;
    }

    /**
     * 
     * @return {boolean} true if this block has a right socket, false otherwise
     */
    public boolean hasRightSocket(){
        if (this.connectionPoints.contains(ConnectionPoint.RIGHTSOCKET)) return true;
        else return false;
    }

    /**
     * 
     * @return {boolean} true if this block has a left plug, false otherwise
     */
    public boolean hasLeftPlug(){
        if (this.connectionPoints.contains(ConnectionPoint.LEFTPLUG)) return true;
        else return false;
    }

    /**
     *
     * @return {boolean} true if this block has a cavity socket, false otherwise
     */
    public boolean hasCavitySocket(){
        if (this.connectionPoints.contains(ConnectionPoint.CAVITYSOCKET)) return true;
        else return false;
    }

    /**
     *
     * @return {boolean} true if this block has a cavity plug, false otherwise
     */
    public boolean hasCavityPlug(){
        if (this.connectionPoints.contains(ConnectionPoint.CAVITYPLUG)) return true;
        else return false;
    }

    public void setConnectionPoints(ArrayList<ConnectionPoint> connectionPoints) {
        this.connectionPoints = connectionPoints;
    }

    public ModelBlock getBottomPlug(){
        return null;
    }
    public ModelBlock getTopSocket(){
        return null;
    }
    public ModelBlock getLeftPlug(){
        return null;
    }
    public ModelBlock getRightSocket(){
        return null;
    }
    public void setBottomPlug(ModelBlock block){

    }
    public void setTopSocket(ModelBlock block){

    }
    public void setLeftPlug(ModelBlock block){

    }
    public void setRightSocket(ModelBlock block){

    }

    public WindowLocation getTopSocketPos() {
        return super.getPos().add(this.getWidth() / 2, + ModelBlock.PLUGSIZE/2);
    }


    public void setTopSocketPos(WindowLocation pos) {
        super.setPos(pos.add(-this.getWidth()/2, -ModelBlock.PLUGSIZE/2));
    }

    public WindowLocation getBottomPlugPos() {
        return super.getPos().add(this.getWidth() / 2, + this.getHeight() + ModelBlock.PLUGSIZE/2);
    }


    public void setBottomPlugPos(WindowLocation pos) {
        super.setPos(pos.add(-this.getWidth()/2, -this.getHeight() - ModelBlock.PLUGSIZE/2));
    }

    public WindowLocation getLeftPlugPos() {
        return super.getPos().add(- ModelBlock.PLUGSIZE / 2, + this.getHeight() / 2);
    }


    public void setLeftPlugPos(WindowLocation pos) {
        super.setPos(pos.add(ModelBlock.PLUGSIZE/2, -this.getHeight()/2));
    }


    public WindowLocation getRightSocketPos() {
        return super.getPos().add(this.getWidth() - ModelBlock.PLUGSIZE/2, + this.getHeight() / 2);
    }


    public void setRightSocketPos(WindowLocation pos) {
        super.setPos(pos.add(-this.getWidth() + ModelBlock.PLUGSIZE/2, -this.getHeight()/2));
    }
}