package utilities;

public final class Blocktype{

    public static final int IF = 0;

    public static final int WHILE = 1;
  
    public static final int MOVEFORWARD = 2;
    
    public static final int TURNLEFT = 3;
    
    public static final int TURNRIGHT = 4;
    
    public static final int WALLINFRONT = 5;

    public static final int NOT = 6;

    private int type;

    public Blocktype(int type){
        if(type >-1 && type < 7){
            this.type = type;
        }
        else{
            throw new IllegalArgumentException("Invalid blocktype given");
        }

    }

    public int getType(){
        return this.type;
    }

    
    public String getTitle(){
        switch(this.type){
            case Blocktype.IF:
                return "If Block";
            case Blocktype.WHILE:
                return "While Block";
            case Blocktype.MOVEFORWARD:
                return "Move Forward";
            case Blocktype.TURNLEFT:
                return "Turn Left";
            case Blocktype.TURNRIGHT:
                return "Turn Right";
            case Blocktype.WALLINFRONT:
                return "Wall in front";
            case Blocktype.NOT:
                return "NotBlock";
            default:
                return "error";
        }

    }
    



}