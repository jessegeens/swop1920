package ui.blocks;

import java.awt.*;

import utilities.Location;

public class UINotBlock extends UIBlock {

    public UINotBlock(Location location) {
        super(location);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(this.getPos().getX(),this.getPos().getY(), this.height, (this.width / 3));
        g.fillRect(this.getPos().getX() - (width/4),this.getPos().getY()+(height/3), height, (width/3));
        g.fillRect(this.getPos().getX(),this.getPos().getY() +(2*height/3),width, (height/3));
        g.setColor(Color.WHITE);
        g.drawString(getTitle(), this.getPos().getX() + 10, this.getPos().getY() + (height/2));
        
    }

    private String getTitle() {
        return "NotBlock";
    }

    
}