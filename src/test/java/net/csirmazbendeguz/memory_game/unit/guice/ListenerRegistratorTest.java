package net.csirmazbendeguz.memory_game.unit.guice;

import com.google.inject.Provider;
import com.google.inject.TypeLiteral;
import com.google.inject.spi.InjectionListener;
import com.google.inject.spi.TypeEncounter;
import net.csirmazbendeguz.memory_game.event.EventListeners;
import net.csirmazbendeguz.memory_game.event.listeners.CardFlipDownListener;
import net.csirmazbendeguz.memory_game.event.listeners.CardFlipUpListener;
import net.csirmazbendeguz.memory_game.event.listeners.GameEndListener;
import net.csirmazbendeguz.memory_game.event.listeners.GameStartListener;
import net.csirmazbendeguz.memory_game.guice.ListenerRegistrator;
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
class ListenerRegistratorTest {

    @Mock
    private EventListeners mockEventListeners;

    @Mock
    private Provider<EventListeners> mockEventListenersProvider;

    private Set<Class<? extends EventListener>> listenerInterfaces = new HashSet<>(Arrays.asList(
        GameStartListener.class,
        CardFlipDownListener.class,
        CardFlipUpListener.class
    ));

    private ListenerRegistrator listenerRegistrator;

    @BeforeEach
    void setup() {
        listenerRegistrator = new ListenerRegistrator(mockEventListenersProvider, listenerInterfaces);
    }

    @Test
    void testNoListenerRegistered() {
        TypeEncounter<GameEndListener> mockEncounter = mock(TypeEncounter.class);
        listenerRegistrator.hear(TypeLiteral.get(GameEndListener.class), mockEncounter);
        verifyZeroInteractions(mockEncounter);
    }

    @Test
    void testListenerRegistered() {
        when(mockEventListenersProvider.get()).thenReturn(mockEventListeners);

        TypeEncounter<GameStartListener> mockEncounter = mock(TypeEncounter.class);
        listenerRegistrator.hear(TypeLiteral.get(GameStartListener.class), mockEncounter);

        ArgumentCaptor<InjectionListener> injectionListenerCaptor = ArgumentCaptor.forClass(InjectionListener.class);
        verify(mockEncounter).register(injectionListenerCaptor.capture());

        InjectionListener injectionListener = injectionListenerCaptor.getValue();
        GameStartListener mockListener = mock(GameStartListener.class);

        injectionListener.afterInjection(mockListener);
        verify(mockEventListeners).register(GameStartListener.class, mockListener);
    }

}
