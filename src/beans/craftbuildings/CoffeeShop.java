package beans.craftbuildings;

import beans.CraftBuilding;
import beans.Item;
import wrk.ItemWrk;

import java.util.ArrayList;
import java.util.HashMap;

public class CoffeeShop extends CraftBuilding {

    public CoffeeShop() {
        super("Coffee shop", "coffeeshop", "cs");

        ArrayList<Item> items = new ArrayList<>();

        /*
            Item : Coffee
            Recipe : 2x Coffee beans
            Production time : 30s
         */
        HashMap<Item, Integer> recipe = new HashMap<>();
        recipe.put(ItemWrk.getItemByAlias("coffeebeans"), 2);

        Item i = new Item("coffee", "Coffee").setRecipe(recipe)
                .setProductionTime(30).setXpGranted(25);
        ItemWrk.addExistingItem(i, this);
        items.add(i);

        /*
            Item : Espresso
            Recipe : 2x Coffee beans
            Production time : 30s
         */
        recipe = new HashMap<>();
        recipe.put(ItemWrk.getItemByAlias("coffeebeans"), 2);

        i = new Item("espresso", "Espresso").setRecipe(recipe)
                .setProductionTime(30).setXpGranted(20);
        ItemWrk.addExistingItem(i, this);
        items.add(i);


        setUpgrades(new Upgrade[] {
                new Upgrade(2, 250, new Item[] { items.get(0) }),

                new Upgrade(11, 50, 100, 1, new Item[] {
                        items.get(1)
                })
        });
    }

}
