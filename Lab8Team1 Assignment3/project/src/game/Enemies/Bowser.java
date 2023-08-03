package game.Enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.DropItemAction;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Action.AttackAction;
import game.Behaviour.Behaviour;
import game.Behaviour.WanderBehaviour;
import game.Item.Fire;
import game.Item.Key;
import game.Resettable;
import game.Status;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
/**
 * A big turtle man
 */
public class Bowser extends Enemies{
    private String dial1 = "What was that sound? Oh, just a fire.";
    private String dial2 = "Princess Peach! You are formally invited... to the creation of my new kingdom!";
    private String dial3 = "Never gonna let you down!";
    private String dial4 = "Wrrrrrrrrrrrrrrrryyyyyyyyyyyyyy!!!!";
    private int ticks=0;
    private Random rand = new Random();
    private final Map<Integer, Behaviour> behaviours = new HashMap<>(); // priority, behaviour


    /**
     * Constructor.
     */
    public Bowser() {
        super("Bowser", 'B', 500,80,50,"punch");
        this.addItemToInventory(new Key());
        this.behaviours.put(10, new WanderBehaviour());

    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        ticks++;
        if (ticks%2==0) {
            dialogue();
        }
        if(!this.isConscious()){
            map.locationOf(this).addItem(new Key());
        }
        return new DoNothingAction();
    }

    public void dialogue(){
        int selection;
        selection = rand.nextInt(4) + 1;
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
            case 4:
                System.out.println(this + ": \"" + dial4 + "\"");
                break;
        }
    }
}

