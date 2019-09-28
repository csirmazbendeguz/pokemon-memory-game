package net.csirmazbendeguz.memory_game.game_state;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import net.csirmazbendeguz.memory_game.event.EventDispatcher;

@Singleton
public class CardFactory {

    private EventDispatcher eventDispatcher;

    @Inject
    public CardFactory(EventDispatcher eventDispatcher) {
        this.eventDispatcher = eventDispatcher;
    }

    public Card create(String imageName) {
        return new Card(imageName, eventDispatcher);
    }

}
