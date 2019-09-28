package net.csirmazbendeguz.memory_game.swing.panels;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import net.csirmazbendeguz.memory_game.event.EventDispatcher;
import net.csirmazbendeguz.memory_game.game_state.Card;
import net.csirmazbendeguz.memory_game.game_state.CardImageCache;

import java.awt.image.BufferedImage;

public class CardPanelFactory {

    private CardImageCache cardImageCache;

    private EventDispatcher eventDispatcher;

    private BufferedImage cardBack;

    @Inject
    public CardPanelFactory(CardImageCache cardImageCache, EventDispatcher eventDispatcher, @Named("cardBack") BufferedImage cardBack) {
        this.cardImageCache = cardImageCache;
        this.eventDispatcher = eventDispatcher;
        this.cardBack = cardBack;
    }

    public CardPanel create(Card card) {
        return new CardPanel(card, cardImageCache, cardBack, eventDispatcher);
    }

}
