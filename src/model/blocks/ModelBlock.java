package model.blocks;

import java.util.ArrayList;

import utilities.Blocktype;
import model.*;
import utilities.*;
/**
 * Abstract representation of a block that can be placed from the palette onto the program area.
 */
public abstract class ModelBlock extends ModelElement{

    //TODO socket and plug positions just getfunction, no variable.
    //TODO cleaner solution for the getPlug and getSocket functions

    private int width = 120; //variable can change
    private int height = 120; //variable can change

    private final int plugSize = 40;
    private final Blocktype type;
    private final Location topSocketPos = super.getPos().add(this.getWidth() / 2, + this.getPlugSize()/2);
    private final Location bottomPlugPos = super.getPos().add(this.getWidth() / 2, + this.getHeight() + this.getPlugSize()/2);
    private final Location rightSocketPos = super.getPos().add(this.getWidth() + this.getPlugSize()/2, + this.getHeight() / 2);
    private final Location leftPlugPos = super.getPos().add(- this.getPlugSize() / 2, + this.getHeight() / 2);
    
    public static final int WIDTH = 120; //final standard width of blocks
    public static final int HEIGHT = 120; //final standard height of blocks

    private boolean highlighted = false;
    

    public ModelBlock(Location pos, Blocktype type){
        super(pos);
        this.type = type;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void move(Location newPos){
        super.move(newPos);

    }

    public void switchHighlight(){
        this.highlighted = !(this.highlighted);
    }

    public boolean isHighlighted(){
        return this.highlighted;
    }

    //For now a block is considered to be a square
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean inBounds(Location coordinate){
        if(this.getPos().getX() < coordinate.getX() && this.getPos().getX() + width > coordinate.getX() && this.getPos().getY() < coordinate.getY() && this.getPos().getY() + width > coordinate.getY() ){
            System.out.println("inbounds");
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Connects this block to the closest block if the distance is less than 50 (pixels), puts the block directly next to the connecting block.
     * If the distance is more than 50 (pixels), the block will be placed exactly at the newPos location.
     * @param newPos The location to which the block is dragged, possibly the new location except if the block connects to a neighbour, then the new
     *              location will be determined by the position of this neighbour and the height/width of the two blocks.
     * @param closest The block closest to the location where the block is dragged.
     */
    public void move(Location newPos, ModelBlock closest){
        super.move(newPos);
        this.disconnect();
        if (newPos.getDistance(closest.getPos()) < 50){
            this.connect(closest);
        }
        if (this.getTopSocket() == closest){
            super.move(closest.getPos().add(0,this.getHeight()));
        }
        if (this.getBottomPlug() == closest){
            super.move(closest.getPos().add(0,-this.getHeight()));
        }
        if (this.getRightSocket() == closest){
            super.move(closest.getPos().add(closest.getWidth(),0));
        }
        if (this.getLeftPlug() == closest){
            super.move(closest.getPos().add(-closest.getWidth(),0));
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

    
    //These getters and setters will be overwritten in the specific blocktype classes so that when they do support the socket or plug it will
    //execute as expected.
    //@return IllegalStateException when the socket or plug is not supported, iow when the function is not overwritten in the subclass.
     

    /**
     * 
     * @return the top socket
     * @throws IllegalStateException if the socket is not supported on this type.
     */
    public ModelBlock getTopSocket(){
        throw new IllegalStateException("This blocktype doesn't support this socket.");
    }

    /**
     * 
     * @param blk the block that this block connects to.
     * @throws IllegalStateException if the socket is not supported on this type.
     */
    public void setTopSocket(ModelBlock blk){
        throw new IllegalStateException("This blocktype doesn't support this socket.");
    }

    /**
     * 
     * @return the bottom plug
     * @throws IllegalStateException if the plug is not supported on this type.
     */
    public ModelBlock getBottomPlug(){
        throw new IllegalStateException("This blocktype doesn't support this plug.");
    }

    /**
     * 
     * @param blk the block that this block connects to.
     * @throws IllegalStateException if the plug is not supported on this type.
     */
    public void setBottomPlug(ModelBlock blk){
        throw new IllegalStateException("This blocktype doesn't support this plug.");
    }
    
    /**
     * 
     * @return the left plug
     * @throws IllegalStateException if the plug is not supported on this type.
     */
    public ModelBlock getLeftPlug(){
        throw new IllegalStateException("This blocktype doesn't support this plug.");
    }

    /**
     * 
     * @param blk the block that this block connects to.
     * @throws IllegalStateException if the plug is not supported on this type.
     */
    public void setLeftPlug(ModelBlock blk){
        throw new IllegalStateException("This blocktype doesn't support this plug.");
    }

    /**
     * 
     * @return the right socket
     * @throws IllegalStateException if the socket is not supported on this type.
     */
    public ModelBlock getRightSocket(){
        throw new IllegalStateException("This blocktype doesn't support this socket.");
    }

    /**
     * 
     * @param blk: the block that this block connects
     * @throws IllegalStateException if the socket is not supported on this type.
     */
    public void setRightSocket(ModelBlock blk){
        throw new IllegalStateException("This blocktype doesn't support this socket.");
    }

    /**
     * A method that gives the connection from a block with all its neighbours.
     * @return the list with all the connection of a block.
     */
    public ArrayList<ModelBlock> getConnections(){
        ArrayList<ModelBlock> connections = new ArrayList<ModelBlock>();
        connections.add(getBottomPlug());
        connections.add(getLeftPlug());
        connections.add(getTopSocket());
        connections.add(getRightSocket());
        return connections; 
    }

    /**
     * @return the height of the block.
     */
    public int getHeight() {
		return this.height;
    }
    
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * @return the width of the block.
     */
    public int getWidth() {
		return this.width;
    }
    
    /**
     * 
     * @return the position of the bottom plug.
     */
    public Location getBottomPlugPos() {
		return this.bottomPlugPos;
	}

    /**
     * 
     * @return the position of the top socket.
     */
    public Location getTopSocketPos() {
		return this.topSocketPos;
	}

    /**
     * 
     * @return the position of the right socket.
     */
    public Location getRightSocketPos() {
		return this.rightSocketPos;
	}

    /**
     * 
     * @return the position of the lefts socket.
     */
    public Location getLeftPlugPos() {
		return this.leftPlugPos;
    }
    
    public Blocktype getBlockType() {
		return this.type;
    }
    

    public int getPlugSize() {
        return this.plugSize;
    }

    public boolean hasTopSocket(){
        return false;
    }

    public boolean hasBottomPlug(){
        return false;
    }

    public boolean hasRightSocket(){
        return false;
    }

    public boolean hasLeftPlug(){
        return false;
    }

	public boolean isFullyConnected() {
        if(this.hasTopSocket() && this.getTopSocket() == null) return false;
        if(this.hasBottomPlug() && this.getBottomPlug() == null) return false;
        if(this.hasRightSocket() && this.getRightSocket() == null) return false;
        if(this.hasLeftPlug() && this.getLeftPlug() == null) return false;
        return true;
	}

}