package model.Actions;

import model.ModelProgramArea;
import model.blocks.ModelBlock;
import utilities.ProgramLocation;

public class DisconnectAction implements Action {


    private ModelBlock block;
    private ModelBlock closest;
    private ProgramLocation location;

    private ModelProgramArea programArea;

    //maybe let PA variable just find the location itself here



    public DisconnectAction(ModelBlock block, ProgramLocation blockLocation, ModelProgramArea programArea){
        this.block = block;
        this.location = blockLocation;
        //this.closest = closest;
        this.programArea = programArea;
    }


    public void undo(){
        this.programArea.findAndConnect(this.location, this.block);


    }
    public void redo(){
        this.programArea.removePABlock(block);
        this.block.setPos(this.location);
        this.programArea.addPABlock(block);

    }

}
