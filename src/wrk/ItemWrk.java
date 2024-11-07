package wrk;

import beans.CraftBuilding;
import beans.Item;

import java.util.HashMap;

public class ItemWrk {

    public static void addExistingItem(Item i, CraftBuilding src) {
        items.put(i.getAlias(), i.setSource(src));
    }

    public static Item getItemByAlias(String alias) {
        return alias != null ? items.get(alias) : null;
    }

    private final static HashMap<String, Item> items = new HashMap<>();
}
