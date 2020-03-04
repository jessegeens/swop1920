package ui;

import java.util.ArrayList;
import utilities.*;

class UIGrid {
    private Location goalCell;
    private int cellHeight;
    private int cellWidth;
    private UIRobot robot;
    private ArrayList<UIWall> walls;



    

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

    public UIRobot getRobot() {
        return this.robot;
    }

    public void setRobot(UIRobot robot) {
        this.robot = robot;
    }

    public ArrayList<UIWall> getWalls() {
        return this.walls;
    }

    public void setWalls(ArrayList<UIWall> walls) {
        this.walls = walls;
    }

}