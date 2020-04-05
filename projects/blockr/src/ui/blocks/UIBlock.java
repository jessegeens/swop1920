package ui.blocks;
import ui.*;
import java.awt.*;


import model.blocks.ModelBlock;
import model.blocks.ModelWhileIfBlock;
import utilities.*;

/**
 * Class representing the different blocks and their visualisation.
 */
public class UIBlock{


    public static final int PLUGSIZE = 20; //final standard size of plugs and sockets
    public static final int STD_WIDTH = 80; //final standard width of blocks
    public static final int STD_HEIGHT = 80; //final standard height of blocks


    public UIBlock() {
    }

    /**
     * Gives the color in which the blocks need to be rendered according to whether they are highlighted or not
     * @param highlighted the bool to know if the block is highlighted
     * @return color green if highlighted, gray otherwise
     * @author Oberon Swings
     */
    private Color getBlockColor(boolean highlighted) {
        if (highlighted) {
            return Color.GREEN;
        } else {
            return Color.GRAY;
        }
    }

    public Color getSocketColor() {
        return Color.WHITE;
    }

    //TODO rendering sockets first and then plugs


    private void renderTopSocket(Graphics g, Location blockLocation, boolean highlighted) {
        g.setColor(getBlockColor(highlighted));
        g.fillRect(blockLocation.getX(), blockLocation.getY(), this.getTopSocketPos(blockLocation).getX() - blockLocation.getX(), PLUGSIZE);
        g.fillRect(this.getTopSocketPos(blockLocation).getX() + PLUGSIZE, blockLocation.getY(), blockLocation.getX() + this.getWidth() - this.getTopSocketPos(blockLocation).getX() - PLUGSIZE, PLUGSIZE);
    }

    private void renderBottomPlug(Graphics g, Location blockLocation, boolean highlighted, int cavitySize) {
        g.setColor(getBlockColor(highlighted));
        g.fillRect(this.getBottomPlugPos(blockLocation, cavitySize).getX(), this.getBottomPlugPos(blockLocation, cavitySize).getY(), PLUGSIZE, PLUGSIZE);
    }

    private void renderLeftPlug(Graphics g, Location blockLocation, boolean highlighted) {
        g.setColor(getBlockColor(highlighted));
        g.fillRect(this.getLeftPlugPos(blockLocation).getX(), this.getLeftPlugPos(blockLocation).getY(), PLUGSIZE, PLUGSIZE);
    }

    private void renderRightSocket(Graphics g, Location blockLocation, boolean highlighted, int cavitySize) {
        g.setColor(getBlockColor(highlighted));
        g.fillRect(this.getRightSocketPos(blockLocation).getX(), blockLocation.getY(), PLUGSIZE, this.getRightSocketPos(blockLocation).getY() - blockLocation.getY());
        g.fillRect(this.getRightSocketPos(blockLocation).getX(), this.getRightSocketPos(blockLocation).getY() + PLUGSIZE, PLUGSIZE, blockLocation.getY() + this.getHeight(cavitySize) - this.getRightSocketPos(blockLocation).getY() - PLUGSIZE);
    }

    private void renderNoRightSocket(Graphics g, Location blockLocation, boolean highlighted, int cavitySize) {
        g.setColor(getBlockColor(highlighted));
        g.fillRect(blockLocation.getX() + this.getWidth() - PLUGSIZE, blockLocation.getY(), PLUGSIZE, this.getHeight(cavitySize));
    }

    private void renderNoTopSocket(Graphics g, Location blockLocation, boolean highlighted) {
        g.setColor(getBlockColor(highlighted));
        g.fillRect(blockLocation.getX(), blockLocation.getY(), this.getWidth(), PLUGSIZE);
    }


    private void renderInnerBlock(Graphics g, Location blockLocation, boolean highlighted, int cavitySize) {
        g.setColor(getBlockColor(highlighted));
        g.fillRect(blockLocation.getX(), blockLocation.getY() + PLUGSIZE, this.getWidth() - PLUGSIZE, this.getHeight(cavitySize) - PLUGSIZE);
    }

    /**
     * This function renders the cavity socket (socket is at the bottom)
     *
     * @param g The Graphics object on which the block is rendered
     * @author Bert De Vleeschouwer
     */
    private void renderCavitySocket(Graphics g, Location blockLocation, int cavitySize) {
        g.setColor(Color.GREEN);
        g.fillRect(this.getCavitySocketPos(blockLocation,cavitySize).getX(), this.getCavitySocketPos(blockLocation,cavitySize).getY(), PLUGSIZE, PLUGSIZE);
    }

