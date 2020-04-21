package model.Actions;


import model.blocks.ModelBlock;
import utilities.ProgramLocation;


public class MoveAction implements Action {

    private ModelBlock movedBlock;
    private ProgramLocation oldL;
    private ProgramLocation newL;


    public MoveAction(ModelBlock movedBlock, ProgramLocation oldL, ProgramLocation newL){
        this.movedBlock = movedBlock;
        this.oldL = oldL;
        this.newL = newL;
    }



    public void undo(){
        movedBlock.setPos(oldL);


    }

    public void redo(){
        movedBlock.setPos(newL);
    }
}
