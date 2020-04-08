package model;

import utilities.*;
import model.blocks.*;
import gameworldapi.*;

public class ProgramRunner {

    private boolean running;
    private ModelBlock current;

    public ProgramRunner(){
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
    }

    /**
     * This function starts the execution
     * 1. this function starts the program if it is not running yet,
     * 2. then it steps,
     * 3. then it checks whether it is finished
     *  3a. if necessary, the program stops running
     *  3b. otherwise, the next block is highlighted
     */
    public ProgramState execute(ProgramState programState){
        if(!(isRunning())){
            throw new IllegalStateException("tried executing the program without initialising it");
        }

        if(this.current == null) {
            reset();
            return ProgramState.getInitialState();
        }
        else{
            System.out.println("now executing: " + current.getBlockType());
            if (this.findNextBlock(programState) != null){
                System.out.println("lala1");
                this.highlightNext(programState);
            }
            else{
                System.out.println("lala2");
                this.current.setUnHighlight();
            }
            ProgramState nextState = step(programState);
            this.current = findNextBlock(programState);
            System.out.println("programState: " + programState.toString());
            return nextState;
        }
    }

    /**
     * This function executes one step of the program
     */
    private ProgramState step(ProgramState pState){
        switch(current.getBlockType()){
            case MOVEFORWARD:
                if(validPosition(move(pState))){
                    return move(pState);
                } else {
                    this.reset();
                }
                break;
            case TURNLEFT:
                Direction directionL = pState.getRobotDirection().turnLeft();
                return ProgramState.generateNew(pState, directionL, pState.getRobotLocation());
            case TURNRIGHT:
                Direction directionR = pState.getRobotDirection().turnRight();
                return ProgramState.generateNew(pState, directionR, pState.getRobotLocation());
            default:
                break;
        }
        return null;
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
    private void highlightNext(ProgramState programState){
        current.setUnHighlight();
        findNextBlock(programState).setHighlight();
    }

    /**
     * Returns the bottomplug if it is a normal block (move block) or if the condition of a WhileIf block fails 
     * If the condition of a WhileIfBlock succeeds it gives the first cavity block of the WhileIf block.
     * //This should point to the next block that has to be executed, this is needed so that when we step we know what block to run but also this one needs to be highlighted.
     * @return the next block that will be run in the program
     * @author Oberon Swings (debugged by Bert)
     */
    private ModelBlock findNextBlock(ProgramState programState){

        if (current instanceof ModelWhileIfBlock){
            if (evaluateCurrentCondition(programState)){
                return ((ModelWhileIfBlock)current).getCavityPlug();
            }
        }
        
        if (current.getBottomPlug() != null && current.getBottomPlug().getBlockType() == BlockType.IF){
            return current.getBottomPlug().getBottomPlug(); //If block should only be executed once.
        } 
        else return current.getBottomPlug();
    }

    /**
     * Evaluates the condition (of a while if block)
     * @return whether the current condition is true or false
     * @author Oberon Swings
     */
    private boolean evaluateCurrentCondition(ProgramState programState){
        Condition condition = null;
        if (current instanceof ModelWhileIfBlock) {
            try {
                condition = ((ModelWhileIfBlock) current).getCondition();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        if (condition != null){
            switch (condition){
                case NOT_WALL_IN_FRONT:
                    if (programState.wallInFrontOfRobot()) return false;
                    else return true;
                case WALL_IN_FRONT:
                    if (programState.wallInFrontOfRobot()) return true;
                    else return false;
            }
        }
        return false;
    }

    private ProgramState move(ProgramState pState){
        ProgramLocation robotGridLocation;
        switch(pState.getRobotDirection()){
            case UP:
                /*Location has an add function so it would be more compact to write:
                robotLocation = robotLocation.add(0,-1);*/
                robotGridLocation = new ProgramLocation(pState.getRobotLocation().getX(), pState.getRobotLocation().getY() - 1);
                break;
            case RIGHT:
                robotGridLocation = new ProgramLocation(pState.getRobotLocation().getX() + 1, pState.getRobotLocation().getY());
                break;
            case DOWN:
                robotGridLocation = new ProgramLocation(pState.getRobotLocation().getX(), pState.getRobotLocation().getY() + 1);
                break;
            case LEFT:
                robotGridLocation = new ProgramLocation(pState.getRobotLocation().getX() - 1, pState.getRobotLocation().getY());
                break;
            default:
                throw new IllegalStateException("ProgramState has an illegal direction");
        }
        return ProgramState.generateNew(pState, pState.getRobotDirection(), robotGridLocation);
    }

    /**
     * 
     * TODO: get max grid width and height and check if it is out of bounds there
     * 
     */
    private boolean validPosition(ProgramState pState){
        if(pState.getRobotLocation().getX() < 0) return false;
        if(pState.getRobotLocation().getY() < 0) return false;
        if(pState.getWalls().contains(pState.getRobotLocation())) return false;
        return true;
    }

}