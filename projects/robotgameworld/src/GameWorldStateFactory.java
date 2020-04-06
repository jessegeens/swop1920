public class GameWorldStateFactory {

    private static GameWorldStateFactory instance;

    GameWorldStateFactory() {}

    public static GameWorldStateFactory getInstance(){
        if(instance == null)
            instance = new GameWorldStateFactory();
        return instance;
    }

    /**
     * TODO implement
     *
     * @param old
     * @param action
     * @author Jesse Geens
     * @return
     */
    GameWorldState createNew(GameWorldState old, Action action){
        switch (action){

        }
        return null;
    }

}
