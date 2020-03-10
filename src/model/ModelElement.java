package model;

import utilities.*;

/**
 * Abstract class representing an element of Blockr.
 */
public abstract class ModelElement{

    private Location pos;

    
    //Location of an element is the topmost left border (where left is more important than top)
    /**
     * Function that moves an element to the new position
     * @param newPos: the new position of the element.
     */
    public void move(Location newPos){
        this.setPos(newPos);
    }

    /**
     * Getter for the position of the element.
     * @return the position of the element.
     */
    public Location getPos() {
        return this.pos;
    }

    /**
     * Setter for the position of the element.
     * @param pos: the position of the element.
     */
    public void setPos(Location pos) {
        this.pos = pos;
    }

    /**
     * Function that declares if the position is a right position. TODO: klopt dit?
     */
    public boolean inBounds(Location pos) {
        return false;
    }

}