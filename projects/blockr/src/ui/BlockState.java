package ui;

import model.blocks.ModelBlock;
import model.blocks.ModelWhileIfBlock;
import utilities.*;

import java.awt.*;
import java.util.ArrayList;

public class BlockState {

    private ProgramLocation blockLocation;
    private ArrayList<ConnectionPoint> connectionPoints;
    private boolean highlighted;
    private int cavitySize;
    private String title;

    /**
     * Constructor for the blockState
     * @param block the block for which the state is generated
     * @author Oberon Swings
     */
    public BlockState(ModelBlock block){
        this.blockLocation = block.getPos();
        this.highlighted = block.isHighlighted();
        this.connectionPoints = block.getConnectionPoints();
        this.title = block.getTitle();
        //TODO if block insteanceof functiondefinition
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
    ProgramLocation getBlockLocation() {
        return blockLocation;
    }

    /**
     *
     * @return the list of connection points
     * @author Oberon Swings
     */
    ArrayList<ConnectionPoint> getConnectionPoints() {
        return connectionPoints;
    }

    /**
     *
     * @return the size of the cavity of the block
     * @author Oberon Swings
     */
    int getCavitySize() {
        return cavitySize;
    }

    /**
     *
     * @return the title of the block
     * @author Oberon Swings
     */
    String getTitle() {
        return title;
    }

    Color getColor(){
        if (highlighted) return Color.YELLOW;
        if (connectionPoints.contains(ConnectionPoint.CAVITY_PLUG)) return Color.BLUE;
        if (connectionPoints.contains(ConnectionPoint.TOP_SOCKET)) return Color.ORANGE;
        if (connectionPoints.contains(ConnectionPoint.RIGHT_SOCKET)) return Color.GREEN;
        if (connectionPoints.contains(ConnectionPoint.LEFT_PLUG)) return Color.RED;
        return Color.GRAY;
    }
}
