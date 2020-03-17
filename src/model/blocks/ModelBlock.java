package model.blocks;

import java.util.ArrayList;

import model.*;
import model.blocks.plugs.*;
import utilities.*;
/**
 * Abstract representation of a block that can be placed from the palette onto the program area.
 */
public abstract class ModelBlock extends ModelElement{

    private int width = 75; //variable can change because of cavity
    private int height = 75; //variable can change because of cavity

    public static final int PLUGSIZE = 20;
    private final Blocktype type;
    /*
    private final Location topSocketPos = super.getPos().add(this.getWidth() / 2, + this.getPlugSize()/2);
    private final Location bottomPlugPos = super.getPos().add(this.getWidth() / 2, + this.getHeight() + this.getPlugSize()/2);
    private final Location rightSocketPos = super.getPos().add(this.getWidth() + this.getPlugSize()/2, + this.getHeight() / 2);
    private final Location leftPlugPos = super.getPos().add(- this.getPlugSize() / 2, + this.getHeight() / 2);
    */
    public static final int STD_WIDTH = 75; //final standard width of blocks
    public static final int STD_HEIGHT = 75; //final standard height of blocks

    private boolean highlighted = false;
    
    // Constructor
    public ModelBlock(WindowLocation pos, Blocktype type){
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
        if(this.getPos().getX() < coordinate.getX() && this.getPos().getX() + width > coordinate.getX() && this.getPos().getY() < coordinate.getY() && this.getPos().getY() + width > coordinate.getY() ){
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
    public void move(WindowLocation newPos, ModelBlock closest){
        super.setPos(newPos);
        this.disconnect();
        if (newPos.getDistance(closest.getPos()) < 50){
            this.connect(closest);
        }
        if (this.hasTopSocket() && ((TopSocket)this).getTopSocket() == closest){
            super.setPos(closest.getPos().add(0,this.getHeight()));
        }
        if (this.hasBottomPlug() && ((BottomPlug)this).getBottomPlug() == closest){
            super.setPos(closest.getPos().add(0,-this.getHeight()));
        }
        if (this.hasRightSocket() && ((RightSocket)this).getRightSocket() == closest){
            super.setPos(closest.getPos().add(closest.getWidth(),0));
        }
        if (this.hasLeftPlug() && ((LeftPlug)this).getLeftPlug() == closest){
            super.setPos(closest.getPos().add(-closest.getWidth(),0));
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
     */
    public ArrayList<ModelBlock> getConnections(){
        ArrayList<ModelBlock> connections = new ArrayList<ModelBlock>();
        if (this.hasBottomPlug() && ((BottomPlug)this).getBottomPlug() != null) connections.add(((BottomPlug)this).getBottomPlug());
        if (this.hasLeftPlug() && ((LeftPlug)this).getLeftPlug() != null) connections.add(((LeftPlug)this).getLeftPlug());
        if (this.hasTopSocket() && ((TopSocket)this).getTopSocket() != null) connections.add(((TopSocket)this).getTopSocket());
        if (this.hasRightSocket() && ((RightSocket)this).getRightSocket() != null) connections.add(((RightSocket)this).getRightSocket());
        return connections; 
    }

    /**
     * @return whether this block is fully connected (at all ends)
     */
    public boolean isFullyConnected() {
        if(this.hasTopSocket() && ((TopSocket)this).getTopSocket() == null) return false;
        if(this.hasBottomPlug() && ((BottomPlug)this).getBottomPlug() == null) return false;
        if(this.hasRightSocket() && ((RightSocket)this).getRightSocket() == null) return false;
        if(this.hasLeftPlug() && ((LeftPlug)this).getLeftPlug() == null) return false;
        return true;
    }
    
    /**
     * @return the height of the block.
     */
    public int getHeight() {
		if (this instanceof ModelWhileIfBlock){
            return (((ModelWhileIfBlock)this).getCavityHeight() + STD_HEIGHT);
        }
        else return STD_HEIGHT;
    }
    
    /**
     * @return the width of the block.
     */
    public int getWidth() {
		if (this instanceof ModelWhileIfBlock){
            return (((ModelWhileIfBlock)this).getCavityWidth() + STD_WIDTH);
        }
        else return STD_WIDTH;
    }
    
    /**
     * 
     * @return {Blocktype} the Blocktype of this block
     */
    public Blocktype getBlockType() {
		return this.type;
    }

    /**
     * If the block implementation has a top socket, override this function to return true
     * 
     * @return {boolean} true if this block has a top socket, false otherwise
     */
    public boolean hasTopSocket(){
        return false;
    }

    /**
     * If the block implementation has a bottom plug, override this function to return true
     * 
     * @return {boolean} true if this block has a bottom plug, false otherwise
     */
    public boolean hasBottomPlug(){
        return false;
    }

    /**
     * If the block implementation has a right socket, override this function to return true
     * 
     * @return {boolean} true if this block has a right socket, false otherwise
     */
    public boolean hasRightSocket(){
        return false;
    }

    /**
     * If the block implementation has a left, override this function to return true
     * 
     * @return {boolean} true if this block has a left plug, false otherwise
     */
    public boolean hasLeftPlug(){
        return false;
    }

}