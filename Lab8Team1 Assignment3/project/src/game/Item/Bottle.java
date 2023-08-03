package game.Item;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.Action.ConsumeAction;

import java.util.ArrayList;
import java.util.List;

public class Bottle extends Item {
   private static ArrayList<String> arrayList = new ArrayList<String>();

    /***
     * Constructor.
     *
     */
    public Bottle() {

        super("Bottle", 'b', false);
        addAction(new ConsumeAction(this));}


    public ArrayList<String> getArrayList() {
        return arrayList;
    }
    public void fillBottle(int fill){
        if(fill ==1){
            arrayList.add("Healing Water");

        }else if (fill == 2){
            arrayList.add("Power Water");
        }
    }



    public ActionList allowableActions(Actor otherActor, Location location, String direction) {
        ActionList actions = new ActionList();
        //actions.add(new ConsumeAction(this));
        return actions;

    }


}
