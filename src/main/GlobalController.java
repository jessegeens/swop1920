package main;

import java.util.ArrayList;
import java.awt.*;

import ui.*;
import ui.blocks.*;
import utilities.*;
import model.*;
import model.blocks.ModelBlock;

class GlobalController {
//on creation provide instructions to modelcontroller
//modelcontroller makes the three windows and manages them

//create ui representation and handle it

//all event handling starts here

    private final int CELL_SIZE = 50;

    private ModelController modelController;
    private UIController uiController;

    

    public GlobalController(Graphics g){
        this.modelController = new ModelController();
        GridInfo gridInfo = new GridInfo(modelController.getGrid(), CELL_SIZE);
        this.uiController = new UIController(g, MyCanvasWindow.WIDTH, MyCanvasWindow.HEIGHT, new ArrayList<ModelBlock>(), gridInfo);      
    }

    private ArrayList<UIElement> uiElements = new ArrayList<UIElement>();
    private ArrayList<ModelElement> modelElements = new ArrayList<ModelElement>();


    //traverse uielements in opposite direction to have top rendering order
    
    public ModelGrid getModelGrid(){
        return modelController.getGrid();
    }

    public void handleMouseEvent(int id, int x, int y, int clickCount){
        Location eventLocation = new Location(x,y);
        modelController.handleMouseEvent(id, eventLocation, clickCount);

    }

    public void handleKeyEvent(int id, int keyCode, char keyChar){
        System.out.println("key");
        switch(keyCode){
            case 116: //F5;
                break;
            
            case 27: //Esc
                break;
        }
        //TODO create function in modelcontroller
    }


    /*
    public void renderUIElements(Graphics g, Rectangle uiBounds){

        //TODO create a separate ui generation method for this later
        
        //TODO why is the UIgrid not a UIElement?this.renderUI(g);
        this.renderUI(g);
        this.renderBlocks(g);
        this.renderGrid(g);

        //TODO why is the UIgrid not a UIElement?

        Location wall1 = new Location(2,2);
        Location wall2 = new Location(1, 2);

        ArrayList<Location> walls = new ArrayList<Location>();

        walls.add(wall1);
        walls.add(wall2);

        Location robot = new Location(0, 0);
        Direction robotDirection = new Direction(Direction.LEFT);

        Location goal = new Location(3,3);


        //w, h, cellSize
        UIGrid grid = new UIGrid(new Location(2*MyCanvasWindow.WIDTH/3, 0), MyCanvasWindow.WIDTH / 3, MyCanvasWindow.HEIGHT, 50, walls, robot, robotDirection, goal);

        grid.render(g);

        Location randomLocation = new Location(300, 300);

        Blocktype block_ = new Blocktype(Blocktype.IF);

        System.out.println("rendering UI Elements");
        uiElements.forEach((UIElement) -> (UIElement).render(g));


    }
    */


    




}