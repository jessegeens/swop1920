package ui.window;

public interface Content {

    public static final int MARGE = 50;

    int getHeight();

    void onClick(int x, int y);

    void onDrag(int x1, int y1, int x2, int y2);

    void onRelease(int x, int y);

}
