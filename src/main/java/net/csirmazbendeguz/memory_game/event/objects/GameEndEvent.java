package net.csirmazbendeguz.memory_game.event.objects;

import net.csirmazbendeguz.memory_game.event.Listener;
import net.csirmazbendeguz.memory_game.event.listeners.GameEndListener;

import java.util.EventObject;

@Listener(GameEndListener.class)
public class GameEndEvent extends EventObject {

    /**
     * The finished game's board dimension.
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
