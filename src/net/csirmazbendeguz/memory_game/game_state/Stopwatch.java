package net.csirmazbendeguz.memory_game.game_state;

import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Service to manage the game timer.
 */
public class Stopwatch extends Observable {

    /**
     * The stopwatch.
     */
    private static Stopwatch instance;

    /**
     * Get the stopwatch.
     *
     * @return The stopwatch.
     */
    public static Stopwatch getInstance() {
        if (instance == null) {
            instance = new Stopwatch();
        }

        return instance;
    }

    private Stopwatch() {}

    /**
     * The timer.
     */
    private Timer timer;

    /**
     * Seconds passed since the start of the timer.
     */
    private long seconds;

    private boolean isTimerRunning() {
        return timer != null;
    }

    /**
     * Reset seconds to zero.
     */
    public void resetSeconds() {
        seconds = 0;
        setChanged();
        notifyObservers(seconds);
    }

    /**
     * Start the timer.
     */
    public void startTimer() {
        if (isTimerRunning()) {
            return;
        }

        resetSeconds();

        timer = new Timer();
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
     * Stop the timer.
     */
    public void stopTimer() {
        if (!isTimerRunning()) {
            return;
        }

        timer.cancel();
        timer = null;
    }

}
