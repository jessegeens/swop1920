package ui;

import java.awt.Graphics;
import java.util.ArrayList;
import utilities.*;

public class UIGrid implements UIWindow {




    private Location goalCell;
    private int cellHeight;
    private int cellWidth;
    private Location robotLocation;
    private Direction robotDirection;
    //TODO robotDriection

    private ArrayList<Location> walls;
    

    public UIGrid(Graphics g) {
    }

    public UIGrid(int cellHeight, int cellWidth, ArrayList<Location> walls, Location robotLocation, Direction roboDirection) {
        this.cellHeight = cellHeight;
        this.cellWidth = cellWidth;
        this.walls = walls;
        this.robotLocation = robotLocation;
        this.robotDirection = roboDirection;
    }
    
    public void updateRobotLocation(Location newRobotLocation){
        this.robotLocation = newRobotLocation;

    }

    public void updateRobotDirection(Direction newRobotDirection){
        this.robotDirection = newRobotDirection;

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