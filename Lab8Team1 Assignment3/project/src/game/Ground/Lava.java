package game.Ground;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;

public class Lava extends Ground {

    /**
     * Constructor.
     */
    public Lava() {
        super('L');
    }

    public void tick(Location location) {
        if (location.containsAnActor() && location.getActor().hasCapability(Status.JUMPABLE)){
            location.getActor().hurt(15);
        }
    }

    public boolean canActorEnter(Actor actor){
        if (actor.hasCapability(Status.JUMPABLE)){
            return true;
        }
        else{
            return false;
        }}
}
