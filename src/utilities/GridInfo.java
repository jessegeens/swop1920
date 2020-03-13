package utilities;

import java.util.ArrayList;
import model.*;

/**
 * Value class containing information about the grid.
 */
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
        this.robotDirection = grid.getRobotDir();
        this.robotLocation = grid.getRobotPos();
        this.walls = grid.getWalls();
        this.cellSize = cellSize;
    }

    /**
     *
     * @return the goal cell.
     */
    public Location getGoalCell(){
        return this.goalCell;
    }

    /**
     *
     * @return a list of walls in the grid.
     */
    public ArrayList<Location> getWalls(){
        return this.walls;
    }

    /**
     *
     * @return the location of the robot.
     */
    public Location getRobotLocation(){
        return this.robotLocation;
    }

    /**
     *
     * @return the direction of the robot.
     */
    public Direction getRobotDirection(){
        return this.robotDirection;
    }

    /**
     *
     * @return the size of the cells.
     */
    public int getCellSize(){
        return this.cellSize;
    }
}