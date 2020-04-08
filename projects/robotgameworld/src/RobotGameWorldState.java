import java.util.ArrayList;

/**
 * State of the Robot game
 * Immutable class
 * @author Jesse Geens
 */
public final class RobotGameWorldState implements GameWorldState {

    private final Direction robotDirection;
    private final GridLocation robotLocation;

    public RobotGameWorldState(Direction robotDirection, GridLocation robotLocation) {
        this.robotDirection = robotDirection;
        this.robotLocation = robotLocation;
    }

    @Override
    public String toString() {
        return "[RobotGameWorldState: " + robotDirection.toString() + ", " + robotLocation.toString() + "]";
    }

    GridLocation getRobotLocation(){
        return this.robotLocation;
    }

    Direction getRobotDirection(){
        return this.robotDirection;
    }


}
