package model.Actions;


import model.ModelProgramArea;
import model.blocks.ModelBlock;
import utilities.ProgramLocation;

public class DeleteAction implements Action {

    private ModelBlock deletedBlock;
    private ModelProgramArea programArea;
    private ProgramLocation location;





    public DeleteAction(ModelBlock createdBlock, ProgramLocation blockLocation,  ModelProgramArea programArea){
        this.deletedBlock = createdBlock;
        this.programArea = programArea;
        this.location = blockLocation;
        //ProgramLocation blockLocation,

    }


    public void undo(){
        this.programArea.addPABlock(this.deletedBlock);
        this.deletedBlock.setPos(this.location);
    }

    public void redo(){
        this.programArea.removePABlock(this.deletedBlock);
    }
}





