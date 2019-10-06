package net.csirmazbendeguz.memory_game.swing.panels;

import net.csirmazbendeguz.memory_game.event.EventListeners;
import net.csirmazbendeguz.memory_game.event.listeners.CardFlipDownListener;
import net.csirmazbendeguz.memory_game.event.listeners.CardFlipUpListener;
import net.csirmazbendeguz.memory_game.event.listeners.CardHideListener;
import net.csirmazbendeguz.memory_game.event.objects.CardFlipDownEvent;
import net.csirmazbendeguz.memory_game.event.objects.CardFlipUpEvent;
import net.csirmazbendeguz.memory_game.event.objects.CardHideEvent;
import net.csirmazbendeguz.memory_game.game_state.Card;
import net.csirmazbendeguz.memory_game.swing.DefaultMouseListener;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.*;

/**
 * Container for memory game cards.
 */
public class CardPanel extends JPanel implements DefaultMouseListener, CardFlipUpListener, CardFlipDownListener, CardHideListener {

    /**
     * The card dimension.
     */
    private static final Dimension SIZE = new Dimension(100, 100);

    private EventListeners eventListeners;

    /**
     * The card state.
     */
    private Card card;

    private BufferedImage cardFront, cardBack;

    /**
     * Construct a container for memory game cards.
     *
     * @param card The card state.
     */
    public CardPanel(Card card, BufferedImage cardFront, BufferedImage cardBack, EventListeners eventListeners) {
        this.card = card;
        this.cardFront = cardFront;
        this.cardBack = cardBack;
        addMouseListener(this);
        // The panels are not created by Guice, so the event listeners must be added manually.
        eventListeners.register(CardFlipUpListener.class, this);
        eventListeners.register(CardFlipDownListener.class, this);
        eventListeners.register(CardHideListener.class, this);
        this.eventListeners = eventListeners;
    }

    /**
     * Draw the card.
     */
    @Override
    public void paintComponent(Graphics graphics) {
        if (!card.isVisible()) {
            return;
        }
        graphics.drawImage(card.isFaceUp() ? cardFront : cardBack, 0, 0, getWidth(), getHeight(), null);
    }

    /**
     * Flip up the card.
     */
    @Override
    public void mousePressed(MouseEvent me) {
        if (card.canFlipUp()) {
            card.flipUp();
        }
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

    @Override
    public void cardFlippedUp(CardFlipUpEvent event) {
        if (event.getCard() == card) {
            update();
        }
    }

    @Override
    public void cardFlippedDown(CardFlipDownEvent event) {
        if (event.getCard() == card) {
            update();
        }
    }

    @Override
    public void cardHidden(CardHideEvent event) {
        if (event.getCard() == card) {
            update();
        }
    }

    public void update() {
        Window window = SwingUtilities.getWindowAncestor(this);
        window.repaint();
        window.revalidate();
    }

    @Override
    public void removeNotify() {
        eventListeners.remove(CardFlipUpListener.class, this);
        eventListeners.remove(CardFlipDownListener.class, this);
        eventListeners.remove(CardHideListener.class, this);
        super.removeNotify();
    }

}
