package ui;

import java.awt.*;

public class UIController {

    public UIController(){
        
    }

    public void renderUIlements(Graphics g, Rectangle uiBounds){
        System.out.println("renderUI");

    }

    public void handleMouseEvent(int id, int x, int y, int clickCount){
        System.out.println("mouse");

    }

    public void handleKeyEvent(int id, int keyCode, char keyChar){
        System.out.println("key");

    }
    
}