package net.csirmazbendeguz.memory_game.unit.state;

import net.csirmazbendeguz.memory_game.state.factories.CardImageCache;
import net.csirmazbendeguz.memory_game.util.loaders.ImageLoader;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CardImageCacheTest {

    @Test
    void testGet() {
        ImageLoader mockImageLoader = mock(ImageLoader.class);
        BufferedImage mockCardImage = mock(BufferedImage.class);
        when(mockImageLoader.loadCardImage("bulbasaur.png")).thenReturn(mockCardImage);
        CardImageCache cache = new CardImageCache(mockImageLoader);
        assertSame(mockCardImage, cache.get("bulbasaur.png"));
        assertSame(mockCardImage, cache.get("bulbasaur.png"));
        verify(mockImageLoader, times(1)).loadCardImage(anyString());
    }

}
