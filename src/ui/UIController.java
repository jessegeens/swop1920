package ui;


import java.util.ArrayList;
import java.awt.*;



import ui.blocks.*;
import utilities.*;
import model.*;


public class UIController {

    private ArrayList<UIElement> uiElements = new ArrayList<UIElement>();
    private ArrayList<ModelElement> modelElements = new ArrayList<ModelElement>();

    

    public UIController(){

        
    }

    public void renderUIElements(Graphics g, Rectangle uiBounds){

        //TODO why is the UIgrid not a UIElement?

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

        Location randomLocation = new Location(300, 300);

        Blocktype block_ = new Blocktype(Blocktype.IF);



        UIElement block = new UIBlock(randomLocation, block_);
        uiElements.add(block);

        

        

        System.out.println("renderUI");
        uiElements.forEach((UIElement) -> (UIElement).render(g));

    }

    //traverse uielements in opposite direction to have top rendering order

    ModelElement active = null;

    public void handleMouseEvent(int id, int x, int y, int clickCount){
        Location eventLocation = new Location(x,y);
        System.out.println("mouse");

        //TODO for some reason I can't use the static fields MouseEvent.MOUSE_PRESSED etc
        //TODO explaining why the list should be traversed in reversed due to render order
        if(id == 501){
            
            for (int i = modelElements.size() - 1; i >= 0; i--) {
                if(modelElements.get(i).inBounds(eventLocation)){
                    active = modelElements.get(i);
                }
            }            
        }
        else if(id == 502){
            this.active = null;

        }
        else if(id == 506){
            
            this.active.move(eventLocation);
            
            //update pos of activelement
        }




        

        //left window
        //mousedown

        //mouseup



        //mousedown

        

        
        //MOUSE_PRESSED where you start holding the button down 501
        //MOUSE_RELEASED where you release the button      502  
        //MOUSE_CLICKED => press + release (comes after released + pressed) only comes if no dragging happended 500
        //MOUSE_DRAGGED => Holding down, gets triggerd after each small move 506
        //interesting to know: there is no difference detected between left and right button in the current handlemouseevent function


        

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