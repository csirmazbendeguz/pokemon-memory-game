package net.csirmazbendeguz.memory_game.unit.guice;

import com.google.inject.Provider;
import com.google.inject.TypeLiteral;
import com.google.inject.spi.InjectionListener;
import com.google.inject.spi.TypeEncounter;
import net.csirmazbendeguz.memory_game.event.EventHandlers;
import net.csirmazbendeguz.memory_game.event.listeners.CardFlipDownListener;
import net.csirmazbendeguz.memory_game.event.listeners.CardFlipUpListener;
import net.csirmazbendeguz.memory_game.event.listeners.GameEndListener;
import net.csirmazbendeguz.memory_game.event.listeners.GameStartListener;
import net.csirmazbendeguz.memory_game.guice.EventHandlerRegistrator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.EventListener;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EventHandlerRegistratorTest {

    @Mock
    private Provider<EventHandlers> mockEventHandlersProvider;

    @Mock
    private EventHandlers mockEventHandlers;

    private Set<Class<? extends EventListener>> listenerInterfaces = new HashSet<>(Arrays.asList(
        GameStartListener.class,
        CardFlipDownListener.class,
        CardFlipUpListener.class
    ));

    private EventHandlerRegistrator eventHandlerRegistrator;

    @BeforeEach
    void setup() {
        eventHandlerRegistrator = new EventHandlerRegistrator(mockEventHandlersProvider, listenerInterfaces);
    }

    /**
     * Test that no encounter is registered when the type is not one of the given interfaces.
     */
    @Test
    void testNoEncounter() {
        TypeEncounter<GameEndListener> mockEncounter = mock(TypeEncounter.class);
        eventHandlerRegistrator.hear(TypeLiteral.get(GameEndListener.class), mockEncounter);
        verifyZeroInteractions(mockEncounter);
    }

    /**
     * Test that an event handler is registered when the type is one of the given interfaces.
     */
    @Test
    void testRegister() {
        when(mockEventHandlersProvider.get()).thenReturn(mockEventHandlers);

        TypeEncounter<GameStartListener> mockEncounter = mock(TypeEncounter.class);
        eventHandlerRegistrator.hear(TypeLiteral.get(GameStartListener.class), mockEncounter);

        ArgumentCaptor<InjectionListener> injectionListenerCaptor = ArgumentCaptor.forClass(InjectionListener.class);
        verify(mockEncounter).register(injectionListenerCaptor.capture());

        InjectionListener injectionListener = injectionListenerCaptor.getValue();
        GameStartListener mockEventHandler = mock(GameStartListener.class);

        injectionListener.afterInjection(mockEventHandler);
        verify(mockEventHandlers).register(GameStartListener.class, mockEventHandler);
    }

}
