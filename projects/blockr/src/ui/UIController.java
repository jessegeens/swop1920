package ui;

import java.util.ArrayList;
import java.awt.*;

import utilities.*;
import model.*;

/**
 * Class controlling the UI elements.
 */
public class UIController {

    private final int wWidth;  //window width
    private final int wHeight; //window height

    UIBlock uiBlock;

    // Constructor
    public UIController(int windowWidth, int windowHeight){
        this.wWidth = windowWidth;
        this.wHeight = windowHeight;
        uiBlock = new UIBlock();
    }

    /**
     * Method to render the UI elements
     * @param g Graphics object
     * @param blocks the block states that need to be rendered
     * @author Oberon Swings
     */
    public void render(Graphics g, ArrayList<BlockState> blocks){
        ProgramLocation gridLocation = new ProgramLocation(wWidth*2/3, 0);
        UIBlock uiBlock = new UIBlock();
        this.renderUI(g);
        for (BlockState block : blocks){
            uiBlock.render(g, block);
        }
    }

    /**
     * Help method for the render method.
     * @param g graphics object where the rendering is done.
     */
    public void renderUI(Graphics g){
        g.setColor(Color.BLACK);
        g.drawLine(this.wWidth/3, 0, this.wWidth/3, this.wHeight);
        g.drawLine(2*this.wWidth/3, 0, 2*this.wWidth/3, this.wHeight);
    }
}