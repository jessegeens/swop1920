package model.blocks.plugs;

import model.blocks.*;
import utilities.*;

public interface TopSocket {
    /**
     * 
     * @return whether the block has a top socket
     * @author Oberon Swings
     */
    public boolean hasTopSocket();

    /**
     * 
     * @param blk the block to attach to the top socket
     * @author Oberon Swings
     */
    public void setTopSocket(ModelBlock blk);

    /**
     * 
     * @return the block attached to the top socket
     * @author Oberon Swings
     */
    public ModelBlock getTopSocket();

    /**
     * 
     * @return the position of the top socket
     * @author Oberon Swings
     */
    public WindowLocation getTopSocketPos();

    /**
     * sets the position of the block so that the top socket will be at pos after execution
     * @param pos the location at which the top socket should be after execution
     * @author Oberon Swings
     */
    public void setTopSocketPos(WindowLocation pos);
}