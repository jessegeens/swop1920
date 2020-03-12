package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import model.blocks.ModelBlock;
import utilities.Location;


/**
 * Class representing the Program Window where the blocks will be set.
 */
class ModelProgramWindow extends ModelWindow{

    private ArrayList<ModelBlock> blocks;

    public ModelProgramWindow(int width, int height){
        super(width, height);
        this.setBlocks(new ArrayList<ModelBlock>());
    }

    public void updateLocationBlocks(){
        if (this.getBlocks().get(0) != null){
            for (ModelBlock blk : this.getBlocks().get(0).getConnections()){
                //blk.setPos(new Location());
            }
        }
    }

    public ArrayList<ModelBlock> getConnectedBlocks(ModelBlock blk){
        ArrayList<ModelBlock> connectedBlocks = new ArrayList<ModelBlock>();
        connectedBlocks.add(blk);
        ArrayList<ModelBlock> nextConnection = blk.getConnections();
        while(!(nextConnection.isEmpty())){
            ModelBlock blk1 = nextConnection.get(0);
            connectedBlocks.add(blk1);
            nextConnection.addAll(blk1.getConnections());
            nextConnection.removeAll(connectedBlocks);
        }
        return connectedBlocks;
    }

    public void handleMouseEvent(int id, Location eventLocation, int clickCount){}
        //MOUSE_PRESSED where you start holding the button down 501
        //MOUSE_RELEASED where you release the button      502  
        //MOUSE_CLICKED => press + release (comes after released + pressed) only comes if no dragging happended 500
        //MOUSE_DRAGGED => Holding down, gets triggerd after each small move 506
        //interesting to know: there is no difference detected between left and right button in the current handlemouseevent function
    }
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

    public void removeBlock(ModelBlock toBeRemoved){
        this.blocks.remove(toBeRemoved);
    }

    public void addBlock(ModelBlock toBeAdded){
        this.blocks.add(toBeAdded);
    }

    public ModelBlock handleMouseDown(Location eventLocation){
        //has to be done in reverse order due to rendering (ask Bert if unclear)
        for(int i = this.getBlocks().size() - 1; i >= 0; i--){
            if(this.getBlocks().get(i).inBounds(eventLocation)){
                ModelBlock toBeReturned = this.getBlocks().get(i);
                this.removeBlock(toBeReturned);
                return toBeReturned;
            }
        }




        return null;
    }

   
}