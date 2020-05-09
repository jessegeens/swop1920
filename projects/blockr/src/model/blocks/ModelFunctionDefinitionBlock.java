package model.blocks;

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

}
