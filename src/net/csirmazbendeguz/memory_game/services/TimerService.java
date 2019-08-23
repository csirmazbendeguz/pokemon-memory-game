package net.csirmazbendeguz.memory_game.services;

import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Service to handle the current timer.
 */
public class TimerService extends Observable {

    /**
     * The timer service.
     */
    private static TimerService instance;

    /**
     * Get the timer service.
     *
     * @return The timer service.
     */
    public static TimerService getInstance() {
        if (instance == null) {
            instance = new TimerService();
        }

        return instance;
    }

    /**
     * The timer.
     */
    private Timer timer = null;

    /**
     * Seconds passed since the start of the timer.
     */
    private long seconds = 0;

    /**
     * Start the timer.
     */
    public void startTimer() {
        if (isTimerRunning()) {
            return;
        }

        seconds = 0;
        setChanged();
        notifyObservers(seconds);

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

    public boolean isTimerRunning() {
        return timer != null;
    }

    /**
     * Restart the timer.
     */
    public void restartTimer() {
        stopTimer();
        startTimer();
    }

}
