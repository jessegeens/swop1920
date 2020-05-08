package model.blocks;

import gameworldapi.ActionType;
import utilities.ConnectionPoint;
import utilities.ProgramLocation;

import java.util.ArrayList;

public class ModelFunctionCallBlock extends ModelBlock {

    private ModelBlock topSocket;
    private ModelBlock bottomPlug;
    private final int id;

    /**
     *
     * @param pos
     * @param id
     *
     * @author Bert_DVL
     */
    public ModelFunctionCallBlock(ProgramLocation pos, int id) {
        super(pos);
        this.setTopSocket(null);
        this.setBottomPlug(null);

        this.id = id;

        ArrayList<ConnectionPoint> connectionPoints = new ArrayList<>();
        connectionPoints.add(ConnectionPoint.BOTTOM_PLUG);
        connectionPoints.add(ConnectionPoint.TOP_SOCKET);
        super.setConnectionPoints(connectionPoints);
    }

    public int getId(){
        return this.id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ModelFunctionCallBlock clone() {
        return new ModelFunctionCallBlock(this.getPos(), this.getId());
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

    /**
     * {@inheritDoc}
     */
    public ModelBlock getTopSocket() {
        return this.topSocket;
    }


}
