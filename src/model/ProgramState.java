package model;

import java.util.ArrayList;
import utilities.*;

public class ProgramState {
    private Direction robotDirection;
    private GridLocation robotLocation;
    private ArrayList<GridLocation> walls;
    private GridLocation goalCell;
    private int cellSize;

    ProgramState(Direction robotDirection, GridLocation robotLocation, ArrayList<GridLocation> walls, GridLocation goalCell, int cellSize){
        this.robotDirection = robotDirection;
        this.robotLocation = robotLocation;
        this.walls = walls;
        this.goalCell = goalCell;
    }

    @Override
    public String toString(){
        return "[ProgramState: " + robotDirection.toString() + ", " + robotLocation.toString() + ", " + goalCell.toString() + "]";
    }

    static ProgramState generateNew(ProgramState programState, Direction robotDirection, GridLocation robotLocation){
        return new ProgramState(robotDirection, robotLocation, programState.getWalls(), programState.getGoalCell(), programState.getCellSize());
    }

    //TODO: implement
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