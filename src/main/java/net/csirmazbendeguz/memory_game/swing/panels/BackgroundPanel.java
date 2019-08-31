package net.csirmazbendeguz.memory_game.swing.panels;

import net.csirmazbendeguz.memory_game.util.ResourceLoader;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 * Container for the game's background image.
 */
public class BackgroundPanel extends JPanel {

    /**
     * The background image.
     */
    private static final BufferedImage IMAGE;

    static {
        IMAGE = ResourceLoader.getInstance().loadBackogroundImage("Background.jpg");
    }

    /**
     * Draw the background image.
     */
    @Override
    public void paintComponent(Graphics g) {
        Container parent = getParent();
        g.drawImage(IMAGE, 0, 0, parent.getWidth(), parent.getHeight(), null);
    }

}
