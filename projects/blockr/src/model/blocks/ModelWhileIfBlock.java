package model.blocks;

import java.util.ArrayList;

import gameworldapi.PredicateType;
import ui.UIBlock;
import utilities.*;

/**
 * Class representing the while and if blocks with one socket on the top and one plug at the bottom. They also have one socket on their 
 * right side for a condition block.
 */
public class ModelWhileIfBlock extends ModelCavityBlock{
    private ModelBlock topSocket;
    private ModelBlock bottomPlug;
    private ModelBlock rightSocket;
    private boolean isIf;

    // Constructor
    public ModelWhileIfBlock(ProgramLocation pos, boolean isIf){
        super(pos);
        this.isIf = isIf;
        this.setTopSocket(null);
        this.setBottomPlug(null);
        this.setRightSocket(null);
        ArrayList<ConnectionPoint> connectionPoints = super.getConnectionPoints();
        connectionPoints.add(ConnectionPoint.BOTTOM_PLUG);
        connectionPoints.add(ConnectionPoint.TOP_SOCKET);
        connectionPoints.add(ConnectionPoint.RIGHT_SOCKET);
        super.setConnectionPoints(connectionPoints);
    }

    @Override
    public ModelWhileIfBlock clone() {
        return new ModelWhileIfBlock(this.getPos(), isIf);
    }

    /**
     * Finds out if the predicate in the while/if block is negated
     * @return true if the predicate is negated, false otherwise
     * @author Oberon Swings
     */
    public boolean isNegated(){
        boolean negated = false;
        ModelBlock current = this.getRightSocket();
        while (!(current instanceof ModelPredicateBlock)){
            if (current instanceof ModelNotBlock){
                negated = !negated;
                current = current.getRightSocket();
            }
            else {
                return false;
            }
        }
        return negated;
    }

    /**
     * Finds out the predicate of the while/if block
     * @return the predicate which is coupled to this while/if block
     * @author Oberon Swings
     */
    public PredicateType getPredicate(){
        ModelBlock current = this.getRightSocket();
        while (!(current instanceof ModelPredicateBlock)){
            if (current == null){
                return null;
            }
            current = current.getRightSocket();
        }
        return ((ModelPredicateBlock)current).getPredicate();
    }

    public boolean isIf(){
        return isIf;
    }

    /**
     * {@inheritDoc}
     */
    public ModelBlock getTopSocket() {
        return this.topSocket;
    }

    /**
     * {@inheritDoc}
     */
    public void setTopSocket(ModelBlock topSocket) {
        this.topSocket = topSocket;
    }

    /**
     * {@inheritDoc}
     */
    public ModelBlock getBottomPlug() {
        return this.bottomPlug;
    }

    /**
     * {@inheritDoc}
     */
    public void setBottomPlug(ModelBlock bottomPlug) {
        this.bottomPlug = bottomPlug;
    }

    /**
     * {@inheritDoc}
     */
    public ModelBlock getRightSocket() {
        return this.rightSocket;
    }

    /**
     * {@inheritDoc}
     */
    public void setRightSocket(ModelBlock rightSocket) {
        this.rightSocket = rightSocket;
    }
}