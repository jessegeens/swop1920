package model;

import utilities.*;
import java.util.ArrayList;

/**
 * Class representing a two-dimensional grid for Blockr. The grid contains walls, a robot and a goal cell.
 */
public class ModelGrid extends ModelWindow {
    private ProgramState gridState;

    // Constructor
    public ModelGrid(int width, int height, ProgramState gridState){
        super(width,height);     
        this.gridState = gridState;
    }

    /**
     * @return the gridState
     */
    public ProgramState getGridState() {
        return gridState;
    }

    /**
     * @param gridState the gridState to set
     */
    public void setGridState(ProgramState gridState) {
        this.gridState = gridState;
    }
}