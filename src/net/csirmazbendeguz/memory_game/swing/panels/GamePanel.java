package net.csirmazbendeguz.memory_game.swing.panels;

import net.csirmazbendeguz.memory_game.game_state.Board;
import net.csirmazbendeguz.memory_game.game_state.Card;
import net.csirmazbendeguz.memory_game.game_state.event.listeners.NewGameListener;
import net.csirmazbendeguz.memory_game.game_state.event.objects.NewGameEvent;
import net.csirmazbendeguz.memory_game.util.ResourceLoader;
import net.csirmazbendeguz.memory_game.swing.GameFrame;
import net.csirmazbendeguz.memory_game.MemoryGame;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class GamePanel extends JPanel implements NewGameListener {

    /**
     * The background image.
     */
    private static final BufferedImage BACKGROUND;

    static {
        BACKGROUND = ResourceLoader.getInstance().loadBackogroundImage("GamePanelBackground.png");
    }

    public GamePanel() {
        this.setBounds(25, 100, 650, 650);
    }

    @Override
    public void newGameStarted(NewGameEvent event) {
        addCardPanels();
    }

    /**
     * Create the card panels based on the board.
     */
    private void addCardPanels() {
        Board board = Board.getInstance();
        Card[][] cards = board.getBoard();
        int dimension = board.getDimension();
        this.removeAll();
        this.setLayout(new GridBagLayout());

        for (int row = 0; row < dimension; ++row) {
            for (int column = 0; column < dimension; ++column) {
                GridBagConstraints constraints = new GridBagConstraints();
                constraints.fill = GridBagConstraints.CENTER;
                constraints.gridx = row;
                constraints.gridy = column;

                CardPanel cardPanel = new CardPanel(cards[row][column]);
                this.add(cardPanel, constraints);
            }
        }

        GameFrame gameFrame = (GameFrame) SwingUtilities.getWindowAncestor(this);
        if (gameFrame != null) {
            gameFrame.validate();
            gameFrame.repaint();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(BACKGROUND, 0, 0, this.getWidth(), this.getHeight(), this);
    }

}
