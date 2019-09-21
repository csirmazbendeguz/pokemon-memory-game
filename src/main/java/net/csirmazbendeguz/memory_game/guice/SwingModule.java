package net.csirmazbendeguz.memory_game.guice;

import com.google.inject.AbstractModule;
import net.csirmazbendeguz.memory_game.swing.labels.TimeLabel;
import net.csirmazbendeguz.memory_game.swing.labels.TriesLabel;

/**
 * Module for binding Swing components.
 */
public class SwingModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(TimeLabel.class).asEagerSingleton();
        bind(TriesLabel.class).asEagerSingleton();
    }

}
