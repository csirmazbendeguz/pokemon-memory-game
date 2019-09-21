package net.csirmazbendeguz.memory_game.swing.buttons;

import com.google.inject.Inject;
import net.csirmazbendeguz.memory_game.event.EventDispatcher;
import net.csirmazbendeguz.memory_game.event.listeners.GameStartListener;
import net.csirmazbendeguz.memory_game.event.objects.GameStartEvent;
import net.csirmazbendeguz.memory_game.game_state.GameState;
import net.csirmazbendeguz.memory_game.util.ResourceLoader;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Button to change the dimension.
 */
public class ChangeDimensionButton extends PokemonButton implements ActionListener, GameStartListener {

    /**
     * Template for the button's label.
     */
    private static final String LABEL = "%1$sx%1$x >>";

    private GameState gameState;

    /**
     * Construct a button for changing the dimension.
     */
    @Inject
    public ChangeDimensionButton(ResourceLoader resourceLoader, EventDispatcher eventDispatcher, GameState gameState) {
        super(resourceLoader);
        setBounds(50, 775, WIDTH, HEIGHT);
        addActionListener(this);
        eventDispatcher.addListener(GameStartEvent.class, this);
        this.gameState = gameState;
    }

    /**
     * Change the dimension.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        int dimension = gameState.getDimension();
        dimension += 2;
        if (dimension == 8) {
            dimension = 2;
        }
        gameState.newGame(dimension);
    }

    /**
     * Update the button's label.
     */
    @Override
    public void gameStarted(GameStartEvent event) {
        setText(String.format(LABEL, event.getDimension()));
    }

}
