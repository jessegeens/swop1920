package model.Actions;

import model.ModelProgramArea;
import model.blocks.ModelBlock;

public class CreateAction implements  Action {
    private static ModelBlock createdBlock;
    private ModelProgramArea programArea;



    public CreateAction(ModelBlock createdBlock, ModelProgramArea programArea){
        this.createdBlock = createdBlock;
        this.programArea = programArea;
    }

    //TODO setting it as active block necessary or not?
    //TODO activate deactivate palette
    //TODO block clones or not
    public void execute(){
        this.programArea.addPABlock(this.createdBlock);
    }

    public void undo(){
        this.programArea.removePABlock(this.createdBlock);


    }

    public void redo(){
        this.programArea.addPABlock(this.createdBlock);
    }
}


