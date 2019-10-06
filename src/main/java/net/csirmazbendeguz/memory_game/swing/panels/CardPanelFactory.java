package net.csirmazbendeguz.memory_game.swing.panels;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import net.csirmazbendeguz.memory_game.event.EventListeners;
import net.csirmazbendeguz.memory_game.state.Card;
import net.csirmazbendeguz.memory_game.state.factories.CardImageCache;

import java.awt.image.BufferedImage;

public class CardPanelFactory {

    private CardImageCache cardImageCache;

    private EventListeners eventListeners;

    private BufferedImage cardBack;

    @Inject
    public CardPanelFactory(CardImageCache cardImageCache, EventListeners eventListeners, @Named("cardBack") BufferedImage cardBack) {
        this.cardImageCache = cardImageCache;
        this.eventListeners = eventListeners;
        this.cardBack = cardBack;
    }

    public CardPanel create(Card card) {
        BufferedImage cardFront = cardImageCache.get(card.getImageName());
        return new CardPanel(card, cardFront, cardBack, eventListeners);
    }

}
