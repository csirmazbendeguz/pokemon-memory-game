package net.csirmazbendeguz.memory_game.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import net.csirmazbendeguz.memory_game.util.ResourceLoader;

import java.awt.image.BufferedImage;

/**
 * Module for binding Swing components.
 */
public class SwingModule extends AbstractModule {

    @Override
    protected void configure() {
    }

    @Provides
    @Named("labelBackground")
    BufferedImage labelBackground(ResourceLoader resourceLoader) {
        return resourceLoader.loadBackogroundImage("Label-Bg.png");
    }

}
