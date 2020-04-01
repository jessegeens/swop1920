package ui.blocks;
import ui.*;
import java.awt.*;


import model.blocks.ModelBlock;
import model.blocks.ModelWhileIfBlock;
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
     * This function renders the cavity socket (socket is at the bottom)
     * 
     * @param g The Graphics object on which the block is rendered
     * @author Bert De Vleeschouwer
     */
    private void renderCavitySocket(Graphics g){
        g.setColor(Color.GREEN);
        g.fillRect(this.getCavitySocketPos().getX(),this.getCavitySocketPos().getY(), this.getSocketSize(), this.getSocketSize());


    }

    /** 
     * This function renders the cavity plug (plug is at the top)
     * 
     * @param g The Graphics object on which the block is rendered
     * @author Bert De Vleeschouwer
     */
    private void renderCavityPlug(Graphics g){
        g.setColor(Color.GREEN);
        g.fillRect(this.getCavityPlugPos().getX(),this.getCavityPlugPos().getY(), this.getSocketSize(), this.getSocketSize());
        
    }

    /** 
     * This function renders the block
     * 
     * @param g The Graphics object on which the block is rendered
     * @author Bert De Vleeschouwer
     */
    public void render(Graphics g){


        
    
        switch (this.getType()){
            case IF:
            case WHILE:
                this.renderInnerBlock(g);
                this.renderBottomPlug(g);
                this.renderTopSocket(g);
                this.renderRightSocket(g);
                this.renderCavityPlug(g);
                this.renderCavitySocket(g);
                break;
            case MOVEFORWARD:
            case TURNLEFT:
            case TURNRIGHT:
                this.renderInnerBlock(g);
                this.renderBottomPlug(g);
                this.renderTopSocket(g);
                this.renderNoRightSocket(g);
                break;
            case NOT:
                this.renderInnerBlock(g);
                this.renderNoTopSocket(g);
                this.renderRightSocket(g);
                this.renderLeftPlug(g);
                break;
            case WALLINFRONT:
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
     * @return the position of the cavity socket in a WHILEIF block
     * @author Bert De Vleeschouwer
     */
    public WindowLocation getCavitySocketPos(){
        if (this.getmBlock().getBlockType() == BlockType.WHILE || this.getmBlock().getBlockType() == BlockType.IF){
            return ((ModelWhileIfBlock)this.getmBlock()).getCavitySocketPos().add(-this.getSocketSize()/2, -this.getSocketSize()/2);
        }
        return new WindowLocation(0,0);
    }

    /**
     *
     * @return the position of the cavity plug in a WHILEIF block
     * @author Bert De Vleeschouwer
     */
    public WindowLocation getCavityPlugPos(){
        if (this.getmBlock().getBlockType() == BlockType.WHILE || this.getmBlock().getBlockType() == BlockType.IF){
            return ((ModelWhileIfBlock)this.getmBlock()).getCavityPlugPos().add(-this.getSocketSize()/2, -this.getSocketSize()/2);
        }
        return new WindowLocation(0,0);
    }

    /**
     *
     * @return the position of the topSocket
     * @author Oberon Swings
     */
    public WindowLocation getTopSocketPos(){
        if (!this.getmBlock().hasTopSocket()) return new WindowLocation(0,0);
        return this.getmBlock().getTopSocketPos().add(-this.getSocketSize()/2, -this.getSocketSize()/2);
    }

    /**
     *
     * @return the position of the rightSocket
     * @author Oberon Swings
     */
    public WindowLocation getRightSocketPos(){
        if (!this.getmBlock().hasRightSocket()) return new WindowLocation(0,0);
        return this.getmBlock().getRightSocketPos().add(-this.getSocketSize()/2, -this.getSocketSize()/2);
    }

    /**
     *
     * @return the position of the bottomPlug
     * @author Oberon Swings
     */
    public WindowLocation getBottomPlugPos(){
        if (!this.getmBlock().hasBottomPlug()) return new WindowLocation(0,0);
        return this.getmBlock().getBottomPlugPos().add(-this.getSocketSize()/2, -this.getSocketSize()/2);
    }

    /**
     *
     * @return the position of the leftPlug
     * @author Oberon Swings
     */
    public WindowLocation getLeftPlugPos() {
        if (!this.getmBlock().hasLeftPlug()) return new WindowLocation(0,0);
        return this.getmBlock().getLeftPlugPos().add(-this.getSocketSize()/2, -this.getSocketSize()/2);
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
    public BlockType getType() {
        return this.getmBlock().getBlockType();
    }
}
