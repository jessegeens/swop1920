import java.util.ArrayList;

public class RobotGameWorldState implements GameWorldState {

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


    public RobotGameWorldState(Direction robotDirection, Location robotLocation, ArrayList<Location> walls, Location goalCell, int cellSize) {
        this.robotDirection = robotDirection;
        this.robotLocation = robotLocation;
        this.walls = walls;
        this.goalCell = goalCell;
        this.cellSize = cellSize;
    }

    public static RobotGameWorldState getInitialState() {
        return new RobotGameWorldState(ROBOT_START_DIRECTION, ROBOT_START_LOCATION, WALLS, GOAL_CELL, CELL_SIZE);
    }

    @Override
    public String toString() {
        return "[RobotGameWorldState: " + robotDirection.toString() + ", " + robotLocation.toString() + ", " + goalCell.toString() + "]";
    }

    static RobotGameWorldState generateNext(RobotGameWorldState robotGameWorldState) {
        return new RobotGameWorldState(robotDirection, robotLocation, walls, goalCell, cellSize);
    }


    /*I would make all variables of Program State immutable, also I would make a function for robot direction and location variable so that the function
    Returns a new ProgramState with all the same fields except that one parameter that is changed. That is handy when the robot direction or position is changed and all
    other things stay the same.*/
    public boolean wallInFrontOfRobot() {
        Location findOutIfWall = new Location(robotLocation.getX(), robotLocation.getY());
        switch (robotDirection.toString()) {
            case "left":
                findOutIfWall.add(-1, 0);
                break;
            case "right":
                findOutIfWall.add(1, 0);
                break;
            case "up":
                findOutIfWall.add(0, 1);
                break;
            case "down":
                findOutIfWall.add(0, -1);
                break;
        }
        for (Location currentWall : walls) {
            if (currentWall.getX() == findOutIfWall.getX() && currentWall.getY() == findOutIfWall.getY()) {
                return true;
            }
        }
        return false;
    }
}
