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
    BufferedImage provideLabelBackground(ResourceLoader resourceLoader) {
        return resourceLoader.loadBackogroundImage("Label-Bg.png");
    }

    @Provides
    @Singleton
    @Named("buttonBackground")
    BufferedImage provideButtonBackground(ResourceLoader resourceLoader) {
        return resourceLoader.loadBackogroundImage("Button.png");
    }

    @Provides
    @Singleton
    @Named("buttonBackgroundHover")
    BufferedImage provideButtonBackgroundHover(ResourceLoader resourceLoader) {
        return resourceLoader.loadBackogroundImage("ButtonHover.png");
    }

    @Provides
    @Singleton
    @Named("buttonBackgroundClick")
    BufferedImage provideButtonBackgroundClick(ResourceLoader resourceLoader) {
        return resourceLoader.loadBackogroundImage("ButtonClick.png");
    }

    @Provides
    @Singleton
    @Named("background")
    BufferedImage provideBackground(ResourceLoader resourceLoader) {
        return resourceLoader.loadBackogroundImage("Background.jpg");
    }

}
