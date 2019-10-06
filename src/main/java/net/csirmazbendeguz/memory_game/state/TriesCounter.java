package net.csirmazbendeguz.memory_game.state;

import com.google.inject.Singleton;

import java.util.Observable;

@Singleton
public class TriesCounter extends Observable {

    /**
     * The number of tries.
     */
    private int tries;

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
