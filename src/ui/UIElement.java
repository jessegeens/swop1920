package ui;

import utilities.*;
import java.awt.*;

/**
 * The UIElement abstract class is an abstraction of a UI Element.
 * This class provides the minimum functions that every UI Element must have.
 * A UI Element is defined as any Element that needs to be rendered,
 *  including Blocks, the Grid, the ProgramArea, etc 
 */
public abstract class UIElement {
	private Location pos;

	public abstract void render(Graphics g);
	
	// Constructor
	public UIElement(Location location) {
		if(location == null)
			throw new IllegalArgumentException("Invalid location given to UIElement init");
		pos = location;
	}

	/**
	 * The position of a UI Element is defined as the 
	 *  top left corner of the element, where we first look
	 *  at the highest y-coordinate and then the leftmost
	 *  x-coordinate
	 * 
	 * @return {Location} the position of the element
	 * 	
	 */
	public Location getPos(){
		return this.pos;
	}

	/**
	 * Sets the position of the UI Element
	 * 
	 * The position of a UI Element is defined as the 
	 *  top left corner of the element, where we first look
	 *  at the highest y-coordinate and then the leftmost
	 *  x-coordinate
	 * 
	 * @param Pos the new position of the UI Element
	 */
	public void setPos(Location Pos) {
		this.pos = Pos;
	}

	
}