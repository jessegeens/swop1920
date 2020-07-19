package ui.window;

import java.awt.*;

public class VerticalScrollbarDecorator implements Window {

    public static final int SCROLLBARWIDTH = 20;
    public static final int PAGESIZE = 30;
    public static final int MARGE = 50;

    private Window windowToDecorate;
    private int verticaleOffset; //The amount of vertical pixels of the content that are above the top edge of the window.
    private boolean scrollActive;
    private int prevY;

    public VerticalScrollbarDecorator(Window windowToDecorate) {
        this.windowToDecorate = windowToDecorate;
        this.verticaleOffset = 0;
    }

    private boolean onScrollBar(int x, int y){
        if (leftOfScrollBar(x, y)) return false;
        if (contentFitsWindow()) return true;
        return (!(underScrollBar(x, y) || aboveScrollBar(x, y)));
    }

    private boolean aboveScrollBar(int x, int y){
        if (leftOfScrollBar(x, y)) return false;
        return y < verticaleOffset * getRelativeFraction();
    }

    private boolean underScrollBar(int x, int y){
        if (leftOfScrollBar(x, y)) return false;
        return y > (verticaleOffset + getHeight()) * getRelativeFraction();
    }

    private boolean leftOfScrollBar(int x, int y){
        return (x < windowToDecorate.getX() + windowToDecorate.getWidth());
    }

    public boolean contentFitsWindow(){
        return windowToDecorate.getContent().getHeight() + MARGE < windowToDecorate.getHeight();
    }

    public float getRelativeFraction(){
        if (contentFitsWindow()) return 1;
        return ((float)windowToDecorate.getHeight()/(windowToDecorate.getContent().getHeight() + MARGE));
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(getLeftEdgeScrollBar(), getY(), SCROLLBARWIDTH, getHeight());
        g.setColor(Color.DARK_GRAY);
        g.fillRect(getLeftEdgeScrollBar(), (int)(verticaleOffset * getRelativeFraction()), SCROLLBARWIDTH, getScrollbarHeight());
        g.translate(0, -verticaleOffset); //I hope this makes the windowToDecorate render it's content according to the scrolled distance.
        windowToDecorate.render(g);
        g.translate(0 , verticaleOffset); //This resets the graphics translation to normal, again I hope so.
    }

    private int getLeftEdgeScrollBar(){
        return getX() + getWidth() - SCROLLBARWIDTH;
    }

    private int getScrollbarHeight(){
        return (int)(getHeight() * getRelativeFraction());
    }

    @Override
    public int getY() {
        return windowToDecorate.getY();
    }

    @Override
    public int getX() {
        return windowToDecorate.getX();
    }

    @Override
    public int getHeight() {
        return windowToDecorate.getHeight();
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
        if(x > windowToDecorate.getX() + windowToDecorate.getWidth() || scrollActive){
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
            verticaleOffset = Math.max(0, Math.min(getContent().getHeight() - getHeight() + MARGE,verticaleOffset));
        }
        else{
            windowToDecorate.handleMouseEvent(id, x, y + verticaleOffset); //The event is translated according to the scrolled distance.
        }
    }

    public void deactivateScroll(){
        scrollActive = false;
    }
}
