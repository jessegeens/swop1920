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
        turnLeftBlock = new ModelMoveBlock(turnLeftWindowLocation, BlockType.TURNLEFT);
        turnRightBlock = new ModelMoveBlock(turnRightWindowLocation, BlockType.TURNRIGHT);
        forwardBlock = new ModelMoveBlock(forwardWindowLocation, BlockType.MOVEFORWARD);
        notBlock = new ModelNotBlock(notWindowLocation, BlockType.NOT);
        wallInFrontBlock = new ModelWallInFrontBlock(wallInFrontWindowLocation, BlockType.WALLINFRONT);
        whileBlock = new ModelWhileIfBlock(whileWindowLocation, BlockType.WHILE);
        ifBlock = new ModelWhileIfBlock(ifWindowLocation, BlockType.IF);
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
            turnLeftBlock = null;
            turnRightBlock = null;
            forwardBlock = null;
            notBlock = null;
            wallInFrontBlock = null;
            whileBlock = null;
            ifBlock = null;
        }
        else{
            switch(blk.getBlockType()){
                case IF:
                    ifBlock = new ModelWhileIfBlock(ifWindowLocation, BlockType.IF);
                    break;
                case WHILE:
                    whileBlock = new ModelWhileIfBlock(whileWindowLocation, BlockType.WHILE);
                    break;
                case MOVEFORWARD:
                    forwardBlock = new ModelMoveBlock(forwardWindowLocation, BlockType.MOVEFORWARD);
                    break;
                case TURNLEFT:
                    turnLeftBlock = new ModelMoveBlock(turnLeftWindowLocation, BlockType.TURNLEFT);
                    break;
                case TURNRIGHT:
                    turnRightBlock = new ModelMoveBlock(turnRightWindowLocation, BlockType.TURNRIGHT);
                    break;
                case WALLINFRONT:
                    wallInFrontBlock = new ModelWallInFrontBlock(wallInFrontWindowLocation, BlockType.WALLINFRONT);
                    break;
                case NOT:
                    notBlock = new ModelNotBlock(notWindowLocation, BlockType.NOT);
                    break;
            }
        }    
    }

    /**
     * Reset all the blocks after the total amount of blocks is lower than the maximum
     */
    public void resetBlocks(){
        ifBlock = new ModelWhileIfBlock(ifWindowLocation, BlockType.IF);
        whileBlock =  new ModelWhileIfBlock(whileWindowLocation, BlockType.WHILE);
        forwardBlock = new ModelMoveBlock(forwardWindowLocation, BlockType.MOVEFORWARD);
        turnLeftBlock =  new ModelMoveBlock(turnLeftWindowLocation, BlockType.TURNLEFT);
        turnRightBlock = new ModelMoveBlock(turnRightWindowLocation, BlockType.TURNRIGHT);
        wallInFrontBlock = new ModelWallInFrontBlock(wallInFrontWindowLocation, BlockType.WALLINFRONT);
        notBlock = new ModelNotBlock(notWindowLocation, BlockType.NOT);
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
        if(turnLeftBlock.inBoundsOfElement(eventWindowLocation)){
            selected = turnLeftBlock;
            this.blockToProgramArea(turnLeftBlock, maxReached);
        }
        else if(turnRightBlock.inBoundsOfElement(eventWindowLocation)){
            selected = turnRightBlock;
            this.blockToProgramArea(turnRightBlock, maxReached);
        }
        else if(forwardBlock.inBoundsOfElement(eventWindowLocation)){
            selected = forwardBlock;
            this.blockToProgramArea(forwardBlock, maxReached);
        }
        else if(notBlock.inBoundsOfElement(eventWindowLocation)){
            selected = notBlock;
            this.blockToProgramArea(notBlock, maxReached);
        }
        else if(wallInFrontBlock.inBoundsOfElement(eventWindowLocation)){
            selected = wallInFrontBlock;
            this.blockToProgramArea(wallInFrontBlock, maxReached);
        }
        else if(whileBlock.inBoundsOfElement(eventWindowLocation)){
            selected = whileBlock;
            this.blockToProgramArea(whileBlock, maxReached);
        }
        else if(ifBlock.inBoundsOfElement(eventWindowLocation)){
            selected = ifBlock;
            this.blockToProgramArea(ifBlock, maxReached);
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
            blocks.add(turnLeftBlock);
            blocks.add(turnRightBlock);
            blocks.add(forwardBlock);
            blocks.add(notBlock);
            blocks.add(wallInFrontBlock);
            blocks.add(whileBlock);
            blocks.add(ifBlock);
        }
        return blocks;
    }
    
}