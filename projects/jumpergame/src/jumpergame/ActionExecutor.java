package jumpergame;

import java.util.ArrayList;
import java.util.Arrays;

import gameworldapi.*;

/**
 * Singleton class that executes an action.
 *
 * @author Jesse Geens
 */
public class ActionExecutor {
    static ActionExecutor instance;

    //Parameters, TODO check whether these are at the correct location
    private final GridLocation PRINCESS = new GridLocation(3, 5);
    private final ArrayList<GridLocation> BLOCKS = new ArrayList<GridLocation>(Arrays.asList(new GridLocation(0, 2), new GridLocation(1, 2), new GridLocation(4, 3)));
    private int GRID_WIDTH = 5;
    private int GRID_HEIGHT = 15;

    private JumperGameWorldState current;
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
            case MOVE_LEFT:
            case MOVE_RIGHT:
            case JUMP:
                JumperGameWorldState next = gameWorldStateFactory.createNew(current, action);
                if(gameFinished(next)) return ActionResult.GAME_OVER;
                if(validState(next)){
                    current = next;
                    return ActionResult.SUCCESS;
                }
                return ActionResult.FAILURE;
            default:
                throw new IllegalStateException("An illegal action has been executed");
        }
    }

    //Getters and setters
    public JumperGameWorldState getState(){
        return current;
    }

    public void setState(JumperGameWorldState state){
        this.current = state;
    }

    public GridLocation getPrincess(){
        return PRINCESS;
    }

    public ArrayList<GridLocation> getBlocks(){
        return BLOCKS;
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
     * robot is valid (not outside the grid)
     *
     * @author Jesse Geens
     *
     * @param state {JumperGameWorldState} state to check
     * @return true if the state is valid, false otherwise
     */
    private boolean validState(JumperGameWorldState state){
        if(state.getPlayerLocation().getX() < 0) return false;
        if(state.getPlayerLocation().getY() < 0) return false;
        if(state.getPlayerLocation().getX() >= GRID_WIDTH) return false;
        if(state.getPlayerLocation().getY() >= GRID_HEIGHT) return false;
        return true;
    }

    /**
     * @author Jesse Geens
     *
     * @param {JumperGameWorldState} state to check if it is finished
     * @return true if the state is a finished game
     *         (princess reached or hit a block),
     *         false otherwise
     */
    private boolean gameFinished(JumperGameWorldState state){
        return BLOCKS.contains(state.getPlayerLocation()) || state.getPlayerLocation() == PRINCESS;
    }

    /**
     * Check if there is a wall in front of the robot in the current game world state
     *
     * @author Jesse Geens
     *
     * @return true if there is a block above the player
     *         false otherwise
     */
    public boolean blockAbovePlayer() {
        return BLOCKS.contains(current.getPlayerLocation().add(0, -1));
    }

}
