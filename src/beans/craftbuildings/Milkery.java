package beans.craftbuildings;

import beans.CraftBuilding;
import beans.Item;
import wrk.ItemWrk;

import java.util.ArrayList;
import java.util.HashMap;

public class Milkery extends CraftBuilding {

    public Milkery() {
        super("Milkery", "milkery", "mk");

        ArrayList<Item> items = new ArrayList<>();

        /*
            Item : Butter
            Recipe : 2x Milk
            Production time : 120s
         */
        HashMap<Item, Integer> recipe = new HashMap<>();
        recipe.put(ItemWrk.getItemByAlias("milk"), 2);

        Item i = new Item("butter", "Butter").setRecipe(recipe)
                .setProductionTime(120).setXpGranted(28);
        ItemWrk.addExistingItem(i, this);
        items.add(i);

        /*
            Item : Cream
            Recipe : 3x Milk
            Production time : 150s
         */
        recipe = new HashMap<>();
        recipe.put(ItemWrk.getItemByAlias("milk"), 3);

        i = new Item("cream", "Cream").setRecipe(recipe)
                .setProductionTime(120).setXpGranted(33);
        ItemWrk.addExistingItem(i, this);
        items.add(i);

        /*
            Item : Yoghurt
            Recipe : 3x Milk + 1x Sugar
            Production time : 300s
         */
        recipe = new HashMap<>();
        recipe.put(ItemWrk.getItemByAlias("milk"), 3);
        recipe.put(ItemWrk.getItemByAlias("sugar"), 1);

        i = new Item("yoghurt", "Yoghurt").setRecipe(recipe)
                .setProductionTime(300).setXpGranted(100);
        ItemWrk.addExistingItem(i, this);
        items.add(i);


        setUpgrades(new Upgrade[] {
                new Upgrade(2, new Item[] { items.get(0) }),

                new Upgrade(11, 250, 100, 1, new Item[] {
                        items.get(1)
                }),

                new Upgrade(13, 250, 125, 1, new Item[] {
                        items.get(2)
                })
        });
    }

}
