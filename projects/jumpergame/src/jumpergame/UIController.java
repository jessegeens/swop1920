package jumpergame;

import java.awt.*;
import java.util.ArrayList;

public class UIController {

    //Width and height of a cell in px
    private final int CELL_SIZE = 40;

    public void render(Graphics graphics, GridLocation gridLocation) {
        this.renderGrid(graphics, ActionExecutor.getInstance().getGridWidth(), ActionExecutor.getInstance().getGridHeight(), gridLocation);
        this.renderBlocks(graphics, ActionExecutor.getInstance().getBlocks(), gridLocation);
        this.renderPrincess(graphics, ActionExecutor.getInstance().getPrincess(), gridLocation);
        this.renderPlayer(graphics, ActionExecutor.getInstance().getState().getPlayerLocation() ,gridLocation);
    }

    public void renderGrid(Graphics g, int width, int height, GridLocation gridPosition){
        g.setColor(Color.GRAY);
        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                g.drawRect(gridPosition.getX() + x * CELL_SIZE, gridPosition.getY() + y*CELL_SIZE, CELL_SIZE, CELL_SIZE);
            }
        }
    }

    public void renderPrincess(Graphics g, GridLocation position, GridLocation gridPosition){
        g.setColor(Color.PINK);
        g.fillRect(gridPosition.getX() + position.getX()*CELL_SIZE, gridPosition.getY() + position.getY()*CELL_SIZE, CELL_SIZE, CELL_SIZE);
    }

    public void renderBlocks(Graphics g, ArrayList<GridLocation> blocks, GridLocation gridPosition){
        g.setColor(new Color(100, 45, 0)); //Brown
        for(GridLocation block : blocks){
            g.fillRect(gridPosition.getX() + block.getX()*CELL_SIZE, gridPosition.getY() + block.getY()*CELL_SIZE,CELL_SIZE, CELL_SIZE);
        }
    }

    public void renderPlayer(Graphics g, GridLocation position, GridLocation gridPosition){
        g.setColor(Color.RED);
        g.fillRect(gridPosition.getX() + position.getX()*CELL_SIZE, gridPosition.getY() + position.getY()*CELL_SIZE, CELL_SIZE, CELL_SIZE);
    }
}
