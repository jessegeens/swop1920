package utilities;


/**
 * Class representing a mapping between the blocktypes and a subset of integers.
 */
public enum BlockType {

    IF, WHILE, MOVEFORWARD, TURNLEFT, TURNRIGHT, WALLINFRONT, NOT;

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