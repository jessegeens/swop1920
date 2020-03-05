package ui.blocks;
import ui.*;
import java.awt.*;
import utilities.*;

public abstract class UIBlock extends UIElement {

    final int width = 120;
    final int height = 120;

    public UIBlock(Location location) {
        super(location);
    }

    public abstract void render(Graphics g);

    
    
    
}
