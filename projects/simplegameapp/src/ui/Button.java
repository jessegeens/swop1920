package ui;

import gameworldapi.ActionType;

import java.awt.*;

public class Button {

    private int x;
    private int y;
    private static int STDWIDTH = 150;
    private static int STDHEIGHT = 50;
    private ActionType action;

    public Button(int x, int y, ActionType action) {
        this.x = x;
        this.y = y;
        this.action = action;
    }

    public boolean inBounds(int x, int y) {
        return (x > this.x && x < this.x + STDWIDTH && y > this.y && y < this.y + STDHEIGHT);
    }

    public void render(Graphics g) {
        g.setColor(Color.ORANGE);
        g.fillRect(this.x, this.y, STDWIDTH, STDHEIGHT);
        g.setColor(Color.BLACK);
        g.drawString(action.toString(), x + 10, y + 10);
    }

    public ActionType getAction() {
        return action;
    }
}
