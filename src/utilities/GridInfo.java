package utilities;

import java.util.ArrayList;
import model.*;

/**
 * Value class containing information about the grid.
 */
public class GridInfo {

    private final GridLocation goalCell;
    private final ArrayList<GridLocation> walls;
    private final GridLocation robotLocation;
    private final Direction robotDirection;
    private final int cellSize;

    // Constructor
    public GridInfo(GridLocation goalCell, ArrayList<GridLocation> walls, GridLocation robotPos, Direction robotDir, int cellSize){
        this.goalCell = goalCell;
        this.walls = walls;
        this.robotLocation = robotPos;
        this.robotDirection = robotDir;
        this.cellSize = cellSize;
    }

    // Constructor based on a ModelGrid
    public GridInfo(ModelGrid grid, int cellSize){
        this.goalCell = grid.getGoalCell();
        this.walls = grid.getWalls();
        this.robotDirection = grid.getRobotDir();
        this.robotLocation = grid.getRobotPos();
        this.cellSize = cellSize;
    }

    /**
     *
     * @return the goal cell.
     */
    public GridLocation getGoalCell(){
        return this.goalCell;
    }

    /**
     *
     * @return a list of walls in the grid.
     */
    public ArrayList<GridLocation> getWalls(){
        return this.walls;
    }

    /**
     *
     * @return the location of the robot.
     */
    public GridLocation getRobotLocation(){
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