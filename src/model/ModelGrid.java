package model;

import utilities.*;
import java.util.ArrayList;

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
    

    public Location getGoalCell() {
        return this.goalCell;
    }

    public void setGoalCell(Location goalCell) {
        this.goalCell = goalCell;
    }

    public int getCellHeight() {
        return this.cellHeight;
    }

    public void setCellHeight(int cellHeight) {
        this.cellHeight = cellHeight;
    }

    public int getCellWidth() {
        return this.cellWidth;
    }

    public void setCellWidth(int cellWidth) {
        this.cellWidth = cellWidth;
    }

    public ModelRobot getRobot() {
        return this.robot;
    }

    public void setRobot(ModelRobot robot) {
        this.robot = robot;
    }

    public ArrayList<Location> getWalls() {
        return this.walls;
    }

    public void setWalls(ArrayList<Location> walls) {
        this.walls = walls;
    }

}