package utilities;


/**
 * Class representing a mapping between the blocktypes and a subset of integers.
 */
public enum BlockType {

    IF, WHILE, NOT, ACTION, PREDICATE;

    public String getTitle(){
        switch(this){
            case IF:
                return "if";
            case WHILE:
                return "while";
            case ACTION:
                return "action";
            case NOT:
                return "not";
            case PREDICATE:
                return "predicate";
            default:
                return "";
        }
    }
}