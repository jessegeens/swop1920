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

    public boolean contentFitsWindow(){
        return content.getHeight() < height;
    }

    @Override
    public void render(Graphics g) {

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
    public void handleMouseEvent(int id, int x, int y) {

    }
}
