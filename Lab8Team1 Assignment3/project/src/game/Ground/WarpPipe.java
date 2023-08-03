package game.Ground;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Action.JumpAction;
import game.Enemies.PiranhaPlant;
import game.Status;

public class WarpPipe extends Ground implements JumpableGround {
    private boolean spawnPiranha = false;
    /**
     * Constructor.
     */
    public WarpPipe() {
        super('C');

    }

    @Override
    public void tick(Location location) {

        if (!spawnPiranha) {
            location.addActor(new PiranhaPlant());
            spawnPiranha = true;
        }

    }


    @Override
    public int fallDamage() {
        return 0;
    }

    @Override
    public int successRate() {
        return 100;
    }

    @Override
    public String groundName() {
        return "WarpPipe";
    }

    @Override
    public ActionList allowableActions(Actor otherActor, Location location, String direction) {
        ActionList actions = new ActionList();
        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
        if (otherActor.hasCapability(Status.JUMPABLE)&& !location.containsAnActor()&& !otherActor.hasCapability(Status.INVINCIBLE)){
            actions.add(new JumpAction(location, direction));

        }



        return actions;


    }


}
