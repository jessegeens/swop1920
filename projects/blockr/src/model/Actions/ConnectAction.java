package model.Actions;

import model.ModelProgramArea;
import model.blocks.ModelBlock;
import utilities.ProgramLocation;

public class ConnectAction implements Action {

    private static ModelBlock block;
    private static ModelBlock closest;
    private static ProgramLocation location;

    private ModelProgramArea programArea;

    //TODO let PA variable just find the location itself here



    public ConnectAction(ModelBlock block, ProgramLocation blockLocation, ModelProgramArea programArea){
        this.block = block;
        this.location = new ProgramLocation(blockLocation.getX(),blockLocation.getY());
        //this.closest = closest;
        this.programArea = programArea;
    }

    public void execute(){

    }
    public void undo(){
        this.programArea.removePABlock(block);
        this.block.setPos(this.location);
        this.programArea.addPABlock(block);
    }
    public void redo(){


    }
}
