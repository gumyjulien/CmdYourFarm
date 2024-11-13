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

        Item i = new Item("wheat", "Wheat").setRecipe(recipe)
                .setProductionTime(10).setXpGranted(5);
        ItemWrk.addExistingItem(i, this);
        items.add(i);

        /*
            Item : Corn
            Production time : 25s
         */

        i = new Item("corn", "Corn").setRecipe(recipe)
                .setProductionTime(15).setXpGranted(7);
        ItemWrk.addExistingItem(i, this);
        items.add(i);

        /*
            Item : Soja
            Production time : 30s
         */

        i = new Item("soja", "Soja").setRecipe(recipe)
                .setProductionTime(30).setXpGranted(10);
        ItemWrk.addExistingItem(i, this);
        items.add(i);

        /*
            Item : Sugar cane
            Production time : 60s
         */

        i = new Item("sugarcane", "Sugar cane").setRecipe(recipe)
                .setProductionTime(60).setXpGranted(15);
        ItemWrk.addExistingItem(i, this);
        items.add(i);

        /*
            Item : Raspberry
            Production time : 120s
         */

        i = new Item("raspberry", "Raspberry").setRecipe(recipe)
                .setProductionTime(120).setXpGranted(20);
        ItemWrk.addExistingItem(i, this);
        items.add(i);

        /*
            Item : Blueberry
            Production time : 120s
         */

        i = new Item("blueberry", "Blueberry").setRecipe(recipe)
                .setProductionTime(120).setXpGranted(20);
        ItemWrk.addExistingItem(i, this);
        items.add(i);

        /*
            Item : Blackberry
            Production time : 150s
         */

        i = new Item("blackberry", "Blackberry").setRecipe(recipe)
                .setProductionTime(150).setXpGranted(30);
        ItemWrk.addExistingItem(i, this);
        items.add(i);

        /*
            Item : Coffee beans
            Production time : 200s
         */

        i = new Item("coffeebeans", "Coffee beans").setRecipe(recipe)
                .setProductionTime(200).setXpGranted(50);
        ItemWrk.addExistingItem(i, this);
        items.add(i);

        /*
            Item : Cocoa
            Production time : 200s
         */

        i = new Item("cocoa", "Cocoa").setRecipe(recipe)
                .setProductionTime(220).setXpGranted(50);
        ItemWrk.addExistingItem(i, this);
        items.add(i);

        /*
            Item : Avocado
            Production time : 250s
         */

        i = new Item("avocado", "Avocado").setRecipe(recipe)
                .setProductionTime(250).setXpGranted(50);
        ItemWrk.addExistingItem(i, this);
        items.add(i);

        /*
            Item : Potato
            Production time : 250s
         */

        i = new Item("potato", "Potato").setRecipe(recipe)
                .setProductionTime(250).setXpGranted(50);
        ItemWrk.addExistingItem(i, this);
        items.add(i);

        /*
            Item : Sweet potato
            Production time : 250s
         */

        i = new Item("sweetpotato", "Sweet potato").setRecipe(recipe)
                .setProductionTime(250).setXpGranted(75);
        ItemWrk.addExistingItem(i, this);
        items.add(i);

        setUpgrades(new Upgrade[] {
                new Upgrade(2, 0, new Item[] { items.get(0) }),

                new Upgrade(3, 50, 20, 1, new Item[] {
                        items.get(1)
                }),
                new Upgrade(5, 75, 25, 0, new Item[] {
                        items.get(2)
                }),
                new Upgrade(8, 100, 40, 0, new Item[] {
                        items.get(3)
                }),
                new Upgrade(9, 125, 60, 3, new Item[] {
                        items.get(4), items.get(5)
                }),
                new Upgrade(10, 150, 75, 1, new Item[] {
                        items.get(6)
                }),
                new Upgrade(11, 150, 100, 2, new Item[] {
                        items.get(7), items.get(8)
                }),
                new Upgrade(11, 150, 100, 0, new Item[] {
                        items.get(9)
                }),
                new Upgrade(13, 150, 100, 1, new Item[] {
                        items.get(10), items.get(11)
                })
        });
    }

}
