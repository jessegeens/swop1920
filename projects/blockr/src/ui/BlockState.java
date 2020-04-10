package ui;

import model.blocks.ModelBlock;
import model.blocks.ModelWhileIfBlock;
import utilities.*;

import java.util.ArrayList;

public class BlockState {

    private ProgramLocation blockLocation;
    private ArrayList<ConnectionPoint> connectionPoints;
    private boolean highlighted;
    private int cavitySize;

    /**
     * Constructor for the blockState
     * @param block the block for which the state is generated
     * @author Oberon Swings
     */
    public BlockState(ModelBlock block){
        this.blockLocation = block.getPos();
        this.highlighted = block.isHighlighted();
        this.connectionPoints = block.getConnectionPoints();
        if (block instanceof ModelWhileIfBlock){
            cavitySize = ((ModelWhileIfBlock)block).getCavityBlocks().size();
        }
        else cavitySize = 0;
    }

    /**
     *
     * @return the location of the block
     * @author Oberon Swings
     */
    public ProgramLocation getBlockLocation() {
        return blockLocation;
    }

    /**
     *
     * @return the list of connection points
     * @author Oberon Swings
     */
    public ArrayList<ConnectionPoint> getConnectionPoints() {
        return connectionPoints;
    }

    /**
     *
     * @return if the block is highlighted or not
     * @author Oberon Swings
     */
    public boolean isHighlighted() {
        return highlighted;
    }

    /**
     *
     * @return the size of the cavity of the block
     * @author Oberon Swings
     */
    public int getCavitySize() {
        return cavitySize;
    }
}
