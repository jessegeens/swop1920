package model;

import utilities.*;

public abstract class ModelElement{

    private Location pos;


    //Location of an element is the topmost left border (where left is more important than top)
    public void move(Location newPos){
        this.setPos(newPos);
    }

    public Location getPos() {
        return this.pos;
    }

    public void setPos(Location pos) {
        this.pos = pos;
    }

    public boolean inBounds(Location pos) {
        return false;
    }

}