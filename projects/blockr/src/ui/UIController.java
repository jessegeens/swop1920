package ui;

import java.awt.*;
import java.util.ArrayList;

import ui.window.WindowContent;
import ui.window.SimpleWindow;
import ui.window.VerticalScrollbarDecorator;

/**
 * Class controlling the UI elements.
 */
public class UIController {

    private VerticalScrollbarDecorator leftWindow;
    private VerticalScrollbarDecorator middleWindow;
    private VerticalScrollbarDecorator rightWindow;

    public final static int PALETTEWIDTH = 300;
    public final static int PROGRAMAREAWIDTH = 500;
    public final static int GAMEWORLDWIDTH = 250;
    public final static int WINDOWHEIGHT = 900;


    // Constructor
    public UIController(ArrayList<WindowContent> windowContent){
        this.leftWindow = new VerticalScrollbarDecorator(new SimpleWindow(0,0, PALETTEWIDTH, WINDOWHEIGHT, windowContent.get(0)));
        this.middleWindow = new VerticalScrollbarDecorator(new SimpleWindow(PALETTEWIDTH + VerticalScrollbarDecorator.SCROLLBARWIDTH, 0, PROGRAMAREAWIDTH, WINDOWHEIGHT, windowContent.get(1)));
        this.rightWindow = new VerticalScrollbarDecorator(new SimpleWindow(PALETTEWIDTH + PROGRAMAREAWIDTH + 2*VerticalScrollbarDecorator.SCROLLBARWIDTH, 0, GAMEWORLDWIDTH, WINDOWHEIGHT, windowContent.get(2)));
    }

    /**
     * Method to render the UI elements
     * @param g Graphics object
     * @author Oberon Swings
     */
    public void render(Graphics g){
        leftWindow.render(g);
        middleWindow.render(g);
        rightWindow.render(g);
    }

    /**
     * Delegates the contents of the controller to handle their own events.
     * @param id of the mouseEvent
     * @param x coordinate of the event
     * @param y coordinate of the event
     */
    public void handleMouseEvent(int id, int x, int y){
        if (x < PALETTEWIDTH + VerticalScrollbarDecorator.SCROLLBARWIDTH) {
            leftWindow.handleMouseEvent(id, x, y);
            middleWindow.deactivateScroll();
            rightWindow.deactivateScroll();
        }
        else if (x < PALETTEWIDTH + PROGRAMAREAWIDTH + 2*VerticalScrollbarDecorator.SCROLLBARWIDTH){
            middleWindow.handleMouseEvent(id, x, y);
            leftWindow.deactivateScroll();
            rightWindow.deactivateScroll();
        }
        else {
            rightWindow.handleMouseEvent(id, x, y);
            middleWindow.deactivateScroll();
            leftWindow.deactivateScroll();
        }
    }
}