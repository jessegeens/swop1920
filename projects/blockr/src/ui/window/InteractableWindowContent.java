package ui.window;

public interface InteractableWindowContent extends WindowContent{

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
}
