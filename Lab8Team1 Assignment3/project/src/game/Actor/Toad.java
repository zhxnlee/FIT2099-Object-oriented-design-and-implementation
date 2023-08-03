package game.Actor;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Action.BuyAction;
import game.Action.SpeakAction;
import game.Item.PowerStar;
import game.Item.SuperMushroom;
import game.Item.Wrench;

/**
 * A class that represents the shop keeper
 */
public class Toad extends Actor {

    /**
     * Figure out what to do next.
     * @see Actor#playTurn(ActionList, Action, GameMap, Display)
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

    /**
     * Constructor.
     *
     */
    public Toad(){
        super("Toad", 'O',2000);
    }

    /**
     * Returns a new collection of the Actions that the otherActor can do to the current Actor.
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return A collection of Actions.
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
        actions.add(new BuyAction(otherActor,direction, new PowerStar()));
        actions.add(new BuyAction(otherActor,direction, new SuperMushroom()));
        actions.add(new BuyAction(otherActor,direction, new Wrench(50,"hit")));
        actions.add(new SpeakAction(this,direction));
        return actions;
    }




}
