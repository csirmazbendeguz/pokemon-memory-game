package net.csirmazbendeguz.memory_game.swing.labels;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Base class for the game's labels.
 */
abstract class BaseLabel extends JLabel {

    /**
     * The label dimensions.
     */
    static final int WIDTH = 150, HEIGHT = 50;

    /**
     * The background image.
     */
    private BufferedImage background;

    /**
     * Initialize the label.
     *
     * @param background The background image.
     */
    BaseLabel(BufferedImage background) {
        this.background = background;
        setForeground(Color.BLACK);
        setFont(new Font("Serif", Font.PLAIN, 25));
        // Add a small left padding so the label's text doesn't overlap the background image.
        setBorder(new EmptyBorder(0, 15, 0, 0));
    }

    /**
     * Draw the label.
     */
    @Override
    protected void paintComponent(Graphics graphics) {
        graphics.drawImage(background, 0, 0, getWidth(), getHeight(), null);
        super.paintComponent(graphics);
    }

}
