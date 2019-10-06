package net.csirmazbendeguz.memory_game.state;

import com.google.inject.Singleton;
import net.csirmazbendeguz.memory_game.event.listeners.GameStartListener;
import net.csirmazbendeguz.memory_game.event.objects.GameStartEvent;

import java.util.Observable;

@Singleton
public class TriesCounter extends Observable implements GameStartListener {

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

    @Override
    public void gameStarted(GameStartEvent event) {
        reset();
    }

}
