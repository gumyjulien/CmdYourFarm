package wrk;

import beans.*;

import java.util.Arrays;

public class AccountWrk extends Wrk {

    public AccountWrk(Account acc) {
        this.acc = acc;
        this.bldWrk = new BuildingWrk();
    }

    public void showResources() {
        System.out.println("XP Level " + xpLvl(acc.getXpLevel().getId() + "") + " (" + acc.getXp() + " / " + acc.getXpLevel().getXpNeeded() + "xp)");
        System.out.println(acc.getGold() + " gold, " + acc.getGems() + " gems");
    }

    public void showStockContent() {
        acc.getStock().printStock();
    }

    public static Stock getStock() {
        return acc.getStock();
    }

    public static void grantXP(int n) {
        acc.addXp(n);
    }

    public void executeCommand(Command c) throws FarmException {
        // first check if the alias matches a building
        for (CraftBuilding b : acc.getBuildings()) {
            if(c.getCommand().equals(b.getAlias()) || c.getCommand().equals(b.getSmol())) {
                String target = c.getTarget();
                if(target == null) throw new FarmException("No building specified");
                for (CustomCommand cu : b.getCmds()) {
                    if(target.equals(cu.getAlias()) || target.equals(cu.getSmol())) {
                        cu.execute(c);
                        break;
                    }
                }
            }
        }
    }

    private BuildingWrk bldWrk;
    private static Account acc;
}
