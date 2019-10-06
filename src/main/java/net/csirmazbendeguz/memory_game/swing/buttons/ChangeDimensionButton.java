package net.csirmazbendeguz.memory_game.swing.buttons;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import net.csirmazbendeguz.memory_game.event.listeners.GameStartListener;
import net.csirmazbendeguz.memory_game.event.objects.GameStartEvent;
import net.csirmazbendeguz.memory_game.state.GameState;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

@Singleton
public class ChangeDimensionButton extends BaseButton implements ActionListener, GameStartListener {

    /**
     * Template for the button's label.
     */
    private static final String LABEL = "%1$sx%1$x >>";

    private GameState gameState;

    @Inject
    public ChangeDimensionButton(@Named("buttonBackground") BufferedImage normal, @Named("buttonBackgroundHover") BufferedImage hover, @Named("buttonBackgroundClick") BufferedImage click, GameState gameState) {
        super(normal, hover, click);
        addActionListener(this);
        this.gameState = gameState;
    }

    /**
     * Start a new game with a new dimension.
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        gameState.increaseDimension();
    }

    /**
     * Update the button's label.
     */
    @Override
    public void gameStarted(GameStartEvent event) {
        setText(String.format(LABEL, event.getBoard().getDimension()));
    }

}
