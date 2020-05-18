package model;

import javax.swing.JOptionPane;

import model.blocks.*;
import gameworldapi.*;

import java.util.ArrayList;
import java.util.Stack;

public class ProgramRunner {

    private ProgramState current;

    private boolean running;
    //private ModelBlock start;
    private GameWorld gameWorld;
    private GameWorldState initialState;

    //private Stack<ModelFunctionCallBlock> callStack;
    //private ArrayList<ModelFunctionDefinitionBlock> definitions;

    //private Stack<ProgramState> programUndoStateStack;
    //private Stack<ProgramState> programRedoStateStack;


    //Constructor
    public ProgramRunner(GameWorld gameWorld){
        this.gameWorld = gameWorld;
        this.initialState = gameWorld.getSnapshot();
        this.running = false;
        UndoRedoHandler.reset();
        //programUndoStateStack = new Stack<>();
        //programRedoStateStack = new Stack<>();
    }

    /**
     * Initialises a game
     *
     * @param start the first block in the program
     */
    public void initialise(ModelBlock start, ArrayList<ModelFunctionDefinitionBlock> definitions){
        this.running = true;
        //this.start = start;
        ModelBlock thisstart = start;
        //this.definitions = definitions;
        //callStack = new Stack<>();
        if (thisstart instanceof ModelCavityBlock) {
            //this.start = findNextBlock(this.start);
            thisstart = newFindNextBlock(thisstart);
        }
        current = new ProgramState(null, thisstart, new Stack<>(), gameWorld.getSnapshot());
        UndoRedoHandler.getInstance().executeRunner(current);
        current.getHighlight().setHighlight();
        //programUndoStateStack.push(new ProgramState(this.start, gameWorld.getSnapshot()));
        //updateHighlight(null, this.start);
    }

