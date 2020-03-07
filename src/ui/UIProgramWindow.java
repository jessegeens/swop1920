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

	@Override
    public void render(Graphics g) {
        blocks.forEach((UIBlock block) -> block.render(g));
    }
    
}