package game.Action;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Ground.Dirt;
import game.Ground.JumpableGround;
import game.Status;

import java.util.Random;
/**
 * Special Action for jumping onto grounds
 */
public class JumpAction extends Action {

    /**
     * The Ground that is to be jumped onto
     */
    protected Location moveToLocation;

    /**
     * The direction of the ground to jump onto.
     */
    protected String direction;

    /**
     * Random number generator
     */
    protected Random rand = new Random();

    /**
     * Constructor.
     *
     * @param jumpToLocation the location to jump onto
     * @param direction the direction of the jump ground
     */
    public JumpAction(Location jumpToLocation, String direction) {
        this.moveToLocation = jumpToLocation;
        this.direction = direction;
    }

    /**
     * Perform the Action.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        JumpableGround jumpableGround = (JumpableGround) moveToLocation.getGround();

        if (actor.hasCapability(Status.TALL)){
            map.moveActor(actor, moveToLocation);
            return actor + " succeeds to jump onto " + jumpableGround.groundName() + ".";
        }
        if (!(rand.nextInt(100) <= jumpableGround.successRate()) ) {
            actor.hurt(jumpableGround.fallDamage());
            return actor + " fails to jump onto " + jumpableGround.groundName() + " and receives " + Integer.toString(jumpableGround.fallDamage()) + " fall damage.";
        }
        else{
            map.moveActor(actor, moveToLocation);
            return actor + " succeeds to jump onto " + jumpableGround.groundName() + ".";
        }
    }
    /**
     * Returns a descriptive string
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {


        return actor + " jumps onto " + ((JumpableGround) moveToLocation.getGround()).groundName() + " at " + direction;
    }
}
