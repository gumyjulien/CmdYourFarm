package beans;

import wrk.Wrk;

public class Countdown implements Runnable {

    public Countdown(int sec, Runnable r) {
        this.seconds = sec;
        this.r = r;
    }

    @Override
    public void run() {
        try {
            this.secondsLeft = this.seconds;

            while(this.secondsLeft > 0) {
                Thread.sleep(1000);
                this.secondsLeft--;
            }
        } catch (InterruptedException ex) {
            System.out.println(Wrk.red("Error : " + ex.getMessage()));
        } finally {
            try {
                r.run();
            } catch(Exception e) {
                System.out.println(Wrk.red(e.getMessage()));
            }
        }
    }

    public int getSeconds() {
        return this.seconds;
    }

    public int getSecondsLeft() {
        return this.secondsLeft;
    }

    private final int seconds;
    private int secondsLeft;
    private Runnable r;
}
