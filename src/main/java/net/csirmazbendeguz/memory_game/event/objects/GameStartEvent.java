package net.csirmazbendeguz.memory_game.event.objects;

import net.csirmazbendeguz.memory_game.game_state.Board;

import java.util.EventObject;

/**
 * Event object for dispatching information about a new game.
 */
public class GameStartEvent extends EventObject {

    private Board board;

    public GameStartEvent(Object source, Board board) {
        super(source);
        this.board = board;
    }

    public Board getBoard() {
        return board;
    }

}
