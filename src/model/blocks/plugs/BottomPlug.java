package model.blocks.plugs;

import model.blocks.*;
import utilities.WindowLocation;

public interface BottomPlug {
    /**
     *
     * @return the true if this interface is implemented
     * @author Oberon Swings
     */
    public boolean hasBottomPlug();

    /**
     * set the block which is connected to the bottomplug to blk
     * @param blk the block to which this block is connecting
     * @author Oberon Swings
     */
    public void setBottomPlug(ModelBlock blk);

    /**
     *
     * @return the block that is connected to the bottom plug.
     * @author Oberon Swings
     */
    public ModelBlock getBottomPlug();

    /**
     *
     * @return the location at which the bottomplug is at.
     * @author Oberon Swings
     */
    public WindowLocation getBottomPlugPos();

    /**
     * sets the position of the block to make sure the bottom plug position will be the given pos.
     * @param pos the location at which the bottom plug should be after execution.
     * @author Oberon Swings
     */
    public void setBottomPlugPos(WindowLocation pos);
}