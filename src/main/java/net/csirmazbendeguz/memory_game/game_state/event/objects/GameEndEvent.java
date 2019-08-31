package net.csirmazbendeguz.memory_game.game_state.event.objects;

import java.util.EventObject;

/**
 * Event object for dispatching information about a finished game.
 */
public class GameEndEvent extends EventObject {

    /**
     * The game board's dimension.
     */
    private int dimension;

    /**
     * The number of seconds it took to finish the game.
     */
    private int seconds;

    /**
     * The number of tries it took to finish the game.
     */
    private int tries;

    public GameEndEvent(Object source, int dimension, int seconds, int tries) {
        super(source);
        this.dimension = dimension;
        this.seconds = seconds;
        this.tries = tries;
    }

    public int getDimension() {
        return dimension;
    }

    public int getSeconds() {
        return seconds;
    }

    public int getTries() {
        return tries;
    }

}
