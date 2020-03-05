package ui.blocks;

import java.awt.Graphics;

import utilities.Location;

public class UIWhileBlock extends UIConditionalBlock {

    public UIWhileBlock(Location location) {
        super(location);
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
    }

    @Override
    protected String getTitle() {
        return "While Block";
    }

}