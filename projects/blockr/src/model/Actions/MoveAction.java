package model.Actions;


import model.ModelProgramArea;
import model.blocks.ModelBlock;
import utilities.ProgramLocation;


public class MoveAction implements Action {

    private ModelBlock movedBlock;
    private ProgramLocation oldL;
    private ProgramLocation newL;
    private ModelProgramArea programArea;


    public MoveAction(ModelBlock movedBlock, ProgramLocation oldL, ProgramLocation newL, ModelProgramArea programArea){
        this.movedBlock = movedBlock;
        this.oldL = oldL;
        this.newL = newL;
        this.programArea = programArea;
    }



    public void undo(){
        programArea.dragBlock(movedBlock, oldL);



    }

    public void redo(){
        programArea.dragBlock(movedBlock, newL);

    }
}


