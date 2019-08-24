package net.csirmazbendeguz.memory_game.swing.panels;

import net.csirmazbendeguz.memory_game.game_state.Board;
import net.csirmazbendeguz.memory_game.game_state.Card;
import net.csirmazbendeguz.memory_game.game_state.TriesCounter;
import net.csirmazbendeguz.memory_game.utils.ResourceLoader;
import net.csirmazbendeguz.memory_game.game_state.Stopwatch;
import net.csirmazbendeguz.memory_game.swing.GameFrame;
import net.csirmazbendeguz.memory_game.MemoryGame;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class GamePanel extends JPanel {

    private CardPanel[][] cardPanels;
    private int size = MemoryGame.DEFAULT_BOARD_DIMENSION;
    private BufferedImage image;

    public GamePanel() {
        this.setBackground(Color.yellow);
        this.setBounds(25, 100, 650, 650);

        image = ResourceLoader.getInstance().loadBackogroundImage("GamePanelBackground.png");

        initGame();
    }

    public int get_size() {
        return size;
    }

    private void initGame() {
        this.removeAll();
        Board.getInstance().newGame(size);
        cardPanels = new CardPanel[size][size];
        initPanel(size);
    }

    public void newGame(int size) {
        this.size = size;
        initGame();

        Stopwatch stopwatch = Stopwatch.getInstance();
        stopwatch.stopTimer();
        stopwatch.resetSeconds();
        TriesCounter.getInstance().reset();

        GameFrame f = (GameFrame) SwingUtilities.getWindowAncestor(this);
        f.validate();
        f.repaint();
    }

    private void initPanel(int size) {
        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        Card[][] board = Board.getInstance().getBoard();

        for(int row = 0; row < size; ++row) {
            for(int column = 0; column < size; ++column) {
                GridBagConstraints constraints = new GridBagConstraints();
                constraints.fill = GridBagConstraints.CENTER;
                constraints.gridx = row;
                constraints.gridy = column;

                CardPanel cardPanel = new CardPanel(board[row][column]);
                cardPanels[row][column] = cardPanel;
                this.add(cardPanel, constraints);
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
    }

}
