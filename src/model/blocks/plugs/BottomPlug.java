package model.blocks.plugs;

import model.blocks.*;
import utilities.WindowLocation;

public interface BottomPlug {
    public boolean hasBottomPlug();
    public void setBottomPlug(ModelBlock blk);
    public ModelBlock getBottomPlug();
    public WindowLocation getBottomPlugPos();
}