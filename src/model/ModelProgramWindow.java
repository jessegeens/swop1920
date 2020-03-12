package model;

import java.util.ArrayList;

import model.blocks.ModelBlock;
import utilities.Location;


/**
 * Class representing the Program Window where the blocks will be set.
 */
class ModelProgramWindow extends ModelWindow{

    public ModelProgramWindow(int width, int height){
        super(width, height);
        super.setBlocks(new ArrayList<ModelBlock>());
    }

    public void updateLocationBlocks(){
        if (this.getBlocks().get(0) != null){
            for (ModelBlock blk : this.getBlocks().get(0).getConnections()){
                blk.setPos(new Location());
            }
        }
    }

    public void handleMouseEvent(int id, Location eventLocation, int clickCount){
        //MOUSE_PRESSED where you start holding the button down 501
        //MOUSE_RELEASED where you release the button      502  
        //MOUSE_CLICKED => press + release (comes after released + pressed) only comes if no dragging happended 500
        //MOUSE_DRAGGED => Holding down, gets triggerd after each small move 506
        //interesting to know: there is no difference detected between left and right button in the current handlemouseevent function

        //TODO cannot acces static MouseEvent.MOUSE_PRESSED etc

        //mouse pressed: update the currently selected block
        if(id == 501){
        }

        //MOUSE RELEASED, check if connections need to be made
        //currently selected block is null
        else if(id == 502){
            

        }

        //MOUSE MOVED, if there is a currently held block, move it
        else if(id == 506){
            
            
            
            //update pos of activelement
        }

    }

   
}