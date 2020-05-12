package model;

import gameworldapi.GameWorldState;
import model.blocks.ModelBlock;

/**
 * Class which keeps a ProgramRunner game state
 *
 * @author bert_dvl
 */
public class ProgramState {
    private final ModelBlock block;
    private final GameWorldState gameState;

    public ProgramState(ModelBlock block, GameWorldState gameState){
        this.block = block;
        this.gameState = gameState;
    }

    public ModelBlock getBlock(){
        return this.block;
    }

    public GameWorldState getGameState(){
        return this.gameState;
    }

}
