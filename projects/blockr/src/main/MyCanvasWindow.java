
package main;

import java.awt.Graphics;

public class MyCanvasWindow extends CanvasWindow {

    public static final int WIDTH = 900;
    public static final int HEIGHT = 900;

    private GlobalController globalController;

    protected MyCanvasWindow(String title) {
        super(title);
        globalController = new GlobalController();
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
    protected void handleKeyEvent(int id, int keyCode, char keyChar) {
        super.handleKeyEvent(id, keyCode, keyChar);
        globalController.handleKeyEvent(id, keyCode, keyChar);
        super.repaint();
    }
}