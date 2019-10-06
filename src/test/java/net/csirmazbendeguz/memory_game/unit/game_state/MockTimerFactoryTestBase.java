package net.csirmazbendeguz.memory_game.unit.game_state;

import net.csirmazbendeguz.memory_game.game_state.TimerFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Timer;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MockTimerFactoryTestBase {

    @Mock
    TimerFactory mockTimerFactory;

    Timer mockTimer() {
        Timer mockTimer = mock(Timer.class);
        when(mockTimerFactory.create()).thenReturn(mockTimer);
        return mockTimer;
    }

}
