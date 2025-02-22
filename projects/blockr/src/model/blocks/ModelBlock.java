package model.blocks;

import java.util.ArrayList;

import model.*;
import ui.UIBlock;
import utilities.*;
/**
 * Abstract representation of a block that can be placed from the palette onto the program area.
 */
public abstract class ModelBlock extends ModelElement implements java.lang.Cloneable{

    private ArrayList<ConnectionPoint> connectionPoints = new ArrayList<>();

    private boolean highlighted = false;
    
    // Constructor
    public ModelBlock(ProgramLocation windowLocation){
        super(windowLocation);
    }

    /**
     * highlights the block.
     */
    public void setHighlight(){  this.highlighted = true;
    }

    /**
     * unhighlights the block.
     */
    public void setUnHighlight(){
        this.highlighted = false;
    }

    /**
     *
     * @return true if the block is highlighted, false otherwise.
     */
    public boolean isHighlighted(){
        return this.highlighted;
    }

    @Override
    public abstract ModelBlock clone();

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean inBoundsOfElement(ProgramLocation coordinate){
        return (this.getPos().getX() <= coordinate.getX() && this.getPos().getX() + getWidth() >= coordinate.getX() && this.getPos().getY() <= coordinate.getY() && this.getPos().getY() + getHeight() >= coordinate.getY());
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
        if (this instanceof ModelCavityBlock) connections.addAll(((ModelCavityBlock) this).getCavityBlocks());
        return connections; 
    }

    /**
     *
     * @return all connections except bottomPlug and topSocket
     */
    public ArrayList<ModelBlock> getConnectionsCavityRight() {
        ArrayList<ModelBlock> connections = new ArrayList<ModelBlock>();
        if (this.hasRightSocket() && this.getRightSocket() != null) connections.add(this.getRightSocket());
        if (this instanceof ModelCavityBlock) connections.addAll(((ModelCavityBlock) this).getCavityBlocks());
        return connections;
    }
    
