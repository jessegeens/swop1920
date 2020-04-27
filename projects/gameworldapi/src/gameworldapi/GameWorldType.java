package gameworldapi;

import java.util.ArrayList;

/**
 * The GameWorldType interface acts as a Facade to
 * the blockr (and others) application.
 *
 * @author Jesse Geens
 */
public interface GameWorldType {

    /**
     * This function should return an ArrayList containing
     * all the supported Actions.
     * @return the list of supported actions.
     */
    public ArrayList<ActionType> getSupportedActions();

    /**
     * This function should return an ArrayList containing
     * all the supported Predicates.
     * @return the supported predicates
     */
    public ArrayList<PredicateType> getSupportedPredicates();

    /**
     * This function returns a new GameWorld instance, which
     * represents the actual game that will be played.
     *
     * In blocker-like applications, it should only be called
     * once, for every call returns a new Game World.
     * @return the new world instance
     */
    public GameWorld newWorldInstance();

}
