package utilities;


/**
 * Class representing a mapping between the blocktypes and a subset of integers.
 */
public final class Blocktype{

    public static final int IF = 0;

    public static final int WHILE = 1;
  
    public static final int MOVEFORWARD = 2;
    
    public static final int TURNLEFT = 3;
    
    public static final int TURNRIGHT = 4;
    
    public static final int WALLINFRONT = 5;

    public static final int NOT = 6;

    private final int type;

    /**
     * Constructor for the blocktype
     * @param type the type of the blocktype
     */
    public Blocktype(int type){
        if(type >-1 && type < 7){
            this.type = type;
        }
        else{
            throw new IllegalArgumentException("Invalid blocktype given");
        }

    }

    /**
     * 
     * @return the type of the block.
     */
    public int getType(){
        return this.type;
    }

    /**
     * 
     * @return the name of the block.
     */
    public String getTitle(){
        switch(this.type){
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

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o){
        if (!(o instanceof Blocktype)) return false;
        if (((Blocktype) o).getType() == this.getType()) return true;
        else return false;
    }
}