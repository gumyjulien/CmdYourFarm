package beans;

public class ProductionSlot {

    public void craft(Item i, boolean notify) {
        this.ready = false;

        this.item = i;
        this.cd = new Countdown(i.getProductionTime(), () -> {
            if(notify) System.out.println("Production completed : " + item.getName());
            this.ready = true;
        });

        new Thread(cd).start();
    }

    public int getTime() {
        return this.cd.getSeconds();
    }

    public int getTimeLeft() {
        return this.cd.getSecondsLeft();
    }

    public Item getItem() { return this.item; }

    public void clearSlot() { this.item = null; this.ready = false; }

    public boolean isReady() { return this.ready; }

    private Item item;
    private Countdown cd;
    private boolean ready;
}
