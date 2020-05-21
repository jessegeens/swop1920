package model;

import utilities.BlockAction;
import utilities.ProgramState;

import java.util.Stack;

public class UndoRedoHandler {

    private static UndoRedoHandler instance;

    private Stack<ProgramState> runnerUndoStack;
    private Stack<ProgramState> runnerRedoStack;

    private Stack<BlockAction> blockActionUndoStack;
    private Stack<BlockAction> blockActionRedoStack;

    private UndoRedoHandler() {
        runnerUndoStack = new Stack<>();
        runnerRedoStack = new Stack<>();
        blockActionUndoStack = new Stack<>();
        blockActionRedoStack = new Stack<>();
    }

    public static UndoRedoHandler getInstance() {
        if (instance == null) {
            instance = new UndoRedoHandler();
        }
        return instance;
    }

    /**
     * resets the undoRedoHandler
     */
    public static void reset() {
        instance = new UndoRedoHandler();
    }

    /**
     * clears the stacks for execution.
     */
    public void clearRunnerStacks() {
        runnerUndoStack.clear();
        runnerRedoStack.clear();
    }

    /**
     * Undo for the execution
     */
    public void undoRunner(){
        if (!this.runnerUndoStack.empty()) runnerRedoStack.push(runnerUndoStack.pop());
    }

    /**
     * redo for the execution
     */
    public void redoRunner(){
        if (!runnerRedoStack.empty()) runnerUndoStack.push(runnerRedoStack.pop());
    }

    /**
     * undo for the block actions
     */
    public void undoAction() {
        if (!blockActionUndoStack.empty()) blockActionRedoStack.push(blockActionUndoStack.pop());
    }

    /**
     * redo for the block actions
     */
    public void redoAction() {
        if (!blockActionRedoStack.empty()) blockActionUndoStack.push(blockActionRedoStack.pop());
    }

    /**
     * global undo
     */
    public void undo() {
        if (runnerUndoStack.empty()) undoAction();
        else undoRunner();
    }

    /**
     * global redo
     */
    public void redo() {
        if (blockActionRedoStack.empty()) redoRunner();
        else redoAction();
    }

    /**
     *
     * @return the state which should be shown now.
     */
    public Object getState() {
        if (!runnerUndoStack.empty()) return runnerUndoStack.peek();
        else if (!blockActionUndoStack.empty()) return blockActionUndoStack.peek();
        else return null;
    }

    /**
     * adds action when this is executed for the first time.
     * @param blockAction
     */
    public void executeAction(BlockAction blockAction) {
        blockActionUndoStack.push(blockAction);
        runnerUndoStack.clear();
    }

    /**
     * clears the block redo stack.
     */
    public void removeRedoActions() {
        blockActionRedoStack.clear();
    }

    /**
     * adds state when this is executed for the first time.
     * @param state
     */
    public void executeRunner(ProgramState state) {
        runnerRedoStack.clear();
        runnerUndoStack.push(state);
    }
}
