package net.csirmazbendeguz.memory_game.util;

import com.google.inject.Inject;
import net.csirmazbendeguz.memory_game.game_state.Card;
import net.csirmazbendeguz.memory_game.game_state.CardFactory;

import java.util.*;

/**
 * Service for generating the memory game cards.
 */
public class RandomCardGenerator {

    private RandomImageNameGenerator randomImageNameGenerator;

    private CardFactory cardFactory;

    @Inject
    public RandomCardGenerator(RandomImageNameGenerator randomImageNameGenerator, CardFactory cardFactory) {
        this.randomImageNameGenerator = randomImageNameGenerator;
        this.cardFactory = cardFactory;
    }

    /**
     * Generate a random board of card image names.
     *
     * @param dimension The board dimension (e.g. 4 for a 4x4 board).
     * @return The generated board of card image names.
     */
    public Card[][] generate(int dimension) {
        List<String> randomImageNames = randomImageNameGenerator.generate(dimension * dimension / 2);

        List<String> pairs = new ArrayList<>();
        pairs.addAll(randomImageNames);
        pairs.addAll(randomImageNames);
        Collections.shuffle(pairs);

        Card[][] cards = new Card[dimension][dimension];

        for (int i = 0; i < dimension; ++i) {
            for (int j = 0; j < dimension; ++j) {
                cards[i][j] = cardFactory.createCard(pairs.get(i * dimension + j));
            }
        }

        return cards;
    }

}
