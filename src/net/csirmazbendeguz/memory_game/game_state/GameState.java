package net.csirmazbendeguz.memory_game.game_state;

import java.util.Observable;

public class GameState extends Observable {

    private static GameState instance;

    public static GameState getInstance() {
        if (instance == null) {
            instance = new GameState();
        }

        return instance;
    }

    private GameState() {}

    public void checkWin() {
        if (Board.getInstance().isGameWon()) {
            setChanged();
            notifyObservers();
        }
    }

}
