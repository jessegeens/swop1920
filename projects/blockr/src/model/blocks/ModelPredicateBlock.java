package model.blocks;

import gameworldapi.PredicateType;
import utilities.*;

import java.util.ArrayList;

/**
 * Class representing the 'wall in front' block with one plug to their left.
 */
public class ModelPredicateBlock extends ModelBlock{
    private ModelBlock leftPlug;
    private PredicateType predicate;

    // Constructor
    public ModelPredicateBlock(ProgramLocation pos, PredicateType predicate){
        super(pos);
        this.setLeftPlug(null);
        this.predicate = predicate;
        ArrayList<ConnectionPoint> connectionPoints = new ArrayList<>();
        connectionPoints.add(ConnectionPoint.LEFT_PLUG);
        super.setConnectionPoints(connectionPoints);
    }

    /**
     *
     * @return the predicate block's predicate
     */
    public PredicateType getPredicate(){
        return this.predicate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ModelBlock clone() {
        return new ModelPredicateBlock(this.getPos(), this.getPredicate());
    }

    /**
     * {@inheritDoc}
     */
    public ModelBlock getLeftPlug() {
        return this.leftPlug;
    }

    /**
     * {@inheritDoc}
     */
    public void setLeftPlug(ModelBlock leftPlug) {
        this.leftPlug = leftPlug;
    }
}