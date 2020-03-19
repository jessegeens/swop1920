package model.blocks.plugs;

import model.blocks.*;
import utilities.*;

public interface TopSocket {
    /**
     * 
     * @return whether the block has a top socket
     */
    public boolean hasTopSocket();

    /**
     * 
     * @param blk the block to attach to the top socket
     */
    public void setTopSocket(ModelBlock blk);

    /**
     * 
     * @return the block attached to the top socket
     */
    public ModelBlock getTopSocket();

    /**
     * 
     * @return the position of the top socket
     */
    public WindowLocation getTopSocketPos();

    /**
     * sets the position of the block so that the top socket will be at pos after execution
     * @param pos the location at which the top socket should be after execution
     */
    public void setTopSocketPos(WindowLocation pos);
}