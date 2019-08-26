package net.csirmazbendeguz.memory_game.game_state.event.objects;

import net.csirmazbendeguz.memory_game.game_state.Board;

import java.util.EventObject;

public class NewGameEvent extends EventObject {

    private Board board;

    public NewGameEvent(Object source, Board board) {
        super(source);
        this.board = board;
    }

    public Board getBoard() {
        return board;
    }

}
