package model;

import utilities.*;
import java.util.ArrayList;

/**
 * Class representing a two-dimensional grid for Blockr. The grid contains walls, a robot and a goal cell.
 */
public class ModelGrid extends ModelWindow {
    private Location goalCell;
    private int cellHeight;
    private int cellWidth;
    private Location robotPos;
    private Direction robotDir;
    private ArrayList<Location> walls;

    // Constructor
    public ModelGrid(int width, int height, Location goal, Location robotPos, Direction robotDir, ArrayList<Location> walls, int cellSize){ 
        super(width,height);
        this.setGoalCell(goal);
        this.cellHeight = cellSize;
        this.cellWidth = cellSize;
        this.setWalls(new ArrayList<Location>());
        this.setRobotDir(robotDir);
        this.setRobotPos(robotPos);        
    }

    /**
     * 
     * @return whether there is a wall in front of the robot
     */
    public boolean wallInFrontOfRobot(){
        if(this.getWalls().contains(this.robotStepPos())) return true;
        return false;
    }

    /**
     * 
     * @return the new position of the robot after stepping
     */
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

    /**
     * Turn the robot 90° to the right
     * 
     * TODO: zal de robot direction op deze manier juist geset worden?
     */
    public void robotTurnRight(){//
        this.getRobotDir().turnRight();
    }

    /**
     * Turn the robot 90° to the left
     * 
     * TODO: zal de robot direction op deze manier juist geset worden?
     */
    public void robotTurnLeft(){
        this.getRobotDir().turnLeft();
    }

    /**
     * Move the robot forward
     */
    public void robotForward(){
        System.out.println("moving robot forward");
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

    /**
     * Getter for the robot position
     * 
     * @return {Location} return the robot's current position
     */
    public Location getRobotPos() {
        return this.robotPos;
    }

    /**
     * Setter for the robot position
     * 
     * @param robotPos the new position of the robot
     */
    public void setRobotPos(Location robotPos) {
        this.robotPos = robotPos;
        System.out.println("ROBOT POS UPDATE");
        System.out.println(this.robotPos.getX());
        System.out.println(this.robotPos.getY());


    }

    /**
     * Getter for the robot direction
     * 
     * @return {Direction} return the robot's current direction
     */
    public Direction getRobotDir() {
        return this.robotDir;
    }

    /**
     * Setter for the robot direction
     * 
     * @param robotDir the new direction of the robot
     */
    public void setRobotDir(Direction robotDir) {
        this.robotDir = robotDir;
    }

    public GridInfo getGridInfo(){
        GridInfo g = new GridInfo(getGoalCell(), getWalls(), getRobotPos(), getRobotDir(), getCellHeight());
        return g;
    }
}