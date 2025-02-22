package ui.window;

import java.awt.*;

public class VerticalScrollbarDecorator implements Window {

    public static final int SCROLLBARWIDTH = 20;
    public static final int PAGESIZE = 30;
    public static final int MARGE = 50;

    private Window windowToDecorate;
    private int verticalOffset; //The amount of vertical pixels of the content that are above the top edge of the window.
    private boolean scrollActive;
    private int prevY;

    private int height;

    public VerticalScrollbarDecorator(Window windowToDecorate, int height) {
        this.windowToDecorate = windowToDecorate;
        this.height = height;
        this.verticalOffset = 0;
    }

    /**
     * @param x coordinate of location
     * @param y coordinate of location
     * @return true if location is not left of scrollbar, not above and not below scrollbar, false otherwise.
     */
    private boolean onScrollBar(int x, int y){
        if (leftOfScrollBar(x, y)) return false;
        if (contentFitsWindow()) return true;
        return (!(underScrollBar(x, y) || aboveScrollBar(x, y)));
    }

    /**
     * @param x coordinate of location
     * @param y coordinate of location
     * @return true if not left of scrollbar and location is above the scrollbar, false otherwise
     */
    private boolean aboveScrollBar(int x, int y){
        if (leftOfScrollBar(x, y)) return false;
        return y < verticalOffset * getRelativeFraction();
    }

    /**
     * @param x coordinate of location
     * @param y coordinate of location
     * @return true if not left of scrollbar and location is underneath the scrollbar, false otherwise
     */
    private boolean underScrollBar(int x, int y){
        if (leftOfScrollBar(x, y)) return false;
        return y > (verticalOffset + height * getRelativeFraction());
    }

    /**
     * @param x coordinate of location
     * @param y coordinate of location
     * @return true if location is left of scrollbar, false otherwise
     */
    private boolean leftOfScrollBar(int x, int y){
        return (x < windowToDecorate.getX() + windowToDecorate.getWidth());
    }

    /**
     * @return true if the content height is smaller than the window height, false otherwise
     */
    public boolean contentFitsWindow(){
        return (windowToDecorate.getHeight() + MARGE < height);
    }

    /**
     * @return the fraction of the window height divided by the content height
     */
    public float getRelativeFraction(){
        if (contentFitsWindow()) return 1;
        return ((float)height/(windowToDecorate.getHeight() + MARGE));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(Graphics g) {
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(getLeftEdgeScrollBar(), getY(), SCROLLBARWIDTH, getHeight());
        g.setColor(Color.DARK_GRAY);
        g.fillRect(getLeftEdgeScrollBar(), (int)(verticalOffset * getRelativeFraction()), SCROLLBARWIDTH, getScrollbarHeight());
        g.translate(0, -verticalOffset); //I hope this makes the windowToDecorate render it's content according to the scrolled distance.
        windowToDecorate.render(g);
        g.translate(0, verticalOffset); //This resets the graphics translation to normal, again I hope so.
    }

    /**
     * @return x coordinate of the left edge of the scrollbar
     */
    private int getLeftEdgeScrollBar(){
        return getX() + getWidth() - SCROLLBARWIDTH;
    }

    /**
     * @return height of scrollbar
     */
    private int getScrollbarHeight(){
        return (int)(height * getRelativeFraction());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getY() {
        return windowToDecorate.getY();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getX() {
        return windowToDecorate.getX();
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
        return windowToDecorate.getWidth() + SCROLLBARWIDTH;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handleMouseEvent(int id, int x, int y) {
        if(x > windowToDecorate.getX() + windowToDecorate.getWidth() || scrollActive){
            switch(id){
                case 501: //MOUSE_PRESSED
                    if (onScrollBar(x, y)) {
                        scrollActive = true;
                        prevY = y;
                    }
                    if (aboveScrollBar(x, y)) verticalOffset -= PAGESIZE;
                    if (underScrollBar(x, y)) verticalOffset += PAGESIZE;
                    break;
                case 502: //MOUSE_RELEASED
                    scrollActive = false;
                    prevY = 0;
                    break;
                case 506: //MOUSE_DRAGGED
                    if(scrollActive){
                        verticalOffset += (y - prevY)/getRelativeFraction();
                        prevY = y;
                    }
                    break;
                default:
                    break;
            }
            verticalOffset = Math.max(0, Math.min(windowToDecorate.getHeight() - height + MARGE, verticalOffset));
            if (windowToDecorate.getHeight() == 0) verticalOffset = 0;
        }
        else{
            windowToDecorate.handleMouseEvent(id, x, y + verticalOffset); //The event is translated according to the scrolled distance.
        }
    }

    /**
     * deactives the scroll functionality
     */
    public void deactivateScroll(){
        scrollActive = false;
    }
}
