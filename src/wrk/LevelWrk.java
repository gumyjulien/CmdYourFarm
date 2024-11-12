package wrk;

import beans.*;

public class LevelWrk extends Wrk {

    public LevelWrk() {
        lvls = new XPLevel[50];

        lvls[0] = new XPLevel(1, 10, new CraftBuilding[] {
                BuildingWrk.getBuildingByAlias("growingfields"),
                BuildingWrk.getBuildingByAlias("bakery")
        });

        lvls[1] = new XPLevel(2, 25, new CraftBuilding[] {});

        lvls[2] = new XPLevel(3, 50, new CraftBuilding[] {});

        lvls[3] = new XPLevel(4, 150, new CraftBuilding[] {});

        lvls[4] = new XPLevel(5, 200, new CraftBuilding[] {});

        lvls[5] = new XPLevel(6, 250, new CraftBuilding[] {});

        lvls[6] = new XPLevel(7, 300, new CraftBuilding[] {
                BuildingWrk.getBuildingByAlias("milkery")
        });

        lvls[7] = new XPLevel(8, 350, new CraftBuilding[] {
                BuildingWrk.getBuildingByAlias("confectionary")
        });

        lvls[8] = new XPLevel(9, 500, new CraftBuilding[] {});

        lvls[9] = new XPLevel(10, 700, new CraftBuilding[] {});

        lvls[10] = new XPLevel(11, 1000, new CraftBuilding[] {});

        lvls[11] = new XPLevel(12, 1500, new CraftBuilding[] {});

        lvls[12] = new XPLevel(13, 2000, new CraftBuilding[] {});

        lvls[13] = new XPLevel(14, 2500, new CraftBuilding[] {});


        for (int i = 15; i <= lvls.length; i++) {
            lvls[i-1] = new XPLevel(i, i * 50, new CraftBuilding[0]);
        }

    }

    public void setAcc(Account acc) {
        LevelWrk.acc = acc;
    }

    public static XPLevel[] getLvls() {
        return lvls;
    }

    public static XPLevel[] getAllPreviousLevels(int lvl) throws FarmException {
        if(lvl > lvls.length) throw new FarmException("Invalid index for level");

        XPLevel[] res = new XPLevel[lvl - 1];
        for (int i = 0; i < lvl - 1; i++) {
            res[i] = lvls[i];
        }
        return res;
    }

    public static XPLevel getLvl(int no) {
        XPLevel res = null;
        for (XPLevel lvl : lvls) {
            if(lvl.getId() == no) {
                res = lvl;
                break;
            }
        }
        return res;
    }

    public static boolean lvlReached(int i) {
        return acc.getXpLevel().getId() >= i;
    }

    public static void lvlUpAccount(Account acc) {
        int id = acc.getXpLevel().getId();
        acc.setXpLevel(getLvl(++id));
        System.out.println("------- LEVEL UP !! ---------");
        System.out.println(" XP Level " + id + " reached ! ");
        if(getLvl(id).getUnlockedBuildings().length > 0) {
            System.out.print("Unlocked building(s) : ");
            StringBuilder list = new StringBuilder();
            for (CraftBuilding bd : getLvl(id).getUnlockedBuildings()) {
                list.append(bd.getName()).append(", ");
            }
            list.delete(list.length() - 2, list.length());
            System.out.println(list);
        }
        System.out.println("-----------------------------");
    }

    private static Account acc;
    private static XPLevel[] lvls;
}
