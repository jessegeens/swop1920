package model;

import java.util.ArrayList;

import utilities.Blocktype;
import utilities.Location;
import model.blocks.*;


/**
 * Class representing the Program Window where the blocks will be set.
 */
public class ModelProgramWindow extends ModelWindow{

    private ArrayList<ModelBlock> blocks;

    public ModelProgramWindow(int width, int height){
        super(width, height);
        this.setBlocks(new ArrayList<ModelBlock>());
    }

    //TODO Oberon gaat dit mooier schrijven
    public void updateLocationBlocks(){
        ArrayList<ModelBlock> thisBlocks = this.getPABlocks();
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
                                if (((ModelWhileIfBlock)blk1).getTopSocket() == upd){
                                    blk1.setPos(upd.getPos().add(new Location(0, upd.getHeight())));
                                }
                                else if (((ModelWhileIfBlock)blk1).getBottomPlug() == upd){
                                    blk1.setPos(upd.getPos().add(new Location(0, -blk1.getHeight())));
                                }
                                else if (((ModelWhileIfBlock)blk1).getRightSocket() == upd){
                                    blk1.setPos(upd.getPos().add(new Location(-blk1.getWidth(),0)));
                                }
                                break;
                            case Blocktype.MOVEFORWARD:
                            case Blocktype.TURNLEFT:
                            case Blocktype.TURNRIGHT:
                                if (((ModelMoveBlock)blk1).getTopSocket() == upd){
                                    blk1.setPos(upd.getPos().add(new Location(0, upd.getHeight())));
                                }
                                else if (((ModelMoveBlock)blk1).getBottomPlug() == upd){
                                    blk1.setPos(upd.getPos().add(new Location(0, -blk1.getHeight())));
                                }
                                break;
                            case Blocktype.WALLINFRONT:
                                if (((ModelWallInFrontBlock)blk1).getLeftPlug() == upd){
                                    blk1.setPos(upd.getPos().add(new Location(upd.getWidth(),0)));
                                }
                                break;
                            case Blocktype.NOT:
                                if (((ModelNotBlock)blk1).getLeftPlug() == upd){
                                    blk1.setPos(upd.getPos().add(new Location(upd.getWidth(),0)));
                                }
                                else if (((ModelNotBlock)blk1).getRightSocket() == upd){
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
        if (this.getPABlocks().isEmpty()) return true;
        ArrayList<ModelBlock> connectedB = getConnectedBlocks(this.getPABlocks().get(0));
        for (ModelBlock blk : getPABlocks()){
            if (!(connectedB.contains(blk))) return false;
            if (!(blk.equals(this.getFinishBlock())||(blk.equals(this.getStartBlock())))){
                return blk.isFullyConnected();
            }
            else if(blk.equals(this.getFinishBlock())) {
                if(blk.hasTopSocket() && ((TopSocket)blk).getTopSocket() == null) return false;
                if(blk.hasRightSocket() && ((RightSocket)blk).getRightSocket() == null) return false;
                if(blk.hasLeftPlug() && ((LeftPlug)blk).getLeftPlug() == null) return false;
            }
            else if(blk.equals(this.getStartBlock())){
                if(blk.hasBottomPlug() && ((BottomPlug)blk).getBottomPlug() == null) return false;
                if(blk.hasRightSocket() && ((RightSocket)blk).getRightSocket() == null) return false;
                if(blk.hasLeftPlug() && ((LeftPlug)blk).getLeftPlug() == null) return false;
            }
        }
        return true;
    }

    /**
     * 
     * @return the starting block of the program if all blocks are connected.
     */
    public ModelBlock getStartBlock(){
        for(ModelBlock blk : getPABlocks()){
            if(blk.hasTopSocket() && ((TopSocket)blk).getTopSocket() == null) return blk;
        }
        return null;
    }

    /**
     * 
     * @return the finishing block of the program if all blocks are connected.
     */
    public ModelBlock getFinishBlock(){
        for(ModelBlock blk : getPABlocks()){
            if(blk.hasBottomPlug() && ((BottomPlug)blk).getBottomPlug() == null) return blk;
        }
        return null;
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
    public ArrayList<ModelBlock> getPABlocks() {
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
        System.out.println("here");
        if(toBeAdded != null){
            this.blocks.add(toBeAdded);
            System.out.println("adds");
        }
        System.out.println("notadds");
        
    }

    /**
     * 
     * @param block The block for which the closest neighbour needs to be found
     * @return The closest neighbour of block
     * return null if no closest block
     */
    public ModelBlock findClosestBlock(ModelBlock block){

        System.out.println("list length");
        System.out.println(this.getPABlocks().size());

        ModelBlock closest_ = this.getPABlocks().get(0);
        ModelBlock closest = null;



        for(int i = 0; i < this.getPABlocks().size(); i++){
            ModelBlock current = this.getPABlocks().get(i);
            System.out.println("INDEX IN LIST");
            System.out.println(i);
            System.out.println("CURRENT BLOCK ID");
            System.out.println(this.getPABlocks().get(i).getBlockType().getType());
            
            if(closest == null){
                if(current.getPos().getDistance(block.getPos()) > 0 ){
                    if(current != block){
                    closest = current;
                    }
                }

            }
            else if(current.getPos().getDistance(block.getPos()) < closest.getPos().getDistance(block.getPos()) ){
                if(current != block){
                    closest = current;
                    System.out.println("CLOSEST CANDIDATE FOUND NO ID ISSUE");

                }
                System.out.println("CLOSEST CANDIDATE FOUND");
                
            }
        }
        return closest;
        
                  
    }

    public ModelBlock handleMouseDown(Location eventLocation){
        //has to be done in reverse order due to rendering (ask Bert if unclear)

        
        for(int i = this.getPABlocks().size() - 1; i >= 0; i--){
            if(this.getPABlocks().get(i).inBounds(eventLocation)){
                ModelBlock toBeReturned = this.getPABlocks().get(i);
                this.removeBlock(toBeReturned);
                return toBeReturned;
            }
        }
        return null;
    }

    public void handleMouseUp(Location eveLocation, ModelBlock activeB){
        this.addBlock(activeB);
        ModelBlock closest = this.findClosestBlock(activeB);
        if (closest != null){
            System.out.println("CLOSEST IS NOT NULL");
            System.out.println(closest.getBlockType().getType());
            System.out.println("list length");
            
            activeB.connect(closest);
        } 
        else{
            System.out.println("CLOSEST IS NULL");
        }
    }

   
}