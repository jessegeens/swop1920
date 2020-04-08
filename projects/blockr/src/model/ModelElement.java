package model;

import utilities.*;

/**
 * Abstract class representing an element of Blockr.
 */
public abstract class ModelElement{

    private ProgramLocation pos;


    public ModelElement(ProgramLocation pos){
        this.pos = pos;
    }

    /**
     * Getter for the position of the element.
     * 
     * @return the position of the element.
     */
    public ProgramLocation getPos() {
        return this.pos;
    }

    /**
     * Setter for the position of the element.
     * 
     * @param pos: the position of the element.
     */
    public void setPos(ProgramLocation pos) {
        this.pos = pos;
    }

    /**
     * Function that declares if the position is a right position. 
     */
    public abstract boolean inBoundsOfElement(ProgramLocation pos);

}