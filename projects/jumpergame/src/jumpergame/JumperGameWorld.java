package jumpergame;

import gameworldapi.*;

import java.awt.*;

public class JumperGameWorld implements GameWorld {

    private UIController uiController = new UIController();

    @Override
    public ActionResult perform(ActionType actionType) {
        return ActionExecutor.getInstance().execute((Action) actionType);
    }

    @Override
    public Boolean evaluate(PredicateType predicateType) {
        switch ((Predicate) predicateType){
            case BLOCK_ABOVE:
                return ActionExecutor.getInstance().blockAbovePlayer();
        }
        throw new IllegalArgumentException("Invalid predicate given");
    }

    @Override
    public GameWorldState getSnapshot() {
    return ActionExecutor.getInstance().getState();
    }

    @Override
    public void restore(GameWorldState gameWorldState) {
        ActionExecutor.getInstance().setState((JumperGameWorldState) gameWorldState);
    }

    @Override
    public void render(Graphics graphics, int x, int y) {
        uiController.render(graphics, new GridLocation(x, y));
    }
}
