import java.awt.*;

public class RobotGameWorld implements GameWorld {

    private GameWorldStateFactory gameWorldStateFactory;

    public static void main(String[] args) {

    }

    @Override
    public ActionResult perform(ActionType actionType) {
        switch ((Action) actionType){
            case MOVE_FORWARD:
                if (validPosition(move(gameWorldStateFactory.getCurrentRobotGameWorldState()))){
                    gameWorldStateFactory.createNext((Action) actionType);
                    return ActionResult.SUCCESS;
                }
                else return ActionResult.FAILURE;
            case TURN_LEFT:
            case TURN_RIGHT:
                gameWorldStateFactory.createNext((Action) actionType);
                return ActionResult.SUCCESS;
        }
        return null;
    }



    @Override
    public Boolean evaluate(PredicateType predicateType) {
        if (predicateType != null){
            switch ((Predicate)predicateType){
                case NOT_WALL_IN_FRONT:
                    if (gameWorldStateFactory.getCurrentRobotGameWorldState().wallInFrontOfRobot()) return false;
                    else return true;
                case WALL_IN_FRONT:
                    if (gameWorldStateFactory.getCurrentRobotGameWorldState().wallInFrontOfRobot()) return true;
                    else return false;
            }
        }
        return false;
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

    private RobotGameWorldState move(RobotGameWorldState State){
        Location robotGridLocation;
        switch(State.getRobotDirection()){
            case UP:
                /*Location has an add function so it would be more compact to write:
                robotLocation = robotLocation.add(0,-1);*/
                robotGridLocation = new Location(State.getRobotLocation().getX(), State.getRobotLocation().getY() - 1);
                break;
            case RIGHT:
                robotGridLocation = new Location(State.getRobotLocation().getX() + 1, State.getRobotLocation().getY());
                break;
            case DOWN:
                robotGridLocation = new Location(State.getRobotLocation().getX(), State.getRobotLocation().getY() + 1);
                break;
            case LEFT:
                robotGridLocation = new Location(State.getRobotLocation().getX() - 1, State.getRobotLocation().getY());
                break;
            default:
                throw new IllegalStateException("ProgramState has an illegal direction");
        }
        return ProgramState.generateNew(State, State.getRobotDirection(), robotGridLocation);
    }

    /**
     *
     * TODO: get max grid width and height and check if it is out of bounds there
     *
     */
    private boolean validPosition(RobotGameWorldState State){
        if(State.getRobotLocation().getX() < 0) return false;
        if(State.getRobotLocation().getY() < 0) return false;
        if(State.getWalls().contains(State.getRobotLocation())) return false;
        return true;
    }


}
