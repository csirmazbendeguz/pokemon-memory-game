package net.csirmazbendeguz.memory_game.game_state;

import java.util.Observable;

/**
 * Service for managing the number of tries.
 */
public class TriesCounter extends Observable {

    /**
     * The number of tries.
     */
    private int tries;

    /**
     * Return the number of tries.
     */
    public int getTries() {
        return tries;
    }

    /**
     * Reset the number of tries to zero.
     */
    public void reset() {
        tries = 0;
        setChanged();
        notifyObservers(tries);
    }

    /**
     * Increment the number of tries by one.
     */
    public void increment() {
        ++tries;
        setChanged();
        notifyObservers(tries);
    }

}
