package model;

import java.util.ArrayList;

import gameworldapi.ActionType;
import gameworldapi.PredicateType;
import model.blocks.*;
import utilities.ProgramLocation;

/**
 * Class representing the palette. Blocks will be dragged from the palette int the program area.
 */
class ModelPalette{

    //TODO dynamically adding function call blocks
    //dynamically removing as well (how do you do this with undo redo?)
    //just add a function definition block to predicates

    private final ArrayList<ActionType> actions;
    private final ArrayList<PredicateType> predicates;
    private ArrayList<ModelBlock> blocks;
    private int functionCounter;

    // Constructor
    public ModelPalette(ArrayList<ActionType> actions, ArrayList<PredicateType> predicates){
        this.actions = actions;
        this.predicates = predicates;
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
            ModelActionBlock actionBlock = new ModelActionBlock(new ProgramLocation(180, 140 + 120*i), action);
            blocks.add(actionBlock);
            i++;
        }
        i = 0;
        for (PredicateType predicate : predicates){
            ModelPredicateBlock predicateBlock = new ModelPredicateBlock(new ProgramLocation(20, 380 + 120*i), predicate);
            blocks.add(predicateBlock);
            i++;
        }
        blocks.add(new ModelWhileIfBlock(new ProgramLocation(20, 20), true));
        blocks.add(new ModelWhileIfBlock(new ProgramLocation(20, 140), false));
        blocks.add(new ModelNotBlock(new ProgramLocation(180, 20)));
        blocks.add(new ModelFunctionDefinitionBlock(new ProgramLocation(20, 260),functionCounter));
    }

    /**
     * removes all the blocks from the palette, when the maximum in the program area is reached for instance
     * @author Oberon Swings
     */
    public void removeBlocks(){
        this.blocks = new ArrayList<ModelBlock>();
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
    protected ModelBlock handleMouseDown(ProgramLocation eventWindowLocation){
        for(ModelBlock block : blocks){
            if(block.inBoundsOfElement(eventWindowLocation)){
                if(block instanceof ModelFunctionDefinitionBlock){
                    this.functionCounter++;
                    ModelFunctionDefinitionBlock paletteReplacement = new ModelFunctionDefinitionBlock(block.getPos(), this.functionCounter);
                    this.blocks.remove(block);
                    this.blocks.add(paletteReplacement);
                    //TODO generate function call block


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
     * Add a function call block to the palette
     *
     * @author bert_dvl
     */
    //TODO what about removal? and repositioning after removal
    //Add it to the blocks list


    /**
     * Return all the blocks in the palette
     */
    protected ArrayList<ModelBlock> getPaletteBlocks(){
        return blocks;
    }
    
}