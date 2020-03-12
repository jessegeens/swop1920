package model;

import java.util.ArrayList;

import model.blocks.ModelBlock;
import utilities.Blocktype;
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
        ArrayList<ModelBlock> thisBlocks = this.getBlocks();
        ArrayList<ModelBlock> updated = new ArrayList<ModelBlock>();
        while (!(thisBlocks.isEmpty())){
            ModelBlock blk = thisBlocks.get(0);
            ArrayList<ModelBlock> connectedBlocks = this.getConnectedBlocks(blk);
            thisBlocks.removeAll(connectedBlocks);
            updated.add(blk);
            while(!(connectedBlocks.isEmpty())){
                forloop:
                for(ModelBlock blk1 : connectedBlocks){
                    for(ModelBlock upd : updated){
                        switch(blk1.getBlockType().getType()){
                            case Blocktype.IF:
                            case Blocktype.WHILE:
                                if (blk1.getTopSocket() == upd){
                                    blk1.setPos(upd.getPos().add(new Location(0, upd.getHeight())));
                                }
                                else if (blk1.getBottomPlug() == upd){
                                    blk1.setPos(upd.getPos().add(new Location(0, -blk1.getHeight())));
                                }
                                else if (blk1.getRightSocket() == upd){
                                    blk1.setPos(upd.getPos().add(new Location(-blk1.getWidth(),0)));
                                }
                                break;
                            case Blocktype.MOVEFORWARD:
                            case Blocktype.TURNLEFT:
                            case Blocktype.TURNRIGHT:
                                if (blk1.getTopSocket() == upd){
                                    blk1.setPos(upd.getPos().add(new Location(0, upd.getHeight())));
                                }
                                else if (blk1.getBottomPlug() == upd){
                                    blk1.setPos(upd.getPos().add(new Location(0, -blk1.getHeight())));
                                }
                                break;
                            case Blocktype.WALLINFRONT:
                                if (blk1.getLeftPlug() == upd){
                                    blk1.setPos(upd.getPos().add(new Location(upd.getWidth(),0)));
                                }
                                break;
                            case Blocktype.NOT:
                                if (blk1.getLeftPlug() == upd){
                                    blk1.setPos(upd.getPos().add(new Location(upd.getWidth(),0)));
                                }
                                else if (blk1.getRightSocket() == upd){
                                    blk1.setPos(upd.getPos().add(new Location(-blk1.getWidth(),0)));
                                }
                                break;
                        }
                        updated.add(blk1);
                        connectedBlocks.removeAll(updated);
                        break forloop;
                    }
                }
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

    public Boolean allBlocksConnected(){
        if (this.getBlocks().isEmpty()) return true;
        ArrayList<ModelBlock> connectedB = getConnectedBlocks(this.getBlocks().get(0));
        for (ModelBlock blk : getBlocks()){
            if (!(connectedB.contains(blk))) return false;
        }
        return true;
    }

    public void handleMouseEvent(int id, Location eventLocation, int clickCount){
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