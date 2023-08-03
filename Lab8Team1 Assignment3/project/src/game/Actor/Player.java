package game.Actor;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Action.AttackAction;
import game.Action.ConsumeAction;
import game.Action.ResetAction;
import game.Action.TeleportAction;
import game.Enemies.PiranhaPlant;
import game.Ground.WarpPipe;
import game.Item.Bottle;
import game.Item.Coin;
import game.Item.Key;
import game.Item.PowerStar;
import game.Resettable;
import game.Status;

/**
 * Class representing the Player.
 */
public class Player extends Actor implements Resettable {
	private int warpPipex;
	private int warpPipey;
	private int invincibilityTimer = 11 ;
	private final Menu menu = new Menu();
	private Wallet wallet;
	private Bottle bottle;
	private int damage;
	private String verb;
	private int fireflowertimer = 21;
	private boolean teleport = false;



	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
		this.addCapability(Status.HOSTILE_TO_ENEMY);

		this.addCapability(Status.JUMPABLE);
		this.addCapability(Status.RESET);
		wallet = new Wallet();
		this.getWallet().setMoney(1600);
		this.registerInstance();
		this.addItemToInventory(new Bottle());
		bottle = new Bottle();
		this.damage = 5;
		this.verb = "punches";

	}

	public int getInvincibilityTimer() {
		return invincibilityTimer;
	}


	/**
	 * Select and return an action to perform on the current turn.
	 *
	 * @param actions    collection of possible Actions for this Actor
	 * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
	 * @param map        the map containing the Actor
	 * @param display    the I/O object to which messages may be written
	 * @return the Action to be performed
	 */
	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		// Handle multi-turn Actions
		System.out.println("Mario's Hitpoints "+ this.printHp());
		System.out.println("Mario at (" + map.locationOf(this).x() + "," + map.locationOf(this).y() + ")");
		System.out.println("Wallet:(" + this.getWallet().getMoney() + "$)");
		System.out.println("Inventory: " + this.getInventory());
		System.out.println("Mario's Damage: " + this.getDamage());
		/*for(int i = 0; i < this.getInventory().size(); i++){

			Item ps = this.getInventory().get(i);

			if (ps.getClass() == PowerStar.class){
				((PowerStar) ps).setAge(((PowerStar) ps).getAge()+1);
				if(((PowerStar) ps).getAge() >= 10){
					this.removeItemFromInventory(ps);
				}
				//ps.togglePortability();
			}
		}*/

		if (lastAction.equals(ResetAction.class)){
			resetInstance();
		}
		if(!this.isConscious()){
			this.addCapability(Status.RESET);
		}

		if (this.invincibilityTimer >0 && this.hasCapability(Status.INVINCIBLE)){
			System.out.println("MARIO IS INVINCIBLE!!(" + invincibilityTimer+ " turns remaining)");
			invincibilityTimer --;
		}

		if (this.fireflowertimer >0 && this.hasCapability(Status.FIREFLOWER)){
			System.out.println("MARIO CONSUMED FIREFLOWER!!(" + (fireflowertimer-1)+ " turns remaining)");
			fireflowertimer --;
		}
		if(this.hasCapability(Status.RESET)){
			actions.add(new ResetAction());
		}



		for(int i = 0; i < this.getInventory().size(); i++){
			Item money = this.getInventory().get(i);

			if (money.getClass() == Coin.class){
				money.togglePortability();
				getWallet().addMoney(Integer.parseInt(getInventory().get(i).toString().split(" ")[1]));

				this.removeItemFromInventory(money);
			}
		}
		if(this.invincibilityTimer ==0 ){
			this.removeCapability(Status.INVINCIBLE);
			invincibilityTimer = 10;
		}
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();

		// return/print the console menu
		return menu.showMenu(this, actions, display);
	}

	public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
		ActionList actions = new ActionList();
		// it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
		if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY) ) {
			actions.add(new AttackAction(this,direction));
		}

		if(map.locationOf(this).getGround().getClass() == WarpPipe.class){
			if(this.teleport) {
				actions.add(new TeleportAction(map.locationOf(this)));
			}
		}

		return actions;
	}



	public void setInvincibilityTimer(int invincibilityTimer) {
		this.invincibilityTimer = invincibilityTimer;
	}
	/**
	 * @return  display character of an instance
	 */
	@Override
	public char getDisplayChar(){
		return this.hasCapability(Status.TALL) ? Character.toUpperCase(super.getDisplayChar()): super.getDisplayChar();
	}
	/**
	 * To change the display of the Player
	 */
	public void setDisplay(char displayChar){
		super.setDisplayChar(displayChar);
	}

	/**
	 * To retrieve the wallet of Player
	 */
	public Wallet getWallet() {
		return wallet;
	}

	@Override
	public void resetInstance() {
		this.resetMaxHp(this.getMaxHp());
		if (this.hasCapability(Status.TALL)) {
			this.removeCapability(Status.TALL);
		}
		if (this.hasCapability(Status.INVINCIBLE)){
			this.removeCapability(Status.INVINCIBLE);
			this.invincibilityTimer = 10;
		}
	}

	/**
	 * To retrieve the bottle with the player
	 */
	public Bottle getBottle() {
		return bottle;
	}

	/**
	 * To set the bottle of the player
	 */
	public void setBottle(Bottle bottle) {
		this.bottle = bottle;
	}

	protected IntrinsicWeapon getIntrinsicWeapon() {

		return new IntrinsicWeapon(this.damage, this.verb);


	}

	public boolean isTeleport() {
		return teleport;
	}

	public void setTeleport(boolean teleport) {
		this.teleport = teleport;
	}

	/**
	 * To retrieve the damage of Player
	 */
	public int getDamage() {
		return damage;
	}

	/**
	 * To set damage of player
	 */
	public void setDamage(int damage) {
		this.damage = damage;
	}

	/**
	 * To increase damage of the player
	 */
	public void increaseDamage(int damage){
		this.damage += damage;
	}

}
