package net.csirmazbendeguz.memory_game.game_state;

import com.google.inject.Inject;
import net.csirmazbendeguz.memory_game.event.EventDispatcher;
import net.csirmazbendeguz.memory_game.event.listeners.CardHideListener;
import net.csirmazbendeguz.memory_game.event.objects.CardHideEvent;
import net.csirmazbendeguz.memory_game.event.objects.GameStartEvent;
import net.csirmazbendeguz.memory_game.event.objects.GameEndEvent;
import net.csirmazbendeguz.memory_game.util.RandomCardGenerator;
import net.csirmazbendeguz.memory_game.util.ResourceLoader;

public class GameState implements CardHideListener {

    private Board board;

    private EventDispatcher eventDispatcher;

    private RandomCardGenerator randomCardGenerator;

    private ResourceLoader resourceLoader;

    private Stopwatch stopwatch;

    private TriesCounter triesCounter;

    @Inject
    public GameState(ResourceLoader resourceLoader, EventDispatcher eventDispatcher, RandomCardGenerator randomCardGenerator, Stopwatch stopwatch, TriesCounter triesCounter) {
        this.resourceLoader = resourceLoader;
        this.eventDispatcher = eventDispatcher;
        this.randomCardGenerator = randomCardGenerator;
        eventDispatcher.addListener(CardHideEvent.class, this);
        this.stopwatch = stopwatch;
        this.triesCounter = triesCounter;
    }

    public void restartGame() {
        newGame(board.getDimension());
    }

    public void newGame(int dimension) {
        stopwatch.stopTimer();
        stopwatch.resetSeconds();
        triesCounter.reset();

        board = new Board(dimension, generateCards(randomCardGenerator.generateBoard(dimension)), triesCounter);
        eventDispatcher.dispatch(new GameStartEvent(this, board));
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

    public Board getBoard() {
        return board;
    }

    /**
     * Check for win.
     */
    @Override
    public void cardHidden(CardHideEvent event) {
        if (board.isGameWon()) {
            stopwatch.stopTimer();
            eventDispatcher.dispatch(new GameEndEvent(
                this,
                board.getDimension(),
                stopwatch.getSeconds(),
                triesCounter.getTries()
            ));
        }
    }

}
