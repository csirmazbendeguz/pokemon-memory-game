package net.csirmazbendeguz.memory_game.util;

import com.google.inject.Inject;

import java.util.*;

public class RandomImageNameGenerator {

    private ResourceLoader resourceLoader;

    @Inject
    public RandomImageNameGenerator(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    /**
     * Randomly generate a list of card image names.
     *
     * @param count The number of card image names to generate.
     */
    public List<String> generate(int count) {
        List<String> imageNames = resourceLoader.getCardImageNames();
        Collections.shuffle(imageNames);
        return imageNames.subList(0, count);
    }

}
