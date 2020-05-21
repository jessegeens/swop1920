package utilities;

import gameworldapi.GameWorldState;
import model.blocks.ModelBlock;
import model.blocks.ModelFunctionCallBlock;

import java.util.Stack;

/**
 * Class which keeps a ProgramRunner game state
 *
 * @author bert_dvl
 */
public class ProgramState {
    private final ModelBlock current;
    private final ModelBlock highlight;
    private final Stack<ModelFunctionCallBlock> callStack;
    private final GameWorldState gameState;

    public ProgramState(ModelBlock current, ModelBlock highlight, Stack<ModelFunctionCallBlock> callStack, GameWorldState gameState){
        this.current = current;
        this.highlight = highlight;
        this.callStack = callStack;
        this.gameState = gameState;
    }

    public ModelBlock getCurrent(){
        return current;
    }

    public ModelBlock getHighlight() {
        return highlight;
    }

    public Stack<ModelFunctionCallBlock> getCallStack() {
        return callStack;
    }

    public GameWorldState getGameState(){
        return gameState;
    }
}
