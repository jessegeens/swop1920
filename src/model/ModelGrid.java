package model;

import utilities.*;
import java.util.ArrayList;

/**
 * Class representing a two-dimensional grid for Blockr. The grid contains walls, a robot and a goal cell.
 */
class ModelGrid{
    private Location goalCell;
    private int cellHeight;
    private int cellWidth;
    private ModelRobot robot;
    private ArrayList<Location> walls;



    public ModelGrid(Location goal, ModelRobot rbt){
        this.setGoalCell(goal);
        this.setCellHeight(30); //random waarde gekozen hier
        this.setCellHeight(30); //idem hier
        this.setRobot(rbt);
        this.setWalls(new ArrayList<Location>());
    }
    

    /**
     * 
     * @return the goal cell.
     */
    public Location getGoalCell() {
        return this.goalCell;
    }

    /**
     * 
     * @param goalCell the cell that will be the goal cell.
     */
    public void setGoalCell(Location goalCell) {
        this.goalCell = goalCell;
    }

    /**
     * 
     * @return the height of the cell.
     */
    public int getCellHeight() {
        return this.cellHeight;
    }

    /**
     * 
     * @param cellHeight the height of the cell.
     */
    public void setCellHeight(int cellHeight) {
        this.cellHeight = cellHeight;
    }

    /**
     * 
     * @return the width of the cell.
     */
    public int getCellWidth() {
        return this.cellWidth;
    }

    /**
     * 
     * @param cellWidth the width of the cell
     */
    public void setCellWidth(int cellWidth) {
        this.cellWidth = cellWidth;
    }

    /**
     * 
     * @return the robot in the grid.
     */
    public ModelRobot getRobot() {
        return this.robot;
    }

    /**
     * 
     * @param robot the robot in the grid.
     */
    public void setRobot(ModelRobot robot) {
        this.robot = robot;
    }

    /**
     * 
     * @return a list of locations where a wall stands.
     */
    public ArrayList<Location> getWalls() {
        return this.walls;
    }

    /**
     * 
     * @param walls a list of walls to add to the grid.
     */
    public void setWalls(ArrayList<Location> walls) {
        this.walls = walls;
    }
}