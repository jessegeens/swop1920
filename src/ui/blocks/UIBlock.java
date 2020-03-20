package ui.blocks;
import model.blocks.plugs.BottomPlug;
import model.blocks.plugs.LeftPlug;
import model.blocks.plugs.RightSocket;
import model.blocks.plugs.TopSocket;
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
        g.fillRect(this.getPos().getX() , this.getPos().getY() , this.getTopSocketPos().getX() - this.getPos().getX(), this.getSocketSize());
        g.fillRect(this.getTopSocketPos().getX() + this.getSocketSize(), this.getPos().getY() , this.getPos().getX() + this.getWidth() - this.getTopSocketPos().getX() - this.getSocketSize(), this.getSocketSize());
    }

    

    private void renderBottomPlug(Graphics g){
        g.setColor(getBlockColor());
        g.fillRect(this.getBottomPlugPos().getX(), this.getBottomPlugPos().getY(), this.getSocketSize(), this.getSocketSize());
    }

    private void renderLeftPlug(Graphics g){
        g.setColor(getBlockColor());
        g.fillRect(this.getLeftPlugPos().getX(),this.getLeftPlugPos().getY(), this.getSocketSize(), this.getSocketSize());
    }

    private void renderRightSocket(Graphics g){
        g.setColor(getBlockColor());
        g.fillRect(this.getRightSocketPos().getX(), this.getPos().getY() , this.getSocketSize(), this.getRightSocketPos().getY() - this.getPos().getY());
        g.fillRect(this.getRightSocketPos().getX(), this.getRightSocketPos().getY() + this.getSocketSize(), this.getSocketSize(), this.getPos().getY() + this.getHeight() - this.getRightSocketPos().getY() - this.getSocketSize());
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
        g.drawString(this.getType().getTitle(), this.getPos().getX() + 10, this.getPos().getY() + (ModelBlock.STD_HEIGHT/2));
        
    }


    /**
     *
     * @return the modelBlock this UIBlock is representing
     * @author Oberon Swings
     */
    public ModelBlock getmBlock(){
        return this.mBlock;
    }

    /**
     *
     * @return the position of the topSocket
     * @author Oberon Swings
     */
    public WindowLocation getTopSocketPos(){
        if (!this.getmBlock().hasTopSocket()) return new WindowLocation(0,0);
        return ((TopSocket)this.getmBlock()).getTopSocketPos().add(-this.getSocketSize()/2, -this.getSocketSize()/2);
    }

    /**
     *
     * @return the position of the rightSocket
     * @author Oberon Swings
     */
    public WindowLocation getRightSocketPos(){
        if (!this.getmBlock().hasRightSocket()) return new WindowLocation(0,0);
        return ((RightSocket)this.getmBlock()).getRightSocketPos().add(-this.getSocketSize()/2, -this.getSocketSize()/2);
    }

    /**
     *
     * @return the position of the bottomPlug
     * @author Oberon Swings
     */
    public WindowLocation getBottomPlugPos(){
        if (!this.getmBlock().hasBottomPlug()) return new WindowLocation(0,0);
        return ((BottomPlug)this.getmBlock()).getBottomPlugPos().add(-this.getSocketSize()/2, -this.getSocketSize()/2);
    }

    /**
     *
     * @return the position of the leftPlug
     * @author Oberon Swings
     */
    public WindowLocation getLeftPlugPos() {
        if (!this.getmBlock().hasLeftPlug()) return new WindowLocation(0,0);
        return ((LeftPlug)this.getmBlock()).getLeftPlugPos().add(-this.getSocketSize()/2, -this.getSocketSize()/2);
    }

    /**
     *
     * @return the width of the modelBlock
     */
    public int getWidth() {
        return this.getmBlock().getWidth();
    }

    /**
     *
     * @return the height of the modelBlock
     */
    public int getHeight() {
        return this.getmBlock().getHeight();
    }

    /**
     *
     * @return The size of the Socket/Plug
     */
    public int getSocketSize() {
        return ModelBlock.PLUGSIZE;
    }

    /**
     *
     * @return true if the modelBlock is highlighted
     */
    public boolean isHighlighted() {
        return this.getmBlock().isHighlighted();
    }

    /**
     *
     * @return the type of the modelBlock
     */
    public Blocktype getType() {
        return this.getmBlock().getBlockType();
    }
}
