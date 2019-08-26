package net.csirmazbendeguz.memory_game.game_state.event.objects;

import java.util.EventObject;

public class WinEvent extends EventObject {

    private int dimension;

    private long seconds;

    private int tries;

    public WinEvent(Object source, int dimension, long seconds, int tries) {
        super(source);
        this.dimension = dimension;
        this.seconds = seconds;
        this.tries = tries;
    }

    public int getDimension() {
        return dimension;
    }

    public long getSeconds() {
        return seconds;
    }

    public int getTries() {
        return tries;
    }

}
