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

    public static void reset() {
        instance = new UndoRedoHandler();
    }

    public void clearRunnerStacks() {
        runnerUndoStack.clear();
        runnerRedoStack.clear();
    }

    public void undoRunner(){
        if (!this.runnerUndoStack.empty()) runnerRedoStack.push(runnerUndoStack.pop());
    }

    public void redoRunner(){
        if (!runnerRedoStack.empty()) runnerUndoStack.push(runnerRedoStack.pop());
    }

    public void undoAction() {
        if (!blockActionUndoStack.empty()) blockActionRedoStack.push(blockActionUndoStack.pop());
    }

    public void redoAction() {
        if (!blockActionRedoStack.empty()) blockActionUndoStack.push(blockActionRedoStack.pop());
    }

    public void undo() {
        if (runnerUndoStack.empty()) undoAction();
        else undoRunner();
    }

    public void redo() {
        if (blockActionRedoStack.empty()) redoRunner();
        else redoAction();
    }

    public Object getState() {
        if (!runnerUndoStack.empty()) return runnerUndoStack.peek();
        else if (!blockActionUndoStack.empty()) return blockActionUndoStack.peek();
        else return null;
    }

    public void executeAction(BlockAction blockAction) {
        blockActionUndoStack.push(blockAction);
        runnerUndoStack.clear();
    }

    public void removeRedoActions() {
        blockActionRedoStack.clear();
    }

    public void executeRunner(ProgramState state) {
        runnerRedoStack.clear();
        runnerUndoStack.push(state);
    }
}
