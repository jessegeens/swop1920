package utilities;

public final class Blocktype{

    public static final int IF = 0;

    public static final int WHILE = 1;
  
    public static final int MOVEFORWARD = 2;
    
    public static final int TURNLEFT = 3;
    
    public static final int TURNRIGHT = 4;
    
    public static final int WALLINFRONT = 5;

    private int type;

    public Blocktype(int type){
        if(type >-1 && type < 6){
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
        return("TODO");

    }
    



}