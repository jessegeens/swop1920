package ui;
import java.awt.*;
import java.util.ArrayList;


import utilities.*;

import javax.xml.stream.events.StartDocument;

/**
 * Class representing the different blocks and their visualisation.
 */
public class UIBlock{

    //To run tests PLUGSIZE = 20, WIDTH & HEIGHT = 80, too much work to make tests dynamic.
    public static final int PLUGSIZE = 20; //final standard size of plugs and sockets
    public static final int STD_WIDTH = 80; //final standard width of blocks
    public static final int STD_HEIGHT = 80; //final standard height of blocks

    UIBlock(){ }

    private void renderTopSocket(Graphics g, ProgramLocation blockLocation, Color color) {
        g.setColor(color);
        g.fillRect(blockLocation.getX(), blockLocation.getY(), this.getTopSocketPos(blockLocation).getX() - blockLocation.getX(), PLUGSIZE);
        g.fillRect(this.getTopSocketPos(blockLocation).getX() + PLUGSIZE, blockLocation.getY(), blockLocation.getX() + STD_WIDTH - this.getTopSocketPos(blockLocation).getX() - PLUGSIZE, PLUGSIZE);
    }

    private void renderBottomPlug(Graphics g, ProgramLocation blockLocation, Color color, int cavitySize) {
        g.setColor(color);
        g.fillRect(this.getBottomPlugPos(blockLocation, cavitySize).getX(), this.getBottomPlugPos(blockLocation, cavitySize).getY(), PLUGSIZE, PLUGSIZE);
    }

    private void renderLeftPlug(Graphics g, ProgramLocation blockLocation, Color color) {
        g.setColor(color);
        g.fillRect(this.getLeftPlugPos(blockLocation).getX() - PLUGSIZE, this.getLeftPlugPos(blockLocation).getY(), PLUGSIZE, PLUGSIZE);
    }

    private void renderRightSocket(Graphics g, ProgramLocation blockLocation, Color color, int cavitySize) {
        g.setColor(color);
        g.fillRect(this.getRightSocketPos(blockLocation).getX(), blockLocation.getY(), PLUGSIZE, this.getRightSocketPos(blockLocation).getY() - blockLocation.getY());
        g.fillRect(this.getRightSocketPos(blockLocation).getX(), this.getRightSocketPos(blockLocation).getY() + PLUGSIZE, PLUGSIZE, blockLocation.getY() + this.getHeight(cavitySize) - this.getRightSocketPos(blockLocation).getY() - PLUGSIZE);
    }

    private void renderNoRightSocket(Graphics g, ProgramLocation blockLocation, Color color, int cavitySize) {
        g.setColor(color);
        g.fillRect(blockLocation.getX() + STD_WIDTH - PLUGSIZE, blockLocation.getY(), PLUGSIZE, this.getHeight(cavitySize));
    }

    private void renderNoTopSocket(Graphics g, ProgramLocation blockLocation, Color color) {
        g.setColor(color);
        g.fillRect(blockLocation.getX(), blockLocation.getY(), STD_WIDTH, PLUGSIZE);
    }


    private void renderInnerBlock(Graphics g, ProgramLocation blockLocation, Color color, int cavitySize) {
        g.setColor(color);
        g.fillRect(blockLocation.getX(), blockLocation.getY() + PLUGSIZE, STD_WIDTH - PLUGSIZE, this.getHeight(cavitySize) - PLUGSIZE);
    }

    /**
     * This function renders the cavity socket (socket is at the bottom)
     * @param blockLocation the location of the block
     * @param cavitySize the size of the cavity of the block
     * @param g The Graphics object on which the block is rendered
     * @author Bert De Vleeschouwer
     */
    private void renderCavitySocket(Graphics g, ProgramLocation blockLocation,Color color, int cavitySize) {
        g.setColor(color);
        g.fillRect(this.getCavitySocketPos(blockLocation,cavitySize).getX(), this.getCavitySocketPos(blockLocation,cavitySize).getY(), PLUGSIZE, PLUGSIZE);
    }

    /**
     * This function renders the cavity plug (plug is at the top)
     * @param blockLocation the location of the block
     * @param g The Graphics object on which the block is rendered
     * @author Bert De Vleeschouwer
     */
    private void renderCavityPlug(Graphics g, ProgramLocation blockLocation, Color color) {
        g.setColor(color);
        g.fillRect(this.getCavityPlugPos(blockLocation).getX(), this.getCavityPlugPos(blockLocation).getY(), PLUGSIZE, PLUGSIZE);
    }

