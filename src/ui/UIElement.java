package ui;

import utilities.*;

public abstract class UIElement {
	private Location pos;

    public abstract void render();

	public Location getPos(){
		return this.pos;
	}

	public void setPos(Location Pos) {
		this.pos = Pos;
	}

}