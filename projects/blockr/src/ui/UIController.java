package ui;

import java.util.ArrayList;
import java.awt.*;

import main.MyCanvasWindow;
import model.blocks.ModelBlock;
import ui.window.Content;
import ui.window.SimpleWindow;
import ui.window.VerticalScrollbarDecorator;
import utilities.*;
import model.*;

/**
 * Class controlling the UI elements.
 */
public class UIController {

    private VerticalScrollbarDecorator palette;
    private VerticalScrollbarDecorator programArea;

    public final static int PALETTEWIDTH = 300;
    public final static int PROGRAMAREAWIDTH = 500;
    public final static int WINDOWHEIGHT = 1000;


    // Constructor
    public UIController(ModelProgramArea pArea, ModelPalette palette){
        this.palette = new VerticalScrollbarDecorator(new SimpleWindow(0,0, PALETTEWIDTH, WINDOWHEIGHT, palette));
        this.programArea = new VerticalScrollbarDecorator(new SimpleWindow(PALETTEWIDTH + VerticalScrollbarDecorator.SCROLLBARWIDTH, 0, PALETTEWIDTH + PROGRAMAREAWIDTH + VerticalScrollbarDecorator.SCROLLBARWIDTH, WINDOWHEIGHT, pArea));
    }

    /**
     * Method to render the UI elements
     * @param g Graphics object
     * @param active the block states that need to be rendered
     * @author Oberon Swings
     */
    public void render(Graphics g, ModelBlock active){
        palette.render(g);
        programArea.render(g);
        if (active != null){
            UIBlock.render(g, new BlockState(active));
        }
        //this.renderUI(g);
//        for (BlockState block : blocks){
//            uiBlock.render(g, block);
//        }
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

    public void handleMouseEvent(int id, int x, int y){
        if (x < PALETTEWIDTH + VerticalScrollbarDecorator.SCROLLBARWIDTH) palette.handleMouseEvent(id, x, y);
        else programArea.handleMouseEvent(id, x, y);
    }
}