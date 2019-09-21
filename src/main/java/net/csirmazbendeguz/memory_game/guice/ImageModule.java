package net.csirmazbendeguz.memory_game.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import net.csirmazbendeguz.memory_game.util.ResourceLoader;

import java.awt.image.BufferedImage;

/**
 * Module for binding images.
 */
class ImageModule extends AbstractModule {

    @Provides
    @Singleton
    @Named("labelBackground")
    BufferedImage labelBackground(ResourceLoader resourceLoader) {
        return resourceLoader.loadBackogroundImage("Label-Bg.png");
    }

    @Provides
    @Singleton
    @Named("buttonBackground")
    BufferedImage buttonBackground(ResourceLoader resourceLoader) {
        return resourceLoader.loadBackogroundImage("Button.png");
    }

    @Provides
    @Singleton
    @Named("buttonBackgroundHover")
    BufferedImage buttonBackgroundHover(ResourceLoader resourceLoader) {
        return resourceLoader.loadBackogroundImage("ButtonHover.png");
    }

    @Provides
    @Singleton
    @Named("buttonBackgroundClick")
    BufferedImage buttonBackgroundClick(ResourceLoader resourceLoader) {
        return resourceLoader.loadBackogroundImage("ButtonClick.png");
    }

}
