package model;

import java.util.ArrayList;
import utilities.*;

public class ProgramState {
    private Direction robotDirection;
    private GridLocation robotLocation;
    private ArrayList<GridLocation> walls;
    private GridLocation goalCell;
    private int cellSize;

    public ProgramState(Direction robotDirection, GridLocation robotLocation, ArrayList<GridLocation> walls, GridLocation goalCell, int cellSize){
        this.robotDirection = robotDirection;
        this.robotLocation = robotLocation;
        this.walls = walls;
        this.goalCell = goalCell;
        this.cellSize = cellSize;
    }

    @Override
    public String toString(){
        return "[ProgramState: " + robotDirection.toString() + ", " + robotLocation.toString() + ", " + goalCell.toString() + "]";
    }

    static ProgramState generateNew(ProgramState programState, Direction robotDirection, GridLocation robotLocation){
        return new ProgramState(robotDirection, robotLocation, programState.getWalls(), programState.getGoalCell(), programState.getCellSize());
    }

    //TODO: implement
    /*I would make all variables of Program State immutable, also I would make a function for robot direction and location variable so that the function
    Returns a new ProgramState with all the same fields except that one parameter that is changed. That is handy when the robot direction or position is changed and all
    other things stay the same.*/
    public boolean wallInFrontOfRobot(){
        return false;
    }

    public ArrayList<GridLocation> getWalls(){
        return this.walls;
    }

    public GridLocation getGoalCell(){
        return this.goalCell;
    }

    public Direction getRobotDirection(){
        return this.robotDirection;
    }

    public GridLocation getRobotLocation(){
        return this.robotLocation;
    }

    public int getCellSize(){
        return this.cellSize;
    }
}