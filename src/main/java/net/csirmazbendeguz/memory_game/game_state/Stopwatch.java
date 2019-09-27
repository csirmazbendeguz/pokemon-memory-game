package net.csirmazbendeguz.memory_game.game_state;

import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

public class Stopwatch extends Observable {

    private Timer timer;

    /**
     * Seconds passed since the start of the timer.
     */
    private int seconds;

    int getSeconds() {
        return seconds;
    }

    void resetSeconds() {
        seconds = 0;
        setChanged();
        notifyObservers(seconds);
    }

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

    void stopTimer() {
        if (!isTimerRunning()) {
            return;
        }

        timer.cancel();
        timer = null;
    }

    private boolean isTimerRunning() {
        return timer != null;
    }

}
