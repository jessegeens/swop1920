package model.blocks.plugs;

import model.blocks.*;
import utilities.WindowLocation;

public interface RightSocket {
    /**
     * 
     * @return whether the block has a right socket
     * @author Oberon Swings
     */
    public boolean hasRightSocket();

    /**
     * 
     * @param blk the block to attach to the right socket
     * @author Oberon Swings
     */
    public void setRightSocket(ModelBlock blk);

    /**
     * 
     * @return the block attached to the right socket
     * @author Oberon Swings
     */
    public ModelBlock getRightSocket();

    /**
     * 
     * @return the position of the right socket
     * @author Oberon Swings
     */
    public WindowLocation getRightSocketPos();

    /**
     * Sets the position of the block so that the RightSocketPostion will be the given pos
     * @param pos the location at which the right socket should be after execution.
     * @author Oberon Swings
     */
    public void setRightSocketPos(WindowLocation pos);
}