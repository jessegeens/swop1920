package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import utilities.*;

public class UIGrid implements UIWindow {

    private int xPosition;
    private int yPosition;
    private int width;
    private int height;

    private int cellSize;
    private Location goalCell;
    private Location robotLocation;
    private Direction robotDirection;
    private ArrayList<Location> walls;
    
    
    // Constructor
    public UIGrid(Location position, int width, int height, int cellSize, ArrayList<Location> walls, Location robotLocation, Direction roboDirection, Location goalCell) {
        this.cellSize = cellSize;
        this.walls = walls;
        this.robotLocation = robotLocation;
        this.robotDirection = roboDirection;
        this.goalCell = goalCell;
        this.xPosition = position.getX();
        this.yPosition = position.getY();
        this.width = width;
        this.height = height;
    }
    

    /**
     * Sets the robot's new location {@link #robotLocation}
     * 
     * @param {Location} newRobotLocation Location the robot should now have
     */
    public void updateRobotLocation(Location newRobotLocation){
        this.robotLocation = newRobotLocation;

    }

    /**
     * Sets the robot's new direction {@link #robotDirection}
     * 
     * @param {Direction} newRobotDirection Direction the robot should now have
     */
    public void updateRobotDirection(Direction newRobotDirection){
        this.robotDirection = newRobotDirection;

    }


    //TODO add graphics Graphics g

    /**
     * This function renders the complete grid:
     *  * the grid itself
     *  * the walls, stored in {@link UIGrid#walls}
     *  * the goal cell, stored in {@link UIGrid#goalCell}
     *  * the robot, with direction stored in {@link UIGrid#robotDirection}
     *     and location stored in {@link UIGrid#robotLocation}
     * 
     * @param g The Graphics object on which the rendering happens
     */
    @Override
    public void render(Graphics g) {
        renderGrid(g);
        renderWalls(g);
        renderGoalCell(g);
        renderRobot(g);
    }


    /**
     * This function renders the grid itself (e.g. the raster lines, given by {@link #dimension})
     * 
     * @param {Graphics} g The Graphics object on which the rendering happens
     */
    public void renderGrid(Graphics g) {
        g.setColor(Color.GRAY);
        for(int x = 0; x < this.width; x++){
            for(int y = 0; y < this.height; y++){
                g.drawRect(xPosition + x * cellSize, yPosition + y*cellSize, cellSize, cellSize);

            }
        }
    }

    /**
     * This function renders all the walls (stored in {@link #walls}) on the grid
     * 
     * @param {Graphics} g The Graphics object on which the rendering happens
     */
    public void renderWalls(Graphics g) {
        g.setColor(Color.BLACK);
        for(Location wall : walls){
            g.fillRect(xPosition + wall.getX()*cellSize, yPosition + wall.getY()*cellSize,cellSize, cellSize);
        }
    }

    /**
     * This function renders the goal cell (stored in {@link #goalCell}) on the grid
     * 
     * @param {Graphics} g The Graphics object on which the rendering happens
     */
    public void renderGoalCell(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillRect(xPosition + goalCell.getX()*cellSize, yPosition + goalCell.getY()*cellSize,cellSize, cellSize);
    }


    /**
     * This function renders the robot (with direction stored in {@link #robotDirection}
     * and location stored in {@link #robotLocation}) on the grid
     * 
     * @param {Graphics} g The Graphics object on which the rendering happens
     */
    public void renderRobot(Graphics g) {
        int x = xPosition + robotLocation.getX();
        int y = yPosition + robotLocation.getY();
        g.setColor(Color.RED);
        g.fillRect(xPosition + robotLocation.getX()*cellSize, yPosition + robotLocation.getY()*cellSize,cellSize, cellSize);

        g.setColor(Color.WHITE);

        //TODO: draw arrow at correct place

        if (this.robotDirection.getDirection() == Direction.UP || this.robotDirection.getDirection() == Direction.DOWN){

            g.drawLine(xPosition + robotLocation.getX()*cellSize + cellSize / 2, yPosition + robotLocation.getY()*cellSize , robotLocation.getX()*cellSize + cellSize / 2,robotLocation.getY()*cellSize + cellSize);
            if(this.robotDirection.getDirection() == Direction.UP){
                g.drawLine(xPosition + robotLocation.getX()*cellSize + cellSize / 2, yPosition + robotLocation.getY()*cellSize, xPosition + robotLocation.getX()*cellSize + cellSize / 4, yPosition + robotLocation.getY()*cellSize + cellSize / 4);
                g.drawLine(robotLocation.getX()*cellSize + cellSize / 2, robotLocation.getY()*cellSize, robotLocation.getX()*cellSize + 3 * cellSize / 4, robotLocation.getY()*cellSize + cellSize / 4);
            }
            else if(this.robotDirection.getDirection() == Direction.DOWN){

                g.drawLine(robotLocation.getX()*cellSize + cellSize / 2, robotLocation.getY()*cellSize + cellSize, robotLocation.getX()*cellSize + cellSize / 4, robotLocation.getY()*cellSize + 3 * cellSize / 4);
                g.drawLine(robotLocation.getX()*cellSize + cellSize / 2, robotLocation.getY()*cellSize + cellSize, robotLocation.getX()*cellSize + 3 * cellSize / 4, robotLocation.getY()*cellSize + 3 * cellSize / 4);
            
            }
        }

        else if (this.robotDirection.getDirection() == Direction.LEFT || this.robotDirection.getDirection() == Direction.RIGHT){

            g.drawLine(xPosition + robotLocation.getX()*cellSize , yPosition + robotLocation.getY()*cellSize + cellSize / 2, xPosition + robotLocation.getX()*cellSize + cellSize, yPosition + robotLocation.getY()*cellSize  + cellSize / 2);
            if(this.robotDirection.getDirection() == Direction.LEFT){

                g.drawLine(robotLocation.getX()*cellSize , robotLocation.getY()*cellSize + cellSize / 2,robotLocation.getX()*cellSize + cellSize / 4, robotLocation.getY()*cellSize + 1 * cellSize / 4);
                g.drawLine(robotLocation.getX()*cellSize , robotLocation.getY()*cellSize + cellSize / 2,robotLocation.getX()*cellSize + cellSize / 4, robotLocation.getY()*cellSize + 3 * cellSize / 4);
            }
            else if(this.robotDirection.getDirection() == Direction.RIGHT){
                g.drawLine(xPosition + robotLocation.getX()*cellSize + cellSize, robotLocation.getY()*cellSize + cellSize / 2,robotLocation.getX()*cellSize + 3 * cellSize / 4, robotLocation.getY()*cellSize + 1 * cellSize / 4);
                g.drawLine(robotLocation.getX()*cellSize + cellSize, robotLocation.getY()*cellSize + cellSize / 2,robotLocation.getX()*cellSize + 3 * cellSize / 4, robotLocation.getY()*cellSize + 3 * cellSize / 4);
            }
        }  
    }
}