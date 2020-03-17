package utilities;

/**
 * Class representing the direction of an element in Blockr.
 */
public final class Direction{

    public static final int UP = 0;

    public static final int RIGHT = 1;
  
    public static final int DOWN = 2;
    
    public static final int LEFT = 3;
    
    private final int direction;

    public Direction(int direction){
        if(direction >-1 && direction < 4){
            this.direction = direction;
        }
        else{
            throw new IllegalArgumentException("Invalid direction given");
        }
    }
    
    /**
     * 
     * @return the direction of the element.
     */
    public int getDirection() {
        return this.direction;
    }

    /**
     * Method to turn left.
     */
    public Direction turnLeft(){
        return new Direction((this.getDirection() + 3) % 4); //plus 3 equals -1 in mod 4
    }

    /**
     * Method to turn right.
     */
    public Direction turnRight(){
        return new Direction((this.getDirection() + 1) % 4);
    }
}