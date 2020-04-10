package model;

import model.blocks.ModelBlock;
import utilities.ProgramLocation;

public class MoveAction implements  Action {

    private static ModelBlock movedBlock;
    private static ProgramLocation oldL;
    private static ProgramLocation newL;


    public MoveAction(ModelBlock movedBlock, ProgramLocation oldL, ProgramLocation newL){
        this.movedBlock = movedBlock;
        this.oldL = oldL;
        this.newL = newL;

    }

    public void execute(){
        movedBlock.setPos(newL);

    }

    public void undo(){
        movedBlock.setPos(oldL);

    }

    public void redo(){
        movedBlock.setPos(newL);

    }
}
