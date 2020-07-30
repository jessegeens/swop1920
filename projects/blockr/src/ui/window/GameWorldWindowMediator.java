package ui.window;

import gameworldapi.GameWorld;
import ui.UIController;

import java.awt.*;

public class GameWorldWindowMediator implements WindowMediator{

    private GameWorld gameWorld;


    public GameWorldWindowMediator(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }

    public int getHeight() {
        return gameWorld.getHeight();
    }

    public void render(Graphics g) {
        g.translate(UIController.PALETTEWIDTH + UIController.PROGRAMAREAWIDTH + 2*VerticalScrollbarDecorator.SCROLLBARWIDTH, 0);
        gameWorld.render(g);
        g.translate((-1)*(UIController.PALETTEWIDTH + UIController.PROGRAMAREAWIDTH + 2*VerticalScrollbarDecorator.SCROLLBARWIDTH), 0);
    }

    public void handleMouseEvent(int id, int x, int y) {
    }
}
