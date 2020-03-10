


import java.util.ArrayList;
import java.awt.*;



import ui.*;
import ui.blocks.*;
import utilities.*;
import model.*;

class GlobalController {
//on creation provide instructions to modelcontroller
//modelcontroller makes the three windows and manages them

//create ui representation and handle it

//all event handling starts here

    private ModelController modelController;
    private UIController uiController;

    static final int DESIGN_MODE = 0;
    static final int RUNNING_MODE = 1;

    int currentMode;


    

    public GlobalController(){
        this.modelController = new ModelController();
        this.uiController = new UIController();  
        //TODO proper setter method
        this.currentMode = DESIGN_MODE;
             
    }



    private ArrayList<UIElement> uiElements = new ArrayList<UIElement>();
    private ArrayList<ModelElement> modelElements = new ArrayList<ModelElement>();


    //traverse uielements in opposite direction to have top rendering order

    

    public void handleMouseEvent(int id, int x, int y, int clickCount){
        Location eventLocation = new Location(x,y);
        modelController.handleMouseEvent(id, eventLocation, clickCount);

    }

    public void handleKeyEvent(int id, int keyCode, char keyChar){
        System.out.println("key");
        //TODO create function 
        //TODO F5 and esc
        //with F5 it needs to be checked whether the mode can change

        

        

        //uiElements.add(grid);

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
        //modelElements.add(block_);



        UIElement block = new UIBlock(randomLocation, block_);
        uiElements.add(block);

        

        

        System.out.println("renderUI");
        uiElements.forEach((UIElement) -> (UIElement).render(g));

    }

    




}