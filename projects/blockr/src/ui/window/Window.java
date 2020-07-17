package ui.window;

import java.awt.*;

public interface Window {

    void render(Graphics g);

    int getTopEdge();
    int getLeftEdge();
    int getHeigth();
    int getWidth();

    void handleMouseEvent(int id, int x, int y);


}
