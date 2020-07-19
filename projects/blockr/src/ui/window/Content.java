package ui.window;

import java.awt.*;

public interface Content {

    /**
     *
     * @return the height of the content in pixels
     */
    int getHeight();

    /**
     * handles click event
     * @param x coordinate of event
     * @param y coordinate of event
     */
    void onClick(int x, int y);

    /**
     * handles drag event
     * @param x coordinate of event
     * @param y coordinate of event
     */
    void onDrag(int x, int y);

    /**
     * handles release event
     * @param x coordinate of event
     * @param y coordinate of event
     */
    void onRelease(int x, int y);

    /**
     * renders the content
     * @param g Graphics object
     */
    void render(Graphics g);

}
