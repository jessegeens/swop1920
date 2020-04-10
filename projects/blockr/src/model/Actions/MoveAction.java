package model.Actions;


import model.blocks.ModelBlock;
import utilities.ProgramLocation;

//TODO what about connections
//TODO do not forget to make everything static

public class MoveAction implements Action {

    private ModelBlock movedBlock;
    private ProgramLocation oldL;
    private ProgramLocation newL;


    public MoveAction(ModelBlock movedBlock, ProgramLocation oldL, ProgramLocation newL){
        this.movedBlock = movedBlock;
        this.oldL = oldL.clone();
        this.newL = newL.clone();

    }

    public void execute(){
        movedBlock.setPos(newL);
        //TODO

    }

    public void undo(){
        movedBlock.setPos(oldL);
        System.out.println("from to");
        System.out.println(oldL.getX());
        System.out.println(newL.getX());

    }

    public void redo(){
        movedBlock.setPos(newL);
        //TODO
    }
}
