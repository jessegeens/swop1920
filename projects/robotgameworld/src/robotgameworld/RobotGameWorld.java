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
     * @param actionType
     * @return
     */
    @Override
    public ActionResult perform(ActionType actionType) {
        return actionExecutor.execute((Action) actionType);
    }

    /**
     * @author Jesse Geens
     * @param predicateType
     * @return
     */
    @Override
    public Boolean evaluate(PredicateType predicateType) {
        if (predicateType != null){
            switch ((Predicate)predicateType){
                case WALL_IN_FRONT:
                    if (actionExecutor.wallInFrontOfRobot()) return true;
                    else return false;
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
     * @param leftTop top-left corner position (in px) of the grid on the CanvasWindow
     */
    @Override
    public void render(Graphics graphics, int x, int y) {
        uiController.render(graphics, ActionExecutor.getInstance().getState(), new GridLocation(x, y));
    }

}
