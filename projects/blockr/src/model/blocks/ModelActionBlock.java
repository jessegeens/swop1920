package model.blocks;

import gameworldapi.*;
import utilities.*;

import java.util.ArrayList;

/**
 * Class representing the move forward, turn left and turn right blocks with one socket at the top and one plug at the bottom.
 */
public class ModelActionBlock extends ModelBlock{

    private ModelBlock topSocket;
    private ModelBlock bottomPlug;
    private ActionType action;
    
    public ModelActionBlock(ProgramLocation pos, ActionType action) {
        super(pos);
        this.setTopSocket(null);
        this.setBottomPlug(null);
        this.action = action;
        ArrayList<ConnectionPoint> connectionPoints = new ArrayList<>();
        connectionPoints.add(ConnectionPoint.BOTTOM_PLUG);
        connectionPoints.add(ConnectionPoint.TOP_SOCKET);
        super.setConnectionPoints(connectionPoints);
    }

    /**
     *
     * @return action {ActionType} block's action
     * @author Jesse Geens
     */
    public ActionType getAction(){
        return this.action;
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
    @Override
    public ModelActionBlock clone() {
        return new ModelActionBlock(this.getPos(), this.getAction());
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
    @Override
    public ModelBlock findNextBlock() {
        return getBottomPlug();
    }

    /**
     * {@inheritDoc}
     */
    public ModelBlock getTopSocket() {
        return this.topSocket;
    }
}