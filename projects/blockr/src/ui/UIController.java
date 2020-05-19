package ui;

import java.util.ArrayList;
import java.awt.*;

import main.MyCanvasWindow;
import utilities.*;
import model.*;

/**
 * Class controlling the UI elements.
 */
public class UIController {

    private static UIController instance;

    public final static int PALETTEWIDTH = 300;
    public final static int PROGRAMAREAWIDTH = 500;


    // Constructor
    private UIController(){
    }

    public static UIController getInstance() {
        if (instance == null) {
            instance = new UIController();
        }
        return instance;
    }

    /**
     * Method to render the UI elements
     * @param g Graphics object
     * @param blocks the block states that need to be rendered
     * @author Oberon Swings
     */
    public void render(Graphics g, ArrayList<BlockState> blocks){
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
    private void renderUI(Graphics g){
        g.setColor(Color.BLACK);
        g.drawLine(PALETTEWIDTH, 0, PALETTEWIDTH, MyCanvasWindow.HEIGHT);
        g.drawLine(PALETTEWIDTH + PROGRAMAREAWIDTH, 0, PALETTEWIDTH + PROGRAMAREAWIDTH, MyCanvasWindow.HEIGHT);
    }
}