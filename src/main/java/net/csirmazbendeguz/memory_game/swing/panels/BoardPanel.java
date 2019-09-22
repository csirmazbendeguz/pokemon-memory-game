package net.csirmazbendeguz.memory_game.swing.panels;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import net.csirmazbendeguz.memory_game.event.EventDispatcher;
import net.csirmazbendeguz.memory_game.game_state.Card;
import net.csirmazbendeguz.memory_game.event.listeners.GameStartListener;
import net.csirmazbendeguz.memory_game.event.objects.GameStartEvent;
import net.csirmazbendeguz.memory_game.swing.GameFrame;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

/**
 * Container for the board.
 */
public class BoardPanel extends JPanel implements GameStartListener {

    /**
     * The container's background image.
     */
    private BufferedImage background;

    /**
     * Initialize the board.
     */
    @Inject
    public BoardPanel(@Named("boardBackground") BufferedImage background, EventDispatcher eventDispatcher) {
        this.background = background;
        eventDispatcher.addListener(GameStartEvent.class, this);
        setLayout(new GridBagLayout());
    }

    /**
     * Initialize the board.
     */
    @Override
    public void gameStarted(GameStartEvent event) {
        Card[][] cards = event.getCards();
        int dimension = event.getDimension();
        removeAll();

        for (int row = 0; row < dimension; ++row) {
            for (int column = 0; column < dimension; ++column) {
                GridBagConstraints constraints = new GridBagConstraints();
                constraints.fill = GridBagConstraints.CENTER;
                constraints.gridx = row;
                constraints.gridy = column;

                CardPanel cardPanel = new CardPanel(cards[row][column]);
                add(cardPanel, constraints);
            }
        }

        GameFrame gameFrame = (GameFrame) SwingUtilities.getWindowAncestor(this);
        gameFrame.validate();
        gameFrame.repaint();
    }

    /**
     * Draw the container's background image.
     */
    @Override
    public void paintComponent(Graphics graphics) {
        graphics.drawImage(background, 0, 0, getWidth(), getHeight(), null);
    }

}
