package utilities;

/**
 * A class representing the location of some element in our system.
 */
public final class Location{
    private int x;
    private int y;

    public Location(int i, int j) {
        this.x = i;
        this.y = j;
    }
    

    /**
     * Method calculating the Euclidian distance of two positions.
     * @param pos the other position
     * @return the distance of this position and another position.
     */
    public int getDistance(Location pos){
        return (int)Math.sqrt(Math.pow(this.getX()-pos.getX(),2)+Math.pow(this.getY()-pos.getY(),2));
    }

    /**
     * A vectorsum method that adds to Location objects.
     * @param pos the other position to add.
     * @return the new position. 
     */
    public Location add(Location pos){
        return new Location(this.getX() + pos.getX(), this.getY() + pos.getY());
    }

    /**
     * Sum method on the components of the Location object.
     * @param i the component to be added to the x part of our location.
     * @param j the component to be added to the y part of our location.
     * @return the sum of the location with i and j.
     */
    public Location add(int i, int j){
        return new Location(this.getX() + i, this.getY() + j);
    }

    /**
     * 
     * @return the x coordinate.
     */
	public int getX() {
        return this.x;
    }
    
    /**
     * 
     * @return the y coordinate.
     */
    public int getY() {
        return this.y;
    }
}