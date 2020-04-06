import java.util.ArrayList;
import java.util.function.Predicate;

public interface GameWorldType {

    public ArrayList<ActionType> getSupportedActions();

    public ArrayList<Predicate> getSupportedPredicates();

    public GameWorld newWorldInstance();

}
