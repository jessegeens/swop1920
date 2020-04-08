package model;

import java.util.ArrayList;

import model.blocks.*;
import utilities.BlockType;
import utilities.Location;

/**
 * Class representing the palette. Blocks will be dragged from the palette int the program area.
 */
class ModelPalette{

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
    public ModelPalette(){
        turnLeftBlock = new ModelMoveBlock(turnLeftWindowLocation, BlockType.TURNLEFT);
        turnRightBlock = new ModelMoveBlock(turnRightWindowLocation, BlockType.TURNRIGHT);
        forwardBlock = new ModelMoveBlock(forwardWindowLocation, BlockType.MOVEFORWARD);
        notBlock = new ModelNotBlock(notWindowLocation, BlockType.NOT);
        wallInFrontBlock = new ModelWallInFrontBlock(wallInFrontWindowLocation, BlockType.WALLINFRONT);
        whileBlock = new ModelWhileIfBlock(whileWindowLocation, BlockType.WHILE);
        ifBlock = new ModelWhileIfBlock(ifWindowLocation, BlockType.IF);
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
     * removes all the blocks from the palette, when the maximum in the program area is reached for instance
     * @author Oberon Swings
     */
    public void removeBlocks(){
        turnLeftBlock = null;
        turnRightBlock = null;
        forwardBlock = null;
        notBlock = null;
        wallInFrontBlock = null;
        whileBlock = null;
        ifBlock = null;
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
    protected ModelBlock handleMouseDown(Location eventWindowLocation){
        ModelBlock selected = null;
        if(turnLeftBlock.inBoundsOfElement(eventWindowLocation)){
            selected = turnLeftBlock;
            resetBlocks();
        }
        else if(turnRightBlock.inBoundsOfElement(eventWindowLocation)){
            selected = turnRightBlock;
            resetBlocks();
        }
        else if(forwardBlock.inBoundsOfElement(eventWindowLocation)){
            selected = forwardBlock;
            resetBlocks();
        }
        else if(notBlock.inBoundsOfElement(eventWindowLocation)){
            selected = notBlock;
            resetBlocks();
        }
        else if(wallInFrontBlock.inBoundsOfElement(eventWindowLocation)){
            selected = wallInFrontBlock;
            resetBlocks();
        }
        else if(whileBlock.inBoundsOfElement(eventWindowLocation)){
            selected = whileBlock;
            resetBlocks();
        }
        else if(ifBlock.inBoundsOfElement(eventWindowLocation)){
            selected = ifBlock;
            resetBlocks();
        }
        return selected;
    }

    /**
     * Return all the blocks in the palette
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