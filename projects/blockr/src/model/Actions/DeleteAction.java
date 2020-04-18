package model.Actions;


import model.ModelProgramArea;
import model.blocks.ModelBlock;
import utilities.ProgramLocation;

public class DeleteAction implements Action {

    private ModelBlock deletedBlock;
    private ModelProgramArea programArea;
    //TODO static or not
    private ProgramLocation location;



    public DeleteAction(ModelBlock createdBlock,   ModelProgramArea programArea){
        this.deletedBlock = createdBlock;
        this.programArea = programArea;
        //this.location = new ProgramLocation(blockLocation.getX(),blockLocation.getY());
        //ProgramLocation blockLocation,

    }

    //TODO setting it as active block necessary or not?
    //TODO activate deactivate palette
    //TODO block clones or not
    public void execute(){
        //TODO

    }

    public void undo(){


        this.programArea.addPABlock(this.deletedBlock);
        //this.deletedBlock.setPos(this.location);



    }

    public void redo(){
        this.programArea.removePABlock(this.deletedBlock);
    }
}





