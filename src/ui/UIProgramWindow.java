package ui;

import java.awt.Graphics;
import java.util.ArrayList;
import ui.blocks.*;
import utilities.Location;


public class UIProgramWindow implements UIWindow {

    ArrayList<UIBlock> blocks = new ArrayList<UIBlock>();
    Graphics g;
    private int xPosition;
    private int yPosition;
    private int width;
    private int height;

    // Constructor
    public UIProgramWindow(Location position, int width, int height, Graphics g) {
        this.g = g;
        this.xPosition = position.getX();
        this.yPosition = position.getY();
        this.width = width;
        this.height = height;
	}

    /**
     * This function traverses the blocks in the Program Window and renders them.
     * 
     * @param {Graphics} g The Graphics object on which the rendering needs to happen
     */
	@Override
    public void render(Graphics g) {

        blocks.forEach((UIBlock block) -> {
            if(block.getPos().getX() > xPosition && block.getPos().getX() < xPosition + width && block.getPos().getY() < height)
                block.render(g);
            else
                throw new IllegalStateException("Block at illegal position");});
    }
    
}