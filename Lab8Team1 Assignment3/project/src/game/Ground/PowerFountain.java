package game.Ground;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Action.RefilAction;
import game.Actor.Player;
import game.Enemies.Enemies;

public class PowerFountain extends Ground implements Fountain{

    private int capacity;
    private boolean finished;
    private int age;
    public PowerFountain() {

        super('A');
        this.capacity = 10;
        this.finished = false;
        this.age = 6;
    }
    @Override
    public boolean canActorEnter(Actor actor) {
        return true;
    }

    /**
     * Returns an empty Action list.
     *
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return a new, empty collection of Actions
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();
        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
        if(location.containsAnActor()){
            actions.add(new RefilAction(location));
        }
        return actions;


    }
    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String groundName() {
        return "Power Fountain (" + this.getCapacity() + "/10)";
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    @Override
    public void tick(Location location) {
        if (this.getCapacity()==0){
            this.finished = true;
            System.out.println("The Power Fountain has been drained!");
        }
        if (this.finished){
            age --;
        }
        if (age ==0){
            this.setCapacity(10);
            this.setAge(6);
            this.setFinished(false);
            System.out.println("The Power Fountain has been replenished!");
        }

        if (location.containsAnActor() && location.getActor().getClass() != Player.class){
            Enemies enemy = (Enemies) location.getActor();
            enemy.increaseDamage();
        }
    }
}
