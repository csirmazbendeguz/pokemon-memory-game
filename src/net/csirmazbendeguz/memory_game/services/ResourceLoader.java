package net.csirmazbendeguz.memory_game.services;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.*;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Service to load resources.
 */
public class ResourceLoader {

    /**
     * The resource loader.
     */
    private static ResourceLoader instance;

    /**
     * Get the resource loader.
     *
     * @return The resource loader.
     */
    public static ResourceLoader getInstance() {
        if (instance == null) {
            instance = new ResourceLoader();
        }

        return instance;
    }

    /**
     * Load a card image.
     *
     * @param card The card image to load.
     * @return The loaded card image.
     */
    public BufferedImage loadCardImage(String card) {
        try {
            return loadImage("img/cards/" + card);
        } catch (IOException exception) {
            throw new RuntimeException(String.format("Card image '%s' not found.", card));
        }
    }

    /**
     * Load a background image.
     *
     * @param background The background image to load.
     * @return The loaded background image.
     */
    public BufferedImage loadBackogroundImage(String background) {
        try {
            return loadImage("img/bg/" + background);
        } catch (IOException exception) {
            throw new RuntimeException(String.format("Background image '%s' not found.", background));
        }
    }

    /**
     * Load an image.
     *
     * @param image The image to load.
     * @return The loaded image.
     * @throws IOException If the image is not found.
     */
    private BufferedImage loadImage(String image) throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream imageStream = classLoader.getResourceAsStream(image);
        return ImageIO.read(imageStream);
    }

    /**
     * Get the card image names.
     *
     * @return The card list.
     */
    public List<String> getCardList() throws Exception {
        URI uri = getClass().getClassLoader().getResource("img/cards").toURI();
        Path path;

        if (uri.getScheme().equals("jar")) {
            FileSystem fileSystem = FileSystems.newFileSystem(uri, Collections.<String, Object>emptyMap());
            path = fileSystem.getPath("img/cards");
        } else {
            path = Paths.get(uri);
        }

        try (Stream<Path> paths = Files.walk(path, 1)) {
            return paths.filter(Files::isRegularFile)
                .map(Path::getFileName)
                .map(Path::toString)
                .collect(Collectors.toList());
        }
    }

}
