package utilities;

import java.util.ArrayList;
import model.*;

public class GridInfo {

    private final Location goalCell;
    private final ArrayList<Location> walls;
    private final Location robotLocation;
    private final Direction robotDirection;
    private final int cellSize;

    // Constructor
    public GridInfo(Location goalCell, ArrayList<Location> walls, ModelRobot robot, int cellSize){
        this.goalCell = goalCell;
        this.walls = walls;
        this.robotLocation = robot.getPos();
        this.robotDirection = robot.getDirection();
        this.cellSize = cellSize;
    }

    public GridInfo(ModelGrid grid, int cellSize){
        this.goalCell = grid.getGoalCell();
        this.walls = grid.getWalls();
        this.robotDirection = grid.getRobotDir();
        this.robotLocation = grid.getRobotPos();
        this.cellSize = cellSize;
    }

    public Location getGoalCell(){
        return this.goalCell;
    }

    public ArrayList<Location> getWalls(){
        return this.walls;
    }

    public Location getRobotLocation(){
        return this.robotLocation;
    }

    public Direction getRobotDirection(){
        return this.robotDirection;
    }

    public int getCellSize(){
        return this.cellSize;
    }
}