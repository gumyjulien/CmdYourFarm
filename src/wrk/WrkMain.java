package wrk;

import beans.*;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import java.util.logging.Level;

public class WrkMain extends Wrk {

    public WrkMain() {
        scanner = new Scanner(System.in);

        bdWrk = new BuildingWrk();
        lvlWrk = new LevelWrk();
        Account acc = new Account();
        accWrk = new AccountWrk(acc);
        lvlWrk.setAcc(acc);

        rootCommands = new CustomCommand[] {
                new CustomCommand("givexp", " ", "free xp") {
                    @Override
                    public void execute(Command c) throws FarmException {
                        AccountWrk.grantXP(c.getFirstInt());
                    }
                },
                new CustomCommand("give", " ","<item> <amount> free items") {
                    @Override
                    public void execute(Command c) throws FarmException {
                        Item it = ItemWrk.getItemByAlias(c.getArg(0));
                        int nb = c.getFirstInt();
                        if(it != null && nb > 0) {
                            acc.getStock().add(it, nb);
                        }
                    }
                },
                new CustomCommand("help", "h", "show this help page") {
                    @Override
                    public void execute(Command c) throws FarmException {
                        showHelp(c.getArgs());
                    }
                },
                new CustomCommand("resources", "rs","show every resource you own") {
                    @Override
                    public void execute(Command c) throws FarmException {
                        accWrk.showResources();
                    }
                },
                new CustomCommand("stock", "stk","show your stock's content") {
                    @Override
                    public void execute(Command c) throws FarmException {
                        accWrk.showStockContent();
                    }
                },
                new CustomCommand("info", "if","<item> show the information about this item") {
                    @Override
                    public void execute(Command c) throws FarmException {
                        Item it = ItemWrk.getItemByAlias(c.getArg(0));
                        if(it != null) {
                            System.out.println("Information about " + it.getName() + " (" + Wrk.italic(it.getAlias()) + ") :");
                            CraftBuilding bd = BuildingWrk.getBuildingByItem(it);
                            if(bd != null) {
                                System.out.println("Produced in " + it.getProductionTime() + "s in your " + bd.getName() + " and grants " + it.getXpGranted() + "xp on pick-up");
                            } else {
                                System.out.println(Wrk.red("This item is not producible"));
                            }
                            System.out.println("The minimum gold value of this item is " + it.getMinGoldValue() + " gold");
                        } else {
                            throw new FarmException("Invalid item specified");
                        }
                    }
                },
                new CustomCommand("sell", "sl","<item> <amount> : sell the amount of items in exchange of gold") {
                    @Override
                    public void execute(Command c) throws FarmException {
                        Item it = ItemWrk.getItemByAlias(c.getArg(0));
                        int nb = c.getFirstInt();
                        if(it != null && nb > 0) {
                            if(acc.getStock().contains(it, nb)) {
                                int gold = it.getMinGoldValue() * nb;
                                acc.changeGold(gold);
                                acc.getStock().remove(it, nb);
                                System.out.println("You received " + gold + " gold in exchange of " + nb + "x " + it.getName());
                            } else {
                                throw new FarmException("Not enough " + it.getName());
                            }
                        } else {
                            throw new FarmException("Invalid item or amount");
                        }
                    }
                },
                new CustomCommand("building-status", "bd-st","show the status of every building") {
                    @Override
                    public void execute(Command c) throws FarmException {
                        bdWrk.printAllBuildingStatus(acc);
                    }
                },
                new CustomCommand("build", "bd","<building> : build the specified building") {
                    @Override
                    public void execute(Command c) throws FarmException {
                        String bld = c.getArg(0);
                        if(bld != null) {
                            CraftBuilding b = BuildingWrk.getBuildingByAlias(bld);
                            if(b != null && b.getNextUpgrade() != null) {
                                if(bdWrk.getAllAvailableBuildings(acc).contains(b) && Arrays.stream(acc.getBuildings()).noneMatch(b::equals)) {
                                    int cost = b.getPrice();
                                    if (acc.getGold() >= cost) {
                                        bdWrk.buildBuilding(acc, b);
                                        acc.changeGold(-cost);
                                        System.out.println(b.getName() + " successfully built !");
                                    } else {
                                        throw new FarmException("You need " + (cost - acc.getGold()) + " more gold to build this building");
                                    }
                                } else {
                                    throw new FarmException("XP Level " + b.getNextUpgrade().xpLevelRequired + " required to build this building");
                                }
                            } else {
                                throw new FarmException("Invalid building to upgrade");
                            }
                        }
                    }
                },
                new CustomCommand("buildings", "bd-ls","show every available building") {
                    @Override
                    public void execute(Command c) throws FarmException {
                        bdWrk.printAllAvailableBuildings(acc);
                    }
                },
                new CustomCommand("upgrade", "up","<building> upgrade the building") {
                    @Override
                    public void execute(Command c) throws FarmException {
                        String bld = c.getArg(0);
                        if(bld != null) {
                            CraftBuilding b = BuildingWrk.getBuildingByAlias(bld);
                            if(b != null && b.getNextUpgrade() != null) {
                                if(LevelWrk.lvlReached(b.getNextUpgrade().xpLevelRequired)) {
                                    int cost = b.getNextUpgrade().goldCost;
                                    if (acc.getGold() >= cost) {
                                        bdWrk.upgradeBuilding(b);
                                        acc.changeGold(-cost);
                                        System.out.println(b.getName() + " successfully upgraded to level " + b.getLevel() + " !");
                                    } else {
                                        throw new FarmException("You need " + (cost - acc.getGold()) + " more gold to upgrade this building");
                                    }
                                } else {
                                    throw new FarmException("XP Level " + b.getNextUpgrade().xpLevelRequired + " required to upgrade this building");
                                }
                            } else {
                                throw new FarmException("Invalid building to upgrade");
                            }
                        }
                    }
                },
                new CustomCommand("quit", "q", italic("quit the game :')")) {
                    @Override
                    public void execute(Command c) throws FarmException {
                        System.exit(-1);
                    }
                },
        };
    }

    public void start() {
        System.out.println("Welcome to Cmd Your Farm !");

        while (true) waitCmd();
    }

    public void waitCmd() {
        String s = scanner.nextLine();
        executeCmd(s);
    }

    private void executeCmd(String cmd) {
        Command c = new Command(cmd);
        CustomCommand command = null;
        for (CustomCommand cu : rootCommands) {
            if (cu.getSmol().equals(c.getCommand()) || cu.getAlias().equals(c.getCommand())) {
                command = cu;
                break;
            }
        }
        try {
            if (command == null) accWrk.executeCommand(c);
            else command.execute(c);
        } catch (FarmException e) {
            System.out.println(red(e.getMessage()));
        }
    }

    public void showHelp(String[] args) {
        System.out.println("--- HELP PAGE ---");
        for (CustomCommand c : rootCommands) {
            System.out.println(c);
        }

        System.out.println();
    }

    final Scanner scanner;
    final AccountWrk accWrk;
    final LevelWrk lvlWrk;
    final BuildingWrk bdWrk;

    final CustomCommand[] rootCommands;
}
