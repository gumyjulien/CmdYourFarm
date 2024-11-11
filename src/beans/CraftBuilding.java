package beans;

import wrk.AccountWrk;
import wrk.ItemWrk;
import wrk.LevelWrk;
import wrk.Wrk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class CraftBuilding extends Building{

    protected CraftBuilding(String name, String alias, String smol) {
        super(name, alias, smol);

        // declare general commands for a craft building
        cmds = new CustomCommand[] {
                new CustomCommand("help", "h", "show all building's commands") {
                    @Override
                    public void execute(Command c) throws FarmException {
                        for (CustomCommand cu : cmds) {
                            System.out.println(cu);
                        }
                    }
                },
                new CustomCommand("recipes", "rp", "show all available items and their recipe") {
                    @Override
                    public void execute(Command c) throws FarmException {
                        for (Item i : getAllAvailableItems()) {
                            StringBuilder alias = new StringBuilder(i.getAlias());
                            alias.append(" ".repeat(Math.max(2, 20 - alias.length())));
                            StringBuilder recipe = new StringBuilder();

                            i.getRecipe().forEach((it, nb) -> recipe.append(it.getName()).append(" x").append(nb).append(", "));

                            if(!recipe.isEmpty()) recipe.delete(recipe.length() - 2, recipe.length());

                            System.out.println(alias.toString() + recipe + Wrk.italic(" ".repeat(Math.max(1, 25 - recipe.length())) + "Production time: " + i.getProductionTime() + "s"));
                        }
                    }
                },
                new CustomCommand("craft", "crf", "<item> craft the item with required resources" +
                        "\r\n[i] number of products (caps with empty slots)" +
                        "\r\n[-n] notify me when ready (false by default)") {
                    @Override
                    public void execute(Command c) throws FarmException {
                        Item it = ItemWrk.getItemByAlias(c.getArg(1));
                        if(it == null) throw new FarmException("Unavailable item");
                        if(!AccountWrk.getStock().contains(it.getRecipe())) throw new FarmException("You need more items to craft this item");

                        boolean notify = c.exists("-n");
                        int nb = c.getFirstInt();
                        if(nb == 0) nb = 1;
                        else if(nb < 1) throw new FarmException("Invalid number of items");
                        int i = 0;
                        for (int j = 0; j < nb; j++) {
                            // find index
                            for (i = 0; i < productionSlots.length; i++) {
                                if(productionSlots[i].getItem() == null) break;
                            }
                            if(i >= productionSlots.length){
                                if(nb == 1) throw new FarmException("No slot available");
                            }
                            else {
                                if (!AccountWrk.getStock().contains(it.getRecipe())) break;
                                productionSlots[i].craft(it, notify);
                                AccountWrk.getStock().removeAll(it.getRecipe());
                            }
                        }
                        System.out.println("Item" + (nb > 1 ? "s" : "") + " ready in " + it.getProductionTime() + "seconds");
                    }
                },
                new CustomCommand("claim", "clm", "<i> claim the ready item at specific index") {
                    @Override
                    public void execute(Command c) throws FarmException {
                        if(c.getArg(1) == null) throw new FarmException("Unspecified slot index");
                        int i = c.getFirstInt();
                        if(i < 0) throw new FarmException("Invalid slot index");

                        Item it = claimItem(i);
                        System.out.println("Item claimed : " + it.getXpGranted() + "XP granted !");
                        AccountWrk.getStock().add(it, 1);
                        AccountWrk.grantXP(it.getXpGranted());
                    }
                },
                new CustomCommand("claim-all", "clm-a", "claim all ready items") {
                    @Override
                    public void execute(Command c) throws FarmException {
                        int totalXp = 0;
                        for (int i = 0; i < productionSlots.length; i++) {
                            if(productionSlots[i].isReady()) {
                                Item it = claimItem(i);
                                AccountWrk.getStock().add(it, 1);
                                totalXp += it.getXpGranted();
                            }
                        }
                        if(totalXp > 0) {
                            System.out.println("Items claimed : " + totalXp + "XP granted !");
                            AccountWrk.grantXP(totalXp);
                        }
                    }
                },
                new CustomCommand("status", "st", "show the status of the production") {
                    @Override
                    public void execute(Command c) throws FarmException {
                        printState();
                    }
                }
        };
    }

    public ArrayList<Item> getAllAvailableItems() {
        ArrayList<Item> items = new ArrayList<>();

        for (int i = 0; i < this.level; i++) {
            items.addAll(List.of(this.upgrades[i].itemsUnlocked));
        }

        return items;
    }

    public Item claimItem(int index) {
        Item i = this.productionSlots[index].getItem();
        this.productionSlots[index].clearSlot();
        return i;
    }

    public CustomCommand[] getCmds() {
        return cmds;
    }

    public Upgrade[] getUpgrades() {
        return this.upgrades;
    }

    public Upgrade getUpgrade(int lvl) {
        return (this.upgrades.length > (lvl - 1) ? this.upgrades[lvl-1] : null);
    }

    public void upgrade() {
        Upgrade up = getUpgrade(++level);
        if(up != null && up.addedSlots > 0) addProductionSlot(up.addedSlots);
    }

    public Upgrade getNextUpgrade() {
        return getUpgrade(level + 1);
    }

    public int getPrice() {
        return upgrades[0].goldCost;
    }

    public void setUpgrades(Upgrade[] ups) {
        this.upgrades = ups;
        setProductionSlots(upgrades[0].addedSlots);
    }

    public void setProductionSlots(int n) {
        productionSlots = new ProductionSlot[n];
        for (int i = 0; i < n; i++) this.productionSlots[i] = new ProductionSlot();
    }

    public void addProductionSlot(int n) {
        ProductionSlot[] newSlots = new ProductionSlot[productionSlots.length + n];

        for (int i = 0; i < productionSlots.length; i++) newSlots[i] = productionSlots[i];
        for (int i = 0; i < n; i++) newSlots[productionSlots.length + i] = new ProductionSlot();

        this.productionSlots = newSlots;
    }

    public void printState() {
        System.out.println(name + " - Level " + level);
        System.out.println("-----------------------------------");
        for (int i = 0; i < productionSlots.length; i++) {
            ProductionSlot slot = productionSlots[i];
            System.out.print("Slot " + i + ": ");
            if(slot.getItem() != null) {
                System.out.print(slot.getItem().getName() + (slot.isReady() ? Wrk.green(" (Ready)") : " (" + slot.getTimeLeft() + "s)"));
            } else {
                System.out.print(Wrk.italic("Empty"));
            }
            System.out.println();
        }
        System.out.println("-----------------------------------");
        printNextLevelInfo();
    }

    public void printProductionState() {
        StringBuilder s = new StringBuilder(name + ": " + " ".repeat(18 - name.length()));
        for (int i = 0; i < productionSlots.length; i++) {
            ProductionSlot slot = productionSlots[i];
            if(slot.getItem() != null) {
                s.append(slot.getItem().getName()).append(slot.isReady() ? Wrk.green(" (Ready)") : " (" + slot.getTimeLeft() + "s)");
            } else {
                s.append(Wrk.italic("Empty"));
            }
            s.append(", ");
        }
        s.delete(s.length() - 2, s.length());
        System.out.println(s);
    }

    @Override
    public void printNextLevelInfo() {
        Upgrade up = this.getUpgrade(level + 1);
        if(up != null) {
            System.out.print("Next upgrade (Level " + (level + 1) + ") : ");
            if (LevelWrk.lvlReached(up.xpLevelRequired)) {
                System.out.println(Wrk.italic("Cost : " + up.goldCost + ", " + (up.addedSlots > 0 ? up.addedSlots + " extra slot(s)" : "no extra slot") + ", grants " + up.xpGranted + "XP"));
                StringBuilder unlockedItems = new StringBuilder("Unlocked Items : ");
                for (Item i : up.itemsUnlocked) {
                    unlockedItems.append(i.getName()).append(", ");
                }
                unlockedItems.delete(unlockedItems.length() - 2, unlockedItems.length());
                System.out.println(unlockedItems);
            } else {
                System.out.println(Wrk.red("XP level " + Wrk.xpLvl(up.xpLevelRequired + "")) + Wrk.red(" required"));
            }
        } else {
            System.out.println(Wrk.italic("Building at max level."));
        }
    }

    private CustomCommand[] cmds;
    private Upgrade[] upgrades;
    protected ProductionSlot[] productionSlots;

    public static class Upgrade {

        public Upgrade(int slots, Item[] itemsUnlocked) {
            this.addedSlots = slots;
            this.itemsUnlocked = itemsUnlocked;
        }

        public Upgrade(int xpLevelRequired, int goldCost, int xpGranted, int addedSlots, Item[] itemsUnlocked) {
            this.xpLevelRequired = xpLevelRequired;
            this.goldCost = goldCost;
            this.xpGranted = xpGranted;
            this.addedSlots = addedSlots;
            this.itemsUnlocked = itemsUnlocked;
        }

        public int xpLevelRequired;
        public int goldCost;
        public int xpGranted;
        public int addedSlots;
        public Item[] itemsUnlocked;

    }

}