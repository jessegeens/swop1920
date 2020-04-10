package robotgameworld;

import gameworldapi.Direction;
import gameworldapi.GameWorldState;
import org.junit.Test;

import static org.junit.Assert.*;

public class RobotGameWorldTest {

    /**
     * @author Jesse Geens
     */
    @Test
    public void locationEqualsTest(){
        GridLocation location1 = new GridLocation(1, 1);
        GridLocation location2 = new GridLocation(1, 1);
        assertTrue(location1.equals(location2));
    }

    /**
     * @author Jesse Geens
     * */
    @Test
    public void WallInFrontTestUnder() {
        RobotGameWorld gameWorld = new RobotGameWorld();
        GameWorldState state = new RobotGameWorldState(Direction.DOWN, new GridLocation(0, 1));
        gameWorld.restore(state);
        assertTrue(gameWorld.evaluate(Predicate.WALL_IN_FRONT));
    }

    /**
     * @author Jesse Geens
     * */
    @Test
    public void WallInFrontTestRight() {
        RobotGameWorld gameWorld = new RobotGameWorld();
        GameWorldState state = new RobotGameWorldState(Direction.RIGHT, new GridLocation(0, 1));
        gameWorld.restore(state);
        assertFalse(gameWorld.evaluate(Predicate.WALL_IN_FRONT));
    }
}