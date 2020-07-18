package ui.window;

import java.awt.*;

public class SimpleWindow implements Window {

    private int topEdge;
    private int leftEdge;
    private int width;
    private int height;
    private Content content;

    public SimpleWindow(int x, int y, int width, int height, Content content) {
        this.topEdge = x;
        this.leftEdge = y;
        this.width = width;
        this.height = height;
        this.content = content;
    }

    @Override
    public void render(Graphics g) {
        g.drawLine(leftEdge, topEdge, leftEdge, height);
        g.drawLine(leftEdge + width, topEdge, leftEdge + width, height);
        content.render(g);
    }

    @Override
    public int getTopEdge() {
        return topEdge;
    }

    @Override
    public int getLeftEdge() {
        return leftEdge;
    }

    @Override
    public int getHeigth() {
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
