package model.blocks;

import utilities.Location;

/**
 * Class representing the move forward, turn left and turn right blocks with one socket at the top and one plug at the bottom.
 */
public class ModelMoveBlock extends ModelBlock {

    public static final int FORWARD = 0;
    public static final int LEFT = 1;
    public static final int RIGHT = 2;

    private ModelBlock topSocket;
    private ModelBlock bottomPlug;


    private int type;    //0 voor forward, 1 naar left, 2 naar right

    
    public ModelMoveBlock(Location pos, int type) {
        super.setPos(pos);
        this.setType(type);
        this.setTopSocket(null);
        this.setBottomPlug(null);
    }


    /**
     * Method describing how a block will disconnect from another block in the program area.
     */
    @Override
    public void disconnect() {
        if (this.getTopSocket() != null){
            this.getTopSocket().setBottomPlug(null);
            this.setTopSocket(null);
        }
        if (this.getBottomPlug() != null){
            this.getBottomPlug().setTopSocket(null);
            this.setBottomPlug(null);
        }
    }

    /**
     * Method describing how a block will connect to another block.
     */
    @Override
    public void connect(ModelBlock block) {
        if ((block.getBottomPlug() == null) && (this.getTopSocketPos().getDistance(block.getBottomPlugPos()) < 50)){
            this.setTopSocket(block);
            block.setBottomPlug(this);    
        }
        if ((block.getTopSocket() == null) && (this.getBottomPlugPos().getDistance(block.getTopSocketPos()) < 50)){
            this.setBottomPlug(block);
            block.setTopSocket(this);    
        }
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
    public void setTopSocket(ModelBlock topSocket) {
        this.topSocket = topSocket;
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
    public void setBottomPlug(ModelBlock bottomPlug) {
        this.bottomPlug = bottomPlug;
    }

    /**
     * The type of a block is given by an int. 0 means a forward block, 1 means a left block, 2 means a right block.
     * @return the type of the given block.
     */
    public int getType() {
        return this.type;
    }

    /**
     * Sets the type of a block.
     * @param type: the type of the block (forward, left or right)
     */
    public void setType(int type) {
        if(type >-1 && type <4){
            this.type = type;
        }
        else{
            throw new IllegalArgumentException("Invalid model block type given");
        }
        
    }
}