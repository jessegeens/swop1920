package main;

import java.util.ArrayList;
import java.awt.*;

import ui.*;
import ui.blocks.*;
import utilities.*;
import model.*;
import model.blocks.ModelBlock;
import model.blocks.ModelNotBlock;
import model.blocks.ModelWallInFrontBlock;
import model.blocks.ModelWhileIfBlock;

public class GlobalController {
//on creation provide instructions to modelcontroller
//modelcontroller makes the three windows and manages them

//create ui representation and handle it
//traverse uielements in opposite direction to have top rendering order
//all event handling starts here

    private final int CELL_SIZE = 50;
    private final Location GOAL_CELL = new Location(5, 5);
    private final Location ROBOT_START_LOCATION = new Location(0, 0);
    private final Direction ROBOT_START_DIRECTION = new Direction(Direction.RIGHT);

    private final int MAX_BLOCKS = 5;

    private ModelController modelController;
    private UIController uiController;

    private boolean running;
    private ModelBlock current;

    public GlobalController(){
        GridInfo gridInfo = new GridInfo(GOAL_CELL, new ArrayList<Location>(), new ModelRobot(ROBOT_START_LOCATION, ROBOT_START_DIRECTION), CELL_SIZE);
        this.modelController = new ModelController(gridInfo, this.MAX_BLOCKS);
        System.out.println(modelController.getModelBlocks());
        this.uiController = new UIController(MyCanvasWindow.WIDTH, MyCanvasWindow.HEIGHT, modelController.getModelBlocks(), gridInfo);      
    }

    private ArrayList<UIElement> uiElements = new ArrayList<UIElement>();
    private ArrayList<ModelElement> modelElements = new ArrayList<ModelElement>();

    public void handleMouseEvent(int id, int x, int y, int clickCount){
        Location eventLocation = new Location(x,y);
        modelController.handleMouseEvent(id, eventLocation, clickCount);
        this.uiController.updateBlocks(this.modelController.getModelBlocks());

    }

    public void handleKeyEvent(int id, int keyCode, char keyChar){
        System.out.println("key");
        switch(keyCode){
            case 116: //F5;
                if (this.modelController.getPWindow().allBlocksConnected()) execute();//Check if all blocks are connected, and if so execute.
                break;
            
            case 27: //Esc
                break;
        }
        //TODO create function in modelcontroller
    }

    public void execute(){
        if(!(isRunning())){
            startRunning();
            setCurrent(this.modelController.getPWindow().getStartBlock());
        }
        step();
        this.setCurrent(findNextBlock());
        this.highlightNext();
    }

    public void step(){
        switch(getCurrent().getBlockType().getType()){
            case(Blocktype.MOVEFORWARD):
            this.modelController.getGrid().robotForward();
            break;
            case(Blocktype.TURNLEFT):
            this.modelController.getGrid().robotTurnLeft();
            break;
            case(Blocktype.TURNRIGHT):
            this.modelController.getGrid().robotTurnRight();
            break;
        }
    }


    public void render(Graphics g){
        uiController.render(g);
    }

    public boolean isRunning(){
        return this.running;
    }

    public void startRunning(){
        this.running = true;
    }

    public void stopRunning(){
        this.running = false;
    }

    public ModelBlock getCurrent(){
        return this.current;
    }

    public void setCurrent(ModelBlock blk){
        this.current = blk;
    }

    public void highlightNext(){
        getCurrent().setUnHighlight();
        findNextBlock().setHighlight();
    }

    public ModelBlock findNextBlock(){//Gives the bottomplug if it is a normal block or if the condition of if or while block fails, if condition
        //of while or if succeeds it gives the first cavity block of the if or while block.
        //TODO next block in execution
        if ((getCurrent().getBlockType().getType() == Blocktype.IF) || (getCurrent().getBlockType().getType() == Blocktype.WHILE)){
            if (evaluateCurrentCondition()){
                return ((ModelWhileIfBlock)getCurrent()).getCavitySocket();
            }
        }
        return getCurrent().getBottomPlug();
    }

    public boolean evaluateCurrentCondition(){
        boolean negate = false;
        ModelBlock cond = getCurrent().getRightSocket();
        while (cond.hasRightSocket()){
            if(cond instanceof ModelNotBlock){
                negate = !negate;
                cond = cond.getRightSocket();
            }
            else if((cond instanceof ModelWallInFrontBlock) && this.modelController.getGrid().wallInFrontOfRobot()){
                return !negate;
            }
        }
        return negate;
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