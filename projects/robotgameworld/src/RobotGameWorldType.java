import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

public class RobotGameWorldType implements GameWorldType {

    @Override
    public ArrayList<ActionType> getSupportedActions() {
        return new ArrayList<Action>(Arrays.asList(Action.MOVE_FORWARD, Action.TURN_LEFT, Action.TURN_RIGHT));
    }

    @Override
    public ArrayList<Predicate> getSupportedPredicates() {
        return null;
    }

    @Override
    public GameWorld newWorldInstance() {
        return null;
    }
}
