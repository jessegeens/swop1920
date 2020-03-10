package tests.utilities;

import static org.junit.Assert.assertEquals;
import org.junit.*;
import utilities.Blocktype;

public class BlocktypeTests {

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
        assertEquals("Wall in front", blkt.getType());
    }

    @Test(expected = IllegalArgumentException.class)
    public void getWrongTitle() {
        Blocktype blkt = new Blocktype(7);
    }
}