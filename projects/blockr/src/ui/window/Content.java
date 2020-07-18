package ui.window;

public interface Content {

    public static final int MARGE = 50;

    int getHeight();

    void onClick(int x, int y);

    void onDrag(int x, int y);

    void onRelease(int x, int y);

}
