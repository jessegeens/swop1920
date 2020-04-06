package model;

import java.util.ArrayList;

import model.blocks.*;
import utilities.BlockType;
import utilities.Location;

/**
 * Class representing the palette. Blocks will be dragged from the palette int the program area.
 */
class ModelPalette extends ModelWindow{

    private final Location turnLeftWindowLocation = new Location(20, 20);
    private final Location turnRightWindowLocation = new Location(180, 20);
    private final Location forwardWindowLocation = new Location(20, 180);
    private final Location notWindowLocation = new Location(180, 180);
    private final Location wallInFrontWindowLocation = new Location(20, 340);
    private final Location whileWindowLocation = new Location(180, 340);
    private final Location ifWindowLocation = new Location(20, 500);

    private ModelMoveBlock turnLeftBlock;
    private ModelMoveBlock turnRightBlock;
    private ModelMoveBlock forwardBlock;
    private ModelNotBlock notBlock;
    private ModelWallInFrontBlock wallInFrontBlock;
    private ModelWhileIfBlock whileBlock;
    private ModelWhileIfBlock ifBlock;

    // Constructor
    public ModelPalette(int width, int height){
        super(width,height);
        this.setTurnLeftBlock(new ModelMoveBlock(this.getTurnLeftWindowLocation(), BlockType.TURNLEFT));
        this.setTurnRightBlock(new ModelMoveBlock(this.getTurnRightWindowLocation(), BlockType.TURNRIGHT));
        this.setForwardBlock(new ModelMoveBlock(this.getForwardWindowLocation(), BlockType.MOVEFORWARD));
        this.setNotBlock(new ModelNotBlock(this.getNotWindowLocation(), BlockType.NOT));
        this.setWallInFrontBlock(new ModelWallInFrontBlock(this.getWallInFrontWindowLocation(), BlockType.WALLINFRONT));
        this.setWhileBlock(new ModelWhileIfBlock(this.getWhileWindowLocation(), BlockType.WHILE));
        this.setIfBlock(new ModelWhileIfBlock(this.getIfWindowLocation(), BlockType.IF));
    }

