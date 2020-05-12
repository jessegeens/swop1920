package model;

import javax.swing.JOptionPane;

import model.blocks.*;
import gameworldapi.*;

import java.util.ArrayList;
import java.util.Stack;

public class ProgramRunner {

    private boolean running;
    private ModelBlock current;
    private ModelBlock start;
    private GameWorld gameWorld;
    private GameWorldState initialState;


    private Stack<ModelBlock> undoHighlightStack;
    private Stack<ModelBlock> redoHighlightStack;


    private Stack<GameWorldState> undoStateStack;
    private Stack<GameWorldState> redoStateStack;

    private Stack<ModelFunctionCallBlock> callStack;
    ArrayList<ModelFunctionDefinitionBlock> definitions;

    private Stack<ProgramState> programUndoStateStack;
    private Stack<ProgramState> programRedoStateStack;


    //Constructor
    public ProgramRunner(GameWorld gameWorld){
        this.gameWorld = gameWorld;
        this.initialState = gameWorld.getSnapshot();
        this.running = false;
        /*

        undoHighlightStack = new Stack<>();
        redoHighlightStack = new Stack<>();

        undoStateStack = new Stack<>();
        redoStateStack = new Stack<>();
        */

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
        this.current = start;
        this.start = start;
        //while (current instanceof ModelWhileIfBlock) this.current = findNextBlock(current);
        this.definitions = definitions;

         callStack = new Stack<>();

         programUndoStateStack.push(new ProgramState(null, gameWorld.getSnapshot()));


        //code for highlight issue with whileif
        /*
        if(current instanceof ModelWhileIfBlock){
            this.setUnHighlight(current);
            if(findNextBlock(current) != null){
                this.highlightNext(findNextBlock(current));
            }

        }
        else{
            this.current.setHighlight();
        }
        */

        //this would need to be commented out for whileif highlight stuff
        //this.current.setHighlight();

        /*

        //because of this you can't have sequential games in undo redo
        this.undoHighlightStack.clear();
        this.redoHighlightStack.clear();

        this.undoStateStack.clear();
        this.redoStateStack.clear();
        */



        current.setHighlight();

        /*
        this.undoHighlightStack.push(current);
        this.undoStateStack.push(initialState);
        */
        //double push due to imbalance in stacks otherwise
        //this.undoStateStack.push(initialState);


    }

