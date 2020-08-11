package ui.window;

import java.awt.*;

public class SimpleWindow implements Window {

    private int y;
    private int x;
    private int width;

    private final WindowMediator windowMediator;

    /**
     * Constructor
     * @param x coordinate of window
     * @param y coordinate of window
     * @param width of window
     * @param windowMediator of window
     */
    public SimpleWindow(int x, int y, int width, WindowMediator windowMediator) {
        this.y = y;
        this.x = x;
        this.width = width;
        this.windowMediator = windowMediator;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(Graphics g) {
        windowMediator.render(g);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getY() {
        return y;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getX() {
        return x;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getHeight() {
        return windowMediator.getHeight();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getWidth() {
        return width;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handleMouseEvent(int id, int x, int y) {
        windowMediator.handleMouseEvent(id, x, y);
    }
}
