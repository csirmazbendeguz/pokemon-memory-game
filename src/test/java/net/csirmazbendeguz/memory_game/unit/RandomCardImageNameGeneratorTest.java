package net.csirmazbendeguz.memory_game.unit;

import net.csirmazbendeguz.memory_game.util.RandomCardImageNameGenerator;
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

@ExtendWith(MockitoExtension.class)
class RandomCardImageNameGeneratorTest {

    /**
     * The object to test.
     */
    private RandomCardImageNameGenerator generator;

    /**
     * The test card image names.
     */
    private List<String> cardImageNames = Arrays.asList(
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
        when(resourceLoader.getCardImageNames()).thenReturn(cardImageNames);
        generator = new RandomCardImageNameGenerator(resourceLoader);
    }

    /**
     * Test that card image names can be generated.
     */
    @Test
    void testGenerate() {
        Set<String> randomCardImageNames = generator.generate(2);
        assertEquals(2, randomCardImageNames.size());
        assertTrue(cardImageNames.containsAll(randomCardImageNames));
    }

    /**
     * Test that all card image names can be generated.
     */
    @Test
    void testGenerateAll() {
        Set<String> randomCardImageNames = generator.generate(3);
        assertEquals(3, randomCardImageNames.size());
        assertTrue(cardImageNames.containsAll(randomCardImageNames));
    }

    /**
     * Test that an empty set is generated when the count is zero.
     */
    @Test
    void testGenerateEmpty() {
        Set<String> randomCardImageNames = generator.generate(0);
        assertTrue(randomCardImageNames.isEmpty());
    }

    /**
     * Test that an exception is thrown when requesting too many card image names.
     */
    @Test
    void testGenerateTooMany() {
        assertThrows(IndexOutOfBoundsException.class, () -> generator.generate(4));
    }

    /**
     * Test that an exception is thrown when the count is negative.
     */
    @Test
    void testGenerateNegative() {
        assertThrows(IllegalArgumentException.class, () -> generator.generate(-1));
    }

    /**
     * Test that card image name pairs can be generated.
     */
    @Test
    void testGeneratePairs() {
        List<String> randomCardImageNamePairs = generator.generatePairs(3);
        assertEquals(6, randomCardImageNamePairs.size());
        assertTrue(cardImageNames.containsAll(randomCardImageNamePairs));
    }

}
