package utilities;

/**
 * Class representing the possible conditions in an IF or WHILE block
 * 
 * @author Bert De Vleeschouwer
 */
public final class Condition{

    public static final int WALL_IN_FRONT  = 0;

    public static final int NOT_WALL_IN_FRONT = 1;

    private final int condition;

    /**
     * Constructor for the condition
     * @param condition the condtion
     */
    public Condition(int condition){
        if(condition >-1 && condition < 2){
            this.condition = condition;
        }
        else{
            throw new IllegalArgumentException("Invalid condition given");
        }
    }

    /**
     * 
     * @return the type of the block.
     */
    public int getCondition(){
        return this.condition;
    }

    /**
     * 
     * @return the name of the condition.
     */
    /*
    public String getTitle(){
        switch(this.condition){
            case BlockType.IF:
                return "IF";
            case BlockType.WHILE:
                return "WHILE";
            case BlockType.MOVEFORWARD:
                return "Move F";
            case BlockType.TURNLEFT:
                return "Turn L";
            case BlockType.TURNRIGHT:
                return "Turn R";
            case BlockType.WALLINFRONT:
                return "Wall In F";
            case BlockType.NOT:
                return "NOT";
            default:
                return "error";
        }
    }*/
    //What is all this? there should only be two conditions wallInFront and notWallInFront. -Oberon

}