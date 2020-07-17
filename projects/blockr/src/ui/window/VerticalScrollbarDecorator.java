package ui.window;

import java.awt.*;

public class VerticalScrollbarDecorator implements Window {

    public static final int SCROLLBARWIDTH = 20;

    private Window windowToDecorate;
    private int verticaleOffset;

    public VerticalScrollbarDecorator(Window windowToDecorate) {
        this.windowToDecorate = windowToDecorate;
    }

    public int getVerticaleOffset(){
        return verticaleOffset;
    }

    @Override
    public void render(Graphics g) {

    }

    @Override
    public int getTopEdge() {
        return windowToDecorate.getTopEdge();
    }

    @Override
    public int getLeftEdge() {
        return windowToDecorate.getLeftEdge();
    }

    @Override
    public int getHeigth() {
        return windowToDecorate.getHeigth();
    }

    @Override
    public int getWidth() {
        return windowToDecorate.getWidth() + SCROLLBARWIDTH;
    }

    @Override
    public void handleMouseEvent(int id, int x, int y) {

    }
}
