package ui.window;

import java.awt.*;

public class SimpleWindow implements Window {

    private int y;
    private int x;
    private int width;
    private int height;
    private Content content;

    /**
     * Constructor
     * @param x coordinate of window
     * @param y coordinate of window
     * @param width of window
     * @param height of window
     * @param content of window
     */
    public SimpleWindow(int x, int y, int width, int height, Content content) {
        this.y = y;
        this.x = x;
        this.width = width;
        this.height = height;
        this.content = content;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(Graphics g) {
        g.drawLine(x, y, x, height);
        g.drawLine(x + width, y, x + width, height);
        content.render(g);
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
        return height;
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
    public Content getContent() {
        return content;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handleMouseEvent(int id, int x, int y) {
        switch(id){
            case 501: //MOUSE_PRESSED
                content.onClick(x, y);
                break;
            case 502: //MOUSE_RELEASED
                content.onRelease(x, y);
                break;
            case 506: //MOUSE_DRAGGED
                content.onDrag(x, y);
                break;
            default:
                break;
        }
    }
}
