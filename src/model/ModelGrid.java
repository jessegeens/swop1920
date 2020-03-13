package model;

import utilities.*;
import java.util.ArrayList;

/**
 * Class representing a two-dimensional grid for Blockr. The grid contains walls, a robot and a goal cell.
 */
public class ModelGrid extends ModelWindow {
    private final int cellSize = 30; //TODO: waarom cellSize hier? die is gedefinieerd in de global controller
    private Location goalCell;
    private int cellHeight;
    private int cellWidth;
    private Location robotPos;
    private Direction robotDir;
    private ArrayList<Location> walls;



    public ModelGrid(int width, int height, Location goal, Location robotPos, Direction robotDir, ArrayList<Location> walls){ 
        super(width,height);
        this.setGoalCell(goal);
        this.setCellHeight(cellSize); //random waarde gekozen hier
        this.setCellWidth(cellSize); //idem hier
        this.setWalls(new ArrayList<Location>());
        this.setRobotDir(robotDir);
        this.setRobotPos(robotPos);        
    }

    public boolean wallInFrontOfRobot(){
        if(this.getWalls().contains(this.robotStepPos())) return true;
        return false;
    }

    public Location robotStepPos(){
        
        switch(getRobotDir().getDirection()){
            case(Direction.DOWN):
            return getRobotPos().add(new Location(0,1));
            case(Direction.UP):
            return getRobotPos().add(new Location(0,-1));
            case(Direction.LEFT):
            return getRobotPos().add(new Location(-1,0));
            case(Direction.RIGHT):
            return getRobotPos().add(new Location(1,0));
        }
        return new Location(0,0);
    }

    public void robotTurnRight(){//TODO zal de robot direction op deze manier juist geset worden?
        this.getRobotDir().turnRight();
    }

    public void robotTurnLeft(){//TODO idem als hierboven.
        this.getRobotDir().turnLeft();
    }

    public void robotForward(){
        this.setRobotPos(this.robotStepPos());
    }

    /**
     * 
     * @return the goal cell.
     */
    public Location getGoalCell() {
        return this.goalCell;
    }

    /**
     * 
     * @param goalCell the cell that will be the goal cell.
     */
    public void setGoalCell(Location goalCell) {
        this.goalCell = goalCell;
    }

    /**
     * 
     * @return the height of the cell.
     */
    public int getCellHeight() {
        return this.cellHeight;
    }

    /**
     * 
     * @param cellHeight the height of the cell.
     */
    public void setCellHeight(int cellHeight) {
        this.cellHeight = cellHeight;
    }

    /**
     * 
     * @return the width of the cell.
     */
    public int getCellWidth() {
        return this.cellWidth;
    }

    /**
     * 
     * @param cellWidth the width of the cell
     */
    public void setCellWidth(int cellWidth) {
        this.cellWidth = cellWidth;
    }

    /**
     * 
     * @return a list of locations where a wall stands.
     */
    public ArrayList<Location> getWalls() {
        return this.walls;
    }

    /**
     * 
     * @param walls a list of walls to add to the grid.
     */
    public void setWalls(ArrayList<Location> walls) {
        this.walls = walls;
    }

    public Location getRobotPos() {
        return this.robotPos;
    }

    public void setRobotPos(Location robotPos) {
        this.robotPos = robotPos;
    }

    public Direction getRobotDir() {
        return this.robotDir;
    }

    public void setRobotDir(Direction robotDir) {
        this.robotDir = robotDir;
    }
}