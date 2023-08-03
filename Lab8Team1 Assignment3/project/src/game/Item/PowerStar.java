package game.Item;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropItemAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.Location;
import game.Action.ConsumeAction;

/**
 * A class that represents the PowerStar item
 */
public class PowerStar extends Item {

    private int age = 0;

    /**
     * Constructor.
     */
    public PowerStar() {
        super("PowerStar", '*', false);
        this.age = 0;
        addAction(new ConsumeAction(this));
    }

    public ActionList allowableActions(Actor otherActor, Location location, String direction) {
        ActionList actions = new ActionList();
        actions.add(new ConsumeAction(this));
        return actions;
    }
    /**
     * Inform a carried Item of the passage of time.
     *
     * This method is called once per turn, if the Item is being carried.
     * @param currentLocation The location of the actor carrying this Item.
     *
     */
    @Override
    public void tick(Location currentLocation) {
        this.setAge(getAge()+1);
        System.out.println("Location " + currentLocation.x() + " " + currentLocation.y());
        if (this.age>10 ){
            currentLocation.removeItem(this);

        }
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    @Override
    public void tick(Location currentLocation, Actor actor){
        this.setAge(getAge()+1);
        if(getAge() > 10){
            actor.removeItemFromInventory(this);
        }
    }

}
