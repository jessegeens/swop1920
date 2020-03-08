package model;

class ModelRobot extends ModelElement{
    private int direction; //0 = right, 1 = down, 2 = left, 3 = up

    public ModelRobot(int dir){
        this.setDirection(dir);
    }


    public void turnLeft(){//turn to the left according to which is the initial direction
        setDirection((getDirection() + 1) % 4);
    }
    public void turnRight(){//turn to the right according to which is the initial direction
        setDirection((getDirection() - 1) % 4);
    }

    public int getDirection() {
        return this.direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

}