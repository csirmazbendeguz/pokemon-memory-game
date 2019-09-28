package net.csirmazbendeguz.memory_game.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import net.csirmazbendeguz.memory_game.util.loaders.ImageLoader;

import java.awt.image.BufferedImage;

/**
 * Module for loading images.
 */
class ImageModule extends AbstractModule {

    @Provides
    @Singleton
    @Named("labelBackground")
    BufferedImage provideLabelBackground(ImageLoader imageLoader) {
        return imageLoader.loadBackogroundImage("labelBackground.png");
    }

    @Provides
    @Singleton
    @Named("buttonBackground")
    BufferedImage provideButtonBackground(ImageLoader imageLoader) {
        return imageLoader.loadBackogroundImage("buttonBackground.png");
    }

    @Provides
    @Singleton
    @Named("buttonBackgroundHover")
    BufferedImage provideButtonBackgroundHover(ImageLoader imageLoader) {
        return imageLoader.loadBackogroundImage("buttonBackgroundHover.png");
    }

    @Provides
    @Singleton
    @Named("buttonBackgroundClick")
    BufferedImage provideButtonBackgroundClick(ImageLoader imageLoader) {
        return imageLoader.loadBackogroundImage("buttonBackgroundClick.png");
    }

    @Provides
    @Singleton
    @Named("background")
    BufferedImage provideBackground(ImageLoader imageLoader) {
        return imageLoader.loadBackogroundImage("background.png");
    }

    @Provides
    @Singleton
    @Named("winScreen")
    BufferedImage provideWinScreen(ImageLoader imageLoader) {
        return imageLoader.loadBackogroundImage("winScreen.png");
    }

    @Provides
    @Singleton
    @Named("boardBackground")
    BufferedImage provideBoardBackground(ImageLoader imageLoader) {
        return imageLoader.loadBackogroundImage("boardBackground.png");
    }

    @Provides
    @Singleton
    @Named("cardBack")
    BufferedImage provideCardBack(ImageLoader imageLoader) {
        return imageLoader.loadBackogroundImage("cardBack.png");
    }

    @Provides
    @Singleton
    @Named("title")
    BufferedImage provideTitle(ImageLoader imageLoader) {
        return imageLoader.loadBackogroundImage("title.png");
    }

    @Provides
    @Singleton
    @Named("backgroundFigure")
    BufferedImage provideBackgroundFigure(ImageLoader imageLoader) {
        return imageLoader.loadBackogroundImage("backgroundFigure.png");
    }

}
