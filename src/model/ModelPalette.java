package model;

import java.util.ArrayList;

import model.blocks.*;
import utilities.Blocktype;
import utilities.WindowLocation;

/**
 * Class representing the palette. Blocks will be dragged from the palette int the program area.
 */
class ModelPalette extends ModelWindow{

    private final WindowLocation turnLeftWindowLocation = new WindowLocation(20, 20);
    private final WindowLocation turnRightWindowLocation = new WindowLocation(180, 20);
    private final WindowLocation forwardWindowLocation = new WindowLocation(20, 180);
    private final WindowLocation notWindowLocation = new WindowLocation(180, 180);
    private final WindowLocation wallInFrontWindowLocation = new WindowLocation(20, 340);
    private final WindowLocation whileWindowLocation = new WindowLocation(180, 340);
    private final WindowLocation ifWindowLocation = new WindowLocation(20, 500);

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
        this.setTurnLeftBlock(new ModelMoveBlock(this.getTurnLeftWindowLocation(), new Blocktype(Blocktype.TURNLEFT)));
        this.setTurnRightBlock(new ModelMoveBlock(this.getTurnRightWindowLocation(), new Blocktype(Blocktype.TURNRIGHT)));
        this.setForwardBlock(new ModelMoveBlock(this.getForwardWindowLocation(), new Blocktype(Blocktype.MOVEFORWARD)));
        this.setNotBlock(new ModelNotBlock(this.getNotWindowLocation(),new Blocktype(Blocktype.NOT)));
        this.setWallInFrontBlock(new ModelWallInFrontBlock(this.getWallInFrontWindowLocation(),new Blocktype(Blocktype.WALLINFRONT)));
        this.setWhileBlock(new ModelWhileIfBlock(this.getWhileWindowLocation(),new Blocktype(Blocktype.WHILE)));
        this.setIfBlock(new ModelWhileIfBlock(this.getIfWindowLocation(),new Blocktype(Blocktype.IF)));
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
            switch(blk.getBlockType().getType()){
                case Blocktype.IF:
                    this.setIfBlock(new ModelWhileIfBlock(this.getIfWindowLocation(),new Blocktype(Blocktype.IF)));
                    break;
                case Blocktype.WHILE:
                    this.setWhileBlock(new ModelWhileIfBlock(this.getWhileWindowLocation(),new Blocktype(Blocktype.WHILE)));
                    break;
                case Blocktype.MOVEFORWARD:
                    this.setForwardBlock(new ModelMoveBlock(this.getForwardWindowLocation(), new Blocktype(Blocktype.MOVEFORWARD)));
                    break;
                case Blocktype.TURNLEFT:
                    this.setTurnLeftBlock(new ModelMoveBlock(this.getTurnLeftWindowLocation(), new Blocktype(Blocktype.TURNLEFT)));
                    break;
                case Blocktype.TURNRIGHT:
                    this.setTurnRightBlock(new ModelMoveBlock(this.getTurnRightWindowLocation(), new Blocktype(Blocktype.TURNRIGHT)));
                    break;
                case Blocktype.WALLINFRONT:
                    this.setWallInFrontBlock(new ModelWallInFrontBlock(this.getWallInFrontWindowLocation(),new Blocktype(Blocktype.WALLINFRONT)));
                    break;
                case Blocktype.NOT:
                    this.setNotBlock(new ModelNotBlock(this.getNotWindowLocation(),new Blocktype(Blocktype.NOT)));
                    break;
            }
        }    
    }

    /**
     * Reset all the blocks after the total amount of blocks is lower than the maximum
     */
    public void resetBlocks(){
        this.setIfBlock(new ModelWhileIfBlock(this.getIfWindowLocation(),new Blocktype(Blocktype.IF)));
        this.setWhileBlock(new ModelWhileIfBlock(this.getWhileWindowLocation(),new Blocktype(Blocktype.WHILE)));
        this.setForwardBlock(new ModelMoveBlock(this.getForwardWindowLocation(), new Blocktype(Blocktype.MOVEFORWARD)));
        this.setTurnLeftBlock(new ModelMoveBlock(this.getTurnLeftWindowLocation(), new Blocktype(Blocktype.TURNLEFT)));
        this.setTurnRightBlock(new ModelMoveBlock(this.getTurnRightWindowLocation(), new Blocktype(Blocktype.TURNRIGHT)));
        this.setWallInFrontBlock(new ModelWallInFrontBlock(this.getWallInFrontWindowLocation(),new Blocktype(Blocktype.WALLINFRONT)));
        this.setNotBlock(new ModelNotBlock(this.getNotWindowLocation(),new Blocktype(Blocktype.NOT)));
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
    public WindowLocation getTurnLeftWindowLocation(){
        return this.turnLeftWindowLocation;
    }

    /**
     * 
     * @return the location of the 'turn right' block.
     */
    public WindowLocation getTurnRightWindowLocation(){
        return this.turnRightWindowLocation;
    }

    /**
     * 
     * @return the location of the 'forward' block.
     */
    public WindowLocation getForwardWindowLocation(){
        return this.forwardWindowLocation;
    }

    /**
     * 
     * @return the location of the not block location.
     */
    public WindowLocation getNotWindowLocation(){
        return this.notWindowLocation;
    }

    /**
     * 
     * @return the location of the 'wall in front' block.
     */
    public WindowLocation getWallInFrontWindowLocation(){
        return this.wallInFrontWindowLocation;
    }

    /**
     * 
     * @return the location of the if block.
     */
    public WindowLocation getIfWindowLocation(){
        return this.ifWindowLocation;
    }

    /**
     * 
     * @return the location of the while block.
     */
    public WindowLocation getWhileWindowLocation(){
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
    protected ModelBlock handleMouseDown(WindowLocation eventWindowLocation, boolean maxReached){
        ModelBlock selected = null;
        if(this.getTurnLeftBlock().inBounds(eventWindowLocation)){
            selected = this.getTurnLeftBlock();
            this.blockToProgramArea(this.getTurnLeftBlock(), maxReached);
        }
        else if(this.getTurnRightBlock().inBounds(eventWindowLocation)){
            selected = this.getTurnRightBlock();
            this.blockToProgramArea(this.getTurnRightBlock(), maxReached);
        }
        else if(this.getForwardBlock().inBounds(eventWindowLocation)){
            selected = this.getForwardBlock();
            this.blockToProgramArea(this.getForwardBlock(), maxReached);
        }
        else if(this.getNotBlock().inBounds(eventWindowLocation)){
            selected = this.getNotBlock();
            this.blockToProgramArea(this.getNotBlock(), maxReached);
        }
        else if(this.getWallInFrontBlock().inBounds(eventWindowLocation)){
            selected = this.getWallInFrontBlock();
            this.blockToProgramArea(this.getWallInFrontBlock(), maxReached);
        }
        else if(this.getWhileBlock().inBounds(eventWindowLocation)){
            selected = this.getWhileBlock();
            this.blockToProgramArea(this.getWhileBlock(), maxReached);
        }
        else if(this.getIfBlock().inBounds(eventWindowLocation)){
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