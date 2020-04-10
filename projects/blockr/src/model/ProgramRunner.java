package model;

import utilities.*;
import model.blocks.*;
import gameworldapi.*;

public class ProgramRunner {

    private boolean running;
    private ModelBlock current;
    private GameWorld gameWorld;
    private GameWorldState initialState;

    //Constructor
    public ProgramRunner(GameWorld gameWorld){
        this.gameWorld = gameWorld;
        this.initialState = gameWorld.getSnapshot();
        this.running = false;
    }

    public void initialise(ModelBlock start){
        this.running = true;
        this.current = start;
        this.current.setHighlight();
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
            //return gameWorld.();
            //TODO: used to reset to initial state
        }
        else{
            next = findNextBlock();
            if (next != null){
                this.highlightNext();
            }
            else{
                this.current.setUnHighlight();
            }
            if(current instanceof ModelActionBlock && gameWorld != null){
                ActionResult result = gameWorld.perform(((ModelActionBlock) current).getAction());
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
            this.current = next;
            while (current instanceof ModelWhileIfBlock) this.current = findNextBlock();
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
    private void highlightNext(){
        current.setUnHighlight();
        findNextBlock().setHighlight();
    }

    /**
     * Returns the bottomplug if it is a normal block (move block) or if the condition of a WhileIf block fails 
     * If the condition of a WhileIfBlock succeeds it gives the first cavity block of the WhileIf block.
     * //This should point to the next block that has to be executed, this is needed so that when we step we know what block to run but also this one needs to be highlighted.
     * @return the next block that will be run in the program
     * @author Oberon Swings (debugged by Bert)
     */
    private ModelBlock findNextBlock(){

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

}