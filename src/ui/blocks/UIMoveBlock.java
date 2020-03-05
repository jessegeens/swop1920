package ui.blocks;

import java.awt.Graphics;

public class UIMoveBlock extends UIBlock {

    @Override
    public void render(Graphics g) {
        g.drawRect(200,200,100,100);
        g.setClip(240,200,20,20);
        g.clipRect(200,200,100,100);

    }

    public UIMoveBlock() {
        super();
    }

}