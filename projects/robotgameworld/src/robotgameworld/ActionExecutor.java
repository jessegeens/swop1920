package robotgameworld;

import java.util.ArrayList;
import java.util.Arrays;

import gameworldapi.*;

/**
 * Singleton class that executes an robotgameworld.Action.
 *
 * @author Jesse Geens
 */
public class ActionExecutor {
    static ActionExecutor instance;

    //Parameters, TODO check whether these are at the correct location
    private final GridLocation GOAL_CELL = new GridLocation(4, 4);
    private final ArrayList<GridLocation> WALLS = new ArrayList<GridLocation>(Arrays.asList(new GridLocation(0, 2), new GridLocation(1, 2), new GridLocation(4, 3)));
    private int GRID_WIDTH = 5;
    private int GRID_HEIGHT = 10;

    private RobotGameWorldState current;
    private GameWorldStateFactory gameWorldStateFactory = GameWorldStateFactory.getInstance();

    //Constructor
    private ActionExecutor(){
        this.current = gameWorldStateFactory.getInitialState();
    }

    //Singleton getInstance()
    public static ActionExecutor getInstance(){
        if (instance == null)
                instance = new ActionExecutor();
        return instance;
    }

    /**
     * Execute an action, return the result of the action and update the current gameWorldState
     * @author Jesse Geens
     *
     * @param action action to execute
     * @return ActionResult.SUCCESS if the action succeeded,
     *         ActionResult.FAILURE if the action failed (eg robot out of bounds)
     *         ActionResult.GAME_OVER if the robot has reached the goal cell
     */
    public ActionResult execute(Action action){
        switch (action){
            case MOVE_FORWARD:
                RobotGameWorldState next = gameWorldStateFactory.createNew(current, action);
                if (validState(next)){
                    current = next;
                    if(gameFinished(current)) return ActionResult.GAME_OVER;
                    return ActionResult.SUCCESS;
                }
                else return ActionResult.FAILURE;
            case TURN_LEFT:
            case TURN_RIGHT:
                current = gameWorldStateFactory.createNew(current, action);
                return ActionResult.SUCCESS;
            default:
                throw new IllegalStateException("An illegal action has been executed");
        }
    }

    //Getters and setters
    public RobotGameWorldState getState(){
        return current;
    }

    public void setState(RobotGameWorldState state){
        this.current = state;
    }

    public GridLocation getGoalCell(){
        return GOAL_CELL;
    }

    public ArrayList<GridLocation> getWalls(){
        return WALLS;
    }

    public int getGridWidth(){
        return GRID_WIDTH;
    }

    public int getGridHeight(){
        return GRID_HEIGHT;
    }

    /**
     * Returns whether a state is valid
     * or thus whether the position of the
     * robot is valid (not outside the grid,
     * not on a wall)
     * @author Jesse Geens
     *
     * @param state {robotgameworld.RobotGameWorldState} state to check
     * @return true if the state is valid, false otherwise
     */
    private boolean validState(RobotGameWorldState state){
        if(state.getRobotLocation().getX() < 0) return false;
        if(state.getRobotLocation().getY() < 0) return false;
        if(state.getRobotLocation().getX() >= GRID_WIDTH) return false;
        if(state.getRobotLocation().getY() >= GRID_HEIGHT) return false;
        if(WALLS.contains(state.getRobotLocation())) return false;
        return true;
    }

    /**
     * @author Jesse Geens
     *
     * @param {robotgameworld.RobotGameWorldState} state to check if it is finished
     * @return true if the state is a finished game,
     *         false otherwise
     */
    private boolean gameFinished(RobotGameWorldState state){
        if(state.getRobotLocation() == GOAL_CELL) return true;
        return false;
    }

    /**
     * Check if there is a wall in front of the robot in the current game world state
     *
     * @author Bert De Vleeschouwer, Jesse Geens
     *
     * @return true if there is a wall in front of the robot
     *         false otherwise
     */
    public boolean wallInFrontOfRobot() {
        Location possibleWall = new GridLocation(current.getRobotLocation().getX(), current.getRobotLocation().getY());
        switch (current.getRobotDirection()) {
            case LEFT:
                possibleWall = possibleWall.add(-1, 0);
                break;
            case RIGHT:
                possibleWall = possibleWall.add(1, 0);
                break;
            case UP:
                possibleWall = possibleWall.add(0, -1);
                break;
            case DOWN:
                possibleWall = possibleWall.add(0, 1);
                break;
        }
        for (Location currentWall : WALLS) {
            if (currentWall.equals(possibleWall)) return true;
        }
        return false;
    }
}
