package model;

import model.blocks.*;
import java.util.ArrayList;

/**
 * Abstract class representing a window in the program. The palette, program window extend this class. 
 */
abstract class ModelWindow{

    private int width; // Should be at least 300
    private int height; // Should be at least 600

    

    public ModelWindow(int width, int height){
        this.setWidth(width);
        this.setHeight(height);
    }

    public static ArrayList<ModelBlock> removeDuplicates(ArrayList<ModelBlock> list) 
    { 
        ArrayList<ModelBlock> newL = new ArrayList<ModelBlock>(); 
        for (ModelBlock blk : list) { 
            if (!newL.contains(blk)) { 
                newL.add(blk); 
            } 
        }
        return newL; 
    } 
    


   /**
     * 
     * @return the width of the palette.
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * 
     * @param width the width to be set to the palette.
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * 
     * @return the height of the palette.
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * 
     * @param height the height to be set to the palette.
     */
    public void setHeight(int height) {
        this.height = height;
    }


}