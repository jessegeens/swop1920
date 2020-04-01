package model.blocks;

import utilities.BlockType;
import utilities.ConnectionPoint;
import utilities.WindowLocation;

import java.util.ArrayList;

/**
 * Class representing the move forward, turn left and turn right blocks with one socket at the top and one plug at the bottom.
 */
public class ModelMoveBlock extends ModelBlock{

    private ModelBlock topSocket;
    private ModelBlock bottomPlug;
    
    public ModelMoveBlock(WindowLocation pos, BlockType type) {
        super(pos,type);
        this.setTopSocket(null);
        this.setBottomPlug(null);
        ArrayList<ConnectionPoint> connectionPoints = new ArrayList<>();
        connectionPoints.add(ConnectionPoint.BOTTOMPLUG);
        connectionPoints.add(ConnectionPoint.TOPSOCKET);
        super.setConnectionPoints(connectionPoints);
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