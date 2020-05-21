package model.blocks;

import utilities.ProgramState;
import model.UndoRedoHandler;
import utilities.ProgramLocation;

public class ModelFunctionDefinitionBlock extends ModelCavityBlock {

    private final int identifier;

    public ModelFunctionDefinitionBlock(ProgramLocation pos, int id){
        super(pos);
        this.identifier = id;
    }

    /**
     *
     * @return the id this block has
     */
    public int getId(){
        return this.identifier;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ModelFunctionDefinitionBlock clone(){
        return new ModelFunctionDefinitionBlock(super.getPos(), this.getId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ModelBlock findNextBlock() {
        Object state = UndoRedoHandler.getInstance().getState();
        if (state instanceof ProgramState) return ((ProgramState) state).getCallStack().peek().getBottomPlug();
        else return null;
    }

}
