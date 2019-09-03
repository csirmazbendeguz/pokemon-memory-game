package net.csirmazbendeguz.memory_game.swing.panels;

import net.csirmazbendeguz.memory_game.event.EventDispatcher;
import net.csirmazbendeguz.memory_game.event.listeners.GameEndListener;
import net.csirmazbendeguz.memory_game.event.objects.GameEndEvent;
import net.csirmazbendeguz.memory_game.util.ResourceLoader;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;

/**
 * Glass pane for the win screen.
 */
public class WinGlassPane extends JPanel implements GameEndListener, MouseListener {

    /**
     * The win screen's background image.
     */
    private BufferedImage background;

    /**
     * The statistics to draw on the win screen.
     */
    private String dimension, seconds, tries;

    /**
     * Whether to render the win screen.
     */
    private boolean shouldRender;

    /**
     * Construct a new glass pane for the win screen.
     */
    public WinGlassPane(ResourceLoader resourceLoader, EventDispatcher eventDispatcher) {
        setBackground(null);
        setOpaque(false);
        addMouseListener(this);
        background = resourceLoader.loadBackogroundImage("Win.png");
        eventDispatcher.addListener(GameEndEvent.class, this);
    }

    /**
     * Show the win screen.
     */
    @Override
    public void gameEnded(GameEndEvent event) {
        // Make the glass pane immediately visible.
        // This will block all the other components from being clicked.
        setVisible(true);

        dimension = String.format("Size: %1$sx%1$s", event.getDimension());
        seconds = String.format("Time: %s seconds", event.getSeconds());
        tries = String.format("Tries: %s", event.getTries());

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                shouldRender = true;
                repaint();
            }
        }, 750);
    }

    /**
     * Draw the win screen.
     */
    @Override
    public void paintComponent(Graphics g) {
        if (!shouldRender) {
            return;
        }

        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(background, 0, 0, getWidth(), getHeight(), null);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Serif", Font.BOLD, 45));
        drawStringToCenter(g2d, dimension, 400);
        drawStringToCenter(g2d, seconds, 500);
        drawStringToCenter(g2d, tries, 600);
    }

    /**
     * Draw the text horizontally centered.
     */
    private void drawStringToCenter(Graphics2D g2d, String text, int y) {
        // Horizontally center the text.
        // Account for the background image not being perfectly centered by shifting the text left a little.
        int x = getWidth() / 2 - g2d.getFontMetrics().stringWidth(text) / 2 - 10;
        g2d.drawString(text, x, y);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        e.consume();
    }

    /**
     * Hide the win screen.
     */
    @Override
    public void mousePressed(MouseEvent e) {
        // Only hide the win screen if it's already visible.
        if (shouldRender) {
            shouldRender = false;
            setVisible(false);
        }
        e.consume();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        e.consume();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        e.consume();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        e.consume();
    }

}
