import java.awt.*;

/*
 The GameWorld interface offers methods to:
- perform one of the supported Actions. It returns a result indicating successful execution, failure to execute because the action is illegal in the current state, or end of the game due to reaching the goal state.
- evaluate one of the supported Predicates.
- create an (opaque, i.e. non-inspectable) snapshot of the game world state and to restore the game world state to a given snapshot.
- paint the current state of the game world, given a graphics object (either java.awt.Graphics or another graphics API of your own design).

 @author Jesse Geens
 */
public interface GameWorld {

    ActionResult perform(Action action);

    Boolean evaluate(Predicate predicate);

    GameWorldState getSnapshot();

    void restore(GameWorldState gameWorldState);

    void render(Graphics g, GameWorldState gameWorldState);

}
