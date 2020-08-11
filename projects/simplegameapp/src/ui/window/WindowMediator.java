package ui.window;

import java.awt.*;

public interface WindowMediator {

    void render(Graphics g);

    int getHeight();

    void handleMouseEvent(int id, int x, int y);
}
