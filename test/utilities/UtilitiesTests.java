package utilities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import model.ModelGrid;
import org.junit.*;

import java.util.ArrayList;

public class UtilitiesTests {

    @Test
    public void getRightType() {
        Blocktype blkt = new Blocktype(1);
        assertEquals(1, blkt.getType());
    }

    @Test(expected = IllegalArgumentException.class)
    public void getWrongType() {
        Blocktype blkt = new Blocktype(-1);
    }

    @Test
    public void getRightTitle() {
        Blocktype blkt = new Blocktype(5);
        assertEquals("Wall in front", blkt.getTitle());
    }

    @Test(expected = IllegalArgumentException.class)
    public void getWrongTitle() {
        Blocktype blkt = new Blocktype(7);
    }

    @Test
    public void getDistanceIsZero() {
        WindowLocation loc1 = new WindowLocation(1, 1);
        WindowLocation loc2 = new WindowLocation(1, 1);
        assertEquals(0, loc1.getDistance(loc2));
        assertEquals(0, loc2.getDistance(loc1));
    }

    @Test
    public void getVectorSum() {
        WindowLocation loc1 = new WindowLocation(2, 3);
        WindowLocation loc2 = new WindowLocation(0, 0);
        WindowLocation loc3 = new WindowLocation(4, 1);
        WindowLocation loc4 = new WindowLocation(6, 4);
        assertEquals(loc4.getX(), loc1.add(loc3).getX());
        assertEquals(loc4.getY(), loc1.add(loc3).getY());
        assertEquals(loc1.getX(), loc1.add(loc2).getX());
        assertEquals(loc1.getY(), loc1.add(loc2).getY());
    }

    @Test
    public void getComponentSum() {
        WindowLocation loc1 = new WindowLocation(2, 3);
        assertEquals(4, loc1.add(2, 0).getX());
        assertEquals(3, loc1.add(2, 0).getY());
    }

    @Test
    public void getRightDirection() {
        Direction dir = new Direction(2);
        assertEquals(2, dir.getDirection());
    }

    @Test(expected = IllegalArgumentException.class)
    public void getWrongDirection() {
        Direction dir = new Direction(4);
    }

    @Test
    public void turn() {
        Direction dir1 = new Direction(3);
        dir1.turnLeft();
        assertEquals(2, dir1.getDirection());
        Direction dir2 = new Direction(3);
        dir2.turnRight();
        assertEquals(0, dir2.getDirection());
    }

    @Test
    public void gridInfoTest() {
        int cellSize = 100;
        GridLocation goal = new GridLocation(10, 15);
        GridLocation rPos = new GridLocation(20, 25);
        Direction rDir = new Direction(Direction.RIGHT);
        ArrayList<GridLocation> walls = new ArrayList<>();
        ModelGrid grid = new ModelGrid(10, 10, goal, rPos, rDir, walls, 100);
        GridInfo gridInfo = new GridInfo(grid, cellSize);
        assertEquals(10, gridInfo.getGoalCell().getX());
        assertEquals(25, gridInfo.getRobotLocation().getY());
        assertNotNull(gridInfo.getRobotDirection());
    }
}