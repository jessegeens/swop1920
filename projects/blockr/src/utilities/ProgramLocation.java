package utilities;

import gameworldapi.*;

/**
 * Representation of location in the grid, not expressed in pixels but in grid tiles.
 */
public final class ProgramLocation implements Location {

    private final int x;
    private final int y;

    public ProgramLocation(int i, int j) {
        this.x = i;
        this.y = j;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public int getDistance(Location pos){
        return (int)Math.sqrt(Math.pow(this.getX()-pos.getX(),2)+Math.pow(this.getY()-pos.getY(),2));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProgramLocation add(Location pos){
        return new ProgramLocation(this.getX() + pos.getX(), this.getY() + pos.getY());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProgramLocation add(int i, int j){
        return new ProgramLocation(this.getX() + i, this.getY() + j);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getX() {
        return this.x;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getY() {
        return this.y;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o){
        if (!(o instanceof Location)) return false;
        if (((Location) o).getX() == this.getX() && ((Location) o).getY() == this.getY()) return true;
        else return false;
    }
}
