package gameworldapi;

/**
 * Enum representing the direction of an element.
 */
public enum Direction{

    UP, RIGHT, DOWN, LEFT;

    /**
     * Method to turn left.
     * @author Oberon Swings
     */
    public Direction turnLeft(){
        switch (this){
            case UP:
                return LEFT;
            case RIGHT:
                return UP;
            case DOWN:
                return RIGHT;
            case LEFT:
                return DOWN;
            default:
                throw new IllegalArgumentException("Invalid direction given");
        }
    }

    /**
     * Method to turn right.
     * @author Oberon Swings
     */
    public Direction turnRight(){
        switch (this){
            case UP:
                return RIGHT;
            case RIGHT:
                return DOWN;
            case DOWN:
                return LEFT;
            case LEFT:
                return UP;
            default:
                throw new IllegalArgumentException("Invalid direction given");
        }
    }

    @Override
    public String toString(){
        switch(this){
            case UP:
                return "up";
            case RIGHT:
                return "right";
            case DOWN:
                return "down";
            case LEFT:
                return "left";
            default:
                throw new IllegalStateException("illegal direction found");
        }
    }
}