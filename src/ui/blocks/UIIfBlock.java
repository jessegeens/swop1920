package ui.blocks;

import java.awt.Graphics;

import utilities.Location;

public class UIIfBlock extends UIConditionalBlock {

    public UIIfBlock(Location location) {
        super(location);
    }

    @Override
    public void render(Graphics g) {
        super.render(g);

    }

    @Override
    protected String getTitle() {
       return "If Block";
    }

    
}