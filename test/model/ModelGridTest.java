package model;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import utilities.Direction;
import utilities.Location;

public class ModelGridTest {

    @Test
    public void TestGoalCell() {
        Location loc = new Location(40, 40);
        Location rPos = new Location(0, 0);
        Direction rDir = new Direction(0);
        ModelRobot robot = new ModelRobot(rPos, rDir);
        ModelGrid grid = new ModelGrid(200, 200, loc, robot);
        assertEquals(robot, grid.getRobot());
        assertEquals(loc, grid.getGoalCell());
    }

}
