package ui;

import java.util.ArrayList;
import java.awt.*;

import ui.blocks.*;
import utilities.*;
import model.blocks.ModelBlock;

/**
 * Class controlling the UI elements.
 */
public class UIController {

    private UIGrid grid;
    
    private final int wWidth;  //window width
    private final int wHeight; //window height

    private ArrayList<UIBlock> blocks;

    public UIController(int windowWidth, int windowHeight, ArrayList<ModelBlock> mBlocks, GridInfo gridInfo){
        this.wWidth = windowWidth;
        this.wHeight = windowHeight;
        updateBlocks(mBlocks);
        updateGrid(gridInfo);
    }

    /**
     * Method to render the UI elements.
     * @param g graphics object where the UI elements are rendered.
     */
    public void render(Graphics g){
        this.renderUI(g);
        blocks.forEach((UIBlock block) -> block.render(g));
        this.grid.render(g);
    }

    /**
     * Help method for the render method.
     * @param g graphics object where the rendering is done.
     */
    public void renderUI(Graphics g){
        g.setColor(Color.BLACK);
        g.drawLine(this.wWidth/3, 0, this.wWidth/3, this.wHeight);
        g.drawLine(2*this.wWidth/3, 0, 2*this.wWidth/3, this.wHeight);
    }

    /**
     * Method to update a list of block elements.
     * @param mBlocks list of blocks to be updated
     */
    public void updateBlocks(ArrayList<ModelBlock> mBlocks){
        this.blocks = new ArrayList<UIBlock>();
        mBlocks.forEach((ModelBlock mBlock) -> this.blocks.add(new UIBlock(mBlock)));
    }

    /**
     * Method to update the grid.
     * @param gridInfo variable containing information about the grid.
     */
    public void updateGrid(GridInfo gridInfo){
        this.grid = new UIGrid(new Location(2*this.wWidth/3, 0), this.wWidth/ 3, this.wHeight, gridInfo.getCellSize(), gridInfo.getWalls(), gridInfo.getRobotLocation(), gridInfo.getRobotDirection(), gridInfo.getGoalCell());
    }


    

}