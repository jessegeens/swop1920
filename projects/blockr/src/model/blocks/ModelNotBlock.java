package model.blocks;

import utilities.*;

import java.util.ArrayList;

/**
 * Class representing the not block with one plug to their left and one socket to their right.
 */
public class ModelNotBlock extends ModelBlock{
    private ModelBlock rightSocket;
    private ModelBlock leftPlug;

    public ModelNotBlock(ProgramLocation pos, BlockType type){
        super(pos,type);
        this.setRightSocket(null);
        this.setLeftPlug(null);
        ArrayList<ConnectionPoint> connectionPoints = new ArrayList<>();
        connectionPoints.add(ConnectionPoint.LEFTPLUG);
        connectionPoints.add(ConnectionPoint.RIGHTSOCKET);
        super.setConnectionPoints(connectionPoints);
    }
    
    /**
     * {@inheritDoc}
     */
    public ModelBlock getRightSocket() {
        return this.rightSocket;
    }

    /**
     * {@inheritDoc}
     */
    public void setRightSocket(ModelBlock rightSocket) {
        this.rightSocket = rightSocket;
    }

    /**
     * {@inheritDoc}
     */
    public ModelBlock getLeftPlug() {
        return this.leftPlug;
    }

    /**
     * {@inheritDoc}
     */
    public void setLeftPlug(ModelBlock leftPlug) {
        this.leftPlug = leftPlug;
    }



}