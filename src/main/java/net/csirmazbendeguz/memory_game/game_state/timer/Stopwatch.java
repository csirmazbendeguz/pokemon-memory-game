package net.csirmazbendeguz.memory_game.game_state.timer;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

@Singleton
public class Stopwatch extends Observable {

    private TimerFactory timerFactory;

    private Timer timer;

    /**
     * Seconds passed since the last reset.
     */
    private int seconds;

    @Inject
    public Stopwatch(TimerFactory timerFactory) {
        this.timerFactory = timerFactory;
    }

    public int getSeconds() {
        return seconds;
    }

    /**
     * Reset the stopwatch to 0.
     */
    public void reset() {
        seconds = 0;
        setChanged();
        notifyObservers(seconds);
    }

    /**
     * Start counting the seconds.
     */
    public void start() {
        if (isRunning()) {
            throw new IllegalStateException("The stopwatch is already running.");
        }

        timer = timerFactory.create();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                ++seconds;
                setChanged();
                notifyObservers(seconds);
            }
        }, 0, 1000);
    }

    /**
     * Stop counting the seconds.
     */
    public void stop() {
        if (!isRunning()) {
            throw new IllegalStateException("The stopwatch is already stopped.");
        }

        timer.cancel();
        timer = null;
    }

    public boolean isRunning() {
        return timer != null;
    }

}
