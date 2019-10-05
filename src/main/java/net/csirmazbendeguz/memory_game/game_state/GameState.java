package net.csirmazbendeguz.memory_game.game_state;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import net.csirmazbendeguz.memory_game.event.EventDispatcher;
import net.csirmazbendeguz.memory_game.event.listeners.CardFlipUpListener;
import net.csirmazbendeguz.memory_game.event.listeners.CardHideListener;
import net.csirmazbendeguz.memory_game.event.objects.CardFlipUpEvent;
import net.csirmazbendeguz.memory_game.event.objects.CardHideEvent;
import net.csirmazbendeguz.memory_game.event.objects.GameStartEvent;
import net.csirmazbendeguz.memory_game.event.objects.GameEndEvent;
import net.csirmazbendeguz.memory_game.util.random.RandomCardGenerator;

@Singleton
public class GameState implements CardHideListener, CardFlipUpListener {

    /**
     * The game board's dimension.
     */
    private int dimension;

    private Board board;

    private EventDispatcher eventDispatcher;

    private RandomCardGenerator randomCardGenerator;

    private Stopwatch stopwatch;

    private TriesCounter triesCounter;

    private boolean isWon;

    @Inject
    public GameState(EventDispatcher eventDispatcher, RandomCardGenerator randomCardGenerator, Stopwatch stopwatch, TriesCounter triesCounter, Board board) {
        this.eventDispatcher = eventDispatcher;
        this.randomCardGenerator = randomCardGenerator;
        this.stopwatch = stopwatch;
        this.triesCounter = triesCounter;
        this.board = board;
    }

    /**
     * Restart the game.
     */
    public void restartGame() {
        newGame(getDimension());
    }

    /**
     * Start a new game.
     *
     * @param dimension The new board's dimension.
     */
    public void newGame(int dimension) {
        isWon = false;
        this.dimension = dimension;
        stopwatch.reset();
        if (stopwatch.isRunning()) {
            stopwatch.stop();
        }
        triesCounter.reset();

        Card[][] cards = randomCardGenerator.generate(dimension);
        board.init(cards);
        eventDispatcher.dispatch(new GameStartEvent(this, dimension, cards));
    }

    /**
     * Move the game to the "won" state when the last card is hidden.
     */
    @Override
    public void cardHidden(CardHideEvent event) {
        if (board.areAllCardsHidden() && !isWon) {
            isWon = true;
            stopwatch.stop();
            eventDispatcher.dispatch(new GameEndEvent(
                this,
                getDimension(),
                stopwatch.getSeconds(),
                triesCounter.getTries()
            ));
        }
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

    /**
     * Get the current game board's dimension.
     */
    public int getDimension() {
        return dimension;
    }

}
