package game.Enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.Action.AttackAction;
import game.Behaviour.AttackBehaviour;
import game.Behaviour.Behaviour;
import game.Behaviour.FollowBehaviour;
import game.Resettable;
import game.Status;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Enemies extends Actor implements Resettable {
    protected AttackBehaviour attackBehaviour;
    private Random rand = new Random();
    private int damage;
    private int hitrate;
    private String verb;
    private int hitPoints;
    protected FollowBehaviour followBehaviour;

    private final Map<Integer, Behaviour> behaviours = new HashMap<>(); // priority, behaviour

    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public Enemies(String name, char displayChar, int hitPoints, int damage, int hitrate, String verb) {
        super(name, displayChar, hitPoints);
        this.hitPoints = hitPoints;
        this.damage = damage;
        this.hitrate = hitrate;
        this.verb = verb;
        this.registerInstance();

    }



    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY) ) {
            actions.add(new AttackAction(this,direction));
        }
        if(attackBehaviour == null && otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            followBehaviour = new FollowBehaviour(otherActor);
            this.behaviours.put(0, followBehaviour);
            attackBehaviour = new AttackBehaviour(otherActor,direction);
            this.behaviours.put(0,attackBehaviour);
        }



        return actions;
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (!this.isConscious()) {
            // remove actor
            map.removeActor(this);
            System.out.println(this.toString() + " has been killed");
        }

        for(game.Behaviour.Behaviour Behaviour : behaviours.values()) {
            Action action = Behaviour.getAction(this, map);
            if (action != null)
                return action;
        }



        return new DoNothingAction();
    }

    protected IntrinsicWeapon getIntrinsicWeapon() {
        if (rand.nextInt(100)< this.hitrate){
            return new IntrinsicWeapon(this.damage, this.verb);
        }
        else{
            return new IntrinsicWeapon(0, "miss");
        }
    }
    public Weapon getWeapon(){
        return getIntrinsicWeapon();
    }

    @Override
    public void resetInstance() {
        this.hitPoints=0;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }
    public void increaseDamage(){
        this.damage +=15;
    }

}
