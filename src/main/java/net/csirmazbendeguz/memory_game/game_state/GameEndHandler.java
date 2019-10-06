package net.csirmazbendeguz.memory_game.game_state;

import com.google.inject.Inject;
import net.csirmazbendeguz.memory_game.event.EventDispatcher;
import net.csirmazbendeguz.memory_game.event.listeners.CardHideListener;
import net.csirmazbendeguz.memory_game.event.objects.CardHideEvent;
import net.csirmazbendeguz.memory_game.event.objects.GameEndEvent;

public class GameEndHandler implements CardHideListener {

    private Board board;

    private Stopwatch stopwatch;

    private TriesCounter triesCounter;

    private EventDispatcher eventDispatcher;

    @Inject
    public GameEndHandler(Board board, Stopwatch stopwatch, TriesCounter triesCounter, EventDispatcher eventDispatcher) {
        this.board = board;
        this.stopwatch = stopwatch;
        this.triesCounter = triesCounter;
        this.eventDispatcher = eventDispatcher;
    }

    /**
     * Finish the game when the last card is removed from the board.
     */
    @Override
    public void cardHidden(CardHideEvent event) {
        if (board.isEmpty()) {
            eventDispatcher.dispatch(new GameEndEvent(
                this,
                board.getDimension(),
                stopwatch.getSeconds(),
                triesCounter.getTries()
            ));
        }
    }

}
