package ui.window;

import gameworldapi.GameWorld;
import ui.BlockState;
import ui.UIBlock;

import java.awt.*;
import java.util.ArrayList;

public class SimpleWindow implements Window {

    private int y;
    private int x;
    private int width;
    private int height;
    private WindowContent windowContent;

    /**
     * Constructor
     * @param x coordinate of window
     * @param y coordinate of window
     * @param width of window
     * @param height of window
     * @param windowContent of window
     */
    public SimpleWindow(int x, int y, int width, int height, WindowContent windowContent) {
        this.y = y;
        this.x = x;
        this.width = width;
        this.height = height;
        this.windowContent = windowContent;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(Graphics g) {
        ArrayList<Object> drawables = windowContent.getDrawables();
        if (drawables.isEmpty()) return;
        if (drawables.get(0) instanceof GameWorld){
            ((GameWorld) drawables.get(0)).render(g);
        }
        else if (drawables.get(0) instanceof BlockState){
            for (Object blockState : drawables){
                if (blockState instanceof BlockState) UIBlock.render(g, (BlockState) blockState);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getY() {
        return y;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getX() {
        return x;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getHeight() {
        return height;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getWidth() {
        return width;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WindowContent getWindowContent() {
        return windowContent;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handleMouseEvent(int id, int x, int y) {
        if (!(windowContent instanceof InteractableWindowContent)) return;
        switch(id){
            case 501: //MOUSE_PRESSED
                ((InteractableWindowContent)windowContent).onClick(x, y);
                break;
            case 502: //MOUSE_RELEASED
                ((InteractableWindowContent)windowContent).onRelease(x, y);
                break;
            case 506: //MOUSE_DRAGGED
                ((InteractableWindowContent)windowContent).onDrag(x, y);
                break;
            default:
                break;
        }
    }
}
