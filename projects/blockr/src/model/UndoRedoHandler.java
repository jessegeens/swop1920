package model;

import java.util.Stack;

public class UndoRedoHandler {

    private static UndoRedoHandler instance;

    private Stack<ProgramState> runnerUndoStack;
    private Stack<ProgramState> runnerRedoStack;

    private Stack<OberonAction> actionUndoStack;
    private Stack<OberonAction> actionRedoStack;

    private UndoRedoHandler() {
        runnerUndoStack = new Stack<>();
        runnerRedoStack = new Stack<>();
        actionUndoStack = new Stack<>();
        actionRedoStack = new Stack<>();
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
        if (!actionUndoStack.empty()) actionRedoStack.push(actionUndoStack.pop());
    }

    public void redoAction() {
        if (!actionRedoStack.empty()) actionUndoStack.push(actionRedoStack.pop());
    }

    public void undo() {
        if (runnerUndoStack.empty()) undoAction();
        else undoRunner();
    }

    public void redo() {
        if (actionRedoStack.empty()) redoRunner();
        else redoAction();
    }

    public Object getState() {
        if (!runnerUndoStack.empty()) return runnerUndoStack.peek();
        else if (!actionUndoStack.empty()) return actionUndoStack.peek();
        else return null;
    }

    public void executeAction(OberonAction action) {
        actionUndoStack.push(action);
        runnerUndoStack.clear();
    }

    public void removeRedoActions() {
        actionRedoStack.clear();
    }

    public void executeRunner(ProgramState state) {
        runnerUndoStack.push(state);
    }
}
