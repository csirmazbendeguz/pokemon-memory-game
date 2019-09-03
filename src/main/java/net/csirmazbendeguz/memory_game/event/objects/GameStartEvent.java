package net.csirmazbendeguz.memory_game.event.objects;

import net.csirmazbendeguz.memory_game.game_state.Card;

import java.util.EventObject;

/**
 * Event object for dispatching information about a new game.
 */
public class GameStartEvent extends EventObject {

    private int dimension;

    private Card[][] board;

    public GameStartEvent(Object source, int dimension, Card[][] board) {
        super(source);
        this.dimension = dimension;
        this.board = board;
    }

    public int getDimension() {
        return dimension;
    }

    public Card[][] getBoard() {
        return board;
    }

}
