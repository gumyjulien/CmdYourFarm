package beans.craftbuildings;

import beans.CraftBuilding;
import beans.Item;
import wrk.ItemWrk;

import java.util.ArrayList;
import java.util.HashMap;

public class FishingSpot extends CraftBuilding {

    public FishingSpot() {
        super("Fishing spot", "fishingspot", "fs");

        ArrayList<Item> items = new ArrayList<>();

        /*
            Item : Fish fillet
            Production time : 100s
         */
        HashMap<Item, Integer> recipe = new HashMap<>();

        Item i = new Item("fishfillet", "Fish fillet").setRecipe(recipe)
                .setProductionTime(100).setXpGranted(25);
        ItemWrk.addExistingItem(i, this);
        items.add(i);

        /*
            Item : Salmon
            Production time : 300s
         */

        i = new Item("salmon", "Salmon").setRecipe(recipe)
                .setProductionTime(300).setXpGranted(100);
        ItemWrk.addExistingItem(i, this);
        items.add(i);

        setUpgrades(new Upgrade[] {
                new Upgrade(3, 1000, new Item[] { items.get(0), items.get(1) })
        });
    }

}
