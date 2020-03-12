package model;

import org.junit.Test;
import utilities.Direction;
import utilities.Location;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.TestCase.assertEquals;

public class ModelRobotTest {

    @Test
    public void testModelRobot() {
        Location pos = new Location(2,2);
        Direction dir = new Direction(Direction.RIGHT);
        ModelRobot rb = new ModelRobot(pos, dir);
        rb.turnLeft();
        rb.turnRight();
        assertNotNull(rb.getDirection());
    }
}
