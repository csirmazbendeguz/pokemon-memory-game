package net.csirmazbendeguz.memory_game.util.loaders;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class ImageLoader {

    /**
     * Load a card image.
     *
     * @param name The card image's name.
     * @return The loaded card image.
     * @throws RuntimeException If the card image is not found.
     */
    public BufferedImage loadCardImage(String name) {
        try {
            return loadImage("images/cards/" + name);
        } catch (IOException exception) {
            throw new RuntimeException(String.format("Card image '%s' not found.", name), exception);
        }
    }

    /**
     * Load a background image.
     *
     * @param name The background image's name.
     * @return The loaded background image.
     * @throws RuntimeException If the background image is not found.
     */
    public BufferedImage loadBackogroundImage(String name) {
        try {
            return loadImage("images/backgrounds/" + name);
        } catch (IOException exception) {
            throw new RuntimeException(String.format("Background image '%s' not found.", name), exception);
        }
    }

    /**
     * Load an image.
     *
     * @param name The image resource's name.
     * @return The loaded image.
     * @throws IOException If the image is not found.
     */
    private BufferedImage loadImage(String name) throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream imageStream = classLoader.getResourceAsStream(name);
        return ImageIO.read(imageStream);
    }

}
