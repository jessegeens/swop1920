package ui.window;

import gameworldapi.GameWorld;
import main.GlobalController;

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
        g.translate(GlobalController.CONTROLS_WIDTH, 0);
        gameWorld.render(g);
        g.translate((-1)*(GlobalController.CONTROLS_WIDTH), 0);
    }

    public void handleMouseEvent(int id, int x, int y) {
    }
}
