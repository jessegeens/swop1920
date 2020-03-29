package utilities;


/**
 * Class representing a mapping between the blocktypes and a subset of integers.
 */
public enum BlockType {

    IF, WHILE, MOVEFORWARD, TURNLEFT, TURNRIGHT, WALLINFRONT, NOT;

    /*public static final int IF = 0;

    public static final int WHILE = 1;
  
    public static final int MOVEFORWARD = 2;
    
    public static final int TURNLEFT = 3;
    
    public static final int TURNRIGHT = 4;
    
    public static final int WALLINFRONT = 5;

    public static final int NOT = 6;
    */

    public String getTitle(){
        switch(this){
            case IF:
                return "if";
            case WHILE:
                return "while";
            case TURNLEFT:
                return "turn left";
            case TURNRIGHT:
                return "turn right";
            case MOVEFORWARD:
                return "move forward";
            case NOT:
                return "not";
            case WALLINFRONT:
                return "wall in front";
            default:
                return "";
        }
    }



}