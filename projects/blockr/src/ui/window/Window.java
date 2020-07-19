package ui.window;

import java.awt.*;

public interface Window {

    void render(Graphics g);

    int getY();
    int getX();
    int getHeight();
    int getWidth();

    Content getContent();

    void handleMouseEvent(int id, int x, int y);


}
