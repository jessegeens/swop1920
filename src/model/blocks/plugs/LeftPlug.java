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
}