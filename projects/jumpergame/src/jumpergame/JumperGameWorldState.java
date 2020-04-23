package jumpergame;

import gameworldapi.GameWorldState;

public class JumperGameWorldState implements GameWorldState {
    private final GridLocation playerLocation;

    public JumperGameWorldState(GridLocation playerLocation) {
        this.playerLocation = playerLocation;
    }

    @Override
    public String toString() {
        return "[jumpergame.JumperGameWorldState: " + playerLocation.toString() + "]";
    }

    GridLocation getPlayerLocation(){
        return this.playerLocation;
    }

}
