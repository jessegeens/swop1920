package model;

import java.util.ArrayList;

import org.w3c.dom.events.MouseEvent;

import model.blocks.*;
import utilities.Blocktype;
import utilities.Location;

/**
 * Class representing the palette. Blocks will be dragged from the palette int the program area.
 */
class ModelPalette extends ModelWindow{

    

    private final Location turnLeftLocation = new Location(20, 20);
    private final Location turnRightLocation = new Location(180, 20);
    private final Location forwardLocation = new Location(20, 180);
    private final Location notLocation = new Location(180, 180);
    private final Location wallInFrontLocation = new Location(20, 340);
    private final Location whileLocation = new Location(180, 340);
    private final Location ifLocation = new Location(20, 600);

    

    private ModelMoveBlock turnLeftBlock;
    private ModelMoveBlock turnRightBlock;
    private ModelMoveBlock forwardBlock;
    private ModelNotBlock notBlock;
    private ModelWallInFrontBlock wallInFrontBlock;
    private ModelWhileIfBlock whileBlock;
    private ModelWhileIfBlock ifBlock;


    public ModelPalette(int width, int height){
        super(width,height);
        this.setTurnLeftBlock(new ModelMoveBlock(this.getTurnLeftLocation(), new Blocktype(Blocktype.TURNLEFT)));
        this.setTurnRightBlock(new ModelMoveBlock(this.getTurnRightLocation(), new Blocktype(Blocktype.TURNRIGHT)));
        this.setForwardBlock(new ModelMoveBlock(this.getForwardLocation(), new Blocktype(Blocktype.MOVEFORWARD)));
        this.setNotBlock(new ModelNotBlock(this.getNotLocation(),new Blocktype(Blocktype.NOT)));
        this.setWallInFrontBlock(new ModelWallInFrontBlock(this.getWallInFrontLocation(),new Blocktype(Blocktype.WALLINFRONT)));
        this.setWhileBlock(new ModelWhileIfBlock(this.getWhileLocation(),new Blocktype(Blocktype.WHILE)));
        this.setIfBlock(new ModelWhileIfBlock(this.getIfLocation(),new Blocktype(Blocktype.IF)));
    }

    /**
     * Method to add a block to the program window.
     * @param blk the block to be added from the palette into the program window. 
     * @param limitReached true than it won't be possible to place a block of some specific type into the program window.
     */

    //TODO limitreached is better of being a global var in modelpalette than a method parameter
    //or maybe have a static int called maxamountofelements somewhere?

    //creates a new block and assigns the global var of this specific blocktype to it
    public void blockToProgramWindow(ModelBlock blk, Boolean limitReached){
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
            switch(blk.getBlockType().getType()){
                case Blocktype.IF:
                    this.setIfBlock(new ModelWhileIfBlock(this.getIfLocation(),new Blocktype(Blocktype.IF)));
                    break;
                case Blocktype.WHILE:
                    this.setWhileBlock(new ModelWhileIfBlock(this.getWhileLocation(),new Blocktype(Blocktype.WHILE)));
                    break;
                case Blocktype.MOVEFORWARD:
                    this.setForwardBlock(new ModelMoveBlock(this.getForwardLocation(), new Blocktype(Blocktype.MOVEFORWARD)));
                    break;
                case Blocktype.TURNLEFT:
                    this.setTurnLeftBlock(new ModelMoveBlock(this.getTurnLeftLocation(), new Blocktype(Blocktype.TURNLEFT)));
                    break;
                case Blocktype.TURNRIGHT:
                    this.setTurnRightBlock(new ModelMoveBlock(this.getTurnRightLocation(), new Blocktype(Blocktype.TURNRIGHT)));
                    break;
                case Blocktype.WALLINFRONT:
                    this.setWallInFrontBlock(new ModelWallInFrontBlock(this.getWallInFrontLocation(),new Blocktype(Blocktype.WALLINFRONT)));
                    break;
                case Blocktype.NOT:
                    this.setNotBlock(new ModelNotBlock(this.getNotLocation(),new Blocktype(Blocktype.NOT)));
                    break;
            }
        }    
    }

    //resets all blocks after the total amount of blocks is lower than max
    public void resetBlocks(){
        this.setIfBlock(new ModelWhileIfBlock(this.getIfLocation(),new Blocktype(Blocktype.IF)));
        this.setWhileBlock(new ModelWhileIfBlock(this.getWhileLocation(),new Blocktype(Blocktype.WHILE)));
        this.setForwardBlock(new ModelMoveBlock(this.getForwardLocation(), new Blocktype(Blocktype.MOVEFORWARD)));
        this.setTurnLeftBlock(new ModelMoveBlock(this.getTurnLeftLocation(), new Blocktype(Blocktype.TURNLEFT)));
        this.setTurnRightBlock(new ModelMoveBlock(this.getTurnRightLocation(), new Blocktype(Blocktype.TURNRIGHT)));
        this.setWallInFrontBlock(new ModelWallInFrontBlock(this.getWallInFrontLocation(),new Blocktype(Blocktype.WALLINFRONT)));
        this.setNotBlock(new ModelNotBlock(this.getNotLocation(),new Blocktype(Blocktype.NOT)));
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

    
    //on mousedown checks if a block is selected
    //if in bounds of a block it calls a function to create a new block
    //the clicked block is returned
    protected ModelBlock handleMouseDown(Location eventLocation, boolean maxReached){
        ModelBlock selected = null;
        if(this.turnLeftBlock.inBounds(eventLocation)){
            selected = this.turnLeftBlock;
            this.blockToProgramWindow(this.turnLeftBlock, maxReached);
        }
        else if(this.turnRightBlock.inBounds(eventLocation)){
            selected = this.turnRightBlock;
            this.blockToProgramWindow(this.turnRightBlock, maxReached);
        }
        else if(this.forwardBlock.inBounds(eventLocation)){
            selected = this.forwardBlock;
            this.blockToProgramWindow(this.forwardBlock, maxReached);
        }
        else if(this.notBlock.inBounds(eventLocation)){
            selected = this.notBlock;
            this.blockToProgramWindow(this.notBlock, maxReached);
        }
        else if(this.wallInFrontBlock.inBounds(eventLocation)){
            selected = this.wallInFrontBlock;
            this.blockToProgramWindow(this.wallInFrontBlock, maxReached);
        }
        else if(this.whileBlock.inBounds(eventLocation)){
            selected = this.whileBlock;
            this.blockToProgramWindow(this.whileBlock, maxReached);
        }
        else if(this.ifBlock.inBounds(eventLocation)){
            selected = this.ifBlock;
            this.blockToProgramWindow(this.ifBlock, maxReached);
        }


        return selected;

    }

    //TODO what when max happens
    //TODO refactoring to global arraylist
    protected ArrayList<ModelBlock> getBlocks(){
        ArrayList<ModelBlock> blocks = new ArrayList<ModelBlock>();
        //if one of them is null, they all are
        if (this.turnLeftBlock != null){
            blocks.add(this.turnLeftBlock);
            blocks.add(this.turnRightBlock);
            blocks.add(this.forwardBlock);
            blocks.add(this.notBlock);
            blocks.add(this.wallInFrontBlock);
            blocks.add(this.whileBlock);
            blocks.add(this.ifBlock);
        }
        return blocks;
        
    }



    

    
}