package ui;

import utilities.*;
import java.awt.*;

public abstract class UIElement {
	private Location pos;

	public abstract void render(Graphics g);
	
	public UIElement() {
		pos = new Location(0,0);
	}

	public Location getPos(){
		return this.pos;
	}

	public void setPos(Location Pos) {
		this.pos = Pos;
	}

	
}