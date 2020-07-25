package ui.window;


import java.util.ArrayList;

public interface WindowContent {

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
     * Enables the content to communicate it's drawables to the window
     * @return an arraylist of objects which should be able to be drawn by the window.
     */
    ArrayList<Object> getDrawables();

}
