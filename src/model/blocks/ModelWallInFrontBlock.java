package model.blocks;

import utilities.Blocktype;
import utilities.Location;

/**
 * Class representing the 'wall in front' block with one plug to their left.
 */
public class ModelWallInFrontBlock extends ModelBlock implements LeftPlug{
    private ModelBlock leftPlug;


    public ModelWallInFrontBlock(Location pos, Blocktype type){
        super(pos,type);
        this.setLeftPlug(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void disconnect() {
        if (this.hasLeftPlug()){
            ((RightSocket)this.getLeftPlug()).setRightSocket(null);
            this.setLeftPlug(null);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void connect(ModelBlock block) {
        System.out.println("DISTANCE");
        System.out.println(this.getLeftPlugPos().getDistance(((RightSocket)block).getRightSocketPos()));
        if ((this.getLeftPlugPos().getDistance(((RightSocket)block).getRightSocketPos()) < ModelBlock.PLUGSIZE * 1.5)){
            System.out.println("WIF CONNECTS");
            if(block.hasRightSocket()){
                this.setLeftPlug(block);
                ((RightSocket)block).setRightSocket(this); 
                this.setPos(block.getPos().add(new Location(block.getWidth(),0)));  

            }
             
        }
        

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ModelBlock getLeftPlug() {
        return this.leftPlug;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLeftPlug(ModelBlock leftPlug) {
        this.leftPlug = leftPlug;
    }

    @Override
    public Location getLeftPlugPos() {
        return super.getPos().add(- ModelBlock.PLUGSIZE / 2, + this.getHeight() / 2);
    }

    @Override
    public boolean hasLeftPlug(){
        return true;
    }

    


}