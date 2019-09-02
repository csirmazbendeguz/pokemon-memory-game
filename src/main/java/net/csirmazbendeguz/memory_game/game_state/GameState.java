package net.csirmazbendeguz.memory_game.game_state;

import com.google.inject.Inject;
import net.csirmazbendeguz.memory_game.event.EventDispatcher;
import net.csirmazbendeguz.memory_game.event.listeners.CardFlipUpListener;
import net.csirmazbendeguz.memory_game.event.listeners.CardHideListener;
import net.csirmazbendeguz.memory_game.event.objects.CardFlipUpEvent;
import net.csirmazbendeguz.memory_game.event.objects.CardHideEvent;
import net.csirmazbendeguz.memory_game.event.objects.GameStartEvent;
import net.csirmazbendeguz.memory_game.event.objects.GameEndEvent;
import net.csirmazbendeguz.memory_game.util.RandomCardGenerator;
import net.csirmazbendeguz.memory_game.util.ResourceLoader;

public class GameState implements CardHideListener, CardFlipUpListener {

    /**
     * The game board's dimension.
     */
    private int dimension;

    private Board board;

    private EventDispatcher eventDispatcher;

    private RandomCardGenerator randomCardGenerator;

    private ResourceLoader resourceLoader;

    private Stopwatch stopwatch;

    private TriesCounter triesCounter;

    @Inject
    public GameState(ResourceLoader resourceLoader, EventDispatcher eventDispatcher, RandomCardGenerator randomCardGenerator, Stopwatch stopwatch, TriesCounter triesCounter, Board board) {
        this.resourceLoader = resourceLoader;
        this.eventDispatcher = eventDispatcher;
        this.randomCardGenerator = randomCardGenerator;
        this.stopwatch = stopwatch;
        this.triesCounter = triesCounter;
        this.board = board;
        eventDispatcher.addListener(CardHideEvent.class, this);
        eventDispatcher.addListener(CardFlipUpEvent.class, this);
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
        this.dimension = dimension;
        stopwatch.stopTimer();
        stopwatch.resetSeconds();
        triesCounter.reset();

        Card[][] cards = generateCards(randomCardGenerator.generateBoard(dimension));
        board.init(cards);
        eventDispatcher.dispatch(new GameStartEvent(this, dimension, cards));
    }

    private Card[][] generateCards(String[][] cardImageNames) {
        Card[][] cards = new Card[cardImageNames.length][cardImageNames.length];

        for (int i = 0; i < cardImageNames.length; ++i) {
            for (int j = 0; j < cardImageNames.length; ++j) {
                cards[i][j] = new Card(cardImageNames[i][j], resourceLoader, eventDispatcher);
            }
        }

        return cards;
    }

    /**
     * Move the game to the "won" state when the last card is hidden.
     */
    @Override
    public void cardHidden(CardHideEvent event) {
        if (board.isGameWon()) {
            stopwatch.stopTimer();
            eventDispatcher.dispatch(new GameEndEvent(
                this,
                getDimension(),
                stopwatch.getSeconds(),
                triesCounter.getTries()
            ));
        }
    }

    /**
     * Start the timer when the first card is flipped.
     */
    @Override
    public void cardFlippedUp(CardFlipUpEvent event) {
        // Start the timer if it's not already running.
        stopwatch.startTimer();
    }

    /**
     * Get the current game board's dimension.
     */
    public int getDimension() {
        return dimension;
    }

}
