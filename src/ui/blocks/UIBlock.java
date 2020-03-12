package ui.blocks;
import ui.*;
import java.awt.*;

import model.blocks.ModelBlock;
import utilities.*;

public class UIBlock extends UIElement {

    int width;
    int height;
    boolean highlighted;
    ModelBlock mBlock;



    // Constructor
    public UIBlock(ModelBlock mBlock) {
        super(mBlock.getPos());
        this.width = mBlock.getWidth();
        this.height = mBlock.getHeight();    
        this.mBlock = mBlock;
        this.highlighted = mBlock.isHighlighted();
    }

    /**
     * This function renders the block
     * 
     * @param {Graphics} g The Graphics object on which the block is rendered
     */
    public void render(Graphics g){
        switch (mBlock.getBlockType().getType()){
            case Blocktype.IF:
                if(highlighted)
                    g.setColor(Color.GREEN);
                else
                    g.setColor(Color.GRAY);
                g.fillRect(this.getPos().getX(),this.getPos().getY(), (this.width / 3), this.height);
                g.fillRect(this.getPos().getX() + (width/3),this.getPos().getY()+(height/4), (width/3), height);
                g.fillRect(this.getPos().getX() + (2*width/3),this.getPos().getY(),(width/3), (height/2));
                g.fillRect(this.getPos().getX() + (2*width/3),this.getPos().getY() + (3*height/4),(width/3), (height/4));
                g.setColor(Color.WHITE);
                g.drawString(mBlock.getBlockType().getTitle(), this.getPos().getX() + 10, this.getPos().getY() + (height/2));
                break;
            case Blocktype.WHILE:
                if(highlighted)
                    g.setColor(Color.GREEN);
                else
                    g.setColor(Color.GRAY);
                g.fillRect(this.getPos().getX(),this.getPos().getY(), (this.width / 3), this.height);
                g.fillRect(this.getPos().getX() + (width/3),this.getPos().getY()+(height/4), (width/3), height);
                g.fillRect(this.getPos().getX() + (2*width/3),this.getPos().getY(),(width/3), (height/2));
                g.fillRect(this.getPos().getX() + (2*width/3),this.getPos().getY() + (3*height/4),(width/3), (height/4));
                g.setColor(Color.WHITE);
                g.drawString(mBlock.getBlockType().getTitle(), this.getPos().getX() + 10, this.getPos().getY() + (height/2));
                break;
            case Blocktype.MOVEFORWARD:
                if(highlighted)
                    g.setColor(Color.GREEN);
                else
                    g.setColor(Color.GRAY);
                g.fillRect(this.getPos().getX(),this.getPos().getY(), (this.width / 3), this.height);
                g.fillRect(this.getPos().getX() + (width/3),this.getPos().getY()+(height/4), (width/3), height);
                g.fillRect(this.getPos().getX() + (2*width/3),this.getPos().getY(),(width/3), height);
                g.setColor(Color.WHITE);
                g.drawString(mBlock.getBlockType().getTitle(), this.getPos().getX() + 10, this.getPos().getY() + (height/2)); 
                break;
            case Blocktype.TURNLEFT:
                if(highlighted)
                    g.setColor(Color.GREEN);
                else
                    g.setColor(Color.GRAY);
                g.fillRect(this.getPos().getX(),this.getPos().getY(), (this.width / 3), this.height);
                g.fillRect(this.getPos().getX() + (width/3),this.getPos().getY()+(height/4), (width/3), height);
                g.fillRect(this.getPos().getX() + (2*width/3),this.getPos().getY(),(width/3), height);
                g.setColor(Color.WHITE);
                g.drawString(mBlock.getBlockType().getTitle(), this.getPos().getX() + 10, this.getPos().getY() + (height/2));
                break;
            case Blocktype.TURNRIGHT:
                if(highlighted)
                    g.setColor(Color.GREEN);
                else
                    g.setColor(Color.GRAY);
                g.fillRect(this.getPos().getX(),this.getPos().getY(), (this.width / 3), this.height);
                g.fillRect(this.getPos().getX() + (width/3),this.getPos().getY()+(height/4), (width/3), height);
                g.fillRect(this.getPos().getX() + (2*width/3),this.getPos().getY(),(width/3), height);
                g.setColor(Color.WHITE);
                g.drawString(mBlock.getBlockType().getTitle(), this.getPos().getX() + 10, this.getPos().getY() + (height/2));
                break;
            case Blocktype.WALLINFRONT:
                if(highlighted)
                    g.setColor(Color.GREEN);
                else
                    g.setColor(Color.GRAY);
                g.fillRect(this.getPos().getX() + (width / 4),this.getPos().getY(), width, height);
                g.fillRect(this.getPos().getX(),this.getPos().getY() + (height / 3), (width/4), height/3);
                g.setColor(Color.WHITE);
                g.drawString(mBlock.getBlockType().getTitle(), this.getPos().getX() + 10, this.getPos().getY() + (height/2));
                break;
            case Blocktype.NOT:
                if(highlighted)
                    g.setColor(Color.GREEN);
                else
                    g.setColor(Color.GRAY);
                g.fillRect(this.getPos().getX(),this.getPos().getY(), this.height, (this.width / 3));
                g.fillRect(this.getPos().getX() - (width/4),this.getPos().getY()+(height/3), height, (width/3));
                g.fillRect(this.getPos().getX(),this.getPos().getY() +(2*height/3),width, (height/3));
                g.setColor(Color.WHITE);
                g.drawString(mBlock.getBlockType().getTitle(), this.getPos().getX() + 10, this.getPos().getY() + (height/2));
                break;
            default:
                throw new IllegalArgumentException("Invalid block type in case statement");
            


        }

    }


    /**
     * public static final int IF = 0;

    public static final int WHILE = 1;
  
    public static final int MOVEFORWARD = 2;
    
    public static final int TURNLEFT = 3;
    
    public static final int TURNRIGHT = 4;
    
    public static final int WALLINFRONT = 5;
     */





    

    

    
    
    
}
