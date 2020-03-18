package model;

import java.util.ArrayList;
import utilities.*;
import model.blocks.*;
import model.blocks.plugs.*;

public class ProgramRunner {

    //Parameters
    private static final int CELL_SIZE = 50;
    private static final GridLocation GOAL_CELL = new GridLocation(5, 5);
    private static final GridLocation ROBOT_START_LOCATION = new GridLocation(0, 0);
    private static final Direction ROBOT_START_DIRECTION = new Direction(Direction.RIGHT);
    private static final ArrayList<GridLocation> WALLS = new ArrayList<GridLocation>();

    private ModelBlock finishBlock;
    private boolean running;
    private ModelBlock current;

    public static ProgramState getInitialState(){
        return new ProgramState(ROBOT_START_DIRECTION, ROBOT_START_LOCATION, WALLS, GOAL_CELL, CELL_SIZE);
    }

    public ProgramRunner(){
        this.running = false;
    }

    public void initialise(ModelBlock start, ModelBlock finish){
        this.running = true;
        this.finishBlock = finish;
        this.current = start;
    }

    public void reset(){
        current.setUnHighlight();
        this.running = false;
        this.finishBlock = null;
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
        System.out.println("now executing: " + current.getBlockType().getType());
        if(this.current.equals(this.finishBlock)) {
            reset();
            return ProgramRunner.getInitialState();
        }
        else{
            this.highlightNext(programState);
            this.current = findNextBlock(programState);
            return step(programState);
        }
    }

    /**
     * This function executes one step of the program
     */
    public ProgramState step(ProgramState pState){
        switch(current.getBlockType().getType()){
            case(Blocktype.MOVEFORWARD):
                if(validPosition(move(pState))){
                    return move(pState);
                } else {
                    //TODO: what is move is invalid? Reset program?
                    this.reset();
                }
                break;
            case(Blocktype.TURNLEFT):
                //TODO: update robot direction
                return ProgramState.generateNew(pState, pState.getRobotDirection(), pState.getRobotLocation());
            case(Blocktype.TURNRIGHT):
                return ProgramState.generateNew(pState, pState.getRobotDirection(), pState.getRobotLocation());
            default:
                //TODO: what with other blocktypes?
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
    public void highlightNext(ProgramState programState){
        current.setUnHighlight();
        findNextBlock(programState).setHighlight();
    }

    /**
     * Returns the bottomplug if it is a normal block (move block) or if the condition of a WhileIf block fails 
     * If the condition of a WhileIfBlock succeeds it gives the first cavity block of the WhileIf block.
     * 
     * TODO: next block in execution // Maybe explain what is meant with this?
     * 
     * @return the next block that will be run in the program
     */
    public ModelBlock findNextBlock(ProgramState programState){
        if ((current.getBlockType().getType() == Blocktype.IF) || (current.getBlockType().getType() == Blocktype.WHILE)){
            if (evaluateCurrentCondition(programState)){
                return ((ModelWhileIfBlock)current).getCavitySocket();
            }
        }
        return ((BottomPlug)current).getBottomPlug();
    }

    /**
     * 
     * @return whether the current condition is true or false
     */
    public boolean evaluateCurrentCondition(ProgramState programState){
        boolean negate = false;
        if (current.hasRightSocket()){
            ModelBlock cond = ((RightSocket)current).getRightSocket();
            while (cond.hasRightSocket()){
                if(cond instanceof ModelNotBlock){
                    negate = !negate;
                    cond = ((ModelNotBlock)cond).getRightSocket();
                }
                else if((cond instanceof ModelWallInFrontBlock) && programState.wallInFrontOfRobot()){
                    return !negate;
                }
            }
        } 
        return negate;
    }

    private ProgramState move(ProgramState pState){
        GridLocation robotLocation;
        switch(pState.getRobotDirection().getDirection()){
            case Direction.UP:
                robotLocation = new GridLocation(pState.getRobotLocation().getX(), pState.getRobotLocation().getY() - 1);
                break;
            case Direction.RIGHT:
                robotLocation = new GridLocation(pState.getRobotLocation().getX() + 1, pState.getRobotLocation().getY());
                break;
            case Direction.DOWN:
                robotLocation = new GridLocation(pState.getRobotLocation().getX(), pState.getRobotLocation().getY() + 1);
                break;
            case Direction.LEFT:
                robotLocation = new GridLocation(pState.getRobotLocation().getX() - 1, pState.getRobotLocation().getY());
                break;
            default:
                throw new IllegalStateException("ProgramState has an illegal direction");
        }
        return ProgramState.generateNew(pState, pState.getRobotDirection(), robotLocation);
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