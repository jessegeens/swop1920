package model.blocks;

import java.util.ArrayList;

import model.*;
import utilities.*;

public abstract class ModelBlock extends ModelElement{

    private final int width = 120;
    private final int height = 120;

    

    @Override
    public void move(Location newPos){
        super.move(newPos);
    }

    /**
     * Connects this block to the closest block if the distance is less than 50 (pixels), puts the block directly next to the connecting block.
     * If the distance is more than 50 (pixels), the block will be placed exactly at the newPos location.
     * @param newPos The location to which the block is dragged, possibly the new location except if the block connects to a neighbour, then the new
     *              location will be determined by the position of this neighbour and the height/width of the two blocks.
     * @param closest The block closest to the location where the block is dragged.
     */
    public void move(Location newPos, ModelBlock closest){
        super.move(newPos);
        this.disconnect();
        if (newPos.getDistance(closest.getPos()) < 50){
            this.connect(closest);
        }
        if (this.getTopSocket() == closest){
            super.setPos(new Location(closest.getPos().getX(),closest.getPos().getY() - closest.getHeight()));
        }
        if (this.getBottomPlug() == closest){
            super.setPos(new Location(closest.getPos().getX(),closest.getPos().getY() + this.getHeight()));
        }
        if (this.getRightSocket() == closest){
            super.setPos(new Location(closest.getPos().getX() + closest.getWidth(),closest.getPos().getY()));
        }
        if (this.getLeftPlug() == closest){
            super.setPos(new Location(closest.getPos().getX() - closest.getWidth(),closest.getPos().getY()));
        }
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
    public ArrayList<ModelBlock> getConnections(){
        ArrayList<ModelBlock> connections = new ArrayList<ModelBlock>();
        connections.add(getBottomPlug());
        connections.add(getLeftPlug());
        connections.add(getTopSocket());
        connections.add(getRightSocket());
        return connections; 
    }

    public int getHeight() {
		return this.height;
	}

    public int getWidth() {
		return this.width;
	}

    
}