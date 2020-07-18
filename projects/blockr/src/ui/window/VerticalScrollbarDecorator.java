package ui.window;

import java.awt.*;

public class VerticalScrollbarDecorator implements Window {

    public static final int SCROLLBARWIDTH = 20;
    public static final int PAGESIZE = 30;

    private Window windowToDecorate;
    private int verticaleOffset;
    private boolean scrollActive;
    private int prevY;

    public VerticalScrollbarDecorator(Window windowToDecorate) {
        this.windowToDecorate = windowToDecorate;
    }

    private boolean onScrollBar(int x, int y){
        if (leftOfScrollBar(x, y)) return false;
        if (contentFitsWindow()) return true;
        return (!(underScrollBar(x, y) || aboveScrollBar(x, y)));
    }

    private boolean aboveScrollBar(int x, int y){
        if (leftOfScrollBar(x, y)) return false;
        return y < verticaleOffset;
    }

    private boolean underScrollBar(int x, int y){
        if (leftOfScrollBar(x, y)) return false;
        return y > verticaleOffset + getHeigth()*getRelativeHeight();
    }

    private boolean leftOfScrollBar(int x, int y){
        return (x < windowToDecorate.getLeftEdge() + windowToDecorate.getWidth());
    }

    public boolean contentFitsWindow(){
        return windowToDecorate.getContent().getHeight() + Content.MARGE < windowToDecorate.getHeigth();
    }

    public float getRelativeHeight(){
        return (float)(windowToDecorate.getHeigth()/(windowToDecorate.getContent().getHeight() + Content.MARGE));
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(getLeftEdgeScrollBar(), getTopEdge(), SCROLLBARWIDTH, getHeigth());
        g.setColor(Color.DARK_GRAY);
        g.fillRect(getLeftEdgeScrollBar(), verticaleOffset, SCROLLBARWIDTH, getScrollbarHeight());
        g.translate(0, -verticaleOffset); //I hope this makes the windowToDecorate render it's content according to the scrolled distance.
        windowToDecorate.render(g);
        g.translate(0 , verticaleOffset); //This resets the graphics translation to normal, again I hope so.
    }

    private int getLeftEdgeScrollBar(){
        return getLeftEdge() + getWidth() - SCROLLBARWIDTH;
    }

    private int getScrollbarHeight(){
        return (int)(getHeigth()*getRelativeHeight());
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
    public Content getContent() {
        return windowToDecorate.getContent();
    }

    @Override
    public void handleMouseEvent(int id, int x, int y) {
        if(x > windowToDecorate.getLeftEdge() + windowToDecorate.getWidth()){
            switch(id){
                case 501: //MOUSE_PRESSED
                    if (onScrollBar(x, y)) {
                        scrollActive = true;
                        prevY = y;
                    }
                    if (aboveScrollBar(x, y)) verticaleOffset -= PAGESIZE;
                    if (underScrollBar(x, y)) verticaleOffset += PAGESIZE;
                    break;
                case 502: //MOUSE_RELEASED
                    scrollActive = false;
                    prevY = 0;
                    break;
                case 506: //MOUSE_DRAGGED
                    if(scrollActive){
                        verticaleOffset += y - prevY;
                        prevY = y;
                    }
                    break;
                default:
                    break;
            }
        }
        else windowToDecorate.handleMouseEvent(id, x, y);
    }
}
