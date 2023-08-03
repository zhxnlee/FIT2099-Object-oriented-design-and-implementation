package game.Action;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.ResetManager;
import game.Status;
/**
 * Special Action for resetting the game
 */
public class ResetAction extends Action {

    /**
     * Perform the Action.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        if(actor.hasCapability(Status.RESET)){
            ResetManager.getInstance().run();
            actor.removeCapability(Status.RESET);
            return "The game has been reset";

        }
        return "The game cannot be reset";
    }
    /**
     * Returns a descriptive string
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Reset the game";
    }
    @Override
    public String hotkey() {
        return "r";
    }
}
