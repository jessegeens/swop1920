package ui.blocks;
import ui.*;
import java.awt.*;

import model.blocks.ModelBlock;
import utilities.*;

/**
 * Class representing the different blocks and their visualisation.
 */
public class UIBlock extends UIElement {

    private ModelBlock mBlock;

    // Constructor
    public UIBlock(ModelBlock mBlock) {
        super(mBlock.getPos());
        this.mBlock = mBlock;
    }

    private Color getBlockColor(){
        if(this.isHighlighted()) {
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


    private void renderTopSocket(Graphics g){
        g.setColor(getBlockColor());
        g.fillRect(this.getPos().getX() , this.getPos().getY() , (this.getWidth()-this.getSocketSize())/2, this.getSocketSize());
        g.fillRect(this.getPos().getX() + (this.getWidth()-this.getSocketSize())/2 + this.getSocketSize(), this.getPos().getY() , (this.getWidth()-this.getSocketSize())/2, this.getSocketSize());
        
    }

    

    private void renderBottomPlug(Graphics g){
        g.setColor(getBlockColor());
        g.fillRect(this.getPos().getX() + (this.getWidth() / 2 - this.getSocketSize()/2), this.getPos().getY() + (this.getHeight()), this.getSocketSize(), this.getSocketSize());
    }

    private void renderLeftPlug(Graphics g){
        g.setColor(getBlockColor());
        g.fillRect(this.getPos().getX() - this.getSocketSize(),this.getPos().getY() + (this.getHeight() - this.getSocketSize())/2, this.getSocketSize(), this.getSocketSize());
    }

    private void renderRightSocket(Graphics g){
        g.setColor(getBlockColor());
        g.fillRect(this.getPos().getX() + this.getWidth() - this.getSocketSize(), this.getPos().getY() , this.getSocketSize(), (this.getHeight()-this.getSocketSize())/2);
        g.fillRect(this.getPos().getX() + this.getWidth() - this.getSocketSize(), this.getPos().getY() + this.getSocketSize() + (this.getHeight()-this.getSocketSize())/2, this.getSocketSize(), (this.getHeight()-this.getSocketSize())/2);
    }

    private void renderNoRightSocket(Graphics g){
        g.setColor(getBlockColor());
        g.fillRect(this.getPos().getX() + this.getWidth() - this.getSocketSize(), this.getPos().getY() , this.getSocketSize(), this.getHeight());
    }

    private void renderNoTopSocket(Graphics g){
        g.setColor(getBlockColor());
        g.fillRect(this.getPos().getX() , this.getPos().getY() , this.getWidth(), this.getSocketSize());
    }


    private void renderInnerBlock(Graphics g){
        g.setColor(getBlockColor());
        g.fillRect(this.getPos().getX() ,this.getPos().getY() + this.getSocketSize(),this.getWidth() - this.getSocketSize(), this.getHeight() - this.getSocketSize());
    }

    /** 
     * This function renders the block
     * 
     * @param g The Graphics object on which the block is rendered
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
        g.drawString(this.getType().getTitle(), this.getPos().getX() + 10, this.getPos().getY() + (this.getHeight()/2));
        
    }


 



    public ModelBlock getmBlock(){
        return this.mBlock;
    }

    public int getWidth() {
        return this.getmBlock().getWidth();
    }

    public int getHeight() {
        return this.getmBlock().getHeight();
    }

    public int getSocketSize() {
        return ModelBlock.PLUGSIZE;
    }

    public boolean isHighlighted() {
        return this.getmBlock().isHighlighted();
    }

    public Blocktype getType() {
        return this.getmBlock().getBlockType();
    }
}
