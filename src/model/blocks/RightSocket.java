package model.blocks;

import utilities.Location;

public interface RightSocket {
    /**
     * 
     * @return whether the block has a right socket
     */
    public boolean hasRightSocket();

    /**
     * 
     * @param {ModelBlock} blk the block to attach to the right socket
     */
    public void setRightSocket(ModelBlock blk);

    /**
     * 
     * @return the block attached to the right socket
     */
    public ModelBlock getRightSocket();

    /**
     * 
     * @return the position of the right socket
     */
    public Location getRightSocketPos();
}