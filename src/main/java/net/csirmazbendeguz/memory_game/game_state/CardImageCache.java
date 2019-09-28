package net.csirmazbendeguz.memory_game.game_state;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import net.csirmazbendeguz.memory_game.util.loaders.ImageLoader;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

@Singleton
public class CardImageCache {

    private ImageLoader imageLoader;

    private Map<String, BufferedImage> cache = new HashMap<>();

    @Inject
    public CardImageCache(ImageLoader imageLoader) {
        this.imageLoader = imageLoader;
    }

    /**
     * Load a card image into memory and cache it.
     *
     * @param name The card image's name.
     * @return The cached card image.
     */
    public BufferedImage get(String name) {
        return cache.computeIfAbsent(name, imageLoader::loadCardImage);
    }

}
