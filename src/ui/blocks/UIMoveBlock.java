package ui.blocks;

import java.awt.Color;
import java.awt.Graphics;
import utilities.*;

public abstract class UIMoveBlock extends UIBlock {

    final int width = 120;
    final int height = 120;

    @Override
    public void render(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(this.getPos().getX(),this.getPos().getY(), (this.width / 3), this.height);
        g.fillRect(this.getPos().getX() + (width/3),this.getPos().getY()+(height/4), (width/3), height);
        g.fillRect(this.getPos().getX() + (2*width/3),this.getPos().getY(),(width/3), height);
        g.setColor(Color.WHITE);
        g.drawString(getTitle(), this.getPos().getX() + 10, this.getPos().getY() + (height/2));
        /*
        System.out.println("x: " + this.getPos().getX());
        System.out.println("y: " + this.getPos().getY());
        System.out.println("w: " + (1/3*this.width));
        System.out.println("h: " + this.height);
        */
    }

    protected abstract String getTitle();

    public UIMoveBlock(Location location) {
        super(location);
    }

}