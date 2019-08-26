package net.csirmazbendeguz.memory_game.swing.panels;

import net.csirmazbendeguz.memory_game.MemoryGame;
import net.csirmazbendeguz.memory_game.game_state.Board;
import net.csirmazbendeguz.memory_game.game_state.Card;
import net.csirmazbendeguz.memory_game.game_state.Stopwatch;
import net.csirmazbendeguz.memory_game.swing.GameFrame;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class CardPanel extends JPanel implements MouseListener, Observer {

    private Card card;

    public CardPanel(Card card) {
        this.card = card;

        Dimension size = new Dimension(100, 100);
        this.setPreferredSize(size);
        this.setMinimumSize(size);
        this.setMaximumSize(size);

        this.addMouseListener(this);
        card.addObserver(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        if (!card.isVisible()) {
            return;
        }
        g.drawImage(card.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
    }

    @Override
    public void mouseClicked(MouseEvent me) {}

    @Override
    public void mousePressed(MouseEvent me) {
        if (card.isVisible() && !card.isFaceUp() && !card.isInAnimation()) {
            Stopwatch.getInstance().startTimer();
            Board board = MemoryGame.gameState.getBoard();
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

    @Override
    public void update(Observable observable, Object o) {
        GameFrame gameFrame = (GameFrame) SwingUtilities.getWindowAncestor(this);
        gameFrame.repaint();
        gameFrame.revalidate();
    }

}
