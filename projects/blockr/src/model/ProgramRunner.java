package model;

import javax.swing.JOptionPane;

import model.blocks.*;
import gameworldapi.*;

import java.util.ArrayList;
import java.util.Stack;

public class ProgramRunner {

    private boolean running;
    //private ModelBlock current;
    private ModelBlock start;
    private GameWorld gameWorld;
    private GameWorldState initialState;

    private Stack<ModelFunctionCallBlock> callStack;
    private ArrayList<ModelFunctionDefinitionBlock> definitions;

    private Stack<ProgramState> programUndoStateStack;
    private Stack<ProgramState> programRedoStateStack;


    //Constructor
    public ProgramRunner(GameWorld gameWorld){
        this.gameWorld = gameWorld;
        this.initialState = gameWorld.getSnapshot();
        this.running = false;

        programUndoStateStack = new Stack<>();
        programRedoStateStack = new Stack<>();
    }

    /**
     * Initialises a game
     *
     * @param start the first block in the program
     */
    public void initialise(ModelBlock start, ArrayList<ModelFunctionDefinitionBlock> definitions){
        //TODO arraylist

        this.running = true;
        this.start = start;

        this.definitions = definitions;

        callStack = new Stack<>();
        while (this.start instanceof ModelCavityBlock) {
            this.start = findNextBlock(this.start);
        }
        programUndoStateStack.push(new ProgramState(this.start, gameWorld.getSnapshot()));
        highlightNext(null, this.start);
    }

    /**
     * Clears all undo and redo stacks
     *
     * @author Bert
     */
    public void clearStacks(){
        this.programUndoStateStack.clear();
        this.programRedoStateStack.clear();

    }

