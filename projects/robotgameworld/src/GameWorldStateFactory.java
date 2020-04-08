import java.util.ArrayList;

/**
 * Singleton Factory that creates new GameWorldStates
 * @author Jesse Geens
 */
public class GameWorldStateFactory {

    private static GameWorldStateFactory instance;


    private static final GridLocation ROBOT_START_LOCATION = new GridLocation(0, 0);
    private static final Direction ROBOT_START_DIRECTION = Direction.RIGHT;



    private GameWorldStateFactory(){}

    public static GameWorldStateFactory getInstance(){
        if(instance == null)
            instance = new GameWorldStateFactory();
        return instance;
    }

    public RobotGameWorldState getInitialState() {
        return new RobotGameWorldState(ROBOT_START_DIRECTION, ROBOT_START_LOCATION);
    }

    /**
     * TODO implement
     *
     * @param old
     * @param action
     * @author Jesse Geens
     * @return
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
     * Get a new GameWorldState in which the robot has moved
     *
     * @author Jesse Geens
     * @param state {robotGameWorldState}
     * @return
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
