package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import model.ProgramState;
import utilities.*;

public class UIGrid {

    private int xPosition;
    private int yPosition;
    private int width;
    private int height;

    private int cellSize;
    private Location goalCell;
    private Location robotGridLocation;
    private Direction robotDirection;
    private ArrayList<Location> walls;
    private Location position;
    
    
    // Constructor
    public UIGrid(Location position, int width, int height, int cellSize, ArrayList<Location> walls, Location robotGridLocation, Direction roboDirection, Location goalCell) {
        this.cellSize = cellSize;
        this.walls = walls;
        this.robotGridLocation = robotGridLocation;
        this.robotDirection = roboDirection;
        this.goalCell = goalCell;
        this.position = position;
        this.xPosition = position.getX();
        this.yPosition = position.getY();
        this.width = width;
        this.height = height;
    }


    /**
     * This function renders the complete grid:
     *  * the grid itself
     *  * the walls, stored in {@link UIGrid#walls}
     *  * the goal cell, stored in {@link UIGrid#goalCell}
     *  * the robot, with direction stored in {@link UIGrid#robotDirection}
     *     and location stored in {@link UIGrid#robotGridLocation}
     * 
     * @param g The Graphics object on which the rendering happens
     */
    public void render(Graphics g) {
        System.out.println("Rendering grid");
        renderGrid(g, width, height, position, cellSize);
        renderWalls(g, walls, position, cellSize);
        renderGoalCell(g, goalCell, position, cellSize);
        renderRobot(g, robotGridLocation, robotDirection, position, cellSize);
    }


    /**
     * This function renders the grid itself (e.g. the raster lines, given by dimension)
     * 
     * @param g The Graphics object on which the rendering happens
     */
    public void renderGrid(Graphics g) {
        g.setColor(Color.GRAY);
        for(int x = 0; x < this.width; x++){
            for(int y = 0; y < this.height; y++){
                g.drawRect(xPosition + x * cellSize, yPosition + y*cellSize, cellSize, cellSize);
                //System.out.println("drawing line at " + x + ", " + y);
            }
        }
    }

