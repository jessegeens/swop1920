package model.blocks;

import java.util.ArrayList;

import model.*;
import model.blocks.plugs.*;
import utilities.*;
/**
 * Abstract representation of a block that can be placed from the palette onto the program area.
 */
public abstract class ModelBlock extends ModelElement{

    public static final int PLUGSIZE = 20;
    private final Blocktype type;

    public static final int STD_WIDTH = 80; //final standard width of blocks
    public static final int STD_HEIGHT = 80; //final standard height of blocks

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
        if (this.hasBottomPlug() && ((BottomPlug)this).getBottomPlug() != null) connections.add(((BottomPlug)this).getBottomPlug());
        if (this.hasLeftPlug() && ((LeftPlug)this).getLeftPlug() != null) connections.add(((LeftPlug)this).getLeftPlug());
        if (this.hasTopSocket() && ((TopSocket)this).getTopSocket() != null) connections.add(((TopSocket)this).getTopSocket());
        if (this.hasRightSocket() && ((RightSocket)this).getRightSocket() != null) connections.add(((RightSocket)this).getRightSocket());
        if (this instanceof ModelWhileIfBlock) connections.addAll(((ModelWhileIfBlock) this).getCavityBlocks());
        return connections; 
    }

    /**
     * @return whether this block is fully connected (at all ends)
     * @author Oberon Swings
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
    public Blocktype getBlockType() {
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
                plug = ((BottomPlug)plug).getBottomPlug();
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
                plug = ((BottomPlug)plug).getBottomPlug();
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
        if (this.hasTopSocket() && ((TopSocket)this).getTopSocket() != null){
            ((TopSocket)this).setTopSocketPos(((BottomPlug)((TopSocket) this).getTopSocket()).getBottomPlugPos());
        }
        if (this.hasBottomPlug() && ((BottomPlug)this).getBottomPlug() != null){
            ((BottomPlug)this).getBottomPlug().updatePos();
        }
        if (this.hasLeftPlug() && ((LeftPlug)this).getLeftPlug() != null){
            ((LeftPlug)this).setLeftPlugPos(((RightSocket)((LeftPlug)this).getLeftPlug()).getRightSocketPos());
        }
        if (this.hasRightSocket() && ((RightSocket)this).getRightSocket() != null){
            ((RightSocket)this).getRightSocket().updatePos();
        }
        if (this instanceof ModelWhileIfBlock) ((ModelWhileIfBlock) this).updateCavityBlocksLocations();
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