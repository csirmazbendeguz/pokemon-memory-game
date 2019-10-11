package net.csirmazbendeguz.memory_game.unit.state;

import net.csirmazbendeguz.memory_game.event.EventDispatcher;
import net.csirmazbendeguz.memory_game.event.objects.GameStartEvent;
import net.csirmazbendeguz.memory_game.state.Board;
import net.csirmazbendeguz.memory_game.state.GameState;
import net.csirmazbendeguz.memory_game.util.random.RandomCardGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GameStateTest {

    @Mock
    private Board mockBoard;

    @Mock
    private RandomCardGenerator mockRandomCardGenerator;

    @Mock
    private EventDispatcher mockEventDispatcher;

    private GameState gameState;

    @BeforeEach
    void setup() {
        gameState = new GameState(mockBoard, mockRandomCardGenerator, mockEventDispatcher);
    }

    @Test
    void testNewGame() {
        gameState.newGame();
        assertGameStartEvent(4);
    }

    @Test
    void testRestartGame() {
        when(mockBoard.getDimension()).thenReturn(6);
        gameState.restartGame();
        assertGameStartEvent(6);
    }

    @Test
    void testIncrementDimension() {
        when(mockBoard.getDimension()).thenReturn(6);
        gameState.increaseDimension();
        assertGameStartEvent(2);
    }

    void assertGameStartEvent(int dimension) {
        verify(mockRandomCardGenerator).generate(dimension);
        ArgumentCaptor<GameStartEvent> eventCaptor = ArgumentCaptor.forClass(GameStartEvent.class);
        verify(mockEventDispatcher).dispatch(eventCaptor.capture());
        GameStartEvent event = eventCaptor.getValue();
        assertSame(dimension, event.getDimension());
    }

}
