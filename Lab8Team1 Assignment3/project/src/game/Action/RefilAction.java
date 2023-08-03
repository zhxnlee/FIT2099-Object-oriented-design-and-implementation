package game.Action;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Actor.Player;
import game.Ground.Fountain;
import game.Ground.HealthFountain;
import game.Ground.JumpableGround;
import game.Ground.PowerFountain;

public class RefilAction extends Action {
    protected Location location;
    public RefilAction(Location location) {
        /**
         * Constructor.
         *
         * @param location of where the actor is
         */
        this.location = location;
    }


    @Override
    public String execute(Actor actor, GameMap map) {

        if(map.locationOf(actor).getGround().getClass() == HealthFountain.class){
            HealthFountain healthFountain = (HealthFountain)  map.locationOf(actor).getGround();
            Player player = (Player) actor;
            if (healthFountain.getCapacity()>0){
                ((Player) actor).getBottle().fillBottle(1);
                healthFountain.setCapacity(healthFountain.getCapacity()-1);

            }



            //System.out.println(player.getBottle().isHealth());
            return actor + " has refilled its Bottle with the water from the Health Fountain!";
        }else if(map.locationOf(actor).getGround().getClass() == PowerFountain.class ){
            PowerFountain powerFountain = (PowerFountain)  map.locationOf(actor).getGround();
            Player player = (Player) actor;
            if(powerFountain.getCapacity()>0){
                ((Player) actor).getBottle().fillBottle(2);
                powerFountain.setCapacity(powerFountain.getCapacity()-1);
            }


        }
        return null;
    }

    @Override
    public String menuDescription(Actor actor) {

        return actor + " refils Bottle with " + ((Fountain) location.getGround()).groundName();
    }
}
