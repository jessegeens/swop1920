package model;

import utilities.*;

class ModelRobot extends ModelElement {
    private Direction direction;

    public ModelRobot(int dir){
        this.setDirection(dir);
    }


    public void turnLeft(){//turn to the left according to which is the initial direction
        this.direction.turnLeft();
    }
    public void turnRight(){//turn to the right according to which is the initial direction
        this.direction.turnRight();
    }

    public Direction getDirection() {
        return this.direction;
    }

    public void setDirection(int direction) {
        this.direction = new Direction(direction);
    }

}