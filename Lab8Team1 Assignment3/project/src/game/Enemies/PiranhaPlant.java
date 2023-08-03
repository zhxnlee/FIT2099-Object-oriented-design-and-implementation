package game.Enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Action.AttackAction;
import game.Actor.Player;
import game.Behaviour.AttackBehaviour;
import game.Behaviour.Behaviour;
import game.Behaviour.FollowBehaviour;
import game.Status;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * The plant from smash
 */
public class PiranhaPlant extends Enemies{
    private String dial1 = "Slsstssthshs~! (Never gonna say goodbye~)";
    private final Map<Integer, Behaviour> behaviours = new HashMap<>(); // priority, behaviour
    private String dial2 = "Ohmnom nom nom nom.";
    private Random rand = new Random();
    private int ticks = 0;

    /**
     * Constructor.
     */
    public PiranhaPlant() {
        super("Piranha Plant", 'Y', 150, 90, 50, "chomps");
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        ticks++;
        if (ticks%2==0) {
            dialogue();
        }
        if(!this.isConscious()){
            map.removeActor(this);
        }

        return new DoNothingAction();


    }

    @Override
    public void resetInstance() {

        this.setHitPoints(getHitPoints()+50);
        this.hurt(getHitPoints());

    }

    public void dialogue(){
        int selection;
        selection = rand.nextInt(2) + 1;
        switch (selection) {
            case 1:
                System.out.println(this + ": \"" + dial1 + "\"");
                break;
            case 2:
                System.out.println(this + ": \"" + dial2 + "\"");
                break;
        }
    }
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY) ) {
            actions.add(new AttackAction(this,direction));
        }

        attackBehaviour = new AttackBehaviour(otherActor,direction);
        this.behaviours.put(0,attackBehaviour);


        return actions;
    }
}
