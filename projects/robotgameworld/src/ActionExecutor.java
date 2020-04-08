import java.util.ArrayList;


/**
 * Singleton class that executes an Action.
 * This class uses the Command (GoF) pattern
 * Executed actions will be saved on a stack for UNDO/REDO
 *
 * @author Jesse Geens
 */
public class ActionExecutor {
    static ActionExecutor instance;

    private final GridLocation GOAL_CELL = new GridLocation(5, 5);
    private final ArrayList<GridLocation> WALLS = new ArrayList<GridLocation>();

    private RobotGameWorldState current;
    private GameWorldStateFactory gameWorldStateFactory = GameWorldStateFactory.getInstance();
    private ArrayList<Action> previous = new ArrayList<Action>();
    private ArrayList<Action> next = new ArrayList<Action>();


    private ActionExecutor(){
        this.current = gameWorldStateFactory.getInitialState();
    }

    public static ActionExecutor getInstance(){
        if (instance == null)
                instance = new ActionExecutor();
        return instance;
    }


    public ActionResult execute(Action action){
        switch (action){
            case MOVE_FORWARD:
                RobotGameWorldState next = gameWorldStateFactory.createNew(current, action);
                if (validState(next)){
                    current = next;
                    previous.add(action);
                    if(gameFinished(current)) return ActionResult.GAME_OVER;
                    return ActionResult.SUCCESS;
                }
                else return ActionResult.FAILURE;
            case TURN_LEFT:
            case TURN_RIGHT:
                current = gameWorldStateFactory.createNew(current, action);
                previous.add(action);
                return ActionResult.SUCCESS;
            default:
                throw new IllegalStateException("An illegal action has been executed");
        }
    }

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

    /**
     * Returns whether a state is valid
     * or thus whether the position of the
     * robot is valid (not outside the grid,
     * not on a wall)
     * @author Jesse Geens
     *
     * TODO: check for out of grid bounds
     *
     * @param state {RobotGameWorldState} state to check
     * @return true if the state is valid, false otherwise
     */
    private boolean validState(RobotGameWorldState state){
        if(state.getRobotLocation().getX() < 0) return false;
        if(state.getRobotLocation().getY() < 0) return false;
        if(WALLS.contains(state.getRobotLocation())) return false;
        return true;
    }

    private boolean gameFinished(RobotGameWorldState state){
        if(state.getRobotLocation() == GOAL_CELL) return true;
        return false;
    }

    public boolean wallInFrontOfRobot() {
        Location findOutIfWall = new GridLocation(current.getRobotLocation().getX(), current.getRobotLocation().getY());
        switch (current.getRobotDirection().toString()) {
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
        for (Location currentWall : WALLS) {
            if (currentWall.getX() == findOutIfWall.getX() && currentWall.getY() == findOutIfWall.getY()) {
                return true;
            }
        }
        return false;
    }
}