    /**
     * Resets the game to its original state
     *
     */
    public void reset(){
        if (isRunning()){
            //updateHighlight(programUndoStateStack.peek().getCurrent(), null);
            this.running = false;
            this.gameWorld.restore(initialState);
            UndoRedoHandler.getInstance().clearRunnerStacks();
            current = null;
            //this.programUndoStateStack.clear();
            //this.programRedoStateStack.clear();
        }
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
        if (current.getHighlight() == null) {
            reset();
        }
        else{
            Stack<ModelFunctionCallBlock> newCallStack = current.getCallStack();
            if(current.getHighlight() instanceof ModelActionBlock && gameWorld != null){
                ActionResult result = gameWorld.perform(((ModelActionBlock) current.getHighlight()).getAction());
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
            if(current.getHighlight() instanceof ModelFunctionCallBlock) newCallStack.push((ModelFunctionCallBlock) current.getHighlight());
            else if(leftFunctionDefinition()) newCallStack.pop();
            ModelBlock nextBlock = newFindNextBlock(current.getHighlight());
            ProgramState nextState = new ProgramState(current.getHighlight(), nextBlock, newCallStack, gameWorld.getSnapshot());
            UndoRedoHandler.getInstance().executeRunner(nextState);
            setState(nextState);
        }
    }

    private boolean leftFunctionDefinition(){
        if (current.getCurrent() == null || current.getHighlight() == null) return false;
        if (current.getCurrent().getSurroundingDefinitionBlock() != null) {
            if (!current.getCurrent().getSurroundingDefinitionBlock().equals(current.getHighlight().getSurroundingDefinitionBlock())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 
     * @return whether the program is currently running
     */
    public boolean isRunning(){
        return this.running;
    }

//    /**
//     * Highlights the next block in the program and unhighlight the current one
//     */
//    private void updateHighlight(ModelBlock current, ModelBlock next){
//        if (current != null)
//            current.setUnHighlight();
//        if (next != null)
//            next.setHighlight();
//    }

    public void setState(ProgramState state) {
        if (current.getHighlight() != null) current.getHighlight().setUnHighlight();
        current = state;
        if (current != null) {
            gameWorld.restore(current.getGameState());
            if (current.getHighlight() != null) current.getHighlight().setHighlight();
        }
        else running = false;
    }

//    /**
//     * Returns the bottomplug if it is a normal block (move block) or if the condition of a WhileIf block fails
//     * If the condition of a WhileIfBlock succeeds it gives the first cavity block of the WhileIf block.
//     * //This should point to the next block that has to be executed, this is needed so that when we step we know what block to run but also this one needs to be highlighted.
//     * @return the next block that will be run in the program
//     * @author Oberon Swings (debugged by Bert)
//     */
//    private ModelBlock findNextBlock(ModelBlock currentBlock){
//        ModelBlock nextBlock;
//        if (currentBlock == null) return start;
//        if (currentBlock instanceof ModelWhileIfBlock){
//            if (((ModelWhileIfBlock) currentBlock).isNegated()) {
//                if (!(gameWorld.evaluate(((ModelWhileIfBlock) currentBlock).getPredicate()))) {
//                    nextBlock =  ((ModelWhileIfBlock) currentBlock).getCavityPlug();
//                } else {
//                    nextBlock =  currentBlock.getBottomPlug();
//                }
//            } else {
//                if((gameWorld.evaluate(((ModelWhileIfBlock) currentBlock).getPredicate()))){
//                    nextBlock = ((ModelWhileIfBlock) currentBlock).getCavityPlug();
//                } else {
//                    nextBlock = currentBlock.getBottomPlug();
//                }
//            }
//        }
//        else if (currentBlock instanceof ModelFunctionCallBlock){
//            ModelFunctionDefinitionBlock def = this.getFuncDefBlockById(((ModelFunctionCallBlock) currentBlock).getId());
//            nextBlock =  def.getCavityPlug();
//        }
//        else if (currentBlock instanceof ModelFunctionDefinitionBlock){
//            //next =  callStack.peek().getBottomPlug();
//            nextBlock = current.getCallStack().peek().getBottomPlug();
//        }
//        else if (currentBlock.getBottomPlug() != null && currentBlock.getBottomPlug().isIf() && ((ModelWhileIfBlock)currentBlock.getBottomPlug()).getCavitySocket() == currentBlock){
//            nextBlock =  currentBlock.getBottomPlug().getBottomPlug(); //If block should only be executed once.
//        }
//        else nextBlock =  currentBlock.getBottomPlug();
//        while (nextBlock instanceof ModelCavityBlock) nextBlock = findNextBlock(nextBlock);
//        return nextBlock;
//    }

    public ModelBlock newFindNextBlock(ModelBlock block) {
        ModelBlock nextBlock = block.findNextBlock();
        if (nextBlock == null) return null;
        while (nextBlock instanceof ModelCavityBlock) {
            if (nextBlock instanceof ModelWhileIfBlock) {
                if (nextBlock.equals(block.getSurroundingCavityBlock()) && nextBlock.isIf()) nextBlock = nextBlock.getBottomPlug();
                else nextBlock = ((ModelWhileIfBlock) nextBlock).findNextBlock(gameWorld.evaluate(((ModelWhileIfBlock) nextBlock).getPredicate()));
            }
            else nextBlock = nextBlock.findNextBlock();
        }
        return nextBlock;
    }

//    public void updateState(ProgramState current, ProgramState next){
//        this.gameWorld.restore(next.getGameState());
//        ModelBlock currentB = current.getCurrent();
//        ModelBlock nextB = next.getCurrent();
//        updateHighlight(currentB, nextB);
//    }

//    public void callStackUpdate(ProgramState current, ProgramState next) {
//        if (current.getCurrent() == null || next.getCurrent() == null) return;
//        if (current.getCurrent().getSurroundingDefinitionBlock() != null) {
//            if (next.getCurrent().getSurroundingDefinitionBlock() != null) {
//                if (!current.getCurrent().getSurroundingDefinitionBlock().equals(next.getCurrent().getSurroundingDefinitionBlock())) {
//                    if (current.getCurrent() instanceof ModelFunctionCallBlock) {
//                        callStack.push((ModelFunctionCallBlock) current.getCurrent());
//                    }
//                    else callStack.pop();
//                }
//            }
//            else callStack.pop();
//        }
//        else
//        if (next.getCurrent().getSurroundingDefinitionBlock() != null) {
//            if (current.getCurrent() instanceof ModelFunctionCallBlock) {
//                callStack.push((ModelFunctionCallBlock) current.getCurrent());
//            }
//            else if (current.getCurrent().getTopSocket() instanceof  ModelFunctionCallBlock) {
//                callStack.push((ModelFunctionCallBlock) current.getCurrent().getTopSocket());
//            }
//        }
//    }
//
//
//    public void undoProgramRunner(){
//        if(this.programUndoStateStack.size() == 1){
//            this.running = false;
//        }
//        else{
//            programRedoStateStack.push(programUndoStateStack.pop());
//            ProgramState pastState = this.programUndoStateStack.peek();
//            callStackUpdate(programRedoStateStack.peek(), pastState);
//            this.updateState(programRedoStateStack.peek(), pastState);
//        }
//    }
//
//    public void redoProgramRunner(){
//        if(!this.programRedoStateStack.isEmpty()){
//            ProgramState pastState = this.programRedoStateStack.pop();
//            callStackUpdate(programUndoStateStack.peek(), pastState);
//            this.updateState(programUndoStateStack.peek() ,pastState);
//            programUndoStateStack.push(pastState);
//        }
//    }

//    /**
//     * Returns the function definition by id or null if the function definition
//     *
//     * @param id The requested id
//     * @return the requested block or null if no block with this ide has been founc
//     * @author bert_dvl
//     */
//    public ModelFunctionDefinitionBlock getFuncDefBlockById(int id){
//        for(ModelBlock block : this.definitions){
//            if(block instanceof ModelFunctionDefinitionBlock){
//                if(((ModelFunctionDefinitionBlock) block).getId() == id){
//                    return (ModelFunctionDefinitionBlock) block;
//                }
//            }
//        }
//        return null;
//    }


}