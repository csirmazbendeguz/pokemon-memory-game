package net.csirmazbendeguz.memory_game.game_state;

import net.csirmazbendeguz.memory_game.game_state.event.EventDispatcher;
import net.csirmazbendeguz.memory_game.game_state.event.objects.NewGameEvent;
import net.csirmazbendeguz.memory_game.game_state.event.objects.WinEvent;

public class GameState {

    private Board board;

    private EventDispatcher eventDispatcher;

    public GameState(EventDispatcher eventDispatcher) {
        this.eventDispatcher = eventDispatcher;
    }

    void checkWin() {
        if (board.isGameWon()) {
            Stopwatch stopwatch = Stopwatch.getInstance();
            stopwatch.stopTimer();

            eventDispatcher.dispatch(new WinEvent(this, board.getDimension(), stopwatch.getSeconds(), TriesCounter.getInstance().getTries()));
        }
    }

    public void restartGame() {
        newGame(board.getDimension());
    }

    public void newGame(int dimension) {
        Stopwatch stopwatch = Stopwatch.getInstance();
        stopwatch.stopTimer();
        stopwatch.resetSeconds();
        TriesCounter.getInstance().reset();

        board = new Board(dimension);
        eventDispatcher.dispatch(new NewGameEvent(this, board));
    }

    public Board getBoard() {
        return board;
    }

}
