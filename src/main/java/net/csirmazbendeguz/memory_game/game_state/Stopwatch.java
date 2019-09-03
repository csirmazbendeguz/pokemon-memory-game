package net.csirmazbendeguz.memory_game.game_state;

import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Service to manage the game timer.
 */
public class Stopwatch extends Observable {

    /**
     * The timer.
     */
    private Timer timer;

    /**
     * Seconds passed since the start of the timer.
     */
    private int seconds;

    /**
     * Return the seconds passed since the start of the timer.
     */
    int getSeconds() {
        return seconds;
    }

    /**
     * Reset the seconds to zero.
     */
    void resetSeconds() {
        seconds = 0;
        setChanged();
        notifyObservers(seconds);
    }

    /**
     * Start the timer.
     */
    void startTimer() {
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
    void stopTimer() {
        if (!isTimerRunning()) {
            return;
        }

        timer.cancel();
        timer = null;
    }

    /**
     * Return whether the timer is running.
     */
    private boolean isTimerRunning() {
        return timer != null;
    }

}
