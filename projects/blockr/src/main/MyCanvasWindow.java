
package main;

import gameworldapi.*;
import ui.UIController;

import java.awt.Graphics;

public class MyCanvasWindow extends CanvasWindow {

    public static final int WIDTH = UIController.PALETTEWIDTH + UIController.PROGRAMAREAWIDTH + 250;
    public static final int HEIGHT = UIController.WINDOWHEIGHT;

    private GlobalController globalController;

    protected MyCanvasWindow(String title, GameWorldType gameWorldType) {
        super(title);
        globalController = new GlobalController(gameWorldType);
    }

    @Override
    protected void paint(Graphics g) {
        this.setSize(MyCanvasWindow.WIDTH, MyCanvasWindow.HEIGHT);
        globalController.render(g);
        super.paint(g);
    }

    protected void setSize(int width, int height){
        super.width = width;
        super.height = height;
    }

    @Override
    protected void handleMouseEvent(int id, int x, int y, int clickCount) {
        super.handleMouseEvent(id, x, y, clickCount);
        globalController.handleMouseEvent(id, x, y, clickCount);
        super.repaint();
    }
    
    @Override
    protected void handleKeyEvent(int id, int keyCode, char keyChar, boolean isControlDown, boolean isShiftDown) {
        super.handleKeyEvent(id, keyCode, keyChar);
        globalController.handleKeyEvent(id, keyCode, keyChar, isControlDown, isShiftDown);
        super.repaint();
    }
}