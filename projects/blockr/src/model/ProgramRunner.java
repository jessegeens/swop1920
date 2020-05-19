package model;

import javax.swing.JOptionPane;
import model.blocks.*;
import gameworldapi.*;
import java.util.ArrayList;
import java.util.Stack;

public class ProgramRunner {

    private ProgramState current;

    private boolean running;
    private GameWorld gameWorld;
    private GameWorldState initialState;

    //Constructor
    public ProgramRunner(GameWorld gameWorld){
        this.gameWorld = gameWorld;
        this.initialState = gameWorld.getSnapshot();
        this.running = false;
        UndoRedoHandler.reset();
    }

    /**
     * Initialises a game
     *
     * @param start the first block in the program
     */
    public void initialise(ModelBlock start){
        this.running = true;
        ModelBlock thisstart = start;
        if (thisstart instanceof ModelCavityBlock) {
            thisstart = newFindNextBlock(thisstart);
        }
        current = new ProgramState(null, thisstart, new Stack<>(), gameWorld.getSnapshot());
        UndoRedoHandler.getInstance().executeRunner(current);
        current.getHighlight().setHighlight();
    }

    /**
     * Resets the game to its original state
     *
     */
    public void reset(){
        if (isRunning()){
            this.running = false;
            this.gameWorld.restore(initialState);
            if (current.getHighlight() != null) current.getHighlight().setUnHighlight();
            UndoRedoHandler.getInstance().clearRunnerStacks();
            current = null;
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

    public void setState(ProgramState state) {
        if (current != null && current.getHighlight() != null) current.getHighlight().setUnHighlight();
        current = state;
        if (current != null) {
            gameWorld.restore(current.getGameState());
            if (current.getHighlight() != null) current.getHighlight().setHighlight();
            running = true;
        }
        else running = false;
    }

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
}