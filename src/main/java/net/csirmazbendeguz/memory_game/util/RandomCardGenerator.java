package net.csirmazbendeguz.memory_game.util;

import com.google.inject.Inject;
import net.csirmazbendeguz.memory_game.game_state.Card;
import net.csirmazbendeguz.memory_game.game_state.CardFactory;

import java.util.*;

/**
 * Service for generating the memory game cards.
 */
public class RandomCardGenerator {

    private RandomCardImageNameGenerator randomCardImageNameGenerator;

    private CardFactory cardFactory;

    @Inject
    public RandomCardGenerator(RandomCardImageNameGenerator randomCardImageNameGenerator, CardFactory cardFactory) {
        this.randomCardImageNameGenerator = randomCardImageNameGenerator;
        this.cardFactory = cardFactory;
    }

    /**
     * Generate a random board of cards.
     *
     * @param dimension The board dimension (e.g. 4 for a 4x4 board).
     * @return The generated board of cards.
     */
    public Card[][] generate(int dimension) {
        List<String> pairs = randomCardImageNameGenerator.generatePairs(dimension * dimension / 2);
        Card[][] cards = new Card[dimension][dimension];

        for (int i = 0; i < dimension; ++i) {
            for (int j = 0; j < dimension; ++j) {
                cards[i][j] = cardFactory.createCard(pairs.get(i * dimension + j));
            }
        }

        return cards;
    }

}
