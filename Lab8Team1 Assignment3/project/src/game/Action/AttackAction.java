package game.Action;

import java.util.Random;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.Item.Fire;
import game.Status;
import game.Item.Wrench;

/**
 * Special Action for attacking other Actors.
 */
public class  AttackAction extends Action {

	/**
	 * The Actor that is to be attacked
	 */
	protected Actor target;

	/**
	 * The direction of incoming attack.
	 */
	protected String direction;

	/**
	 * Random number generator
	 */
	protected Random rand = new Random();

	/**
	 * Constructor.
	 * 
	 * @param target the Actor to attack
	 */
	public AttackAction(Actor target, String direction) {
		this.target = target;
		this.direction = direction;
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
		Weapon weapon = actor.getWeapon();
		if(actor.hasCapability(Status.FIREFLOWER)){
			map.locationOf(target).addItem(new Fire());
		}

		if ((rand.nextInt(100) <= weapon.chanceToHit()) && target.hasCapability(Status.DORMANT) && weapon.getClass() == Wrench.class)
		{
			int wrenchDamage = weapon.damage();
			target.hurt(weapon.damage());
			return actor + " " + weapon.verb() + " " + target + " for " + wrenchDamage + " damage.";
		}else if(target.hasCapability(Status.DORMANT) && weapon.getClass() == Wrench.class){
			map.removeActor(target);
			//map.locationOf(target).addItem(new SuperMushroom());
			return actor + " misses " + target + ".";
		}
		if (!(rand.nextInt(100) > weapon.chanceToHit()) ) {
			return actor + " misses " + target + ".";

		}
		if(target.hasCapability(Status.INVINCIBLE)){
			return actor + " " + weapon.verb() + " " + target + " for 0 damage.";
		}
		if(target.hasCapability(Status.TALL) && !(target.hasCapability(Status.INVINCIBLE))){
			target.removeCapability(Status.TALL);
			target.resetMaxHp(100);
		}



		int damage = weapon.damage();


		String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
		target.hurt(damage);

		if(!target.isConscious()){
			ActionList dropActions = new ActionList();
			for (Item item : target.getInventory()){
				dropActions.add(item.getDropAction(actor));

			}
			for (Action drop :dropActions){
				drop.execute(target, map);

			}
			map.removeActor(target);
			result += System.lineSeparator() + target + " is killed!";


		}

		if (actor.hasCapability(Status.FIREFLOWER)){
			result = actor + " attacks " + target.toString() + " at " + direction + " with Fire!";
		}

		return result;
	}

	/**
	 * Returns a descriptive string
	 * @param actor The actor performing the action.
	 * @return the text we put on the menu
	 */
	@Override
	public String menuDescription(Actor actor) {
		if(target.hasCapability(Status.DORMANT)){
			return actor + "attacks Koopa's shell at " + direction;
		}
		else{

			return actor + " attacks " + target + " at " + direction;}
	}
}
