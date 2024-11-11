package beans;

import java.util.HashMap;

public class Item /*implements Comparable*/ {

    public Item(String alias, String name) {
        this.alias = alias;
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public String getName() {
        return name;
    }

    public CraftBuilding getSource() {
        return this.src;
    }

    public Item setSource(CraftBuilding c) {
        this.src = c;
        return this;
    }

    public int getXpGranted() {
        return xpGranted;
    }

    public Item setXpGranted(int xpGranted) {
        this.xpGranted = xpGranted;
        return this;
    }

    public HashMap<Item, Integer> getRecipe() {
        return recipe;
    }

    public Item setRecipe(HashMap<Item, Integer> recipe) {
        this.recipe = recipe;
        return this;
    }

    public int getProductionTime() {
        return productionTime;
    }

    public Item setProductionTime(int productionTime) {
        this.productionTime = productionTime;
        return this;
    }

    public int getMinGoldValue() {
        return getGoldValue(this);
    }

    private int getGoldValue(Item i) {
        int v = 0;
        if(!i.recipe.isEmpty()) {
            for (Item it : i.recipe.keySet()) {
                v += getGoldValue(it) * i.recipe.get(it);
            }
        } else {
            v = Math.max(1, productionTime / 25);
        }
        return v;
    }

    private String alias;
    private String name;
    private CraftBuilding src;

    private int xpGranted;
    private HashMap<Item, Integer> recipe;
    private int productionTime;

    /*@Override
    public int compareTo(Object o) {
        int res = -1;
        try {
            Item it = (Item) o;
            if(this.recipe.size() == it.getRecipe().size()) {
                res = this.productionTime > it.getProductionTime() ? 0 : 1;
            } else {
                res = this.recipe.size() > it.getRecipe().size() ? 0 : 1;
            }
        } catch (Exception e) {
            res = getClass().getName().compareTo(o.getClass().getName());
        }
        return res;
    }*/
}
