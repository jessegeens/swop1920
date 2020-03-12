package model.blocks;

import utilities.Location;

public interface RightSocket {
    public boolean hasRightSocket();
    public void setRightSocket(ModelBlock blk);
    public ModelBlock getRightSocket();
    public Location getRightSocketPos();
}