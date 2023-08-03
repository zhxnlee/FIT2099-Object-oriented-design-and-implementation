package game.Enemies;


import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.Action.AttackAction;
import game.Behaviour.AttackBehaviour;
import game.Behaviour.Behaviour;
import game.Behaviour.FollowBehaviour;
import game.Resettable;
import game.Status;
import game.Behaviour.WanderBehaviour;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * A little fungus guy.
 */
public class Goomba extends Enemies {
	private String dial1 = "Mugga mugga!";
	private String dial2 = "Ugha ugha... (Never gonna run around and desert you...)";
	private String dial3 = "Ooga-Chaka Ooga-Ooga!";
	private Random rand = new Random();

	private int ticks = 0;
	private final Map<Integer, Behaviour> behaviours = new HashMap<>(); // priority, behaviour

	/**
	 * Constructor.
	 */
	public Goomba() {
		super("Goomba", 'g', 20,10,50,"kick");
		this.behaviours.put(10, new WanderBehaviour());

		this.registerInstance();


	}

	/**
	 * At the moment, we only make it can be attacked by Player.
	 * You can do something else with this method.
	 * @param otherActor the Actor that might perform an action.
	 * @param direction  String representing the direction of the other Actor
	 * @param map        current GameMap
	 * @return list of actions
	 * @see Status#HOSTILE_TO_ENEMY
	 */


	/**
	 * Figure out what to do next.
	 * @see Actor#playTurn(ActionList, Action, GameMap, Display)
	 */

	/**
	 * the method of attack
	 */
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		if (!this.isConscious()) {
			// remove actor
			System.out.println(this.toString() + " has been killed");
			map.removeActor(this);

		}
		ticks++;
		if (ticks%2==0) {
			dialogue();
		}
		return new DoNothingAction();
	}


	public void tick(Location location){
		if (rand.nextInt(100)<10){
			this.hurt(20);
		}
	}

	public void dialogue(){
		int selection;
		selection = rand.nextInt(3) + 1;
		switch (selection) {
			case 1:
				System.out.println(this + ": \"" + dial1 + "\"");
				break;
			case 2:
				System.out.println(this + ": \"" + dial2 + "\"");
				break;
			case 3:
				System.out.println(this + ": \"" + dial3 + "\"");
				break;
		}
	}


}
