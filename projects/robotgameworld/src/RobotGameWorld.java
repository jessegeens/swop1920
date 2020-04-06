import java.awt.*;

public class RobotGameWorld implements GameWorld {
    public static void main(String[] args) {

    }

    @Override
    public ActionResult perform(ActionType actionType) {
        switch ((Action) actionType){
            case MOVE_FORWARD:
                break;
            case TURN_LEFT:
                break;
            case TURN_RIGHT:
                break;
        }
        return null;
    }

    @Override
    public Boolean evaluate(PredicateType predicateType) {
        return predicateType.evaluate();
    }

    @Override
    public GameWorldState getSnapshot() {
        return null;
    }

    @Override
    public void restore(GameWorldState gameWorldState) {

    }

    @Override
    public void render(Graphics graphics, GameWorldState gameWorldState) {

    }
}
