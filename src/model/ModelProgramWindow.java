package model;
import utilities.Location;


/**
 * Class representing the Program Window where the blocks will be set.
 */
class ModelProgramWindow extends ModelWindow{


    private int width;
    private int height;

    
    public ModelProgramWindow(int width, int height){
        super(width, height);
    }

    public void handleMouseEvent(int id, Location eventLocation, int clickCount){
    }

   
}