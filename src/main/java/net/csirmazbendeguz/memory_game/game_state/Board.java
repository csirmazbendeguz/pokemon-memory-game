package net.csirmazbendeguz.memory_game.game_state;

import com.google.inject.Singleton;

/**
 * The board state.
 */
@Singleton
public class Board {

    /**
     * The memory card board.
     */
    private Card[][] board;

    /**
     * Initialize the board.
     */
    void init(Card[][] board) {
        this.board = board;
    }

    /**
     * Check if all the cards are hidden.
     */
    boolean areAllCardsHidden() {
        for (Card[] cards : board) {
            for (Card card : cards) {
                if (card.isVisible()) {
                    return false;
                }
            }
        }

        return true;
    }

}
