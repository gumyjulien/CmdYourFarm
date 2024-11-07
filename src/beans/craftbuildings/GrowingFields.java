package beans.craftbuildings;

import beans.CraftBuilding;
import beans.Item;
import wrk.ItemWrk;

import java.util.ArrayList;
import java.util.HashMap;

public class GrowingFields extends CraftBuilding {

    public GrowingFields() {
        super("Growing fields", "growingfields", "gf");

        ArrayList<Item> items = new ArrayList<>();

        /*
            Item : Wheat
            Production time : 10s
         */
        HashMap<Item, Integer> recipe = new HashMap<>();

        Item i = new Item("wheat", "Wheat").setRecipe(recipe).setProductionTime(10).setXpGranted(500);
        ItemWrk.addExistingItem(i, this);
        items.add(i);

        /*
            Item : Corn
            Production time : 20s
         */

        i = new Item("corn", "Corn").setRecipe(recipe).setProductionTime(20).setXpGranted(7);
        ItemWrk.addExistingItem(i, this);
        items.add(i);

        setUpgrades(new Upgrade[] {
                new Upgrade(2, new Item[] { items.get(0) }),

                new Upgrade(2, 50, 20, 1, new Item[] {
                        items.get(1)
                })
        });
    }

}
