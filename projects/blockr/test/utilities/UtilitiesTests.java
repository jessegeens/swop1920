package utilities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import model.ModelGrid;
import model.ProgramState;
import org.junit.*;

import java.util.ArrayList;

public class UtilitiesTests {

    @Test
    public void getRightType() {
        BlockType blkt = BlockType.MOVEFORWARD;
        assertEquals(BlockType.MOVEFORWARD, blkt);
    }

    @Test
    public void getRightTitle() {
        BlockType blkt = BlockType.WALLINFRONT;
        assertEquals("wall in front", blkt.getTitle());
    }


    @Test
    public void getDistanceIsZero() {
        Location loc1 = new Location(1, 1);
        Location loc2 = new Location(1, 1);
        assertEquals(0, loc1.getDistance(loc2));
        assertEquals(0, loc2.getDistance(loc1));
    }

    @Test
    public void getVectorSum() {
        Location loc1 = new Location(2, 3);
        Location loc2 = new Location(0, 0);
        Location loc3 = new Location(4, 1);
        Location loc4 = new Location(6, 4);
        assertEquals(loc4.getX(), loc1.add(loc3).getX());
        assertEquals(loc4.getY(), loc1.add(loc3).getY());
        assertEquals(loc1.getX(), loc1.add(loc2).getX());
        assertEquals(loc1.getY(), loc1.add(loc2).getY());
    }

    @Test
    public void getComponentSum() {
        Location loc1 = new Location(2, 3);
        assertEquals(4, loc1.add(2, 0).getX());
        assertEquals(3, loc1.add(2, 0).getY());
    }

    @Test
    public void getRightDirection() {
        //Direction dir = new Direction(2);
        //assertEquals(2, dir.getDirection());
    }

    @Test(expected = IllegalArgumentException.class)
    public void getWrongDirection() {
        //Direction dir = new Direction(4);
    }

    @Test
    public void turn() {
        //Direction dir1 = new Direction(3);
        //dir1.turnLeft();
        //assertEquals(2, dir1.getDirection());
        //Direction dir2 = new Direction(3);
        //dir2.turnRight();
        //assertEquals(0, dir2.getDirection());
    }

    @Test
    public void gridInfoTest() {
        int cellSize = 100;
        GridLocation goal = new GridLocation(10, 15);
        GridLocation rPos = new GridLocation(20, 25);
        Direction rDir = Direction.RIGHT;
        ArrayList<GridLocation> walls = new ArrayList<>();
        ProgramState state = new ProgramState(rDir, rPos, walls, goal, 100);
        ModelGrid grid = new ModelGrid(10, 10, state);
        assertEquals(10, state.getGoalCell().getX());
        assertEquals(25, state.getRobotLocation().getY());
        assertNotNull(state.getRobotDirection());
    }

    //TODO fix test (mainly because utilities became truly immutable now)
}