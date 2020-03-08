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

    /**
     * These getters and setters will be overwritten in the specific blocktype classes so that when they do support the socket or plug it will
     * execute as expected.
     * @return IllegalStateException when the socket or plug is not supported, iow when the function is not overwritten in the subclass.
     */
    public ModelBlock getTopSocket(){
        throw new IllegalStateException("This blocktype doesn't support this socket.");
    }
    public void setTopSocket(ModelBlock blk){
        throw new IllegalStateException("This blocktype doesn't support this socket.");
    }
    public ModelBlock getBottomPlug(){
        throw new IllegalStateException("This blocktype doesn't support this plug.");
    }
    public void setBottomPlug(ModelBlock blk){
        throw new IllegalStateException("This blocktype doesn't support this plug.");
    }
    public ModelBlock getLeftPlug(){
        throw new IllegalStateException("This blocktype doesn't support this plug.");
    }
    public void setLeftPlug(ModelBlock blk){
        throw new IllegalStateException("This blocktype doesn't support this plug.");
    }
    public ModelBlock getRightSocket(){
        throw new IllegalStateException("This blocktype doesn't support this socket.");
    }
    public void setRightSocket(ModelBlock blk){
        throw new IllegalStateException("This blocktype doesn't support this socket.");
    }

    
}