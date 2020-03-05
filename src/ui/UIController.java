package ui;

import java.awt.*;
import java.util.ArrayList;
import ui.blocks.*;
import utilities.Location;

public class UIController {

    private ArrayList<UIElement> uiElements = new ArrayList<UIElement>();

    

    public UIController(){
        
    }

    public void renderUIElements(Graphics g, Rectangle uiBounds){
        System.out.println("renderUI");
        uiElements.forEach((UIElement) -> (UIElement).render(g));

    }

    public void handleMouseEvent(int id, int x, int y, int clickCount){
        System.out.println("mouse");
        Location clickLocation = new Location(x, y);
        UIElement block = new UIWhileBlock(clickLocation);
        uiElements.add(block);

    }

    public void handleKeyEvent(int id, int keyCode, char keyChar){
        System.out.println("key");

    }

    public ArrayList<UIElement> getUiElements() {
        return this.uiElements;
    }

    public void setUiElements(ArrayList<UIElement> uiElements) {
        this.uiElements = uiElements;
    }
    
}