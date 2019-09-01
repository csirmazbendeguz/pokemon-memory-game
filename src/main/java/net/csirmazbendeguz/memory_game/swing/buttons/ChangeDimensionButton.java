package net.csirmazbendeguz.memory_game.swing.buttons;

import net.csirmazbendeguz.memory_game.MemoryGame;
import net.csirmazbendeguz.memory_game.event.EventDispatcher;
import net.csirmazbendeguz.memory_game.event.listeners.GameStartListener;
import net.csirmazbendeguz.memory_game.event.objects.GameStartEvent;
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

    /**
     * Construct a button for changing the dimension.
     */
    public ChangeDimensionButton(ResourceLoader resourceLoader, EventDispatcher eventDispatcher) {
        super(resourceLoader);
        setBounds(50, 775, WIDTH, HEIGHT);
        addActionListener(this);
        eventDispatcher.addListener(GameStartEvent.class, this);
    }

    /**
     * Change the dimension.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        int dimension = MemoryGame.gameState.getBoard().getDimension();
        dimension += 2;
        if (dimension == 8) {
            dimension = 2;
        }
        MemoryGame.gameState.newGame(dimension);
    }

    /**
     * Update the button's label.
     */
    @Override
    public void gameStarted(GameStartEvent event) {
        setText(String.format(LABEL, event.getBoard().getDimension()));
    }

}