    /**
     * This function renders the cavity plug (plug is at the top)
     *
     * @param g The Graphics object on which the block is rendered
     * @author Bert De Vleeschouwer
     */
    private void renderCavityPlug(Graphics g, Location blockLocation) {
        g.setColor(Color.GREEN);
        g.fillRect(this.getCavityPlugPos(blockLocation).getX(), this.getCavityPlugPos(blockLocation).getY(), PLUGSIZE, PLUGSIZE);
    }

    /**
     * This function renders the block
     *
     * @param g The Graphics object on which the block is rendered
     * @param blockState the state in which the block needs to be rendered
     * @author Bert De Vleeschouwer
     */
    public void render(Graphics g, BlockState blockState) {
        Location blockLocation = blockState.getBlockLocation();
        BlockType blockType = blockState.getBlockType();
        boolean highlighted = blockState.isHighlighted();
        int cavitySize = blockState.getCavitySize();
        switch (blockType) {
            case IF:
            case WHILE:
                this.renderInnerBlock(g,blockLocation,highlighted,cavitySize);
                this.renderBottomPlug(g,blockLocation,highlighted,cavitySize);
                this.renderTopSocket(g,blockLocation,highlighted);
                this.renderRightSocket(g,blockLocation,highlighted,cavitySize);
                this.renderCavityPlug(g,blockLocation);
                this.renderCavitySocket(g,blockLocation,cavitySize);
                break;
            case MOVEFORWARD:
            case TURNLEFT:
            case TURNRIGHT:
                this.renderInnerBlock(g,blockLocation,highlighted,cavitySize);
                this.renderBottomPlug(g,blockLocation,highlighted,cavitySize);
                this.renderTopSocket(g,blockLocation,highlighted);
                this.renderNoRightSocket(g,blockLocation,highlighted,cavitySize);
                break;
            case NOT:
                this.renderInnerBlock(g,blockLocation,highlighted,cavitySize);
                this.renderNoTopSocket(g,blockLocation,highlighted);
                this.renderRightSocket(g,blockLocation,highlighted,cavitySize);
                this.renderLeftPlug(g,blockLocation,highlighted);
                break;
            case WALLINFRONT:
                this.renderInnerBlock(g,blockLocation,highlighted,cavitySize);
                this.renderLeftPlug(g,blockLocation,highlighted);
                this.renderNoRightSocket(g,blockLocation,highlighted,cavitySize);
                this.renderNoTopSocket(g,blockLocation,highlighted);
                break;
            default:
                break;
        }
        g.setColor(Color.WHITE);
        g.drawString(blockType.getTitle(), blockLocation.getX() + 10, blockLocation.getY() + (ModelBlock.STD_HEIGHT / 2));

    }


    /**
     * @return the position of the cavity socket in a WHILEIF block
     * @author Bert De Vleeschouwer
     */
    public Location getCavitySocketPos(Location blockLocation, int cavitySize) {
        return blockLocation.add(2*ModelBlock.STD_WIDTH/3, this.getHeight(cavitySize) - ModelBlock.STD_HEIGHT/3);
    }

    /**
     * @return the position of the cavity plug in a WHILEIF block
     * @author Bert De Vleeschouwer
     */
    public Location getCavityPlugPos(Location blockLocation) {
        return blockLocation.add(2*ModelBlock.STD_WIDTH/3, 2*ModelBlock.STD_HEIGHT/3);
    }

    /**
     * @return the position of the topSocket
     * @author Oberon Swings
     */
    public Location getTopSocketPos(Location blockLocation) {
        return blockLocation.add(this.getWidth()/2 - PLUGSIZE/2, 0);
    }

    /**
     * @return the position of the rightSocket
     * @author Oberon Swings
     */
    public Location getRightSocketPos(Location blockLocation) {
        return blockLocation.add(this.getWidth() - PLUGSIZE, STD_HEIGHT/2 -PLUGSIZE/2);
    }

    /**
     * @return the position of the bottomPlug
     * @author Oberon Swings
     */
    public Location getBottomPlugPos(Location blockLocation, int cavitySize) {
        return blockLocation.add(this.getWidth()/2 - PLUGSIZE/2, this.getHeight(cavitySize));
    }

    /**
     * @return the position of the leftPlug
     * @author Oberon Swings
     */
    public Location getLeftPlugPos(Location blockLocation) {
        return blockLocation.add(0, STD_HEIGHT/2 - PLUGSIZE/2);
    }

    /**
     * @return the width of the modelBlock
     */
    public int getWidth() {
        return STD_WIDTH;
    }

    /**
     * gives the height that the rendered block needs to have
     * @param cavitySize the size of the cavity if the block has one, 0 if there is no cavity
     * @return the height that the rendered block gets
     */
    public int getHeight(int cavitySize) {
        return (cavitySize + 1) * STD_HEIGHT;
    }
}
