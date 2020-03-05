package ui;

import java.util.ArrayList;
import java.awt.*;
import utilities.*;
import ui.blocks.*;

public class UIPalette implements UIWindow {
    ArrayList<UIBlock> blocks = new ArrayList<UIBlock>();
    Graphics g;

    public UIPalette(Graphics g){
        this.g = g;
        blocks.add(new UIIfBlock(new Location(10, 10)));
    }

    @Override
    public void render() {
        blocks.forEach((UIBlock block) -> block.render(g));
    }
}