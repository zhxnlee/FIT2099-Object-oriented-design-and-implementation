package game.Behaviour;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.NumberRange;
import game.Action.AttackAction;
import game.Status;

public class AttackBehaviour implements Behaviour {
    private Actor target;
    private String direction;


    /**
     * Constructor.
     *
     * @param target the Actor to attack
     * @param direction direction to attack
     */
    public AttackBehaviour(Actor target,String direction) {
        this.target = target;
        this.direction = direction;
    }

    public String execute(Actor actor, GameMap map) {
        target.hurt(actor.getWeapon().damage());
        return actor + actor.getWeapon().verb() + target + "." + System.lineSeparator();
    }


    // TODO: develop and use it to attack the player automatically.
    @Override
    public Action getAction(Actor actor, GameMap map) {

        Location here = map.locationOf(actor);
        Location there = map.locationOf(target);

        NumberRange xs, ys;
        if (here.x() == there.x() || here.y() == there.y()) {
            xs = new NumberRange(Math.min(here.x(), there.x()), Math.abs(here.x() - there.x()) + 1);
            ys = new NumberRange(Math.min(here.y(), there.y()), Math.abs(here.y() - there.y()) + 1);

            for (int x : xs) {
                for (int y : ys) {
                    if(map.at(x, y).getGround().blocksThrownObjects())
                        return null;
                }
            }

            if(Math.abs(here.x() - there.x()) == 1 || Math.abs(here.y() - there.y()) == 1 && !actor.hasCapability(Status.DORMANT)){

                return new AttackAction(target, direction);
            }

        }
        return null;

    }
}
