package ui;

import java.awt.*;
import java.util.ArrayList;
import ui.blocks.*;
import utilities.*;


public class UIController {

    private ArrayList<UIElement> uiElements = new ArrayList<UIElement>();

    

    public UIController(){

        
    }

    public void renderUIElements(Graphics g, Rectangle uiBounds){

        Location wall1 = new Location(2,2);
        Location wall2 = new Location(1, 2);

        ArrayList<Location> walls = new ArrayList<Location>();
    
        walls.add(wall1);
        walls.add(wall2);

        Location robot = new Location(0, 0);
        Direction robotDirection = new Direction(Direction.LEFT);

        Location goal = new Location(3,3);



        UIGrid grid = new UIGrid(50, 5, walls, robot, robotDirection, goal);

        grid.render(g);

        

        System.out.println("renderUI");
        uiElements.forEach((UIElement) -> (UIElement).render(g));

    }

    public void handleMouseEvent(int id, int x, int y, int clickCount){
        System.out.println("mouse");
        Location clickLocation = new Location(x, y);
        Blocktype block_ = new Blocktype(Blocktype.IF);
        UIElement block = new UIBlock(clickLocation, block_);
        uiElements.add(block);

    }

    public void handleKeyEvent(int id, int keyCode, char keyChar){
        System.out.println("key");

        

        //uiElements.add(grid);

    }

    public ArrayList<UIElement> getUiElements() {
        return this.uiElements;
    }

    public void setUiElements(ArrayList<UIElement> uiElements) {
        this.uiElements = uiElements;
    }
    
}