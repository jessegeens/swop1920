package gameworldapi;
public interface Location {

        /**
         * Method calculating the Euclidian distance of two positions.
         * @param pos the other position
         * @return the distance of this position and another position.
         * @author Oberon Swings
         */
        public int getDistance(Location pos);

        /**
         * A vectorsum method that adds to Location objects.
         * @param pos the other position to add.
         * @return the new position.
         * @author Oberon Swings
         */
        public Location add(Location pos);

        /**
         * Sum method on the components of the Location object.
         * @param i the component to be added to the x part of our location.
         * @param j the component to be added to the y part of our location.
         * @return the sum of the location with i and j.
         * @author Oberon Swings
         */
        public Location add(int i, int j);

        /**
         *
         * @return the x coordinate.
         */
        public int getX();

        /**
         *
         * @return the y coordinate.
         */
        public int getY();

}
