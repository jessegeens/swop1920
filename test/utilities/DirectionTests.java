package tests.utilities;

import static org.junit.Assert.assertEquals;
import org.junit.*;
import utilities.Direction;

public class DirectionTests {

    @Test
    public void getRightDirection() {
        Direction dir = new Direction(2);
        assertEquals(2, dir.getDirection());
    }

    @Test(expected = IllegalArgumentException)
    public void getWrongDirection() {
        Direction dir = new Direction(4);
    }

    @Test
    public void turn() {
        Direction dir1 = new Direction(3);
        dir.turnLeft();
        assertEquals(2, dir1.getDirection());
        Direction dir2 = new Direction(1);
        dir2.turnRight();
        assertEquals(0, dir2.getDirection());
    }
}