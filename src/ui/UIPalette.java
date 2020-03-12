package ui;

import java.util.ArrayList;
import java.awt.*;
import ui.blocks.*;
import model.blocks.ModelBlock;

public class UIPalette implements UIWindow {
    ArrayList<UIBlock> blocks = new ArrayList<UIBlock>();
    Graphics g;

    public UIPalette(Graphics g, ArrayList<ModelBlock> mBlocks){
        this.g = g;
        mBlocks.forEach((ModelBlock block) -> blocks.add(new UIBlock(block)));
    }

    @Override
    public void render(Graphics g) {
        blocks.forEach((UIBlock block) -> block.render(g));
    }
}