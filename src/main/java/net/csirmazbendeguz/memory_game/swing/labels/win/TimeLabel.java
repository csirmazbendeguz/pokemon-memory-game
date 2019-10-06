package net.csirmazbendeguz.memory_game.swing.labels.win;

import com.google.inject.Singleton;
import net.csirmazbendeguz.memory_game.event.listeners.GameEndListener;
import net.csirmazbendeguz.memory_game.event.objects.GameEndEvent;

@Singleton
public class TimeLabel extends BaseLabel implements GameEndListener {

    @Override
    public void gameEnded(GameEndEvent event) {
        setText(String.format("Time: %s seconds", event.getSeconds()));
    }

}
