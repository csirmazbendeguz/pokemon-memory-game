package net.csirmazbendeguz.memory_game.swing.labels;

import net.csirmazbendeguz.memory_game.util.ResourceLoader;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;


/**
 * Base class for labels with background images.
 */
abstract class PokemonLabel extends JLabel {

    /**
     * The background image.
     */
    private BufferedImage background;

    PokemonLabel(ResourceLoader resourceLoader) {
        background = resourceLoader.loadBackogroundImage("Label-Bg.png");
        // Add a small left padding so the label's text doesn't overlap the background image.
        setBorder(new EmptyBorder(0, 15, 0, 0));
    }

    /**
     * Draw the background image.
     */
    @Override
    protected void paintComponent(Graphics graphics) {
        graphics.drawImage(background, 0, 0, getWidth(), getHeight(), null);
        super.paintComponent(graphics);
    }

}
