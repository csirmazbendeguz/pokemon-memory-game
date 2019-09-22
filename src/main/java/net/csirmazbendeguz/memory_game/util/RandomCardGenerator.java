package net.csirmazbendeguz.memory_game.util;

import com.google.inject.Inject;
import net.csirmazbendeguz.memory_game.game_state.Card;
import net.csirmazbendeguz.memory_game.game_state.CardFactory;

import java.util.*;

/**
 * Service for generating the memory game cards.
 */
public class RandomCardGenerator {

    /**
     * The resource loader.
     */
    private ResourceLoader resourceLoader;

    private CardFactory cardFactory;

    @Inject
    public RandomCardGenerator(ResourceLoader resourceLoader, CardFactory cardFactory) {
        this.resourceLoader = resourceLoader;
        this.cardFactory = cardFactory;
    }

    /**
     * Generate a random board of card image names.
     *
     * @param dimension The board dimension (e.g. 4 for a 4x4 board).
     * @return The generated board of card image names.
     */
    public Card[][] generate(int dimension) {
        Set<String> randomImageNames = getRandomImageNames(dimension * dimension / 2);

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

    /**
     * Randomly select a set of card image names.
     *
     * @param count The number of card image names to select.
     * @throws IllegalArgumentException If there are not enough images.
     */
    public Set<String> getRandomImageNames(int count) {
        List<String> imageNames = resourceLoader.getCardImageNames();

        if (imageNames.size() < count) {
            throw new IllegalArgumentException("Failed to find enough images.");
        }

        Set<String> randomImageNames = new HashSet<>();
        Random random = new Random();

        while (randomImageNames.size() < count) {
            int randomIndex = random.nextInt(imageNames.size());
            String randomImageName = imageNames.get(randomIndex);
            randomImageNames.add(randomImageName);
        }

        return randomImageNames;
    }

}
