package net.csirmazbendeguz.memory_game.unit;

import net.csirmazbendeguz.memory_game.util.RandomImageNameGenerator;
import net.csirmazbendeguz.memory_game.util.ResourceLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RandomImageNameGeneratorTest {

    /**
     * The object to test.
     */
    private RandomImageNameGenerator randomImageNameGenerator;

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
        randomImageNameGenerator = new RandomImageNameGenerator(resourceLoader);
    }

    /**
     * Test that all image names can be successfully selected.
     */
    @Test
    void testAll() {
        List<String> randomImageNames = randomImageNameGenerator.generate(3);
        assertTrue(randomImageNames.containsAll(imageNames));
    }

    /**
     * Test that fewer image names can be successfully selected.
     */
    @Test
    void testFewer() {
        List<String> randomImageNames = randomImageNameGenerator.generate(2);
        assertEquals(2, randomImageNames.size());
        assertTrue(imageNames.containsAll(randomImageNames));
    }

    /**
     * Test that an empty list is returned when the count is zero.
     */
    @Test
    void testNone() {
        List<String> randomImageNames = randomImageNameGenerator.generate(0);
        assertTrue(randomImageNames.isEmpty());
    }

    /**
     * Test that an exception is thrown when requesting too many image names.
     */
    @Test
    void testTooMany() {
        assertThrows(IndexOutOfBoundsException.class, () -> randomImageNameGenerator.generate(4));
    }

    /**
     * Test that an exception is thrown when the count is negative.
     */
    @Test
    void testNegative() {
        assertThrows(IllegalArgumentException.class, () -> randomImageNameGenerator.generate(-1));
    }

}
