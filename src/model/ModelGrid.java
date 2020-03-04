package model;

import utilities.*;
import java.util.ArrayList;

class ModelGrid{
    private Location goalCell;
    private int cellHeight;
    private int cellWidth;
    private ModelRobot robot;
    private ArrayList<ModelWall> walls;




    

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

    public ArrayList<ModelWall> getWalls() {
        return this.walls;
    }

    public void setWalls(ArrayList<ModelWall> walls) {
        this.walls = walls;
    }

}