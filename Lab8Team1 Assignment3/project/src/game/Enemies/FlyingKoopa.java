package game.Enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Behaviour.Behaviour;
import game.Behaviour.WanderBehaviour;
import game.Item.SuperMushroom;
import game.Status;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
/**
 * A flying turtle man
 */
public class FlyingKoopa extends Enemies{


    private String dial1 = "Never gonna make you cry!";
    private String dial2 = "Koopi koopi koopii~!";
    private String dial3 = "Pam pam pam!";
    private Random rand = new Random();
    private int ticks = 0;
    private final Map<Integer, Behaviour> behaviours = new HashMap<>(); // priority, behaviour


    /**
     * Constructor.
     */
    public FlyingKoopa() {

        super("Flying Koopa", 'F', 150, 30, 50, "punch");
        this.behaviours.put(10, new WanderBehaviour());
        this.registerInstance();
        this.addItemToInventory(new SuperMushroom());
        this.addCapability(Status.TALL);
    }
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        ticks++;
        if (ticks%2==0) {
            dialogue();
        }

        if (this.isConscious() == false){
            setDisplayChar('D');
            addCapability(Status.DORMANT);
        }
        for(game.Behaviour.Behaviour Behaviour : behaviours.values()) {
            Action action = Behaviour.getAction(this, map);
            if (action != null)
                return action;
        }

        return new DoNothingAction();

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
