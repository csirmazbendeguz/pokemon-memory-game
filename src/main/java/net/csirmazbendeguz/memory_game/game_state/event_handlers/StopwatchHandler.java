package net.csirmazbendeguz.memory_game.game_state.event_handlers;

import com.google.inject.Inject;
import net.csirmazbendeguz.memory_game.event.listeners.CardFlipUpListener;
import net.csirmazbendeguz.memory_game.event.listeners.GameEndListener;
import net.csirmazbendeguz.memory_game.event.listeners.GameStartListener;
import net.csirmazbendeguz.memory_game.event.objects.CardFlipUpEvent;
import net.csirmazbendeguz.memory_game.event.objects.GameEndEvent;
import net.csirmazbendeguz.memory_game.event.objects.GameStartEvent;
import net.csirmazbendeguz.memory_game.game_state.Stopwatch;

public class StopwatchHandler implements GameStartListener, GameEndListener, CardFlipUpListener {

    private Stopwatch stopwatch;

    @Inject
    public StopwatchHandler(Stopwatch stopwatch) {
        this.stopwatch = stopwatch;
    }

    @Override
    public void gameStarted(GameStartEvent event) {
        // Stop the running stopwatch unless it's the first game.
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
