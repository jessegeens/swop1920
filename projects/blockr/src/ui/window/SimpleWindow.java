package ui.window;

import java.awt.*;

public class SimpleWindow implements Window {

    private int y;
    private int x;
    private int width;
    private int height;
    private Content content;

    public SimpleWindow(int x, int y, int width, int height, Content content) {
        this.y = y;
        this.x = x;
        this.width = width;
        this.height = height;
        this.content = content;
    }

    @Override
    public void render(Graphics g) {
        g.drawLine(x, y, x, height);
        g.drawLine(x + width, y, x + width, height);
        content.render(g);
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public Content getContent() {
        return content;
    }

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
