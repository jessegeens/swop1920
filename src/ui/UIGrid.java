package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import utilities.*;

public class UIGrid implements UIWindow {

    private int xPosition = 0;
    private int yPosition = 0;

    

    //both cells and the grid are square so only one dimension is needed
    private int cellSize;
    private int dimension;

    private Location goalCell;
    private Location robotLocation;
    private Direction robotDirection;
    private ArrayList<Location> walls;
    
    

    public UIGrid(int cellSize, int dimension, ArrayList<Location> walls, Location robotLocation, Direction roboDirection, Location goalCell) {
        this.cellSize = cellSize;
        this.dimension = dimension;
        this.walls = walls;
        this.robotLocation = robotLocation;
        this.robotDirection = roboDirection;
        this.goalCell = goalCell;
    }
    
    public void updateRobotLocation(Location newRobotLocation){
        this.robotLocation = newRobotLocation;

    }

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
     * This function renders the grid itself (e.g. the raster lines, given by {@link UIGrid#dimension})
     * 
     * @param g The Graphics object on which the rendering happens
     */
    public void renderGrid(Graphics g) {
        g.setColor(Color.GRAY);
        for(int x = 0; x < this.dimension; x++){
            for(int y = 0; y < this.dimension; y++){
                g.drawRect(xPosition + x * cellSize, yPosition + y*cellSize, cellSize, cellSize);

            }
        }
    }

    /**
     * This function renders all the walls (stored in {@link UIGrid#walls}) on the grid
     * 
     * @param g The Graphics object on which the rendering happens
     */
    public void renderWalls(Graphics g) {
        g.setColor(Color.BLACK);
        for(Location wall : walls){
            g.fillRect(wall.getX()*cellSize,wall.getY()*cellSize,cellSize, cellSize);
        }
    }

    /**
     * This function renders the goal cell (stored in {@link UIGrid#goalCell}) on the grid
     * 
     * @param g The Graphics object on which the rendering happens
     */
    public void renderGoalCell(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillRect(goalCell.getX()*cellSize,goalCell.getY()*cellSize,cellSize, cellSize);
    }


    /**
     * This function renders the robot (with direction stored in {@link UIGrid#robotDirection}
     * and location stored in {@link UIGrid#robotLocation}) on the grid
     * 
     * @param g The Graphics object on which the rendering happens
     */
    public void renderRobot(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(robotLocation.getX()*cellSize,robotLocation.getY()*cellSize,cellSize, cellSize);

        g.setColor(Color.WHITE);

        if (this.robotDirection.getDirection() == Direction.UP || this.robotDirection.getDirection() == Direction.DOWN){

            g.drawLine(robotLocation.getX()*cellSize + cellSize / 2, robotLocation.getY()*cellSize , robotLocation.getX()*cellSize + cellSize / 2,robotLocation.getY()*cellSize + cellSize);
            if(this.robotDirection.getDirection() == Direction.UP){
                g.drawLine(robotLocation.getX()*cellSize + cellSize / 2, robotLocation.getY()*cellSize, robotLocation.getX()*cellSize + cellSize / 4, robotLocation.getY()*cellSize + cellSize / 4);
                g.drawLine(robotLocation.getX()*cellSize + cellSize / 2, robotLocation.getY()*cellSize, robotLocation.getX()*cellSize + 3 * cellSize / 4, robotLocation.getY()*cellSize + cellSize / 4);
            }
            else if(this.robotDirection.getDirection() == Direction.DOWN){

                g.drawLine(robotLocation.getX()*cellSize + cellSize / 2, robotLocation.getY()*cellSize + cellSize, robotLocation.getX()*cellSize + cellSize / 4, robotLocation.getY()*cellSize + 3 * cellSize / 4);
                g.drawLine(robotLocation.getX()*cellSize + cellSize / 2, robotLocation.getY()*cellSize + cellSize, robotLocation.getX()*cellSize + 3 * cellSize / 4, robotLocation.getY()*cellSize + 3 * cellSize / 4);
            
            }
        }

        else if (this.robotDirection.getDirection() == Direction.LEFT || this.robotDirection.getDirection() == Direction.RIGHT){

            g.drawLine(robotLocation.getX()*cellSize , robotLocation.getY()*cellSize + cellSize / 2, robotLocation.getX()*cellSize + cellSize,robotLocation.getY()*cellSize  + cellSize / 2);
            if(this.robotDirection.getDirection() == Direction.LEFT){

                g.drawLine(robotLocation.getX()*cellSize , robotLocation.getY()*cellSize + cellSize / 2,robotLocation.getX()*cellSize + cellSize / 4, robotLocation.getY()*cellSize + 1 * cellSize / 4);
                g.drawLine(robotLocation.getX()*cellSize , robotLocation.getY()*cellSize + cellSize / 2,robotLocation.getX()*cellSize + cellSize / 4, robotLocation.getY()*cellSize + 3 * cellSize / 4);
            }
            else if(this.robotDirection.getDirection() == Direction.RIGHT){
                g.drawLine(robotLocation.getX()*cellSize + cellSize, robotLocation.getY()*cellSize + cellSize / 2,robotLocation.getX()*cellSize + 3 * cellSize / 4, robotLocation.getY()*cellSize + 1 * cellSize / 4);
                g.drawLine(robotLocation.getX()*cellSize + cellSize, robotLocation.getY()*cellSize + cellSize / 2,robotLocation.getX()*cellSize + 3 * cellSize / 4, robotLocation.getY()*cellSize + 3 * cellSize / 4);
            }
        }  
    }
}