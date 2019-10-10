package net.csirmazbendeguz.memory_game.state.event_handlers;

import com.google.inject.Inject;
import net.csirmazbendeguz.memory_game.event.listeners.CardFlipUpListener;
import net.csirmazbendeguz.memory_game.event.listeners.GameEndListener;
import net.csirmazbendeguz.memory_game.event.listeners.GameStartListener;
import net.csirmazbendeguz.memory_game.event.objects.CardFlipUpEvent;
import net.csirmazbendeguz.memory_game.event.objects.GameEndEvent;
import net.csirmazbendeguz.memory_game.event.objects.GameStartEvent;
import net.csirmazbendeguz.memory_game.state.Stopwatch;

public class StopwatchHandler implements GameStartListener, GameEndListener, CardFlipUpListener {

    private Stopwatch stopwatch;

    @Inject
    public StopwatchHandler(Stopwatch stopwatch) {
        this.stopwatch = stopwatch;
    }

    @Override
    public void gameStarted(GameStartEvent event) {
        // Stop the stopwatch if it's still running.
        // The stopwatch can still run if a new game is started without finishing the previous one.
        if (stopwatch.isRunning()) {
            stopwatch.stop();
        }
        stopwatch.reset();
    }

    /**
     * Start the stopwatch when the first card is flipped up.
     */
    @Override
    public void cardFlippedUp(CardFlipUpEvent event) {
        if (!stopwatch.isRunning()) {
            stopwatch.start();
        }
    }

    @Override
    public void gameEnded(GameEndEvent event) {
        stopwatch.stop();
    }

}
