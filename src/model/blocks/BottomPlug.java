package model.blocks;

import utilities.Location;

public interface BottomPlug {
    public boolean hasBottomPlug();
    public void setBottomPlug(ModelBlock blk);
    public ModelBlock getBottomPlug();
    public Location getBottomPlugPos();
}