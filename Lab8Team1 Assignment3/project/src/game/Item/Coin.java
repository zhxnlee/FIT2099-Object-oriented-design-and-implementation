package game.Item;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropItemAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.Resettable;

import java.util.List;
import java.util.Random;
/**
 * A class that represents the Coin item
 */
public class Coin extends Item implements Resettable {
    private ActionList allowableActions;
    private boolean convertedBack = false;
    Random rand = new Random();


    public int amount;

    /***
     * Constructor.
     *  @param amount is the amount of money
     */
    public Coin(int amount) {
        super("Coin " + Integer.toString(amount), '$', true);
        this.amount = amount;


    }

    /**
     * Inform a carried Item of the passage of time.
     *
     * This method is called once per turn, if the Item is being carried.
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    public void tick(Location currentLocation, Actor actor) {
        if(convertedBack){
            currentLocation.removeItem(this);
        }
    }


    @Override
    public void resetInstance() {
        convertedBack = true;

    }

    public DropItemAction getDropAction(Actor actor) {
        return null;
    }

}
