package model;


import utilities.*;
import model.blocks.*;
import gameworldapi.*;

import java.util.ArrayList;
import java.util.Stack;

public class ProgramRunner {

    private boolean running;
    private ModelBlock current;
    private GameWorld gameWorld;
    private GameWorldState initialState;

    private Stack<ModelBlock> undoHighlightStack;
    private Stack<ModelBlock> redoHighlightStack;


    private Stack<GameWorldState> undoStateStack;
    private Stack<GameWorldState> redoStateStack;


    //Constructor
    public ProgramRunner(GameWorld gameWorld){
        this.gameWorld = gameWorld;
        this.initialState = gameWorld.getSnapshot();
        this.running = false;

        undoHighlightStack = new Stack<>();
        redoHighlightStack = new Stack<>();

        undoStateStack = new Stack<>();
        redoStateStack = new Stack<>();
    }

    public void initialise(ModelBlock start){
        this.running = true;
        this.current = start;
        this.current.setHighlight();

        //because of this you can't have sequential games in undo redo
        this.undoHighlightStack.clear();
        this.redoHighlightStack.clear();

        this.undoStateStack.clear();
        this.redoStateStack.clear();

        //TODO null or first block?
        this.undoHighlightStack.push(null);
        this.undoStateStack.push(initialState);




    }

    public void reset(){
        if (this.current != null){
            this.current.setUnHighlight();
        }
        this.running = false;
        this.current = null;
        this.gameWorld.restore(initialState);


    }

    /**
     * This function starts the execution
     * 1. this function starts the program if it is not running yet,
     * 2. then it steps,
     * 3. then it checks whether it is finished
     *  3a. if necessary, the program stops running
     *  3b. otherwise, the next block is highlighted
     * @author Jesse Geens
     */
    public void execute(){



        if(!(isRunning())){
            throw new IllegalStateException("tried executing the program without initialising it");
        }
        ModelBlock next;
        if(this.current == null) {
            reset();
        }
        else{
            if(current instanceof ModelActionBlock && gameWorld != null){
                ActionResult result = gameWorld.perform(((ModelActionBlock) current).getAction());

                this.undoHighlightStack.push(current);
                this.undoStateStack.push(gameWorld.getSnapshot());

                switch (result){
                    case FAILURE:
                        break;
                    case SUCCESS:
                        //TODO: Bert, hier moet ge de action aan de action stack toevoegen voor undo/redo
                        break;
                    case GAME_OVER:
                        //TODO: bekijken wat we gaan doen als het game over is
                        break;
                }
            }
            next = findNextBlock(current);
            while (next instanceof ModelWhileIfBlock) next = findNextBlock(next);
            if (next != null){
                this.highlightNext(next);
            }
            else{
                this.current.setUnHighlight();
            }
            this.current = next;
        }
    }

    /**
     * 
     * @return whether the program is currently running
     */
    public boolean isRunning(){
        return this.running;
    }

    /**
     * Highlights the next block in the program and unhighlight the currrent one
     */
    private void highlightNext(ModelBlock next){
        current.setUnHighlight();
        next.setHighlight();
    }

    /**
     *
     * @param block
     * @author Bert
     */
    private void setHighlight(ModelBlock block){
        if(block != null){
            block.setHighlight();
        }
    }

    /**
     *
     * @param block
     * @author Bert
     */
    private void setUnHighlight(ModelBlock block){
        if(block != null){
            block.setUnHighlight();
        }
    }

    /**
     * Returns the bottomplug if it is a normal block (move block) or if the condition of a WhileIf block fails 
     * If the condition of a WhileIfBlock succeeds it gives the first cavity block of the WhileIf block.
     * //This should point to the next block that has to be executed, this is needed so that when we step we know what block to run but also this one needs to be highlighted.
     * @return the next block that will be run in the program
     * @author Oberon Swings (debugged by Bert)
     */
    private ModelBlock findNextBlock(ModelBlock current){

        if (current instanceof ModelWhileIfBlock){
            if (((ModelWhileIfBlock) current).isNegated()) {
                if (!(gameWorld.evaluate(((ModelWhileIfBlock) current).getPredicate()))) {
                    return ((ModelWhileIfBlock) current).getCavityPlug();
                } else {
                    return current.getBottomPlug();
                }
            } else {
                if((gameWorld.evaluate(((ModelWhileIfBlock) current).getPredicate()))){
                    return ((ModelWhileIfBlock) current).getCavityPlug();
                } else {
                    return current.getBottomPlug();
                }
            }
        }
        
        if (current.getBottomPlug() != null && current.getBottomPlug().isIf() && ((ModelWhileIfBlock)current.getBottomPlug()).getCavitySocket() == current){
            return current.getBottomPlug().getBottomPlug(); //If block should only be executed once.
        } 
        else return current.getBottomPlug();
    }


    /**
     * @author Bert
     */
    public void undoProgramRunner(){
        this.setUnHighlight(current);

        if(!undoHighlightStack.isEmpty()){
            current = undoHighlightStack.pop();
            this.redoHighlightStack.push(current);
            this.setHighlight(current);



            GameWorldState undoState = undoStateStack.pop();
            this.redoStateStack.push(undoState);
            if(undoState == gameWorld.getSnapshot()){
                undoState = undoStateStack.pop();
                this.redoStateStack.push(undoState);

            }

            this.gameWorld.restore(undoState);


        }





    }

    /**
     * @author Bert
     */
    public void redoProgramrunner(){

    }
}