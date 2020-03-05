package ui.blocks;

import java.awt.Graphics;

import utilities.Location;

class UITurnLeftBlock extends UIMoveBlock {

    public UITurnLeftBlock(Location location) {
        super(location);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
        // TODO Auto-generated method stub

    }

    @Override
    protected String getTitle() {
        return "Turn Left";
    }

    
}