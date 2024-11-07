package beans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class Command {

    public Command(String input) {
        String[] split = input.split(" ");
        this.command = split[0];

        String[] args = new String[split.length - 1];
        for (int i = 0; i < split.length - 1; i++) {
            args[i] = split[i + 1];
        }
        this.args = args;
    }

    public String getCommand() {
        return this.command;
    }

    public String getTarget() {
        return (args.length > 0 ? args[0] : null);
    }

    public boolean exists(String s) {
        return Arrays.asList(args).contains(s);
    }

    public boolean exists(int i) {
        return getIntArgs().contains(i);
    }

    public List<Integer> getIntArgs() {
        ArrayList<Integer> res = new ArrayList<>();
        Arrays.stream(args).filter(arg -> {
            try {
                Integer.parseInt(arg);
                return true;
            } catch(NumberFormatException e) {
                return false;
            }
        }).forEach((arg) -> res.add(Integer.parseInt(arg)));
        return res;
    }

    public int getFirstInt() {
        int res;
        try {
            res = getIntArgs().getFirst();
        } catch (NoSuchElementException e) {
            res = 0;
        }
        return res;
    }

    public String[] getArgsFrom(int index) {
        if(index > args.length) return null;

        String[] res = new String[args.length - index];
        for (int i = 0; i < res.length; i++) {
            res[i] = args[i + index];
        }
        return res;
    }

    public String getArg(int index) {
        return args.length > index ? args[index] : null;
    }

    public String[] getArgs() {
        return args;
    }

    private String command;
    private String[] args;
}
