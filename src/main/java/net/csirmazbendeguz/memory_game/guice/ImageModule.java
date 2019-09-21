package net.csirmazbendeguz.memory_game.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import net.csirmazbendeguz.memory_game.util.ResourceLoader;

import java.awt.image.BufferedImage;

/**
 * Module for loading images.
 */
class ImageModule extends AbstractModule {

    @Provides
    @Singleton
    @Named("labelBackground")
    BufferedImage provideLabelBackground(ResourceLoader resourceLoader) {
        return resourceLoader.loadBackogroundImage("labelBackground.png");
    }

    @Provides
    @Singleton
    @Named("buttonBackground")
    BufferedImage provideButtonBackground(ResourceLoader resourceLoader) {
        return resourceLoader.loadBackogroundImage("buttonBackground.png");
    }

    @Provides
    @Singleton
    @Named("buttonBackgroundHover")
    BufferedImage provideButtonBackgroundHover(ResourceLoader resourceLoader) {
        return resourceLoader.loadBackogroundImage("buttonBackgroundHover.png");
    }

    @Provides
    @Singleton
    @Named("buttonBackgroundClick")
    BufferedImage provideButtonBackgroundClick(ResourceLoader resourceLoader) {
        return resourceLoader.loadBackogroundImage("buttonBackgroundClick.png");
    }

    @Provides
    @Singleton
    @Named("background")
    BufferedImage provideBackground(ResourceLoader resourceLoader) {
        return resourceLoader.loadBackogroundImage("background.jpg");
    }

    @Provides
    @Singleton
    @Named("winScreen")
    BufferedImage provideWinScreen(ResourceLoader resourceLoader) {
        return resourceLoader.loadBackogroundImage("winScreen.png");
    }

    @Provides
    @Singleton
    @Named("boardBackground")
    BufferedImage provideBoardBackground(ResourceLoader resourceLoader) {
        return resourceLoader.loadBackogroundImage("boardBackground.png");
    }

    @Provides
    @Singleton
    @Named("cardBack")
    BufferedImage provideCardBack(ResourceLoader resourceLoader) {
        return resourceLoader.loadBackogroundImage("cardBack.png");
    }

}
