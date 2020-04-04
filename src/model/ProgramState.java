package model;

import java.util.ArrayList;
import utilities.*;

public class ProgramState {
    private Direction robotDirection;
    private Location robotLocation;
    private ArrayList<Location> walls;
    private Location goalCell;
    private int cellSize;

    //Parameters
    private static final int CELL_SIZE = 50;
    private static final Location GOAL_CELL = new Location(5, 5);
    private static final Location ROBOT_START_LOCATION = new Location(0, 0);
    private static final Direction ROBOT_START_DIRECTION = Direction.RIGHT;
    private static final ArrayList<Location> WALLS = new ArrayList<Location>();


    public ProgramState(Direction robotDirection, Location robotLocation, ArrayList<Location> walls, Location goalCell, int cellSize){
        this.robotDirection = robotDirection;
        this.robotLocation = robotLocation;
        this.walls = walls;
        this.goalCell = goalCell;
        this.cellSize = cellSize;
    }

    public static ProgramState getInitialState(){
        return new ProgramState(ROBOT_START_DIRECTION, ROBOT_START_LOCATION, WALLS, GOAL_CELL, CELL_SIZE);
    }

    @Override
    public String toString(){
        return "[ProgramState: " + robotDirection.toString() + ", " + robotLocation.toString() + ", " + goalCell.toString() + "]";
    }

    static ProgramState generateNew(ProgramState programState, Direction robotDirection, Location robotGridLocation){
        return new ProgramState(robotDirection, robotGridLocation, programState.getWalls(), programState.getGoalCell(), programState.getCellSize());
    }

    //TODO: implement
    /*I would make all variables of Program State immutable, also I would make a function for robot direction and location variable so that the function
    Returns a new ProgramState with all the same fields except that one parameter that is changed. That is handy when the robot direction or position is changed and all
    other things stay the same.*/
    public boolean wallInFrontOfRobot(){
        return false;
    }

    public ArrayList<Location> getWalls(){
        return this.walls;
    }

    public Location getGoalCell(){
        return this.goalCell;
    }

    public Direction getRobotDirection(){
        return this.robotDirection;
    }

    public Location getRobotLocation(){
        return this.robotLocation;
    }

    public int getCellSize(){
        return this.cellSize;
    }
}