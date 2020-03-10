package model;

import model.blocks.*;
import utilities.Location;

/**
 * Class representing the palette. Blocks will be dragged from the palette int the program area.
 */
class ModelPalette extends ModelWindow{

    private int width; // Should be at least 300
    private int height; // Should be at least 600

    private final Location turnLeftLocation = new Location(20, 20);
    private final Location turnRightLocation = new Location(160, 20);
    private final Location forwardLocation = new Location(300, 20);
    private final Location notLocation = new Location(440, 20);
    private final Location wallInFrontLocation = new Location(20, 160);
    private final Location whileLocation = new Location(160, 160);
    private final Location ifLocation = new Location(300, 160);

    private ModelMoveBlock turnLeftBlock;
    private ModelMoveBlock turnRightBlock;
    private ModelMoveBlock forwardBlock;
    private ModelNotBlock notBlock;
    private ModelWallInFrontBlock wallInFrontBlock;
    private ModelWhileIfBlock whileBlock;
    private ModelWhileIfBlock ifBlock;


    public ModelPalette(){
        this.setTurnLeftBlock(new ModelMoveBlock(this.getTurnLeftLocation(), ModelMoveBlock.LEFT));
        this.setTurnRightBlock(new ModelMoveBlock(this.getTurnRightLocation(),ModelMoveBlock.RIGHT));
        this.setForwardBlock(new ModelMoveBlock(this.getForwardLocation(),ModelMoveBlock.FORWARD));
        this.setNotBlock(new ModelNotBlock(this.getNotLocation()));
        this.setWallInFrontBlock(new ModelWallInFrontBlock(this.getWallInFrontLocation()));
        this.setWhileBlock(new ModelWhileIfBlock(this.getWhileLocation(),true));
        this.setIfBlock(new ModelWhileIfBlock(this.getIfLocation(),false));
    }

    /**
     * Method to add a block to the program window.
     * @param blk the block to be added from the palette into the program window. 
     * @param limitReached true than it won't be possible to place a block of some specific type into the program window.
     */
    public void BlockToProgramWindow(ModelBlock blk, Boolean limitReached){
        if (limitReached){
            setTurnLeftBlock(null);
            setTurnRightBlock(null);
            setForwardBlock(null);
            setNotBlock(null);
            setWallInFrontBlock(null);
            setWhileBlock(null);
            setIfBlock(null);
        }
        else{
            if (blk instanceof ModelMoveBlock){
                if (((ModelMoveBlock) blk).getType() == 1){
                    this.setTurnLeftBlock(new ModelMoveBlock(this.getTurnLeftLocation(),ModelMoveBlock.LEFT));
                }
                if (((ModelMoveBlock) blk).getType() == 2){
                    this.setTurnRightBlock(new ModelMoveBlock(this.getTurnRightLocation(),ModelMoveBlock.RIGHT));
                }
                if (((ModelMoveBlock) blk).getType() == 0){
                    this.setForwardBlock(new ModelMoveBlock(this.getForwardLocation(),ModelMoveBlock.FORWARD));
                }
            }
            else if (blk instanceof ModelNotBlock){
                this.setNotBlock(new ModelNotBlock(this.getNotLocation()));
            }
            else if (blk instanceof ModelWallInFrontBlock){
                this.setWallInFrontBlock(new ModelWallInFrontBlock(this.getWallInFrontLocation()));
            }
            else if (blk instanceof ModelWhileIfBlock){
                if (((ModelWhileIfBlock) blk).isWhile()){
                    this.setWhileBlock(new ModelWhileIfBlock(this.getWhileLocation(), true));
                }
                else {
                    this.setIfBlock(new ModelWhileIfBlock(this.getIfLocation(), false));
                }
            }
        }
    }

    /**
     * 
     * @return a turn left block.
     */
    public ModelMoveBlock getTurnLeftBlock() {
        return this.turnLeftBlock;
    }

    /**
     * 
     * @param turnLeftBlock sets the turn left block.
     */
    public void setTurnLeftBlock(ModelMoveBlock turnLeftBlock) {
        this.turnLeftBlock = turnLeftBlock;
    }

    /**
     * 
     * @return a turn right block.
     */
    public ModelMoveBlock getTurnRightBlock() {
        return this.turnRightBlock;
    }

    /**
     * 
     * @param turnRightBlock sets the turn right block.
     */
    public void setTurnRightBlock(ModelMoveBlock turnRightBlock) {
        this.turnRightBlock = turnRightBlock;
    }

    /**
     * 
     * @return the forward block.
     */
    public ModelMoveBlock getForwardBlock() {
        return this.forwardBlock;
    }

    /**
     * 
     * @param forwardBlock sets the forward block.
     */
    public void setForwardBlock(ModelMoveBlock forwardBlock) {
        this.forwardBlock = forwardBlock;
    }

    /**
     * 
     * @return the not block.
     */
    public ModelNotBlock getNotBlock() {
        return this.notBlock;
    }

    /**
     * 
     * @param notBlock sets the not block.
     */
    public void setNotBlock(ModelNotBlock notBlock) {
        this.notBlock = notBlock;
    }

    /**
     * 
     * @return the 'wall in front' block.
     */
    public ModelWallInFrontBlock getWallInFrontBlock() {
        return this.wallInFrontBlock;
    }

    /**
     * 
     * @param wallInFrontBlock sets the 'wall in front' block.
     */
    public void setWallInFrontBlock(ModelWallInFrontBlock wallInFrontBlock) {
        this.wallInFrontBlock = wallInFrontBlock;
    }

    /**
     * 
     * @return the while block.
     */
    public ModelWhileIfBlock getWhileBlock() {
        return this.whileBlock;
    }

    /**
     * 
     * @param whileBlock sets the while block.
     */
    public void setWhileBlock(ModelWhileIfBlock whileBlock) {
        this.whileBlock = whileBlock;
    }

    /**
     * 
     * @return the if block.
     */
    public ModelWhileIfBlock getIfBlock() {
        return this.ifBlock;
    }

    /**
     * 
     * @param ifBlock sets the if block.
     */
    public void setIfBlock(ModelWhileIfBlock ifBlock) {
        this.ifBlock = ifBlock;
    }

    /**
     * 
     * @return the width of the palette.
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * 
     * @param width the width to be set to the palette.
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * 
     * @return the height of the palette.
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * 
     * @param height the height to be set to the palette.
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * 
     * @return the location of the 'turn left' block.
     */
    public Location getTurnLeftLocation(){
        return this.turnLeftLocation;
    }

    /**
     * 
     * @return the location of the 'turn right' block.
     */
    public Location getTurnRightLocation(){
        return this.turnRightLocation;
    }

    /**
     * 
     * @return the location of the 'forward' block.
     */
    public Location getForwardLocation(){
        return this.forwardLocation;
    }

    /**
     * 
     * @return the location of the not block location.
     */
    public Location getNotLocation(){
        return this.notLocation;
    }

    /**
     * 
     * @return the location of the 'wall in front' block.
     */
    public Location getWallInFrontLocation(){
        return this.wallInFrontLocation;
    }

    /**
     * 
     * @return the location of the if block.
     */
    public Location getIfLocation(){
        return this.ifLocation;
    }

    /**
     * 
     * @return the location of the while block.
     */
    public Location getWhileLocation(){
        return this.whileLocation;
    }

    
}