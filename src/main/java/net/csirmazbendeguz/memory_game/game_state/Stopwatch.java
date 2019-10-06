package net.csirmazbendeguz.memory_game.game_state;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import net.csirmazbendeguz.memory_game.event.listeners.CardFlipUpListener;
import net.csirmazbendeguz.memory_game.event.listeners.GameEndListener;
import net.csirmazbendeguz.memory_game.event.listeners.GameStartListener;
import net.csirmazbendeguz.memory_game.event.objects.CardFlipUpEvent;
import net.csirmazbendeguz.memory_game.event.objects.GameEndEvent;
import net.csirmazbendeguz.memory_game.event.objects.GameStartEvent;

import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

@Singleton
public class Stopwatch extends Observable implements GameStartListener, CardFlipUpListener, GameEndListener {

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

    @Override
    public void gameStarted(GameStartEvent event) {
        if (isRunning()) {
            stop();
        }
        reset();
    }

    /**
     * Start the stopwatch when the first card is flipped up.
     */
    @Override
    public void cardFlippedUp(CardFlipUpEvent event) {
        if (!isRunning()) {
            start();
        }
    }

    @Override
    public void gameEnded(GameEndEvent event) {
        stop();
    }

}
