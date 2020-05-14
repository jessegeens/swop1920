package model.Actions;


import model.ModelProgramArea;
import model.blocks.ModelBlock;
import utilities.ProgramLocation;

import java.util.ArrayList;

public class DeleteAction implements Action {

    private ModelBlock deletedBlock;
    private ModelProgramArea programArea;
    private ProgramLocation location;





    public DeleteAction(ModelBlock deletededBlock, ProgramLocation blockLocation,  ModelProgramArea programArea){
        this.deletedBlock = deletededBlock;
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

        ArrayList<ModelBlock> blocks = new ArrayList<>();
        blocks = this.programArea.getPABlocks();
        for (ModelBlock listBlock : blocks) {
            if (listBlock.equals(this.deletedBlock)) {
                this.programArea.removePABlock(this.deletedBlock);

                return;
            }
        }
    }
}





