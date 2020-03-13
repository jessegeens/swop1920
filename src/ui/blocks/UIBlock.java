package ui.blocks;
import ui.*;
import java.awt.*;

import model.blocks.ModelBlock;
import utilities.*;

/**
 * Class representing the different blocks and their visualisation.
 */
public class UIBlock extends UIElement {

    int width;
    int height;
    int socketSize;
    boolean highlighted;
    Blocktype type;





    // Constructor
    public UIBlock(ModelBlock mBlock) {
        super(mBlock.getPos());
        this.width = mBlock.getWidth();
        this.height = mBlock.getHeight(); 
        this.socketSize = ModelBlock.PLUGSIZE;   
        this.highlighted = mBlock.isHighlighted();
        this.type = mBlock.getBlockType();
    }

    public Color getBlockColor(){
        if(this.highlighted) {
            return Color.GREEN;
        }
        else {
            return Color.GRAY;
        }
    }

    public Color getSocketColor(){
        return Color.WHITE;
    }

    //TODO rendering sockets first and then plugs


    public void renderTopSocket(Graphics g){
        g.setColor(getBlockColor());
        g.fillRect(this.getPos().getX() , (int) this.getPos().getY() , (int) (this.getWidth()-this.getSocketSize())/2, this.getSocketSize());
        g.fillRect(this.getPos().getX() + (int) (this.getWidth()-this.getSocketSize())/2 + this.getSocketSize(), (int) this.getPos().getY() , (int) (this.getWidth()-this.getSocketSize())/2, this.getSocketSize());
        
    }

    

    public void renderBottomPlug(Graphics g){
        g.setColor(getBlockColor());
        g.fillRect(this.getPos().getX() + (int) (this.getWidth() / 2 - this.getSocketSize()/2), this.getPos().getY() + (int) (this.getHeight()), this.getSocketSize(), this.getSocketSize());
     }

    public void renderLeftPlug(Graphics g){
        g.setColor(getBlockColor());
        g.fillRect(this.getPos().getX() - this.getSocketSize(),this.getPos().getY() + (this.getHeight() - this.getSocketSize())/2, this.getSocketSize(), this.getSocketSize());
    }

    public void renderRightSocket(Graphics g){
        g.setColor(getBlockColor());
        g.fillRect(this.getPos().getX() + this.getWidth() - this.getSocketSize(), (int) this.getPos().getY() , this.getSocketSize(), (int) (this.getHeight()-this.getSocketSize())/2);
        g.fillRect(this.getPos().getX() + this.getWidth() - this.getSocketSize(), (int) this.getPos().getY() + this.getSocketSize() + (this.getHeight()-this.getSocketSize())/2, this.getSocketSize(), (int) (this.getHeight()-this.getSocketSize())/2);
    }

    public void renderNoRightSocket(Graphics g){
        g.setColor(getBlockColor());
        g.fillRect(this.getPos().getX() + this.getWidth() - this.getSocketSize(), (int) this.getPos().getY() , this.getSocketSize(), this.getHeight());
    }

    public void renderNoTopSocket(Graphics g){
        g.setColor(getBlockColor());
        g.fillRect(this.getPos().getX() , (int) this.getPos().getY() , this.getWidth(), this.getSocketSize());
    }


    public void renderInnerBlock(Graphics g){
        g.setColor(getBlockColor());
        g.fillRect(this.getPos().getX() ,this.getPos().getY() + this.getSocketSize(),this.getWidth() - this.getSocketSize(), this.getHeight() - this.getSocketSize());
    }

    /** 
     * This function renders the block
     * 
     * @param {Graphics} g The Graphics object on which the block is rendered
     */
    public void render(Graphics g){


        
    
        switch (this.getType().getType()){
            case(Blocktype.IF):
            case(Blocktype.WHILE):
                this.renderInnerBlock(g);
                this.renderBottomPlug(g);
                this.renderTopSocket(g);
                this.renderRightSocket(g);
                
                break;
            case(Blocktype.MOVEFORWARD):
            case(Blocktype.TURNLEFT):
            case(Blocktype.TURNRIGHT):
                this.renderInnerBlock(g);
                this.renderBottomPlug(g);
                this.renderTopSocket(g);
                this.renderNoRightSocket(g);
                
                
                break;
            case(Blocktype.NOT):
                this.renderInnerBlock(g);
                this.renderNoTopSocket(g);
                this.renderRightSocket(g);
                this.renderLeftPlug(g);
                
                
                
                break;
            case(Blocktype.WALLINFRONT):
                this.renderInnerBlock(g);
                this.renderLeftPlug(g);
                this.renderNoRightSocket(g);
                this.renderNoTopSocket(g);
                
                
                break;
            default:
                
                break;

            

                
        }

        g.setColor(Color.WHITE);
        g.drawString(this.type.getTitle(), this.getPos().getX() + 10, this.getPos().getY() + (height/2));
        
    }


 





    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public int getSocketSize() {
        return this.socketSize;
    }

    public boolean isHighlighted() {
        return this.highlighted;
    }

    public Blocktype getType() {
        return this.type;
    }
}
