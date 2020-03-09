package model;

import model.blocks.*;
import java.util.ArrayList;

abstract class ModelWindow{
    private ArrayList<ModelBlock> blocks;


    

    public ArrayList<ModelBlock> getBlocks() {
        return this.blocks;
    }

    public void setBlocks(ArrayList<ModelBlock> blocks) {
        this.blocks = blocks;
    }


}