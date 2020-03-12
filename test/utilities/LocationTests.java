package utilities;

import static org.junit.Assert.assertEquals;
import org.junit.*;

public class LocationTests {

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
}