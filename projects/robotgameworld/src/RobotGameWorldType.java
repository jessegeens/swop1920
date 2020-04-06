import java.util.ArrayList;
import java.util.Arrays;

public class RobotGameWorldType implements GameWorldType {

    @Override
    public ArrayList<ActionType> getSupportedActions() {
        return new ArrayList<ActionType>(Arrays.asList(Action.MOVE_FORWARD, Action.TURN_LEFT, Action.TURN_RIGHT));
    }

    @Override
    public ArrayList<PredicateType> getSupportedPredicates() {
        return new ArrayList<PredicateType>(Arrays.asList());
    }

    @Override
    public GameWorld newWorldInstance() {
        return new RobotGameWorld();
    }
}
