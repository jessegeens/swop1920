package ui;

import java.awt.Graphics;
import java.util.ArrayList;
import utilities.*;

public class UIGrid implements UIWindow {




    private Location goalCell;
    private int cellHeight;
    private int cellWidth;
    private Location robotLocation;
    //TODO robotDriection

    private ArrayList<Location> walls;
    

    public UIGrid(Graphics g) {
    }

    public UIGrid(int cellHeight, int cellWidth, ArrayList<Location> walls, Location robotLocation) {
    }
    
    public void updateRobot(Location newRobotLocation){

    }


    //TODO add graphics Graphics g
    @Override
    public void render() {
        // TODO Auto-generated method stub

    }

    /*

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

    */

    

}