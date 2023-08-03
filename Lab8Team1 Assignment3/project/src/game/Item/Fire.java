package game.Item;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.Enemies.Enemies;

public class Fire extends Item {
    private int age = 0;

    /***
     * Constructor.
     *

     */
    public Fire() {
        super("Fire", 'v', false);
    }

    @Override
    public void tick(Location currentLocation) {
        System.out.println(currentLocation.containsAnActor());
        age++;
        if(currentLocation.containsAnActor()){
            currentLocation.getActor().hurt(20);
            Enemies enemy = (Enemies) currentLocation.getActor();
            enemy.hurt(20);
            System.out.println(enemy.toString());
            System.out.println(enemy.getHitPoints());
        }
        if(age>=3){
            currentLocation.removeItem(this);

        }
    }
}
