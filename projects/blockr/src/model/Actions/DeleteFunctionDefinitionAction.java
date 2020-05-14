package model.Actions;

import model.ModelProgramArea;
import model.blocks.ModelBlock;
import model.blocks.ModelFunctionCallBlock;
import utilities.ProgramLocation;

import java.util.ArrayList;

public class DeleteFunctionDefinitionAction extends DeleteAction implements Action  {

    ArrayList<DeleteAction> functionCallDeletes;


    public DeleteFunctionDefinitionAction(ModelBlock deletedBlock, ProgramLocation blockLocation, ModelProgramArea programArea, ArrayList<ModelFunctionCallBlock> callBlocks){
        super(deletedBlock, blockLocation, programArea);
        functionCallDeletes = new ArrayList<>();

        for(ModelFunctionCallBlock block : callBlocks){
            functionCallDeletes.add(new DeleteAction(block, block.getPos(), programArea));

        }


    }

    @Override
    public void undo() {
        super.undo();

        for(DeleteAction deleteAction : functionCallDeletes){
            deleteAction.undo();
        }
    }

    @Override
    public void redo() {
        super.undo();
        for(DeleteAction deleteAction : functionCallDeletes){
            deleteAction.redo();
        }
    }
}
