package utilities;

/**
 * Class representing the possible conditions in an IF or WHILE block
 * 
 * @author Bert De Vleeschouwer
 */
public enum Condition{

    WALL_IN_FRONT, NOT_WALL_IN_FRONT;

    /*public static final int WALL_IN_FRONT  = 0;

    public static final int NOT_WALL_IN_FRONT = 1;
*/

    /**
     * 
     * @return the name of the condition.
     */
    public String getTitle(){
        switch(this){
            case WALL_IN_FRONT:
                return "wall in front";
            case NOT_WALL_IN_FRONT:
                return "not wall in front";
            default:
                return "";
        }
    }

}