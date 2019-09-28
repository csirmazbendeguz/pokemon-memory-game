package net.csirmazbendeguz.memory_game.unit;

import net.csirmazbendeguz.memory_game.util.RandomCardImageNameGenerator;
import net.csirmazbendeguz.memory_game.util.ResourceLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RandomCardImageNameGeneratorTest {

    private RandomCardImageNameGenerator generator;

    /**
     * The mocked card image names.
     */
    private List<String> cardImageNames = Arrays.asList(
        "bulbasaur.png",
        "charmander.png",
        "pikachu.png"
    );

    @BeforeEach
    void setup() {
        ResourceLoader resourceLoader = mock(ResourceLoader.class);
        when(resourceLoader.getCardImageNames()).thenReturn(cardImageNames);
        generator = new RandomCardImageNameGenerator(resourceLoader);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void testGenerate(int count) {
        Set<String> randomCardImageNames = generator.generate(count);
        assertEquals(count, randomCardImageNames.size());
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
     * Test that an exception is thrown when the count is too high.
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

    @Test
    void testGeneratePairs() {
        List<String> randomCardImageNamePairs = generator.generatePairs(3);
        assertEquals(6, randomCardImageNamePairs.size());
        assertTrue(cardImageNames.containsAll(randomCardImageNamePairs));
        assertEquals(2, Collections.frequency(randomCardImageNamePairs, "bulbasaur.png"));
        assertEquals(2, Collections.frequency(randomCardImageNamePairs, "charmander.png"));
        assertEquals(2, Collections.frequency(randomCardImageNamePairs, "pikachu.png"));
    }

}
