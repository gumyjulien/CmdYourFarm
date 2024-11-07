package wrk;

public class Wrk {
    public static String red(String s) {
        return RED + s + RESET;
    }

    public static String green(String s) {
        return GREEN + s + RESET;
    }

    public static String bold(String s) {
        return BOLD + s + RESET;
    }

    public static String redBold(String s) {
        return RED + BOLD + s + RESET;
    }

    public static String italic(String s) {
        return ITALIC + s + RESET;
    }

    public static String xpLvl(String s) {
        return XP + s + RESET;
    }

    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String BOLD = "\u001B[1m";
    private static final String ITALIC = "\u001B[3m";
    private static final String GREEN = "\u001B[32m";
    private static final String XP = "\u001B[104m\u001B[46m" + BOLD;
}
