package gameworldapi;

/**
 * The ActionResult is an enum describing
 * the possible outcomes of performing an
 * action on a GameWorldState. There are
 * four possible ActionResults:
 *  - SUCCESS: this means that the action
 *      was performed successfully and that
 *      the GameWorldState has been updated
 *      to reflect the action. The game
 *      is thus still going on.
 *
 *   - FAILURE: this means that the action
 *       could not be performed for some
 *       reason. The GameWorldState has not
 *       been updated, but the game is still
 *       running.
 *
 *   - GAME_OVER: this means that the action
 *       that was executed has lead to the
 *       end of a game in a negative way,
 *       e.g. the player has died. The next
 *       execution, the gameworld should be
 *       reset.
 *
 *   - GAME_SUCCESS: this means that the game
 *       has ended successfully; the player won
 *       the game. The gameworldstate should be
 *       reset after this.
 * @author Jesse Geens
 */
public enum ActionResult {
    SUCCESS, FAILURE, GAME_OVER, GAME_SUCCESS
}
