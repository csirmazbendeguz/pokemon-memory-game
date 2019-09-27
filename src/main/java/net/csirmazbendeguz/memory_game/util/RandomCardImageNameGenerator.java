package net.csirmazbendeguz.memory_game.util;

import com.google.inject.Inject;

import java.util.*;

public class RandomCardImageNameGenerator {

    private ResourceLoader resourceLoader;

    @Inject
    public RandomCardImageNameGenerator(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    /**
     * Generate a list of card image name pairs.
     *
     * @param count The number of card image name pairs to generate.
     */
    public List<String> generatePairs(int count) {
        Set<String> randomCardImageNames = generate(count);

        List<String> pairs = new ArrayList<>();
        pairs.addAll(randomCardImageNames);
        pairs.addAll(randomCardImageNames);
        Collections.shuffle(pairs);

        return pairs;
    }

    /**
     * Generate a set of card image names.
     *
     * @param count The number of card image names to generate.
     */
    public Set<String> generate(int count) {
        List<String> cardImageNames = resourceLoader.getCardImageNames();
        Collections.shuffle(cardImageNames);

        return new HashSet<>(cardImageNames.subList(0, count));
    }

}
