package net.csirmazbendeguz.memory_game.swing.panels;

import com.google.inject.Inject;
import net.csirmazbendeguz.memory_game.event.EventDispatcher;
import net.csirmazbendeguz.memory_game.game_state.Card;
import net.csirmazbendeguz.memory_game.game_state.CardImageCache;

public class CardPanelFactory {

    private CardImageCache cardImageCache;

    private EventDispatcher eventDispatcher;

    @Inject
    public CardPanelFactory(CardImageCache cardImageCache, EventDispatcher eventDispatcher) {
        this.cardImageCache = cardImageCache;
        this.eventDispatcher = eventDispatcher;
    }

    public CardPanel create(Card card) {
        return new CardPanel(card, cardImageCache, eventDispatcher);
    }

}
