package ui;

import java.util.ArrayList;
import java.awt.*;

import ui.blocks.*;
import utilities.*;
import model.blocks.ModelBlock;
import model.*;

/**
 * Class controlling the UI elements.
 */
public class UIController {

    private UIGrid grid;
    
    private final int wWidth;  //window width
    private final int wHeight; //window height

    private ArrayList<UIBlock> blocks;

    // Constructor
    public UIController(int windowWidth, int windowHeight, ArrayList<ModelBlock> mBlocks, ProgramState programState){
        this.wWidth = windowWidth;
        this.wHeight = windowHeight;
        updateBlocks(mBlocks);
        updateGrid(programState);
    }

    /**
     * Method to render the UI elements.
     * @param g graphics object where the UI elements are rendered.
     */
    public void render(Graphics g){
        System.out.println("Rendering UI, blocks and grid");
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
     * @param programState variable containing information about the grid.
     */
    public void updateGrid(ProgramState programState){
        if (programState == null) throw new IllegalArgumentException("updating grid with an illegal programState (is null)");
        System.out.println("updating grid: " + programState.toString());
        this.grid = new UIGrid(new Location(2*this.wWidth/3, 0), this.wWidth/ 3, this.wHeight, programState.getCellSize(), programState.getWalls(), programState.getRobotLocation(), programState.getRobotDirection(), programState.getGoalCell());
    }


    

}