package utilities;

public final class Location{
    private int x;
    private int y;

    public Location(int i, int j) {
        this.x = i;
        this.y = j;
    }
    

    public int getDistance(Location pos){
        return (int)Math.sqrt(Math.pow(this.getX()-pos.getX(),2)+Math.pow(this.getY()-pos.getY(),2));
    }

    public Location add(Location pos){
        return new Location(this.getX() + pos.getX(), this.getY() + pos.getY());
    }

    public Location add(int i, int j){
        return new Location(this.getX() + i, this.getY() + j);
    }

	public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }

    
}