package beans.craftbuildings;

import beans.CraftBuilding;
import beans.Item;
import wrk.ItemWrk;

import java.util.ArrayList;
import java.util.HashMap;

public class Grill extends CraftBuilding {

    public Grill() {
        super("Grill", "grill", "gr");

        ArrayList<Item> items = new ArrayList<>();

        /*
            Item : Eggs & bacon
            Recipe : 2x Egg + 2x Bacon
            Production time : 60s
         */
        HashMap<Item, Integer> recipe = new HashMap<>();
        recipe.put(ItemWrk.getItemByAlias("egg"), 2);
        recipe.put(ItemWrk.getItemByAlias("bacon"), 2);

        Item i = new Item("eggs&bacon", "Eggs and bacon").setRecipe(recipe)
                .setProductionTime(60).setXpGranted(40);
        ItemWrk.addExistingItem(i, this);
        items.add(i);

        /*
            Item : Avocado toast
            Recipe : 1x Raw toast + 1x Butter + 1x Avocado
            Production time : 90s
         */
        recipe = new HashMap<>();
        recipe.put(ItemWrk.getItemByAlias("rawtoast"), 1);
        recipe.put(ItemWrk.getItemByAlias("butter"), 1);
        recipe.put(ItemWrk.getItemByAlias("avocado"), 1);

        i = new Item("avocadotoast", "Avocado toast").setRecipe(recipe)
                .setProductionTime(90).setXpGranted(100);
        ItemWrk.addExistingItem(i, this);
        items.add(i);

        /*
            Item : Fishburger
            Recipe : 1x Empty burger + 2x Fish fillet
            Production time : 60s
         */
        recipe = new HashMap<>();
        recipe.put(ItemWrk.getItemByAlias("emptyburger"), 1);
        recipe.put(ItemWrk.getItemByAlias("fishfillet"), 2);

        i = new Item("fishburger", "Fishburger").setRecipe(recipe)
                .setProductionTime(60).setXpGranted(120);
        ItemWrk.addExistingItem(i, this);
        items.add(i);

        /*
            Item : Smoked salmon
            Recipe : 1x Empty burger + 2x Fish fillet
            Production time : 100s
         */
        recipe = new HashMap<>();
        recipe.put(ItemWrk.getItemByAlias("salmon"), 2);

        i = new Item("smokedsalmon", "Smoked salmon").setRecipe(recipe)
                .setProductionTime(100).setXpGranted(125);
        ItemWrk.addExistingItem(i, this);
        items.add(i);


        setUpgrades(new Upgrade[] {
                new Upgrade(2, 200, new Item[] { items.get(0) }),

                new Upgrade(12, 250, 200, 1, new Item[] {
                        items.get(1)
                }),

                new Upgrade(14, 500, 500, 2, new Item[] {
                        items.get(2), items.get(3)
                })
        });
    }

}
