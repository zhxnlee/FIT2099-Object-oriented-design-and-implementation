package game.Action;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Actor.PrincessPeach;
import game.Actor.Toad;

/**
 * Special Action for speaking to actors
 */
public class SpeakAction extends Action {
    /**
     * The Actor to speak to
     */
    protected Actor target;

    /**
     * The direction of actor.
     */
    protected String direction;


    /**
     * Perform the Action.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Monologue monologue = new Monologue();
        return monologue.dialogueAction(actor);
    }

    /**
     * Returns a descriptive string
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " talks with "  + target.toString() ;

    }
    public SpeakAction(Actor target, String direction) {
        this.target = target;
        this.direction = direction;

    }
}
