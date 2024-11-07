package beans;

import java.util.Collections;
import java.util.HashMap;
import java.util.TreeMap;

public class Stock extends Building {

    protected Stock() {
        super("Stock", "stock", "st");
        this.content = new HashMap<>();
    }

    public void printStock() {
        System.out.println("----- STOCK -----");
        System.out.println("Capacity used : " + usedCapacity + " / " + maxCapacity);
        System.out.println("Content :");
        int i = 1;
        for (Item it : content.keySet()) {
            if(content.get(it) > 0) {
                StringBuilder st = new StringBuilder();
                st.append(content.get(it)).append(" ").append(it.getName());
                st.append(" ".repeat(Math.max(20 - st.length(), 1)));
                System.out.print(st);
                if(i % 3 == 0) {
                    System.out.println();
                    i = 1;
                } else {
                    i++;
                }
            }
        }
        System.out.println();
    }

    public void add(Item i, int nb) {
        content.put(i, nb + content.getOrDefault(i, 0));
        usedCapacity += nb;
    }

    public void addAll(HashMap<Item, Integer> items) {
        for (Item i : items.keySet()) add(i, items.get(i));
    }

    public void remove(Item i, int nb) throws FarmException {
        if(content.containsKey(i) && content.get(i) >= nb) {
            content.put(i, content.get(i) - nb);
            usedCapacity -= nb;
        } else {
            throw new FarmException("Not enough items");
        }
    }

    public void removeAll(HashMap<Item, Integer> items) throws FarmException {
        for (Item i : items.keySet()) remove(i, items.get(i));
    }

    public boolean contains(HashMap<Item, Integer> items) {
        boolean res = true;
        for (Item i : items.keySet()) {
            int nbReq = items.get(i);
            if(content.getOrDefault(i, 0) < nbReq) {
                res = false;
                break;
            }
        }
        return res;
    }

    private int usedCapacity;
    private int maxCapacity;
    private HashMap<Item, Integer> content;
}
