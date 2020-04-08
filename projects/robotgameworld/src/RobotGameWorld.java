import java.awt.*;
import java.util.ArrayList;

public class RobotGameWorld implements GameWorld {

    private ActionExecutor actionExecutor = ActionExecutor.getInstance();
    private UIController uiController = new UIController();

    public static void main(String[] args) {

    }

    @Override
    public ActionResult perform(ActionType actionType) {
        return actionExecutor.execute((Action) actionType);
    }

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

    @Override
    public GameWorldState getSnapshot() {
        return actionExecutor.getState();
    }

    @Override
    public void restore(GameWorldState gameWorldState) {
        actionExecutor.setState((RobotGameWorldState) gameWorldState);
    }

    @Override
    public void render(Graphics graphics, GameWorldState gameWorldState, Location leftTop, int width, int height) {
        uiController.render(graphics, (RobotGameWorldState) gameWorldState, (GridLocation) leftTop, width, height);
    }

}
