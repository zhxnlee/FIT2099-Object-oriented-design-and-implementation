package game.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.Actor.PrincessPeach;
import game.Item.Key;
import game.Item.PowerStar;
import game.Item.Wrench;

import java.util.List;
import java.util.Random;

/**
 * A class that figures the Monologue that the Toad can speak to the Player
 *
 *
 */
public class Monologue {
    private static String princess = "Congratulations! You have completed the game!";
    private static String dial1 = "You might need a wrench to smash Koopa's hard shells.";
    private static String dial2 = "You better get back to finding the Power Stars.";
    private static String dial3 = "The Princess is depending on you! You are our only hope";
    private static String dial4 = "Being imprisoned in these walls can drive a fungus crazy :(";



    public String dialogueAction(Actor actor){
        Random rand = new Random();
        List<Item> collection = actor.getInventory();
        boolean hasWrench = false;
        boolean hasPowerStar = false;
        boolean hasKey = false;
        int selection;
       for (int i = 0; i < collection.size(); i++){
           if (collection.get(i).getClass() == Wrench.class ){
               hasWrench = true;
           }
           else if (collection.get(i).getClass() == PowerStar.class){
               hasPowerStar = true;
           }
           else if (collection.get(i).getClass() == Key.class){
               hasKey = true;
           }
       }

       if (hasKey){
           return princess;
       }

        while (true) {
            selection = rand.nextInt(4) + 1;
            switch (selection) {

                case 1:
                    if (!hasWrench)
                        return dial1;
                    break;
                case 2:
                    if (!hasPowerStar)
                        return dial2;
                    break;
                case 3:
                    return dial3;
                case 4:
                    return dial4;
            }
        }
    }
}
