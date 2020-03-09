package ui;

import java.awt.Graphics;
import java.util.ArrayList;
import ui.blocks.*;


public class UIProgramWindow implements UIWindow {

    ArrayList<UIBlock> blocks = new ArrayList<UIBlock>();
    Graphics g;

    public UIProgramWindow(Graphics g) {
        this.g = g;
	}

    /**
     * This function traverses the blocks in the Program Window and renders them.
     * 
     * @param g Graphics object on which the rendering needs to happen
     */
	@Override
    public void render(Graphics g) {
        blocks.forEach((UIBlock block) -> block.render(g));
    }
    
}