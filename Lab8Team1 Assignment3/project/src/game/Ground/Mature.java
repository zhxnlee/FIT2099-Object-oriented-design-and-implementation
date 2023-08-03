package game.Ground;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Action.JumpAction;
import game.Enemies.FlyingKoopa;
import game.Enemies.Koopa;
import game.Item.Coin;
import game.Resettable;
import game.Status;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.*;

/**
 * A class that represents Mature trees
 */
public class Mature extends Tree {


    /**
     * Constructor.
     */
    public Mature() {
        super('T', 70, 30, "Mature");

    }


    /**
     * Mature can also experience the joy of time.
     *
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        if (this.isConvertedBack() == true) {
            location.setGround(new Dirt());
        }
        this.setAge(this.getAge() + 1);
        if (this.getRand().nextInt(100) < 15 && !location.containsAnActor()) {
            if(this.getRand().nextInt(100)< 50){
                location.addActor(new Koopa());
            }
            else{
                location.addActor(new FlyingKoopa());
            }
        }
        if (this.getRand().nextInt(100) < 20) {
            location.setGround(new Dirt());
        }

        if (this.getAge() %5 == 0) {
            growSurrounding(location, 0);
        }

        if (location.containsAnActor() && location.getActor().hasCapability(Status.INVINCIBLE)) {
            location.setGround(new Dirt());
            location.addItem(new Coin(5));
        }
    }
    public void plant(Location location, int x, int y ,int counter){
        if (location.map().getXRange().contains(x) && location.map().getYRange().contains(y) && location.map().at(x, y).getGround().hasCapability(Status.FERTILE)) {
            location.map().at(x, y).setGround(new Sprout());
            counter = 0;
        } else if (counter >= 7) {
            return;
        } else {

            counter += 1;
            growSurrounding(location, counter);

        }
    }

    public void growSurrounding(Location location, int counter) {
        Random rand = new Random();
        int switchValue = rand.nextInt(8);
        int x;
        int y;
        switch (switchValue){
        case 0:
            x = location.x() - 1;
            y = location.y() - 1;
            plant(location, x, y, counter);
            break;
        case 1:
            x = location.x();
            y = location.y() - 1;
            plant(location, x, y, counter);
            break;
        case 2:
            x = location.x() + 1;
            y = location.y() - 1;
            plant(location, x, y, counter);
            break;

        case 3:
            x = location.x() - 1;
            y = location.y();
            plant(location, x, y, counter);
            break;

        case 4:
            x = location.x() + 1;
            y = location.y();
            plant(location, x, y, counter);
            break;

        case 5:
            x = location.x() - 1;
            y = location.y() + 1;
            plant(location, x, y, counter);
            break;

        case 6:
            x = location.x();
            y = location.y() + 1;
            plant(location, x, y, counter);
            break;

        case 7:
            x = location.x() + 1;
            y = location.y() + 1;
            plant(location, x, y, counter);
            break;

    }


}}
