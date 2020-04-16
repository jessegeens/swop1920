package model.Actions;


import model.ModelProgramArea;
import model.blocks.ModelBlock;

public class DeleteAction implements Action {

    private ModelBlock deletedBlock;
    private ModelProgramArea programArea;



    public DeleteAction(ModelBlock createdBlock, ModelProgramArea programArea){
        this.deletedBlock = createdBlock;
        this.programArea = programArea;

    }

    //TODO setting it as active block necessary or not?
    //TODO activate deactivate palette
    //TODO block clones or not
    public void execute(){
        this.programArea.removePABlock(this.deletedBlock);

    }

    public void undo(){

        this.programArea.addPABlock(this.deletedBlock);


    }

    public void redo(){
        this.programArea.removePABlock(this.deletedBlock);
    }
}





