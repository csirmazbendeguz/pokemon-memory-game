package net.csirmazbendeguz.memory_game.util.loaders;

import org.apache.commons.io.FilenameUtils;
import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;

import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ResourceLoader {

    /**
     * Load the PNG image filenames of the images/cards folder.
     *
     * @return The loaded filenames.
     */
    public Set<String> loadCardImageNames() {
        return new Reflections("images.cards", new ResourcesScanner())
            .getResources(Pattern.compile(".+\\.png$"))
            .stream()
            .map(FilenameUtils::getName)
            .collect(Collectors.toSet());
    }

}
