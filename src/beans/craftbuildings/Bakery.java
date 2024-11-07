package beans.craftbuildings;

import beans.CraftBuilding;
import beans.Item;
import beans.ProductionSlot;
import wrk.ItemWrk;

import java.util.ArrayList;
import java.util.HashMap;

public class Bakery extends CraftBuilding {

    public Bakery() {
        super("Bakery", "bakery", "bk");

        ArrayList<Item> items = new ArrayList<>();

        /*
            Item : Bread
            Recipe : 3x Wheat
            Production time : 30s
         */
        HashMap<Item, Integer> recipe = new HashMap<>();
        recipe.put(ItemWrk.getItemByAlias("wheat"), 3);

        Item i = new Item("bread", "Bread").setRecipe(recipe).setProductionTime(30).setXpGranted(25);
        ItemWrk.addExistingItem(i, this);
        items.add(i);

        /*
            Item : Empty sandwich
            Recipe : 2x Bread
            Production time : 20s
         */
        recipe = new HashMap<>();
        recipe.put(ItemWrk.getItemByAlias("bread"), 2);

        i = new Item("emptysandwich", "Empty sandwich").setRecipe(recipe).setProductionTime(20).setXpGranted(10);
        ItemWrk.addExistingItem(i, this);
        items.add(i);

        setUpgrades(new Upgrade[] {
                new Upgrade(2, new Item[] { items.get(0) }),

                new Upgrade(2, 120, 50, 1, new Item[] {
                        items.get(1)
                })
        });
    }

}
