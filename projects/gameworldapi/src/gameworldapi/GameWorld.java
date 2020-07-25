package gameworldapi;
import java.awt.*;

/**
 The GameWorld interface offers methods to:
- perform one of the supported Actions. It returns a result indicating successful execution, failure to execute because the action is illegal in the current state, or end of the game due to reaching the goal state.
- evaluate one of the supported Predicates.
- create an (opaque, i.e. non-inspectable) snapshot of the game world state and to restore the game world state to a given snapshot.
- paint the current state of the game world, given a graphics object (either java.awt.Graphics or another graphics API of your own design).

 @author Jesse Geens
 */

public interface GameWorld {

    /**
     * This function takes an ActionType
     * as an argument and performs the
     * action on the current gameworldstate.
     *
     * It returns an ActionResult indicating
     * whether the action was successful or
     * not and whether the game has ended now
     * or not.
     *
     * If performing the action was successful,
     * the gameworldstate should be updated
     * accordingly.
     * @param action the action to perform
     * @return the action result
     * @author Jesse Geens
     */

    ActionResult perform(ActionType action);

    /**
     * This function evaluates a predicate
     * in the given gameworldstate
     * and subsequently returns a boolean
     * indicating the evaluation of the
     * predicate.
     * @param predicate the predicate
     * @return true if evaluated correctly
     * @author Jesse Geens
     */
    Boolean evaluate(PredicateType predicate);

    /**
     * This function returns a snapshot
     * of the current gameworldstate.
     *
     * This snapshot is immutable.
     * @return the snapshot of the game world.
     * @author Jesse Geens
     */
    GameWorldState getSnapshot();

    /**
     * This function takes a snapshot
     * of a gameworldstate as an argument
     * and updates the gameworld so that
     * the current state is set to the argument.
     * @param gameWorldState the state of the world.
     * @author Jesse Geens
     */
    void restore(GameWorldState gameWorldState);

    /**
     * This function triggers painting the
     * gameworld on the canvas window, using
     * the given Graphics object.
     * @param g the graphic
     * @author Jesse Geens
     */
    void render(Graphics g);

    /**
     *
     * @return the height which the gameworld takes up on the screen.
     */
    int getHeight();

}
