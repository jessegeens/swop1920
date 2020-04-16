package model.Actions;


import model.blocks.ModelBlock;
import utilities.ProgramLocation;


public class MoveAction implements Action {

    private ModelBlock movedBlock;
    private ProgramLocation oldL;
    private ProgramLocation newL;


    public MoveAction(ModelBlock movedBlock, ProgramLocation oldL, ProgramLocation newL){
        this.movedBlock = movedBlock;
        this.oldL = new ProgramLocation(oldL.getX(),oldL.getY());
        this.newL = new ProgramLocation(newL.getX(), newL.getY());
    }

    public void execute(){
        movedBlock.setPos(newL);


    }

    public void undo(){
        movedBlock.setPos(oldL);
        /*
        System.out.println("from to");
        System.out.println(oldL.getX());
        System.out.println(newL.getX());
        */

    }

    public void redo(){
        movedBlock.setPos(newL);
    }
}
