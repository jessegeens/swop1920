package model.blocks;

import utilities.*;

interface TopSocket {
    public boolean hasTopSocket();
    public void setTopSocket(ModelBlock blk);
    public ModelBlock getTopSocket();
    public void updateTopSocketPos();
    public Location getTopSocketPos();
}

interface BottomPlug {
    public boolean hasBottomPlug();
    public void setBottomPlug(ModelBlock blk);
    public ModelBlock getBottomPlug();
    public void updateBottomPlugPos();
    public Location getBottomPlugPos();
}

interface RightSocket {
    public boolean hasRightSocket();
    public void setRightSocket(ModelBlock blk);
    public ModelBlock getRightSocket();
    public void updateRightSocketPos();
    public Location getRightSocketPos();
}

interface LeftPlug {
    public boolean hasLeftPlug();
    public void setLeftPlug(ModelBlock blk);
    public ModelBlock getLeftPlug();
    public void updateLeftPlugPos();
    public Location getLeftPlugPos();
}