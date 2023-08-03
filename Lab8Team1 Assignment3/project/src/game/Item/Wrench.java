package game.Item;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.weapons.Weapon;
/**
 * A class that represents the Wrench item/weapon
 */
public class Wrench extends Item implements Weapon  {

    private final int damage;
    private final String verb;
    private final int hitRate;
    private final int cost = 200;
    /***
     * Constructor.
     *
     * @param damage the damage of the Wrench
     * @param verb how it attacks
     *
     */
    public Wrench(int damage, String verb) {
        super("Wrench", 'l', false);
        this.damage = 50;
        this.verb = verb;
        this.hitRate = 80; // 50% chance to hit
    }

    /***
     * Get the damage of Wrench
     *
     */
    @Override
    public int damage() {
        return damage;
    }

    /***
     * Get how the Wrench attacks
     *
     */
    @Override
    public String verb() {
        return verb;
    }


    /***
     * The hitrate of Wrench
     *
     */
    @Override
    public int chanceToHit() {
        return hitRate;
    }

    /***
     * The cost of Wrench
     *
     */
    public int getCost() {
        return cost;
    }
}
