package net.csirmazbendeguz.memory_game.swing.button;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import net.csirmazbendeguz.memory_game.event.EventDispatcher;
import net.csirmazbendeguz.memory_game.event.listeners.GameStartListener;
import net.csirmazbendeguz.memory_game.event.objects.GameStartEvent;
import net.csirmazbendeguz.memory_game.game_state.GameState;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

/**
 * Button to change the dimension.
 */
public class ChangeDimensionButton extends BaseButton implements ActionListener, GameStartListener {

    /**
     * Template for the button's label.
     */
    private static final String LABEL = "%1$sx%1$x >>";

    private GameState gameState;

    /**
     * Construct a button for changing the dimension.
     */
    @Inject
    public ChangeDimensionButton(@Named("buttonBackground") BufferedImage normal, @Named("buttonBackgroundHover") BufferedImage hover, @Named("buttonBackgroundClick") BufferedImage click, EventDispatcher eventDispatcher, GameState gameState) {
        super(normal, hover, click);
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
