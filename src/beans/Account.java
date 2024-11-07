package beans;

import beans.craftbuildings.Bakery;
import wrk.AccountWrk;
import wrk.LevelWrk;

import java.util.Arrays;

public class Account {

    public Account () {
        this.gold = 500;
        this.gems = 25;
        this.xp = 0;
        this.xpLevel = LevelWrk.getLvl(1);
        this.blds = new CraftBuilding[0];
        for (CraftBuilding cb : xpLevel.getUnlockedBuildings()) if(cb != null) buildNewBuilding(cb);
        this.stock = new Stock();
    }

    public int getGold() {
        return gold;
    }

    public Account setGold(int gold) {
        this.gold = gold;
        return this;
    }

    public Account changeGold(int n) {
        this.gold += n;
        return this;
    }

    public int getGems() {
        return gems;
    }

    public Account setGems(int gems) {
        this.gems = gems;
        return this;
    }

    public XPLevel getXpLevel() {
        return xpLevel;
    }

    public void setXpLevel(XPLevel xpLevel) {
        this.xpLevel = xpLevel;
    }

    public int getXp() {
        return xp;
    }

    public Account addXp(int xp) {
        this.xp += xp;
        checkLevelUp();
        return this;
    }

    public Account setXp(int xp) {
        this.xp = xp;
        return this;
    }

    public Stock getStock() {
        return stock;
    }

    public boolean isBuildingBuilt(CraftBuilding b) {
        return Arrays.asList(blds).contains(b);
    }

    public CraftBuilding[] getBuildings() {
        return blds;
    }

    public void buildNewBuilding(CraftBuilding b) {
        CraftBuilding[] newB = new CraftBuilding[this.blds.length + 1];
        for (int i = 0; i < blds.length; i++) {
            newB[i] = blds[i];
        }
        newB[newB.length - 1] = b;
        this.blds = newB;
    }

    private void checkLevelUp() {
        while (this.xp >= this.xpLevel.getXpNeeded()) {
            this.xp -= this.xpLevel.getXpNeeded();
            LevelWrk.lvlUpAccount(this);
        }
    }

    // resources
    private int gold;
    private int gems;
    private XPLevel xpLevel;
    private int xp;

    // buildings
    private Stock stock;
    private CraftBuilding[] blds;
}
