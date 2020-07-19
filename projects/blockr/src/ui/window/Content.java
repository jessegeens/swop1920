package ui.window;

import java.awt.*;

public interface Content {

    int getHeight();

    void onClick(int x, int y);

    void onDrag(int x, int y);

    void onRelease(int x, int y);

    void render(Graphics g);

}
