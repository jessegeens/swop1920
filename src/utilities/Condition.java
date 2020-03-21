package utilities;

/**
 * Class representing the possible conditions in an IF or WHILE block
 */
public final class Condition{

    public static final int WALL_IN_FRONT  = 0;

    public static final int NOT_WALL_IN_FRONT = 1;

    private final int condition;

    /**
     * Constructor for the condition
     * @param condtion the condtion
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
    public String getTitle(){
        switch(this.condition){
            case Blocktype.IF:
                return "IF";
            case Blocktype.WHILE:
                return "WHILE";
            case Blocktype.MOVEFORWARD:
                return "Move F";
            case Blocktype.TURNLEFT:
                return "Turn L";
            case Blocktype.TURNRIGHT:
                return "Turn R";
            case Blocktype.WALLINFRONT:
                return "Wall In F";
            case Blocktype.NOT:
                return "NOT";
            default:
                return "error";
        }
    }

}