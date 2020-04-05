package ui;

import model.blocks.ModelBlock;
import model.blocks.ModelWhileIfBlock;
import utilities.*;

public class BlockState {

    private Location blockLocation;
    private BlockType blockType;
    private boolean highlighted;
    private int cavitySize;

    /**
     * Constructor for the blockState
     * @param block the block for which the state is generated
     * @author Oberon Swings
     */
    public BlockState(ModelBlock block){
        this.blockLocation = block.getPos();
        this.blockType = block.getBlockType();
        this.highlighted = block.isHighlighted();
        if (blockType == BlockType.IF || blockType == BlockType.WHILE){
            cavitySize = ((ModelWhileIfBlock)block).getCavityBlocks().size();
        }
        else cavitySize = 0;
    }

    /**
     *
     * @return the location of the block
     * @author Oberon Swings
     */
    public Location getBlockLocation() {
        return blockLocation;
    }

    /**
     *
     * @return the type of the block
     * @author Oberon Swings
     */
    public BlockType getBlockType() {
        return blockType;
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