    /**
     * This method creates a new block and assigns the global variable of this specific blocktype to it.
     * 
     * TODO: limitreached is better of being a global var in modelpalette than a method parameter
     *       or maybe have a static int called maxAmountOfElements somewhere?
     * 
     * @param {ModelBlock} blk the block to be added from the palette into the program window. 
     * @param {Boolean} limitReached true than it won't be possible to place a block of some specific type into the program window.
     */
    public void blockToProgramArea(ModelBlock blk, Boolean limitReached){
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
            switch(blk.getBlockType()){
                case IF:
                    this.setIfBlock(new ModelWhileIfBlock(this.getIfWindowLocation(), BlockType.IF));
                    break;
                case WHILE:
                    this.setWhileBlock(new ModelWhileIfBlock(this.getWhileWindowLocation(), BlockType.WHILE));
                    break;
                case MOVEFORWARD:
                    this.setForwardBlock(new ModelMoveBlock(this.getForwardWindowLocation(), BlockType.MOVEFORWARD));
                    break;
                case TURNLEFT:
                    this.setTurnLeftBlock(new ModelMoveBlock(this.getTurnLeftWindowLocation(), BlockType.TURNLEFT));
                    break;
                case TURNRIGHT:
                    this.setTurnRightBlock(new ModelMoveBlock(this.getTurnRightWindowLocation(), BlockType.TURNRIGHT));
                    break;
                case WALLINFRONT:
                    this.setWallInFrontBlock(new ModelWallInFrontBlock(this.getWallInFrontWindowLocation(), BlockType.WALLINFRONT));
                    break;
                case NOT:
                    this.setNotBlock(new ModelNotBlock(this.getNotWindowLocation(), BlockType.NOT));
                    break;
            }
        }    
    }

    /**
     * Reset all the blocks after the total amount of blocks is lower than the maximum
     */
    public void resetBlocks(){
        this.setIfBlock(new ModelWhileIfBlock(this.getIfWindowLocation(), BlockType.IF));
        this.setWhileBlock(new ModelWhileIfBlock(this.getWhileWindowLocation(), BlockType.WHILE));
        this.setForwardBlock(new ModelMoveBlock(this.getForwardWindowLocation(), BlockType.MOVEFORWARD));
        this.setTurnLeftBlock(new ModelMoveBlock(this.getTurnLeftWindowLocation(), BlockType.TURNLEFT));
        this.setTurnRightBlock(new ModelMoveBlock(this.getTurnRightWindowLocation(), BlockType.TURNRIGHT));
        this.setWallInFrontBlock(new ModelWallInFrontBlock(this.getWallInFrontWindowLocation(), BlockType.WALLINFRONT));
        this.setNotBlock(new ModelNotBlock(this.getNotWindowLocation(), BlockType.NOT));
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
    public Location getTurnLeftWindowLocation(){
        return this.turnLeftWindowLocation;
    }

    /**
     * 
     * @return the location of the 'turn right' block.
     */
    public Location getTurnRightWindowLocation(){
        return this.turnRightWindowLocation;
    }

    /**
     * 
     * @return the location of the 'forward' block.
     */
    public Location getForwardWindowLocation(){
        return this.forwardWindowLocation;
    }

    /**
     * 
     * @return the location of the not block location.
     */
    public Location getNotWindowLocation(){
        return this.notWindowLocation;
    }

    /**
     * 
     * @return the location of the 'wall in front' block.
     */
    public Location getWallInFrontWindowLocation(){
        return this.wallInFrontWindowLocation;
    }

    /**
     * 
     * @return the location of the if block.
     */
    public Location getIfWindowLocation(){
        return this.ifWindowLocation;
    }

    /**
     * 
     * @return the location of the while block.
     */
    public Location getWhileWindowLocation(){
        return this.whileWindowLocation;
    }

    

    /**
     * This function handles the mouse down
     * 
     * 1. On mousedown, it checks whether a block is selected
     * 2. If it is in bounds of a block it calls a function to create a new block
     * 3. The clicked block is returned
     * 
     * @param {Location} eventLocation the location of the mouse
     * @param {Boolean} maxReached signifies whether the max number of blocks has been reached
     * @return the block to return when the mouse is held down
     */
    protected ModelBlock handleMouseDown(Location eventWindowLocation, boolean maxReached){
        ModelBlock selected = null;
        if(this.getTurnLeftBlock().inBoundsOfElement(eventWindowLocation)){
            selected = this.getTurnLeftBlock();
            this.blockToProgramArea(this.getTurnLeftBlock(), maxReached);
        }
        else if(this.getTurnRightBlock().inBoundsOfElement(eventWindowLocation)){
            selected = this.getTurnRightBlock();
            this.blockToProgramArea(this.getTurnRightBlock(), maxReached);
        }
        else if(this.getForwardBlock().inBoundsOfElement(eventWindowLocation)){
            selected = this.getForwardBlock();
            this.blockToProgramArea(this.getForwardBlock(), maxReached);
        }
        else if(this.getNotBlock().inBoundsOfElement(eventWindowLocation)){
            selected = this.getNotBlock();
            this.blockToProgramArea(this.getNotBlock(), maxReached);
        }
        else if(this.getWallInFrontBlock().inBoundsOfElement(eventWindowLocation)){
            selected = this.getWallInFrontBlock();
            this.blockToProgramArea(this.getWallInFrontBlock(), maxReached);
        }
        else if(this.getWhileBlock().inBoundsOfElement(eventWindowLocation)){
            selected = this.getWhileBlock();
            this.blockToProgramArea(this.getWhileBlock(), maxReached);
        }
        else if(this.getIfBlock().inBoundsOfElement(eventWindowLocation)){
            selected = this.getIfBlock();
            this.blockToProgramArea(this.getIfBlock(), maxReached);
        }
        return selected;
    }

    /**
     * Return all the blocks in the palette
     * 
     * TODO: what when max happens
     * TODO: refactoring to global arraylist
     * 
     */
    protected ArrayList<ModelBlock> getPaletteBlocks(){
        ArrayList<ModelBlock> blocks = new ArrayList<ModelBlock>();
        //if one of them is null, they all are
        if (this.turnLeftBlock != null){
            blocks.add(this.getTurnLeftBlock());
            blocks.add(this.getTurnRightBlock());
            blocks.add(this.getForwardBlock());
            blocks.add(this.getNotBlock());
            blocks.add(this.getWallInFrontBlock());
            blocks.add(this.getWhileBlock());
            blocks.add(this.getIfBlock());
        }
        return blocks;

    }
    
}