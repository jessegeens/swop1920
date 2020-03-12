package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import utilities.Direction;
import utilities.Location;

import java.util.ArrayList;

public class ModelGridTest {

    @Test
    public void TestModelGrid() {
        Location loc = new Location(40, 40);
        Location rPos = new Location(0, 0);
        Direction rDir = new Direction(0);
        ModelRobot robot = new ModelRobot(rPos, rDir);
        ModelGrid grid = new ModelGrid(200, 200, loc, robot);
        assertEquals(robot, grid.getRobot());
        assertEquals(loc, grid.getGoalCell());
        Location wall1 = new Location(12, 15);
        Location wall2 = new Location(22, 24);
        ArrayList<Location> lst = new ArrayList<>();
        lst.add(wall1);
        lst.add(wall2);
        grid.setWalls(lst);
        assertNotNull(lst);
        assertEquals(22, lst.get(1).getX());
        assertEquals(15, grid.getWalls().get(0).getY());
    }

}
