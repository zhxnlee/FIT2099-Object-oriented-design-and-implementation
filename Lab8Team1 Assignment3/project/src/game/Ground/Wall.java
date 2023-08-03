package game.Ground;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Action.JumpAction;
import game.Item.Coin;
import game.Status;

import java.util.List;
import java.util.Random;
/**
 * A class that represents Walls
 */
public class Wall extends Ground implements JumpableGround {
	private Random rand = new Random();
	private final int successRate;
	private final int fallDamage;

	/**
	 * Constructor.
	 */
	public Wall() {
		super('#');
		this.successRate = 80;
		this.fallDamage = 20;
	}

	@Override
	public boolean canActorEnter(Actor actor){
		if (actor.hasCapability(Status.INVINCIBLE)){
			return true;
		}
		else{
		return false;
	}}

	
	@Override
	public boolean blocksThrownObjects() {
		return true;
	}

	@Override
	public int fallDamage() {
		return 20;
	}

	@Override
	public int successRate() {
		return 80;
	}

	public String groundName() {
		return "Wall";
	}

	@Override
	public void tick(Location location){
		if(location.containsAnActor() && location.getActor().hasCapability(Status.INVINCIBLE)){
			location.setGround(new Dirt());
			location.addItem(new Coin(5));
			List<Item> itemsOn= location.getItems();

		}

	}
	/**
	 * Returns an empty Action list.
	 *
	 * @param otherActor the Actor acting
	 * @param location the current Location
	 * @param direction the direction of the Ground from the Actor
	 * @return a new, empty collection of Actions
	 */
	@Override
	public ActionList allowableActions(Actor otherActor, Location location, String direction) {
		ActionList actions = new ActionList();
		// it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
		if (otherActor.hasCapability(Status.JUMPABLE)&& !location.containsAnActor()&& !otherActor.hasCapability(Status.INVINCIBLE)){
			actions.add(new JumpAction(location, direction));
		}
		return actions;


	}
}