    /**
     * Render function for the grid without using class variables
     * @param g Graphics object
     * @param width the width of the grid
     * @param height the height of the grid
     * @param gridLocation the location where the grid starts
     * @param cellSize the size of the grid cells
     * @author Oberon Swings
     */
    public void renderGrid(Graphics g, int width, int height, Location gridLocation, int cellSize){
        g.setColor(Color.GRAY);
        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                g.drawRect(gridLocation.getX() + x * cellSize, gridLocation.getY() + y*cellSize, cellSize, cellSize);
            }
        }
    }

    /**
     * This function renders all the walls (stored in {@link #walls}) on the grid
     * 
     * @param g The Graphics object on which the rendering happens
     */
    public void renderWalls(Graphics g) {
        g.setColor(Color.BLACK);
        for(Location wall : walls){
            g.fillRect(xPosition + wall.getX()*cellSize, yPosition + wall.getY()*cellSize,cellSize, cellSize);
        }
    }

    /**
     * Render function for the walls without using class variables
     * @param g Graphics object
     * @param walls arraylist of locations where the walls are
     * @param gridLocation the location where the grid starts
     * @param cellSize the size of the grid cells
     * @author Oberon Swings
     */
    public void renderWalls(Graphics g, ArrayList<Location> walls, Location gridLocation, int cellSize){
        g.setColor(Color.BLACK);
        for(Location wall : walls){
            g.fillRect(gridLocation.getX() + wall.getX()*cellSize, gridLocation.getY() + wall.getY()*cellSize,cellSize, cellSize);
        }
    }

    /**
     * This function renders the goal cell (stored in {@link #goalCell}) on the grid
     * 
     * @param g The Graphics object on which the rendering happens
     */
    public void renderGoalCell(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillRect(xPosition + goalCell.getX()*cellSize, yPosition + goalCell.getY()*cellSize,cellSize, cellSize);
    }

    /**
     * Render function for the goal cell without using class variables
     * @param g Graphics object
     * @param goalCell the location of the goal cell
     * @param gridLocation the location where the grid starts
     * @param cellSize the size of the grid cells
     */
    public void renderGoalCell(Graphics g, Location goalCell, Location gridLocation, int cellSize){
        g.setColor(Color.YELLOW);
        g.fillRect(gridLocation.getX() + goalCell.getX()*cellSize, gridLocation.getY() + goalCell.getY()*cellSize,cellSize, cellSize);
    }


    /**
     * This function renders the robot (with direction stored in {@link #robotDirection}
     * and location stored in {@link #robotGridLocation}) on the grid
     * 
     * @param g The Graphics object on which the rendering happens
     */
    public void renderRobot(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(xPosition + robotGridLocation.getX()*cellSize, yPosition + robotGridLocation.getY()*cellSize,cellSize, cellSize);
        System.out.println("RENDERING");
        System.out.println(robotGridLocation.getX());
        System.out.println(robotGridLocation.getY());
        g.setColor(Color.BLUE);

        if (this.robotDirection == Direction.UP || this.robotDirection == Direction.DOWN){

            g.drawLine(xPosition + robotGridLocation.getX()*cellSize + cellSize / 2, yPosition + robotGridLocation.getY()*cellSize , xPosition + robotGridLocation.getX()*cellSize + cellSize / 2, yPosition + robotGridLocation.getY()*cellSize + cellSize);
            if(this.robotDirection == Direction.UP){
                g.drawLine(xPosition + robotGridLocation.getX()*cellSize + cellSize / 2, yPosition + robotGridLocation.getY()*cellSize, xPosition + robotGridLocation.getX()*cellSize + cellSize / 4, yPosition + robotGridLocation.getY()*cellSize + cellSize / 4);
                g.drawLine(xPosition + robotGridLocation.getX()*cellSize + cellSize / 2, yPosition + robotGridLocation.getY()*cellSize, xPosition + robotGridLocation.getX()*cellSize + 3 * cellSize / 4, yPosition + robotGridLocation.getY()*cellSize + cellSize / 4);
            }
            else if(this.robotDirection == Direction.DOWN){

                g.drawLine(xPosition + robotGridLocation.getX()*cellSize + cellSize / 2, yPosition + robotGridLocation.getY()*cellSize + cellSize, xPosition + robotGridLocation.getX()*cellSize + cellSize / 4, yPosition + robotGridLocation.getY()*cellSize + 3 * cellSize / 4);
                g.drawLine(xPosition + robotGridLocation.getX()*cellSize + cellSize / 2, yPosition + robotGridLocation.getY()*cellSize + cellSize, xPosition + robotGridLocation.getX()*cellSize + 3 * cellSize / 4, yPosition + robotGridLocation.getY()*cellSize + 3 * cellSize / 4);
            
            }
        }

        else if (this.robotDirection == Direction.LEFT || this.robotDirection == Direction.RIGHT){

            g.drawLine(xPosition + robotGridLocation.getX()*cellSize , yPosition + robotGridLocation.getY()*cellSize + cellSize / 2, xPosition + robotGridLocation.getX()*cellSize + cellSize, yPosition + robotGridLocation.getY()*cellSize  + cellSize / 2);
            if(this.robotDirection == Direction.LEFT){
                g.drawLine(xPosition + robotGridLocation.getX()*cellSize , yPosition + robotGridLocation.getY()*cellSize + cellSize / 2, xPosition + robotGridLocation.getX()*cellSize + cellSize / 4, yPosition + robotGridLocation.getY()*cellSize + 1 * cellSize / 4);
                g.drawLine(xPosition + robotGridLocation.getX()*cellSize , yPosition + robotGridLocation.getY()*cellSize + cellSize / 2, xPosition + robotGridLocation.getX()*cellSize + cellSize / 4, yPosition + robotGridLocation.getY()*cellSize + 3 * cellSize / 4);
            }
            else if(this.robotDirection == Direction.RIGHT){
                g.drawLine(xPosition + robotGridLocation.getX()*cellSize + cellSize, yPosition + robotGridLocation.getY()*cellSize + cellSize / 2, xPosition + robotGridLocation.getX()*cellSize + 3 * cellSize / 4, yPosition + robotGridLocation.getY()*cellSize + 1 * cellSize / 4);
                g.drawLine(xPosition + robotGridLocation.getX()*cellSize + cellSize, yPosition + robotGridLocation.getY()*cellSize + cellSize / 2, xPosition + robotGridLocation.getX()*cellSize + 3 * cellSize / 4, yPosition + robotGridLocation.getY()*cellSize + 3 * cellSize / 4);
            }
        }  
    }

    /**
     * Render function for the robot without using class variables
     * @param g Graphics object
     * @param robotLocation the location of the robot
     * @param robotDirection the direction of the robot
     * @param gridLocation the location where the grid starts
     * @param cellSize the size of the grid cells
     */
    public void renderRobot(Graphics g, Location robotLocation, Direction robotDirection, Location gridLocation, int cellSize){
        g.setColor(Color.RED);
        g.fillRect(gridLocation.getX() + robotLocation.getX()*cellSize, gridLocation.getY() + robotLocation.getY()*cellSize,cellSize, cellSize);
        System.out.println("RENDERING");
        System.out.println(robotLocation.getX());
        System.out.println(robotLocation.getY());
        g.setColor(Color.BLUE);

        if (robotDirection == Direction.UP || robotDirection == Direction.DOWN){
            g.drawLine(gridLocation.getX() + robotLocation.getX()*cellSize + cellSize / 2, gridLocation.getY() + robotLocation.getY()*cellSize , gridLocation.getX() + robotLocation.getX()*cellSize + cellSize / 2, gridLocation.getY() + robotLocation.getY()*cellSize + cellSize);
            if(robotDirection == Direction.UP){
                g.drawLine(gridLocation.getX() + robotLocation.getX()*cellSize + cellSize / 2, gridLocation.getY() + robotLocation.getY()*cellSize, gridLocation.getX() + robotLocation.getX()*cellSize + cellSize / 4, gridLocation.getY() + robotLocation.getY()*cellSize + cellSize / 4);
                g.drawLine(gridLocation.getX() + robotLocation.getX()*cellSize + cellSize / 2, gridLocation.getY() + robotLocation.getY()*cellSize, gridLocation.getX() + robotLocation.getX()*cellSize + 3 * cellSize / 4, gridLocation.getY() + robotLocation.getY()*cellSize + cellSize / 4);
            }
            else{
                g.drawLine(gridLocation.getX() + robotLocation.getX()*cellSize + cellSize / 2, gridLocation.getY() + robotLocation.getY()*cellSize + cellSize, gridLocation.getX() + robotLocation.getX()*cellSize + cellSize / 4, gridLocation.getY() + robotLocation.getY()*cellSize + 3 * cellSize / 4);
                g.drawLine(gridLocation.getX() + robotLocation.getX()*cellSize + cellSize / 2, gridLocation.getY() + robotLocation.getY()*cellSize + cellSize, gridLocation.getX() + robotLocation.getX()*cellSize + 3 * cellSize / 4, gridLocation.getY() + robotLocation.getY()*cellSize + 3 * cellSize / 4);
            }
        }

        else if (robotDirection == Direction.LEFT || robotDirection == Direction.RIGHT){
            g.drawLine(gridLocation.getX() + robotLocation.getX()*cellSize , gridLocation.getY() + robotLocation.getY()*cellSize + cellSize / 2, gridLocation.getX() + robotLocation.getX()*cellSize + cellSize, gridLocation.getY() + robotLocation.getY()*cellSize  + cellSize / 2);
            if(robotDirection == Direction.LEFT){
                g.drawLine(gridLocation.getX() + robotLocation.getX()*cellSize , gridLocation.getY() + robotLocation.getY()*cellSize + cellSize / 2, gridLocation.getX() + robotLocation.getX()*cellSize + cellSize / 4, gridLocation.getY() + robotLocation.getY()*cellSize + 1 * cellSize / 4);
                g.drawLine(gridLocation.getX() + robotLocation.getX()*cellSize , gridLocation.getY() + robotLocation.getY()*cellSize + cellSize / 2, gridLocation.getX() + robotLocation.getX()*cellSize + cellSize / 4, gridLocation.getY() + robotLocation.getY()*cellSize + 3 * cellSize / 4);
            }
            else{
                g.drawLine(gridLocation.getX() + robotLocation.getX()*cellSize + cellSize, gridLocation.getY() + robotLocation.getY()*cellSize + cellSize / 2, gridLocation.getX() + robotLocation.getX()*cellSize + 3 * cellSize / 4, gridLocation.getY() + robotLocation.getY()*cellSize + 1 * cellSize / 4);
                g.drawLine(gridLocation.getX() + robotLocation.getX()*cellSize + cellSize, gridLocation.getY() + robotLocation.getY()*cellSize + cellSize / 2, gridLocation.getX() + robotLocation.getX()*cellSize + 3 * cellSize / 4, gridLocation.getY() + robotLocation.getY()*cellSize + 3 * cellSize / 4);
            }
        }
    }
}