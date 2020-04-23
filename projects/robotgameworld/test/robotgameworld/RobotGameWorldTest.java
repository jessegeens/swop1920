package robotgameworld;

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
        assertEquals(location1, location2);
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

    @Test
    public void executeMove() {
        RobotGameWorld gameWorld = new RobotGameWorld();
        GameWorldState state = new RobotGameWorldState(Direction.RIGHT, new GridLocation(0, 0));
        gameWorld.restore(state);
        gameWorld.perform(Action.MOVE_FORWARD);
        assertEquals(new GridLocation(1, 0), ((RobotGameWorldState) gameWorld.getSnapshot()).getRobotLocation());
    }

    @Test
    public void executeTurn() {
        RobotGameWorld gameWorld = new RobotGameWorld();
        GameWorldState state = new RobotGameWorldState(Direction.RIGHT, new GridLocation(0, 0));
        gameWorld.restore(state);
        gameWorld.perform(Action.TURN_RIGHT);
        assertEquals(Direction.DOWN, ((RobotGameWorldState) gameWorld.getSnapshot()).getRobotDirection());
    }
}