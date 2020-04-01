package model.blocks;

import utilities.*;

import java.util.ArrayList;

/**
 * Class representing the not block with one plug to their left and one socket to their right.
 */
public class ModelNotBlock extends ModelBlock{
    private ModelBlock rightSocket;
    private ModelBlock leftPlug;

    public ModelNotBlock(WindowLocation pos, BlockType type){
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
     * @author Oberon Swings
     */
    @Override
    public void disconnect() {
        if (this.getRightSocket() != null){
            this.getRightSocket().setLeftPlug(null);
            this.setRightSocket(null);
        }
        if (this.getLeftPlug() != null){
            this.getLeftPlug().setRightSocket(null);
            this.setLeftPlug(null);
        }
    }

    /**
     * {@inheritDoc}
     * @author Oberon Swings
     */
    @Override
    public void connect(ModelBlock block) {
        if (block.hasLeftPlug() && (this.getRightSocketPos().getDistance(block.getLeftPlugPos()) < ModelBlock.PLUGSIZE * 1.5)
        && block.getLeftPlug() == null){
            System.out.println("NOT CONNECTS LEFT");
            this.setRightSocket(block);
            block.setLeftPlug(this);
            this.setRightSocketPos(block.getLeftPlugPos());
        }
        if ((block.hasRightSocket()) && (this.getLeftPlugPos().getDistance(block.getRightSocketPos()) < ModelBlock.PLUGSIZE * 1.5)
        && block.getRightSocket() == null){
            System.out.println("NOT CONNECTS RIGHT");
            this.setLeftPlug(block);
            block.setRightSocket(this);
            this.setLeftPlugPos(block.getRightSocketPos());
        }
        
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