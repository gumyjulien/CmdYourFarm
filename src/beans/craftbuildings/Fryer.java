package beans.craftbuildings;

import beans.CraftBuilding;
import beans.Item;
import wrk.ItemWrk;

import java.util.ArrayList;
import java.util.HashMap;

public class Fryer extends CraftBuilding {

    public Fryer() {
        super("Fryer", "fryer", "fr");

        ArrayList<Item> items = new ArrayList<>();

        /*
            Item : French fries
            Recipe : 3x Potato
            Production time : 100s
         */
        HashMap<Item, Integer> recipe = new HashMap<>();
        recipe.put(ItemWrk.getItemByAlias("potato"), 3);

        Item i = new Item("frenchfries", "French fries").setRecipe(recipe)
                .setProductionTime(100).setXpGranted(40);
        ItemWrk.addExistingItem(i, this);
        items.add(i);

        /*
            Item : Sweet potato fries
            Recipe : 3x Sweet potato
            Production time : 150s
         */
        recipe = new HashMap<>();
        recipe.put(ItemWrk.getItemByAlias("sweetpotato"), 3);

        i = new Item("sweetpotatofries", "Sweet potato fries").setRecipe(recipe)
                .setProductionTime(150).setXpGranted(100);
        ItemWrk.addExistingItem(i, this);
        items.add(i);

        setUpgrades(new Upgrade[] {
                new Upgrade(3, 400, new Item[] { items.get(0), items.get(1) }),
        });
    }

}
