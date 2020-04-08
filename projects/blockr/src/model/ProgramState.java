package model;

import gameworldapi.*;
import java.util.ArrayList;
import utilities.*;

public class ProgramState {
    private Direction robotDirection;
    private ProgramLocation robotLocation;
    private ArrayList<ProgramLocation> walls;
    private ProgramLocation goalCell;
    private int cellSize;

    //Parameters
    private static final int CELL_SIZE = 50;
    private static final ProgramLocation GOAL_CELL = new ProgramLocation(5, 5);
    private static final ProgramLocation ROBOT_START_LOCATION = new ProgramLocation(0, 0);
    private static final Direction ROBOT_START_DIRECTION = Direction.RIGHT;
    private static final ArrayList<ProgramLocation> WALLS = new ArrayList<ProgramLocation>();


    public ProgramState(Direction robotDirection, ProgramLocation robotLocation, ArrayList<ProgramLocation> walls, ProgramLocation goalCell, int cellSize){
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

    static ProgramState generateNew(ProgramState programState, Direction robotDirection, ProgramLocation robotGridLocation){
        return new ProgramState(robotDirection, robotGridLocation, programState.getWalls(), programState.getGoalCell(), programState.getCellSize());
    }

    
    /*I would make all variables of Program State immutable, also I would make a function for robot direction and location variable so that the function
    Returns a new ProgramState with all the same fields except that one parameter that is changed. That is handy when the robot direction or position is changed and all
    other things stay the same.*/

    //TODO is a game border a wall?
    /**
     * 
     * @return true if there's a wall in front of the direction the robot is facing 
     * @author Bert
     */
    public boolean wallInFrontOfRobot(){
        ProgramLocation findOutIfWall = new ProgramLocation(robotLocation.getX(), robotLocation.getY());
        switch (robotDirection.toString()){
            case "left": 
                findOutIfWall.add(-1,0);
                break;
            case "right": 
                findOutIfWall.add(1,0);
                break;
            case "up": 
                findOutIfWall.add(0,1);
                break;
            case "down": 
                findOutIfWall.add(0,-1);
                break;
        }

        for (Location currrentWall : walls){
            if(currrentWall.getX() == findOutIfWall.getX() && currrentWall.getY() == findOutIfWall.getY()){
                return true;
            }
        }      


        return false;
    }



    public ArrayList<ProgramLocation> getWalls(){
        return this.walls;
    }

    public ProgramLocation getGoalCell(){
        return this.goalCell;
    }

    public Direction getRobotDirection(){
        return this.robotDirection;
    }

    public ProgramLocation getRobotLocation(){
        return this.robotLocation;
    }

    public int getCellSize(){
        return this.cellSize;
    }
}