    /**
     * When undo happens, the game only needs to be activated, currentblock does not need to be reset
     *
     * @author Bert
     */
    public void initialiseForUndo(){
        this.running = true;


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

        if (this.current != null){
            this.current.setUnHighlight();
        }
        this.running = false;
        this.current = null;
        this.gameWorld.restore(initialState);

        /*
        this.undoHighlightStack.clear();
        this.redoHighlightStack.clear();

        this.undoStateStack.clear();
        this.redoStateStack.clear();

        */
        /*

        this.redoStateStack.clear();
        this.redoHighlightStack.clear();
        */

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
     * @return  true of executed
     * @author Jesse Geens
     */
    public boolean execute(){
        //this.clearStacks();




        if(!(isRunning())){
            throw new IllegalStateException("tried executing the program without initialising it");
        }
        ModelBlock next;
        if(this.current == null) {
            reset();
            return true;

        }
        else{
            if(current instanceof ModelActionBlock && gameWorld != null){
                ActionResult result = gameWorld.perform(((ModelActionBlock) current).getAction());
                programUndoStateStack.push(new ProgramState(current, gameWorld.getSnapshot()));
                /*
                this.undoHighlightStack.push(current);
                this.undoStateStack.push(gameWorld.getSnapshot());
                System.out.println(result.toString());
                */
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
                this.callStack.push((ModelFunctionCallBlock) current);


            }

            next = findNextBlock(current);

            if (next != null){
                this.highlightNext(next);
                //TODO whileif highlight code

                /*
                if(next instanceof ModelWhileIfBlock){
                    this.setUnHighlight(next);
                    if(findNextBlock(next) != null){
                        this.highlightNext(findNextBlock(next));
                    }

                }
*/

            }
            else{
                this.current.setUnHighlight();
            }


            this.current = next;
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

    /**
     * Highlights the next block in the program and unhighlight the currrent one
     */
    private void highlightNext(ModelBlock next){
        current.setUnHighlight();
        next.setHighlight();
    }

    /**
     *  Highlights a block
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
     *  Unhighlights a block
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

        ModelBlock next = null;

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
        if (current instanceof ModelFunctionCallBlock){
            this.callStack.add((ModelFunctionCallBlock) current);

            ModelFunctionDefinitionBlock def = this.getFuncDefBlockById(((ModelFunctionCallBlock) current).getId());
            next =  def.getCavityPlug();


        }

        if (current instanceof ModelFunctionDefinitionBlock){
            next =  callStack.pop().getBottomPlug();
        }
        
        if (current.getBottomPlug() != null && current.getBottomPlug().isIf() && ((ModelWhileIfBlock)current.getBottomPlug()).getCavitySocket() == current){
            next =  current.getBottomPlug().getBottomPlug(); //If block should only be executed once.
        }

        else next =  current.getBottomPlug();

        while (next instanceof ModelCavityBlock) next = findNextBlock(next);

        return next;
    }

    /**
     * Checks wheter the undo stacks are empty
     * @return true if finished
     * @author bert_dvl
     */
    public boolean undoFinished(){
        return (this.undoHighlightStack.isEmpty() || this.undoStateStack.isEmpty());
    }

    /**
     * Checks whether the redo stacks are empty
     * @return true if finished
     * @author bert_dvl
     */
    public boolean redoFinished(){
        return (this.redoHighlightStack.isEmpty() || this.redoStateStack.isEmpty());
    }



    public void setState(ProgramState toBeSet){
        //this.setHighlight(findNextBlock(current));

        if(toBeSet.getBlock() == null){
            this.setHighlight(start);
            this.gameWorld.restore(toBeSet.getGameState());
        }
        else{
            this.current = toBeSet.getBlock();
            this.gameWorld.restore(toBeSet.getGameState());
            this.setHighlight(findNextBlock(current));
        }



        //highlight


    }


    public void undoProgramRunner(){
        //TODO make sure this works
        if(this.programUndoStateStack.size() == 1){
            this.running = false;
        }
        else{
            programRedoStateStack.push(programUndoStateStack.pop());
            ProgramState pastState = this.programUndoStateStack.peek();
            this.setState(pastState);

        }

    }

    public void redoProgramRunner(){
        if(this.programRedoStateStack.isEmpty()){
            //this.running = false;
        }
        else{
            ProgramState pastState = this.programRedoStateStack.pop();

            if(pastState.getBlock() == current && pastState.getGameState() == gameWorld.getSnapshot()){
                programUndoStateStack.push(pastState);
                pastState = this.programRedoStateStack.pop();
            }
            this.setState(pastState);
            programUndoStateStack.push(pastState);
        }

    }


    /**
     * Undoes the programrunner by taking the previous gamestate and previously highlighted block and setting these as the current state
     * The popped states get pushed to their respective redo stacks
     *
     * @author Bert
     */
    public void undoProgramRunner_(){
        /*
        System.out.println("undostack PR undo");
        System.out.println(undoStateStack.size());
        System.out.println(undoHighlightStack.size());
        System.out.println(undoStateStack.toString());
        System.out.println(undoHighlightStack.toString());
        System.out.println("redostack PR undo");
        System.out.println(redoStateStack.toString());
        System.out.println(redoHighlightStack.toString());
        */

        //ifwhile wordt niet gehighlight bij undo
        //highlighten in het algemeen doet (mogelijks) louche bij sequentiële undo redo
        this.setUnHighlight(current);

        if(!(undoHighlightStack.isEmpty() || undoStateStack.isEmpty())){
            redoHighlightStack.push(current);
            this.current = undoHighlightStack.pop();

            //this.redoHighlightStack.push(current);
            this.setHighlight(current);



            GameWorldState undoState = undoStateStack.pop();
            this.redoStateStack.push(undoState);
            if(undoState == gameWorld.getSnapshot() && !undoStateStack.isEmpty() ){

                undoState = undoStateStack.pop();
                this.redoStateStack.push(undoState);


                /*
                this.setUnHighlight(current);
                redoHighlightStack.push(current);
                this.current = undoHighlightStack.pop();
                this.setHighlight(current);
                */

            }

            this.gameWorld.restore(undoState);


        }

        if((undoHighlightStack.isEmpty() || undoStateStack.isEmpty())){
            this.running = false;
            this.setUnHighlight(current);

            //TODO check undo after game end

            System.out.println("empty");



        }










    }



    /**
     * Redoes the programrunner by taking the next gamestate and previously highlighted block and setting these as the current state
     * The popped states get pushed to their respective undo stacks
     *
     * @author Bert
     */
    public void redoProgramRunner_(){

        /*

        System.out.println("undostack PR redo ");
        System.out.println(undoStateStack.toString());
        System.out.println(undoHighlightStack.toString());
        System.out.println("redostack PR redo");
        System.out.println(redoStateStack.toString());
        System.out.println(redoHighlightStack.toString());

        */

        this.setUnHighlight(current);

        if(!(redoHighlightStack.isEmpty() || redoStateStack.isEmpty())){
            this.undoHighlightStack.push(current);

            current = redoHighlightStack.pop();



            this.setHighlight(current);



            GameWorldState redoState = redoStateStack.pop();
            this.undoStateStack.push(redoState);
            if(redoState == gameWorld.getSnapshot() && !redoStateStack.isEmpty()){
                redoState = redoStateStack.pop();
                this.undoStateStack.push(redoState);

            }

            this.gameWorld.restore(redoState);


        }

        System.out.println(this.isRunning());
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