    /**
     * Resets the game to its original state
     *
     */
    public void reset(){
        highlightNext(programUndoStateStack.peek().getBlock(), null);
        this.running = false;
        this.gameWorld.restore(initialState);

        this.programUndoStateStack.clear();
        this.programRedoStateStack.clear();

        System.out.println("RESET");
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
        ModelBlock current = programUndoStateStack.peek().getBlock();
        ModelBlock next = findNextBlock(current);

        if (current == null) {
            reset();
        }
        else{
            ProgramState currentState = programUndoStateStack.peek();
            if(current instanceof ModelActionBlock && gameWorld != null){
                ActionResult result = gameWorld.perform(((ModelActionBlock) current).getAction());
                programUndoStateStack.push(new ProgramState(next, gameWorld.getSnapshot()));

                switch (result){

                    case FAILURE:
                        break;
                    case SUCCESS:
                        break;
                    case GAME_OVER:
                        JOptionPane.showMessageDialog(null, "Too bad, you lost!", "Game lost", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    case GAME_SUCCESS:
                        JOptionPane.showMessageDialog(null, "Congratulations! You won!", "Game won", JOptionPane.INFORMATION_MESSAGE);
                        break;
                }
            }
            if(current instanceof ModelFunctionCallBlock){
                programUndoStateStack.push(new ProgramState(next, gameWorld.getSnapshot()));

            }
            ProgramState nextState = programUndoStateStack.peek();
            callStackUpdate(currentState, nextState);
        }
        this.highlightNext(current, next);
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
    private void highlightNext(ModelBlock current, ModelBlock next){
        if (current != null)
            current.setUnHighlight();
        if (next != null)
            next.setHighlight();
    }

    /**
     *  Highlights a block
     *
     * @param block
     * @author Bert
     */
    private void setHighlight(ModelBlock block){
        while (block instanceof ModelCavityBlock) block = findNextBlock(block); //still important
        if(block != null){
            block.setHighlight();
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
        ModelBlock next;

        if (current == null) return start;

        if (current instanceof ModelWhileIfBlock){
            if (((ModelWhileIfBlock) current).isNegated()) {
                if (!(gameWorld.evaluate(((ModelWhileIfBlock) current).getPredicate()))) {
                    next =  ((ModelWhileIfBlock) current).getCavityPlug();
                } else {
                    next =  current.getBottomPlug();
                }
            } else {
                if((gameWorld.evaluate(((ModelWhileIfBlock) current).getPredicate()))){
                    next = ((ModelWhileIfBlock) current).getCavityPlug();
                } else {
                    next = current.getBottomPlug();
                }
            }
        }
        else if (current instanceof ModelFunctionCallBlock){

            ModelFunctionDefinitionBlock def = this.getFuncDefBlockById(((ModelFunctionCallBlock) current).getId());
            next =  def.getCavityPlug();


        }
        else if (current instanceof ModelFunctionDefinitionBlock){
            next =  callStack.peek().getBottomPlug();
        }
        else if (current.getBottomPlug() != null && current.getBottomPlug().isIf() && ((ModelWhileIfBlock)current.getBottomPlug()).getCavitySocket() == current){
            next =  current.getBottomPlug().getBottomPlug(); //If block should only be executed once.
        }
        else next =  current.getBottomPlug();

        while (next instanceof ModelCavityBlock) next = findNextBlock(next);

        return next;
    }

    public void setState(ProgramState toBeSet){
        if(toBeSet.getBlock() == null){
            this.setHighlight(start);
            this.gameWorld.restore(toBeSet.getGameState());
        }
        else{
            this.gameWorld.restore(toBeSet.getGameState());
            ModelBlock current = programUndoStateStack.peek().getBlock();
            ModelBlock next = findNextBlock(current);
            highlightNext(current, next);
        }
    }

    public void callStackUpdate(ProgramState current, ProgramState next) {
        if (current.getBlock() == null || next.getBlock() == null) return;
        if (current.getBlock().getSurroundingCavityBlock() instanceof ModelFunctionDefinitionBlock) {
            if (next.getBlock().getSurroundingCavityBlock() instanceof ModelFunctionDefinitionBlock) {
                if (!current.getBlock().getSurroundingCavityBlock().equals(next.getBlock().getSurroundingCavityBlock())) {
                    if (current.getBlock() instanceof ModelFunctionCallBlock) {
                        callStack.push((ModelFunctionCallBlock) current.getBlock());
                    }
                    else callStack.pop();
                }
            }
            else callStack.pop();
        }
        else
        if (next.getBlock().getSurroundingCavityBlock() instanceof ModelFunctionDefinitionBlock) {
            if (current.getBlock() instanceof ModelFunctionCallBlock) {
                callStack.push((ModelFunctionCallBlock) current.getBlock());
            }
            if (current.getBlock().getTopSocket() instanceof  ModelFunctionCallBlock) {
                callStack.push((ModelFunctionCallBlock) current.getBlock().getTopSocket());
            }
        }
    }


    public void undoProgramRunner(){
        if(this.programUndoStateStack.size() == 1){
            this.running = false;
        }
        else{
            programRedoStateStack.push(programUndoStateStack.pop());
            ProgramState pastState = this.programUndoStateStack.peek();
            callStackUpdate(programRedoStateStack.peek(), pastState);
            this.setState(pastState);
        }
    }

    public void redoProgramRunner(){
        if(!this.programRedoStateStack.isEmpty()){
            ProgramState pastState = this.programRedoStateStack.pop();
            callStackUpdate(programUndoStateStack.peek(), pastState);
            this.setState(pastState);
            programUndoStateStack.push(pastState);
        }
    }

    /**
     * Returns the function definition by id or null if the function definition
     *
     * @param id The requested id
     * @return the requested block or null if no block with this ide has been founc
     * @author bert_dvl
     */
    public ModelFunctionDefinitionBlock getFuncDefBlockById(int id){

        for(ModelBlock block : this.definitions){
            if(block instanceof ModelFunctionDefinitionBlock){
                if(((ModelFunctionDefinitionBlock) block).getId() == id){
                    return (ModelFunctionDefinitionBlock) block;
                }
            }
        }
        return null;
    }


}