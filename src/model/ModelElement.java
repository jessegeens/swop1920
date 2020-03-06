package model;

import utilities.*;

public abstract class ModelElement{

    private Location pos;



    public void move(Location newPos){
        this.setPos(newPos);
    }

    public Location getPos() {
        return this.pos;
    }

    public void setPos(Location pos) {
        this.pos = pos;
    }

}