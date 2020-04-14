package jumpergame;

import gameworldapi.*;

/**
 * Singleton Factory that creates new GameWorldStates
 * @author Jesse Geens
 */
public class GameWorldStateFactory {

    private static GameWorldStateFactory instance;


    private static final GridLocation PLAYER_START_LOCATION = new GridLocation(0, 0);


    //Constructor
    private GameWorldStateFactory(){}

    /**
     * Get the singleton instance
     *
     * @author Jesse Geens
     * @return robotgameworld.GameWorldStateFactory instance
     */
    public static GameWorldStateFactory getInstance(){
        if(instance == null)
            instance = new GameWorldStateFactory();
        return instance;
    }


    public JumperGameWorldState getInitialState() {
        return new JumperGameWorldState(PLAYER_START_LOCATION);
    }

    /**
     *
     * @param old previous gameWorldState
     * @param action action to execute for generating the new state
     * @author Jesse Geens
     * @return {GameWorldState} gameWorldState after the action has been executed
     */
    public JumperGameWorldState createNew(JumperGameWorldState old, Action action){
        switch (action){
            case MOVE_LEFT:
                return new JumperGameWorldState(old.getPlayerLocation().add(-1, 0));
            case MOVE_RIGHT:
                return new JumperGameWorldState(old.getPlayerLocation().add(1, 0));
            case JUMP:
                return new JumperGameWorldState(old.getPlayerLocation().add(0, 2));

        }
        return null;
    }



}

