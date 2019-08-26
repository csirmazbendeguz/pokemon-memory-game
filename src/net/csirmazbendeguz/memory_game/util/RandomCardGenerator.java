package net.csirmazbendeguz.memory_game.util;

import net.csirmazbendeguz.memory_game.game_state.Card;

import java.util.*;

public class RandomCardGenerator {

    /**
     * Generate a random board of cards.
     *
     * @param dimension The board dimension (e.g. 4 for a 4x4 board).
     * @return The generated board.
     */
    public Card[][] generateBoard(int dimension) {
        Set<String> cardImageNames = getRandomCardImageNames(dimension * dimension / 2);

        List<String> cards = new ArrayList<>();
        cards.addAll(cardImageNames);
        cards.addAll(cardImageNames);
        Collections.shuffle(cards);

        Card[][] board = new Card[dimension][dimension];

        for (int i = 0; i < dimension; ++i) {
            for (int j = 0; j < dimension; ++j) {
                board[i][j] = new Card(cards.get(i * dimension + j));
            }
        }

        return board;
    }

    /**
     * Randomly select a given number of card image names.
     */
    private Set<String> getRandomCardImageNames(int count) {
        List<String> cardImageNames = ResourceLoader.getInstance().getCardImageNames();
        if (cardImageNames.size() < count) {
            throw new IllegalArgumentException("Failed to find enough cards.");
        }

        Set<String> randomCardImageNames = new HashSet<>();
        Random random = new Random();

        while (randomCardImageNames.size() < count) {
            int randomIndex = random.nextInt(cardImageNames.size());
            String randomCardImageName = cardImageNames.get(randomIndex);
            randomCardImageNames.add(randomCardImageName);
        }

        return randomCardImageNames;
    }

}
