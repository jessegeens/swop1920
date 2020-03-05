package ui.blocks;

import java.awt.Color;
import java.awt.Graphics;
import utilities.*;

abstract class UIConditionalBlock extends UIBlock {

    public UIConditionalBlock(Location location) {
        super(location);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(this.getPos().getX(),this.getPos().getY(), (this.width / 3), this.height);
        g.fillRect(this.getPos().getX() + (width/3),this.getPos().getY()+(height/4), (width/3), height);
        g.fillRect(this.getPos().getX() + (2*width/3),this.getPos().getY(),(width/3), (height/2));
        g.fillRect(this.getPos().getX() + (2*width/3),this.getPos().getY() + (3*height/4),(width/3), (height/4));
        g.setColor(Color.WHITE);
        g.drawString(getTitle(), this.getPos().getX() + 10, this.getPos().getY() + (height/2));
    }

    protected abstract String getTitle();


}