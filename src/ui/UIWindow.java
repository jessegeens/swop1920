package ui;
import java.awt.*;

/**
 * Interface representing the physical window.
 */
interface UIWindow {

    //TODO width and height currently only matter in canvaswindow
    final int width = 200;
    final int height = 600;

    /**
     * Method that describes how an element gets rendered in the UIWindow.
     * @param g the graphical part to be rendered.
     */
    public void render(Graphics g);
}