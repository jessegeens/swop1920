package robotgameworld;


/**
 * Singleton Factory that creates new GameWorldStates
 * @author Jesse Geens
 */
public class GameWorldStateFactory {

    private static GameWorldStateFactory instance;


    private static final GridLocation ROBOT_START_LOCATION = new GridLocation(0, 0);
    private static final Direction ROBOT_START_DIRECTION = Direction.RIGHT;


    //Constructor
    private GameWorldStateFactory(){}

    /**
     * Get the singleton instance
     *
     * @author Jesse Geens
     * @return robotgameworld.GameWorldStateFactory instance
     */
    public static GameWorldStateFactory getInstance(){
        if(instance == null)
            instance = new GameWorldStateFactory();
        return instance;
    }


    public RobotGameWorldState getInitialState() {
        return new RobotGameWorldState(ROBOT_START_DIRECTION, ROBOT_START_LOCATION);
    }

    /**
     *
     * @param old previous gameWorldState
     * @param action action to execute for generating the new state
     * @author Jesse Geens
     * @return {GameWorldState} gameWorldState after the action has been executed
     */
    public RobotGameWorldState createNew(RobotGameWorldState old, Action action){
        switch (action){
            case MOVE_FORWARD:
                return new RobotGameWorldState(old.getRobotDirection(), move(old));
            case TURN_LEFT:
                return new RobotGameWorldState(old.getRobotDirection().turnLeft(), old.getRobotLocation());
            case TURN_RIGHT:
                return new RobotGameWorldState(old.getRobotDirection().turnRight(), old.getRobotLocation());
        }
        return null;
    }

    /**
     * Get the new location of the robot when it moves
     *
     * @author Jesse Geens
     * @param state {robotGameWorldState}
     * @return {robotgameworld.GridLocation} new location of the robot
     */
    private GridLocation move(RobotGameWorldState state){
        switch(state.getRobotDirection()){
            case UP:
                return state.getRobotLocation().add(0, -1);
            case RIGHT:
                return state.getRobotLocation().add(1, 0);
            case DOWN:
                return state.getRobotLocation().add(0, 1);
            case LEFT:
                return state.getRobotLocation().add(-1, 0);
            default:
                throw new IllegalStateException("ProgramState has an illegal direction");
        }
    }

}
