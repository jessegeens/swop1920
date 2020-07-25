package robotgameworld;

import gameworldapi.*;
import robotgameworld.Action;
import robotgameworld.ActionExecutor;
import robotgameworld.GridLocation;
import robotgameworld.Predicate;

import java.awt.*;

/**
 * Class that implements the GameWorld API
 * Acts as a Facade to the Blockr application for the current world instance
 *
 * @author Jesse Geens
 */
public class RobotGameWorld implements GameWorld {

    private ActionExecutor actionExecutor = ActionExecutor.getInstance();
    private UIController uiController = new UIController();

    /**
     *
     * @author Jesse Geens
     * @param actionType the type of the action
     * @return the result of the action
     */
    @Override
    public ActionResult perform(ActionType actionType) {
        return actionExecutor.execute((Action) actionType);
    }

    /**
     * @author Jesse Geens
     * @param predicateType the type of the predicate
     * @return the result of the evaluation
     */
    @Override
    public Boolean evaluate(PredicateType predicateType) {
        if (predicateType != null){
            switch ((Predicate)predicateType){
                case WALL_IN_FRONT:
                    return actionExecutor.wallInFrontOfRobot();
                default:
                    throw new IllegalStateException("Illegal predicate found");
            }
        }
        return false;
    }

    /**
     * @author Jesse Geens
     * @return {GameWorldState} the current GameWorldState
     */
    @Override
    public GameWorldState getSnapshot() {
        return actionExecutor.getState();
    }

    /**
     * Set the gameWorld to a given gameWorldState
     *
     * @author Jesse Geens
     * @param gameWorldState state to set
     */
    @Override
    public void restore(GameWorldState gameWorldState) {
        actionExecutor.setState((RobotGameWorldState) gameWorldState);
    }

    /**
     * @author Jesse Geens
     * @param graphics Graphics object to draw on
     */
    @Override
    public void render(Graphics graphics) {
        uiController.render(graphics, ActionExecutor.getInstance().getState(), new GridLocation(0, 0));
    }

    @Override
    public int getHeight() {
        return ActionExecutor.GRID_HEIGHT * UIController.CELL_SIZE;
    }
}
