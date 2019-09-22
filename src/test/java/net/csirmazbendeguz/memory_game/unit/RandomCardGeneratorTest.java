package net.csirmazbendeguz.memory_game.unit;

import net.csirmazbendeguz.memory_game.util.RandomCardGenerator;
import net.csirmazbendeguz.memory_game.util.ResourceLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Tests for selecting random image names.
 *
 * @see net.csirmazbendeguz.memory_game.util.RandomCardGenerator#getRandomImageNames
 */
@ExtendWith(MockitoExtension.class)
class GetRandomImageNamesTest {

    /**
     * The object to test.
     */
    private RandomCardGenerator randomCardGenerator;

    /**
     * The test image names.
     */
    private List<String> imageNames = Arrays.asList(
        "bulbasaur.png",
        "charmander.png",
        "pikachu.png"
    );

    /**
     * Set up the test fixture.
     */
    @BeforeEach
    void setup() {
        ResourceLoader resourceLoader = mock(ResourceLoader.class);
        when(resourceLoader.getCardImageNames()).thenReturn(imageNames);
        randomCardGenerator = new RandomCardGenerator(resourceLoader, null);
    }

    /**
     * Test that all images can be successfully selected.
     */
    @Test
    void testAll() {
        Set<String> randomImageNames = randomCardGenerator.getRandomImageNames(3);
        assertTrue(randomImageNames.containsAll(imageNames));
    }

    /**
     * Test that fewer images can be successfully selected.
     */
    @Test
    void testFewer() {
        Set<String> randomImageNames = randomCardGenerator.getRandomImageNames(2);
        assertTrue(imageNames.containsAll(randomImageNames));
    }

    /**
     * Test that an empty set is returned when the count is zero.
     */
    @Test
    void testNone() {
        Set<String> randomImageNames = randomCardGenerator.getRandomImageNames(0);
        assertTrue(randomImageNames.isEmpty());
    }

    /**
     * Test that an exception is thrown when requesting too many images.
     */
    @Test
    void testTooMany() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
            randomCardGenerator.getRandomImageNames(4));
        assertEquals("Failed to find enough images.", exception.getMessage());
    }

}

/**
 * Tests for selecting random image names without mocking any dependencies.
 *
 * @see net.csirmazbendeguz.memory_game.util.RandomCardGenerator#getRandomImageNames
 */
class GetRandomImageNamesNoMockTest {

    /**
     * Test that an exception is thrown when the count is negative.
     */
    @Test
    void testNegative() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
            new RandomCardGenerator(null, null).getRandomImageNames(-1));
        assertEquals(exception.getMessage(), "Count can't be negative.");
    }

}
