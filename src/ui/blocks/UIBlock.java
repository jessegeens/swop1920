package ui.blocks;
import ui.*;
import java.awt.*;
import utilities.*;

abstract class UIBlock extends UIElement {

    public UIBlock(Location location) {
        super(location);
    }

    public abstract void render(Graphics g);

    
    
    
}
