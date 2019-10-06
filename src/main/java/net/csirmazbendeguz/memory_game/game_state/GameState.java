package net.csirmazbendeguz.memory_game.game_state;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import net.csirmazbendeguz.memory_game.event.EventDispatcher;
import net.csirmazbendeguz.memory_game.event.listeners.CardHideListener;
import net.csirmazbendeguz.memory_game.event.objects.CardHideEvent;
import net.csirmazbendeguz.memory_game.event.objects.GameStartEvent;
import net.csirmazbendeguz.memory_game.event.objects.GameEndEvent;
import net.csirmazbendeguz.memory_game.util.random.RandomCardGenerator;

@Singleton
public class GameState implements CardHideListener {

    private EventDispatcher eventDispatcher;

    private RandomCardGenerator randomCardGenerator;

    private Stopwatch stopwatch;

    private TriesCounter triesCounter;

    private Board board;

    @Inject
    public GameState(EventDispatcher eventDispatcher, RandomCardGenerator randomCardGenerator, Stopwatch stopwatch, TriesCounter triesCounter) {
        this.eventDispatcher = eventDispatcher;
        this.randomCardGenerator = randomCardGenerator;
        this.stopwatch = stopwatch;
        this.triesCounter = triesCounter;
    }

    public void restartGame() {
        newGame(board.getDimension());
    }

    public void increaseDimension() {
        int dimension = board.getDimension();
        dimension += 2;
        if (dimension == 8) {
            dimension = 2;
        }
        newGame(dimension);
    }

    /**
     * Start a new game.
     *
     * @param dimension The new board's dimension.
     */
    public void newGame(int dimension) {
        board = new Board(randomCardGenerator.generate(dimension));
        eventDispatcher.dispatch(new GameStartEvent(this, board));
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
