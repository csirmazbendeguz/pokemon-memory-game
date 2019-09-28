package net.csirmazbendeguz.memory_game.game_state;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import net.csirmazbendeguz.memory_game.event.EventDispatcher;

@Singleton
public class CardFactory {

    private TimerFactory timerFactory;

    private EventDispatcher eventDispatcher;

    @Inject
    public CardFactory(TimerFactory timerFactory, EventDispatcher eventDispatcher) {
        this.timerFactory = timerFactory;
        this.eventDispatcher = eventDispatcher;
    }

    public Card create(String imageName) {
        return new Card(imageName, timerFactory, eventDispatcher);
    }

}
