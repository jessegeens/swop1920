package model.blocks;

import model.Actions.OberonAction;
import model.ProgramState;
import model.UndoRedoHandler;
import ui.UIBlock;
import utilities.ConnectionPoint;
import utilities.ProgramLocation;

import java.util.ArrayList;

public class ModelFunctionDefinitionBlock extends ModelCavityBlock {

    private final int identifier;

    public ModelFunctionDefinitionBlock(ProgramLocation pos, int id){
        super(pos);

        this.identifier = id;
    }

    public int getId(){
        return this.identifier;
    }


    @Override
    public ModelFunctionDefinitionBlock clone(){
        return new ModelFunctionDefinitionBlock(super.getPos(), this.getId());
    }

    @Override
    public ModelBlock findNextBlock() {
        Object state = UndoRedoHandler.getInstance().getState();
        if (state instanceof ProgramState) return ((ProgramState) state).getCallStack().peek().getBottomPlug();
        else return null;
    }

}
