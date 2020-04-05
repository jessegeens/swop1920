package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import model.ProgramState;
import utilities.*;

public class UIGrid {

    // Constructor
    public UIGrid() {
    }

    /**
     * Renders everything in the grid,
     * the grid itself, the walls, the goal cell and the robot.
     * @param g Graphics object
     * @param width width of the window
     * @param height height of the window
     * @param gridLocation location where the grid starts
     * @param state the programState that needs to be rendered
     * @author Oberon Swings
     */
    public void render(Graphics g, int width, int height, Location gridLocation, ProgramState state){
        renderGrid(g, width, height, gridLocation, state.getCellSize());
        renderWalls(g, state.getWalls(), gridLocation, state.getCellSize());
        renderGoalCell(g, state.getGoalCell(), gridLocation, state.getCellSize());
        renderRobot(g, state.getRobotLocation(), state.getRobotDirection(), gridLocation, state.getCellSize());
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
     * Render function for the goal cell without using class variables
     * @param g Graphics object
     * @param goalCell the location of the goal cell
     * @param gridLocation the location where the grid starts
     * @param cellSize the size of the grid cells
     * @author Oberon Swings
     */
    public void renderGoalCell(Graphics g, Location goalCell, Location gridLocation, int cellSize){
        g.setColor(Color.YELLOW);
        g.fillRect(gridLocation.getX() + goalCell.getX()*cellSize, gridLocation.getY() + goalCell.getY()*cellSize,cellSize, cellSize);
    }

    /**
     * Render function for the robot without using class variables
     * @param g Graphics object
     * @param robotLocation the location of the robot
     * @param robotDirection the direction of the robot
     * @param gridLocation the location where the grid starts
     * @param cellSize the size of the grid cells
     * @author Oberon Swings
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