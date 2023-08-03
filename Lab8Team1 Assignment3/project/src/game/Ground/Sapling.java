package game.Ground;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Action.JumpAction;
import game.Item.Coin;
import game.Item.FireFlower;
import game.Resettable;
import game.Status;

import java.util.Random;
/**
 * A class that represents Sapling trees
 */
public class Sapling extends Tree {


    /**
     * Constructor.
     */
    public Sapling() {
        super('t',80,20,"Sapling");
    }
    /**
     * Sapling can also experience the joy of time.
     * @param location The location of the Ground
     */
    public void tick(Location location){
        if(this.isConvertedBack()){
            location.setGround(new Dirt());
        }
        this.setAge(this.getAge()+1);
        if(this.getAge() == 10){
            location.setGround(new Mature());
            if(this.getRand().nextInt(100) < 50){
                location.addItem(new FireFlower());
            }
            this.addCapability(Status.TALL);
        }
        if (this.getRand().nextInt(10) < 1 && !location.containsAnActor()){
            location.addItem(new Coin(20));
        }
        if(location.containsAnActor() && location.getActor().hasCapability(Status.INVINCIBLE)){
            location.setGround(new Dirt());
            location.addItem(new Coin(5));
        }
    }



}
