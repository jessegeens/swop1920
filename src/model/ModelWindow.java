package model;

import model.blocks.*;
import java.util.ArrayList;

/**
 * Abstract class representing a window in the program. The palette, program window extend this class. 
 */
abstract class ModelWindow{

    private ArrayList<ModelBlock> blocks;

    /**
     * 
     * @return a list of blocks in the window.
     */
    public ArrayList<ModelBlock> getBlocks() {
        return this.blocks;
    }

    /**
     * 
     * @param blocks list of blocks to be set in a window.
     */
    public void setBlocks(ArrayList<ModelBlock> blocks) {
        this.blocks = blocks;
    }


}