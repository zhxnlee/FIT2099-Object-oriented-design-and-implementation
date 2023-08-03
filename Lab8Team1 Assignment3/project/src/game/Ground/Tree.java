package game.Ground;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Action.JumpAction;
import game.Resettable;
import game.Status;

import java.util.Random;

public abstract class Tree extends Ground implements JumpableGround, Resettable {
    private int age;
    private final int successRate;

    public String getGroundName() {
        return groundName;
    }

    private final int fallDamage;
    private final String groundName;
    private Random rand = new Random();
    private boolean convertedBack = false;
    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     */
    public Tree(char displayChar, int successRate, int fallDamage, String groundName) {
        super(displayChar);
        this.age = age;
        this.successRate = successRate;
        this.groundName = groundName;
        this.fallDamage = fallDamage;
        this.registerInstance();

    }

    /**
     * To retrieve the ground name
     */
    public String groundName(){
        return groundName;
    }
    /**
     * To retrieve age
     */
    public int getAge() {
        return age;
    }


    public void setAge(int age) {
        this.age = age;
    }

    public int successRate() {
        return successRate;
    }

    public int fallDamage() {
        return fallDamage;
    }

    public Random getRand() {
        return rand;
    }

    public void setRand(Random rand) {
        this.rand = rand;
    }

    public boolean isConvertedBack() {
        return convertedBack;
    }

    public void setConvertedBack(boolean convertedBack) {
        this.convertedBack = convertedBack;
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
    public ActionList allowableActions(Actor otherActor, Location location, String direction) {
        ActionList actions = new ActionList();
        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
        if (otherActor.hasCapability(Status.JUMPABLE)&& !location.containsAnActor()&& !otherActor.hasCapability(Status.INVINCIBLE)){
            actions.add(new JumpAction(location, direction));


        }
        return actions;


    }

    @Override
    public void resetInstance() {

        if(rand.nextInt(100) < 50){
            convertedBack = true;
        }
    }
}
