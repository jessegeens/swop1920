import java.awt.*;
import java.util.ArrayList;

public class UIController {

    private final int CELL_SIZE = 50;
    
    // Constructor
    public UIController(){}


    public void render(Graphics g, RobotGameWorldState state, GridLocation leftTop, int width, int height){
        renderGrid(g, width, height, leftTop);
        renderWalls(g, ActionExecutor.getInstance().getWalls(), leftTop);
        renderGoalCell(g, ActionExecutor.getInstance().getGoalCell(), leftTop);
        renderRobot(g, state.getRobotLocation(), state.getRobotDirection(), leftTop);
    }

    /**
     * Render function for the grid without using class variables
     * @param g Graphics object
     * @param width the width of the grid
     * @param height the height of the grid
     * @param gridPosition the location where the grid starts
     * @author Jesse Geens
     */
    public void renderGrid(Graphics g, int width, int height, GridLocation gridPosition){
        g.setColor(Color.GRAY);
        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                g.drawRect(gridPosition.getX() + x * CELL_SIZE, gridPosition.getY() + y*CELL_SIZE, CELL_SIZE, CELL_SIZE);
            }
        }
    }

    /**
     * Render function for the walls without using class variables
     * @param g Graphics object
     * @param walls arraylist of locations where the walls are
     * @param gridPosition the position of the left top corner of the grid
     * @author Jesse Geens
     */
    public void renderWalls(Graphics g, ArrayList<GridLocation> walls, Location gridPosition){
        g.setColor(Color.BLACK);
        for(GridLocation wall : walls){
            g.fillRect(gridPosition.getX() + wall.getX()*CELL_SIZE, gridPosition.getY() + wall.getY()*CELL_SIZE,CELL_SIZE, CELL_SIZE);
        }
    }

    /**
     * Render function for the goal cell without using class variables
     * @param g Graphics object
     * @param goalCell the location of the goal cell
     * @param gridPosition the position of the left top corner of the grid
     * @author Jesse Geens
     */
    public void renderGoalCell(Graphics g, GridLocation goalCell, GridLocation gridPosition){
        g.setColor(Color.YELLOW);
        g.fillRect(gridPosition.getX() + goalCell.getX()*CELL_SIZE, gridPosition.getY() + goalCell.getY()*CELL_SIZE, CELL_SIZE, CELL_SIZE);
    }

    /**
     * Render function for the robot without using class variables
     * @param g Graphics object
     * @param robotLocation the location of the robot
     * @param robotDirection the direction of the robot
     * @param gridPosition the position of the left top corner of the grid
     * @author Oberon Swings
     */
    public void renderRobot(Graphics g, Location robotLocation, Direction robotDirection, GridLocation gridPosition){
        g.setColor(Color.RED);
        g.fillRect(gridPosition.getX() + robotLocation.getX()*CELL_SIZE, gridPosition.getY() + robotLocation.getY()*CELL_SIZE,CELL_SIZE, CELL_SIZE);
        g.setColor(Color.BLUE);

        if (robotDirection == Direction.UP || robotDirection == Direction.DOWN){
            g.drawLine(gridPosition.getX() + robotLocation.getX()*CELL_SIZE + CELL_SIZE / 2, gridPosition.getY() + robotLocation.getY()*CELL_SIZE , gridPosition.getX() + robotLocation.getX()*CELL_SIZE + CELL_SIZE / 2, gridPosition.getY() + robotLocation.getY()*CELL_SIZE + CELL_SIZE);
            if(robotDirection == Direction.UP){
                g.drawLine(gridPosition.getX() + robotLocation.getX()*CELL_SIZE + CELL_SIZE / 2, gridPosition.getY() + robotLocation.getY()*CELL_SIZE, gridPosition.getX() + robotLocation.getX()*CELL_SIZE + CELL_SIZE / 4, gridPosition.getY() + robotLocation.getY()*CELL_SIZE + CELL_SIZE / 4);
                g.drawLine(gridPosition.getX() + robotLocation.getX()*CELL_SIZE + CELL_SIZE / 2, gridPosition.getY() + robotLocation.getY()*CELL_SIZE, gridPosition.getX() + robotLocation.getX()*CELL_SIZE + 3 * CELL_SIZE / 4, gridPosition.getY() + robotLocation.getY()*CELL_SIZE + CELL_SIZE / 4);
            }
            else{
                g.drawLine(gridPosition.getX() + robotLocation.getX()*CELL_SIZE + CELL_SIZE / 2, gridPosition.getY() + robotLocation.getY()*CELL_SIZE + CELL_SIZE, gridPosition.getX() + robotLocation.getX()*CELL_SIZE + CELL_SIZE / 4, gridPosition.getY() + robotLocation.getY()*CELL_SIZE + 3 * CELL_SIZE / 4);
                g.drawLine(gridPosition.getX() + robotLocation.getX()*CELL_SIZE + CELL_SIZE / 2, gridPosition.getY() + robotLocation.getY()*CELL_SIZE + CELL_SIZE, gridPosition.getX() + robotLocation.getX()*CELL_SIZE + 3 * CELL_SIZE / 4, gridPosition.getY() + robotLocation.getY()*CELL_SIZE + 3 * CELL_SIZE / 4);
            }
        }

        else if (robotDirection == Direction.LEFT || robotDirection == Direction.RIGHT){
            g.drawLine(gridPosition.getX() + robotLocation.getX()*CELL_SIZE , gridPosition.getY() + robotLocation.getY()*CELL_SIZE + CELL_SIZE / 2, gridPosition.getX() + robotLocation.getX()*CELL_SIZE + CELL_SIZE, gridPosition.getY() + robotLocation.getY()*CELL_SIZE  + CELL_SIZE / 2);
            if(robotDirection == Direction.LEFT){
                g.drawLine(gridPosition.getX() + robotLocation.getX()*CELL_SIZE , gridPosition.getY() + robotLocation.getY()*CELL_SIZE + CELL_SIZE / 2, gridPosition.getX() + robotLocation.getX()*CELL_SIZE + CELL_SIZE / 4, gridPosition.getY() + robotLocation.getY()*CELL_SIZE + 1 * CELL_SIZE / 4);
                g.drawLine(gridPosition.getX() + robotLocation.getX()*CELL_SIZE , gridPosition.getY() + robotLocation.getY()*CELL_SIZE + CELL_SIZE / 2, gridPosition.getX() + robotLocation.getX()*CELL_SIZE + CELL_SIZE / 4, gridPosition.getY() + robotLocation.getY()*CELL_SIZE + 3 * CELL_SIZE / 4);
            }
            else{
                g.drawLine(gridPosition.getX() + robotLocation.getX()*CELL_SIZE + CELL_SIZE, gridPosition.getY() + robotLocation.getY()*CELL_SIZE + CELL_SIZE / 2, gridPosition.getX() + robotLocation.getX()*CELL_SIZE + 3 * CELL_SIZE / 4, gridPosition.getY() + robotLocation.getY()*CELL_SIZE + 1 * CELL_SIZE / 4);
                g.drawLine(gridPosition.getX() + robotLocation.getX()*CELL_SIZE + CELL_SIZE, gridPosition.getY() + robotLocation.getY()*CELL_SIZE + CELL_SIZE / 2, gridPosition.getX() + robotLocation.getX()*CELL_SIZE + 3 * CELL_SIZE / 4, gridPosition.getY() + robotLocation.getY()*CELL_SIZE + 3 * CELL_SIZE / 4);
            }
        }
    }

}