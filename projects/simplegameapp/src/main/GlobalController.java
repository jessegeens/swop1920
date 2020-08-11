package main;

import gameworldapi.ActionType;
import gameworldapi.GameWorld;
import gameworldapi.GameWorldType;

import java.awt.*;
import java.util.ArrayList;
import ui.Button;
import ui.window.GameWorldWindowMediator;
import ui.window.SimpleWindow;
import ui.window.VerticalScrollbarDecorator;
import ui.window.WindowMediator;

public class GlobalController {

    private ArrayList<Button> buttons = new ArrayList<>();
    private VerticalScrollbarDecorator gameWorldDecorator;
    private GameWorld gameWorld;

    public final static int GAMEWORLDWIDTH = 250;
    public final static int WINDOWHEIGHT = 900;
    public static final int CONTROLS_WIDTH = 630;

    public GlobalController(GameWorldType gameWorldType) {
        GameWorld gameWorld = gameWorldType.newWorldInstance();
        this.gameWorld = gameWorld;
        WindowMediator gameWorldMediator = new GameWorldWindowMediator(gameWorld);
        this.gameWorldDecorator = new VerticalScrollbarDecorator(new SimpleWindow(CONTROLS_WIDTH, 0, GAMEWORLDWIDTH, gameWorldMediator), WINDOWHEIGHT);
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
        if (x < CONTROLS_WIDTH) {
            for (Button button : buttons) {
                if (button.inBounds(x, y)) {
                    gameWorld.perform(button.getAction());
                }
            }
        } else {
            gameWorldDecorator.handleMouseEvent(id, x, y);
        }

    }

    public void render(Graphics g) {
        gameWorldDecorator.render(g);
        for (Button button : buttons) {
            button.render(g);
        }
    }
}
