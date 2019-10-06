package net.csirmazbendeguz.memory_game.swing.panels;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import net.csirmazbendeguz.memory_game.event.EventDispatcher;
import net.csirmazbendeguz.memory_game.game_state.Board;
import net.csirmazbendeguz.memory_game.game_state.Card;
import net.csirmazbendeguz.memory_game.event.listeners.GameStartListener;
import net.csirmazbendeguz.memory_game.event.objects.GameStartEvent;
import net.csirmazbendeguz.memory_game.game_state.CardImageCache;
import net.csirmazbendeguz.memory_game.swing.GameFrame;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

/**
 * Container for the board.
 */
@Singleton
public class BoardPanel extends JPanel implements GameStartListener {

    /**
     * The board dimension.
     */
    private static final Dimension SIZE = new Dimension(650, 650);

    /**
     * The container's background image.
     */
    private BufferedImage background;

    private CardPanelFactory cardPanelFactory;

    /**
     * Initialize the board.
     */
    @Inject
    public BoardPanel(@Named("boardBackground") BufferedImage background, CardPanelFactory cardPanelFactory) {
        this.background = background;
        setLayout(new GridBagLayout());
        this.cardPanelFactory = cardPanelFactory;
    }

    /**
     * Initialize the board.
     */
    @Override
    public void gameStarted(GameStartEvent event) {
        Board board = event.getBoard();
        int dimension = board.getDimension();
        removeAll();

        for (int row = 0; row < dimension; ++row) {
            for (int column = 0; column < dimension; ++column) {
                GridBagConstraints constraints = new GridBagConstraints();
                constraints.fill = GridBagConstraints.CENTER;
                constraints.gridx = row;
                constraints.gridy = column;

                CardPanel cardPanel = cardPanelFactory.create(board.getCard(row, column));
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

    @Override
    public Dimension getMinimumSize() {
        return SIZE;
    }

    @Override
    public Dimension getPreferredSize() {
        return SIZE;
    }

    @Override
    public Dimension getMaximumSize() {
        return SIZE;
    }

}
