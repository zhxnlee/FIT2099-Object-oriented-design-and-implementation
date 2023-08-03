package game.Action;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.World;
import game.Ground.WarpPipe;

public class TeleportAction extends Action {
    private Location location;
    private boolean teleport = false;
    public TeleportAction(Location location) {
        this.location = location;
    }

    @Override
    public String execute(Actor actor, GameMap map) {

        return null;
    }

    @Override
    public String menuDescription(Actor actor) {
        return null;
    }


}
