package model.blocks;

import gameworldapi.ActionType;
import utilities.ConnectionPoint;
import utilities.ProgramLocation;

import java.util.ArrayList;

public class ModelFunctionCallBlock extends ModelBlock {

    private ModelBlock topSocket;
    private ModelBlock bottomPlug;
    //private final int id;
    private ModelFunctionDefinitionBlock definitionBlock;

    /**
     *
     * @param pos
     * @param
     *
     * @author Bert_DVL
     */
    public ModelFunctionCallBlock(ProgramLocation pos, ModelFunctionDefinitionBlock block) {
        super(pos);
        this.setTopSocket(null);
        this.setBottomPlug(null);

        //this.id = id;
        this.definitionBlock = block;

        ArrayList<ConnectionPoint> connectionPoints = new ArrayList<>();
        connectionPoints.add(ConnectionPoint.BOTTOM_PLUG);
        connectionPoints.add(ConnectionPoint.TOP_SOCKET);
        super.setConnectionPoints(connectionPoints);
    }

    public int getId(){
        return definitionBlock.getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ModelFunctionCallBlock clone() {
        return new ModelFunctionCallBlock(this.getPos(), this.definitionBlock);
    }

    /**
     * {@inheritDoc}
     */
    public void setBottomPlug(ModelBlock blk) {
        this.bottomPlug = blk;
    }

    /**
     * {@inheritDoc}
     */
    public ModelBlock getBottomPlug() {
        return this.bottomPlug;
    }


    /**
     * {@inheritDoc}
     */
    public void setTopSocket(ModelBlock blk) {
        this.topSocket = blk;
    }

    @Override
    public ModelBlock findNextBlock() {
        return definitionBlock.getCavityPlug();
    }

    /**
     * {@inheritDoc}
     */
    public ModelBlock getTopSocket() {
        return this.topSocket;
    }


}
