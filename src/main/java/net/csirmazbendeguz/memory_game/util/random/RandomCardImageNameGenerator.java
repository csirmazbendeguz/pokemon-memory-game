package net.csirmazbendeguz.memory_game.util.random;

import com.google.inject.Inject;
import net.csirmazbendeguz.memory_game.util.loaders.ResourceLoader;

import java.util.*;

public class RandomCardImageNameGenerator {

    private ResourceLoader resourceLoader;

    @Inject
    public RandomCardImageNameGenerator(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    /**
     * Generate a random list of card image name pairs.
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
     * Generate a random set of card image names.
     *
     * @param count The number of card image names to generate.
     */
    public Set<String> generate(int count) {
        List<String> cardImageNames = resourceLoader.getCardImageNames();
        Collections.shuffle(cardImageNames);

        return new HashSet<>(cardImageNames.subList(0, count));
    }

}
