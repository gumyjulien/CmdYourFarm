package beans;

public abstract class Building {

    protected Building(String name, String alias, String smol) {
        this.name = name;
        this.alias = alias;
        this.smol = smol;
        this.level = 1;
    }

    public String getName() {
        return name;
    }

    public String getAlias() {
        return alias;
    }

    public String getSmol() {
        return smol;
    }

    public int getLevel() {
        return level;
    }

    public void printNextLevelInfo() {
        System.out.println("No upgrade available for this building.");
    }

    String name;
    String alias;
    String smol;
    int level;
}