    /**
     * @return the height of the block.
     * @author Oberon Swings
     */
    public int getHeight() {
		if (this instanceof ModelCavityBlock){
            return (((ModelCavityBlock)this).getCavityHeight() + UIBlock.STD_HEIGHT);
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
     * @return true if and only if the block is within a cavity, false otherwise
     * @author Oberon Swings
     */
    public boolean isInCavity(){
        if (this.hasTopSocket()){
            ModelBlock socket = this.getTopSocket();
            while (socket != null){
                if (socket instanceof ModelCavityBlock && ((ModelCavityBlock) socket).getCavityBlocks().contains(this)) return true;
                socket = socket.getTopSocket();
            }
            return false;
        }
        return false;
    }

    /**
     *
     * @return the cavity block that has this block in it's cavity, null if this block is within no cavity
     * @author Oberon Swings
     */
    public ModelCavityBlock getSurroundingCavityBlock(){
        if (this.hasTopSocket()){
            ModelBlock socket = this.getTopSocket();
            while (socket != null){
                if (socket instanceof ModelCavityBlock && ((ModelCavityBlock) socket).getCavityBlocks().contains(this)) return ((ModelCavityBlock)socket);
                socket = socket.getTopSocket();
            }
            return null;
        }
        return null;
    }

    /**
     *
     * @return the function block that has this block in it's cavity, null if this block is within no cavity
     * @author Oberon Swings
     */
    public ModelFunctionDefinitionBlock getSurroundingDefinitionBlock(){
        if (this.hasTopSocket()){
            ModelBlock socket = this.getTopSocket();
            while (socket != null){
                if (socket instanceof ModelFunctionDefinitionBlock && ((ModelFunctionDefinitionBlock) socket).getCavityBlocks().contains(this)) return ((ModelFunctionDefinitionBlock)socket);
                socket = socket.getTopSocket();
            }
            return null;
        }
        return null;
    }

    /**
     * 
     * @return {boolean} true if this block has a top socket, false otherwise
     */
    public boolean hasTopSocket(){
        return (this.connectionPoints.contains(ConnectionPoint.TOP_SOCKET));
    }

    /**
     * 
     * @return {boolean} true if this block has a bottom plug, false otherwise
     */
    public boolean hasBottomPlug(){
        return (this.connectionPoints.contains(ConnectionPoint.BOTTOM_PLUG));
    }

    /**
     * 
     * @return {boolean} true if this block has a right socket, false otherwise
     */
    public boolean hasRightSocket(){
        return  (this.connectionPoints.contains(ConnectionPoint.RIGHT_SOCKET));
    }

    /**
     * 
     * @return {boolean} true if this block has a left plug, false otherwise
     */
    public boolean hasLeftPlug(){
        return (this.connectionPoints.contains(ConnectionPoint.LEFT_PLUG));
    }

    /**
     * Sets the connection points this block has.
     * @param connectionPoints
     */
    void setConnectionPoints(ArrayList<ConnectionPoint> connectionPoints) {
        this.connectionPoints = connectionPoints;
    }

    /**
     *
     * @return the connection points this block has.
     */
    public ArrayList<ConnectionPoint> getConnectionPoints() {
        return connectionPoints;
    }

    /**
     *
     * @return the block connected to this block's bottom plug.
     */
    public ModelBlock getBottomPlug(){
        return null;
    }

    /**
     *
     * @return the block connected to this block's top socket.
     */
    public ModelBlock getTopSocket(){
        return null;
    }

    /**
     *
     * @return the block connected to this block's left plug.
     */
    public ModelBlock getLeftPlug(){
        return null;
    }

    /**
     *
     * @return the block connected to this block's right socket.
     */
    public ModelBlock getRightSocket(){
        return null;
    }

    /**
     * set the bottomPlug connection of this block to the given block.
     * @param block block to be connected
     */
    public void setBottomPlug(ModelBlock block){}

    /**
     * set the topSocket connection of this block to the given block.
     * @param block block to be connected
     */
    public void setTopSocket(ModelBlock block){}

    /**
     * set the leftPlug connection of this block to the given block.
     * @param block block to be connected
     */
    public void setLeftPlug(ModelBlock block){}

    /**
     * set the rightSocket connection of this block to the given block.
     * @param block block to be connected
     */
    public void setRightSocket(ModelBlock block){}

    /**
     *
     * @return the location of the topSocket of this block.
     */
    public ProgramLocation getTopSocketPos() {
        return super.getPos().add(UIBlock.STD_WIDTH / 2, + UIBlock.PLUGSIZE/2);
    }

    /**
     *
     * @return the location of the bottomPlug of this block.
     */
    public ProgramLocation getBottomPlugPos() {
        return super.getPos().add(UIBlock.STD_WIDTH / 2, + this.getHeight() + UIBlock.PLUGSIZE/2);
    }

    /**
     *
     * @return the location of the leftPlug of this block.
     */
    private ProgramLocation getLeftPlugPos() {
        return super.getPos().add(- UIBlock.PLUGSIZE / 2, + UIBlock.STD_HEIGHT / 2);
    }

    /**
     *
     * @return the location of the rightSocket of this block.
     */
    public ProgramLocation getRightSocketPos() {
        return super.getPos().add(this.getWidth() - UIBlock.PLUGSIZE/2, + UIBlock.STD_HEIGHT / 2);
    }

    /**
     * Finds out if this block is compatible with the right block
     * @param right the block which this block is checked against
     * @return true if this has a rightSocket and right has a leftPlug
     */
    public boolean compatibleLeftRight(ModelBlock right){
        return (this.hasRightSocket() && right.hasLeftPlug());
    }

    /**
     * Finds out if this block is compatible with the bottom block
     * @param bottom the block which this block is checked against
     * @return true if this has a bottomPlug and bottom has a topSocket
     */
    public boolean compatibleTopBottom(ModelBlock bottom){
        return (this.hasBottomPlug() && bottom.hasTopSocket());
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

    /**
     *
     * @return true if the block is an IFBlock
     */
    public boolean isIf(){
        return false;
    }

    /**
     *
     * @return the title of the block
     */
    public String getTitle(){
        if (this instanceof ModelWhileIfBlock) {
            if (this.isIf()) return "IF";
            else return "WHILE";
        }
        if (this instanceof ModelNotBlock) return "NOT";
        if (this instanceof ModelActionBlock){
            return ((ModelActionBlock)this).getAction().toString();
        }
        if (this instanceof ModelPredicateBlock){
            return ((ModelPredicateBlock)this).getPredicate().toString();
        }
        if (this instanceof ModelFunctionDefinitionBlock){
            return "FUNC DEF " + ((ModelFunctionDefinitionBlock) this).getId();
        }
        if (this instanceof ModelFunctionCallBlock){
            return "FUNC CALL " + ((ModelFunctionCallBlock) this).getId();
        }
        return "Block";
    }

    /**
     * Finds the next block in the program execution according to the gamestate and the block connections.
     * @return One of its connections or a block that is linked to this block in another way (definition and call blocks)
     */
    public abstract ModelBlock findNextBlock();
}