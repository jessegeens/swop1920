package ui;

import java.util.ArrayList;
import java.awt.*;

import ui.blocks.*;
import utilities.*;
import model.blocks.ModelBlock;


public class UIController {

    private UIGrid grid;
    
    private final int wWidth;  //window width
    private final int wHeight; //window height
    private final Graphics g;

    private ArrayList<UIBlock> blocks;

    public UIController(Graphics g, int windowWidth, int windowHeight, ArrayList<ModelBlock> mBlocks, GridInfo gridInfo){
        this.wWidth = windowWidth;
        this.wHeight = windowHeight;
        this.g = g;
        updateBlocks(mBlocks);
        updateGrid(gridInfo);
    }

    public void render(){
        this.renderUI();
        blocks.forEach((UIBlock block) -> block.render(g));
    }

    public void renderUI(){
        g.setColor(Color.BLACK);
        g.drawLine(this.wWidth/3, 0, this.wWidth/3, this.wHeight);
        g.drawLine(2*this.wWidth/3, 0, 2*this.wWidth/3, this.wHeight);
    }

    public void updateBlocks(ArrayList<ModelBlock> mBlocks){
        this.blocks = new ArrayList<UIBlock>();
        mBlocks.forEach((ModelBlock mBlock) -> this.blocks.add(new UIBlock(mBlock)));
    }

    public void updateGrid(GridInfo gridInfo){
        this.grid = new UIGrid(new Location(this.wWidth/3, 0), this.wWidth/ 3, this.wHeight, gridInfo.getCellSize(), gridInfo.getWalls(), gridInfo.getRobotLocation(), gridInfo.getRobotDirection(), gridInfo.getGoalCell());
    }


    

}