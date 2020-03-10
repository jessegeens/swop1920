package utilities;

/**
 * Class representing the direction of an element in Blockr.
 */
public final class Direction{

    public static final int UP = 0;

    public static final int RIGHT = 1;
  
    public static final int DOWN = 2;
    
    public static final int LEFT = 3;
    
    private int direction;

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
    public void turnLeft(){
        this.direction = (this.direction - 1) % 4;

    }

    /**
     * Method to turn right.
     */
    public void turnRight(){
        this.direction = (this.direction + 1) % 4;
        
    }
}