package ui.window;

import java.util.ArrayList;

public class SimpleContent implements WindowContent {

    private int height;
    private ArrayList<Object> drawables;

    public SimpleContent(int height, ArrayList<Object> drawables) {
        this.height = height;
        this.drawables = drawables;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void onClick(int x, int y) {
    }

    @Override
    public void onDrag(int x, int y) {
    }

    @Override
    public void onRelease(int x, int y) {
    }

    @Override
    public ArrayList<Object> getDrawables() {
        return drawables;
    }
}
