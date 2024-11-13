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
            Item : Corn cubes
            Recipe : 3x Corn + 2x Egg
            Production time : 60s
         */
        recipe = new HashMap<>();
        recipe.put(ItemWrk.getItemByAlias("corn"), 3);
        recipe.put(ItemWrk.getItemByAlias("egg"), 2);

        i = new Item("corncubes", "Corn cubes").setRecipe(recipe)
                .setProductionTime(60).setXpGranted(25);
        ItemWrk.addExistingItem(i, this);
        items.add(i);

        /*
            Item : Muffin dough
            Recipe : 2x Wheat + 1x Egg + 1x Sugar
            Production time : 15s
         */
        recipe = new HashMap<>();
        recipe.put(ItemWrk.getItemByAlias("wheat"), 2);
        recipe.put(ItemWrk.getItemByAlias("egg"), 1);
        recipe.put(ItemWrk.getItemByAlias("sugar"), 1);

        i = new Item("muffindough", "Muffin dough").setRecipe(recipe)
                .setProductionTime(15).setXpGranted(15);
        ItemWrk.addExistingItem(i, this);
        items.add(i);

        /*
            Item : Raspberry muffin
            Recipe : 1x Muffin dough + 2x Raspberry
            Production time : 150s
         */
        recipe = new HashMap<>();
        recipe.put(ItemWrk.getItemByAlias("muffindough"), 1);
        recipe.put(ItemWrk.getItemByAlias("raspberry"), 2);

        i = new Item("raspberrymuffin", "Raspberry muffin").setRecipe(recipe)
                .setProductionTime(150).setXpGranted(40);
        ItemWrk.addExistingItem(i, this);
        items.add(i);

        /*
            Item : Blueberry muffin
            Recipe : 1x Muffin dough + 2x Blueberry
            Production time : 150s
         */
        recipe = new HashMap<>();
        recipe.put(ItemWrk.getItemByAlias("muffindough"), 1);
        recipe.put(ItemWrk.getItemByAlias("blueberry"), 2);

        i = new Item("blueberrymuffin", "Blueberry muffin").setRecipe(recipe)
                .setProductionTime(150).setXpGranted(40);
        ItemWrk.addExistingItem(i, this);
        items.add(i);

        /*
            Item : 3-Berries muffin
            Recipe : 1x Muffin dough + 1x Raspberry + 1x Blueberry + 1x Blackberry
            Production time : 200s
         */
        recipe = new HashMap<>();
        recipe.put(ItemWrk.getItemByAlias("muffindough"), 1);
        recipe.put(ItemWrk.getItemByAlias("raspberry"), 1);
        recipe.put(ItemWrk.getItemByAlias("blueberry"), 1);
        recipe.put(ItemWrk.getItemByAlias("blackberry"), 1);

        i = new Item("raspberrymuffin", "Raspberry muffin").setRecipe(recipe)
                .setProductionTime(200).setXpGranted(75);
        ItemWrk.addExistingItem(i, this);
        items.add(i);

        /*
            Item : Raw toast
            Recipe : 3x Wheat
            Production time : 60s
         */
        recipe = new HashMap<>();
        recipe.put(ItemWrk.getItemByAlias("wheat"), 3);

        i = new Item("rawtoast", "Raw toast").setRecipe(recipe)
                .setProductionTime(60).setXpGranted(20);
        ItemWrk.addExistingItem(i, this);
        items.add(i);

        /*
            Item : Empty burger
            Recipe : 2x Wheat + 1 Egg
            Production time : 60s
         */
        recipe = new HashMap<>();
        recipe.put(ItemWrk.getItemByAlias("wheat"), 2);
        recipe.put(ItemWrk.getItemByAlias("egg"), 1);

        i = new Item("emptysandwich", "Empty sandwich").setRecipe(recipe)
                .setProductionTime(60).setXpGranted(40);
        ItemWrk.addExistingItem(i, this);
        items.add(i);

        setUpgrades(new Upgrade[] {
                new Upgrade(2, 50, new Item[] { items.get(0) }),

                new Upgrade(2, 120, 50, 1, new Item[] {
                        items.get(1)
                }),

                new Upgrade(9, 250, 75, 2, new Item[] {
                        items.get(2), items.get(3), items.get(4)
                }),

                new Upgrade(10, 250, 100, 0, new Item[] {
                        items.get(5)
                }),

                new Upgrade(12, 300, 125, 1, new Item[] {
                        items.get(6)
                }),

                new Upgrade(14, 350, 150, 1, new Item[] {
                        items.get(7)
                }),
        });
    }

}
