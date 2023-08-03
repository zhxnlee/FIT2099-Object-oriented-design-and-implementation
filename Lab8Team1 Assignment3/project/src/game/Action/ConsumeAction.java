package game.Action;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;

import game.Actor.Player;
import game.Item.Bottle;
import game.Item.FireFlower;
import game.Item.PowerStar;
import game.Item.SuperMushroom;
import game.Status;
/**
 * Special Action for consuming items
 */
public class ConsumeAction extends Action {
    private Item item;

    /**
     * Constructor.
     *
     * @param item the item to be consumed
     */
    public ConsumeAction(Item item) {
        this.item = item;
    }

    /**
     * Perform the Action.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Player player = (Player) actor;

        if(item.getClass() == SuperMushroom.class){
            actor.increaseMaxHp(50);
            actor.addCapability(Status.TALL);
            map.locationOf(actor).removeItem(item);

        }
        else if (item.getClass() == PowerStar.class){
            actor.addCapability(Status.INVINCIBLE);
            actor.heal(200);
            map.locationOf(actor).removeItem(item);

        }else if (item.getClass() == Bottle.class && player.getBottle().getArrayList().get(player.getBottle().getArrayList().size()-1) == "Healing Water" ){
            player.increaseMaxHp(50);
            player.getBottle().getArrayList().remove(player.getBottle().getArrayList().size()-1);


        }
        else if (item.getClass() == Bottle.class && player.getBottle().getArrayList().get(player.getBottle().getArrayList().size()-1) == "Power Water"){
            ((Player) actor).increaseDamage(15);
            player.getBottle().getArrayList().remove(player.getBottle().getArrayList().size()-1);

        }
        else if(item.getClass() == FireFlower.class){
            actor.addCapability(Status.FIREFLOWER);
        }


        return null;
    }
    /**
     * Returns a descriptive string
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        Player player = (Player) actor;
        if (item.getClass() == Bottle.class){
            return actor + " drinks " + item + player.getBottle().getArrayList();
        }else{
            return actor + " consumes " + item ;
    }}
}
