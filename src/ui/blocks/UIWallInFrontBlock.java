package ui.blocks;

import java.awt.*;

import utilities.Location;

public class UIWallInFrontBlock extends UIBlock {



    public UIWallInFrontBlock(Location location) {
        super(location);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(this.getPos().getX() + (width / 4),this.getPos().getY(), width, height);
        g.fillRect(this.getPos().getX(),this.getPos().getY() + (height / 3), (width/4), height/3);
        g.setColor(Color.WHITE);
        g.drawString("WallInFront", this.getPos().getX() + 10, this.getPos().getY() + (height/2));
    }

}