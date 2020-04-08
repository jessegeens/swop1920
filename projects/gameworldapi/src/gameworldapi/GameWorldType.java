package gameworldapi;

import java.util.ArrayList;

public interface GameWorldType {

    public ArrayList<ActionType> getSupportedActions();

    public ArrayList<PredicateType> getSupportedPredicates();

    public GameWorld newWorldInstance();

}
