package model.blocks;

import utilities.Location;

public interface LeftPlug {
    public boolean hasLeftPlug();
    public void setLeftPlug(ModelBlock blk);
    public ModelBlock getLeftPlug();
    public Location getLeftPlugPos();
}