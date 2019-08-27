package net.csirmazbendeguz.memory_game.swing.panels;

import net.csirmazbendeguz.memory_game.game_state.event.listeners.WinListener;
import net.csirmazbendeguz.memory_game.game_state.event.objects.WinEvent;
import net.csirmazbendeguz.memory_game.util.ResourceLoader;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;

/**
 * Container for the win screen.
 */
public class WinPanel extends JPanel implements WinListener {

    /**
     * The container's background image.
     */
    private static final BufferedImage BACKGROUND;

    static {
        BACKGROUND = ResourceLoader.getInstance().loadBackogroundImage("Win.png");
    }

    /**
     * Whether the win screen
     */
    private boolean isVisible = false;
    private int size;
    private String time, tries;

    private Component glassPane;

    public WinPanel(Component glassPane) {
        this.glassPane = glassPane;
        this.glassPane.setBounds(0, 0, 1100, 900);
        this.glassPane.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                e.consume();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                hideWin();
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
        });

        this.setBackground(null);
        this.setOpaque(false);
        this.setBounds(0, 0, 1100, 900);
    }

    /**
     * Hide the win screen.
     */
    private void hideWin() {
        isVisible = false;
        glassPane.setVisible(false);
        repaint();
    }

    /**
     * Show the win screen.
     */
    @Override
    public void gameWon(WinEvent event) {
        glassPane.setVisible(true);

        size = event.getDimension();
        time = String.valueOf(event.getSeconds());
        tries = String.valueOf(event.getTries());

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                isVisible = true;
                repaint();
            }
        }, 1000);
    }

    /**
     * Draw the win screen.
     */
    @Override
    public void paintComponent(Graphics g) {
        if (!isVisible) {
            return;
        }

        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(BACKGROUND, 0, 0, getWidth(), getHeight(), null);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Serif", Font.BOLD, 45));
        drawStringToCenter(g2d, String.format("Size: %1$sx%1$s", size), 400);
        drawStringToCenter(g2d, String.format("Time: %s seconds", time), 500);
        drawStringToCenter(g2d, String.format("Tries: %s", tries), 600);
    }

    /**
     * Draw the text horizontally centered.
     */
    private void drawStringToCenter(Graphics2D g2d, String str, int y) {
        // Horizontally center the text.
        // Account for the background image not being perfectly centered by shifting the text left a little.
        int x = getWidth() / 2 - g2d.getFontMetrics().stringWidth(str) / 2 - 10;
        g2d.drawString(str, x, y);
    }

}
