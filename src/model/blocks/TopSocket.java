package model.blocks;

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
    public Location getTopSocketPos();
}