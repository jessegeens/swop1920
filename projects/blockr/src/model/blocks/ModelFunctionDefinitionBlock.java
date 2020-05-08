package model.blocks;

import ui.UIBlock;
import utilities.ConnectionPoint;
import utilities.ProgramLocation;

import java.util.ArrayList;

public class ModelFunctionDefinitionBlock extends ModelBlock {

    private ModelBlock cavitySocket;
    private ModelBlock cavityPlug;
    private final int identifier;

    public ModelFunctionDefinitionBlock(ProgramLocation pos, int id){
        super(pos);
        this.setTopSocket(null);
        this.setBottomPlug(null);
        this.setRightSocket(null);

        ArrayList<ConnectionPoint> connectionPoints = new ArrayList<>();
        connectionPoints.add(ConnectionPoint.CAVITY_PLUG);
        connectionPoints.add(ConnectionPoint.CAVITY_SOCKET);
        super.setConnectionPoints(connectionPoints);

        this.identifier = id;
    }

    public int getId(){
        return this.identifier;
    }


    @Override
    public ModelFunctionDefinitionBlock clone(){
        return new ModelFunctionDefinitionBlock(super.getPos(), this.getId());
    }

    /**
     * Getter for the height of the cavity of the while and if block.
     * @return the height of the block
     * @author Oberon Swings
     */
    public int getCavityHeight() {
        if(!(getCavityBlocks().size() == 0)){
            return getCavityBlocks().size() * UIBlock.STD_HEIGHT;
        }
        else{
            return 0;
        }
    }

    /**
     * Getter for a list of blocks this block has in its cavity.
     * @return the list of blocks.
     * @author Oberon Swings
     */
    public ArrayList<ModelBlock> getCavityBlocks() {
        ArrayList<ModelBlock> cav = new ArrayList<ModelBlock>();
        ModelBlock blk = this.getCavityPlug();
        while(!(blk.equals(this) || blk == null)){
            cav.add(blk);
            if (blk instanceof ModelWhileIfBlock) cav.addAll(((ModelWhileIfBlock) blk).getCavityBlocks());
            if (blk == null) return null;
            if(blk.hasBottomPlug()){
                blk = blk.getBottomPlug();
            }
        }
        return cav;
    }

    /**
     *
     * @return the cavity of the socket.
     */
    public ModelBlock getCavitySocket() {
        return this.cavitySocket;
    }

    /**
     *
     * @param cavitySocket sets the cavity of the socket.
     */
    public void setCavitySocket(ModelBlock cavitySocket) {
        this.cavitySocket = cavitySocket;
    }

    /**
     *
     * @return the cavity of the plug.
     */
    public ModelBlock getCavityPlug() {
        return this.cavityPlug;
    }

    /**
     *
     * @param cavityPlug sets the cavity of the plug.
     */
    public void setCavityPlug(ModelBlock cavityPlug) {
        this.cavityPlug = cavityPlug;
    }

    /**
     *
     * @return the position of the cavity socket
     * @author Oberon Swings
     */
    public ProgramLocation getCavitySocketPos() {
        return this.getPos().add(2* UIBlock.STD_WIDTH/3, this.getHeight() - UIBlock.STD_HEIGHT/3);
    }

    /**
     *
     * @return the position of the cavity plug
     * @author Oberon Swings
     */
    public ProgramLocation getCavityPlugPos() {
        return this.getPos().add(2*UIBlock.STD_WIDTH/3, 2*UIBlock.STD_HEIGHT/3);
    }

    /**
     * Calculates the distance between cavityPlug and TopSocket
     * @param top the block with the topSocket
     * @return the distance form the cavityPlug to the topSocket of the top block
     */
    public int distanceCavityPlug(ModelBlock top){
        return this.getCavityPlugPos().getDistance(top.getTopSocketPos());
    }

    /**
     * Calculates the distance between cavitySocket and bottomPlug
     * @param bottom the block with the bottomPlug
     * @return the distance form the cavitySocket to the bottomPlug of the bottom block
     */
    public int distanceCavitySocket(ModelBlock bottom){
        return this.getCavitySocketPos().getDistance(bottom.getBottomPlugPos());
    }

}
