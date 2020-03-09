package model;

import model.blocks.*;
import utilities.Location;

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
        this.setTurnLeftBlock(new ModelMoveBlock(this.getTurnLeftLocation(), 1));
        this.setTurnRightBlock(new ModelMoveBlock(this.getTurnRightLocation(),2));
        this.setForwardBlock(new ModelMoveBlock(this.getForwardLocation(),0));
        this.setNotBlock(new ModelNotBlock(this.getNotLocation()));
        this.setWallInFrontBlock(new ModelWallInFrontBlock(this.getWallInFrontLocation()));
        this.setWhileBlock(new ModelWhileIfBlock(this.getWhileLocation(),true));
        this.setIfBlock(new ModelWhileIfBlock(this.getIfLocation(),false));
    }

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
                    this.setTurnLeftBlock(new ModelMoveBlock(this.getTurnLeftLocation(),1));
                }
                if (((ModelMoveBlock) blk).getType() == 2){
                    this.setTurnRightBlock(new ModelMoveBlock(this.getTurnRightLocation(),2));
                }
                if (((ModelMoveBlock) blk).getType() == 0){
                    this.setForwardBlock(new ModelMoveBlock(this.getForwardLocation(),0));
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

    public ModelMoveBlock getTurnLeftBlock() {
        return this.turnLeftBlock;
    }

    public void setTurnLeftBlock(ModelMoveBlock turnLeftBlock) {
        this.turnLeftBlock = turnLeftBlock;
    }

    public ModelMoveBlock getTurnRightBlock() {
        return this.turnRightBlock;
    }

    public void setTurnRightBlock(ModelMoveBlock turnRightBlock) {
        this.turnRightBlock = turnRightBlock;
    }

    public ModelMoveBlock getForwardBlock() {
        return this.forwardBlock;
    }

    public void setForwardBlock(ModelMoveBlock forwardBlock) {
        this.forwardBlock = forwardBlock;
    }

    public ModelNotBlock getNotBlock() {
        return this.notBlock;
    }

    public void setNotBlock(ModelNotBlock notBlock) {
        this.notBlock = notBlock;
    }

    public ModelWallInFrontBlock getWallInFrontBlock() {
        return this.wallInFrontBlock;
    }

    public void setWallInFrontBlock(ModelWallInFrontBlock wallInFrontBlock) {
        this.wallInFrontBlock = wallInFrontBlock;
    }

    public ModelWhileIfBlock getWhileBlock() {
        return this.whileBlock;
    }

    public void setWhileBlock(ModelWhileIfBlock whileBlock) {
        this.whileBlock = whileBlock;
    }

    public ModelWhileIfBlock getIfBlock() {
        return this.ifBlock;
    }

    public void setIfBlock(ModelWhileIfBlock ifBlock) {
        this.ifBlock = ifBlock;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Location getTurnLeftLocation(){
        return this.turnLeftLocation;
    }

    public Location getTurnRightLocation(){
        return this.turnRightLocation;
    }

    public Location getForwardLocation(){
        return this.forwardLocation;
    }

    public Location getNotLocation(){
        return this.notLocation;
    }

    public Location getWallInFrontLocation(){
        return this.wallInFrontLocation;
    }

    public Location getIfLocation(){
        return this.ifLocation;
    }

    public Location getWhileLocation(){
        return this.whileLocation;
    }

    
}