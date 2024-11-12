package wrk;

import beans.*;
import beans.craftbuildings.Bakery;
import beans.craftbuildings.Confectionery;
import beans.craftbuildings.GrowingFields;
import beans.craftbuildings.Milkery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Objects;

public class BuildingWrk extends Wrk {

    public BuildingWrk() {
        if(buildings == null) {
            this.buildings = new CraftBuilding[]{
                    new GrowingFields(),
                    new Bakery(),
                    new Milkery(),
                    new Confectionery()
            };
        }
    }

    public void printAllAvailableBuildings(Account acc) throws FarmException {
        ArrayList<CraftBuilding> blds = getAllAvailableBuildings(acc);

        if(!blds.isEmpty()) {
            for (CraftBuilding cb : blds) {
                boolean isBuilt = acc.isBuildingBuilt(cb);
                System.out.println((isBuilt ? " ".repeat(11) : Wrk.italic("(Un-built)")) + " " +
                        cb.getName() + " ".repeat(Math.max(1, 18 - cb.getName().length())) + Wrk.italic(cb.getAlias()) +
                        (!isBuilt ? " Cost : " + cb.getPrice() + " gold" : ""));
            }
        }
    }

    public void printAllBuildingStatus(Account acc) {
        for (CraftBuilding bd : acc.getBuildings()) bd.printProductionState();
    }

    public ArrayList<CraftBuilding> getAllAvailableBuildings(Account acc) throws FarmException {
        ArrayList<CraftBuilding> blds = new ArrayList<>(Arrays.asList(acc.getXpLevel().getUnlockedBuildings()));

        for (XPLevel lvl : LevelWrk.getAllPreviousLevels(acc.getXpLevel().getId())) {
            blds.addAll(Arrays.asList(lvl.getUnlockedBuildings()));
        }

        blds.removeIf(Objects::isNull);

        return blds;
    }

    public void upgradeBuilding(CraftBuilding b) throws FarmException {
        if(b != null && b.getNextUpgrade() != null) {
            b.upgrade();
        } else {
            throw new FarmException("Invalid building to upgrade");
        }
    }

    public void buildBuilding(Account acc, CraftBuilding b) throws FarmException {
        if(b != null) {
            acc.buildNewBuilding(b);
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
