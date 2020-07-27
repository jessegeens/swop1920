package ui.window;


import java.util.ArrayList;

public interface WindowContent {

    /**
     *
     * @return the height of the content in pixels
     */
    int getHeight();

    /**
     * Enables the content to communicate it's drawables to the window
     * @return an arraylist of objects which should be able to be drawn by the window.
     */
    ArrayList<Object> getDrawables();

}
