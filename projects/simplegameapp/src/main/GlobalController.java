package main;

import gameworldapi.ActionType;
import gameworldapi.GameWorld;
import gameworldapi.GameWorldType;

import java.awt.*;
import java.util.ArrayList;
import ui.Button;

public class GlobalController {

    private ArrayList<Button> buttons = new ArrayList<>();
    private GameWorld gameWorld;

    public GlobalController(GameWorldType gameWorldType) {
        gameWorld = gameWorldType.newWorldInstance();
        int i = 0;
        for (ActionType action : gameWorldType.getSupportedActions()) {
            Button button = new Button(20, 20+80*i, action);
            buttons.add(button);
            i++;
        }
    }

    public void handleMouseEvent(int id, int x, int y, int clickCount) {
        if (id != 501) {
            return;
        }
        for (Button button : buttons) {
            if (button.inBounds(x, y)) {
                gameWorld.perform(button.getAction());
            }
        }
    }

    public void render(Graphics g) {
        gameWorld.render(g, 300, 10);
        for (Button button : buttons) {
            button.render(g);
        }
    }
}
