package game.Actor;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Action.BuyAction;
import game.Action.SpeakAction;
import game.Item.PowerStar;
import game.Item.SuperMushroom;
import game.Item.Wrench;

import java.util.Random;

/**
 * A class that represents the shop keeper
 */
public class PrincessPeach extends Actor {

    private String dial1 = "Dear Mario, I'll be waiting for you...";
    private String dial2 = "Never gonna give you up!";
    private String dial3 = "Release me, or I will kick you!";
    private int ticks=0;
    /**
     * Figure out what to do next.
     * @see Actor#playTurn(ActionList, Action, GameMap, Display)
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        ticks++;
        if (ticks%2==0) {
            dialogue();
        }
        return new DoNothingAction();
    }

    /**
     * Constructor.
     *
     */
    public PrincessPeach(){
        super("PrincessPeach", 'O',2000);
    }

    /**
     * Returns a new collection of the Actions that the otherActor can do to the current Actor.
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return A collection of Actions.
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
        actions.add(new SpeakAction(this,direction));
        return actions;
    }

    public void dialogue(){
        int selection;
        Random rand = new Random();
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
