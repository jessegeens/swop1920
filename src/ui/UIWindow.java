package ui;
import java.awt.*;

interface UIWindow {

    //TODO width and height currently only matter in canvaswindow
    final int width = 200;
    final int height = 600;

    public void render(Graphics g);
}