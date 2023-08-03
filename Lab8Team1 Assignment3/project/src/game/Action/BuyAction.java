package game.Action;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Item.PowerStar;
import game.Item.SuperMushroom;
import game.Actor.Player;
import game.Item.Wrench;

import java.util.Random;
/**
 * Special Action for buying items
 */
public class BuyAction extends Action {
    /**
     * The Actor that buys from toad
     */
    protected Actor target;

    /**
     * The direction of the actor
     */
    protected String direction;

    /**
     * Random number generator
     */
    protected Random rand = new Random();
    /**
     * The item that the actor wishes to buy
     */
    protected Item buyItem;
    /**
     * Constructor.
     *
     * @param target The Actor that buys from toad
     * @param direction The direction of the actor
     * @param buyItem The item that the actor wishes to buy

     */

    public BuyAction(Actor target, String direction, Item buyItem) {
        this.target = target;
        this.direction = direction;
        this.buyItem = buyItem;
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
  
        if(buyItem.getClass() == SuperMushroom.class){
            if(player.getWallet().getMoney() >= 400){
                player.getWallet().withdraw(400);
                actor.addItemToInventory(new SuperMushroom());
            }
        }else if(buyItem.getClass() == PowerStar.class){
            if(player.getWallet().getMoney() >= 600){
                player.getWallet().withdraw(600);
                actor.addItemToInventory(new PowerStar());
            }
        }else if(buyItem.getClass() == Wrench.class){
            if(player.getWallet().getMoney() >= 200){
                player.getWallet().withdraw(200);
                actor.addItemToInventory(new Wrench(50, "hit"));
            }
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
        String cost = "";
        if(buyItem.getClass() == SuperMushroom.class){
            cost = "$400";
        }else if (buyItem.getClass() == PowerStar.class){
            cost = "$600";
        }
        else{
            cost = "$200";

        }
        return actor + " buys " + buyItem.toString() + " (" + cost + ")";
    }
}
