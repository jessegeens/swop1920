package ui;

import java.awt.*;
import java.util.ArrayList;
import ui.blocks.*;

public class UIController {

    private ArrayList<UIElement> uiElements;

    

    public UIController(){
        
    }

    public void renderUIlements(Graphics g, Rectangle uiBounds){
        System.out.println("renderUI");
        uiElements.forEach((UIElement) -> (UIElement).render(g));

    }

    public void handleMouseEvent(int id, int x, int y, int clickCount){
        System.out.println("mouse");
        UIElement block = new UIMoveBlock();
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