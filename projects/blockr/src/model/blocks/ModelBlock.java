package model.blocks;

import java.util.ArrayList;

import model.*;
import ui.UIBlock;
import utilities.*;
/**
 * Abstract representation of a block that can be placed from the palette onto the program area.
 */
public abstract class ModelBlock extends ModelElement{

    private final BlockType type;
    private ArrayList<ConnectionPoint> connectionPoints = new ArrayList<>();

    private boolean highlighted = false;
    
    // Constructor
    public ModelBlock(Location windowLocation, BlockType type){
        super(windowLocation);
        this.type = type;
    }

    public void setHighlight(){
        this.highlighted = true;
    }

    public void setUnHighlight(){
        
        this.highlighted = false;
    }

    public boolean isHighlighted(){
        return this.highlighted;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean inBoundsOfElement(Location coordinate){
        if(this.getPos().getX() < coordinate.getX() && this.getPos().getX() + getWidth() > coordinate.getX() && this.getPos().getY() < coordinate.getY() && this.getPos().getY() + getWidth() > coordinate.getY() ){
            return true;
        }
        else{
            return false;
        }
    }

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
            return (((ModelWhileIfBlock)this).getCavityHeight() + UIBlock.STD_HEIGHT);
        }
        else return UIBlock.STD_HEIGHT;

    }
    
    /**
     * @return the width of the block.
     * @author Oberon Swings
     */
    public int getWidth() {
        return UIBlock.STD_WIDTH;
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
    public ModelWhileIfBlock getSurroundingWhileIfBlock(){
        ModelBlock plug = this;
        if (this.hasBottomPlug()){
            while (!(plug instanceof ModelWhileIfBlock || plug == null)){
                plug = plug.getBottomPlug();
            }
            if (plug == null) return null;
            else if (plug instanceof ModelWhileIfBlock && ((ModelWhileIfBlock) plug).getCavityBlocks().contains(this)) return ((ModelWhileIfBlock)plug);
        }
        return null;
    }

    /**
     * TODO move to LocationController
     * 
     * TODO2 this method causes a bug (it might be constantly calling itself)
     * Updates the position of the block according to their left and top connections to other blocks
     * The update goes from top to bottom and from left to right
     * @author Oberon Swings
     * 
     * 
     */
    public void updatePos(){
        System.out.println("updatepos");
        //System.out.println(this.getBlockType().toString());
        /*
        try{
        System.out.println(this.getBottomPlug().getBlockType().toString());
        }
        catch(Exception e){
            System.out.println("null");
        }
        */
        
        if (this.hasTopSocket() && this.getTopSocket() != null){
            //System.out.println("topsocket update pos");
            this.setTopSocketPos(this.getTopSocket().getBottomPlugPos());
        }

        if (this.hasBottomPlug() && this.getBottomPlug() != null){
            //System.out.println("bottomplug update pos");
            this.getBottomPlug().updatePos();
        }

        if (this.hasLeftPlug() && this.getLeftPlug() != null){
            //System.out.println("leftplug update pos");
            this.setLeftPlugPos(this.getLeftPlug().getRightSocketPos());
        }
        if (this.hasRightSocket() && this.getRightSocket() != null){
            //System.out.println("rightsocket update pos");
            this.getRightSocket().updatePos();
        }
        if (this instanceof ModelWhileIfBlock) ((ModelWhileIfBlock) this).updateCavityBlocksLocations();
    }

    public void setTopSocketPos(Location pos) {
        super.setPos(pos.add(-this.getWidth()/2, -UIBlock.PLUGSIZE/2));
    }
    public void setLeftPlugPos(Location pos) {
        super.setPos(pos.add(UIBlock.PLUGSIZE/2, -this.getHeight()/2));
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

    protected void setConnectionPoints(ArrayList<ConnectionPoint> connectionPoints) {
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

    public Location getTopSocketPos() {
        return super.getPos().add(this.getWidth() / 2, + UIBlock.PLUGSIZE/2);
    }
    public Location getBottomPlugPos() {
        return super.getPos().add(this.getWidth() / 2, + this.getHeight() + UIBlock.PLUGSIZE/2);
    }
    public Location getLeftPlugPos() {
        return super.getPos().add(- UIBlock.PLUGSIZE / 2, + this.getHeight() / 2);
    }
    public Location getRightSocketPos() {
        return super.getPos().add(this.getWidth() - UIBlock.PLUGSIZE/2, + this.getHeight() / 2);
    }


    /**
     * Finds out if this block is compatible with the right block
     * @param right the block which this block is checked against
     * @return true if this has a rightSocket and right has a leftPlug
     */
    public boolean compatibleLeftRight(ModelBlock right){
        if (this.hasRightSocket() && right.hasLeftPlug()) return true;
        else return false;
    }

    /**
     * Finds out if this block is compatible with the bottom block
     * @param bottom the block which this block is checked against
     * @return true if this has a bottomPlug and bottom has a topSocket
     */
    public boolean compatibleTopBottom(ModelBlock bottom){
        if (this.hasBottomPlug() && bottom.hasTopSocket()) return true;
        else return false;
    }

    /**
     * Calculates the distance between rightSocket and leftPlug
     * @param right the block with the leftPlug
     * @return the distance form the rightSocket to the leftPlug of the right block
     */
    public int distanceLeftRight(ModelBlock right){
        return this.getRightSocketPos().getDistance(right.getLeftPlugPos());
    }

    /**
     * Calculates the distance between bottomPlug and TopSocket
     * @param bottom the block with the topSocket
     * @return the distance form the bottomPlug to the topSocket of the bottom block
     */
    public int distanceTopBottom(ModelBlock bottom){
        return this.getBottomPlugPos().getDistance(bottom.getTopSocketPos());
    }
    
}