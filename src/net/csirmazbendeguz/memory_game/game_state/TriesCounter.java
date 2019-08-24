package net.csirmazbendeguz.memory_game.game_state;

import java.util.Observable;

public class TriesCounter extends Observable {

    private static TriesCounter instance;

    public static TriesCounter getInstance() {
        if (instance == null) {
            instance = new TriesCounter();
        }

        return instance;
    }

    private TriesCounter() {}

    private int tries;

    public int getTries() {
        return tries;
    }

    public void reset() {
        tries = 0;
        setChanged();
        notifyObservers(tries);
    }

    public void increase() {
        ++tries;
        setChanged();
        notifyObservers(tries);
    }

}
