package model;

import utilities.*;

/**
 * Class representing the robot. The robot has an orientiation and lives in the Game World.
 */
public class ModelRobot extends ModelElement {
    private Direction direction;

    public ModelRobot(Location pos, Direction dir){
        super(pos);
        this.setDirection(dir);
    }

    /**
     * Method that turns the robot left.
     */
    public void turnLeft(){//turn to the left according to which is the initial direction
        this.direction.turnLeft();
    }

    /**
     * Method that turns the robot right.
     */
    public void turnRight(){//turn to the right according to which is the initial direction
        this.direction.turnRight();
    }

    /**
     * 
     * @return the direction of the robot.
     */
    public Direction getDirection() {
        return this.direction;
    }

    /**
     * 
     * @param direction the direction that the robot will be set to.
     */
    public void setDirection(Direction dir) {
        this.direction = dir;
    }

}