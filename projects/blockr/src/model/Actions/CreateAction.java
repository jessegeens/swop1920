package model.Actions;

import model.ModelProgramArea;
import model.blocks.ModelBlock;

public class CreateAction implements  Action {
    private ModelBlock createdBlock;
    private ModelProgramArea programArea;



    public CreateAction(ModelBlock createdBlock, ModelProgramArea programArea){
        this.createdBlock = createdBlock;
        this.programArea = programArea;
    }





    public void undo(){
        this.programArea.removePABlock(this.createdBlock);
    }

    public void redo(){
        this.programArea.addPABlock(this.createdBlock);
    }
}


