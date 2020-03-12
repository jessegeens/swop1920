package model.blocks;

import utilities.*;

public interface TopSocket {
    public boolean hasTopSocket();
    public void setTopSocket(ModelBlock blk);
    public ModelBlock getTopSocket();
    public Location getTopSocketPos();
}