package model.blocks.plugs;

import model.blocks.*;
import utilities.WindowLocation;

public interface LeftPlug {
    /**
     * 
     * @return whether the block has a left plug
     */
    public boolean hasLeftPlug();

    /**
     * 
     * @param blk the block to attach to the left plug
     */
    public void setLeftPlug(ModelBlock blk);

    /**
     * 
     * @return the block attached to the right socket
     */
    public ModelBlock getLeftPlug();

    /**
     * 
     * @return the position of the right socket
     */
    public WindowLocation getLeftPlugPos();

    /**
     * sets the position of the block according to the position that is given and the fact that this given position needs to be the new position of the left plug
     * @param pos the position at which the left plug has to be after the block is moved.
     */
    public void setLeftPlugPos(WindowLocation pos);
}