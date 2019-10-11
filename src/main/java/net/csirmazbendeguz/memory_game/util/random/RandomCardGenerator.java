package net.csirmazbendeguz.memory_game.util.random;

import com.google.inject.Inject;
import net.csirmazbendeguz.memory_game.state.Card;
import net.csirmazbendeguz.memory_game.util.CardFactory;

import java.util.*;

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
     * @return The generated board.
     */
    public Card[][] generate(int dimension) {
        List<String> pairs = randomCardImageNameGenerator.generatePairs(dimension * dimension / 2);
        Card[][] cards = new Card[dimension][dimension];

        for (int i = 0; i < dimension; ++i) {
            for (int j = 0; j < dimension; ++j) {
                cards[i][j] = cardFactory.create(pairs.get(i * dimension + j));
            }
        }

        return cards;
    }

}
