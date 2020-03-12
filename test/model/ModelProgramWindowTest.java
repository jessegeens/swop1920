package model;

import org.junit.Test;
import utilities.Blocktype;
import utilities.Location;
import static org.junit.Assert.*;

public class ModelProgramWindowTest {

    @Test
    public void programWindowTest() {
        ModelProgramWindow pw = new ModelProgramWindow(200, 200);
        pw.updateLocationBlocks();
    }
}
