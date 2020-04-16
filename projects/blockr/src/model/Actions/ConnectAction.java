package model.Actions;

import model.ModelProgramArea;
import model.blocks.ModelBlock;

public class ConnectAction implements Action {

    private static ModelBlock block;
    private static ModelBlock closest;

    private ModelProgramArea programArea;

    //TODO let PA variable just find the location itself here



    public ConnectAction(ModelBlock block, ModelBlock closest, ModelProgramArea programArea){
        this.block = block;
        this.closest = closest;
        this.programArea = programArea;
    }

    public void execute(){

    }
    public void undo(){


    }
    public void redo(){

    }
}
