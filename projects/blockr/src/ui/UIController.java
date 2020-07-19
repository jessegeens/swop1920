package ui;

import java.awt.*;
import java.util.ArrayList;

import main.MyCanvasWindow;
import model.blocks.ModelBlock;
import ui.window.Content;
import ui.window.SimpleWindow;
import ui.window.VerticalScrollbarDecorator;
import model.*;

/**
 * Class controlling the UI elements.
 */
public class UIController {

    private VerticalScrollbarDecorator palette;
    private VerticalScrollbarDecorator programArea;

    public final static int PALETTEWIDTH = 300;
    public final static int PROGRAMAREAWIDTH = 500;
    public final static int WINDOWHEIGHT = 900;


    // Constructor
    public UIController(ArrayList<Content> content){
        this.palette = new VerticalScrollbarDecorator(new SimpleWindow(0,0, PALETTEWIDTH, WINDOWHEIGHT, content.get(0)));
        this.programArea = new VerticalScrollbarDecorator(new SimpleWindow(PALETTEWIDTH + VerticalScrollbarDecorator.SCROLLBARWIDTH, 0, PROGRAMAREAWIDTH, WINDOWHEIGHT, content.get(1)));
    }

    /**
     * Method to render the UI elements
     * @param g Graphics object
     * @author Oberon Swings
     */
    public void render(Graphics g){
        palette.render(g);
        programArea.render(g);
    }

    public void handleMouseEvent(int id, int x, int y){
        if (x < PALETTEWIDTH + VerticalScrollbarDecorator.SCROLLBARWIDTH) {
            palette.handleMouseEvent(id, x, y);
            programArea.deactivateScroll();
        }
        else {
            programArea.handleMouseEvent(id, x, y);
            palette.deactivateScroll();
        }
    }
}