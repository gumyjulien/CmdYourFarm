package beans.craftbuildings;

import beans.CraftBuilding;
import beans.Item;
import wrk.ItemWrk;

import java.util.ArrayList;
import java.util.HashMap;

public class Confectionery extends CraftBuilding {

    public Confectionery() {
        super("Confectionery", "confectionery", "cf");

        ArrayList<Item> items = new ArrayList<>();

        /*
            Item : Brown sugar
            Recipe : 2x Sugar cane
            Production time : 60s
         */
        HashMap<Item, Integer> recipe = new HashMap<>();
        recipe.put(ItemWrk.getItemByAlias("sugarcane"), 2);

        Item i = new Item("brownsugar", "Brown sugar").setRecipe(recipe)
                .setProductionTime(60).setXpGranted(20);
        ItemWrk.addExistingItem(i, this);
        items.add(i);

        /*
            Item : Sugar
            Recipe : 3x Sugar cane
            Production time : 60s
         */
        recipe = new HashMap<>();
        recipe.put(ItemWrk.getItemByAlias("sugarcane"), 3);

        i = new Item("sugar", "Sugar").setRecipe(recipe)
                .setProductionTime(60).setXpGranted(20);
        ItemWrk.addExistingItem(i, this);
        items.add(i);

        /*
            Item : Chocolate
            Recipe : 3x Cocoa
            Production time : 90s
         */
        recipe = new HashMap<>();
        recipe.put(ItemWrk.getItemByAlias("cocoa"), 3);

        i = new Item("chocolate", "Chocolate").setRecipe(recipe)
                .setProductionTime(90).setXpGranted(55);
        ItemWrk.addExistingItem(i, this);
        items.add(i);


        setUpgrades(new Upgrade[] {
                new Upgrade(3, 250, new Item[] { items.get(0), items.get(1) }),

                new Upgrade(11, 250, 200, 2, new Item[] {
                        items.get(2)
                })
        });
    }

}
