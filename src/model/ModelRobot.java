package model;

import utilities.*;

/**
 * Class representing the robot. The robot has an orientiation and lives in the Game World.
 */
class ModelRobot extends ModelElement {
    private Direction direction;

    public ModelRobot(int dir){
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
    public void setDirection(int direction) {
        this.direction = new Direction(direction);
    }

}