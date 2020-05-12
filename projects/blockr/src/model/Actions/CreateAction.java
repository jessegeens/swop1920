package model.Actions;

import model.ModelProgramArea;
import model.blocks.ModelBlock;

import java.util.ArrayList;

public class CreateAction implements  Action {
    private ModelBlock createdBlock;
    private ModelProgramArea programArea;



    public CreateAction(ModelBlock createdBlock, ModelProgramArea programArea){
        this.createdBlock = createdBlock;
        this.programArea = programArea;
    }





    public void undo(){
        this.programArea.removePABlock(this.createdBlock);

        //remove similar
        ArrayList<ModelBlock> blocks = new ArrayList<>();
        blocks = this.programArea.getPABlocks();
        for (ModelBlock listBlock : blocks) {
            if (listBlock.equals(this.createdBlock)) {
                this.programArea.removePABlock(this.createdBlock);

                return;
            }
        }

    }

    public void redo(){
        this.programArea.addPABlock(this.createdBlock);
    }
}