    /**
     * This function renders the block
     * @param g The Graphics object on which the block is rendered
     * @param blockState the state in which the block needs to be rendered
     * @author Bert De Vleeschouwer
     */
    public void render(Graphics g, BlockState blockState) {
        ProgramLocation blockLocation = blockState.getBlockLocation();
        ArrayList<ConnectionPoint> connectionPoints = blockState.getConnectionPoints();
        Color color = blockState.getColor();
        int cavitySize = blockState.getCavitySize();
        renderInnerBlock(g, blockLocation, color, cavitySize);
        if (connectionPoints.contains(ConnectionPoint.TOP_SOCKET)) this.renderTopSocket(g, blockLocation, color);
        else this.renderNoTopSocket(g, blockLocation, color);
        if (connectionPoints.contains(ConnectionPoint.BOTTOM_PLUG)) this.renderBottomPlug(g, blockLocation, color, cavitySize);
        if (connectionPoints.contains(ConnectionPoint.RIGHT_SOCKET)) this.renderRightSocket(g, blockLocation, color, cavitySize);
        else this.renderNoRightSocket(g, blockLocation, color, cavitySize);
        if (connectionPoints.contains(ConnectionPoint.LEFT_PLUG)) this.renderLeftPlug(g, blockLocation, color);
        if (connectionPoints.contains(ConnectionPoint.CAVITY_PLUG)) this.renderCavityPlug(g, blockLocation, color);
        if (connectionPoints.contains(ConnectionPoint.CAVITY_SOCKET)) this.renderCavitySocket(g, blockLocation, color, cavitySize);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.PLAIN, 10));
        g.drawString(blockState.getTitle(), blockLocation.getX() + 8, blockLocation.getY() + (STD_HEIGHT / 2));
    }

    /**
     * @param blockLocation the location of the block
     * @param cavitySize the size of the cavity of the block
     * @return the position of the cavity socket in a WHILEIF block
     * @author Bert De Vleeschouwer
     */
    private ProgramLocation getCavitySocketPos(ProgramLocation blockLocation, int cavitySize) {
        return blockLocation.add(2*STD_WIDTH/3 - PLUGSIZE/2, this.getHeight(cavitySize) - STD_HEIGHT/3 - PLUGSIZE/2);
    }

    /**
     * @param blockLocation the location of the block
     * @return the position of the cavity plug in a WHILEIF block
     * @author Bert De Vleeschouwer
     */
    private ProgramLocation getCavityPlugPos(ProgramLocation blockLocation) {
        return blockLocation.add(2*STD_WIDTH/3 - PLUGSIZE/2, 2*STD_HEIGHT/3 - PLUGSIZE/2);
    }

    /**
     * @param blockLocation the location of the block
     * @return the position of the topSocket
     * @author Oberon Swings
     */
    private ProgramLocation getTopSocketPos(ProgramLocation blockLocation) {
        return blockLocation.add(STD_WIDTH/2 - PLUGSIZE/2, 0);
    }

    /**
     * @param blockLocation the location of the block
     * @return the position of the rightSocket
     * @author Oberon Swings
     */
    private ProgramLocation getRightSocketPos(ProgramLocation blockLocation) {
        return blockLocation.add(STD_WIDTH - PLUGSIZE, STD_HEIGHT/2 -PLUGSIZE/2);
    }

    /**
     * @param blockLocation the location of the block
     * @param cavitySize the size of the cavity of the block
     * @return the position of the bottomPlug
     * @author Oberon Swings
     */
    private ProgramLocation getBottomPlugPos(ProgramLocation blockLocation, int cavitySize) {
        return blockLocation.add(STD_WIDTH/2 - PLUGSIZE/2, this.getHeight(cavitySize));
    }

    /**
     * @param blockLocation the location of the block
     * @return the position of the leftPlug
     * @author Oberon Swings
     */
    private ProgramLocation getLeftPlugPos(ProgramLocation blockLocation) {
        return blockLocation.add(0, STD_HEIGHT/2 - PLUGSIZE/2);
    }

    /**
     * gives the height that the rendered block needs to have
     * @param cavitySize the size of the cavity if the block has one, 0 if there is no cavity
     * @return the height that the rendered block gets
     */
    private int getHeight(int cavitySize) {
        return (cavitySize + 1) * STD_HEIGHT;
    }
}
