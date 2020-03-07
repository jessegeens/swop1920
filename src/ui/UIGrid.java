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
    
    //TODO This old constructor is still being used in a class somewhere
    public UIGrid(Graphics g) {
    }

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
    @Override
    public void render(Graphics g) {
        renderGrid(g);
        renderWalls(g);
        renderGoalCell(g);
        renderRobot(g);
        

    }

    public void renderGrid(Graphics g) {
        g.setColor(Color.GRAY);
        for(int x = 0; x < this.dimension; x++){
            for(int y = 0; y < this.dimension; y++){
                g.drawRect(xPosition + x * cellSize, yPosition + y*cellSize, cellSize, cellSize);

            }

        }

        

    }

    public void renderWalls(Graphics g) {
        g.setColor(Color.BLACK);
        for(Location wall : walls){
            g.fillRect(wall.getX()*cellSize,wall.getY()*cellSize,cellSize, cellSize);
        }
        

    }

    public void renderGoalCell(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillRect(goalCell.getX()*cellSize,goalCell.getY()*cellSize,cellSize, cellSize);
    }



    public void renderRobot(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(robotLocation.getX()*cellSize,robotLocation.getY()*cellSize,cellSize, cellSize);

        g.setColor(Color.WHITE);

        if (this.robotDirection.getDirection() == Direction.UP || this.robotDirection.getDirection() == Direction.DOWN){
            //draw vertical line
            if(this.robotDirection.getDirection() == Direction.UP){
                //draw arrows
            }
            else if(this.robotDirection.getDirection() == Direction.DOWN){
                //draw arrows
            }


        }
        else if (this.robotDirection.getDirection() == Direction.LEFT || this.robotDirection.getDirection() == Direction.RIGHT){
            //draw horizontal line
            if(this.robotDirection.getDirection() == Direction.LEFT){
                //draw arrows
            }
            else if(this.robotDirection.getDirection() == Direction.RIGHT){
                //draw arrows
            }


        }
        

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