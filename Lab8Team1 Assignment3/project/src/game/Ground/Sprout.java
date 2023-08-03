package game.Ground;


import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Action.JumpAction;
import game.Enemies.Goomba;
import game.Item.Coin;
import game.Item.FireFlower;
import game.Resettable;
import game.Status;

import java.util.Random;
/**
 * A class that represents Sprout
 */
public class Sprout extends Tree {


    /**
     * Constructor.
     */
    public Sprout() {
        super('+',70,30,"Sprout");


    }
    /**
     * Sprout can also experience the joy of time.
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location){
        if(this.isConvertedBack()){
            location.setGround(new Dirt());
        }
        this.setAge(this.getAge()+1);
        if (this.getAge() ==10){
            location.setGround(new Sapling());
            if(this.getRand().nextInt(100) < 50){
                location.addItem(new FireFlower());
            }

        }
        if (this.getRand().nextInt(10) < 1 && !location.containsAnActor()){
            location.addActor(new Goomba());

        }
        if(location.containsAnActor() && location.getActor().hasCapability(Status.INVINCIBLE)){
            location.setGround(new Dirt());
            location.addItem(new Coin(5));
        }
    }}






