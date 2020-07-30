package ui.window;

import ui.BlockState;
import ui.UIBlock;

import java.awt.*;
import java.util.ArrayList;

public class ModelWindowMediator implements WindowMediator{

    private ModelListener model;


    public ModelWindowMediator(ModelListener model) {
        this.model = model;
    }

    public int getHeight() {
        return model.getHeight();
    }

    public void render(Graphics g) {
        ArrayList<BlockState> drawables = model.getDrawables();
        for (BlockState blockState : drawables) {
            UIBlock.render(g, blockState);
        }
    }

    public void handleMouseEvent(int id, int x, int y) {
        switch(id){
            case 501: //MOUSE_PRESSED
                model.onClick(x, y);
                break;
            case 502: //MOUSE_RELEASED
                model.onRelease(x, y);
                break;
            case 506: //MOUSE_DRAGGED
                model.onDrag(x, y);
                break;
            default:
                break;
        }
    }
}
