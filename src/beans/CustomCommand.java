package beans;

import wrk.Wrk;

public abstract class CustomCommand {
    public CustomCommand(String alias, String smol, String desc) {
        this.alias = alias;
        this.desc = desc;
        this.smol = smol;
    }

    public String getAlias() {
        return alias;
    }

    public String getSmol() {
        return this.smol;
    }

    public String getDesc() {
        return this.desc;
    }

    public abstract void execute(Command c) throws FarmException;

    @Override
    public String toString() {
        return this.alias + " ".repeat(10 - alias.length()) + Wrk.italic(this.smol) + " ".repeat(6 - smol.length())
                + desc.replace("\r\n", "\r\n" + " ".repeat(15));
    }

    private String alias;
    private String smol;
    private String desc;
}
