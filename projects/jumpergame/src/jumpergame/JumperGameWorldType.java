package jumpergame;

import gameworldapi.*;

import java.util.ArrayList;
import java.util.Arrays;

public class JumperGameWorldType implements GameWorldType{

    @Override
    public ArrayList<ActionType> getSupportedActions() {
        return new ArrayList<>(Arrays.asList(Action.JUMP, Action.MOVE_LEFT, Action.MOVE_RIGHT));
    }

    @Override
    public ArrayList<PredicateType> getSupportedPredicates() {
        return new ArrayList<>(Arrays.asList(Predicate.BLOCK_ABOVE));
    }

    @Override
    public GameWorld newWorldInstance() {
        return null;
    }
}
