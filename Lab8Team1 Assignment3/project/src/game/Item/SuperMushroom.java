package game.Item;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.Action.ConsumeAction;

/**
 * A class that represents the SuperMushroom item
 */
public class SuperMushroom extends Item{
    private int cost = 400;

    public ActionList allowableActions(Actor otherActor, Location location, String direction) {
        ActionList actions = new ActionList();
        actions.add(new ConsumeAction(this));
        return actions;

    }

    /**
     * Constructor.
     */
    public SuperMushroom() {

        super("SuperMushroom", '^', false);
        addAction(new ConsumeAction(this));

    }


}

