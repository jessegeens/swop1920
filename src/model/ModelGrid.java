package model;

import utilities.*;
import java.util.ArrayList;

/**
 * Class representing a two-dimensional grid for Blockr. The grid contains walls, a robot and a goal cell.
 */
public class ModelGrid extends ModelWindow {
    private GridLocation goalCell;
    private int cellHeight;
    private int cellWidth;
    private GridLocation robotPos;
    private Direction robotDir;
    private ArrayList<GridLocation> walls;

    // Constructor
    public ModelGrid(int width, int height, GridLocation goal, GridLocation robotPos, Direction robotDir, ArrayList<GridLocation> walls, int cellSize){
        super(width,height);
        this.setGoalCell(goal);
        this.cellHeight = cellSize;
        this.cellWidth = cellSize;
        this.setWalls(new ArrayList<GridLocation>());
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
    public GridLocation robotStepPos(){
        
        switch(getRobotDir().getDirection()){
            case(Direction.DOWN):
            return getRobotPos().add(new GridLocation(0,1));
            case(Direction.UP):
            return getRobotPos().add(new GridLocation(0,-1));
            case(Direction.LEFT):
            return getRobotPos().add(new GridLocation(-1,0));
            case(Direction.RIGHT):
            return getRobotPos().add(new GridLocation(1,0));
        }
        return new GridLocation(0,0);
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
    public GridLocation getGoalCell() {
        return this.goalCell;
    }

    /**
     * 
     * @param goalCell the cell that will be the goal cell.
     */
    public void setGoalCell(GridLocation goalCell) {
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
    public ArrayList<GridLocation> getWalls() {
        return this.walls;
    }

    /**
     * 
     * @param walls a list of walls to add to the grid.
     */
    public void setWalls(ArrayList<GridLocation> walls) {
        this.walls = walls;
    }

    /**
     * Getter for the robot position
     * 
     * @return {Location} return the robot's current position
     */
    public GridLocation getRobotPos() {
        return this.robotPos;
    }

    /**
     * Setter for the robot position
     * 
     * @param robotPos the new position of the robot
     */
    public void setRobotPos(GridLocation robotPos) {
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