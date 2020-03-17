package model;

import utilities.*;

/**
 * Abstract class representing an element of Blockr.
 */
public abstract class ModelElement{

    private WindowLocation pos;


    public ModelElement(WindowLocation pos){
        this.pos = pos;
    }

    /**
     * Getter for the position of the element.
     * 
     * @return the position of the element.
     */
    public WindowLocation getPos() {
        return this.pos;
    }

    /**
     * Setter for the position of the element.
     * 
     * @param pos: the position of the element.
     */
    public void setPos(WindowLocation pos) {
        this.pos = pos;
    }

    /**
     * Function that declares if the position is a right position. 
     */
    public abstract boolean inBounds(WindowLocation pos);

}