package wrk;

import beans.*;
import beans.craftbuildings.Bakery;
import beans.craftbuildings.GrowingFields;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class BuildingWrk extends Wrk {

    public BuildingWrk() {
        this.buildings = new CraftBuilding[] {
                new GrowingFields(),
                new Bakery()
        };
    }

    public void printAllAvailableBuildings(Account acc) throws FarmException {
        ArrayList<CraftBuilding> blds = new ArrayList<>(Arrays.asList(acc.getXpLevel().getUnlockedBuildings()));

        for (XPLevel lvl : LevelWrk.getAllPreviousLevels(acc.getXpLevel().getId())) {
            blds.addAll(Arrays.asList(lvl.getUnlockedBuildings()));
        }

        blds.removeIf(Objects::isNull);

        if(blds.isEmpty()) {

        } else {
            for (CraftBuilding cb : blds) {
                boolean isBuilt = acc.isBuildingBuilt(cb);
                System.out.println((isBuilt ? " ".repeat(11) : Wrk.italic("(Un-built)")) + " " +
                        cb.getName() + " ".repeat(Math.max(1, 15 - cb.getName().length())) + Wrk.italic(cb.getAlias()) +
                        (!isBuilt ? " Cost : " + cb.getPrice() + " gold" : ""));
            }
        }
    }

    public void upgradeBuilding(CraftBuilding b) throws FarmException {
        if(b != null && b.getNextUpgrade() != null) {
            b.upgrade();
        } else {
            throw new FarmException("Invalid building to upgrade");
        }
    }
    public static CraftBuilding[] getAllBuildings() {
        return buildings;
    }

    public static CraftBuilding getBuildingByAlias(String alias) {
        CraftBuilding b = null;

        for (CraftBuilding bd : buildings) {
            if(bd.getAlias().equals(alias)) {
                b = bd;
                break;
            }
        }

        return b;
    }

    static CraftBuilding[] buildings;
}
