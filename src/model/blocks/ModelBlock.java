package model.blocks;

import model.*;
import utilities.*;

public abstract class ModelBlock extends ModelElement{

    @Override
    public void move(Location newPos){
        super.move(newPos);
    }

    public void move(Location newPos, ModelBlock closest){
        super.move(newPos);
        this.disconnect();
        this.connect(closest);
    }

    public abstract void disconnect();
    public abstract void connect(ModelBlock block);

    
}