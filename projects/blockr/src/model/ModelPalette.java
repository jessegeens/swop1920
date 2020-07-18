package model;

import java.util.ArrayList;
import java.util.Comparator;

import gameworldapi.ActionType;
import gameworldapi.PredicateType;
import model.blocks.*;
import ui.UIBlock;
import ui.window.Content;
import utilities.ProgramLocation;

/**
 * Class representing the palette. Blocks will be dragged from the palette int the program area.
 */
public class ModelPalette implements Content {

    private final ModelController modelController;

    private final ArrayList<ActionType> actions;
    private final ArrayList<PredicateType> predicates;
    private ArrayList<ModelBlock> blocks;

    private int functionCounter;


    public ModelPalette(ArrayList<ActionType> actions, ArrayList<PredicateType> predicates, ModelController modelController){
        this.actions = actions;
        this.predicates = predicates;
        this.modelController = modelController;
        functionCounter = 0;
        populateBlocks();
    }

    /**
     * Fill the list of blocks with one of each type
     * @author Jesse Geens
     */
    public void populateBlocks(){
        this.blocks = new ArrayList<ModelBlock>();
        int i = 0;
        for (ActionType action : actions){
            ModelActionBlock actionBlock = new ModelActionBlock(new ProgramLocation(180, 20 + (int)(UIBlock.STD_HEIGHT * 1.5f * (i + 1))), action);
            blocks.add(actionBlock);
            i++;
        }
        int j = 0;
        for (PredicateType predicate : predicates){
            ModelPredicateBlock predicateBlock = new ModelPredicateBlock(new ProgramLocation(20, 20 + (int)(UIBlock.STD_HEIGHT * 1.5f * (j + 3))), predicate);
            blocks.add(predicateBlock);
            j++;
        }
        blocks.add(new ModelWhileIfBlock(new ProgramLocation(20, 20), true));
        blocks.add(new ModelWhileIfBlock(new ProgramLocation(20, 20 + (int)(UIBlock.STD_HEIGHT * 1.5f)), false));
        blocks.add(new ModelNotBlock(new ProgramLocation(180, 20)));
        blocks.add(new ModelFunctionDefinitionBlock(new ProgramLocation(20, 20 + (int)(UIBlock.STD_HEIGHT * 1.5f * 2)),functionCounter));
    }

    public void populateBlocks(ArrayList<ModelFunctionDefinitionBlock> idList){
        this.populateBlocks();
        idList.sort(Comparator.comparingInt(ModelFunctionDefinitionBlock::getId));
        for(int i = 0; i<idList.size(); i++){
            int Xpos = i%2 == 0 ? (20) : (180);
            int Ypos = (int) (Math.floor(i/2f) * (int)(UIBlock.STD_HEIGHT * 1.5f) + getMaxColumnHeight());
            this.blocks.add(new ModelFunctionCallBlock(new ProgramLocation(Xpos, Ypos), idList.get(i)));
        }
    }

    /**
     * check for the columnHeight for populating the palette.
     * @return
     */
    private int getMaxColumnHeight() {
        return Math.max(20 + (int)(UIBlock.STD_HEIGHT * 1.5f * (actions.size() + 1)), 20 + (int)(UIBlock.STD_HEIGHT * 1.5f * (predicates.size() + 3)));
    }

    /**
     * removes all the blocks from the palette, when the maximum in the program area is reached for instance
     * @author Oberon Swings
     */
    public void removeBlocks(){
        this.blocks = new ArrayList<ModelBlock>();
    }

    /**
     * Updates the function counter according to the active defenitionBlocks.
     * @param ids
     */
    public void updateFunctionCounter(ArrayList<ModelFunctionDefinitionBlock> ids) {
        int highestId = -1;
        for (ModelFunctionDefinitionBlock id : ids) {
            if (id.getId() > highestId) highestId = id.getId();
        }
        functionCounter = highestId + 1;
    }

    /**
     * This function handles the mouse down
     * 
     * 1. On mousedown, it checks whether a block is selected
     * 2. If it is in bounds of a block it calls a function to create a new block
     * 3. The clicked block is returned
     * 
     * @param {ProgramLocation} eventWindowLocation the location of the mouse
     * @return the block to return when the mouse is held down
     *
     * @author Bert
     */
    protected ModelBlock returnSelectedBlock(ProgramLocation eventWindowLocation){
        for(ModelBlock block : blocks){
            if(block.inBoundsOfElement(eventWindowLocation)){
                if(block instanceof ModelFunctionDefinitionBlock){
                    this.functionCounter++;
                    return block;
                }
                else{
                    return block.clone();
                }
            }
        }
        return null;
    }

    /**
     * Return all the blocks in the palette
     */
    protected ArrayList<ModelBlock> getPaletteBlocks(){
        return blocks;
    }

    @Override
    public int getHeight() {
        int minY = 0;
        int maxY = Integer.MIN_VALUE;
        for (ModelBlock block : blocks){
            if (block.getPos().getY() < minY) minY = block.getPos().getY();
            if (block.getPos().getY() + block.getHeight() > maxY) maxY = block.getPos().getY() + block.getHeight();
        }
        return maxY - minY;
    }

    @Override
    public void onClick(int x, int y) {
        ProgramLocation location = new ProgramLocation(x, y);
        modelController.selectPalette(location);
    }

    @Override
    public void onDrag(int x, int y) {
        ProgramLocation location = new ProgramLocation(x, y);
        modelController.drag(location);
    }

    @Override
    public void onRelease(int x, int y) {
        ProgramLocation location = new ProgramLocation(x, y);
        modelController.releasePalette(location);
    }
}