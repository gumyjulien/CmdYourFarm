package beans;

public class XPLevel {

    public XPLevel(int id, int xpNeeded, CraftBuilding[] unlockedBuildings) {
        this.id = id;
        this.xpNeeded = xpNeeded;
        this.unlockedBuildings = unlockedBuildings;
    }

    public int getId() {
        return id;
    }

    public int getXpNeeded() {
        return xpNeeded;
    }

    public CraftBuilding[] getUnlockedBuildings() {
        return unlockedBuildings;
    }

    private int id;
    private int xpNeeded;
    private CraftBuilding[] unlockedBuildings;
}
