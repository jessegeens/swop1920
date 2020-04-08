import java.util.ArrayList;
import java.util.Arrays;

/**
 * The Game World API defines a GameWorldType interface, that offers methods to:
 * - retrieve the list of Actions supported by the GameWorldType. For Robot, this is: Turn Left, Turn Right, Move Forward.
 * - retrieve the list of Predicates supported by the GameWorldType. For Robot, this is: WallInFront.
 * - create a new game world instance, which implements interface GameWorld.
 *
 * Class that implements the GameWorldType API
 * This class connects to Blockr and returns the
 *  game world, list of supported actions and list
 *  of supported predicates
 * @author Jesse Geens
 */
public class RobotGameWorldType implements GameWorldType {

    @Override
    public ArrayList<ActionType> getSupportedActions() {
        return new ArrayList<ActionType>(Arrays.asList(Action.MOVE_FORWARD, Action.TURN_LEFT, Action.TURN_RIGHT));
    }

    @Override
    public ArrayList<PredicateType> getSupportedPredicates() {
        return new ArrayList<PredicateType>(Arrays.asList(Predicate.WALL_IN_FRONT));
    }

    @Override
    public GameWorld newWorldInstance() {
        return new RobotGameWorld();
    }
}
