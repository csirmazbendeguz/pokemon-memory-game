package net.csirmazbendeguz.memory_game.swing.panels;

import net.csirmazbendeguz.memory_game.game_state.Board;
import net.csirmazbendeguz.memory_game.game_state.Card;
import net.csirmazbendeguz.memory_game.game_state.GameState;
import net.csirmazbendeguz.memory_game.game_state.Stopwatch;
import net.csirmazbendeguz.memory_game.swing.GameFrame;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * Container for memory game cards.
 */
public class CardPanel extends JPanel implements MouseListener, Observer {

    /**
     * The card state.
     */
    private Card card;

    private GameState gameState;

    private Stopwatch stopwatch;

    /**
     * Construct a container for memory game cards.
     *
     * @param card The card state.
     */
    public CardPanel(Card card, GameState gameState, Stopwatch stopwatch) {
        this.card = card;

        Dimension size = new Dimension(100, 100);
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);

        addMouseListener(this);
        card.addObserver(this);
        this.gameState = gameState;
        this.stopwatch = stopwatch;
    }

    /**
     * Draw the card.
     */
    @Override
    public void paintComponent(Graphics g) {
        if (!card.isVisible()) {
            return;
        }
        g.drawImage(card.getImage(), 0, 0, getWidth(), getHeight(), null);
    }

    @Override
    public void mouseClicked(MouseEvent me) {}

    /**
     * Handle the card click event.
     */
    @Override
    public void mousePressed(MouseEvent me) {
        if (card.isVisible() && !card.isFaceUp() && !card.isInAnimation()) {
            card.flipUp();
            stopwatch.startTimer();
            Board board = gameState.getBoard();
            board.flipUp(card);
            board.checkPairs();
        }
    }

    @Override
    public void mouseReleased(MouseEvent me) {}

    @Override
    public void mouseEntered(MouseEvent me) {}

    @Override
    public void mouseExited(MouseEvent me) {}

    /**
     * Redraw the card.
     */
    @Override
    public void update(Observable observable, Object o) {
        GameFrame gameFrame = (GameFrame) SwingUtilities.getWindowAncestor(this);
        gameFrame.repaint();
        gameFrame.revalidate();
    }

}
