package utilities;

import static org.junit.Assert.*;

import gameworldapi.GameWorldType;
import model.blocks.ModelWhileIfBlock;
import org.junit.*;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class UtilitiesTests {

    @Test
    public void connectionPoint() {
        ModelWhileIfBlock mwif = new ModelWhileIfBlock(new ProgramLocation(10, 10), true);
        assertTrue(mwif.getConnectionPoints().contains(ConnectionPoint.CAVITY_PLUG));
    }


    @Test
    public void getDistanceIsZero() {
        ProgramLocation loc1 = new ProgramLocation(1, 1);
        ProgramLocation loc2 = new ProgramLocation(1, 1);
        assertEquals(0, loc1.getDistance(loc2));
    }


    @Test
    public void getVectorSum() {
        ProgramLocation loc1 = new ProgramLocation(2, 3);
        ProgramLocation loc2 = new ProgramLocation(4, 1);
        ProgramLocation loc3 = new ProgramLocation(6, 4);
        assertEquals(loc3, loc1.add(loc2));
    }

    @Test
    public void getComponentSum() {
        ProgramLocation loc1 = new ProgramLocation(2, 3);
        assertEquals(3, loc1.add(2, 0).getY());
    }
}