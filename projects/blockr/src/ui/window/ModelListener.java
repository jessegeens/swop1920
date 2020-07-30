package ui.window;

import ui.BlockState;

import java.util.ArrayList;

public interface ModelListener {

    /**
     * Enables the content to communicate it's drawables to the window
     * @return an arraylist of objects which should be able to be drawn by the window.
     */
    ArrayList<BlockState> getDrawables();

    int getHeight();

    void onClick(int x, int y);
    void onDrag(int x, int y);
    void onRelease(int x, int y);
}
