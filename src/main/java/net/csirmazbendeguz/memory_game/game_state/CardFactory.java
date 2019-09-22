package net.csirmazbendeguz.memory_game.game_state;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import net.csirmazbendeguz.memory_game.event.EventDispatcher;
import net.csirmazbendeguz.memory_game.util.ResourceLoader;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

@Singleton
public class CardFactory {

    private ResourceLoader resourceLoader;

    private EventDispatcher eventDispatcher;

    private Map<String, BufferedImage> loadedCardImages = new HashMap<>();

    private BufferedImage cardBack;

    @Inject
    public CardFactory(@Named("cardBack") BufferedImage cardBack, ResourceLoader resourceLoader, EventDispatcher eventDispatcher) {
        this.cardBack = cardBack;
        this.resourceLoader = resourceLoader;
        this.eventDispatcher = eventDispatcher;
    }

    public Card createCard(String imageName) {
        loadedCardImages.computeIfAbsent(imageName, resourceLoader::loadCardImage);
        return new Card(imageName, loadedCardImages.get(imageName), cardBack, eventDispatcher);
    }

}
