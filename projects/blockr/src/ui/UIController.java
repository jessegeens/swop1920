package ui;

import java.util.ArrayList;
import java.awt.*;

import ui.blocks.*;
import utilities.*;
import model.blocks.ModelBlock;
import model.*;

/**
 * Class controlling the UI elements.
 */
public class UIController {

    private final int wWidth;  //window width
    private final int wHeight; //window height

    // Constructor
    public UIController(int windowWidth, int windowHeight){
        this.wWidth = windowWidth;
        this.wHeight = windowHeight;
    }

    /**
     * Method to render the UI elements.
     * @param g graphics object where the UI elements are rendered.
     */
    public void render(Graphics g){
        System.out.println("Rendering UI, blocks and grid");
        this.renderUI(g);
        //blocks.forEach((UIBlock block) -> block.render(g));
        //this.grid.render(g);
    }

    /**
     * Method to render the UI elements
     * @param g Graphics object
     * @param state the program state that needs to be rendered
     */
    public void render(Graphics g, ProgramState state, ArrayList<BlockState> blocks){
        Location gridLocation = new Location(wWidth*2/3, 0);
        UIBlock uiBlock = new UIBlock();
        UIGrid uiGrid = new UIGrid();
        System.out.println("Rendering UI, blocks and grid");
        this.renderUI(g);
        for (BlockState block : blocks){
            uiBlock.render(g, block);
        }
        uiGrid.render(g, wWidth, wHeight, gridLocation, state);
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