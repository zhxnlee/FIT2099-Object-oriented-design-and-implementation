package game.Item;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.Action.ConsumeAction;

public class FireFlower extends Item {

    /***
     * Constructor.
     *
     */
    public FireFlower() {
        super("FireFlower", 'f', false);
        addAction(new ConsumeAction(this));
    }

    public ActionList allowableActions(Actor otherActor, Location location, String direction) {
        ActionList actions = new ActionList();
        actions.add(new ConsumeAction(this));
        return actions;

    }
}

