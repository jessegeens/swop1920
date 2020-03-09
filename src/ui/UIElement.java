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
	
	public UIElement(Location location) {
		pos = location;
	}

	public Location getPos(){
		return this.pos;
	}

	public void setPos(Location Pos) {
		this.pos = Pos;
	}

	
}