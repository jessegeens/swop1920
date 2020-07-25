package ui.window;

import java.awt.*;

public interface Window {

    /**
     * Renders the window and calls the content to render itself
     * @param g Graphics object to render
     */
    void render(Graphics g);

    /**
     * @return the y coordinate
     */
    int getY();

    /**
     * @return the x coordinate
     */
    int getX();

    /**
     * @return the height
     */
    int getHeight();

    /**
     * @return the width
     */
    int getWidth();

    /**
     * @return the content
     */
    WindowContent getWindowContent();

    /**
     * Handle the mouse event in the window
     * @param id of the event
     * @param x coordinate of the event
     * @param y coordinate of the event
     */
    void handleMouseEvent(int id, int x, int y);


}
